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

import com.baobang.piospa.entities.ServicePackage;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.ServicePackageRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
  * @author BaoBang
  * @Created May 4, 2018
  * 
  */
@RestController
@RequestMapping(RequestPath.SERVICE_PACKAGE_PATH)
public class ServicePackageController {
	@Autowired
	ServicePackageRepository mServicePackageRepository;

	/**
	 * @api {get} / Request Service Package information
	 * @apiName getAll
	 * @apiPackage Service
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list Service Package of the response
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Service Packages")
	public DataResult<List<ServicePackage>> getAll() {

		List<ServicePackage> servicePackages = mServicePackageRepository.findAll();

		return new DataResult<List<ServicePackage>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, servicePackages);
	}

	/**
	 *  
	 * @api {get} /{servicePackageId} Request Service Package information
	 * @apiName getServicePackageById
	 * @apiPackage Service
	 * 
	 * @param {ServicePackageId} id Service Package unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServicePackage} the Service Package was got
	 * 
	 */
	@RequestMapping(//
			value = "/{servicePackageId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get Service Package by id")
	public DataResult<ServicePackage> getServicePackageById(@PathVariable(value = "servicePackageId") int servicePackageId) {
		DataResult<ServicePackage> result = new DataResult<>();
		Optional<ServicePackage> option = mServicePackageRepository.findById(servicePackageId);
		ServicePackage servicePackage = option.get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(servicePackage);
		return result;
	}

	/**
	 * @api {post} / Create a new Service Package
	 * @apiName createServicePackage
	 * @apiPackage Service
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServicePackage} the new Service Package was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new Service Package")
	public DataResult<ServicePackage> CreateServicePackage(@RequestBody ServicePackage servicePackage) {
		DataResult<ServicePackage> result = new DataResult<>();

		ServicePackage sPackage = mServicePackageRepository.findByName(servicePackage.getServicePackageName());
		
		if(sPackage == null) {
			Date date = new Date();
			servicePackage.setCreatedAt(date);
			servicePackage.setUpdatedAt(date);
			sPackage = mServicePackageRepository.save(servicePackage);
			result.setMessage(MessageResponse.SUCCESSED);
			result.setStatusCode(HttpStatus.OK.value());
		}else {
			result.setMessage(MessageResponse.EXITS);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		result.setData(sPackage);
		return result;
	}

	/**
	 * @api {put}/{servicePackageId} update Service Package by id
	 * @apiName updateServicePackage
	 * @apiPackage Service
	 * 
	 * @apiParam {ServicePackageId} id Service Package unique ID.
	 * @apiBody {ServicePackage} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServicePackage} the new Service Package was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{servicePackageId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update Service Package by id")
	public DataResult<ServicePackage> updateServicePackage(@PathVariable(value = "servicePackageId") int servicePackageId,
			@RequestBody ServicePackage servicePackage) {
		DataResult<ServicePackage> result;
		Optional<ServicePackage> option = mServicePackageRepository.findById(servicePackageId);

		ServicePackage Package = option.get();

		Package.setServicePackageName(servicePackage.getServicePackageName());
		Package.setIsActive(servicePackage.getIsActive());
		Package.setUpdatedAt(new Date());
		Package.setUpdatedBy(servicePackage.getUpdatedBy());
		Package.setCreatedBy(servicePackage.getCreatedBy());

		Package = mServicePackageRepository.save(Package);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, Package);

		return result;
	}

	/**
	 * @api {delete}/{servicePackageId} delete Service Package by id
	 * @apiName deleteServicePackage
	 * @apiPackage Service
	 * 
	 * @apiParam {servicePackageId} id Service Package unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ServicePackage} the Service Package was deleted
	 * 
	 */
	@RequestMapping(//
			value = "/{servicePackageId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete Service Package by id")
	public DataResult<ServicePackage> deleteServicePackage(@PathVariable(value = "servicePackageId") int servicePackageId) {
		DataResult<ServicePackage> dataResult = new DataResult<>();
		ServicePackage servicePackage = mServicePackageRepository.findById(servicePackageId).get();
		mServicePackageRepository.deleteById(servicePackageId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(servicePackage);
		return dataResult;
	}

}
