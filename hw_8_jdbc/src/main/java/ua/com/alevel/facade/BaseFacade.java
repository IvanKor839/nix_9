package ua.com.alevel.facade;

import org.springframework.web.context.request.WebRequest;
import ua.com.alevel.view.dto.RequestDto;
import ua.com.alevel.view.dto.ResponseDto;
import ua.com.alevel.view.dto.response.PageData;

import java.io.IOException;
import java.sql.SQLException;

public interface BaseFacade<RES extends ResponseDto, REQ extends RequestDto>{

    void create(REQ entity);
    void delete(Long id);
    void update(REQ entity, Long id) throws SQLException;
    RES findById(Long id);
    PageData<RES> findAll(WebRequest request) throws IOException;
}
