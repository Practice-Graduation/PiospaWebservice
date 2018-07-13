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

import com.baobang.piospa.entities.StaffDepartment;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.StaffDepartmentRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
  * @author BaoBang
  * @Created Apr 26, 2018
  * 
  */
@RestController
@RequestMapping(RequestPath.STAFF_DEPARTMENT_PATH)
public class StaffDepartmentController {
	@Autowired
	StaffDepartmentRepository mStaffDepartmentRepository;
	
	/**
	 * @api {get} / Request Product StaffDepartment information
	 * @apiName getAll
	 * @apiGroup Staff
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list product StaffDepartment of the response
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all product StaffDepartments")
	public DataResult<List<StaffDepartment>> getAll() {

		List<StaffDepartment> StaffDepartments = mStaffDepartmentRepository.findAll();

		return new DataResult<List<StaffDepartment>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, StaffDepartments);
	}

	/**
	 * @api {get} /{staffDepartmentId} Request StaffDepartment information
	 * @apiName getStaffDepartmentById
	 * @apiGroup Staff
	 * 
	 * @apiParam {staffDepartmentId} id StaffDepartment unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {StaffDepartment} the Product StaffDepartment was got
	 * 
	 */
	@RequestMapping(//
			value = "/{staffDepartmentId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get product StaffDepartment by id")
	public DataResult<StaffDepartment> getStaffDepartmentById(@PathVariable(value = "staffDepartmentId") int staffDepartmentId){
		DataResult<StaffDepartment> result = new DataResult<>();
		Optional<StaffDepartment> option = mStaffDepartmentRepository.findById(staffDepartmentId);
		StaffDepartment StaffDepartment = option.get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(StaffDepartment);
		return result;
	}
	
	/**
	 * @api {post} / Create a new  StaffDepartment 
	 * @apiName createStaffDepartment
	 * @apiGroup Staff
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {StaffDepartment} the new StaffDepartment was created
	 * 
	 */
	@RequestMapping(//
			value = {"", "/"}, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new StaffDepartment")
	public DataResult<StaffDepartment> CreateStaffDepartment(@RequestBody StaffDepartment staffDepartment){
		DataResult<StaffDepartment> result = new DataResult<>();
		StaffDepartment temp = mStaffDepartmentRepository.findByCode(staffDepartment.getStaffDepartmentCode());
		
		if(temp == null) {
			
			Date date = new Date();
			staffDepartment.setStaffDepartmentId(0);
			staffDepartment.setCreatedAt(date);
			staffDepartment.setUpdatedAt(date);
			temp = mStaffDepartmentRepository.save(staffDepartment);
			

			result.setMessage(MessageResponse.SUCCESSED);
			result.setStatusCode(HttpStatus.OK.value());
		}else {
			result.setMessage(MessageResponse.EXITS);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		
		result.setData(temp);
		return result;
	}
	
	/**
	 * @api {put}/{staffDepartmentId} update StaffDepartment by id 
	 * @apiName updateStaffDepartment
	 * @apiGroup Staff
	 * 
	 * @apiParam {staffDepartmentId} id Product StaffDepartment unique ID.
	 * @apiBody {StaffDepartment} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {StaffDepartment} the new StaffDepartment was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{staffDepartmentId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Update product StaffDepartment by id")
	public DataResult<StaffDepartment> updateStaffDepartment(@PathVariable(value = "staffDepartmentId") int staffDepartmentId, @RequestBody StaffDepartment staffDepartment) {
		DataResult<StaffDepartment> result;
		Optional<StaffDepartment> option = mStaffDepartmentRepository.findById(staffDepartmentId);

		StaffDepartment newStaffDepartment = option.get();
		
		newStaffDepartment.setIsActive(staffDepartment.getIsActive());
		newStaffDepartment.setStaffDepartmentDescription(staffDepartment.getStaffDepartmentDescription());
		newStaffDepartment.setStaffDepartmentName(staffDepartment.getStaffDepartmentName());
		newStaffDepartment.setUpdatedBy(staffDepartment.getUpdatedBy());
		newStaffDepartment.setUpdatedAt(new Date());

		newStaffDepartment = mStaffDepartmentRepository.save(newStaffDepartment);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, newStaffDepartment);

		return result;
	}

	/**
	 * @api {delete}/{staffDepartmentId} delete StaffDepartment by id 
	 * @apiName deleteStaffDepartment
	 * @apiGroup Staff
	 * 
	 * @apiParam {staffDepartmentId} id StaffDepartment unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {StaffDepartment} the  Product StaffDepartment was deleted
	 * 
	 */
	@RequestMapping(//
			value = "/{staffDepartmentId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Delete staffDepartment by id")
	public DataResult<StaffDepartment> deleteStaffDepartment(@PathVariable(value = "staffDepartmentId") int staffDepartmentId) {
		DataResult<StaffDepartment> dataResult = new DataResult<>();
		StaffDepartment staffDepartment = mStaffDepartmentRepository.findById(staffDepartmentId).get();
		mStaffDepartmentRepository.deleteById(staffDepartmentId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(staffDepartment);
		return dataResult;
	}
}
