package Dao;

import Entity.ProductSizeEntity;
import Interface.ProductSizeInterface;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductSizeDao  implements ProductSizeInterface {

    @Autowired
    SessionFactory sessionFactory;
    @Transactional
    public List<ProductSizeEntity> getListAll() {
        Session session = sessionFactory.getCurrentSession();
        List<ProductSizeEntity> productSizeEntities =  new ArrayList<ProductSizeEntity>();
        try {
            StringBuilder sql = new StringBuilder(" FROM ProductSizeEntity ");
            productSizeEntities= session.createQuery(sql.toString()).getResultList();

        } catch (NullPointerException e) {
            throw  e;
        }
        return productSizeEntities;
    }

}
