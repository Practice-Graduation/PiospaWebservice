package com.baobang.piospa.controller.api;

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

import com.baobang.piospa.entities.ProductOrigin;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.ProductOriginRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
  * @author BaoBang
  * @Created Apr 18, 2018
  * 
  */
@RestController
@RequestMapping(RequestPath.PRODUCT_ORIGIN_PATH)
public class ProductOriginController {
	@Autowired
	ProductOriginRepository mOriginRepository;

	/**
	 * @api {get} / Request Product Origin information
	 * @apiName getAll
	 * @apiOrigin Product
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list product Origin of the response
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all product Origins")
	public DataResult<List<ProductOrigin>> getAll() {

		List<ProductOrigin> productOrigins = mOriginRepository.findAll();

		return new DataResult<List<ProductOrigin>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, productOrigins);
	}

	/**
	 * @api {get} /{productOriginId} Request Product Origin information
	 * @apiName getProductOriginById
	 * @apiOrigin Product
	 * 
	 * @apiParam {productOriginId} id Product Origin unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ProductOrigin} the Product Origin was got
	 * 
	 */
	@RequestMapping(//
			value = "/{productOriginId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get product Origin by id")
	public DataResult<ProductOrigin> getProductOriginById(@PathVariable(value = "productOriginId") int productOriginId) {
		DataResult<ProductOrigin> result = new DataResult<>();
		Optional<ProductOrigin> option = mOriginRepository.findById(productOriginId);
		ProductOrigin Origin = option.get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(Origin);
		return result;
	}

	/**
	 * @api {post} / Create a new Product Origin
	 * @apiName createProductOrigin
	 * @apiOrigin Product
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ProductOrigin} the new Product Origin was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new product Origin")
	public DataResult<ProductOrigin> CreateProductOrigin(@RequestBody ProductOrigin productOrigin) {
	
		DataResult<ProductOrigin> result = new DataResult<>();
		ProductOrigin origin = mOriginRepository.findByName(productOrigin.getProductOriginName());
		if(origin == null) {
			productOrigin.setProductOriginId(0);
			productOrigin = mOriginRepository.save(productOrigin);
			result.setMessage(MessageResponse.SUCCESSED);
			result.setStatusCode(HttpStatus.OK.value());
		}else {
			result.setMessage(MessageResponse.EXITS);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		result.setData(productOrigin);
		
		return result;
	}

	/**
	 * @api {put}/{productOriginId} update Product Origin by id
	 * @apiName updateProductOrigin
	 * @apiOrigin Product
	 * 
	 * @apiParam {productOriginId} id Product Origin unique ID.
	 * @apiBody {productOrigin} the info of Product Origin need to update
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ProductOrigin} the new Product Origin was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{productOriginId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update product Origin by id")
	public DataResult<ProductOrigin> updateProductOrigin(@PathVariable(value = "productOriginId") int productOriginId,
			@RequestBody ProductOrigin productOrigin) {
		DataResult<ProductOrigin> result;
		Optional<ProductOrigin> option = mOriginRepository.findById(productOriginId);

		ProductOrigin Origin = option.get();

		Origin.setProductOriginName(productOrigin.getProductOriginName());
		Origin.setIsActive(productOrigin.getIsActive());

		Origin = mOriginRepository.save(Origin);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, Origin);

		return result;
	}

	/**
	 * @api {delete}/{productOriginId} delete Product Origin by id
	 * @apiName deleteProductOrigin
	 * @apiOrigin Product
	 * 
	 * @apiParam {productOriginId} id Product Origin unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ProductOrigin} the Product Origin was deleted
	 * 
	 */
	@RequestMapping(//
			value = "/{productOriginId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete product Origin by id")
	public DataResult<ProductOrigin> deleteProductOrigin(@PathVariable(value = "productOriginId") int productOriginId) {
		DataResult<ProductOrigin> dataResult = new DataResult<>();
		ProductOrigin productOrigin = mOriginRepository.findById(productOriginId).get();
		mOriginRepository.deleteById(productOriginId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(productOrigin);
		return dataResult;
	}
}
