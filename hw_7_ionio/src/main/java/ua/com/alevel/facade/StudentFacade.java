package ua.com.alevel.facade;

import ua.com.alevel.dto.student.StudentRequestDto;
import ua.com.alevel.dto.student.StudentResponseDto;

import java.io.IOException;
import java.util.Collection;

public interface StudentFacade extends BaseFacade<StudentResponseDto, StudentRequestDto>{
    Collection<StudentResponseDto> findAllStudentInGroup(String idGroup) throws IOException;
}
