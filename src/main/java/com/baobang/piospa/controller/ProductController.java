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
import com.baobang.piospa.utils.MessageResponse;
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
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list product  of the response
	 *
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all products")
	public DataResult<List<Product>> getAll() {
		
		List<Product> products = mProductRepository.findAll();

		return new DataResult<List<Product>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, products);
	}
	
	/**
	 * @api {get} /{productId} Request Product  information
	 * @apiName getProductById
	 * @api Product
	 * 
	 * @apiParam {productId} id Product  unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Product} the Product  was got
	 * 
	 */
	@RequestMapping(//
			value = "/{productId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get product  by id")
	public DataResult<Product> getProductById(@PathVariable(value = "productId") int productId) {
		DataResult<Product> result = new DataResult<>();
		Product product  = getProduct(productId);
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(product);
		return result;
	}
	
	public Product getProduct(int productId) {
		Optional<Product> option = mProductRepository.findById(productId);
		Product product  = option.get();
		return product;
	}
	
	/**
	 * @api {post} / Create a new Product 
	 * @apiName createProduct
	 * @api Product
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Product} the new Product  was created
	 * 
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
	 * @api {put}/{productId} update Product  by id
	 * @apiName updateProduct
	 * @api Product
	 * 
	 * @apiParam {productId} id Product  unique ID.
	 * @apiBody {product} the info of Product  need to update
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Product} the new Product  was updated
	 * 
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

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, product);

		return result;
	}

	/**
	 * @api {delete}/{productId} delete Product  by id
	 * @apiName deleteProduct
	 * @api Product
	 * 
	 * @apiParam {productId} id Product  unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Product} the Product  was deleted
	 * 
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
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(product);
		return dataResult;
	}
}
