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

import com.baobang.piospa.entities.User;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.model.LoginForm;
import com.baobang.piospa.repositories.UserRepository;
import com.baobang.piospa.utils.MessageResponese;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(RequestPath.USER_PATH)
public class UserController {

	@Autowired
	private UserRepository mUserRepository;

	/**
	 * @api {get} / Request User information
	 * @apiName getAll
	 * @apiGroup User
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {array} the list user of the responese
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK { "statusCode" : 200,
	 *                    "message" : "OK", "data" :[ {User info}, {User info} ] }
	 * @apiError
	 *
	 * @apiErrorExample Error-Response: HTTP/1.1 404 Not Found { "statusCode" : 404,
	 *                  "message" : "Not Found", "data" :[] }
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all users")
	public DataResult<List<User>> getAll() {

		List<User> users = mUserRepository.findAll();

		return new DataResult<List<User>>(HttpStatus.OK.value(), MessageResponese.SUCCESSED, users);
	}

	/**
	 * @api {get} /{userId} Request User information
	 * @apiName getUserById
	 * @apiGroup User
	 * 
	 * @apiParam {userId} id Users unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {User} the user was got
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK { "statusCode" : 200,
	 *                    "message" : "OK", "data":{ "id":1,
	 *                    "createdAt":"2017-03-11T01:58:25.000+0000",
	 *                    "email":"admin@tamtm.com", "isActive":1, "name":"Admin",
	 *                    "updatedAt":"2017-10-07T03:03:02.000+0000" } }
	 * @apiError
	 *
	 * @apiErrorExample Error-Response: HTTP/1.1 404 Not Found { "statusCode" : 404,
	 *                  "message" : "Not Found", "data" :{} }
	 */
	@RequestMapping(//
			value = "/{userId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get user by id")
	public DataResult<User> getUserById(@PathVariable(value = "userId") int userId){
		DataResult<User> result = new DataResult<>();
		Optional<User> option = mUserRepository.findById(userId);
		User user = option.get();
		result.setMessage(MessageResponese.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(user);
		return result;
	}

	/**
	 * @api {put} /{userId} update User information
	 * @apiName updateUser
	 * @apiGroup User
	 * 
	 * @apiParam {userId} id Users unique ID.
	 * @apiBody {user} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {User} the user was updated
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK { "statusCode" : 200,
	 *                    "message" : "OK", "data":{ "id":1,
	 *                    "createdAt":"2017-03-11T01:58:25.000+0000",
	 *                    "email":"admin@tamtm.com", "isActive":1, "name":"Admin",
	 *                    "updatedAt":"2017-10-07T03:03:02.000+0000" } }
	 * @apiError
	 *
	 * @apiErrorExample Error-Response: HTTP/1.1 404 Not Found { "statusCode" : 404,
	 *                  "message" : "Not Found", "data" :{} }
	 */
	@RequestMapping(//
			value = "/{userId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Update user by id")
	public DataResult<User> updateUser(@PathVariable(value = "userId") int userId, @RequestBody User user) {
		DataResult<User> result;
		Optional<User> option = mUserRepository.findById(userId);

		User newUser = option.get();
		
		newUser.setName(user.getName());
		newUser.setIsActive(user.getIsActive());
		newUser.setUpdatedAt(new Date());

		newUser = mUserRepository.save(newUser);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponese.SUCCESSED, newUser);

		return result;
	}

