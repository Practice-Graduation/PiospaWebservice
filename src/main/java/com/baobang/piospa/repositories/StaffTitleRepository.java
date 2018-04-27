package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.StaffTitle;

/**
  * @author BaoBang
  * @Created Apr 26, 2018
  * 
  */
@Repository
public interface StaffTitleRepository extends JpaRepository<StaffTitle, Integer>{
	@Query("select st from StaffTitle st where st.staffTitleCode = :staffTitleCode")
	public StaffTitle findByCode(@Param("staffTitleCode") String staffTitleCode);
}
