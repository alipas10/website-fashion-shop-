package Interface;

import Entity.ProductEntity;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface ProductInterface   {
    void deleteProductById (Integer productId );
    List<ProductEntity> getListAllAndLimit(Integer startProduct);
    List<ProductEntity> getListProductById (Integer id);
    ProductEntity getListById(Integer productId);
    Integer addProduct (ProductEntity productEntity);
    void updateProduct(ProductEntity productEntity);
}
