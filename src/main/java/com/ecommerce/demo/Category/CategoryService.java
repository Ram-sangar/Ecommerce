package com.ecommerce.demo.Category;

import com.ecommerce.demo.Users.User;
import com.ecommerce.demo.Users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    UserRepository userRepository;

    //create
    public void createCategory(Category category) {
        System.out.println("service "+ category.getCategoryDescription());
        User user = userRepository.getById(category.getUserId());

        category.setUser(user);

        categoryRepository.save(category);
    }

    //getall
    public List<Category> listcategory() {
        return categoryRepository.findAll();
    }

    //getbyid
    public Optional<Category> findById(long categoryId) {
        return categoryRepository.findById(categoryId);
    }

    //update
    public void editcategory( long categoryId, Category updatecategory){

        Category category = categoryRepository.getById(categoryId);

        if (updatecategory.getCategoryName() != null) {
            category.setCategoryName(updatecategory.getCategoryName());
            category.setCategortTimstamp(LocalDateTime.now());
        }
        if (updatecategory.getCategoryDescription() != null) {
            category.setCategoryDescription(updatecategory.getCategoryDescription());
            category.setCategortTimstamp(LocalDateTime.now());
        }
        if (updatecategory.getImageurl() != null) {
            category.setImageurl(updatecategory.getImageurl());
            category.setCategortTimstamp(LocalDateTime.now());
        }

        categoryRepository.save(category);
    }

    //delete
    public void deleteByid(long categoryid) {
        categoryRepository.deleteById(categoryid);
    }

    //getid
    public boolean findId(long categoryId) {
        return categoryRepository.findById(categoryId).isPresent();
    }

}
