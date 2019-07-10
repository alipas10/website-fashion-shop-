package Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "PRODUCTCOLOR")
public class ProductColorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer productColorId;
    String productColorName;


}
