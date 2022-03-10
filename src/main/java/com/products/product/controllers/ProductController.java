package com.products.product.controllers;

import com.products.product.models.ProductModel;
import com.products.product.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    //CREATE
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody ProductModel productModel){
        return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
    }
    //END CREATE

    //READ
    @GetMapping
    public ResponseEntity<List<ProductModel>> getAll(){

        return ResponseEntity.status(HttpStatus.OK).body(productRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable(value = "id") UUID id){
        Optional<ProductModel> productModelOptional = productRepository.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(productModelOptional.get());
    }
    //END READ

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable(value = "id") UUID id,
                                         @RequestBody ProductModel productModel){
        Optional<ProductModel> productModelOptional = productRepository.findById(id);
        productModel.setId(productModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
    }
    //END UPDATE

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id") UUID id){
        Optional<ProductModel> productModelOptional = productRepository.findById(id);

        productRepository.delete(productModelOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }
    //END DELETE
}
