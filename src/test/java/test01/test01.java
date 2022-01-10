package test01;

import com.zhuquan.controller.UserController;
import com.zhuquan.domain.User;
import com.zhuquan.mapper.UserMapper;
import com.zhuquan.service.UserService;
import com.zhuquan.util.UserUtil;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.spring.annotation.MapperScan;

import javax.annotation.Resource;
import java.util.ArrayList;
@MapperScan("com.zhuquan.mapper")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class test01
{
    @Resource
    private  UserMapper userMapper;
    //单元测试，查询
    @Test
    public void tet01(){
        User user=new User();
        user.setUsername("18173425291");
        userMapper.selectOne(user);
   }
}
