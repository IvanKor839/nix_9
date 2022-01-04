package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.entity.Group;
import ua.com.alevel.view.dto.request.GroupRequestDto;
import ua.com.alevel.view.dto.response.GroupResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.util.List;

public interface GroupFacade extends BaseFacade<GroupResponseDto, GroupRequestDto>{

    PageData<GroupResponseDto> findByStudentId(WebRequest request, Long studentId);
    List<GroupResponseDto> findAll();
}
