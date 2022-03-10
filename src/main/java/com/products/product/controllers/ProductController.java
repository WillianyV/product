package com.products.product.controllers;

import com.products.product.dtos.ProductDto;
import com.products.product.models.ProductModel;
import com.products.product.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    //CREATE
    @PostMapping
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductDto productDto){
        var productModel = new ProductModel();

        BeanUtils.copyProperties(productDto,productModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(productModel));
    }
    //END CREATE

    //READ
    @GetMapping
    public ResponseEntity<List<ProductModel>> findAllProducts(){

        return ResponseEntity.status(HttpStatus.OK).body(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findByIdProduct(@PathVariable(value = "id") UUID id){

        Optional<ProductModel> productModelOptional = productService.findById(id);

        if (!productModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(productModelOptional.get());
    }
    //END READ

    //UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value = "id") UUID id,
                                         @RequestBody @Valid ProductDto productDto){

        Optional<ProductModel> productModelOptional = productService.findById(id);

        if (!productModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        var productModel = new ProductModel();

        BeanUtils.copyProperties(productDto,productModel);

        productModel.setId(productModelOptional.get().getId());

        return ResponseEntity.status(HttpStatus.OK).body(productService.save(productModel));
    }
    //END UPDATE

    //DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") UUID id){

        Optional<ProductModel> productModelOptional = productService.findById(id);

        if (!productModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
        }

        productService.delete(productModelOptional.get());

        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }
    //END DELETE
}
