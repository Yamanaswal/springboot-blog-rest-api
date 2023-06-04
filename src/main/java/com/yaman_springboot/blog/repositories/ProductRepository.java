package com.yaman_springboot.blog.repositories;

import com.yaman_springboot.blog.models.jpa_models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    //JPQL QUERY
    @Query(value = "SELECT p FROM Product p WHERE " +
            "p.name LIKE CONCAT('%', :query ,'%')" +
            "p.description LIKE CONCAT('%', :query ,'%')", nativeQuery = true)
    List<Product> searchProducts(String query);


    //Native Sql Query
    @Query(value = "SELECT * FROM Product p WHERE " +
            "p.name LIKE CONCAT('%', :query ,'%')" +
            "p.description LIKE CONCAT('%', :query ,'%')", nativeQuery = true)
    List<Product> searchProductsSQL(String query);


}
