package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.datatable.DataTableRequest;
import ua.com.alevel.datatable.DataTableResponse;
import ua.com.alevel.entity.Group;
import ua.com.alevel.type.GroupType;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.*;

@Service
public class GroupDaoImpl implements GroupDao {

    private final JpaConfig jpaConfig;

    public GroupDaoImpl(JpaConfig jpaConfig) {
        this.jpaConfig = jpaConfig;
    }

    @Override
    public void create(Group entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("INSERT INTO groups VALUES(default,?,?,?,?,?)")) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setString(3, entity.getName());
            preparedStatement.setString(4, entity.getNameMentor());
            preparedStatement.setString(5, entity.getGroupType().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("DELETE FROM groups WHERE id = " + id)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public void update(Group entity) {
        try (PreparedStatement preparedStatement = jpaConfig.getConnection().prepareStatement("UPDATE groups SET updated=? name = ? name_mentor = ? group_type = ? where id = " + entity.getId())) {
            preparedStatement.setTimestamp(1, new Timestamp(new Date().getTime()));
            preparedStatement.setString(2, entity.getName());
            preparedStatement.setString(3, entity.getNameMentor());
            preparedStatement.setString(4, entity.getGroupType().name());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
    }

    @Override
    public boolean existById(Long id) {
        long count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("SELECT COUNT(*) FROM groups WHERE id = " + id)) {
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
    public Group findById(Long id) {
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("SELECT * FROM groups WHERE id = " + id)) {
            while (resultSet.next()) {
                return initGroupByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public long count() {
        int count = 0;
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("SELECT COUNT(*) as count FROM groups")) {
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    @Override
    public DataTableResponse<Group> findAll(DataTableRequest request) throws IOException {
        List<Group> groups = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        String sql = "select `groups`.id, `groups`.created, `groups`.updated, `groups`.group_type, `groups`.name, `groups`.name_mentor, count(id_student) as student_count " +
                " from groups left outer join groups_students as gs on `groups`.id = gs.id_group group by  `groups`.id order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();

        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                GroupResultSet groupResultSet = convertToGroupResultSet(resultSet);
                groups.add(groupResultSet.getGroup());
                otherParamMap.put(groupResultSet.getGroup().getId(), groupResultSet.getStudentCount());
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        DataTableResponse<Group> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(groups);
        dataTableResponse.setOtherParamMap(otherParamMap);
        return dataTableResponse;
    }

    @Override
    public List<Group> findAll() {
        List<Group> groupList = new ArrayList<>();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery("SELECT * FROM groups")) {
            while (resultSet.next()) {
                groupList.add(initGroupByResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groupList;
    }

    private Group initGroupByResultSet(ResultSet resultSet) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String nameMentor = resultSet.getString("name_mentor");
        String type = resultSet.getString("group_type");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");

        Group group = new Group();
        group.setId(id);
        group.setName(name);
        group.setNameMentor(nameMentor);
        group.setGroupType(GroupType.valueOf(type));
        group.setCreated(new Date(created.getTime()));
        group.setUpdated(new Date(updated.getTime()));
        return group;
    }

    private GroupResultSet convertToGroupResultSet(ResultSet resultSet) throws SQLException {
        Group group = initGroupByResultSet(resultSet);
        long studentCount = resultSet.getInt("student_count");
        return new GroupResultSet(group, studentCount);
    }

    @Override
    public DataTableResponse<Group> findByStudentId(DataTableRequest request, Long id) {
        List<Group> groupList = new ArrayList<>();
        Map<Object, Object> otherParamMap = new HashMap<>();
        int limit = (request.getCurrentPage() - 1) * request.getPageSize();
        String sql = "select groups.id, groups.created, groups.updated, name, name_mentor, group_type, count(id_student) as student_count " +
                "from `groups` left outer join groups_students as gs on `groups`.id = gs.id_group where `groups`.id in (select id_group from groups_students where id_student= " + id + ")" +
                "group by `groups`.id order by " +
                request.getSort() + " " +
                request.getOrder() + " limit " +
                limit + "," +
                request.getPageSize();
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql)) {
            while (resultSet.next()) {
                GroupResultSet groupResultSet = convertToGroupResultSet(resultSet);
                groupList.add(groupResultSet.getGroup());
                otherParamMap.put(groupResultSet.getGroup().getId(), groupResultSet.getStudentCount());
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        DataTableResponse<Group> dataTableResponse = new DataTableResponse<>();
        dataTableResponse.setItems(groupList);
        dataTableResponse.setOtherParamMap(otherParamMap);
        return dataTableResponse;
    }

    public int countByStudentId(Long studentId) {
        int count = 0;
        String sql = "select count(*) as count from groups_students where id_student =";
        try (ResultSet resultSet = jpaConfig.getStatement().executeQuery(sql + studentId)) {
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }

    private record GroupResultSet(Group group, long studentCount) {

        public Group getGroup() {
            return group;
        }

        public long getStudentCount() {
            return studentCount;
        }
    }
}
