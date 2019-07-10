package Service;

import Dao.ProductDao;
import Entity.ProductEntity;
import Interface.ProductInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductInterface {

    @Autowired
    ProductDao productDao;

    public void deleteProductById(Integer productId) {
        productDao.deleteProductById(productId);
    }

    public List<ProductEntity> getListAllAndLimit(Integer startProduct) {
        return productDao.getListAllAndLimit(startProduct);
    }

    public List<ProductEntity> getListProductById(Integer id) {
        return  productDao.getListProductById(id);
    }

    public ProductEntity getListById(Integer productId) {
        productDao.getListById(productId);

        return productDao.getListById(productId);
    }

    public Integer addProduct(ProductEntity productEntity) {
        return productDao.addProduct(productEntity);
    }

    public void updateProduct(ProductEntity productEntity) {
        productDao.updateProduct(productEntity);
    }
}
