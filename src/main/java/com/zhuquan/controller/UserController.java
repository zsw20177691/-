package com.zhuquan.controller;

import com.github.pagehelper.Page;
import com.zhuquan.domain.CompanyInfo;
import com.zhuquan.domain.PersonalInfo;
import com.zhuquan.domain.RecruitInfo;
import com.zhuquan.domain.User;
import com.zhuquan.service.UserService;
import com.zhuquan.util.ExceptionResult;
import com.zhuquan.util.PageResult;
import com.zhuquan.util.UserUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /***
     * @Desc  用户登录，登录成功返回登录者信息
     * @Date 2021/3/4 22:06
     */
    @RequestMapping("/login")
        public User Login(String username, String password){
        //抛出异常，交给前端展示。
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)){
            throw ExceptionResult.build(HttpStatus.BAD_REQUEST, "请填写用户名密码");
        }
        User user = userService.selectUser(username, password);
        UserUtil.setUser(user);
        return user;
    }


    /***
     * @Desc  判断用户名是否存在，不存在返回true
     * @Date 2021/3/4 22:07
     */
    @RequestMapping("/checkUsername")
    public Boolean checkUsername(String username){
        return userService.selectByUsername(username) == null;
    }

    /***
     * @Desc  学生用户注册
     * @Date 2021/3/5 23:37
     */
    @RequestMapping("/registerPersonal")
    public void registerPersonal(User user, PersonalInfo personal){
        personal.setStatus(0);
        userService.registerPersonal(user, personal);

    }


    /***
     * @Desc  企业用户注册
     * @Date 2021/3/5 23:37
     */
    @RequestMapping("/registerCompany")
    public void registerCompany(User user, CompanyInfo enterprise){
        userService.registerCompany(user, enterprise);

    }

    /***
     * @Date 2021/3/9 16:18
     * @Desc  获取用户信息
     */
    @RequestMapping("/info")
    public Object getUserInfo(){
        User user = UserUtil.getUser();
        return userService.getUserInfo(user);
    }

    /***
     * @Date 2021/3/11 23:01
     * @Desc  修改用户信息 姓名、头像
     */
    @RequestMapping("/edit")
    public void editUser(String name, String avatar){
        User user = UserUtil.getUser();

        User edit = new User();
        edit.setId(user.getId());
        edit.setName(name);
        edit.setAvatar(avatar);
        // 修改数据
        userService.editUser(edit);

        user.setName(name);
        user.setAvatar(avatar);
    }

    /***
     * @Date 2021/3/11 23:01
     * @Desc  修改用户密码
     */
    @RequestMapping("/editPassword")
    public void editUserPassword(String oldPw, String newPw){
        User user = UserUtil.getUser();
        userService.editPassword(user, oldPw, newPw);
    }


    /***
     * @Date 2021/3/9 20:37
     * @Desc  修改公司信息
     */
    @RequestMapping("/editCompany")
    public void editCompany(@RequestBody CompanyInfo companyInfo){
        userService.editCompany(companyInfo);
    }

    /***
     * @Date 2021/3/9 20:37
     * @Desc  修改普通用户信息
     */
    @RequestMapping("/editPersonal")
    public void editPersonal(@RequestBody PersonalInfo personalInfo){
        userService.editPersonal(personalInfo);
    }


    @RequestMapping("/personal/{id}")
    public PersonalInfo getPersonalInfo(@PathVariable("id") Integer id){
        return userService.getPersonalInfo(id);
    }

    @RequestMapping("/company/{id}")
    public CompanyInfo getCompanyInfo(@PathVariable("id") Integer id){
        return userService.getCompanyInfo(id);
    }

    @RequestMapping("/list")
    public PageResult<User> findByPage(User user,
                                       @RequestParam(defaultValue = "1") Integer page,
                                       @RequestParam(defaultValue = "9") Integer rows){
        return userService.findByPage(user, page, rows);
    }

    /***
     * @Date 2021/3/8 20:22
     * @Desc  修改
     */
    @RequestMapping("/editStatus")
    public void editStatus(@RequestBody User user){
        // 增加公司id筛选条件，防止删除其他公司创建的数据

        userService.editStatus(user);
    }
}
