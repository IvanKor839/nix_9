package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.dao.GroupStudentDao;
import ua.com.alevel.entity.GroupStudent;
import ua.com.alevel.entity.Student;

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
            preparedStatement.setLong(1, entity.getGroupId());
            preparedStatement.setLong(2, entity.getStudentId());
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
            preparedStatement.setLong(1,entity.getGroupId());
            preparedStatement.setLong(2,entity.getStudentId());
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
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery("SELECT * FROM groups_students WHERE id = "+id)) {
            while(resultSet.next()){
                return initGroupStudentByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<GroupStudent> findAll() throws IOException {
        List<GroupStudent> groupStudent = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery("SELECT * FROM groups_students")) {
            while (resultSet.next()) {
                groupStudent.add(initGroupStudentByResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return groupStudent;
    }

    @Override
    public void deleteAllStudent(Long groupId) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("DELETE FROM students WHERE id_group = " + groupId)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void deleteAllGroup(Long studentId) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("DELETE * FROM groups_student" + studentId)){
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println("problem: = " + e.getMessage());
        }
    }

    private GroupStudent initGroupStudentByResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        Long idGroup = resultSet.getLong("id_group");
        Long idStudent = resultSet.getLong("id_student");
        GroupStudent groupStudent = new GroupStudent();
        groupStudent.setId(id);
        groupStudent.setGroupId(idGroup);
        groupStudent.setStudentId(idStudent);
        return groupStudent;
    }
}
