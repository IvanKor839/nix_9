package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Group;
import ua.com.alevel.utill.FacadeUtil;
import ua.com.alevel.utill.WebRequestUtill;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.request.StudentRequestDto;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.StudentResponseDto;
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
    public PageData<StudentResponseDto> findAll(WebRequest request) throws IOException {
        PageAndSizeData pageAndSizeData = WebRequestUtill.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtill.generateSortData(request);
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);
        DataTableResponse<Student> all = studentService.findAll(dataTableRequest);
        List<StudentResponseDto> list = all.getItems().stream().map(StudentResponseDto::new).collect(Collectors.toList());
        PageData<StudentResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(list, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }
    @Override
    public List<StudentResponseDto> findAll(){
        return studentService.findAll().stream().map(StudentResponseDto::new).toList();
    }

    @Override
    public PageData<StudentResponseDto> findByGroupId(WebRequest request, Long groupId) {
        PageAndSizeData pageAndSizeData = WebRequestUtill.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtill.generateSortData(request);
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);
        DataTableResponse<Student> all = studentService.findByGroupId(dataTableRequest, groupId);
        List<StudentResponseDto> list = all.getItems().stream().map(StudentResponseDto::new).toList();
        PageData<StudentResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(list, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }
}
