package Dao;

import Entity.BillEntity;
import Interface.BillInterface;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BillDao implements BillInterface {

    @Autowired
    SessionFactory sessionFactory;


    @Transactional
    public Integer addCart(BillEntity billEntity) {
        Session session = sessionFactory.getCurrentSession();
        Integer result= (Integer) session.save(billEntity);
        if (result != null) {
            return result;
        }
        else return null;

    }
}
