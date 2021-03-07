import com.kuang.dao.UserMapper;
import com.kuang.pojo.User;
import com.kuang.utils.MybatisUtils;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyTest {
    static Logger logger = Logger.getLogger(MyTest.class);

    @Test
    public void test(){
        //第一步：获取SqlSession对象
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        try{
            //执行sql,方式一：getMapper  (mapper和dao其实是一个东西)
            UserMapper userDao = sqlSession.getMapper(UserMapper.class);
            User user= userDao.getUserById(1);
            System.out.println(user);
        }catch (Exception e){
            System.out.println(e);
        }finally {
            //关闭SqlSession, SqlSession不是线程安全的，放在finally中保证每次都被正确关闭
            sqlSession.close();
        }
    }

    @Test
    public void testLog4j(){
        logger.info("log4j的info日志");
        logger.debug("log4j的debug日志");
        logger.error("log4j的error日志");
    }

    @Test
    public void testLimit(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        UserMapper mapper =     sqlSession.getMapper(UserMapper.class);
        Map<String, Integer> map = new HashMap<String, Integer>();
        //key与配置文件中的sql语句中的#{}内的一致
        map.put("startIndex", 0);
        map.put("pageSize", 2);
        List<User> users = mapper.getUsersByLimit(map);
        for(User user:users){
            System.out.println(user);
        }
        sqlSession.close();
    }

    @Test
    public void testRowBounds(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        RowBounds rowBounds = new RowBounds(1, 2);

        //通过java代码层面实现分页
        List<User> users = sqlSession.selectList("com.kuang.dao.UserMapper.getUsersByRowBounds",null,rowBounds);
        for (User user:users){
            System.out.println(user);
        }

        sqlSession.close();
    }
}
