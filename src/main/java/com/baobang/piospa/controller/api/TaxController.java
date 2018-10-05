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
import com.baobang.piospa.entities.Tax;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.TaxRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;
import io.swagger.annotations.ApiOperation;

/**
  * @author BaoBang
  * @Created Jul 24, 2018
  * 
  */
@RestController
@RequestMapping(RequestPath.TAX_PATH)
public class TaxController {
	
	@Autowired
	TaxRepository mTaxRepository;
	
	/**
	 * @api {get} / Request Tax information
	 * @apiName getAll
	 * @apiTime Tax
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list Tax of the response
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all tax")
	public DataResult<List<Tax>> getAll() {

		List<Tax> taxes = mTaxRepository.findAll();

		return new DataResult<List<Tax>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, taxes);
	}

	/**
	 *  
	 * @api {get} /{taxId} Request Tax information
	 * @apiName getTaxById
	 * @apiGroup Tax
	 * 
	 * @param {taxId} id of tax unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Tax} the tax was got
	 * 
	 */
	@RequestMapping(//
			value = "/{taxId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get tax by id")
	public DataResult<Tax> getServiceTimeById(@PathVariable(value = "taxId") int taxId) {
		DataResult<Tax> result = new DataResult<>();
		Optional<Tax> option = mTaxRepository.findById(taxId);
		Tax tax = option.get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(tax);
		return result;
	}

	/**
	 * @api {post} / Create a new tax
	 * @apiName createTax
	 * @apiGroup tax
	 * 
	 * @apiParam none
	 * @apiBody {Tax}
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Tax} the new tax was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new tax")
	public DataResult<Tax> CreateServiceTime(@RequestBody Tax tax) {
		DataResult<Tax> result = new DataResult<>();

		tax = mTaxRepository.save(tax);

		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());

		result.setData(tax);
		return result;
	}

	/**
	 * @api {put}/{taxId} update tax by id
	 * @apiName updateTax
	 * @apiGrouope Tax
	 * 
	 * @apiParam {taxId} id Tax unique ID.
	 * @apiBody {Tax} the info of tax need to update
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Tax} the new tax was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{taxId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update tax by id")
	public DataResult<Tax> updateTax(@PathVariable(value = "taxId") int taxId,
			@RequestBody Tax tax) {
		DataResult<Tax> result;
		Optional<Tax> option = mTaxRepository.findById(taxId);
		Tax oldTax = option.get();

		oldTax.setName(tax.getName());
		oldTax.setValue(tax.getValue());
		oldTax.setType(tax.getType());

		oldTax = mTaxRepository.save(oldTax);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, oldTax);

		return result;
	}

	/**
	 * @api {delete}/{taxId} delete tax by id
	 * @apiName deleteTax
	 * @apiTime Tax
	 * 
	 * @apiParam {taxiD} id of tax unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Tax} the Tax was deleted
	 * 
	 */
	@RequestMapping(//
			value = "/{taxId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete tax by id")
	public DataResult<Tax> deleteTax(@PathVariable(value = "taxId") int taxId) {
		DataResult<Tax> dataResult = new DataResult<>();
		Tax tax = mTaxRepository.findById(taxId).get();
		mTaxRepository.deleteById(taxId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(tax);
		return dataResult;
	}
}
