package com.example.demo.product;

import com.example.demo.category.CategoryRepository;
import com.example.demo.entities.Category;
import com.example.demo.entities.Dto.ProductDto;
import com.example.demo.entities.Product;
import com.example.demo.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return (List<Product>) productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public Product save(ProductDto productDto)  {
        Category categoryOptional = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Categoty with id " + productDto.getCategoryId() + "not found"));

        Product product = new Product();
        product.setName(productDto.getName());
        product.setImage(productDto.getImage());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        product.setDescription(productDto.getDescription());
        product.setCategory(categoryOptional);

        return productRepository.save(product);

    }

    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> productOptional = productRepository.findById(id);
        if (productOptional.isPresent()) {
            Product productDb = productOptional.orElseThrow();

            productDb.setName(product.getName());
            productDb.setPrice(product.getPrice());
            productDb.setStock(product.getStock());
            productDb.setDescription(product.getDescription());
            productDb.setImage(product.getImage());
        return Optional.of(productRepository.save(productDb));
        }
        return productOptional;
    }

    @Override
    public Optional<Product> delete(Long id) {

        Optional<Product> product = productRepository.findById(id);
        product.ifPresent(value -> productRepository.delete(value));
        return product;
    }
}
