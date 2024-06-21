package com.ecommerce.demo.Product;


import com.ecommerce.demo.Category.Category;
import com.ecommerce.demo.Category.CategoryRepository;
import com.ecommerce.demo.Users.UserRepository;
import com.ecommerce.demo.Users.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CategoryRepository categoryRepository;
    /*
        User user = userRepository.getById(category.getUserId());

        category.setUser(user);

        categoryRepository.save(category);
    }
*/

    public Product createProduct(Product product) {
        //check user-id
        System.out.println(product.getTempUserId());
        if (product.getTempUserId() == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        User user = userRepository.getById(product.getTempUserId());

        product.setUser(user);

        Category category = categoryRepository.findById(product.getCategoryId()).orElseThrow(() -> new IllegalArgumentException("CategoryId  does not exist"));
        product.setCategory(category);
        product.setProductTimestamp(new Timestamp(System.currentTimeMillis()));
        return productRepository.save(product);
    }

    public List<Product> listproduct() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(long productId) {
        return productRepository.findById(productId);
    }

    public void editproduct(long productId, Product updateproduct) {
        Product product = productRepository.getById(productId);
        if(product.getProductName()!=null){product.setProductName(product.getProductName()); product.setProductTimestamp(Timestamp.valueOf(LocalDateTime.now()));}
        if(product.getProductDescription()!=null){product.setProductDescription(product.getProductDescription());product.setProductTimestamp(Timestamp.valueOf(LocalDateTime.now()));}
        if(product.getGstPercentage()!=null){     product.setGstPercentage(product.getGstPercentage());product.setProductTimestamp(Timestamp.valueOf(LocalDateTime.now()));}
        if(product.getProductPrice()!=null){product.setProductPrice(product.getProductPrice());product.setProductTimestamp(Timestamp.valueOf(LocalDateTime.now()));}

        productRepository.save(product);
    }

    public void deletebyid(long productid) {
        productRepository.deleteById(productid);
    }

    public boolean findId(long productId) {
        return productRepository.findById(productId).isPresent();
    }
}

