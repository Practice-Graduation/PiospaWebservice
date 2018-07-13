package com.baobang.piospa.controller.api;

import java.util.Date;
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

import com.baobang.piospa.entities.ServiceTime;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.ServiceTimeRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
  * @author BaoBang
  * @Created May 4, 2018
  * 
  */
@RestController
@RequestMapping(RequestPath.SERVICE_TIME_PATH)
public class ServiceTimeController {
	@Autowired
	ServiceTimeRepository mServiceTimeRepository;

	/**
	 * @api {get} / Request Service Time information
	 * @apiName getAll
	 * @apiTime Service
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list Service Time of the response
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Service Times")
	public DataResult<List<ServiceTime>> getAll() {

		List<ServiceTime> serviceTimes = mServiceTimeRepository.findAll();

		return new DataResult<List<ServiceTime>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, serviceTimes);
	}

	/**
	 *  
	 * @api {get} /{serviceTimeId} Request Service Time information
	 * @apiName getServiceTimeById
	 * @apiGroup Service
	 * 
	 * @param {ServiceTimeId} id Service Time unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServiceTime} the Service Time was got
	 * 
	 */
	@RequestMapping(//
			value = "/{serviceTimeId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get Service Time by id")
	public DataResult<ServiceTime> getServiceTimeById(@PathVariable(value = "serviceTimeId") int serviceTimeId) {
		DataResult<ServiceTime> result = new DataResult<>();
		Optional<ServiceTime> option = mServiceTimeRepository.findById(serviceTimeId);
		ServiceTime serviceTime = option.get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(serviceTime);
		return result;
	}

	/**
	 * @api {post} / Create a new Service Time
	 * @apiName createServiceTime
	 * @apiTime Service
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServiceTime} the new Service Time was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new Service Time")
	public DataResult<ServiceTime> CreateServiceTime(@RequestBody ServiceTime serviceTime) {
		DataResult<ServiceTime> result = new DataResult<>();

		Date date = new Date();
		serviceTime.setCreatedAt(date);
		serviceTime.setUpdatedAt(date);
		serviceTime = mServiceTimeRepository.save(serviceTime);

		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());

		result.setData(serviceTime);
		return result;
	}

	/**
	 * @api {put}/{serviceTimeId} update Service Time by id
	 * @apiName updateServiceTime
	 * @apiTime Service
	 * 
	 * @apiParam {ServiceTimeId} id Service Time unique ID.
	 * @apiBody {ServiceTime} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServiceTime} the new Service Time was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{serviceTimeId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update Service Time by id")
	public DataResult<ServiceTime> updateServiceTime(@PathVariable(value = "serviceTimeId") int serviceTimeId,
			@RequestBody ServiceTime serviceTime) {
		DataResult<ServiceTime> result;
		Optional<ServiceTime> option = mServiceTimeRepository.findById(serviceTimeId);
		ServiceTime time = option.get();

		time.setTime(serviceTime.getTime());
		time.setIsActive(serviceTime.getIsActive());
		time.setUpdatedAt(new Date());
		time.setUpdatedBy(serviceTime.getUpdatedBy());
		time.setCreatedBy(serviceTime.getCreatedBy());

		time = mServiceTimeRepository.save(time);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, time);

		return result;
	}

	/**
	 * @api {delete}/{serviceTimeId} delete Service Time by id
	 * @apiName deleteServiceTime
	 * @apiTime Service
	 * 
	 * @apiParam {serviceTimeId} id Service Time unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServiceTime} the Service Time was deleted
	 * 
	 */
	@RequestMapping(//
			value = "/{serviceTimeId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete Service Time by id")
	public DataResult<ServiceTime> deleteServiceTime(@PathVariable(value = "serviceTimeId") int serviceTimeId) {
		DataResult<ServiceTime> dataResult = new DataResult<>();
		ServiceTime serviceTime = mServiceTimeRepository.findById(serviceTimeId).get();
		mServiceTimeRepository.deleteById(serviceTimeId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(serviceTime);
		return dataResult;
	}
}
