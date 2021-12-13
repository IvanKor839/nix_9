package ua.com.alevel.facade.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.dto.groupstudent.GroupStudentRequestDto;
import ua.com.alevel.dto.groupstudent.GroupStudentResponseDto;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.GroupStudent;
import ua.com.alevel.entity.Student;
import ua.com.alevel.facade.GroupStudentFacade;
import ua.com.alevel.service.GroupService;
import ua.com.alevel.service.GroupStudentService;
import ua.com.alevel.service.StudentService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupStudentFacadeImpl implements GroupStudentFacade {

    private final GroupService groupService;
    private final StudentService studentService;
    private final GroupStudentService groupStudentService;

    public GroupStudentFacadeImpl(GroupService groupService,
                                  StudentService studentService,
                                  GroupStudentService groupStudentService) {
        this.groupService = groupService;
        this.studentService = studentService;
        this.groupStudentService = groupStudentService;
    }

    @Override
    public void create(GroupStudentRequestDto entity) {
        GroupStudent groupStudent = new GroupStudent();
        groupStudent.setGroupId(entity.getGroupId());
        groupStudent.setStudentId(entity.getStudentId());
        groupStudentService.create(groupStudent);
    }

    @Override
    public void delete(Long id) {
        groupStudentService.delete(id);
    }

    @Override
    public void update(GroupStudentRequestDto entity, Long id) throws SQLException {
        GroupStudent groupStudent = groupStudentService.findById(id);
        groupStudent.setGroupId(entity.getGroupId());
        groupStudent.setStudentId(entity.getStudentId());
        groupStudentService.update(groupStudent);
    }

    @Override
    public GroupStudentResponseDto findById(Long id) {
        GroupStudent groupStudent = groupStudentService.findById(id);
        Student student = studentService.findById(groupStudent.getStudentId());
        Group group = groupService.findById(groupStudent.getGroupId());
        return new GroupStudentResponseDto(groupStudent.getId(), group,student);
    }

    @Override
    public List<GroupStudentResponseDto> findAll() throws IOException {
        return groupStudentService.findAll()
                .stream()
                .map(groupStudent -> findById(groupStudent.getId()))
                .collect(Collectors.toList());
    }
}
