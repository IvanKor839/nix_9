package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.student.StudentRequestDto;
import ua.com.alevel.dto.student.StudentResponseDto;
import ua.com.alevel.entity.Student;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.service.StudentService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService;

    public StudentFacadeImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public void create(StudentRequestDto entity) {
        Student student = new Student();
        student.setFirstName(entity.getFirstName());
        student.setLastName(entity.getLastName());
        student.setAge(entity.getAge());
        studentService.create(student);
    }

    @Override
    public void delete(Long id) {
        studentService.delete(id);
    }

    @Override
    public void update(StudentRequestDto entity, Long id) throws SQLException {
        Student student = studentService.findById(id);
        student.setFirstName(entity.getFirstName());
        student.setLastName(entity.getLastName());
        student.setAge(entity.getAge());
        studentService.update(student);
    }

    @Override
    public StudentResponseDto findById(Long id) {
        return new StudentResponseDto(studentService.findById(id));
    }

    @Override
    public List<StudentResponseDto> findAll() throws IOException {
        return studentService.findAll().stream().map(StudentResponseDto::new).collect(Collectors.toList());
    }
}
