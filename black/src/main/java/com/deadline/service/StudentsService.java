package com.deadline.service;

import com.deadline.bean.Students;
import com.deadline.mapper.StudentsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;


@CacheConfig(cacheNames = "student_id")  //这个注解表示类中共同放入到act 模块中
@Service
public class StudentsService {

    @Autowired
    StudentsMapper studentsMapper;

    /**
     * @return
     * @throws Exception
     * @Cacheable 1、先查缓存，
     * 2、若没有缓存，就执行方法
     * 3、若有缓存。则返回，不执行方法
     * <p>
     * 所以@Cacheable 不能使用result
     */
    @Cacheable(key = "#root.methodName")
    public List<Students> getStudents() {
        List<Students> students = studentsMapper.getStudents();
        return students;
    }

    /**
     * @param
     * @return
     * @throws Exception
     * @CachePut 更新并刷新缓存
     * 1、先调用目标方法
     * 2、把结果缓存
     */
    @CachePut(key = "#result.id", unless = "#result.id == null")
    public int saveStudents(Students students) {
        int insertStudents = studentsMapper.saveStudents(students);
        return insertStudents;
    }

    /**
     * 删除指定key 的缓存
     * beforeInvocation=false  缓存的清除是否在方法之前执行
     * 默认是在方法之后执行
     *
     * @return
     * @throws Exception
     * @paramd
     */
    @CacheEvict(key = "#id", beforeInvocation = true)
    public int delStudentsById(Integer id) {
        int delStudentsById = studentsMapper.delStudentsById(id);
        return delStudentsById;
    }

    /**
     * 删除所有缓存
     *
     * @return
     * @throws Exception
     */
    @CacheEvict(allEntries = true)
    public int delAll() {
        return studentsMapper.delAll();
    }

    @CachePut(key = "#result.id", unless = "#result.id == null")
    public int updateStudents(Students students) {
        int updateStudents = studentsMapper.updateStudents(students);
        return updateStudents;
    }
}
