package com.baobang.piospa.controller;

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

import com.baobang.piospa.entities.Service;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.ServiceRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
 * @author BaoBang
 * @Created May 4, 2018
 * 
 */
@RestController
@RequestMapping(RequestPath.SERVICE_PATH)
public class ServiceController {
	@Autowired
	ServiceRepository mServiceRepository;

	/**
	 * @api {get} / Request Service information
	 * @apiName getAll
	 * @apiGroup Service
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list Service of the response
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Service s")
	public DataResult<List<Service>> getAll() {

		List<Service> services = mServiceRepository.findAll();

		return new DataResult<List<Service>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, services);
	}

	/**
	 * @api {get} /{serviceId} Request Service information
	 * @apiName getServiceById
	 * @apiGroup Service
	 * 
	 * @apiParam {ServiceId} id Service unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Service} the Service was got
	 * 
	 */
	@RequestMapping(//
			value = "/{serviceId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get Service  by id")
	public DataResult<Service> getServiceById(@PathVariable(value = "serviceId") int serviceId) {
		DataResult<Service> result = new DataResult<>();
		Optional<Service> option = mServiceRepository.findById(serviceId);
		Service service = option.get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(service);
		return result;
	}

	/**
	 * @api {post} / Create a new Service
	 * @apiName createService
	 * @apiGroup Service
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Service} the new Service was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new Service ")
	public DataResult<Service> CreateService(@RequestBody Service service) {
		DataResult<Service> result = new DataResult<>();

		Service s = mServiceRepository.findByCode(service.getServiceCode());

		if (s == null) {
			Date date = new Date();
			service.setCreatedAt(date);
			service.setUpdatedAt(date);
			s = mServiceRepository.save(service);
			result.setMessage(MessageResponse.SUCCESSED);
			result.setStatusCode(HttpStatus.OK.value());
		} else {
			result.setMessage(MessageResponse.EXITS);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		result.setData(s);
		return result;
	}

	/**
	 * @api {put}/{serviceId} update Service by id
	 * @apiName updateService
	 * @apiGroup Service
	 * 
	 * @apiParam {ServiceId} id Service unique ID.
	 * @apiBody {Service} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Service} the new Service was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{serviceId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update Service  by id")
	public DataResult<Service> updateService(@PathVariable(value = "serviceId") int serviceId,
			@RequestBody Service service) {
		DataResult<Service> result;
		Optional<Service> option = mServiceRepository.findById(serviceId);

		Service temp = option.get();

		temp.setServiceName(service.getServiceName());
		temp.setServiceTime(service.getServiceTime());
		temp.setDescription(service.getDescription());
		temp.setImage(service.getImage());
		temp.setDetail(service.getDetail());
		temp.setIsActive(service.getIsActive());
		temp.setUpdatedAt(new Date());
		temp.setUpdatedBy(service.getUpdatedBy());
		temp.setCreatedBy(service.getCreatedBy());

		temp = mServiceRepository.save(temp);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, temp);

		return result;
	}

	/**
	 * @api {delete}/{serviceId} delete Service by id
	 * @apiName deleteService
	 * @apiGroup Service
	 * 
	 * @apiParam {serviceId} id Service unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Service} the Service was deleted
	 * 
	 */
	@RequestMapping(//
			value = "/{serviceId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete Service  by id")
	public DataResult<Service> deleteService(@PathVariable(value = "serviceId") int serviceId) {
		DataResult<Service> dataResult = new DataResult<>();
		Service service = mServiceRepository.findById(serviceId).get();
		mServiceRepository.deleteById(serviceId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(service);
		return dataResult;
	}
}
