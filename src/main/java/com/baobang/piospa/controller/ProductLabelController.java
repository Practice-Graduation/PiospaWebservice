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

import com.baobang.piospa.entities.ProductLabel;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.ProductLabelRepository;
import com.baobang.piospa.utils.MessageResponese;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
 * @author BaoBang
 * @Created Apr 18, 2018
 * 
 */
@RestController
@RequestMapping(RequestPath.PRODUCT_LABEL_PATH)
public class ProductLabelController {

	@Autowired
	ProductLabelRepository mLabelRepository;

	/**
	 * @api {get} / Request Product Label information
	 * @apiName getAll
	 * @apiGroup Product
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {array} the list product label of the responese
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK  {
	 * 					 "data": [ {
	 *                   	 "createdAt": "2018-04-18T08:08:02.828Z",
	 *                       "createdBy": 0,
	 *                    	 "isActive": 0,
	 *                    	 "productLabelCode": "string",
	 *                    	 "productLabelDescription": "string", 
	 *                       "productLabelId": 0,
	 *                   	 "productLabelName": "string", 
	 *                       "updatedAt": "2018-04-18T08:08:02.828Z", "updatedBy": 0
	 *                    } ],
	 *               	  "message": "string",
	 *                    "statusCode": 0 
	 *                }
	 * @apiError
	 *
	 * @apiErrorExample Error-Response: HTTP/1.1 404 Not Found
	 * 					 { 
	 * 						 "statusCode" : 404,
	 *                 		 "message" : "Not Found",
	 *                 		 "data" : [] 
	 *                 	 }
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all product labels")
	public DataResult<List<ProductLabel>> getAll() {

		List<ProductLabel> productLabels = mLabelRepository.findAll();

		return new DataResult<List<ProductLabel>>(HttpStatus.OK.value(), MessageResponese.SUCCESSED, productLabels);
	}

	/**
	 * @api {get} /{productLabelId} Request Product Label information
	 * @apiName getProductLabelById
	 * @apiGroup Product
	 * 
	 * @apiParam {productLabelId} id Product Label unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {User} the Product Label was got
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK  {
	 * 					 "data": [ {
	 *                   	 "createdAt": "2018-04-18T08:08:02.828Z",
	 *                   	 "createdBy": 0,
	 *                    	 "isActive": 0,
	 *                    	 "productLabelCode": "string",
	 *                    	 "productLabelDescription": "string",
	 *                    	 "productLabelId": 0,
	 *                   	 "productLabelName": "string",
	 *                   	 "updatedAt":
	 *                   	 "2018-04-18T08:08:02.828Z",
	 *                   	 "updatedBy": 0
	 *                    } ],
	 *               	  "message": "string",
	 *                    "statusCode": 0 
	 *                }
	 * @apiError
	 *
	 * @apiErrorExample Error-Response: HTTP/1.1 404 Not Found
	 * 			 { 
	 * 					"statusCode" : 404,
	 *                  "message" : "Not Found",
	 *                   "data" :{}
	 *           }
	 */
	@RequestMapping(//
			value = "/{productLabelId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get product label by id")
	public DataResult<ProductLabel> getProductLabelById(@PathVariable(value = "productLabelId") int productLabelId){
		DataResult<ProductLabel> result = new DataResult<>();
		Optional<ProductLabel> option = mLabelRepository.findById(productLabelId);
		ProductLabel label = option.get();
		result.setMessage(MessageResponese.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(label);
		return result;
	}
	
	/**
	 * @api {post} / Create a new Product Label 
	 * @apiName createProductLabel
	 * @apiGroup Product
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {ProductLabel} the new Product Label was created
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK  {
	 * 					 "data": [ {
	 *                   	 "createdAt": "2018-04-18T08:08:02.828Z",
	 *                   	 "createdBy": 0,
	 *                    	 "isActive": 0,
	 *                    	 "productLabelCode": "string",
	 *                    	 "productLabelDescription": "string",
	 *                    	 "productLabelId": 0,
	 *                   	 "productLabelName": "string",
	 *                   	 "updatedAt":
	 *                   	 "2018-04-18T08:08:02.828Z",
	 *                   	 "updatedBy": 0
	 *                    } ],
	 *               	  "message": "string",
	 *                    "statusCode": 0 
	 *                }
	 * @apiError
	 *
	 * @apiErrorExample Error-Response: HTTP/1.1 404 Not Found
	 * 			 { 
	 * 					"statusCode" : 404,
	 *                  "message" : "Product Laebl was exited",
	 *                   "data" :{}
	 *           }
	 */
	@RequestMapping(//
			value = {"", "/"}, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new product label")
	public DataResult<ProductLabel> CreateProductLabel(@RequestBody ProductLabel productLabel){
		DataResult<ProductLabel> result = new DataResult<>();
		ProductLabel label = mLabelRepository.findByCode(productLabel.getProductLabelCode());
		
		if(label == null) {
			
			Date date = new Date();
			productLabel.setProductLabelId(0);
			productLabel.setCreatedAt(date);
			productLabel.setUpdatedAt(date);
			label = mLabelRepository.save(productLabel);
			

			result.setMessage(MessageResponese.SUCCESSED);
			result.setStatusCode(HttpStatus.OK.value());
		}else {
			result.setMessage(MessageResponese.EXITS);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		
		result.setData(label);
		return result;
	}
	
	/**
	 * @api {put}/{productLabelId} update  Product Label by id 
	 * @apiName updateProductLabel
	 * @apiGroup Product
	 * 
	 * @apiParam {productLabelId} id Product Label unique ID.
	 * @apiBody {productLabel} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {ProductLabel} the new Product Label was updated
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK  {
	 * 					 "data": [ {
	 *                   	 "createdAt": "2018-04-18T08:08:02.828Z",
	 *                   	 "createdBy": 0,
	 *                    	 "isActive": 0,
	 *                    	 "productLabelCode": "string",
	 *                    	 "productLabelDescription": "string",
	 *                    	 "productLabelId": 0,
	 *                   	 "productLabelName": "string",
	 *                   	 "updatedAt":
	 *                   	 "2018-04-18T08:08:02.828Z",
	 *                   	 "updatedBy": 0
	 *                    } ],
	 *               	  "message": "string",
	 *                    "statusCode": 0 
	 *                }
	 * @apiError
	 *
	 * @apiErrorExample Error-Response: HTTP/1.1 404 Not Found
	 * 			 { 
	 * 					"statusCode" : 404,
	 *                  "message" : "Product Label was exited",
	 *                   "data" :{}
	 *           }
	 */
	@RequestMapping(//
			value = "/{productLabelId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Update product label by id")
	public DataResult<ProductLabel> updateProductLabel(@PathVariable(value = "productLabelId") int productLabelId, @RequestBody ProductLabel productLabel) {
		DataResult<ProductLabel> result;
		Optional<ProductLabel> option = mLabelRepository.findById(productLabelId);

		ProductLabel newProductLabel = option.get();
		
		newProductLabel.setIsActive(productLabel.getIsActive());
		newProductLabel.setProductLabelDescription(productLabel.getProductLabelDescription());
		newProductLabel.setProductLabelName(productLabel.getProductLabelName());
		newProductLabel.setUpdatedBy(productLabel.getUpdatedBy());
		newProductLabel.setUpdatedAt(new Date());

		newProductLabel = mLabelRepository.save(newProductLabel);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponese.SUCCESSED, newProductLabel);

		return result;
	}

	/**
	 * @api {delete}/{productLabelId} delete  Product Label by id 
	 * @apiName deleteProductLabel
	 * @apiGroup Product
	 * 
	 * @apiParam {productLabelId} id Product Label unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {ProductLabel} the  Product Label was deleted
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK  {
	 * 					 "data": [ {
	 *                   	 "createdAt": "2018-04-18T08:08:02.828Z",
	 *                   	 "createdBy": 0,
	 *                    	 "isActive": 0,
	 *                    	 "productLabelCode": "string",
	 *                    	 "productLabelDescription": "string",
	 *                    	 "productLabelId": 0,
	 *                   	 "productLabelName": "string",
	 *                   	 "updatedAt":
	 *                   	 "2018-04-18T08:08:02.828Z",
	 *                   	 "updatedBy": 0
	 *                    } ],
	 *               	  "message": "string",
	 *                    "statusCode": 0 
	 *                }
	 * @apiError
	 *
	 * @apiErrorExample Error-Response: HTTP/1.1 404 Not Found
	 * 			 { 
	 * 					"statusCode" : 404,
	 *                  "message" : "Product Label was not found",
	 *                   "data" :{}
	 *           }
	 */
	@RequestMapping(//
			value = "/{productLabelId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Delete product label by id")
	public DataResult<ProductLabel> deleteProductLabel(@PathVariable(value = "productLabelId") int productLabelId) {
		DataResult<ProductLabel> dataResult = new DataResult<>();
		ProductLabel productLabel = mLabelRepository.findById(productLabelId).get();
		mLabelRepository.deleteById(productLabelId);
		dataResult.setMessage(MessageResponese.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(productLabel);
		return dataResult;
	}
}
