package Dto;

import Entity.CatalogEntity;
import Entity.DiscountEntity;
import Entity.ProductDetailEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
public class ProductDto implements Serializable {
    Integer productId;
    CatalogEntity catalogEntity;

    String productName;
    String price;
    String productDescription;
    String productImage;
    String reserve;
    Set<ProductDetailEntity> productDetailEntities;
    Set<DiscountEntity> discountEntities;


}

