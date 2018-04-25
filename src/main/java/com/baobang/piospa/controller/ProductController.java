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

import com.baobang.piospa.entities.Product;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.ProductRepository;
import com.baobang.piospa.utils.MessageResponese;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
  * @author BaoBang
  * @Created Apr 18, 2018
  * 
  */
@RestController
@RequestMapping(RequestPath.PRODUCT_PATH)
public class ProductController {
	@Autowired
	ProductRepository mProductRepository;
	
	/**
	 * @api {get} / Request Product  information
	 * @apiName getAll
	 * @api Product
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {array} the list product  of the responese
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK { "data": [ {
	 *                    "createdAt": "2018-04-18T09:28:51.022Z", "createdBy": 0,
	 *                    "isActive": 0, "productCode": "string",
	 *                    "productDescription": "string", "productId": 0,
	 *                    "productName": "string", "updatedAt":
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
	@ApiOperation(value = "Get all products")
	public DataResult<List<Product>> getAll() {
		
		List<Product> products = mProductRepository.findAll();

		return new DataResult<List<Product>>(HttpStatus.OK.value(), MessageResponese.SUCCESSED, products);
	}

	/**
	 * @api {get} /{productId} Request Product  information
	 * @apiName getProductById
	 * @api Product
	 * 
	 * @apiParam {productId} id Product  unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {Product} the Product  was got
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK { "data": [ {
	 *                    "createdAt": "2018-04-18T08:08:02.828Z", "createdBy": 0,
	 *                    "isActive": 0, "productCode": "string",
	 *                    "productDescription": "string", "productId": 0,
	 *                    "productName": "string", "updatedAt":
	 *                    "2018-04-18T08:08:02.828Z", "updatedBy": 0 } ], "message":
	 *                    "string", "statusCode": 0 }
	 * @apiError
	 *
	 * @apiErrorExample Error-Response: HTTP/1.1 404 Not Found { "statusCode" : 404,
	 *                  "message" : "Not Found", "data" :{} }
	 */
	@RequestMapping(//
			value = "/{productId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get product  by id")
	public DataResult<Product> getProductById(@PathVariable(value = "productId") int productId) {
		DataResult<Product> result = new DataResult<>();
		Optional<Product> option = mProductRepository.findById(productId);
		Product product  = option.get();
		result.setMessage(MessageResponese.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(product);
		return result;
	}

	/**
	 * @api {post} / Create a new Product 
	 * @apiName createProduct
	 * @api Product
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {Product} the new Product  was created
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK { "statusCode": 200,
	 *                    "message": "Success!", "data": { "productId": 1,
	 *                    "createdAt": "2018-04-18T09:26:21.686+0000", "createdBy":
	 *                    1, "isActive": 0, "productCode": "123",
	 *                    "productDescription": "This is a Product 
	 *                    description", "productName": " 1", "updatedAt":
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
	@ApiOperation(value = "Create a new product ")
	public DataResult<Product> CreateProduct(@RequestBody Product product) {
	
		DataResult<Product> result = new DataResult<>();

		Product temp = mProductRepository.findByCode(product.getProductCode());
		if(temp == null) {
			Date date = new Date();
			product.setProductId(0);
			product.setCreatedAt(date);
			product.setUpdatedAt(date);
			temp = mProductRepository.save(product);

			result.setMessage(MessageResponese.SUCCESSED);
			result.setStatusCode(HttpStatus.OK.value());
		}else {
			result.setMessage(MessageResponese.EXITS);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		
		
		result.setData(temp);
		
		return result;
	}

	/**
	 * @api {put}/{productId} update Product  by id
	 * @apiName updateProduct
	 * @api Product
	 * 
	 * @apiParam {productId} id Product  unique ID.
	 * @apiBody {product} the info of Product  need to update
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {Product} the new Product  was updated
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK { "statusCode": 200,
	 *                    "message": "Success!", "data": { "productId": 1,
	 *                    "createdAt": "2018-04-18T09:26:21.686+0000", "createdBy":
	 *                    1, "isActive": 0, "productCode": "123",
	 *                    "productDescription": "This is a Product 
	 *                    description", "productName": " 1", "updatedAt":
	 *                    "2018-04-18T09:26:21.686+0000", "updatedBy": 1 } }
	 * @apiError
	 *
	 * @apiErrorExample Error-Response: HTTP/1.1 404 Not Found { "statusCode" : 404,
	 *                  "message" : "Product  was exited", "data" :{} }
	 */
	@RequestMapping(//
			value = "/{productId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update product  by id")
	public DataResult<Product> updateProduct(@PathVariable(value = "productId") int productId,
			@RequestBody Product product) {
		DataResult<Product> result;
		Optional<Product> option = mProductRepository.findById(productId);
		Product oldProduct = option.get();

		product.setProductId(oldProduct.getProductId());

		product = mProductRepository.save(product);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponese.SUCCESSED, product);

		return result;
	}

	/**
	 * @api {delete}/{productId} delete Product  by id
	 * @apiName deleteProduct
	 * @api Product
	 * 
	 * @apiParam {productId} id Product  unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {Product} the Product  was deleted
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK { "data": [ {
	 *                    "createdAt": "2018-04-18T08:08:02.828Z", "createdBy": 0,
	 *                    "isActive": 0, "productCode": "string",
	 *                    "productDescription": "string", "productId": 0,
	 *                    "productName": "string", "updatedAt":
	 *                    "2018-04-18T08:08:02.828Z", "updatedBy": 0 } ], "message":
	 *                    "string", "statusCode": 0 }
	 * @apiError
	 *
	 * @apiErrorExample Error-Response: HTTP/1.1 404 Not Found { "statusCode" : 404,
	 *                  "message" : "Product  was not found", "data" :{} }
	 */
	@RequestMapping(//
			value = "/{productId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete product  by id")
	public DataResult<Product> deleteProduct(@PathVariable(value = "productId") int productId) {
		DataResult<Product> dataResult = new DataResult<>();
		Product product = mProductRepository.findById(productId).get();
		mProductRepository.deleteById(productId);
		dataResult.setMessage(MessageResponese.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(product);
		return dataResult;
	}
}
