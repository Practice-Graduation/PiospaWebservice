package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.ProductUnit;

/**
  * @author BaoBang
  * @Created Apr 18, 2018
  * 
  */
@Repository
public interface ProductUnitRepository extends JpaRepository<ProductUnit, Integer> {

}
