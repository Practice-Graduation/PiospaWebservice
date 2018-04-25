package com.baobang.piospa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baobang.piospa.entities.Ward;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.WardRepository;
import com.baobang.piospa.utils.MessageResponese;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
 * @author BaoBang
 * @Created Apr 20, 2018
 * 
 */
@RestController
@RequestMapping(RequestPath.WARD_PATH)
public class WardController{
	
	

	@Autowired
	WardRepository mWardRepository;

	/**
	 * @api {get} / Request Ward information
	 * @apiName getAll
	 * @apiGroup ward
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {array} the list ward
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all wards")
	public DataResult<List<Ward>> getAll() {

		List<Ward> wards = mWardRepository.findAll();

		return new DataResult<List<Ward>>(HttpStatus.OK.value(), MessageResponese.SUCCESSED, wards);
	}

	/**
	 * @api {get} /{wardId} Request ward information
	 * @apiName getWardById
	 * @apiOrigin ward
	 * 
	 * @apiParam {wardId} id Ward unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {ProductOrigin} the Ward was got
	 * 
	 */
	@RequestMapping(//
			value = "/{wardId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get Ward by id")
	public DataResult<Ward> getWardById(@PathVariable(value = "wardId") int wardId) {
		DataResult<Ward> result = new DataResult<>();
		Ward ward = mWardRepository.findById(wardId).get();
		if (ward == null) {
			result.setMessage(MessageResponese.NOT_FOUND);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		} else {
			result.setMessage(MessageResponese.SUCCESSED);
			result.setStatusCode(HttpStatus.OK.value());
		}

		result.setData(ward);
		return result;
	}
	
	

}
