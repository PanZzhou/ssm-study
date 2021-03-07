import com.kuang.pojo.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationcontext.xml");
        //动态代理代理的是接口。
        UserService user = (UserService) context.getBean("userService");
        user.query();
    }
}
