package Service;

import Dao.ProductColorDao;
import Entity.ProductColorEntity;
import Interface.ProductColorInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductColorService implements ProductColorInterface {
    @Autowired
    ProductColorDao productColorDao;
    public List<ProductColorEntity> getListAll() {
        return productColorDao.getListAll();
    }
}
