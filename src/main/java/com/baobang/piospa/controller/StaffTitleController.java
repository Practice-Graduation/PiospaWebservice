package com.baobang.piospa.controller;
/**
  * @author BaoBang
  * @Created Apr 26, 2018
  * 
  */

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

import com.baobang.piospa.entities.StaffTitle;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.StaffTitleRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(RequestPath.STAFF_TITLE_PATH)
public class StaffTitleController {

	@Autowired
	StaffTitleRepository mStaffTitleRepository;
	
	/**
	 * @api {get} / Request Product StaffTitle information
	 * @apiName getAll
	 * @apiGroup Staff
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list product StaffTitle of the response
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all product StaffTitles")
	public DataResult<List<StaffTitle>> getAll() {

		List<StaffTitle> StaffTitles = mStaffTitleRepository.findAll();

		return new DataResult<List<StaffTitle>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, StaffTitles);
	}

	/**
	 * @api {get} /{staffTitleId} Request StaffTitle information
	 * @apiName getStaffTitleById
	 * @apiGroup Staff
	 * 
	 * @apiParam {staffTitleId} id StaffTitle unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {StaffTitle} the Product StaffTitle was got
	 * 
	 */
	@RequestMapping(//
			value = "/{staffTitleId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get product StaffTitle by id")
	public DataResult<StaffTitle> getStaffTitleById(@PathVariable(value = "staffTitleId") int staffTitleId){
		DataResult<StaffTitle> result = new DataResult<>();
		Optional<StaffTitle> option = mStaffTitleRepository.findById(staffTitleId);
		StaffTitle StaffTitle = option.get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(StaffTitle);
		return result;
	}
	
	/**
	 * @api {post} / Create a new  StaffTitle 
	 * @apiName createStaffTitle
	 * @apiGroup Staff
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {StaffTitle} the new StaffTitle was created
	 * 
	 */
	@RequestMapping(//
			value = {"", "/"}, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new StaffTitle")
	public DataResult<StaffTitle> CreateStaffTitle(@RequestBody StaffTitle staffTitle){
		DataResult<StaffTitle> result = new DataResult<>();
		StaffTitle temp = mStaffTitleRepository.findByCode(staffTitle.getStaffTitleCode());
		
		if(temp == null) {
			
			Date date = new Date();
			staffTitle.setStaffTitleId(0);
			staffTitle.setCreatedAt(date);
			staffTitle.setUpdatedAt(date);
			temp = mStaffTitleRepository.save(staffTitle);
			

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
	 * @api {put}/{staffTitleId} update StaffTitle by id 
	 * @apiName updateStaffTitle
	 * @apiGroup Staff
	 * 
	 * @apiParam {staffTitleId} id Product StaffTitle unique ID.
	 * @apiBody {StaffTitle} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {StaffTitle} the new StaffTitle was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{staffTitleId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Update product StaffTitle by id")
	public DataResult<StaffTitle> updateStaffTitle(@PathVariable(value = "staffTitleId") int staffTitleId, @RequestBody StaffTitle staffTitle) {
		DataResult<StaffTitle> result;
		Optional<StaffTitle> option = mStaffTitleRepository.findById(staffTitleId);

		StaffTitle newStaffTitle = option.get();
		
		newStaffTitle.setIsActive(staffTitle.getIsActive());
		newStaffTitle.setStaffTitleDescription(staffTitle.getStaffTitleDescription());
		newStaffTitle.setStaffTitleName(staffTitle.getStaffTitleName());
		newStaffTitle.setUpdatedBy(staffTitle.getUpdatedBy());
		newStaffTitle.setUpdatedAt(new Date());

		newStaffTitle = mStaffTitleRepository.save(newStaffTitle);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, newStaffTitle);

		return result;
	}

	/**
	 * @api {delete}/{staffTitleId} delete StaffTitle by id 
	 * @apiName deleteStaffTitle
	 * @apiGroup Staff
	 * 
	 * @apiParam {staffTitleId} id StaffTitle unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {StaffTitle} the  Product StaffTitle was deleted
	 * 
	 */
	@RequestMapping(//
			value = "/{staffTitleId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Delete staffTitle by id")
	public DataResult<StaffTitle> deleteStaffTitle(@PathVariable(value = "staffTitleId") int staffTitleId) {
		DataResult<StaffTitle> dataResult = new DataResult<>();
		StaffTitle staffTitle = mStaffTitleRepository.findById(staffTitleId).get();
		mStaffTitleRepository.deleteById(staffTitleId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(staffTitle);
		return dataResult;
	}
}
