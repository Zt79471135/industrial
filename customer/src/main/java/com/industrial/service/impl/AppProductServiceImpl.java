package com.industrial.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.industrial.common.dto.ProductDto;
import com.industrial.common.exception.ServiceException;
import com.industrial.common.pojo.ProductExcel;
import com.industrial.common.utils.StringUtils;
import com.industrial.common.vo.ProductVo;
import com.industrial.domin.*;
import com.industrial.mapper.*;
import com.industrial.service.AppProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhu
 * @date 2021年12月24日 9:18
 */
@Service
public class AppProductServiceImpl implements AppProductService {
    @Resource
    private AppProductMapper productMapper;
    @Resource
    private AppProductFileMapper productFileMapper;
    @Resource
    private DictDataMapper dictDataMapper;
    @Resource
    private AppCategoryMapper categoryMapper;
    @Resource
    private AppImageFileMapper imageFileMapper;
    @Resource
    private UserMapper userMapper;
    public static final Byte PUTAWAY_STATUS = 3;
    /**
     * 商品类别
     */
    public static final String DICT_CATE_TYPE = "dict_cate_type";
    /**
     * 商品单位
     */
    public static final String DICT_UNIT = "dict_unit";


    /**
     * 更改商品状态
     *
     * @param status
     * @return
     */
    @Override
    public boolean changeStatus(int status, Integer productId) {
        com.industrial.domin.AppProduct product = new com.industrial.domin.AppProduct();
        product.setStatus((byte) status);
        product.setId(productId);
        QueryWrapper<com.industrial.domin.AppProduct> qw = new QueryWrapper<>();
        qw.lambda().eq(com.industrial.domin.AppProduct::getDeleted, 0);
        return productMapper.update(product, qw) == 1;
    }

