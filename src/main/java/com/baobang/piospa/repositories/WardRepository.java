package com.baobang.piospa.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.Ward;

/**
  * @author BaoBang
  * @Created Apr 20, 2018
  * 
  */
@Repository
public interface WardRepository extends JpaRepository<Ward, Integer>{

	@Query("select w from Ward w where w.wardid = :wardid")
	public Ward findById(@Param("wardid")String wardId);
	
}
