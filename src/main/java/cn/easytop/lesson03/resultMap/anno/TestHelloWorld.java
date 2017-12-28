package cn.easytop.lesson03.resultMap.anno;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import cn.easytop.lesson03.resultMap.xml.Grade;
import cn.easytop.lesson03.resultMap.xml.Student;

public class TestHelloWorld {

	public static SqlSession getSession() throws IOException{
		String resource = "cn/easytop/lesson03/resultMap/anno/mybatis.xml";
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
		Grade g=fm.queryGrade("3");
		for(Student s:g.getStudentList()){
			System.out.println("姓名："+s.getSname());
		}
	}
}
