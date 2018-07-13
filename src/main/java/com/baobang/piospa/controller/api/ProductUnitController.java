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

import com.baobang.piospa.entities.ProductUnit;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.ProductUnitRepository;
import com.baobang.piospa.utils.MessageResponse;
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
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list product unit of the response
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all product units")
	public DataResult<List<ProductUnit>> getAll() {

		List<ProductUnit> productUnits = mUnitRepository.findAll();

		return new DataResult<List<ProductUnit>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, productUnits);
	}

	/**
	 * @api {get} /{productUnitId} Request Product Unit information
	 * @apiName getProductUnitById
	 * @apiUnit Product
	 * 
	 * @apiParam {productUnitId} id Product Unit unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ProductUnit} the Product Unit was got
	 * 
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
		result.setMessage(MessageResponse.SUCCESSED);
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
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ProductUnit} the new Product Unit was created
	 * 
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

		result.setMessage(MessageResponse.SUCCESSED);
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
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ProductUnit} the new Product Unit was updated
	 * 
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

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, Unit);

		return result;
	}

	/**
	 * @api {delete}/{productUnitId} delete Product Unit by id
	 * @apiName deleteProductUnit
	 * @apiUnit Product
	 * 
	 * @apiParam {productUnitId} id Product Unit unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ProductUnit} the Product Unit was deleted
	 * 
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
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(productUnit);
		return dataResult;
	}
}
