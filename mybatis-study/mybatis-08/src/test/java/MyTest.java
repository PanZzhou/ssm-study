import com.kuang.dao.BlogMapper;
import com.kuang.pojo.Blog;
import com.kuang.utils.IdUtils;
import com.kuang.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.*;

public class MyTest {
    @Test
    public void test(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        Blog blog = new Blog();
        blog.setId(IdUtils.getId());
        blog.setAuthor("panda");
        blog.setTitle("Mybatis如此简单");
        blog.setViews(999);
        blog.setCreateTime(new Date());
        mapper.addBlog(blog);

        blog.setId(IdUtils.getId());
        blog.setTitle("Java如此简单");
        mapper.addBlog(blog);

        blog.setId(IdUtils.getId());
        blog.setTitle("Spring如此简单");
        mapper.addBlog(blog);

        blog.setId(IdUtils.getId());
        blog.setTitle("微服务如此简单");
        mapper.addBlog(blog);

        sqlSession.close();
    }

    @Test
    public void testIf(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map = new HashMap<>();
        map.put("title","Java如此简单");
        map.put("author","panda");
        List<Blog> blogs = mapper.queryBlogIF(map);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }


        sqlSession.close();
    }

    @Test
    public void testChoose(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map = new HashMap<>();
        //when标签只会从上到下匹配一个先符合条件的句子
        //map.put("title","Java如此简单");
        //map.put("author","panda");
        map.put("views","999");
        List<Blog> blogs = mapper.queryBlogChoose(map);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }


        sqlSession.close();
    }

    @Test
    public void testSet(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map = new HashMap<>();
        map.put("id","7275089c3eab4159bc3608b16317ba0b");
        map.put("title","盼盼");
        map.put("author","杨红焰");
        mapper.updateBlog(map);

        sqlSession.close();
    }

    @Test//sql片段
    public void testIf1(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map = new HashMap<>();
        map.put("title","Java如此简单");
        map.put("author","panda");
        List<Blog> blogs = mapper.queryBlogIF1(map);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }


        sqlSession.close();
    }

    @Test//foreach标签测试
    public void testForeach(){
        SqlSession sqlSession = MybatisUtils.getSqlSession();
        BlogMapper mapper = sqlSession.getMapper(BlogMapper.class);
        HashMap map = new HashMap<>();
        ArrayList<Integer> ids = new ArrayList<>();
        ids.add(1);
        map.put("ids",ids);

        List<Blog> blogs = mapper.queryBlogForeach(map);
        for (Blog blog : blogs) {
            System.out.println(blog);
        }


        sqlSession.close();
    }
}
