package ua.com.alevel.dao.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.config.jpa.JpaConfig;
import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.entity.Group;
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
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery("SELECT * FROM groups WHERE id = "+id)) {
            while(resultSet.next()){
                return initGroupByResultSet(resultSet);
            }
        } catch (SQLException e) {
            System.out.println("problem: = " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Group> findAll() throws IOException {
        List<Group> groups = new ArrayList<>();
        try(ResultSet resultSet = jpaConfig.getStatement().executeQuery("SELECT * FROM 'groups'")) {
            while (resultSet.next()){
                groups.add(initGroupByResultSet(resultSet));
            }
        }catch (SQLException e){
            System.out.println("problem: = " + e.getMessage());
        }
        return groups;
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
}
