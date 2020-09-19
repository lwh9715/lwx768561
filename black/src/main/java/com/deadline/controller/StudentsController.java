package com.deadline.controller;

import com.deadline.bean.Students;
import com.deadline.service.StudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentsController {

    @Autowired
    StudentsService studentsService;

    @GetMapping("/query")
    public List<Students> getStudents() {
        return studentsService.getStudents();

    }

    @GetMapping("/del/{id}")
    public int delStudents(@PathVariable("id") Integer id) {
        return studentsService.delStudentsById(id);
    }

    @GetMapping("/delAll")
    public int delAll() {
        studentsService.delAll();
        return 88;
    }

    @GetMapping("/save")
    public Students saveStudents(Students students) {
        studentsService.saveStudents(students);
        return students;
    }

    @GetMapping("/update/{id}")
    public Students updateStudents(Students students) {
        studentsService.updateStudents(students);
        return students;
    }


}
