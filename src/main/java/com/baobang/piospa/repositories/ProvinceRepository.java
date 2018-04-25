package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.Province;

/**
  * @author BaoBang
  * @Created Apr 20, 2018
  * 
  */
@Repository
public interface ProvinceRepository extends JpaRepository<Province, Integer>{
	@Query("select p from Province p where p.provinceid = :provinceid")
	public Province findById(@Param("provinceid")String provinceid);
}
