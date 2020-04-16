package com.wt.mapper;

import com.wt.entity.Student;
import com.wt.util.PageUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    //查询学生
    public List<Student> findStudents(@Param("name") String name, @Param("className") String className, @Param("pageUtils")PageUtils pageUtils);
    //删除学生
    public int deleteStu(@Param("stuId") int stuId);
    //修改学生信息
    public Integer updateStu(@Param("student") Student student);
    //统计学生人数
    int countStu();
    //学生毕业
   public Integer graduate(@Param("classId") int classId);

    public Integer addStu(@Param("stu") Student stu,@Param("classId") Integer classId);

    List<Student> findStudentsByStaffTel(@Param("staffTel") String staffTel);
}
