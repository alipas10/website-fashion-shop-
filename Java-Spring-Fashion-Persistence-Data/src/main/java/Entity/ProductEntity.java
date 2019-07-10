package Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
//@Builder
@Table(name = "PRODUCT")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer productId;

    @OneToOne
    @JoinColumn(name = "catalogId")
    CatalogEntity catalogEntity;

    String productName;
    String price;
    String productDescription;
    String productImage;
    String reserve;



    @OneToMany(fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    @JoinColumn(name = "productId")
    Set<ProductDetailEntity> productDetailEntities;


    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "DISCOUNTDETAIL",
            joinColumns = @JoinColumn(name = "productId", referencedColumnName = "productId"),
            inverseJoinColumns = @JoinColumn(name = "discountId", referencedColumnName = "discountId")
    )
    Set<DiscountEntity> discountEntities;


}
