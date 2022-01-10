package ua.com.alevel.persistence.dao.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.dao.GroupDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Group;
import ua.com.alevel.persistence.entity.Student;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@Transactional
public class GroupDaoImpl implements GroupDao {

    private final SessionFactory sessionFactory;

    public GroupDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Set<Student> getStudents(Long id) {
        return sessionFactory.getCurrentSession().find(Group.class, id).getStudents();
    }

    @Override
    public void addStudent(Long groupId, Long studentId) {
        Group group = sessionFactory.getCurrentSession().find(Group.class, groupId);
        Student student = sessionFactory.getCurrentSession().find(Student.class, studentId);
        group.addStudent(student);
    }

    @Override
    public void removeStudent(Long groupId, Long studentId) {
        Group group = sessionFactory.getCurrentSession().find(Group.class, groupId);
        Student student = sessionFactory.getCurrentSession().find(Student.class, studentId);
        group.removeStudent(student);
    }

    @Override
    public void create(Group entity) {
        sessionFactory.getCurrentSession().persist(entity);
    }

    @Override
    public void delete(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("delete from Group g where g.id= :id")
                .setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void update(Group entity) {
        Session session = sessionFactory.getCurrentSession();
        session.merge(entity);
    }

    @Override
    public boolean existById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("select count(g.id) from Group g where g.id = :id")
                .setParameter("id", id);
        return (Long) query.getSingleResult() == 1;
    }

    @Override
    public Group findById(Long id) {
        return sessionFactory.getCurrentSession().find(Group.class, id);
    }

    @Override
    public long count() {
        Query query = sessionFactory.getCurrentSession().createQuery("select count(g.id) from Group g");
        return (Long) query.getSingleResult();
    }

    @Override
    public DataTableResponse<Group> findAll(DataTableRequest request) throws IOException {
        CriteriaBuilder criteriaBuilder = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Group> criteriaQuery = criteriaBuilder.createQuery(Group.class);
        Root<Group> from = criteriaQuery.from(Group.class);
        if (request.getOrder().equals("desc")) {
            criteriaQuery.orderBy(criteriaBuilder.desc(from.get(request.getSort())));
        } else {
            criteriaQuery.orderBy(criteriaBuilder.asc(from.get(request.getSort())));
        }
        int page = (request.getCurrentPage() - 1) * request.getPageSize();
        int size = page + request.getPageSize();

        List<Group> items = sessionFactory.getCurrentSession().createQuery(criteriaQuery)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
        Map<Object, Object> otherParamMap = new HashMap<>();
        items.forEach(item -> otherParamMap.put(item.getId(), (long) getStudents(item.getId()).size()));
        DataTableResponse<Group> response = new DataTableResponse<>();
        response.setItems(items);
        response.setOtherParamMap(otherParamMap);
        return response;
    }
}
