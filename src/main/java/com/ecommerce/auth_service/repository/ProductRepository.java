package com.ecommerce.auth_service.repository;

import com.ecommerce.auth_service.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}