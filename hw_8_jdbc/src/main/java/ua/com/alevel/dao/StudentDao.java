package ua.com.alevel.dao;

import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Student;

import java.util.List;

public interface StudentDao extends BaseDao<Student>{

    DataTableResponse<Student> findByGroupId(DataTableRequest request, Long groupId);
    int countByGroupId(Long studentId);
    List<Student> findAll();
}
