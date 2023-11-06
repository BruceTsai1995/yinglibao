package com.bjpowernode.dataservice.mapper;

import com.bjpowernode.api.model.ProductInfo;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface ProductInfoMapper {
    /*收益率平均值*/
    BigDecimal selectAvgRate();

    /*按照产品类型分页查询*/
    List<ProductInfo> selectByTypeLimit(@Param("ptype") Integer ptype,
                                        @Param("offset") Integer offset,
                                        @Param("rows") Integer rows);

    /*某个产品类型的记录总数*/
    Integer selectCountByType(@Param("ptype")Integer pType);

    int deleteByPrimaryKey(Integer id);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);


}