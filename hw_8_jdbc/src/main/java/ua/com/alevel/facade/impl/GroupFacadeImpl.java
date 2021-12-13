package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.group.GroupRequestDto;
import ua.com.alevel.dto.group.GroupResponseDto;
import ua.com.alevel.entity.Group;
import ua.com.alevel.facade.GroupFacade;
import ua.com.alevel.service.GroupService;

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
    public List<GroupResponseDto> findAll() throws IOException {
        return groupService.findAll()
                .stream()
                .map(GroupResponseDto::new)
                .collect(Collectors.toList());
    }
}
