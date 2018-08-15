package com.baobang.piospa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.baobang.piospa.entities.Booking;

/**
  * @author BaoBang
  * @Created May 13, 2018
  * 
  */
public interface BookingRepository extends JpaRepository<Booking, Integer>{

}
