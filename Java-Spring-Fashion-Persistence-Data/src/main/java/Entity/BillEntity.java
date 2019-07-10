package Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "BILL")
public class BillEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer billId;
    String customerName;
    String phoneNumber;
    String deliveryAddress;
    Boolean billStatus;
    Date setUpDate;
    String typeReceiving;
    String note;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idBill")
    Set<BillDetailEntity> billDetailEntities;


}
