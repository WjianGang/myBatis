package cn.easytop.lesson04.anno;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.jdbc.SQL;

import cn.easytop.lesson04.xml.Student;

public interface StudentMapper {
	static class StudentProvier{
		public String queryStudentSql(Map map){
			Student student = (Student)map.get("stu");
			String sql = "select * from student where 1=1";
			if(student.getSname()!=null &&!"".equals(student.getSname())){
				student.setSname("%"+student.getSname()+"%");
				sql+=" and sname like #{stu.sname}";
			}
			if(student.getAddress()!=null &&!"".equals(student.getAddress())){
				student.setAddress("%"+student.getAddress()+"%");
				sql+="  and address like #{stu.address} ";
			}
			return sql;
		}
		
		public String queryStudentSql1(Map map){
			Student student=(Student)map.get("stu");
			SQL sql=new SQL();
			sql.SELECT("*").FROM("student");
			if(student.getSname()!=null &&!"".equals(student.getSname())){
				student.setSname("%"+student.getSname()+"%");
				sql.WHERE(" sname like #{stu.sname}");
			}
			if(student.getAddress()!=null &&!"".equals(student.getAddress())){
				student.setAddress("%"+student.getAddress()+"%");
				sql.AND();
				sql.WHERE(" address like #{stu.address}");
			}
			return sql.toString();
		}
		
		public String updateStudentSql(Map map){   //修改
			Student student=(Student)map.get("stu");
			SQL sql=new SQL();
			sql.UPDATE("student");
			if(student.getSname()!=null &&!"".equals(student.getSname())){
				sql.SET("sname = "+"'"+student.getSname()+"'");
			}
			if(student.getAge()!=null && !"".equals(student.getAge())){
				sql.SET("age="+student.getAge());
			}
			if(student.getSex()!=null && !"".equals(student.getSex())){
				sql.SET("sex="+student.getSex());
			}
			if(student.getAddress()!=null &&!"".equals(student.getAddress())){
				sql.SET("address="+"'"+student.getAddress()+"'");
			}
			sql.WHERE("sid = "+student.getSid());
			return sql.toString();
		}
//		public String queryByGid(Map map){  //按in(1,2)查询
//			Student student=(Student)map.get("stu");
//			SQL sql=new SQL();
//			sql.SELECT("*").FROM("student");
//		
//			return sql.toString();
//		}
	}

	/**
	 * 通过编号查询学生
	 * @param sid
	 * @return
	 */
	@SelectProvider(type=StudentProvier.class,method="queryStudentSql")
	public List<Student> queryStudent(@Param("stu")Student student);
	
	@Select("<script>select * from student where 1=1 " +
			"<choose>"+
      	"<when test=\"sex!=null\">"+
      	 "   and sex=#{sex}"+
      	"</when>"+
      	" <otherwise>"+
      	"   and sex=1 "+
      	"</otherwise>"+
      "</choose></script>")
	public List<Student> querySex(Student student);
	
	@UpdateProvider(type=StudentProvier.class,method="updateStudentSql")
	public void updateStudent(@Param("stu")Student student);
	
	public List<Student> queryStudentbyAnyGrade(@Param("gradeList")List<String> gradeList);
}
