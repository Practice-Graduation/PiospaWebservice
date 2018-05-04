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

import com.baobang.piospa.entities.ServiceType;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.ServiceTypeRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
  * @author BaoBang
  * @Created May 4, 2018
  * 
  */
@RestController
@RequestMapping(RequestPath.SERVICE_TYPE_PATH)
public class ServiceTypeController {
	@Autowired
	ServiceTypeRepository mServiceTypeRepository;

	/**
	 * @api {get} / Request Service Type information
	 * @apiName getAll
	 * @apiType Service
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list Service Type of the response
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Service Types")
	public DataResult<List<ServiceType>> getAll() {

		List<ServiceType> serviceTypes = mServiceTypeRepository.findAll();

		return new DataResult<List<ServiceType>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, serviceTypes);
	}

	/**
	 * @api {get} /{serviceTypeId} Request Service Type information
	 * @apiName getServiceTypeById
	 * @apiType Service
	 * 
	 * @apiParam {ServiceTypeId} id Service Type unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServiceType} the Service Type was got
	 * 
	 */
	@RequestMapping(//
			value = "/{serviceTypeId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get Service Type by id")
	public DataResult<ServiceType> getServiceTypeById(@PathVariable(value = "serviceTypeId") int serviceTypeId) {
		DataResult<ServiceType> result = new DataResult<>();
		Optional<ServiceType> option = mServiceTypeRepository.findById(serviceTypeId);
		ServiceType Type = option.get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(Type);
		return result;
	}

	/**
	 * @api {post} / Create a new Service Type
	 * @apiName createServiceType
	 * @apiType Service
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServiceType} the new Service Type was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new Service Type")
	public DataResult<ServiceType> CreateServiceType(@RequestBody ServiceType serviceType) {
		DataResult<ServiceType> result = new DataResult<>();

		ServiceType sType = mServiceTypeRepository.findByName(serviceType.getServiceTypeName());
		
		if(sType == null) {
			Date date = new Date();
			serviceType.setCreatedAt(date);
			serviceType.setUpdatedAt(date);
			sType = mServiceTypeRepository.save(serviceType);
			result.setMessage(MessageResponse.SUCCESSED);
			result.setStatusCode(HttpStatus.OK.value());
		}else {
			result.setMessage(MessageResponse.EXITS);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		result.setData(sType);
		return result;
	}

	/**
	 * @api {put}/{serviceTypeId} update Service Type by id
	 * @apiName updateServiceType
	 * @apiType Service
	 * 
	 * @apiParam {ServiceTypeId} id Service Type unique ID.
	 * @apiBody {ServiceType} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServiceType} the new Service Type was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{serviceTypeId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update Service Type by id")
	public DataResult<ServiceType> updateServiceType(@PathVariable(value = "serviceTypeId") int serviceTypeId,
			@RequestBody ServiceType serviceType) {
		DataResult<ServiceType> result;
		Optional<ServiceType> option = mServiceTypeRepository.findById(serviceTypeId);

		ServiceType Type = option.get();

		Type.setServiceTypeName(serviceType.getServiceTypeName());
		Type.setIsActive(serviceType.getIsActive());
		Type.setUpdatedAt(new Date());
		Type.setUpdatedBy(serviceType.getUpdatedBy());
		Type.setCreatedBy(serviceType.getCreatedBy());

		Type = mServiceTypeRepository.save(Type);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, Type);

		return result;
	}

	/**
	 * @api {delete}/{serviceTypeId} delete Service Type by id
	 * @apiName deleteServiceType
	 * @apiType Service
	 * 
	 * @apiParam {serviceTypeId} id Service Type unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServiceType} the Service Type was deleted
	 * 
	 */
	@RequestMapping(//
			value = "/{serviceTypeId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete Service Type by id")
	public DataResult<ServiceType> deleteServiceType(@PathVariable(value = "serviceTypeId") int serviceTypeId) {
		DataResult<ServiceType> dataResult = new DataResult<>();
		ServiceType serviceType = mServiceTypeRepository.findById(serviceTypeId).get();
		mServiceTypeRepository.deleteById(serviceTypeId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(serviceType);
		return dataResult;
	}
}
