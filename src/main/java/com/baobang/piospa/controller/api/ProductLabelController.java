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

import com.baobang.piospa.entities.ProductLabel;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.ProductLabelRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;
import com.baobang.piospa.utils.Utils;

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
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list product label of the response
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all product labels")
	public DataResult<List<ProductLabel>> getAll() {

		List<ProductLabel> productLabels = mLabelRepository.findAll();

		return new DataResult<List<ProductLabel>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, productLabels);
	}

	/**
	 * @api {get} /{productLabelId} Request Product Label information
	 * @apiName getProductLabelById
	 * @apiGroup Product
	 * 
	 * @apiParam {productLabelId} id Product Label unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ProductLabel} the Product Label was got
	 * 
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
		result.setMessage(MessageResponse.SUCCESSED);
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
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ProductLabel} the new Product Label was created
	 * 
	 */
	@RequestMapping(//
			value = {"", "/"}, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new product label")
	public DataResult<ProductLabel> CreateProductLabel(@RequestBody ProductLabel productLabel){
		DataResult<ProductLabel> result = new DataResult<>();
		
		productLabel.setProductLabelId(0);
		productLabel = mLabelRepository.save(productLabel);
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		
		result.setData(productLabel);
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
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ProductLabel} the new Product Label was updated
	 * 
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
		newProductLabel.setProductLabelName(productLabel.getProductLabelName());

		newProductLabel = mLabelRepository.save(newProductLabel);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, newProductLabel);

		return result;
	}

	/**
	 * @api {delete}/{productLabelId} delete  Product Label by id 
	 * @apiName deleteProductLabel
	 * @apiGroup Product
	 * 
	 * @apiParam {productLabelId} id Product Label unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ProductLabel} the  Product Label was deleted
	 * 
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
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(productLabel);
		return dataResult;
	}
}
