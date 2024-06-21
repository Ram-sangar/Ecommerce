
package com.ecommerce.demo.Category;

import com.ecommerce.demo.Address.Address;
import com.ecommerce.demo.ApiResponse;
import com.ecommerce.demo.Carts.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createCategory (@RequestBody Category category){

        if(! categoryService.findId(category.getUserId())) {
            return new ResponseEntity<>(new ApiResponse(false,"user id does not exist"),HttpStatus.NOT_FOUND);}
        categoryService.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true,"create a new  category"), HttpStatus.CREATED);
    }

    @GetMapping("/List")
    public ResponseEntity<List<Category>> listcategory(){
        List<Category> categoryList = categoryService.listcategory();
        return ResponseEntity.ok(categoryList);
    }

    @GetMapping("/List/{categoryId}")
    public Optional<Category> findById(@PathVariable("categoryId") long categoryId){
        return categoryService.findById(categoryId);
    }

    @PutMapping("/update/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") long categoryId,@RequestBody Category category){

        //check id
        if (!categoryService.findId(categoryId)) {
         return new ResponseEntity<>(new ApiResponse(false,"category does not exist"),HttpStatus.NOT_FOUND);}

        // Validate that at least one field is not null
        if (category.getCategoryName() == null && category.getCategoryDescription() == null && category.getImageurl() == null) {
            return new ResponseEntity<>(new ApiResponse(false,"At least one field must be provided."),HttpStatus.NOT_FOUND);
        }

        //save
        categoryService.editcategory(categoryId,category);

        return  new ResponseEntity<>(new ApiResponse(true,"category update successfully"),HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<String> deleteById(@PathVariable("categoryId") long categoryid){
        System.out.println("delete by categoryid "+ categoryid);
        categoryService.deleteByid(categoryid);
        return ResponseEntity.ok("delete success");
    }


}




















































































































































































































