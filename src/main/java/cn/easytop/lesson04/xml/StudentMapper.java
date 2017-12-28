package cn.easytop.lesson04.xml;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StudentMapper {

	/**
	 * ͨ����Ų�ѯѧ��
	 * @param sid
	 * @return
	 */
	public List<Student> queryStudent(Student student);
	public List<Student> querySex(Student student);
	public void updateStudent(Student student);
	public List<Student> queryStudentbyAnyGrade(@Param("gradeList")List<String> gradeList);
}
