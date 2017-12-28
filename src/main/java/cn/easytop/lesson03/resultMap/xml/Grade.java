package cn.easytop.lesson03.resultMap.xml;

import java.util.ArrayList;
import java.util.List;

public class Grade {
	private String gid;
	private String gname;
	private List<Student> studentList = new ArrayList<Student>();
	public List<Student> getStudentList() {
		return studentList;
	}
	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getGname() {
		return gname;
	}
	public void setGname(String gname) {
		this.gname = gname;
	}
	
}
