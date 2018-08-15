package com.baobang.piospa.controller.api;
/**
  * @author BaoBang
  * @Created Apr 24, 2018
  * 
  */

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.baobang.piospa.entities.Customer;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.model.LoginForm;
import com.baobang.piospa.repositories.CustomerRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;
import com.baobang.piospa.utils.Utils;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(RequestPath.CUSTOMER_PATH)
public class CustomerController {

	
	@Autowired
	CustomerRepository mCustomerRepository;
	
	/**
	 * @api {get} / Request Customer information
	 * @apiName getAll
	 * @api Customer
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list Customer
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Customers")
	public DataResult<List<Customer>> getAll() {

		List<Customer> customers = mCustomerRepository.findAll();

		return new DataResult<List<Customer>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, customers);
	}

	/**
	 * @api {get} /{CustomerId} Request Customer information
	 * @apiName getCustomerById
	 * @apiOrigin Customer
	 * 
	 * @apiParam {CustomerId} id Customer unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {ProductOrigin} the Customer was got
	 * 
	 */
	@RequestMapping(//
			value = "/{customerId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get Customer by id")
	public DataResult<Customer> getCustomerById(
			@PathVariable(value = "customerId") int customerId) {
		DataResult<Customer> result = new DataResult<>();
		Customer customer = mCustomerRepository.findById(customerId).get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(customer);
		return result;
	}

	/**
	 * @api {post} / Create a new Customer information
	 * @apiName createCustomer
	 * @api Customer
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Customer} the customer  was created
	 * 
	 */
	@RequestMapping(//
			value = {"", "/"}, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create new Customer")
	public DataResult<Customer> createCustomer(@RequestBody Customer customer) {
		DataResult<Customer> result = new DataResult<>();
		Customer temp  = mCustomerRepository.findByAccount(customer.getAccount());

		if (temp == null) {
			temp = mCustomerRepository.save(customer);
			result.setMessage(MessageResponse.SUCCESSED);
			result.setStatusCode(HttpStatus.OK.value());
		} else {
			result.setMessage(MessageResponse.ACCOUNT_EXSIT);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		result.setData(temp);
		return result;
	}

	/**
	 * @api {put} /{customerId} update Customer information
	 * @apiName updateCustomer
	 * @api Customer
	 * 
	 * @apiParam {CustomerId} id Customer unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Customer} the customer  was created
	 * 
	 */
	@RequestMapping(//
			value = "/{customerId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update Customer by id")
	public DataResult<Customer> updateCustomer(
			@PathVariable(value = "customerId") int customerId,
			@RequestBody Customer customer) {

		DataResult<Customer> result = new DataResult<>();
		Customer temp = mCustomerRepository.findById(customerId).get();

		temp.setFullname(customer.getFullname());
		temp.setGender(customer.getGender());
		temp.setBirthday(customer.getBirthday());
		temp.setPhone(customer.getPhone());
		temp.setIsActive(customer.getIsActive());
		temp.setProvince(customer.getProvince());
		temp.setDistrict(customer.getDistrict());
		temp.setWard(customer.getWard());
		temp.setEmail(customer.getEmail());
		temp.setAddress(customer.getAddress());
		temp.setCustomerAvatar(customer.getCustomerAvatar());
		temp.setPassword(customer.getPassword());
		temp = mCustomerRepository.save(temp);
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());

		result.setData(temp);
		return result;
	}
	
	/**
	 * @api {delete} /{customerId} delete Customer information
	 * @apiName deleteCustomer
	 * @api Customer
	 * 
	 * @apiParam {CustomerId} id Customer unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Customer} the customer  was created
	 * 
	 */
	@RequestMapping(//
			value = "/{customerId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete Customer by id")
	public DataResult<Customer> deleteCustomer(
			@PathVariable(value = "customerId") int customerId) {

		DataResult<Customer> result = new DataResult<>();
		Customer customer = mCustomerRepository.findById(customerId).get();

		mCustomerRepository.delete(customer);
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());

		result.setData(customer);
		return result;
	}
	
	/**
	 * @api {post} /login  request login
	 * @apiName login
	 * @api Customer
	 * 
	 * @apiParam  none
	 * @apiBody {username, password} account login
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Customer} the customer  was logined
	 * 
	 */
	@RequestMapping(//
			value = "/login", //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "login")
	public DataResult<Customer> login(
			@RequestBody LoginForm loginRequest) {

		DataResult<Customer> result = new DataResult<>();
		Customer customer = mCustomerRepository.findByAccount(loginRequest.getUsername());
		
		if(customer == null) {
			result.setMessage(MessageResponse.ACCOUNT_NOT_EXSIT);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		}else {
			customer = mCustomerRepository.findByAccountAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
			if(customer == null) {
				result.setMessage(MessageResponse.PASSWORD_INCORRECT);
				result.setStatusCode(HttpStatus.NOT_FOUND.value());
			}else {
				result.setMessage(MessageResponse.SUCCESSED);
				result.setStatusCode(HttpStatus.OK.value());
			}
		}

		result.setData(customer);
		return result;
	}
	
	
}
