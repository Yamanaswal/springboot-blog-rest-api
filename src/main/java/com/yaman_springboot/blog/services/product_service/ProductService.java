package com.yaman_springboot.blog.services.product_service;

import com.yaman_springboot.blog.models.jpa_models.Product;

import java.util.List;

public interface ProductService {

    List<Product> searchProducts(String query);

    Product createProduct(Product product);

}
