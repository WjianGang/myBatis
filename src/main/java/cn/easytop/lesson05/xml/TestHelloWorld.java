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
		//���S�
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		
		return sqlSessionFactory;
	}
	
	public static SqlSession getSession() throws IOException{
		String resource = "cn/easytop/lesson05/xml/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//���S�
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session=sqlSessionFactory.openSession();
		return session;
	}
	
	@Test
	public void testXmlInterface() throws IOException{  //һ������
		SqlSession session =getSession();
		StudentMapper fm=session.getMapper(StudentMapper.class);
		
		Student stu = fm.queryStudentById("1");
		Student stu1 = fm.queryStudentById("1");
		System.out.println(stu==stu1);
	}
	
	/**
	 * ����������ͬһ��sessionFactory�в�ͬ��session��Ҳ�ǿ��Թ���
	 * һ�����治���ڿ�session �����������Ǵ��ڿ�seeion��
	 * @throws IOException
	 */
	@Test
	public void testTwoXmlInterface() throws IOException{   //��������
		SqlSessionFactory sessionFactory = getSessionFactory();
		SqlSession sql = sessionFactory.openSession();
		SqlSession sql1 = sessionFactory.openSession();
		

		StudentMapper fm=sql.getMapper(StudentMapper.class);
		Student s=fm.queryStudentById("1");
		//�ӻ����в�ѯ��
		sql.close();
		
		StudentMapper fm1=sql1.getMapper(StudentMapper.class);
		Student s1=fm1.queryStudentById("1");
		System.out.println(s==s1);
	}

}
