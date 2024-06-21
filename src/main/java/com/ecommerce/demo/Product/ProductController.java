package com.ecommerce.demo.Product;

import com.ecommerce.demo.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createProduct (@RequestBody  Product product){
        if(! productService.findId(product.getCategoryId()) ) {
            return new ResponseEntity<>(new ApiResponse(false ,"category id is missing"),HttpStatus.NOT_FOUND);}

        if(! productService.findId(product.getTempUserId()) ) {
            return new ResponseEntity<>(new ApiResponse(false ,"User id is missing"),HttpStatus.NOT_FOUND);}

        productService.createProduct(product);
        return new ResponseEntity<>(new ApiResponse(true, "product create successfully"),HttpStatus.CREATED);
    }

    @GetMapping("/list")
    public List<Product> listproduct(){
        return productService.listproduct();
    }

    @GetMapping("/List/{productId}")
    public Optional<Product> findById(@PathVariable("productId") long productId){
        return productService.findById(productId);
    }

    //
    @PutMapping("/update/{productId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("productId") long productId, @RequestBody Product product){
        if(!productService.findId(productId)){
            return new ResponseEntity<>(new ApiResponse(false,"product id not found"), HttpStatus.NOT_FOUND);
        }
        if(product.getCategoryId()==null && product.getProductName()==null && product.getProductDescription()==null && product.getProductPrice()==null && product.getTempUserId()==null){
            return new ResponseEntity<>(new ApiResponse(false,"product not found"), HttpStatus.BAD_REQUEST);
        }
        productService.editproduct(productId,product);
        return new ResponseEntity<>(new ApiResponse(true,"product update successfully"), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteById(@PathVariable("productId") long productid){
        System.out.println("delete by productid "+ productid);
        productService.deletebyid(productid);
        return ResponseEntity.ok("delete success");
    }

}


