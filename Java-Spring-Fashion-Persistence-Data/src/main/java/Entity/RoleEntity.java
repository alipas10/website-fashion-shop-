package Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "ROLE")
public class RoleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer roleId;
    String roleName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "roleId")
    Set<StaffEntity> staffEntities;


}
