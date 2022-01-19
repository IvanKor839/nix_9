package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.util.FacadeUtil;
import ua.com.alevel.util.WebRequestUtil;
import ua.com.alevel.view.dto.request.GroupRequestDto;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
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
public class GroupFacadeImpl implements GroupFacade {

    private final GroupService groupService;

    public GroupFacadeImpl(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    public Set<StudentResponseDto> getStudents(Long groupId) {
        Set<Student> students = groupService.getStudents(groupId);
        Set<StudentResponseDto> studentResponseDtos = new HashSet<>();
        students.forEach(student -> {
            StudentResponseDto studentResponseDto = new StudentResponseDto(student);
            studentResponseDtos.add(studentResponseDto);
        });
        return studentResponseDtos;
    }

    @Override
    public void addStudent(Long groupId, Long studentId) {
        groupService.addStudent(groupId,studentId);
    }

    @Override
    public void removeStudent(Long groupId, Long studentId) {
        groupService.removeStudent(groupId,studentId);
    }

    @Override
    public void create(GroupRequestDto entity) {
        Group group = new Group();
        group.setGroupType(entity.getGroupType());
        group.setName(entity.getName());
        group.setNameMentor(entity.getNameMentor());
        groupService.create(group);
    }

    @Override
    public void delete(Long id) {
        groupService.delete(id);
    }

    @Override
    public void update(GroupRequestDto entity, Long id) throws SQLException {
        Group group = groupService.findById(id).get();
        group.setGroupType(entity.getGroupType());
        group.setName(entity.getName());
        group.setNameMentor(entity.getNameMentor());
        groupService.update(group);
    }

    @Override
    public GroupResponseDto findById(Long id) {
        return new GroupResponseDto(groupService.findById(id).get());
    }

    @Override
    public PageData<GroupResponseDto> findAll(WebRequest request) throws IOException {
        PageAndSizeData pageAndSizeData = WebRequestUtil.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtil.generateSortData(request);
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);
        DataTableResponse<Group> all = groupService.findAll(dataTableRequest);
        List<GroupResponseDto> list = mapDTRespToListOfDto(all);
        PageData<GroupResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(list, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }

    private List<GroupResponseDto> mapDTRespToListOfDto(DataTableResponse<Group> all) {
        return all.getItems().
                stream().
                map(GroupResponseDto::new).
                peek(dto -> dto.setStudentCount((Long) all.getOtherParamMap().get(dto.getId()))).
                collect(Collectors.toList());
    }
}
