package com.baobang.piospa.controller.api;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baobang.piospa.entities.BookingDetail;
import com.baobang.piospa.model.BookingDetailRequest;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.BookingDetailRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
 * @author BaoBang
 * @Created May 13, 2018
 * 
 */
@RestController
@RequestMapping(RequestPath.BOOKING_DETAIL_PATH)
public class BookingDetailController {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	BookingDetailRepository mBookingDetailRepository;

	/**
	 * @api {get} / Request BookingDetail information
	 * @apiName getAll
	 * @apiGroup BookingDetail
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the BookingDetail of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list BookingDetails
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all BookingDetails")
	public DataResult<List<BookingDetail>> getAll() {

		List<BookingDetail> BookingDetails = mBookingDetailRepository.findAll();

		return new DataResult<List<BookingDetail>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, BookingDetails);
	}

	/**
	 * @api {post} /date Request BookingDetail information
	 * @apiName getBookingDetailByDateBooking
	 * @apiGroup BookingDetail
	 * 
	 * @apiBody {String} the date booking
	 * 
	 * @apiSuccess {Integer} the BookingDetail of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the BookingDetails was got
	 * 
	 */
	@RequestMapping(//
			value = "/date", //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get BookingDetail by date booking")
	public DataResult<List<BookingDetail>> getBookingDetailưById(@RequestBody BookingDetailRequest bookingDetailRequest) {
		
		DataResult<List<BookingDetail>> result = new DataResult<>();
		List<BookingDetail> details = mBookingDetailRepository.getBookingDetailByDateBooking(bookingDetailRequest.getDate());

		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(details);
		return result;
	}

	/**
	 * @api {get} /{BookingDetailId} Request BookingDetail information
	 * @apiName getBookingDetailById
	 * @apiGroup BookingDetail
	 * 
	 * @apiParam {BookingDetailId} id BookingDetail DeliveryStatus unique ID.
	 * 
	 * @apiSuccess {Integer} the BookingDetail of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {BookingDetail} the BookingDetail was got
	 * 
	 */
	@RequestMapping(//
			value = "/{BookingDetailId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get BookingDetail by id")
	public DataResult<BookingDetail> getBookingDetailưById(
			@PathVariable(value = "BookingDetailId") int BookingDetailId) {
		DataResult<BookingDetail> result = new DataResult<>();
		BookingDetail BookingDetail = mBookingDetailRepository.findById(BookingDetailId).get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(BookingDetail);
		return result;
	}

	/**
	 * @api {post} / Create a new BookingDetail
	 * @apiName createBookingDetail
	 * @apiGroup BookingDetail
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the DeliveryStatus of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {BookingDetail} the new BookingDetail DeliveryStatus was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new BookingDetail")
	public DataResult<BookingDetail> createBookingDetail(@RequestBody BookingDetail bookingDetail) {
		DataResult<BookingDetail> result = new DataResult<>();

		Date date = new Date();
		bookingDetail.setCreatedAt(date);
		bookingDetail.setUpdatedAt(date);

		bookingDetail = mBookingDetailRepository.save(bookingDetail);
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());

		result.setData(bookingDetail);
		return result;
	}

	/**
	 * @api {put}/{BookingDetailId} update BookingDetail by id
	 * @apiName updateBookingDetail
	 * @apiGroup BookingDetail
	 * 
	 * @apiParam {BookingDetailId} id BookingDetail unique ID.
	 * @apiBody {BookingDetail} the info of BookingDetail need to update
	 * 
	 * @apiSuccess {Integer} the DeliveryStatus of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {BookingDetail} the new BookingDetail was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{bookingDetailId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update BookingDetail by id")
	public DataResult<BookingDetail> updateBookingDetail(@PathVariable(value = "bookingDetailId") int bookingDetailId,
			@RequestBody BookingDetail bookingDetail) {
		DataResult<BookingDetail> result;
		Optional<BookingDetail> option = mBookingDetailRepository.findById(bookingDetailId);

		BookingDetail newBookingDetail = option.get();

		newBookingDetail.setBooking(bookingDetail.getBooking());
		newBookingDetail.setServicePrice(bookingDetail.getServicePrice());
		newBookingDetail.setRoom(bookingDetail.getRoom());
		newBookingDetail.setDateBooking(bookingDetail.getDateBooking());
		newBookingDetail.setTimeStart(bookingDetail.getTimeStart());
		newBookingDetail.setUpdatedAt(new Date());
		newBookingDetail.setUpdatedBy(bookingDetail.getUpdatedBy());

		newBookingDetail = mBookingDetailRepository.save(newBookingDetail);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, newBookingDetail);

		return result;
	}

	/**
	 * @api {delete}/{bookingDetailId} delete Booking Detail by id
	 * @apiName deleteBooking
	 * @apiGroup Product
	 * 
	 * @apiParam {bookingDetailId} id Booking Detail unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Booking} the Booking Detail was deleted
	 * 
	 * 
	 */
	@RequestMapping(//
			value = "/{bookingDetailId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete Booking Detail by id")
	public DataResult<BookingDetail> deleteBooking(@PathVariable(value = "bookingDetailId") int bookingDetailId) {
		DataResult<BookingDetail> dataResult = new DataResult<>();
		BookingDetail bookingDetail = mBookingDetailRepository.findById(bookingDetailId).get();
		mBookingDetailRepository.deleteById(bookingDetailId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(bookingDetail);
		return dataResult;
	}

}
