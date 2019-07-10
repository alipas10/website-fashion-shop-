package Dao;

import Entity.CatalogEntity;
import Interface.CatalogInterface;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CatalogDao implements CatalogInterface {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public List<CatalogEntity> getListAll() {
        Session session = sessionFactory.getCurrentSession();
        List<CatalogEntity> catalogEntities =  new ArrayList<CatalogEntity>();
        try {
            StringBuilder sql = new StringBuilder(" FROM CatalogEntity ");
            catalogEntities= session.createQuery(sql.toString()).getResultList();

        } catch (NullPointerException e) {
            throw  e;
        }
        return catalogEntities;
    }
}
