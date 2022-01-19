package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.StudentFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.util.FacadeUtil;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.request.StudentRequestDto;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.view.dto.response.PageData;
import ua.com.alevel.view.dto.response.StudentResponseDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentFacadeImpl implements StudentFacade {

    private final StudentService studentService;

    public StudentFacadeImpl(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    public Set<GroupResponseDto> getGroups(Long studentId) {
        Set<Group> groups = studentService.getGroups(studentId);
        Set<GroupResponseDto> groupResponseDtos = new HashSet<>();
        groups.forEach(group -> {
            GroupResponseDto groupResponseDto = new GroupResponseDto(group);
            groupResponseDtos.add(groupResponseDto);
        });
        return groupResponseDtos;
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
        Student student = studentService.findById(id).get();
        student.setFirstName(entity.getFirstName());
        student.setLastName(entity.getLastName());
        student.setAge(entity.getAge());
        studentService.update(student);
    }

    @Override
    public StudentResponseDto findById(Long id) {
        return new StudentResponseDto(studentService.findById(id).get());
    }

    @Override
    public PageData<StudentResponseDto> findAll(WebRequest request) throws IOException {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);
        DataTableResponse<Student> all = studentService.findAll(dataTableRequest);
        List<StudentResponseDto> list = all.getItems().stream().map(StudentResponseDto::new).collect(Collectors.toList());
        PageData<StudentResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(list, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }
}
