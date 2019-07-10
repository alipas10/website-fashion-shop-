package Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@Table(name = "CATALOG")
public class CatalogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer catalogId;
    String catalogName;
    String catalogImage;

    @OneToMany
    @JoinColumn(name = "catalogId")
    Set<ProductEntity> productEntities;


}
