package ua.com.alevel.facade;

import ua.com.alevel.persistence.entity.Student;
import ua.com.alevel.view.dto.request.GroupRequestDto;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.view.dto.response.StudentResponseDto;

import java.util.Set;

public interface GroupFacade extends BaseFacade<GroupResponseDto, GroupRequestDto>{

    Set<StudentResponseDto> getStudents(Long groupId);
    void addStudent(Long groupId, Long studentId);
    void removeStudent(Long groupId, Long studentId);
}
