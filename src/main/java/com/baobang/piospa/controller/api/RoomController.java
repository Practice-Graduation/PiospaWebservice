package com.baobang.piospa.controller.api;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baobang.piospa.entities.Booking;
import com.baobang.piospa.entities.BookingDetail;
import com.baobang.piospa.entities.Room;
import com.baobang.piospa.entities.ServicePrice;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.model.DateRequest;
import com.baobang.piospa.repositories.BookingDetailRepository;
import com.baobang.piospa.repositories.RoomRepository;
import com.baobang.piospa.utils.DateTimeUtils;
import com.baobang.piospa.utils.MessageResponse;

import io.swagger.annotations.ApiOperation;

/**
  * @author BaoBang
  * @Created Aug 16, 2018
  * 
  */
@RestController
@RequestMapping("/room")
public class RoomController {
	@Autowired
	BookingDetailRepository mBookingDetailRepository;
	@Autowired
	RoomRepository mRoomRepository;
	
	@RequestMapping(//
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all room active")
	public DataResult<List<Room>> getRoomCanBooking(@RequestBody DateRequest dateRequest) {
		
		List<Room> allRooms = mRoomRepository.findAll();

		Date dateBooking = DateTimeUtils.getDate(dateRequest.getDate(),  dateRequest.getTime());
		

		List<Room> rooms = new ArrayList<>();
		for(Room room : allRooms) {
			List<BookingDetail> bookingDetails = mBookingDetailRepository.findByDateAndRoom(room.getRoomId(), dateRequest.getDate());
			int count = 0;
			for(BookingDetail detail : bookingDetails) {
				Date start = DateTimeUtils.getDate(detail.getDateBooking(), detail.getTimeStart());
				
				Date end = new Date(start.getTime());
				int time = getTime(detail.getServicePrice());
				end = DateTimeUtils.addMinute(end, time);
				if(dateBooking.after(start) && dateBooking.before(end)) {
					count += detail.getNumber();
				}
			}
			if(count < room.getRoomLimit()) {
				room.setServing(count);
				rooms.add(room);
			}
		}
		
		return new DataResult<List<Room>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, rooms);

	}

	private int getTime(ServicePrice servicePrice) {
		int time = 0;
		
		if(servicePrice.getService() != null) {
			time = Integer.parseInt(servicePrice.getService().getServiceTime().getTime());
		}else {
			time = servicePrice.getServicePackage().getTime();
		}
		
		return time;
	}


}
