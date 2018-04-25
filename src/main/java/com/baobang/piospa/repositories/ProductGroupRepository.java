package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.ProductGroup;

/**
  * @author BaoBang
  * @Created Apr 18, 2018
  * 
  */
@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroup, Integer>{
	@Query("select pg from ProductGroup pg where pg.productGroupCode = :productGroupCode")
	public ProductGroup findByCode(@Param("productGroupCode") String productGroupCode);
}