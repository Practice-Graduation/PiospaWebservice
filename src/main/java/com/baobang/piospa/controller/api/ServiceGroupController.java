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

import com.baobang.piospa.entities.ServiceGroup;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.ServiceGroupRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
 * @author BaoBang
 * @Created May 4, 2018
 * 
 */
@RestController
@RequestMapping(RequestPath.SERVICE_GROUP_PATH)
public class ServiceGroupController {

	@Autowired
	ServiceGroupRepository mServiceGroupRepository;

	/**
	 * @api {get} / Request Service Group information
	 * @apiName getAll
	 * @apiGroup Service
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list Service group of the response
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Service groups")
	public DataResult<List<ServiceGroup>> getAll() {

		List<ServiceGroup> serviceGroups = mServiceGroupRepository.findAll();

		return new DataResult<List<ServiceGroup>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, serviceGroups);
	}

	/**
	 * @api {get} /{serviceGroupId} Request Service Group information
	 * @apiName getServiceGroupById
	 * @apiGroup Service
	 * 
	 * @apiParam {ServiceGroupId} id Service Group unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServiceGroup} the Service Group was got
	 * 
	 */
	@RequestMapping(//
			value = "/{serviceGroupId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get Service group by id")
	public DataResult<ServiceGroup> getServiceGroupById(@PathVariable(value = "serviceGroupId") int serviceGroupId) {
		DataResult<ServiceGroup> result = new DataResult<>();
		Optional<ServiceGroup> option = mServiceGroupRepository.findById(serviceGroupId);
		ServiceGroup group = option.get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(group);
		return result;
	}

	/**
	 * @api {post} / Create a new Service Group
	 * @apiName createServiceGroup
	 * @apiGroup Service
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServiceGroup} the new Service Group was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new Service group")
	public DataResult<ServiceGroup> createServiceGroup(@RequestBody ServiceGroup serviceGroup) {
		DataResult<ServiceGroup> result = new DataResult<>();

		ServiceGroup sGroup = mServiceGroupRepository.findByName(serviceGroup.getServiceGroupName());
		
		if(sGroup == null) {
			sGroup = mServiceGroupRepository.save(serviceGroup);
			result.setMessage(MessageResponse.SUCCESSED);
			result.setStatusCode(HttpStatus.OK.value());
		}else {
			result.setMessage(MessageResponse.EXITS);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		result.setData(sGroup);
		return result;
	}

	/**
	 * @api {put}/{serviceGroupId} update Service Group by id
	 * @apiName updateServiceGroup
	 * @apiGroup Service
	 * 
	 * @apiParam {ServiceGroupId} id Service Group unique ID.
	 * @apiBody {ServiceGroup} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServiceGroup} the new Service Group was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{serviceGroupId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update Service group by id")
	public DataResult<ServiceGroup> updateServiceGroup(@PathVariable(value = "serviceGroupId") int serviceGroupId,
			@RequestBody ServiceGroup serviceGroup) {
		DataResult<ServiceGroup> result;
		Optional<ServiceGroup> option = mServiceGroupRepository.findById(serviceGroupId);

		ServiceGroup group = option.get();

		group.setServiceGroupName(serviceGroup.getServiceGroupName());

		group = mServiceGroupRepository.save(group);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, group);

		return result;
	}

	/**
	 * @api {delete}/{serviceGroupId} delete Service Group by id
	 * @apiName deleteServiceGroup
	 * @apiGroup Service
	 * 
	 * @apiParam {serviceGroupId} id Service Group unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServiceGroup} the Service Group was deleted
	 * 
	 */
	@RequestMapping(//
			value = "/{serviceGroupId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete Service group by id")
	public DataResult<ServiceGroup> deleteServiceGroup(@PathVariable(value = "serviceGroupId") int serviceGroupId) {
		DataResult<ServiceGroup> dataResult = new DataResult<>();
		ServiceGroup serviceGroup = mServiceGroupRepository.findById(serviceGroupId).get();
		mServiceGroupRepository.deleteById(serviceGroupId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(serviceGroup);
		return dataResult;
	}

}
