package cn.easytop.lesson01;

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

public class TestHelloWorld {
	public static void main(String[] args) throws IOException {
		String resource = "cn/easytop/lesson01/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//工厂类
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		
//		query(session);
//		insert(session);
		delete(session);				
	}
	public static void query(SqlSession session){ //查询所有
		//这是查询所有
		List list = session.selectList("a.selectFood");
		System.out.println(list);
	}
	public static void insert(SqlSession session){ //添加
		session.insert("a.insertFood");
		System.out.println("添加成功！");
		session.commit();
	}
	public static void delete(SqlSession session){  //删除
		session.delete("a.deleteFood");
		System.out.println("删除成功！");
		session.commit();
	}
	
	public static void update(SqlSession session){  //修改
		session.delete("a.updateFood");
		System.out.println("修改成功！");
		session.commit();
	}
	Logger logger = Logger.getLogger(TestHelloWorld.class);
	@Test
	public void test() throws IOException{
		String resource = "cn/easytop/lesson01/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//工厂类
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		Map map=new HashMap();
		map.put("myfoodname", "方北饺子");
		List list = session.selectList("a.selectFoodByParam",map);
		logger.debug(list);
	}
	
	@Test
	public void savePrice() throws IOException{
		String resource = "cn/easytop/lesson01/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//工厂类
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		Map map=new HashMap();
		map.put("price", 100);
		map.put("foodname", "青椒肉丝");
		session.insert("a.saveFood",map);
		session.commit();
	}

}
