package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.dao.StudentDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Student;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class StudentDaoImpl implements StudentDao {

    private final JpaConfig jpaConfig;

    public StudentDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Student entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("INSERT INTO students VALUES(default,?,?,?,?,?)")) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setString(3, entity.getFirstName());
            preparedStatement.setString(4, entity.getLastName());
            preparedStatement.setInt(5, entity.getAge());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("DELETE FROM students WHERE id = " + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(Student entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("UPDATE students SET  updated=?  first_name = ? last_name = ? age = ? where id = " + entity.getId())) {
            preparedStatement.setTimestamp(1, new Timestamp(new Date().getTime()));
            preparedStatement.setString(2, entity.getFirstName());
            preparedStatement.setString(3, entity.getLastName());
            preparedStatement.setInt(4, entity.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        long count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("SELECT COUNT(*) FROM students WHERE id = " + id)) {
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
    public Student findById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("SELECT * FROM students WHERE id = " + id)) {
            while (resultSet.next()) {
                return initStudentByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public long count() {
        int count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("SELECT COUNT(*) as count FROM students")) {
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public DataTableResponse<Student> findAll(DataTableRequest request) throws IOException {
        List<Student> students = new ArrayList<>();
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        String sql = "select * from students order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();

        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                students.add(getStudentFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        DataTableResponse<Student> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(students);
        return dataTableResponse;
    }

    @Override
    public List<Student> findAll() {
        List<Student> students = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("SELECT * FROM students")) {
            while (resultSet.next()) {
                students.add(initStudentByResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    private Student initStudentByResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String firstName = resultSet.getString("first_name");
        String lastName = resultSet.getString("last_name");
        int age = resultSet.getInt("age");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        Student student = new Student();
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setAge(age);
        student.setCreated(new Date(created.getTime()));
        student.setUpdated(new Date(updated.getTime()));
        return student;
    }

    @Override
    public DataTableResponse<Student> findByGroupId(DataTableRequest request, Long groupId) {
        List<Student> students = new ArrayList<>();
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        String sql = "select * from `students` where `students`.id in (select id_student from groups_students where id_group= " + groupId + ")" +
                "group by `students`.id order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                students.add(getStudentFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        DataTableResponse<Student> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(students);
        return dataTableResponse;
    }

    @Override
    public int countByGroupId(Long groupId) {
        int count = 0;
        String sql = "select count(*) as count from groups_students where id_group =";
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql + groupId)) {
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    private Student getStudentFromResultSet(ResultSet resultSet) throws SQLException {
        Student student = new Student();
        student.setId(resultSet.getLong("id"));
        student.setCreated(resultSet.getTimestamp("created"));
        student.setUpdated(resultSet.getTimestamp("updated"));
        student.setFirstName(resultSet.getString("first_name"));
        student.setLastName(resultSet.getString("last_name"));
        student.setAge(resultSet.getInt("age"));
        return student;
    }
}
