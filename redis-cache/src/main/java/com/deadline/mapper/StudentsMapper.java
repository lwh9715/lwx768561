package com.deadline.mapper;

import com.deadline.bean.Students;
import org.apache.ibatis.annotations.*;

import java.util.List;

//指定这是一个操作数据库的mapper
//mapper或者mapperScan将接口扫描装配到容器中
public interface StudentsMapper {

    @Select("select * from students")
    public List<Students> getStudents();

    @Delete("delete from students where id=#{id}")
    public int delStudentsById(Integer id);

    @Delete("delete from students")
    public int delAll();

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into students(name) values(#{name})")
    public int saveStudents(Students students);

    @Update("update students set name=#{name} where id=#{id}")
    public int updateStudents(Students students);


}
