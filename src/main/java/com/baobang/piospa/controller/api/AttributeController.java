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

import com.baobang.piospa.entities.Attribute;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.AttributeRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
 * @author BaoBang
 * @Created Apr 24, 2018
 * 
 */
@RestController
@RequestMapping(RequestPath.ATTRIBUTE_PATH)
public class AttributeController {
	@Autowired

	AttributeRepository mAttributeRepository;

	/**
	 * @api {get} / Request Product attribute information
	 * @apiName getAll
	 * @apiGroup Product
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list product attribute of the response
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all product attributes")
	public DataResult<List<Attribute>> getAll() {
		
		List<Attribute> Attributes = mAttributeRepository.findAll();

		return new DataResult<List<Attribute>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, Attributes);
	}

	/**
	 * @api {get} /{attributeId} Request Product attribute information
	 * @apiName getAttributeById
	 * @apiGroup Product
	 * 
	 * @apiParam {attributeId} id Product attribute unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Attribute} the Product attribute was got
	 * 
	 */
	@RequestMapping(//
			value = "/{attributeId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get product attribute by id")
	public DataResult<Attribute> getAttributeById(@PathVariable(value = "attributeId") int attributeId) {
		DataResult<Attribute> result = new DataResult<>();
		Optional<Attribute> option = mAttributeRepository.findById(attributeId);
		Attribute attribute = option.get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(attribute);
		return result;
	}

	/**
	 * @api {post} / Create a new Product attribute
	 * @apiName createAttribute
	 * @apiGroup Product
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Attribute} the new Product attribute was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new product attribute")
	public DataResult<Attribute> CreateAttribute(@RequestBody Attribute attribute) {
		DataResult<Attribute> result = new DataResult<>();

		Date date = new Date();
		attribute.setAttributeId(0);
		attribute.setCreatedAt(date);
		attribute.setUpdatedAt(date);
		attribute = mAttributeRepository.save(attribute);
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(attribute);
		return result;
	}

	/**
	 * @api {put}/{attributeId} update Product attribute by id
	 * @apiName updateAttribute
	 * @apiGroup Product
	 * 
	 * @apiParam {attributeId} id Product attribute unique ID.
	 * @apiBody {Attribute} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Attribute} the new Product attribute was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{attributeId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update product attribute by id")
	public DataResult<Attribute> updateAttribute(@PathVariable(value = "attributeId") int attributeId,
			@RequestBody Attribute attribute) {
		DataResult<Attribute> result;
		Optional<Attribute> option = mAttributeRepository.findById(attributeId);

		Attribute newAttribute = option.get();

		newAttribute.setIsActive(attribute.getIsActive());
		newAttribute.setAttributeName(attribute.getAttributeName());
		newAttribute.setUpdatedBy(attribute.getUpdatedBy());
		newAttribute.setUpdatedAt(new Date());

		newAttribute = mAttributeRepository.save(newAttribute);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, newAttribute);

		return result;
	}

	/**
	 * @api {delete}/{attributeId} delete Product attribute by id
	 * @apiName deleteAttribute
	 * @apiGroup Product
	 * 
	 * @apiParam {attributeId} id Product attribute unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Attribute} the Product attribute was deleted
	 * 
	 * 
	 */
	@RequestMapping(//
			value = "/{attributeId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete product attribute by id")
	public DataResult<Attribute> deleteAttribute(@PathVariable(value = "attributeId") int attributeId) {
		DataResult<Attribute> dataResult = new DataResult<>();
		Attribute attribute = mAttributeRepository.findById(attributeId).get();
		mAttributeRepository.deleteById(attributeId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(attribute);
		return dataResult;
	}

}
