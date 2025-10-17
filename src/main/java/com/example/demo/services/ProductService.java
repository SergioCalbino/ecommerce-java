package com.example.demo.services;

import com.example.demo.Dto.product.ProductUpdateDto;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.entities.Category;
import com.example.demo.Dto.product.ProductDto;
import com.example.demo.mappers.ProductMapper;
import com.example.demo.Dto.product.ProductResponseDto;
import com.example.demo.entities.Product;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductService implements com.example.demo.interfaces.ProductService {


    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }


    //Este es para los admin porque trae absolutamente todo
    @Transactional(readOnly = true)
    @Override
    public Page<ProductResponseDto> findAll(String name, Pageable pageable) {
        Page<Product> productPage;
        if (name != null && !name.trim().isEmpty()) {
            productPage = productRepository.findByNameContainingIgnoreCase(name, pageable);


        } else {
            productPage = productRepository.findAll(pageable);

        }

        return productPage.map(product -> ProductMapper.toDto(product));
    }

    //Este es el findAll para los clientes
    @Override
    public Page<ProductResponseDto> findAllActive(String name, Pageable pageable) {
        Page<Product> productPage;
        if (name != null && !name.trim().isEmpty()) {
            productPage = productRepository.findByNameContainingIgnoreCaseAndIsActiveTrue(name, pageable);
        } else {
            productPage = productRepository.findByIsActiveTrue(pageable);
        }

        return productPage.map(ProductMapper::toDto);
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
       product.setIsActive(true);
       Product productSaved =  productRepository.save(product);
       return ProductMapper.toDto(productSaved);

    }

    @Override
    @Transactional
    public ProductResponseDto update(Long id, ProductUpdateDto productDto) {
        Product productDb = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        Category categoryDb = categoryRepository.findById(productDto.getCategoryId())
                .orElseThrow(() -> new NotFoundException("Category not found"));

       // boolean nameExists = productRepository.existsByNameIgnoreCase(productDto.getName());
        boolean nameExists = productRepository.existsByNameIgnoreCaseAndIdNot(productDto.getName(), id);
        System.out.println("Quiero ver que me imprime el nameexisti" + nameExists);
        //boolean sameName = productDb.getName().equalsIgnoreCase(productDto.getName());

        if (nameExists) {
            throw new IllegalArgumentException("Product name already exists");
        }

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
    @Transactional
    public void delete(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product with id " + id + "not found"));

       // ProductResponseDto productDeleted = ProductMapper.toDto(product);
       // productRepository.delete(product);
        product.setIsActive(false);
        productRepository.save(product);
        //return productDeleted;
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponseDto findByName(String name) {
        Product productSearch = productRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new NotFoundException("Product not found"));

        return ProductMapper.toDto(productSearch);

    }

    @Override
    public void reActivate(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Product not fond"));
        product.setIsActive(true);

        productRepository.save(product);
    }

    //Hacer el service que ractive los productos para el admin

}
