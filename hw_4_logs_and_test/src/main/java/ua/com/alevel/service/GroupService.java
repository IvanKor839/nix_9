package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.GroupDao;
import ua.com.alevel.entity.Group;

public class GroupService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");

    private final GroupDao groupDao = new GroupDao();

    public void create(Group group) {
        LOGGER_INFO.info("group start created");
        groupDao.create(group);
        LOGGER_INFO.info("group finish created");
    }

    public void update(Group group) {
        groupDao.update(group);
    }

    public void delete(String id) {
        LOGGER_WARN.warn("group start deleted");
        groupDao.delete(id);
        LOGGER_WARN.warn("group finish deleted");
    }

    public Group findById(String id) {
        try {
            return groupDao.findById(id);
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("group not found by id: " + id);
        }
        return null;
    }

    public Group[] findAll() {
        return groupDao.findAll();
    }
}
