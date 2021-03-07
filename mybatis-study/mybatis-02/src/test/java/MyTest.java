import com.kuang.dao.UserMapper;
import com.kuang.pojo.User;
import com.kuang.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTest {
    @Test
    public void test(){
        //第一步：获取SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try{
            //执行sql,方式一：getMapper  (mapper和dao其实是一个东西)
            UserMapper userDao = sqlSession.getMapper(UserMapper.class);
            List<User> userList = userDao.getUserList();

            for (User user : userList) {
                System.out.println(user);
            }
        }catch (Exception e){
            System.out.println(e);
        }finally {
            //关闭SqlSession, SqlSession不是线程安全的，放在finally中保证每次都被正确关闭
            sqlSession.close();
        }
    }
}
