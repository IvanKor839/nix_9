package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.utill.FacadeUtil;
import ua.com.alevel.utill.WebRequestUtill;
import ua.com.alevel.view.dto.request.GroupRequestDto;
import ua.com.alevel.view.dto.request.PageAndSizeData;
import ua.com.alevel.view.dto.request.SortData;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.entity.Group;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.view.dto.response.PageData;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupFacadeImpl implements GroupFacade {

    private final GroupService groupService;

    public GroupFacadeImpl(GroupService groupService) {
        this.groupService = groupService;
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
        Group group = groupService.findById(id);
        group.setGroupType(entity.getGroupType());
        group.setName(entity.getName());
        group.setNameMentor(entity.getNameMentor());
        groupService.update(group);
    }

    @Override
    public GroupResponseDto findById(Long id) {
        return new GroupResponseDto(groupService.findById(id));
    }

    @Override
    public PageData<GroupResponseDto> findAll(WebRequest request) throws IOException {
        PageAndSizeData pageAndSizeData = WebRequestUtill.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtill.generateSortData(request);
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);
        DataTableResponse<Group> all = groupService.findAll(dataTableRequest);
        List<GroupResponseDto> list = mapDTRespToListOfDto(all);
        PageData<GroupResponseDto> pageData = FacadeUtil.getPageDataFromDTResp(list, pageAndSizeData, sortData);
        pageData.setItemsSize(all.getItemsSize());
        pageData.initPaginationState(pageData.getCurrentPage());
        return pageData;
    }
    @Override
    public List<GroupResponseDto> findAll(){
        return groupService.findAll().stream().map(GroupResponseDto::new).toList();
    }

    @Override
    public PageData<GroupResponseDto> findByStudentId(WebRequest request, Long studentId) {
        PageAndSizeData pageAndSizeData = WebRequestUtill.generatePageAndSizeData(request);
        SortData sortData = WebRequestUtill.generateSortData(request);
        DataTableRequest dataTableRequest = FacadeUtil.getDTReqFromPageAndSortData(pageAndSizeData, sortData);
        DataTableResponse<Group> all = groupService.findByStudentId(dataTableRequest, studentId);
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
