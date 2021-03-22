package dao;

import models.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import utils.HibernateSessionFactoryUtil;
import java.util.List;

public class TattooDAOImpl implements TattooDAO{
    @Override
    public Tattoo findByT_id(int t_id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Tattoo.class, t_id);
    }
    @Override
    public void save(Tattoo tattoo) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.save(tattoo);
        transaction1.commit();
        session.close();
    }
    @Override
    public void update(Tattoo tattoo) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.update(tattoo);
        transaction1.commit();
        session.close();
    }
    @Override
    public void delete(Tattoo tattoo) {
        Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction1 = session.beginTransaction();
        session.delete(tattoo);
        transaction1.commit();
        session.close();
    }
    @Override
    public Artist findArtistByA_id(int a_id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Artist.class, a_id);
    }
    @Override
    public Artist findArtistByName(String a_name) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Artist.class, a_name);
    }
    @Override
    public Category findCategoryByC_id(int c_id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Category.class, c_id);
    }
    @Override
    public Category findCategoryByName(String c_name) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Category.class, c_name);
    }
    @Override
    public Price findPriceByP_id(int p_id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Price.class, p_id);
    }
    @Override
    public Price findPriceByAmount(int p_amount) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Price.class, p_amount);
    }
    @Override
    public Size findSizeByS_id(int s_id) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Size.class, s_id);
    }
    @Override
    public Size findSizeByName(String s_name) {
        return HibernateSessionFactoryUtil.getSessionFactory().openSession().get(Size.class, s_name);
    }

    @Override
    public List<Tattoo> findAll() {
        return (List<Tattoo>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Tattoo ").list();
    }
    @Override
    public List<Price> findPrices() {
        return (List<Price>)  HibernateSessionFactoryUtil.getSessionFactory().openSession().createQuery("FROM Price ").list();
    }

}
