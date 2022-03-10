package com.products.product.repositories;

import com.products.product.models.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProductRepository extends JpaRepository<ProductModel, UUID> {

}
