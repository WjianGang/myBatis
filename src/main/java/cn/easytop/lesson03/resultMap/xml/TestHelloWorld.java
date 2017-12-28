package cn.easytop.lesson03.resultMap.xml;

import java.io.IOException;
import java.io.InputStream;
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
		String resource = "cn/easytop/lesson03/resultMap/xml/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//工廠類
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session=sqlSessionFactory.openSession();
		return session;
	}
	
	@Test
	public void testXmlInterface() throws IOException{
		SqlSession session =getSession();
		GradeMapper fm=session.getMapper(GradeMapper.class);
		List<Grade> list=fm.queryAllGrade();
		for(Grade g:list){
			System.out.println("班级："+g.getGname()+"，id:"+g.getGid());
		}
		session.commit();
	}
	
	
	@Test
	public void testManyToOne() throws IOException{
		SqlSession session =getSession();
		StudentMapper fm=session.getMapper(StudentMapper.class);
		Student student = fm.queryStudent("2");
		System.out.println("姓名:"+student.getSname()+"，班级："+student.getGrade().getGname());
	}
	
	@Test
	public void testOneToMany() throws IOException{
		SqlSession session =getSession();
		GradeMapper fm=session.getMapper(GradeMapper.class);
		Grade g=fm.queryGrade("1");
		for(Student s:g.getStudentList()){
			System.out.println("姓名："+s.getSname());
		}
	}
	


}
