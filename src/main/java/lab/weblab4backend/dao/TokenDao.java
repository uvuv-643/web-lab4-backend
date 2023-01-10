package lab.weblab4backend.dao;


import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import lab.weblab4backend.model.Hit;
import lab.weblab4backend.model.Token;
import lab.weblab4backend.model.User;
import lab.weblab4backend.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class TokenDao {

    public Token findByToken(String token) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Token> cr = cb.createQuery(Token.class);
        Root<Token> root = cr.from(Token.class);
        cr.select(root).where(cb.equal(root.get("token"), token));
        Query query = session.createQuery(cr);
        query.setMaxResults(1);
        List<Token> resultList = query.getResultList();
        return resultList.get(0);
    }

    public void save(Token token) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(token);
        tx1.commit();
        session.close();
    }

}