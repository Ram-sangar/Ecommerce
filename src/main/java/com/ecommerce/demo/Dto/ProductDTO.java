package com.ecommerce.demo.Dto;



import com.ecommerce.demo.Product.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    Product product;

    private long temporaryUserId;
    private int tempCategoryId;


}




