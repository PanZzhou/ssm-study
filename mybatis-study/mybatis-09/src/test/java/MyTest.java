import com.kuang.dao.UserMapper;
import com.kuang.pojo.User;
import com.kuang.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class MyTest {
    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.queryUserById(1);
        System.out.println(user);

        User user2 = mapper.queryUserById(1);
        System.out.println(user2);
        System.out.println(user==user2);

        //mapper.updateUser(new User(2,"aaaaa", "bbbbbb"));//更新操作
        sqlSession.clearCache();  //手动清理缓存

        User user1 = mapper.queryUserById(1);
        System.out.println(user1);

        System.out.println(user==user1);

        sqlSession.close();
    }

    @Test//测试二级缓存
    public void testSecondCache(){
        //开启两个不同的sqlSession
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        SqlSession sqlSession1 = MybatisUtils.getSqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        UserMapper mapper1 = sqlSession1.getMapper(UserMapper.class);

        User user = mapper.queryUserById(1);
        System.out.println(user);
        //第一个会话查完之后关闭，此时数据已被记录在二级缓存中
        sqlSession.close();

        //第二个会话重新查询数据，看是否访问了二级缓存
        User user2 = mapper1.queryUserById(1);
        System.out.println(user2);
        System.out.println(user==user2);

        User user1 = mapper1.queryUserById(2);
        System.out.println(user1);

        System.out.println(user2==user1);

        sqlSession1.close();
    }
}
