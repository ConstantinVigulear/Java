package utils;

import models.*;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateSessionFactoryUtil {
    private static SessionFactory sessionFactory;

    private HibernateSessionFactoryUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Artist.class);
                configuration.addAnnotatedClass(Category.class);
                configuration.addAnnotatedClass(Price.class);
                configuration.addAnnotatedClass(Size.class);
                configuration.addAnnotatedClass(Tattoo.class);
                StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception exception) {
                System.out.println("Exception!!!" + exception);
            }
        }
        return sessionFactory;
    }
}
