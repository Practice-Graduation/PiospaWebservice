package com.baobang.piospa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.baobang.piospa.entities.BookingDetail;

/**
  * @author BaoBang
  * @Created May 13, 2018
  * 
  */
@Repository
public interface BookingDetailRepository extends JpaRepository<BookingDetail, Integer>{

	@Query(value = "SELECT * FROM booking_detail WHERE room_id = :roomId and date_booking = :dateBooking", nativeQuery = true)
	public List<BookingDetail> getBookingDetailByDateBooking(@Param("roomId") int roomId, 
			@Param("dateBooking")String dateBooking);
}
