package com.itheima.test;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itheima.domain.User;
import com.itheima.mapper.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class MybatisTest {
    Log log = LogFactory.getLog(MybatisTest.class);

    @Test
    public void test3() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //设置分页相关参数   当前页+每页显示的条数
        PageHelper.startPage(1, 3);

        List<User> userList = mapper.findAll();
        Iterator<User> iterator = userList.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            log.debug(user.toString());
        }
        for (User user : userList) {
            System.out.println(user);
        }

        // PageHelper.startPage(2, 3);
        //获得与分页相关参数
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        System.out.println("当前页：" + pageInfo.getPageNum());
        System.out.println("每页显示条数：" + pageInfo.getPageSize());
        System.out.println("总条数：" + pageInfo.getTotal());
        System.out.println("总页数：" + pageInfo.getPages());
        System.out.println("上一页：" + pageInfo.getPrePage());
        System.out.println("下一页：" + pageInfo.getNextPage());
        System.out.println("是否是第一个：" + pageInfo.isIsFirstPage());
        System.out.println("是否是最后一个：" + pageInfo.isIsLastPage());


        PageHelper.startPage(2, 3,true);
        userList = mapper.findAll();
        iterator = userList.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            log.debug(user.toString());
        }
        //获得与分页相关参数
         pageInfo = new PageInfo<User>(userList);
        System.out.println("当前页：" + pageInfo.getPageNum());
        System.out.println("每页显示条数：" + pageInfo.getPageSize());
        System.out.println("总条数：" + pageInfo.getTotal());
        System.out.println("总页数：" + pageInfo.getPages());
        System.out.println("上一页：" + pageInfo.getPrePage());
        System.out.println("下一页：" + pageInfo.getNextPage());
        System.out.println("是否是第一个：" + pageInfo.isIsFirstPage());
        System.out.println("是否是最后一个：" + pageInfo.isIsLastPage());
        sqlSession.close();
    }

    @Test
    public void test2() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        User user = mapper.findById(2);
        log.debug(user != null ? "user中的birthday：" + user.getBirthday() : "查无此人");

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void test1() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);

        //创建user
        User user = new User();
        user.setUsername("ceshi");
        user.setPassword("abc");
        user.setBirthday(new Date());
        //执行保存造作
        mapper.save(user);

        sqlSession.commit();
        sqlSession.close();
    }

}
