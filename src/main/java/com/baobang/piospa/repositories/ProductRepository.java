package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.Product;

/**
  * @author BaoBang
  * @Created Apr 18, 2018
  * 
  */
@Repository
public interface ProductRepository  extends JpaRepository<Product, Integer> {
	
	@Query("select p from Product p where p.productCode = :productCode")
	public Product findByCode(@Param("productCode") String productCode);
}
