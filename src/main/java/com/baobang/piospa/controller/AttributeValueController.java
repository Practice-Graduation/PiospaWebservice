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

import com.baobang.piospa.entities.AttributeValue;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.AttributeValueRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
  * @author BaoBang
  * @Created Apr 24, 2018
  * 
  */
@RestController
@RequestMapping(RequestPath.ATTRIBUTE_VALUE_PATH)
public class AttributeValueController {
	@Autowired

	AttributeValueRepository mAttributeValueRepository;

	/**
	 * @api {get} / Request Product attribute Value information
	 * @apiName getAll
	 * @apiGroup Product
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list product attribute Value of the response
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all product attributeValues")
	public DataResult<List<AttributeValue>> getAll() {

		List<AttributeValue> AttributeValues = mAttributeValueRepository.findAll();

		return new DataResult<List<AttributeValue>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, AttributeValues);
	}

	/**
	 * @api {get} /{attributeValueId} Request Product attributeValue information
	 * @apiName getAttributeValueById
	 * @apiGroup Product
	 * 
	 * @apiParam {attributeValueId} id Product attributeValue unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {AttributeValue} the Product attribute Value was got
	 * 
	 */
	@RequestMapping(//
			value = "/{attributeValueId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get product attributeValue by id")
	public DataResult<AttributeValue> getAttributeValueById(@PathVariable(value = "attributeValueId") int attributeValueId) {
		DataResult<AttributeValue> result = new DataResult<>();
		Optional<AttributeValue> option = mAttributeValueRepository.findById(attributeValueId);
		AttributeValue attributeValue = option.get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(attributeValue);
		return result;
	}

	/**
	 * @api {post} / Create a new Product attributeValue
	 * @apiName createAttributeValue
	 * @apiGroup Product
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {AttributeValue} the new Product attributeValue was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new product attributeValue")
	public DataResult<AttributeValue> CreateAttributeValue(@RequestBody AttributeValue attributeValue) {
		DataResult<AttributeValue> result = new DataResult<>();

		Date date = new Date();
		attributeValue.setAttributeValueId(0);
		attributeValue.setCreatedAt(date);
		attributeValue.setUpdatedAt(date);
		attributeValue = mAttributeValueRepository.save(attributeValue);
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(attributeValue);
		return result;
	}

	/**
	 * @api {put}/{AttributeValueId} update Product attributeValue by id
	 * @apiName updateAttributeValue
	 * @apiGroup Product
	 * 
	 * @apiParam {AttributeValueId} id Product attributeValue unique ID.
	 * @apiBody {AttributeValue} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {AttributeValue} the new Product attributeValue was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{attributeValueId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update product attributeValue by id")
	public DataResult<AttributeValue> updateAttributeValue(@PathVariable(value = "attributeValueId") int attributeValueId,
			@RequestBody AttributeValue attributeValue) {
		DataResult<AttributeValue> result;
		Optional<AttributeValue> option = mAttributeValueRepository.findById(attributeValueId);

		AttributeValue newAttributeValue = option.get();

		newAttributeValue.setAttribute(attributeValue.getAttribute());
		newAttributeValue.setIsActive(attributeValue.getIsActive());
		newAttributeValue.setAttributeValueName(attributeValue.getAttributeValueName());
		newAttributeValue.setUpdatedBy(attributeValue.getUpdatedBy());
		newAttributeValue.setUpdatedAt(new Date());

		newAttributeValue = mAttributeValueRepository.save(newAttributeValue);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, newAttributeValue);

		return result;
	}

	/**
	 * @api {delete}/{AttributeValueId} delete Product attributeValue by id
	 * @apiName deleteAttributeValue
	 * @apiGroup Product
	 * 
	 * @apiParam {AttributeValueId} id Product attributeValue unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {AttributeValue} the Product attributeValue was deleted
	 * 
	 * 
	 */
	@RequestMapping(//
			value = "/{attributeValueId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete product attributeValue by id")
	public DataResult<AttributeValue> deleteAttributeValue(@PathVariable(value = "attributeValueId") int attributeValueId) {
		DataResult<AttributeValue> dataResult = new DataResult<>();
		AttributeValue attributeValue = mAttributeValueRepository.findById(attributeValueId).get();
		mAttributeValueRepository.deleteById(attributeValueId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(attributeValue);
		return dataResult;
	}

}
