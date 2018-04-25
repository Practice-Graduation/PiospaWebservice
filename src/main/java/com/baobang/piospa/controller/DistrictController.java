package com.baobang.piospa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baobang.piospa.entities.District;
import com.baobang.piospa.entities.Ward;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.DistrictRepository;
import com.baobang.piospa.utils.MessageResponese;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
  * @author BaoBang
  * @Created Apr 20, 2018
  * 
  */
@RestController
@RequestMapping(RequestPath.DISTRICT_PATH)
public class DistrictController {
	@Autowired
	DistrictRepository mDistrictRepository;

	/**
	 * @api {get} / Request District information
	 * @apiName getAll
	 * @apiGroup District
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {array} the list District
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Districts")
	public DataResult<List<District>> getAll() {

		List<District> districts = mDistrictRepository.findAll();

		return new DataResult<List<District>>(HttpStatus.OK.value(), MessageResponese.SUCCESSED, districts);
	}

	/**
	 * @api {get} /{districtId} Request district information
	 * @apiName getDistrictById
	 * @apiOrigin district
	 * 
	 * @apiParam {districtId} id district unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {District} the district was got
	 * 
	 */
	@RequestMapping(//
			value = "/{districtId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get district by id")
	public DataResult<District> getDistrictById(@PathVariable(value = "districtId") int districtId) {
		DataResult<District> result = new DataResult<>();
		District district = mDistrictRepository.findById(districtId).get();
		if (district == null) {
			result.setMessage(MessageResponese.NOT_FOUND);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		} else {
			result.setMessage(MessageResponese.SUCCESSED);
			result.setStatusCode(HttpStatus.OK.value());
		}

		result.setData(district);
		return result;
	}
	
	/**
	 * @api {get} /{districtId}/ward Request district information
	 * @apiName getDistrictById
	 * @apiOrigin district
	 * 
	 * @apiParam {districtId} id district unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {array[District]} the district was got
	 * 
	 */
	@RequestMapping(//
			value = "/{districtId}/ward", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get ward of district by id")
	public DataResult<List<Ward>> getWardOfDistrictById(@PathVariable(value = "districtId") int districtId) {
		DataResult<List<Ward>> result = new DataResult<>();
		District district = mDistrictRepository.findById(districtId).get();
		List<Ward> wards = null;
		if (district == null) {
			result.setMessage(MessageResponese.NOT_FOUND);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		} else {
			wards = district.getWards();
			result.setMessage(MessageResponese.SUCCESSED);
			result.setStatusCode(HttpStatus.OK.value());
		}

		result.setData(wards);
		return result;
	}
}
