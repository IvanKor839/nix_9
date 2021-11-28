package ua.com.alevel.facade;

import ua.com.alevel.dto.BaseRequestDto;
import ua.com.alevel.dto.BaseResponseDto;

import java.io.IOException;
import java.util.Collection;

public interface BaseFacade<RES extends BaseResponseDto , REQ extends BaseRequestDto> {

    void create(REQ req);

    void update(REQ req, String id);

    void delete(String id);

    RES findById(String id);

    Collection<RES> findAll() throws IOException;
}
