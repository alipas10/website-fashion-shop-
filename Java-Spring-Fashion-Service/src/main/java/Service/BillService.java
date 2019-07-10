package Service;

import Dao.BillDao;
import Entity.BillEntity;
import Interface.BillInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Service
public class BillService implements BillInterface {
    @Autowired
    BillDao billDao;

    public Integer addCart(BillEntity billEntity) {
        return billDao.addCart(billEntity);
    }
}
