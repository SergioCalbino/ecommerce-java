package com.example.demo.product;

import com.example.demo.category.CategoryRepository;
import com.example.demo.entities.Category;
import com.example.demo.entities.Dto.ProductDto;
import com.example.demo.entities.Dto.ProductMapper;
import com.example.demo.entities.Dto.ProductResponseDto;
import com.example.demo.entities.Product;
import com.example.demo.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



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
    public Page<ProductResponseDto> findAll(Pageable pageable) {
       Page<Product> productPage = productRepository.findAll(pageable);
       return productPage.map(product -> ProductMapper.toDto(product));
    }

    @Transactional(readOnly = true)
    @Override
    public ProductResponseDto findById(Long id) {

       Product product  = productRepository.findById(id)
               .orElseThrow(() -> new NotFoundException("Product with id " + id + "not found"));

       ProductResponseDto productResponseDto = ProductMapper.toDto(product);
       return productResponseDto;


    }

    @Transactional
    @Override
    public ProductResponseDto save(ProductDto productDto)  {
        Category categoryOptional = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Categoty with id " + productDto.getCategoryId() + "not found"));

        //Guardo lo que me llega del body
       Product product = ProductMapper.fromDto(productDto, categoryOptional);
       Product productSaved =  productRepository.save(product);
       return ProductMapper.toDto(productSaved);





    }

    @Override
    public ProductResponseDto update(Long id, ProductDto productDto) {
        Product productDb = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        Category categoryDb = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));

        productDb.setName(productDto.getName());
        productDb.setImage(productDto.getImage());
        productDb.setPrice(productDto.getPrice());
        productDb.setStock(productDto.getStock());
        productDb.setCategory(categoryDb);
        productDb.setDescription(productDto.getDescription());

        Product productToUpdate = productRepository.save(productDb);

        return ProductMapper.toDto(productToUpdate);

    }

    @Override
    public ProductResponseDto delete(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id " + id + "not found"));

        ProductResponseDto productDeleted = ProductMapper.toDto(product);
        productRepository.delete(product);
        return productDeleted;
    }
}
