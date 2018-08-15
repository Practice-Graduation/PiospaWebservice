package com.baobang.piospa.repositories;

import java.util.List;

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
	
	
	@Query("select p from Product p where p.productGroup.productGroupId = :groupId and p.isActive = 1")
	public List<Product> findByGroupId(@Param("groupId") int groupId);
	
	@Query(value="SELECT * FROM products WHERE product_group_id = ?1 and is_active = 1 LIMIT 10", nativeQuery = true)
	public List<Product> findTopTenByGroupId(String groupId);

	
}
