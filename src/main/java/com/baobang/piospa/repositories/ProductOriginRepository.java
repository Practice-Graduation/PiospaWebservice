package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.ProductOrigin;

/**
  * @author BaoBang
  * @Created Apr 18, 2018
  * 
  */
@Repository
public interface ProductOriginRepository extends JpaRepository<ProductOrigin, Integer> {
	
	@Query("select po from ProductOrigin po where po.productOriginName = :productOriginName")
	public ProductOrigin findByName(@Param("productOriginName") String productOriginName);

}
