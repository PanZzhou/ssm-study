package com.kuang.dao;

import com.kuang.pojo.Blog;

import java.util.List;
import java.util.Map;

public interface BlogMapper {
    //插入数据
    int addBlog(Blog blog);
    //查询blog
    List<Blog> queryBlogIF(Map map);

    List<Blog> queryBlogChoose(Map map);

    int updateBlog(Map map);

    //sql片段
    List<Blog> queryBlogIF1(Map map);

    //foreach标签
    //查询第1，2，3号id的博客
    List<Blog> queryBlogForeach(Map map);
}
