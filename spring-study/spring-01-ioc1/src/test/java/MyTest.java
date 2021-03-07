import com.kuang.dao.UserDaoMysqlImpl;
import com.kuang.dao.UserDaoOracleImpl;
import com.kuang.service.UserService;
import com.kuang.service.UserServiceImpl;

public class MyTest {
    public static void main(String[] args) {
        //用户实际调用的是业务层，dao层他们不需要接触
        //UserService userService = new UserServiceImpl();
        //userService.getUser();

        UserService userService = new UserServiceImpl();
        //改动程序后，用户端动态配置，不再是在程序里写死，而是在这里根据用户需求来做，
        ((UserServiceImpl) userService).setUserDao(new UserDaoOracleImpl());
        userService.getUser();
        ((UserServiceImpl) userService).setUserDao(new UserDaoMysqlImpl());
        userService.getUser();
    }
}
