import com.kuang.pojo.Student;
import com.kuang.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Student stu = (Student) context.getBean("student");
        System.out.println(stu.toString());
    }

    //测试p和c命名空间的使用
    @Test
    public void test2(){
        ApplicationContext context = new ClassPathXmlApplicationContext("userbean.xml");
        User user = context.getBean("user", User.class);
        System.out.println(user.toString());
        //在getBean()函数传入第二个参数，表示要获得的对象类型，传入之后就不用强制转换类型了。
        User userc = (User)context.getBean("userc");
        System.out.println(userc.toString());
    }

    //bean标签scope属性功能测试。
    @Test
    public void test3(){
        ApplicationContext context = new ClassPathXmlApplicationContext("userbean.xml");
        User user = context.getBean("user2",User.class);
        User user1 = context.getBean("user2",User.class);
        System.out.println(user==user1);
        //配置文件中scope=singleton时，user与user1相等，两个引用同一个对象；scope=prototype时，不等，每个变量都要创建一个实例
    }
}
