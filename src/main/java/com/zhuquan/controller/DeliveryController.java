package com.zhuquan.controller;

import com.zhuquan.domain.Delivery;
import com.zhuquan.domain.User;
import com.zhuquan.enums.StatusType;
import com.zhuquan.service.DeliveryService;
import com.zhuquan.util.PageResult;
import com.zhuquan.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/***
 * @Date 2021/3/14 16:25
 * @Desc  处理求职信息 请求
 */
@RestController
@RequestMapping("/delivery")
public class DeliveryController {

    @Autowired
    private DeliveryService deliveryService;


    /***
     * @Date 2021/3/14 16:25
     * @Desc  新增 求职信息
     */
    @RequestMapping("/add")
    public void add(@RequestBody Delivery delivery){
        delivery.setUserId(UserUtil.getUser().getId());
        delivery.setCreateDate(new Date());
        delivery.setStatus(StatusType.NORMAL.getValue());
        deliveryService.add(delivery);
    }

    /***
     * @Date 2021/3/14 16:41
     * @Desc  公司查询投递本公司的求职信息
     */
    @RequestMapping("/company")
    public PageResult<Delivery> findDeliveryByCompanyPage(Delivery delivery,
                                                          @RequestParam(defaultValue = "1") Integer page,
                                                          @RequestParam(defaultValue = "9") Integer rows){
        delivery.setCompanyId(UserUtil.getUser().getId());
        return deliveryService.findDeliveryByPage(delivery, page, rows);
    }

    /***
     * @Date 2021/3/14 16:41
     * @Desc  学生查询自己 已投递的求职
     */
    @RequestMapping("/list")
    public PageResult<Delivery> findDeliveryByPage(Delivery delivery,
                                                          @RequestParam(defaultValue = "1") Integer page,
                                                          @RequestParam(defaultValue = "9") Integer rows){
        delivery.setUserId(UserUtil.getUser().getId());
        return deliveryService.findDeliveryByPage(delivery, page, rows);
    }

    /***
     * @Date 2021/3/14 19:07
     * @Desc  修改
     */
    @RequestMapping("/edit")
    public void edit(@RequestBody Delivery delivery){
        System.out.println(delivery.getStatus());
        delivery.setCompanyId(UserUtil.getUser().getId());
        deliveryService.edit(delivery);
    }

}
