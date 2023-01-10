package lab.weblab4backend.dao;


import jakarta.persistence.OrderColumn;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Order;
import jakarta.persistence.criteria.Root;
import lab.weblab4backend.model.Hit;
import lab.weblab4backend.model.Token;
import lab.weblab4backend.model.User;
import lab.weblab4backend.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDao {

    public User findByUsername(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root).where(cb.equal(root.get("username"), username));
        Query query = session.createQuery(cr);
        query.setMaxResults(1);
        List<User> resultList = query.getResultList();
        return resultList.get(0);
    }

    public User findByPhone(String phone) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root).where(cb.equal(root.get("phone"), phone));
        Query query = session.createQuery(cr);
        query.setMaxResults(1);
        List<User> resultList = query.getResultList();
        return resultList.size() > 0 ? resultList.get(0) : null;
    }

    public void save(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(user);
        tx1.commit();
        session.close();
    }

}