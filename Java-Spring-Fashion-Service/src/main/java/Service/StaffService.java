package Service;

import Dao.StaffDao;
import Entity.StaffEntity;
import Interface.StaffInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StaffService implements StaffInterface {
    @Autowired
    StaffDao staffDao;

    public StaffEntity checkLogin(String email, String passcode) {
        StaffEntity staffEntity = staffDao.checkLogin(email, passcode);
        return staffEntity;
    }

    public StaffEntity creatStaff(String email, String passcode) {
        StaffEntity result = staffDao.creatStaff(email,passcode);
        return  result;
    }
}
