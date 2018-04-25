package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.Attribute;

/**
  * @author BaoBang
  * @Created Apr 24, 2018
  * 
  */
@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Integer>{

}
