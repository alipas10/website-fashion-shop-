package Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "PRODUCTSIZE")
public class ProductSizeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer productSizeId;
    String productSizeName;


}
