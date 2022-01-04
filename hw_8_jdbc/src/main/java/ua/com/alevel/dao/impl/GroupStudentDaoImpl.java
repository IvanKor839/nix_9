package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.dao.GroupStudentDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Group;
import ua.com.alevel.entity.GroupStudent;
import ua.com.alevel.entity.Student;
import ua.com.alevel.type.GroupType;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class GroupStudentDaoImpl implements GroupStudentDao {

    private final JpaConfig jpaConfig;

    public GroupStudentDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(GroupStudent entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("INSERT INTO groups_students VALUES(default,?,?)")) {
            preparedStatement.setLong(1, entity.getGroup().getId());
            preparedStatement.setLong(2, entity.getStudent().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("DELETE FROM groups_students WHERE id = "+ id)){
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(GroupStudent entity) throws SQLException {
        try(PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("UPDATE groups_students SET id_group=? id_student=? WHERE id = "+entity.getId())) {
            preparedStatement.setLong(1,entity.getGroup().getId());
            preparedStatement.setLong(2,entity.getStudent().getId());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        long count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("SELECT COUNT(*) FROM groups_students WHERE id = " + id)) {
            while (resultSet.next()) {
                count = resultSet.getLong("COUNT(*)");
                System.out.println("count = " + count);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return count == 1;
    }

    @Override
    public GroupStudent findById(Long id) {
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery("select * from groups_students join `groups` g on " +
                "groups_students.id_group = g.id JOIN students s on groups_students.id_student = s.id WHERE groups_students.id= "+id)) {
            while(resultSet.next()){
                return initGroupStudentByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public long count() {
        int count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("SELECT COUNT(*) as count FROM groups_students")) {
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
    @Override
    public DataTableResponse<GroupStudent> findAll(DataTableRequest request) throws IOException {
        List<GroupStudent> groupStudent = new ArrayList<>();
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        String sql = "select * from groups_students " +
                "join groups as g on groups_students.id_group = g.id " +
                "join students as s on groups_students.id_student = s.id " +
                "order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                groupStudent.add(initGroupStudentByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        DataTableResponse<GroupStudent> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(groupStudent);
        return dataTableResponse;
    }

    @Override
    public void deleteAllStudent(Long groupId) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("DELETE FROM groups_students WHERE id_group = " + groupId)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void deleteAllGroup(Long studentId) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("DELETE  FROM groups_students where id_student = " + studentId)){
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private GroupStudent initGroupStudentByResultSet(ResultSet resultSet) throws SQLException {
        GroupStudent groupStudent = new GroupStudent();
        groupStudent.setId(resultSet.getLong("groups_students.id"));

        Group group = new Group();
        group.setId(resultSet.getLong("g.id"));
        group.setCreated(resultSet.getTimestamp("g.created"));
        group.setUpdated(resultSet.getTimestamp("g.updated"));
        group.setName(resultSet.getString("name"));
        group.setNameMentor(resultSet.getString("name_mentor"));
        group.setGroupType(GroupType.valueOf(resultSet.getString("group_type")));

        Student student = new Student();
        student.setId(resultSet.getLong("s.id"));
        student.setCreated(resultSet.getTimestamp("s.created"));
        student.setUpdated(resultSet.getTimestamp("s.updated"));
        student.setFirstName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));
        student.setAge(resultSet.getInt("age"));

        groupStudent.setGroup(group);
        groupStudent.setStudent(student);

        return groupStudent;
    }
}
