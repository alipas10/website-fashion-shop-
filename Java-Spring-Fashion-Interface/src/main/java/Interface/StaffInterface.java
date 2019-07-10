package Interface;

import Entity.StaffEntity;

public interface StaffInterface {
    StaffEntity checkLogin(String name, String password);
    StaffEntity creatStaff (String name, String passcode);
}
