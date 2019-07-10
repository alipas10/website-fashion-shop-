package Entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "PRODUCTDETAIL")
public class ProductDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer productDetailId;

    @OneToOne()
    @JoinColumn(name = "productId")
    ProductEntity productEntity;

    @OneToOne()
    @JoinColumn(name = "productColorId")
    ProductColorEntity productColorEntity;

    @OneToOne()
    @JoinColumn(name = "productSizeId")
    ProductSizeEntity productSizeEntity;

    Integer deal;
    Date cereiptDate;


}
