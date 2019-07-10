package Dao;

import Entity.ProductDetailEntity;
import Entity.ProductEntity;
import Interface.ProductInterface;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class ProductDao implements ProductInterface {

    @Autowired
    SessionFactory sessionFactory;

    @Transactional
    public void deleteProductById(Integer productId) {
        Session session = sessionFactory.getCurrentSession();
        ProductEntity productEntity = session.get(ProductEntity.class, productId);
        Set<ProductDetailEntity> productDetailEntities = productEntity.getProductDetailEntities();
        try {
            for (ProductDetailEntity productDetailEntity : productDetailEntities) {
                session.createQuery("delete BillDetailEntity WHERE productDetailId = " + productDetailEntity.getProductDetailId()).executeUpdate();
            }
            session.createQuery("delete ProductDetailEntity WHERE productEntity.productId = " + productId).executeUpdate();
            session.createQuery("delete ProductEntity WHERE productId = " + productId).executeUpdate();

        } catch (HibernateException e) {
            e.getMessage();
        }

    }

    @Transactional
    public List<ProductEntity> getListAllAndLimit(Integer startProduct) {
        Session session = sessionFactory.getCurrentSession();
        List<ProductEntity> productEntities = new ArrayList<ProductEntity>();
        if (startProduct < 0) {
            StringBuilder sql = new StringBuilder(" FROM ProductEntity ");
            productEntities = (List<ProductEntity>) session.createQuery(sql.toString()).getResultList();
            return productEntities;
        } else {
            StringBuilder sql = new StringBuilder(" FROM ProductEntity ");
            productEntities = (List<ProductEntity>) session.createQuery(sql.toString()).setFirstResult(startProduct).setMaxResults(2).getResultList();
            return productEntities;
        }
    }

    @Transactional
    public ProductEntity getListById(Integer productId) {
        Session session = sessionFactory.getCurrentSession();
        StringBuilder sql = new StringBuilder(" FROM ProductEntity pe WHERE pe.productId = ");
        sql.append(productId);
        ProductEntity productEntities = (ProductEntity) session.createQuery(sql.toString()).getSingleResult();
        return productEntities;
    }

    @Transactional
    public Integer addProduct(ProductEntity productEntity) {
        Session session = sessionFactory.getCurrentSession();
        Integer productId = (Integer) session.save(productEntity);
        return productId;
    }

    @Transactional
    public void updateProduct(ProductEntity productEntity) {
        Session session = sessionFactory.getCurrentSession();
        session.update(productEntity);
    }

    @Transactional
    public List<ProductEntity> getListProductById(Integer id) {
        Session session = sessionFactory.getCurrentSession();
        List<ProductEntity> productEntities = new ArrayList<ProductEntity>();

        if (id != null) {
            try {
                StringBuilder sql = new StringBuilder(" FROM ProductEntity pe WHERE pe.catalogEntity.catalogId = ");
                sql.append(id);
                productEntities = (List<ProductEntity>) session.createQuery(sql.toString()).getResultList();
            } catch (HibernateException e) {
                return null;
            }
        }
        return productEntities;
    }
}
