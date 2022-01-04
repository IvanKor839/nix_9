package ua.com.alevel.service;

import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Group;

import java.util.List;

public interface GroupService extends BaseService<Group>{

    DataTableResponse<Group> findByStudentId(DataTableRequest dataTableRequest, Long studentId);

    List<Group> findAll();
}
