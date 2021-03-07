import com.kuang.pojo.People;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        People p = context.getBean("people",People.class);
        p.getCat().shout();
        p.getDog().shout();
    }
}
