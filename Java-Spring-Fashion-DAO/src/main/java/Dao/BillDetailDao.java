package Dao;

import Entity.BillDetailEntity;
import Entity.BillDetailId;
import Interface.BillDetailInterface;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class BillDetailDao implements BillDetailInterface {

    @Autowired
    SessionFactory sessionFactory;


    @Transactional
    public Boolean addBillDetaild(BillDetailEntity billDetailEntity) {
        Session session = sessionFactory.getCurrentSession();
        BillDetailId billDetailId = (BillDetailId) session.save(billDetailEntity);
        if (billDetailId != null ) {
            return true;

        }else         return false;

    }
}
