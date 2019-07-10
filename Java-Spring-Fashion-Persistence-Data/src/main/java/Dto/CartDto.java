package Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {
    Integer productDettailId;
    Integer productId;
    Integer productColorId;
    Integer productSizeId;
    String productName;
    String price;
    String colorName;
    String sizeName;
    Integer deal;


}
