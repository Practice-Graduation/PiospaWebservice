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

import com.baobang.piospa.entities.ProductUnit;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.ProductUnitRepository;
import com.baobang.piospa.utils.MessageResponese;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
 * @author BaoBang
 * @Created Apr 18, 2018
 * 
 */
@RestController
@RequestMapping(RequestPath.PRODUCT_UNIT_PATH)
public class ProductUnitController {
	@Autowired
	ProductUnitRepository mUnitRepository;

	/**
	 * @api {get} / Request Product Unit information
	 * @apiName getAll
	 * @apiUnit Product
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {array} the list product unit of the responese
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK { "data": [ {
	 *                    "createdAt": "2018-04-18T09:28:51.022Z", "createdBy": 0,
	 *                    "isActive": 0, "productUnitCode": "string",
	 *                    "productUnitDescription": "string", "productUnitId": 0,
	 *                    "productUnitName": "string", "updatedAt":
	 *                    "2018-04-18T09:28:51.022Z", "updatedBy": 0 } ], "message":
	 *                    "string", "statusCode": 0 }
	 * @apiError
	 *
	 * @apiErrorExample Error-Response: HTTP/1.1 404 Not Found { "statusCode" : 404,
	 *                  "message" : "null", "data" : null }
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all product units")
	public DataResult<List<ProductUnit>> getAll() {

		List<ProductUnit> productUnits = mUnitRepository.findAll();

		return new DataResult<List<ProductUnit>>(HttpStatus.OK.value(), MessageResponese.SUCCESSED, productUnits);
	}

	/**
	 * @api {get} /{productUnitId} Request Product Unit information
	 * @apiName getProductUnitById
	 * @apiUnit Product
	 * 
	 * @apiParam {productUnitId} id Product Unit unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {Productunit} the Product Unit was got
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK { "data": [ {
	 *                    "createdAt": "2018-04-18T08:08:02.828Z", "createdBy": 0,
	 *                    "isActive": 0, "productUnitCode": "string",
	 *                    "productUnitDescription": "string", "productUnitId": 0,
	 *                    "productUnitName": "string", "updatedAt":
	 *                    "2018-04-18T08:08:02.828Z", "updatedBy": 0 } ], "message":
	 *                    "string", "statusCode": 0 }
	 * @apiError
	 *
	 * @apiErrorExample Error-Response: HTTP/1.1 404 Not Found { "statusCode" : 404,
	 *                  "message" : "Not Found", "data" :{} }
	 */
	@RequestMapping(//
			value = "/{productUnitId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get product Unit by id")
	public DataResult<ProductUnit> getProductUnitById(@PathVariable(value = "productUnitId") int productUnitId) {
		DataResult<ProductUnit> result = new DataResult<>();
		Optional<ProductUnit> option = mUnitRepository.findById(productUnitId);
		ProductUnit Unit = option.get();
		result.setMessage(MessageResponese.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(Unit);
		return result;
	}

	/**
	 * @api {post} / Create a new Product Unit
	 * @apiName createProductUnit
	 * @apiUnit Product
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {ProductUnit} the new Product Unit was created
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK { "statusCode": 200,
	 *                    "message": "Success!", "data": { "productUnitId": 1,
	 *                    "createdAt": "2018-04-18T09:26:21.686+0000", "createdBy":
	 *                    1, "isActive": 0, "productUnitCode": "123",
	 *                    "productUnitDescription": "This is a Product Unit
	 *                    description", "productUnitName": "Unit 1", "updatedAt":
	 *                    "2018-04-18T09:26:21.686+0000", "updatedBy": 1 } }
	 * @apiError
	 *
	 * @apiErrorExample Error-Response: HTTP/1.1 404 Not Found { "statusCode" : 404,
	 *                  "message" : "Product Laebl was exited", "data" :{} }
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new product Unit")
	public DataResult<ProductUnit> CreateProductUnit(@RequestBody ProductUnit productUnit) {
	
		DataResult<ProductUnit> result = new DataResult<>();

		productUnit.setProductUnitId(0);
		Date date = new Date();
		productUnit.setCreatedAt(date);
		productUnit.setUpdatedAt(date);
		productUnit = mUnitRepository.save(productUnit);

		result.setMessage(MessageResponese.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(productUnit);
		
		return result;
	}

	/**
	 * @api {put}/{productUnitId} update Product Unit by id
	 * @apiName updateProductUnit
	 * @apiUnit Product
	 * 
	 * @apiParam {productUnitId} id Product Unit unique ID.
	 * @apiBody {productUnit} the info of Product Unit need to update
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {ProductUnit} the new Product Unit was updated
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK { "statusCode": 200,
	 *                    "message": "Success!", "data": { "productUnitId": 1,
	 *                    "createdAt": "2018-04-18T09:26:21.686+0000", "createdBy":
	 *                    1, "isActive": 0, "productUnitCode": "123",
	 *                    "productUnitDescription": "This is a Product Unit
	 *                    description", "productUnitName": "Unit 1", "updatedAt":
	 *                    "2018-04-18T09:26:21.686+0000", "updatedBy": 1 } }
	 * @apiError
	 *
	 * @apiErrorExample Error-Response: HTTP/1.1 404 Not Found { "statusCode" : 404,
	 *                  "message" : "Product Unit was exited", "data" :{} }
	 */
	@RequestMapping(//
			value = "/{productUnitId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update product Unit by id")
	public DataResult<ProductUnit> updateProductUnit(@PathVariable(value = "productUnitId") int productUnitId,
			@RequestBody ProductUnit productUnit) {
		DataResult<ProductUnit> result;
		Optional<ProductUnit> option = mUnitRepository.findById(productUnitId);

		ProductUnit Unit = option.get();

		Unit.setProductUnitName(productUnit.getProductUnitName());
		Unit.setProductUnitDescription(productUnit.getProductUnitDescription());
		Unit.setIsActive(productUnit.getIsActive());
		Unit.setUpdatedAt(new Date());
		Unit.setUpdatedBy(productUnit.getUpdatedBy());
		Unit.setCreatedBy(productUnit.getCreatedBy());

		Unit = mUnitRepository.save(Unit);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponese.SUCCESSED, Unit);

		return result;
	}

	/**
	 * @api {delete}/{productUnitId} delete Product Unit by id
	 * @apiName deleteProductUnit
	 * @apiUnit Product
	 * 
	 * @apiParam {productUnitId} id Product Unit unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {ProductUnit} the Product Unit was deleted
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK { "data": [ {
	 *                    "createdAt": "2018-04-18T08:08:02.828Z", "createdBy": 0,
	 *                    "isActive": 0, "productUnitCode": "string",
	 *                    "productUnitDescription": "string", "productUnitId": 0,
	 *                    "productUnitName": "string", "updatedAt":
	 *                    "2018-04-18T08:08:02.828Z", "updatedBy": 0 } ], "message":
	 *                    "string", "statusCode": 0 }
	 * @apiError
	 *
	 * @apiErrorExample Error-Response: HTTP/1.1 404 Not Found { "statusCode" : 404,
	 *                  "message" : "Product Unit was not found", "data" :{} }
	 */
	@RequestMapping(//
			value = "/{productUnitId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete product Unit by id")
	public DataResult<ProductUnit> deleteProductUnit(@PathVariable(value = "productUnitId") int productUnitId) {
		DataResult<ProductUnit> dataResult = new DataResult<>();
		ProductUnit productUnit = mUnitRepository.findById(productUnitId).get();
		mUnitRepository.deleteById(productUnitId);
		dataResult.setMessage(MessageResponese.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(productUnit);
		return dataResult;
	}
}
