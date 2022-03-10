package com.products.product.services;

import com.products.product.models.ProductModel;
import com.products.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    //CREATE and UPDATE
    @Transactional
    public ProductModel save(ProductModel productModel){
        return productRepository.save(productModel);
    }
    //READ ALL
    public List<ProductModel> findAll(){
        return productRepository.findAll();
    }
    //READ BY ID
    public Optional<ProductModel> findById(UUID id){
        return productRepository.findById(id);
    }
    //DELETE
    @Transactional
    public void delete(ProductModel productModel){
        productRepository.delete(productModel);
    }
}
