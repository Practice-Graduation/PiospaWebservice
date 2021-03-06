package com.baobang.piospa.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.Service;

/**
  * @author BaoBang
  * @Created May 4, 2018
  * 
  */
@Repository
public interface ServiceRepository extends JpaRepository<Service, Integer>{
	
	
}
