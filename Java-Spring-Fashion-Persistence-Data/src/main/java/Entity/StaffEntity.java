package Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@Builder
@Table(name = "STAFF")
public class StaffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer staffId;
    String staffName;
    String address;
    Boolean sex;
    String idCard;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "roleId")
    RoleEntity roleEntity;

    String email;
    String userName;
    String passcode;


}
