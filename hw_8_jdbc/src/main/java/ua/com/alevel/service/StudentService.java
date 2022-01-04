package ua.com.alevel.service;

import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Student;

import java.util.List;

public interface StudentService extends BaseService<Student> {

    DataTableResponse<Student> findByGroupId(DataTableRequest dataTableRequest, Long groupId);

    List<Student> findAll();
}
