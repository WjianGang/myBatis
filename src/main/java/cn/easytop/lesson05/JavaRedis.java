package cn.easytop.lesson05;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.sun.xml.messaging.saaj.util.ByteInputStream;
import com.sun.xml.messaging.saaj.util.ByteOutputStream;

import redis.clients.jedis.Jedis;

public class JavaRedis {
	/**
	 * 序列化对象为数组
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static byte[] objectToByteArray(Object obj) throws IOException{
		ByteOutputStream boss=new ByteOutputStream();
		ObjectOutputStream boo = new ObjectOutputStream(boss);
		boo.writeObject(obj);
		return boss.getBytes();
	}
	
	/**
	 * 反序列化字节数组为对象
	 * @param bt
	 * @return
	 * @throws Exception
	 */
	public static Object byteArrayToObject(byte[] bt) throws Exception{
		ByteInputStream in = new ByteInputStream();
		in.read(bt);
		ObjectInputStream obj = new ObjectInputStream(in);
		return obj.readObject();
	}
	
	public static void main(String[] args) {
		Jedis jedis = new Jedis("localhost",6379);
		jedis.set("myname", "jiaozi");
		System.out.println(jedis.get("myname"));
	}
}
