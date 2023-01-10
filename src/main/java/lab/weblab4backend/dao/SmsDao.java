package lab.weblab4backend.dao;


import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lab.weblab4backend.model.SMS;
import lab.weblab4backend.model.Token;
import lab.weblab4backend.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class SmsDao {

    public SMS findByPhone(String token) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<SMS> cr = cb.createQuery(SMS.class);
        Root<SMS> root = cr.from(SMS.class);
        cr.select(root).where(cb.equal(root.get("phone"), token)).orderBy(cb.desc(root.get("createdAt")));
        Query query = session.createQuery(cr);
        query.setMaxResults(1);
        List<SMS> resultList = query.getResultList();
        return resultList.size() > 0 ? resultList.get(0) : null;
    }

    public void save(SMS token) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(token);
        tx1.commit();
        session.close();
    }

}