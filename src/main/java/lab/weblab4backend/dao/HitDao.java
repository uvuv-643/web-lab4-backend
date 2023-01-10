package lab.weblab4backend.dao;


import lab.weblab4backend.model.Hit;
import lab.weblab4backend.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class HitDao {

    public Hit findById(int id) {
        return HibernateUtil.getSessionFactory().openSession().get(Hit.class, id);
    }

    public void save(Hit hit) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(hit);
        tx1.commit();
        session.close();
    }

    public void delete(Hit hit) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(hit);
        tx1.commit();
        session.close();
    }

    public List<Hit> findAll() {
        List<Hit> hits = (List<Hit>)  HibernateUtil.getSessionFactory().openSession().createQuery("From Hit").list();
        return hits;
    }

}