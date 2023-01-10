package lab.weblab4backend.utils;

import lab.weblab4backend.model.Hit;
import lab.weblab4backend.model.SMS;
import lab.weblab4backend.model.Token;
import lab.weblab4backend.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

    private static SessionFactory sessionFactory;

    private HibernateUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Hit.class);
                configuration.addAnnotatedClass(User.class);
                configuration.addAnnotatedClass(Token.class);
                configuration.addAnnotatedClass(SMS.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());

            } catch (Exception e) {
                System.out.println("Исключение!" + e);
            }
        }
        return sessionFactory;
    }

}