    /**
     * 通过商品ID检索商品详情
     *
     * @param productId
     * @return
     */
    @Override
    public ProductDto selectProductById(Integer productId) {
        ProductDto productDto = new ProductDto();
        com.industrial.domin.AppProduct product = productMapper.selectById(productId);
        Integer categoryId = product.getCategoryId();
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    /**
     * 根据状态检索商品
     *
     * @param status
     * @return
     */
    @Override
    public List<ProductDto> selectProductByStatus(int status) {
        QueryWrapper<com.industrial.domin.AppProduct> qw = new QueryWrapper<>();
        qw.lambda().eq(com.industrial.domin.AppProduct::getStatus, (byte) status);
        /**
         * 当查询上架商品时,同样把禁用商品一起查询出来
         */
        if (status == PUTAWAY_STATUS) {
            qw.lambda().eq(com.industrial.domin.AppProduct::getStatus, (byte) 0);
        }
        List<com.industrial.domin.AppProduct> productList = productMapper.selectList(qw);
        if (productList.size() == 0) {
            throw new ServiceException("无此状态的商品");
        } else {
            List<ProductDto> productDtoList = productList.stream().map(this::apply).collect(Collectors.toList());
            return productDtoList;
        }
    }

    /**
     * 商品保存
     * 成功待审核
     *
     * @param productVo
     * @return
     */
    @Transactional(rollbackFor = ServiceException.class)
    @Override
    public boolean insert(ProductVo productVo) {
        com.industrial.domin.AppProduct product = new com.industrial.domin.AppProduct();
        BeanUtils.copyProperties(productVo, product);
        List<Integer> imgIds = productVo.getImgIds();
        if (imgIds.size() > 0) {
            Integer integer = imgIds.get(0);
            AppImageFile imageFile = imageFileMapper.selectById(integer);
            String filePath = imageFile.getFilePath();
            product.setMainImgUrl(filePath);
        }
        if (productMapper.insert(product) == 1) {
            AppProductFile productFile = new AppProductFile();
            productFile.setProductId(product.getId());
            List<Integer> integerList = imgIds.stream().map(id -> {
                productFile.setFileId(id);
                return productFileMapper.insert(productFile);
            }).collect(Collectors.toList());
            return imgIds.size() == integerList.size();
        } else {
            throw new ServiceException();
        }
    }

    /**
     * 删除
     *
     * @param productId
     * @return
     */
    @Override
    public boolean remove(Integer productId) {
        return productMapper.deleteById(productId) == 1;
    }

    /**
     * 商品更新
     *
     * @param productVo
     * @return
     */
    @Override
    public boolean update(ProductVo productVo) {
        Integer id = productVo.getId();
        if (id != 0) {
            com.industrial.domin.AppProduct product = new com.industrial.domin.AppProduct();
            BeanUtils.copyProperties(productVo, product);
            product.setStatus((byte) 1);
            QueryWrapper<com.industrial.domin.AppProduct> qw = new QueryWrapper<>();
            qw.lambda().eq(com.industrial.domin.AppProduct::getId, id);
            return productMapper.update(product, qw) == 1;
        } else {
            throw new ServiceException("id不能为空");
        }

    }

    /**
     * 根据分类id或者商品名称查询
     *
     * @param categoryId
     * @param productName
     * @param status
     * @return
     */
    @Override
    public List<ProductDto> selectProductByCategoryId(Integer categoryId, String productName, int status) {
        QueryWrapper<com.industrial.domin.AppProduct> qw = new QueryWrapper<>();
        if (categoryId != null || !"".equals(productName)) {
            if (categoryId != null) {
                qw.lambda().eq(com.industrial.domin.AppProduct::getCategoryId, categoryId);
            }
            if (!"".equals(productName)) {
                qw.lambda().like(com.industrial.domin.AppProduct::getName, productName);
            }
        }
        String sql = "and (status = " + status + " or status =" + 0;
        List<com.industrial.domin.AppProduct> productList = productMapper.selectList(qw);
        if (productList.size() == 0) {
            //throw new ServiceException("无此状态的商品");
            List<ProductDto> productDtoList = productList.stream().map(this::apply).collect(Collectors.toList());
            return productDtoList;
        } else {
            List<ProductDto> productDtoList = productList.stream().map(this::apply).collect(Collectors.toList());
            return productDtoList;
        }


    }
    @Override
    public boolean putaway(List<Integer> ids, byte status) {
        int size = ids.size();
        if (size > 0) {
            List<Integer> integerList = ids.stream().map(id -> {
                if (!changeStatus(status, id)) {
                    throw new ServiceException();
                }
                return 1;
            }).collect(Collectors.toList());
            return integerList.size() == size;
        } else {
            throw new ServiceException();
        }
    }
    @Override
    public List<ProductExcel> selectProductExcelList(ProductVo productVo) {
        Integer id = productVo.getId();
        Integer categoryId = productVo.getCategoryId();
        String contacts = productVo.getContacts();
        Integer createUserId = productVo.getCreateUserId();
        BigDecimal floorPrice = productVo.getFloorPrice();
        String name = productVo.getName();
        String maintenance = productVo.getMaintenance();
        QueryWrapper<com.industrial.domin.AppProduct> qw = new QueryWrapper<>();
        if (id != null && id != 0) {
            qw.lambda().eq(com.industrial.domin.AppProduct::getId, id);
        }
        if (categoryId != null && categoryId != 0) {
            qw.lambda().eq(com.industrial.domin.AppProduct::getCategoryId, categoryId);
        }
        if (contacts != null && !"".equals(contacts)) {
            qw.lambda().like(com.industrial.domin.AppProduct::getContacts, contacts);
        }
        if (createUserId != null && createUserId != 0) {
            qw.lambda().eq(com.industrial.domin.AppProduct::getCreateUserId, createUserId);
        }
        if (floorPrice != null) {
            qw.lambda().eq(com.industrial.domin.AppProduct::getFloorPrice, floorPrice);
        }
        if (name != null && !"".equals(name)) {
            qw.lambda().like(com.industrial.domin.AppProduct::getName, name);
        }
        if (maintenance != null && !"".equals(maintenance)) {
            qw.lambda().eq(com.industrial.domin.AppProduct::getMaintenance, maintenance);
        }
        List<AppProduct> productList = productMapper.selectList(qw);
        return productList.stream().map(this::getProductExcel).collect(Collectors.toList());
    }

    /**
     * 导入用户数据
     *
     * @param productList 用户数据列表
     * @param productList
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @param operName 操作用户
     * @return 结果
     */
    @Override
    public String importData(List<AppProduct> productList, Boolean isUpdateSupport, String operName)
    {
        if (StringUtils.isNull(productList) || productList.size() == 0)
        {
            throw new ServiceException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        List<ProductExcel> existList = selectProductExcelList(null);
        for (AppProduct product : productList)
        {
            try {

                boolean userFlag = false;
                for (ProductExcel entry : existList) {
                    if (entry.getName().equals(product.getName())) {
                        userFlag = true;
                        break;
                    }
                }
                if (!userFlag) {
                    insertProductExcelList(product);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、数据 " + product.getName() + " 导入成功");
                } else if (isUpdateSupport) {
                    updateProductExcelList(product);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、数据 " + product.getName() + " 更新成功");
                } else {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、数据 " + product.getName() + " 已存在");
                }
            }catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、账号 " + product.getName() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new ServiceException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    private void updateProductExcelList(AppProduct product) {
        productMapper.insert(product);
    }

    private void insertProductExcelList(AppProduct product) {
        productMapper.updateById(product);
    }


    private ProductDto apply(com.industrial.domin.AppProduct product) {
        ProductDto productDto = new ProductDto();
        QueryWrapper<DictData> queryWrapper = null;
        /**
         *查询商品分类
         */
        AppCategory category = categoryMapper.selectAppCategoryById((long)product.getCategoryId());
        /**
         * 查询商品单位
         */
        queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().eq(DictData::getDictType, DICT_UNIT);
        queryWrapper.lambda().eq(DictData::getDictValue, product.getUnitId());
        DictData dictData = dictDataMapper.selectOne(queryWrapper);
        String dictLabel = dictData.getDictLabel();
        productDto.setUnitName(dictLabel);
        productDto.setCategory(category);
        BeanUtils.copyProperties(product, productDto);
        return productDto;
    }

    private ProductExcel getProductExcel(com.industrial.domin.AppProduct product) {
        ProductExcel productExcel = new ProductExcel();
        String categoryName = categoryMapper.selectAppCategoryById((long)product.getCategoryId()).getCategoryName();
        productExcel.setCategoryName(categoryName);
        String userName = userMapper.selectById(product.getCreateUserId()).getUserName();
        productExcel.setContactsUserName(userName);
        productExcel.setFloorPrice(String.valueOf(product.getFloorPrice()));
        productExcel.setMaintenance(product.getMaintenance());
        productExcel.setPrice(String.valueOf(product.getPrice()));
        productExcel.setCreateTime(String.valueOf(product.getCreateTime()));
        productExcel.setId(product.getId());
        productExcel.setName(product.getName());
        productExcel.setStock(product.getStock());
        productExcel.setSpecifica(product.getSpecifica());
        if (product.getStatus() == 0) {
            productExcel.setStatus("启用");
        } else {
            productExcel.setStatus("禁用");
        }
        return productExcel;
    }
}