	/**
	 * @api {delete} /{userId} delete User by id
	 * @apiName deleteUserById
	 * @apiGroup User
	 * 
	 * @apiParam {userId} id Users unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {User} the user of the was deleted
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK { "statusCode" : 200,
	 *                    "message" : "OK", "data":{ "id":1,
	 *                    "createdAt":"2017-03-11T01:58:25.000+0000",
	 *                    "email":"admin@tamtm.com", "isActive":1, "name":"Admin",
	 *                    "updatedAt":"2017-10-07T03:03:02.000+0000" } }
	 * @apiError
	 *
	 * @apiErrorExample Error-Response: HTTP/1.1 404 Not Found { "statusCode" : 404,
	 *                  "message" : "Not Found", "data" :{} }
	 */
	@RequestMapping(//
			value = "/{userId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete user by id")
	public DataResult<User> deleteUserById(@PathVariable(value = "userId")int userId) {
		DataResult<User> dataResult = new DataResult<>();
		User user = mUserRepository.findById(userId).get();
		mUserRepository.deleteById(userId);
		dataResult.setMessage(MessageResponese.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(user);
		return dataResult;
	}
	
	/**
	 * @api {post} / create a new user
	 * @apiName createUser
	 * @apiGroup User
	 * 
	 * @apiBody {user} the info of user need to create
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {User} the user of the was created
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK { "statusCode" : 200,
	 *                    "message" : "OK", "data":{ "id":1,
	 *                    "createdAt":"2017-03-11T01:58:25.000+0000",
	 *                    "email":"admin@tamtm.com", "isActive":1, "name":"Admin",
	 *                    "updatedAt":"2017-10-07T03:03:02.000+0000" } }
	 * @apiError
	 *
	 * @apiErrorExample Error-Response: HTTP/1.1 404 Not Found { "statusCode" : 404,
	 *                  "message" : "User was exited", "data" :{user} }
	 */
	@RequestMapping(//
			value = {"", "/"}, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Create a new user")
	public DataResult<User> createUser(@RequestBody User user) {
		DataResult<User> dataResult = new DataResult<>();
		
		User temp = mUserRepository.findByEmail(user.getEmail());
		
		if(temp == null) {
			Date date = new Date();
			user.setId(0);
			user.setCreatedAt(date);
			user.setUpdatedAt(date);
			temp = mUserRepository.save(user);
			dataResult.setMessage(MessageResponese.SUCCESSED);
			dataResult.setStatusCode(HttpStatus.OK.value());
		}else {
			dataResult.setMessage(MessageResponese.EXITS);
			dataResult.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		dataResult.setData(temp);
		
		return dataResult;
	}

	/**
	 * @api {post} / login
	 * @apiName login
	 * @apiGroup User
	 * 
	 * @apiBody {loginForm} the username and password 
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {User} the user of the was logined
	 * 
	 * @apiSuccessExample Success-Response: HTTP/1.1 200 OK { "statusCode" : 200,
	 *                    "message" : "OK", "data":{ "id":1,
	 *                    "createdAt":"2017-03-11T01:58:25.000+0000",
	 *                    "email":"admin@tamtm.com", "isActive":1, "name":"Admin",
	 *                    "updatedAt":"2017-10-07T03:03:02.000+0000" } }
	 * @apiError
	 *
	 * @apiErrorExample Error-Response: HTTP/1.1 404 Not Found { "statusCode" : 404,
	 *                  "message" : "User name or password invalid", "data" :{user} }
	 */
	@RequestMapping(//
			value = {"/login"}, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Login")
	public DataResult<User> login(@RequestBody LoginForm loginForm) {
		DataResult<User> dataResult = new DataResult<>();
		
		User temp = mUserRepository.findByEmail(loginForm.getUsername());
		
		if(temp == null) {
			dataResult.setMessage(MessageResponese.ACCOUNT_NOT_EXSIT);
			dataResult.setStatusCode(HttpStatus.NOT_FOUND.value());
			
		}else {
			temp = mUserRepository.findByEmailAndPassword(loginForm.getUsername(), loginForm.getPassword());
			
			if(temp == null) {
				dataResult.setMessage(MessageResponese.PASSWORD_INCORRECT);
				dataResult.setStatusCode(HttpStatus.NOT_FOUND.value());
			}else {
				dataResult.setMessage(MessageResponese.SUCCESSED);
				dataResult.setStatusCode(HttpStatus.OK.value());
			}
		}
		dataResult.setData(temp);
		
		return dataResult;
	}
}
