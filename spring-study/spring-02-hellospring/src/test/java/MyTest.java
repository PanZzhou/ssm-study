import com.kuang.pojo.Hello;
import com.kuang.pojo.Hello_arg;
import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        //获取spring上下文对象，beans.xml中的所有bean都会被创建
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        //根据id，获取beans.xml配置文件中的对象。
        Hello hello = (Hello)context.getBean("hello");
        System.out.println(hello.toString());

        //Hello_arg中只有有参构造，在bean.xml中又是使用<property>来配置，<property>使用的是无参构造，最后会造成报错，解决方法是在xml文件中使用有参构造方法。
        Hello_arg hello_arg = (Hello_arg) context.getBean("hello_arg");
        System.out.println(hello_arg.toString());

        //spring标签使用测试：
        //alias
        Hello helloalias = (Hello) context.getBean("hahaha");//配置文件<alias>别名标签测试
        System.out.println(helloalias.toString());
        //bean
        Hello hello1 = (Hello)context.getBean("hello3");//bean标签name属性测试：name也是别名，所以alias标签没啥用了就。
        System.out.println(hello1.toString());
        //import
        // 如下所示，如果项目有多个配置文件，就需要像下面一样写，导入多个配置文件，使用在beans.xml中<import>标签并导入另外两个文件后，
        //就可以只用导入beans.xml文件即可，就像下面第二行所示：
        //ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml","beans1.xml","beans2.xml");
        //ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    }
}
