package ua.com.alevel.dao;

import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Group;

import java.util.List;

public interface GroupDao extends BaseDao<Group>{

    DataTableResponse<Group> findByStudentId(DataTableRequest request, Long id);
    int countByStudentId(Long studentId);
    List<Group> findAll();
}
