package com.bjpowernode.api.service;

import com.bjpowernode.api.pojo.BidInfoProduct;

import java.util.List;

public interface InvestService {
    /**
     * 查询某个产品的投资记录
     */
    List<BidInfoProduct> queryBidListByProductId(Integer productId, Integer pageNo, Integer pageSize);
}
