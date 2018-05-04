package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.ServiceGroup;

/**
  * @author BaoBang
  * @Created May 4, 2018
  * 
  */
@Repository
public interface ServiceGroupRepository extends JpaRepository<ServiceGroup, Integer>{

	@Query("select sg from ServiceGroup sg where sg.serviceGroupName = :serviceGroupName")
	public ServiceGroup findByName(@Param("serviceGroupName")String serviceGroupName);
}
