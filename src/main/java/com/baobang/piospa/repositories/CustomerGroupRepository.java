package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.CustomerGroup;

/**
  * @author BaoBang
  * @Created Apr 24, 2018
  * 
  */
@Repository
public interface CustomerGroupRepository extends JpaRepository<CustomerGroup, Integer>{
	@Query("select cg from CustomerGroup cg where cg.customerGroupCode = :customerGroupCode")
	public CustomerGroup findByCode(@Param("customerGroupCode") String customerGroupCode);
}
