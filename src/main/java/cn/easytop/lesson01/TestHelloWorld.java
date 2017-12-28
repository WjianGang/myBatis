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
		//������
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		
//		query(session);
//		insert(session);
		delete(session);				
	}
	public static void query(SqlSession session){ //��ѯ����
		//���ǲ�ѯ����
		List list = session.selectList("a.selectFood");
		System.out.println(list);
	}
	public static void insert(SqlSession session){ //���
		session.insert("a.insertFood");
		System.out.println("��ӳɹ���");
		session.commit();
	}
	public static void delete(SqlSession session){  //ɾ��
		session.delete("a.deleteFood");
		System.out.println("ɾ���ɹ���");
		session.commit();
	}
	
	public static void update(SqlSession session){  //�޸�
		session.delete("a.updateFood");
		System.out.println("�޸ĳɹ���");
		session.commit();
	}
	Logger logger = Logger.getLogger(TestHelloWorld.class);
	@Test
	public void test() throws IOException{
		String resource = "cn/easytop/lesson01/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//������
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		Map map=new HashMap();
		map.put("myfoodname", "��������");
		List list = session.selectList("a.selectFoodByParam",map);
		logger.debug(list);
	}
	
	@Test
	public void savePrice() throws IOException{
		String resource = "cn/easytop/lesson01/mybatis.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		//������
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = sqlSessionFactory.openSession();
		Map map=new HashMap();
		map.put("price", 100);
		map.put("foodname", "�ཷ��˿");
		session.insert("a.saveFood",map);
		session.commit();
	}

}
