package ua.com.alevel.facade;

import ua.com.alevel.view.dto.request.StudentRequestDto;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.view.dto.response.StudentResponseDto;

import java.util.Set;


public interface StudentFacade extends BaseFacade<StudentResponseDto, StudentRequestDto>{

    Set<GroupResponseDto> getGroups(Long studentId);
}
