package com.baobang.piospa.controller.api;
/**
  * @author BaoBang
  * @Created May 13, 2018
  * 
  */

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baobang.piospa.entities.Booking;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.BookingRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(RequestPath.BOOKING_PATH)
public class BookingController {

	@Autowired
	BookingRepository mBookingRepository;
	
	/**
	 * @api {get} / Request Booking information
	 * @apiName getAll
	 * @apiGroup Booking
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the Booking of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list Bookings
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Bookings")
	public DataResult<List<Booking>> getAll() {

		List<Booking> Bookings = mBookingRepository.findAll();

		return new DataResult<List<Booking>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, Bookings);
	}

	/**
	 * @api {get} /{bookingId} Request Booking information
	 * @apiName getBookingById
	 * @apiGroup Booking
	 * 
	 * @apiParam {BookingId} id Booking DeliveryStatus unique ID.
	 * 
	 * @apiSuccess {Integer} the Booking of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Booking} the Booking was got
	 * 
	 */
	@RequestMapping(//
			value = "/{bookingId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get Booking by id")
	public DataResult<Booking> getBookingưById(@PathVariable(value = "bookingId") int bookingId) {
		DataResult<Booking> result = new DataResult<>();
		Booking Booking = mBookingRepository.findById(bookingId).get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(Booking);
		return result;
	}

	/**
	 * @api {post} / Create a new Booking
	 * @apiName createBooking
	 * @apiGroup Booking
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the DeliveryStatus of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Booking} the new Booking DeliveryStatus was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new Booking")
	public DataResult<Booking> createBooking(@RequestBody Booking booking) {
		DataResult<Booking> result = new DataResult<>();

		booking = mBookingRepository.save(booking);
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(booking);
		return result;
	}

	/**
	 * @api {put}/{bookingId} update Booking by id
	 * @apiName updateBooking
	 * @apiGroup Booking
	 * 
	 * @apiParam {bookingId} id Booking unique ID.
	 * @apiBody {Booking} the info of Booking need to update
	 * 
	 * @apiSuccess {Integer} the DeliveryStatus of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Booking} the new Booking was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{bookingId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update Booking by id")
	public DataResult<Booking> updateBooking(
			@PathVariable(value = "bookingId") int bookingId,
			@RequestBody Booking booking) {
		DataResult<Booking> result;
		Optional<Booking> option = mBookingRepository.findById(bookingId);

		Booking newBooking = option.get();
		
		newBooking.setCustomer(booking.getCustomer());
		newBooking.setPrice(booking.getPrice());
		newBooking.setNumber(booking.getNumber());
		newBooking.setDiscount(booking.getDiscount());
		newBooking.setTotal(booking.getTotal());
		
		
		newBooking = mBookingRepository.save(newBooking);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, newBooking);

		return result;
	}
}
