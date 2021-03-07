import com.kuang.pojo.Books;
import com.kuang.service.BookService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {
    @Test
    public void test(){
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        BookService bookServiceImpl = context.getBean("BookServiceImpl", BookService.class);
        List<Books> books = bookServiceImpl.queryAllBook();
        for (Books book : books) {
            System.out.println(book);
        }
    }
}
