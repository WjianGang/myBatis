package cn.easytop.lesson05.xml;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StudentMapper {

	/**
	 * 通过编号查询学生
	 * @param sid
	 * @return
	 */
	public Student queryStudentById(String sid);
	
}
