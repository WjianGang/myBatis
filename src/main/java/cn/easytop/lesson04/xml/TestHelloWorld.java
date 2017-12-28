package cn.easytop.lesson04.xml;

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
	
	public static SqlSession getSession() throws IOException{
		String resource = "cn/easytop/lesson04/xml/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//工廠類
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session=sqlSessionFactory.openSession();
		return session;
	}
	
	@Test
	public void testXmlInterface() throws IOException{
		SqlSession session =getSession();
		StudentMapper fm=session.getMapper(StudentMapper.class);
		Student student = new Student();
		student.setSname("王");
		student.setAddress("深圳龙");
		List<Student> list=fm.queryStudent(student);
		for(Student g:list){
			System.out.println(g.getSname());
		}
	}
	
	/**  0是男生 1 是女生 
	 * 	@Test
	 * @throws IOException
	 */
	@Test
	public void testSexInterface() throws IOException{
		SqlSession session =getSession();
		StudentMapper fm=session.getMapper(StudentMapper.class);
		Student student = new Student();
		student.setSex(0);
		List<Student> list=fm.querySex(student);
		for(Student g:list){
			System.out.println(g.getSname());
		}
	}
	
	@Test
	public void updateStudent() throws IOException{
		SqlSession session =getSession();
		StudentMapper fm=session.getMapper(StudentMapper.class);
		Student student = new Student();
		student.setSid("1");
		student.setSname("李杰2");
		fm.updateStudent(student);
		session.commit();
	}
	
	
	@Test
	public void testForEachInterface() throws IOException{
		SqlSession session =getSession();
		StudentMapper fm=session.getMapper(StudentMapper.class);
	    List list = new ArrayList();
	    list.add("1");
	    list.add("2");
	   
	   	List<Student> stu = fm.queryStudentbyAnyGrade(list);
	   	for (int i = 0; i < stu.size(); i++) {
			System.out.println(stu.get(i).getSname());
		}
		session.commit();
	}



}
