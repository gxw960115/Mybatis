package com.itheima.test;

import com.itheima.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import javax.xml.ws.RequestWrapper;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class MyBatisTest1 {
    Log log = LogFactory.getLog(MyBatisTest1.class);

    @Test
    public void test1() throws IOException {
        // 获得核心配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        // 获得session工厂对象
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        // 获得session会话对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 执行操作 参数：namespace+id
        List<User> userList = sqlSession.selectList("userMapper.findAll");
        // 打印数据
        System.out.println(!userList.isEmpty() && userList != null ? "查询成功！\n" + userList : "查询失败！\n" + userList);
        // 释放资源
        sqlSession.close();
    }

    @Test
    public void test2() throws IOException {
        User user = new User();
        user.setUsername("wq");
        user.setPassword("666");
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        int i = sqlSession.insert("userMapper.save", user);
        // sqlSession.commit();
        System.out.println(i == 1 ? "添加成功！" : "添加失败！");
        sqlSession.close();
    }

    @Test
    public void test3() throws IOException {
        User user = new User();
        user.setId(2);
        user.setUsername("lucy");
        user.setPassword("456");
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        int i = sqlSession.update("userMapper.update", user);
        sqlSession.commit();
        System.out.println(i == 1 ? "更新成功！" : "更新失败！");
        sqlSession.close();
    }

    @Test
    public void test4() throws IOException {
        User user = new User();
        user.setId(2);
        user.setUsername("lucy");
        user.setPassword("456");
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // int i = sqlSession.delete("userMapper.delete", 9);
        int[] ids = {9, 10, 11};
        int i = sqlSession.delete("userMapper.delete", 9);
        sqlSession.commit();
        System.out.println(i == 1 ? "删除成功！" : "删除失败！");
        sqlSession.close();
    }

    @Test
    public void test5() throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("sqlMapConfig.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        User user = sqlSession.selectOne("userMapper.findById", 5);
        sqlSession.commit();
        System.out.println(user != null ? "查询成功！" + user : "查询失败！" + user);
        sqlSession.close();
    }
}
