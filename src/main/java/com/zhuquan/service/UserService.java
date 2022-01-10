package com.zhuquan.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhuquan.domain.CompanyInfo;
import com.zhuquan.domain.PersonalInfo;
import com.zhuquan.domain.User;
import com.zhuquan.enums.StatusType;
import com.zhuquan.enums.UserType;
import com.zhuquan.mapper.CompanyInfoMapper;
import com.zhuquan.mapper.PersonalInfoMapper;
import com.zhuquan.mapper.UserMapper;
import com.zhuquan.util.EncryptionUtil;
import com.zhuquan.util.ExceptionResult;
import com.zhuquan.util.PageResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private PersonalInfoMapper personalInfoMapper;

    @Resource
    private CompanyInfoMapper companyInfoMapper;

    // 用户登录
    public User selectUser(String username, String password){

        User login = selectByUsername(username);//执行完对应的方法之后，此时login包含用户名密码。
        System.out.println(login);
        //条件满足抛出异常
         if (login == null || !login.getPassword().equals(EncryptionUtil.getMD5Str(password))){
            throw ExceptionResult.build(HttpStatus.BAD_REQUEST, "用户名或密码错误");
        }
        //条件满足抛出异常
        if (StatusType.INVALID.getValue().equals(login.getStatus())){
            throw ExceptionResult.build(HttpStatus.BAD_REQUEST, "用户已被冻结，暂时无法登录");
        }

        return login;
    }

    // 根据用户名查询， 判断该账号是否存在
    public User selectByUsername(String username){
        User user = new User();//实例化一个User对象来接收参数
        user.setUsername(username);//通过set方法为对象user中的参数Username赋值为前端传的参数
        return userMapper.selectOne(user);//查询用户数据
    }

    private void checkUsername(String username){
        if (!Pattern.matches("^[1][3,4,5,7,8,9][0-9]{9}$", username)){
            throw ExceptionResult.build(HttpStatus.BAD_REQUEST, "手机号格式不正确");
        }
        if (selectByUsername(username) != null){
            throw ExceptionResult.build(HttpStatus.BAD_REQUEST, "手机号已被注册");
        }
    }

    @Transactional
    public synchronized void registerPersonal(User user, PersonalInfo personal){
        // 校验用户名
        checkUsername(user.getUsername());
        // 对密码进行md5加密
        user.setPassword(EncryptionUtil.getMD5Str(user.getPassword()));
        user.setStatus(StatusType.NORMAL.getValue());
        user.setType(UserType.STUDENT.getValue());

        userMapper.insert(user);
        personal.setUserId(user.getId());
        personalInfoMapper.insert(personal);
    }

    @Transactional
    public synchronized void registerCompany(User user, CompanyInfo enterprise) {
        // 校验用户名
        checkUsername(user.getUsername());
        // 对密码进行md5加密
        user.setPassword(EncryptionUtil.getMD5Str(user.getPassword()));
        user.setStatus(StatusType.NORMAL.getValue());
        user.setType(UserType.COMPANY.getValue());

        userMapper.insert(user);
        enterprise.setUserId(user.getId());
        companyInfoMapper.insert(enterprise);

    }


    public Object getUserInfo(User user) {

        if (UserType.STUDENT.getValue().equals(user.getType())){

            return personalInfoMapper.selectByPrimaryKey(user.getId());

        }else if (UserType.COMPANY.getValue().equals(user.getType())){

            return companyInfoMapper.selectByPrimaryKey(user.getId());

        }

        return null;
    }

    public void editCompany(CompanyInfo companyInfo) {
        companyInfoMapper.updateByPrimaryKeySelective(companyInfo);
    }

    public void editPersonal(PersonalInfo personalInfo) {
        personalInfoMapper.updateByPrimaryKeySelective(personalInfo);
    }

    @Transactional
    public void editUser(User edit) {
        userMapper.updateByPrimaryKeySelective(edit);
        if (UserType.STUDENT.getValue().equals(edit.getType())){
             PersonalInfo personalInfo = new PersonalInfo();
             personalInfo.setUserId(edit.getId());
             personalInfo.setName(edit.getName());
             personalInfoMapper.updateByPrimaryKeySelective(personalInfo);

        }else if (UserType.COMPANY.getValue().equals(edit.getType())){
            CompanyInfo companyInfo = new CompanyInfo();
            companyInfo.setUserId(edit.getId());
            companyInfo.setName(edit.getName());
             companyInfoMapper.updateByPrimaryKeySelective(companyInfo);

        }
    }

    public void editPassword(User user, String oldPw, String newPw) {
        if (!user.getPassword().equals(EncryptionUtil.getMD5Str(oldPw))){
            throw ExceptionResult.build(HttpStatus.BAD_REQUEST, "原始密码输入错误");
        }
        User edit = new User();
        edit.setId(user.getId());
        edit.setPassword(EncryptionUtil.getMD5Str(newPw));
        userMapper.updateByPrimaryKeySelective(edit);
        user.setPassword(EncryptionUtil.getMD5Str(newPw));
    }

    public PersonalInfo getPersonalInfo(Integer id) {
        return personalInfoMapper.selectByPrimaryKey(id);
    }

    public CompanyInfo getCompanyInfo(Integer id) {
        return companyInfoMapper.selectByPrimaryKey(id);
    }

    public PageResult<User> findByPage(User user, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);

        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (user.getType() == null){
            criteria.andNotEqualTo("type", "0");
        }else {
            criteria.andEqualTo("type", user.getType());
        }
        if (StringUtils.isNotBlank(user.getName())){
            Example.Criteria criteria1 = example.createCriteria();
            criteria1.orLike("name", "%" + criteria1+ "%")
                    .orLike("username", "%" + criteria1+ "%");
            example.or(criteria1);
        }
        List<User> users = userMapper.selectByExample(example);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return new PageResult(pageInfo.getTotal(), users);
    }

    public void editStatus(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }
}
