package Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "DISCOUNT")
public class DiscountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer discountId;
    String discountName;
    Date starDate;
    Date endDate;
    String discountDescription;
    String discountImage;
    Integer discountPrice;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "DISCOUNTDETAIL",
            joinColumns = @JoinColumn(name = "discountId", referencedColumnName = "discountId"),
            inverseJoinColumns = @JoinColumn(name = "productId", referencedColumnName = "productId")
    )
    Set<ProductEntity> productEntities;



}
