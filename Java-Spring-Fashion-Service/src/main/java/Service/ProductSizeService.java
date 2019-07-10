package Service;

import Dao.ProductSizeDao;
import Entity.ProductSizeEntity;
import Interface.ProductSizeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductSizeService implements ProductSizeInterface {
    @Autowired
    ProductSizeDao productSizeDao;

    public List<ProductSizeEntity> getListAll() {
        return productSizeDao.getListAll();
    }

}
