package Service;

import Dao.BillDetailDao;
import Entity.BillDetailEntity;
import Interface.BillDetailInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillDetailService implements BillDetailInterface {
    @Autowired
    BillDetailDao billDetailDao;
    public Boolean addBillDetaild(BillDetailEntity billDetailEntity) {
        return billDetailDao.addBillDetaild(billDetailEntity);
    }
}
