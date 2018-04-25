package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.ProductAttribute;

/**
  * @author BaoBang
  * @Created Apr 25, 2018
  * 
  */
@Repository
public interface ProductAttributeRepository extends JpaRepository<ProductAttribute, Integer> {

}
