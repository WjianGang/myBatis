package cn.easytop.lesson03.resultMap.xml;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.easytop.lesson02.Food;

public interface GradeMapper {
	public List queryAllGrade();
	public Grade queryGrade(String gid);
}
