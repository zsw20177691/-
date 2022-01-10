package com.zhuquan.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuquan.domain.Delivery;
import com.zhuquan.mapper.DeliveryMapper;
import com.zhuquan.util.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeliveryService {

    @Resource
    private DeliveryMapper deliveryMapper;

    public void add(Delivery delivery) {
        deliveryMapper.insertSelective(delivery);
    }
    //分页查询
    public PageResult<Delivery> findDeliveryByPage(Delivery delivery, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        // 查询条件
        Example example = new Example(Delivery.class);
        Example.Criteria criteria = example.createCriteria();
        if (delivery.getCompanyId() != null){
            criteria.andEqualTo("companyId", delivery.getCompanyId());
        }
        if (delivery.getUserId() != null){
            criteria.andEqualTo("userId", delivery.getUserId());
        }
        if (delivery.getStatus() != null){
            criteria.andEqualTo("status", delivery.getStatus());
        }
        if (StringUtils.isNotBlank(delivery.getPositionName())){
            criteria.andLike("positionName", "%" + delivery.getPositionName() + "%");
        }
        example.orderBy("createDate").desc();

        // 查询
        List<Delivery> deliveries = deliveryMapper.selectByExample(example);

        PageInfo<Delivery> pageInfo = new PageInfo<>(deliveries);
        return new PageResult(pageInfo.getTotal(), deliveries);
    }

    public void edit(Delivery delivery) {
        deliveryMapper.updateByPrimaryKeySelective(delivery);

    }
}
