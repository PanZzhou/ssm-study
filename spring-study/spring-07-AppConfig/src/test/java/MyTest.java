import com.kuang.config.KuangConfig;
import com.kuang.pojo.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        //使用java配置类来初始化bean
        ApplicationContext context = new AnnotationConfigApplicationContext(KuangConfig.class);
        User user = context.getBean("getUser",User.class);  //getUser是配置类中获取User实例的方法名。
        System.out.println(user.toString());
    }
}
