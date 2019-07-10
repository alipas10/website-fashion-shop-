package Dao;

import Entity.StaffEntity;
import Interface.StaffInterface;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class StaffDao implements StaffInterface {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public StaffEntity checkLogin(String email, String passcode) {
        Session session = sessionFactory.getCurrentSession();
        try {
            StringBuilder sql = new StringBuilder(" FROM StaffEntity se WHERE se.userName='" + email + "' AND se.passcode='" + passcode + "' ");
            StaffEntity staffEntity = (StaffEntity) session.createQuery(sql.toString()).getSingleResult();
            if (staffEntity != null) {
                return staffEntity;
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return null;
    }

    @Transactional
    public StaffEntity creatStaff(String name, String passcode) {
        Session session = sessionFactory.getCurrentSession();
        try {
            StaffEntity staffEntity = StaffEntity.builder().userName(name).passcode(passcode).build();
//            staffEntity.setUserName(name);
//            staffEntity.setPasscode(passcode);
            Integer result = (Integer) session.save(staffEntity);
            if (result != null) {
                return staffEntity;
            }
        } catch (HibernateException e) {
            e.getMessage();
        }
        return null;
    }
}
