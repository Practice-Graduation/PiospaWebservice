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
import com.baobang.piospa.entities.Province;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.ProvinceRepository;
import com.baobang.piospa.utils.MessageResponese;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
  * @author BaoBang
  * @Created Apr 20, 2018
  * 
  */

@RestController
@RequestMapping(RequestPath.PROVINCE_PATH)
public class ProvinceController {
	@Autowired
	ProvinceRepository mProvinceRepository;

	/**
	 * @api {get} / Request Province information
	 * @apiName getAll
	 * @apiGroup Province
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {array} the list Province
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Provinces")
	public DataResult<List<Province>> getAll() {

		List<Province> provinces = mProvinceRepository.findAll();

		return new DataResult<List<Province>>(HttpStatus.OK.value(), MessageResponese.SUCCESSED, provinces);
	}

	/**
	 * @api {get} /{ProvinceId} Request Province information
	 * @apiName getProvinceById
	 * @apiOrigin Province
	 * 
	 * @apiParam {ProvinceId} id Province unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {ProductOrigin} the Province was got
	 * 
	 */
	@RequestMapping(//
			value = "/{ProvinceId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get Province by id")
	public DataResult<Province> getProvinceById(@PathVariable(value = "ProvinceId") int ProvinceId) {
		DataResult<Province> result = new DataResult<>();
		Province province = mProvinceRepository.findById(ProvinceId).get();
		if (province == null) {
			result.setMessage(MessageResponese.NOT_FOUND);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		} else {
			result.setMessage(MessageResponese.SUCCESSED);
			result.setStatusCode(HttpStatus.OK.value());
		}

		result.setData(province);
		return result;
	}
	
	/**
	 * @api {get} /{ProvinceId} Request Province information
	 * @apiName getProvinceById
	 * @apiOrigin Province
	 * 
	 * @apiParam {ProvinceId} id Province unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {ProductOrigin} the Province was got
	 * 
	 */
	@RequestMapping(//
			value = "/{ProvinceId}/district", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get districts of Province by id")
	public DataResult<List<District>> getDistrictOfProvinceById(@PathVariable(value = "ProvinceId") int ProvinceId) {
		DataResult<List<District>> result = new DataResult<>();
		List<District> districts = null;
		Province province = mProvinceRepository.findById(ProvinceId).get();
		if (province == null) {
			result.setMessage(MessageResponese.NOT_FOUND);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		} else {
			districts = province.getDistricts();
			result.setMessage(MessageResponese.SUCCESSED);
			result.setStatusCode(HttpStatus.OK.value());
		}

		result.setData(districts);
		return result;
	}
}
