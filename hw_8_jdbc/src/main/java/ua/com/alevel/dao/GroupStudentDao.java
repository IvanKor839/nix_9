package ua.com.alevel.dao;

import ua.com.alevel.entity.GroupStudent;

public interface GroupStudentDao extends BaseDao<GroupStudent> {

    void deleteAllStudent(Long groupId);
    void deleteAllGroup(Long studentId);
}
