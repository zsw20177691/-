package com.zhuquan.controller;

import com.zhuquan.domain.*;
import com.zhuquan.enums.StatusType;
import com.zhuquan.service.RecruitService;
import com.zhuquan.util.PageResult;
import com.zhuquan.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/***
 * @Date 2021/3/9 16:22
 * @Desc  招聘信息管理
 */
@RestController
@RequestMapping("/recruit")
public class RecruitController {

    @Autowired
    private RecruitService recruitService;

    /***
     * @Date 2021/3/8 20:22
     * @Desc  新增
     */
    @RequestMapping("/add")
    public void addRecruit(RecruitInfo recruitInfo){
        User user = UserUtil.getUser();
        recruitInfo.setCreateDate(new Date());
        recruitInfo.setCompanyId(user.getId());
        recruitInfo.setStatus(StatusType.NORMAL.getValue());
        recruitService.addRecruit(recruitInfo);
    }

    /***
     * @Date 2021/3/8 20:22
     * @Desc  查询
     */
    @RequestMapping("/list")
    public PageResult<RecruitInfo> findRecruitByPage(RecruitInfo recruitInfo,
                                                     @RequestParam(defaultValue = "1") Integer page,
                                                     @RequestParam(defaultValue = "10") Integer rows){
        // 仅限查询当前公司数据
        User user = UserUtil.getUser();
        recruitInfo.setCompanyId(user.getId());
        return recruitService.findRecruitByPage(recruitInfo, page,  rows);
    }


    /***
     * @Date 2021/3/8 20:22
     * @Desc  修改
     */
    @RequestMapping("/edit")
    public void editRecruit(@RequestBody RecruitInfo recruitInfo){
        // 增加公司id筛选条件，防止删除其他公司创建的数据
        User user = UserUtil.getUser();
        recruitInfo.setCompanyId(user.getId());

        recruitService.editRecruit(recruitInfo);
    }

    /***
     * @Date 2021/3/9 21:50
     * @Desc  学生查询所有企业招聘信息
     */
    @RequestMapping("/infoList")
    public PageResult<RecruitVO> findRecruitInfoByPage(RecruitRequest recruitRequest,
                                                       @RequestParam(defaultValue = "1") Integer page,
                                                       @RequestParam(defaultValue = "9") Integer rows){

        recruitRequest.setStatus(StatusType.NORMAL.getValue());
        return recruitService.findRecruitInfoByPage(recruitRequest, page,  rows);
    }

    /***
     * @Date 2021/3/9 21:50
     * @Desc  学生查询所有企业招聘信息
     */
    @RequestMapping("/info/{id}")
    public RecruitInfoVO findRecruitInfo(@PathVariable("id") Integer id){
        return recruitService.findRecruitInfo(id);
    }
}
