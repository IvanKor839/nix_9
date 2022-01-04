package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.utill.FacadeUtil;
import ua.com.alevel.utill.WebRequestUtill;
import ua.com.alevel.view.dto.request.GroupStudentRequestDto;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.GroupStudentResponseDto;
import ua.com.alevel.entity.GroupStudent;
import ua.com.alevel.facade.GroupStudentFacade;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.GroupStudentService;
import ua.com.alevel.service.StudentService;
import ua.com.alevel.view.dto.response.PageData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public class GroupStudentFacadeImpl implements GroupStudentFacade {

    private final GroupStudentService groupStudentService;
    private final GroupService groupService;
    private final StudentService studentService;

    public GroupStudentFacadeImpl(GroupStudentService groupStudentService, GroupService groupService, StudentService studentService) {
        this.groupStudentService = groupStudentService;
        this.groupService = groupService;
        this.studentService = studentService;
    }

    @Override
    public void create(GroupStudentRequestDto entity) {
        GroupStudent groupStudent = new GroupStudent();
        groupStudent.setGroup(groupService.findById(entity.getGroupId()));
        groupStudent.setStudent(studentService.findById(entity.getStudentId()));
        groupStudentService.create(groupStudent);
    }

    @Override
    public void delete(Long id) {
        groupStudentService.delete(id);
    }

    @Override
    public void update(GroupStudentRequestDto entity, Long id) throws SQLException {
        GroupStudent groupStudent = groupStudentService.findById(id);
        groupStudent.setGroup(groupService.findById(entity.getGroupId()));
        groupStudent.setStudent(studentService.findById(entity.getStudentId()));
        groupStudentService.update(groupStudent);
    }

    @Override
    public GroupStudentResponseDto findById(Long id) {
        GroupStudent groupStudent = groupStudentService.findById(id);
        return new GroupStudentResponseDto(groupStudent);
    }

    @Override
    public PageData<GroupStudentResponseDto> findAll(WebRequest request) throws IOException {
        PageAndSizeData pageAndSizeData = WebRequestUtill.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtill.generateSortData(request);
        if (sortData.getSort().equals("created")) {
            sortData.setSort("groups." + sortData.getSort());
        }
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);
        DataTableResponse<GroupStudent> all = groupStudentService.findAll(dataTableRequest);
        List<GroupStudentResponseDto> list = all.getItems().stream().map(GroupStudentResponseDto::new).toList();
        PageData<GroupStudentResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(list, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }
}
