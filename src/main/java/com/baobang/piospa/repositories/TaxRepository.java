package com.baobang.piospa.repositories;
/**
  * @author BaoBang
  * @Created Jul 24, 2018
  * 
  */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.Tax;
@Repository
public interface TaxRepository extends JpaRepository<Tax, Integer>{

}
