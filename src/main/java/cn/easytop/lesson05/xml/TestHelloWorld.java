package cn.easytop.lesson05.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.junit.Test;

import cn.easytop.lesson02.Food;

public class TestHelloWorld {
	
	public static SqlSessionFactory getSessionFactory() throws IOException{
		String resource = "cn/easytop/lesson05/xml/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//工廠類
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		return sqlSessionFactory;
	}
	
	public static SqlSession getSession() throws IOException{
		String resource = "cn/easytop/lesson05/xml/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//工廠類
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session=sqlSessionFactory.openSession();
		return session;
	}
	
	@Test
	public void testXmlInterface() throws IOException{  //一级缓存
		SqlSession session =getSession();
		StudentMapper fm=session.getMapper(StudentMapper.class);
		
		Student stu = fm.queryStudentById("1");
		Student stu1 = fm.queryStudentById("1");
		System.out.println(stu==stu1);
	}
	
	/**
	 * 二级缓存在同一个sessionFactory中不同的session中也是可以共享
	 * 一级缓存不存在跨session 而二级缓存是存在跨seeion的
	 * @throws IOException
	 */
	@Test
	public void testTwoXmlInterface() throws IOException{   //二级缓存
		SqlSessionFactory sessionFactory = getSessionFactory();
		SqlSession sql = sessionFactory.openSession();
		SqlSession sql1 = sessionFactory.openSession();
		

		StudentMapper fm=sql.getMapper(StudentMapper.class);
		Student s=fm.queryStudentById("1");
		//从缓存中查询的
		sql.close();
		
		StudentMapper fm1=sql1.getMapper(StudentMapper.class);
		Student s1=fm1.queryStudentById("1");
		System.out.println(s==s1);
	}

}
