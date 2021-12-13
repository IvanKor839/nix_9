package ua.com.alevel.facade;

import ua.com.alevel.dto.RequestDto;
import ua.com.alevel.dto.ResponseDto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface BaseFacade<RES extends ResponseDto, REQ extends RequestDto>{

    void create(REQ entity);
    void delete(Long id);
    void update(REQ entity, Long id) throws SQLException;
    RES findById(Long id);
    List<RES> findAll() throws IOException;
}
