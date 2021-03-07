import com.kuang.mapper.UserMapper;
import com.kuang.mapper.UserMapperImpl;
import com.kuang.mapper.UserMapperImpl1;
import com.kuang.pojo.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.InputStreamResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MyTest {

    @Test
    public void test(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ApplicationContext.xml");
        //方式一
        UserMapper userMapper = applicationContext.getBean("UserMapper", UserMapper.class);
        List<User> users = userMapper.getUsers();
        for (User user : users) {
            System.out.println(user);
        }

        //方式二
        UserMapperImpl1 userMapper1 = applicationContext.getBean("UserMapper1", UserMapperImpl1.class);
        List<User> users1 = userMapper1.getUsers();
        for (User user : users1) {
            System.out.println(user);
        }
    }
}
