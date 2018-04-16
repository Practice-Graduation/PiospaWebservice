package com.baobang.piospa.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baobang.piospa.entities.User;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.UserRepository;
import com.baobang.piospa.utils.HttpCode;
import com.baobang.piospa.utils.MessageResponese;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository mUserRepository;

	/**
	 * @api {get} / Request User information
	 * @apiName getAll
	 * @apiGroup User
	 * 
	 * @apiParam  none
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {array} the list user of the responese
	 * 
	 * @apiSuccessExample Success-Response:
	 * HTTP/1.1 200 OK
	 * {
	 * 		"statusCode" : 200,
	 * 		"message"    : "OK",
	 * 		"data"		 :[
	 * 			{User info},
	 * 			{User info}
	 * 		]
	 * }
	 * @apiError 
	 *
	 * @apiErrorExample Error-Response:
	 *     HTTP/1.1 404 Not Found
	 *     {
	 *       "statusCode" : 404,
	 * 		 "message"    : "Not Found",
	 * 		 "data"		 :[]
	 *     }
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all users")
	public DataResult<List<User>> getAll() {

		List<User> users = mUserRepository.findAll();

		return new DataResult<List<User>>(HttpCode.OK.getCode(), MessageResponese.SUCCESSED, users);
	}

	/**
	 * @api {get} /{userId} Request User information
	 * @apiName getUserById
	 * @apiGroup User
	 * 
	 * @apiParam  {userId} id Users unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the responese
	 * @apiSuccess {String} the message of the responese
	 * @apiSuccess {User} the user of the responese
	 * 
	 * @apiSuccessExample Success-Response:
	 * HTTP/1.1 200 OK
	 * {
	 * 		"statusCode" : 200,
	 * 		"message"    : "OK",
	 * 		"data":{
	 * 				"id":1,
	 * 				"createdAt":"2017-03-11T01:58:25.000+0000",
	 * 				"email":"admin@tamtm.com",
	 * 				"isActive":1,
	 * 				"name":"Admin",
	 * 				"updatedAt":"2017-10-07T03:03:02.000+0000"
	 * 		}
	 * }
	 * @apiError 
	 *
	 * @apiErrorExample Error-Response:
	 *     HTTP/1.1 404 Not Found
	 *     {
	 *       "statusCode" : 404,
	 * 		 "message"    : "Not Found",
	 * 		 "data"		 :[]
	 *     }
	 */
	@RequestMapping(//
			value = "/{userId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Get user by id")
	public DataResult<User> getUserById(@PathVariable(value = "userId") int userId) {
		DataResult<User> result = new DataResult<>();
		Optional<User> option = mUserRepository.findById(userId);
		User user = null;
		if (option == null) {
			result.setMessage(MessageResponese.USER_NOT_FOUND);
			result.setStatusCode(HttpCode.NO_CONTENT.getCode());
		} else {
			user = option.get();
			result.setMessage(MessageResponese.SUCCESSED);
			result.setStatusCode(HttpCode.OK.getCode());
		}
		result.setData(user);
		return result;
	}

	/**
	 * 
	 * @param usedId
	 *            the id of user
	 * @param user
	 *            user's info need to update
	 * @return the user was updated
	 *
	 */
	@RequestMapping(//
			value = "/{userId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "Update user by id")
	public DataResult<User> updateUser(@PathVariable(value = "userId") int userId, @RequestBody User user) {
		DataResult<User> result;
		Optional<User> option = mUserRepository.findById(userId);
		if (option == null) {
			result = new DataResult<>(HttpCode.NOT_FOUND.getCode(), MessageResponese.USER_NOT_FOUND, null);
		} else {

			User newUser = option.get();

			newUser.setName(user.getName());
			newUser.setEmail(user.getEmail());
			newUser.setIsActive(user.getIsActive());
			newUser.setPassword(user.getPassword());
			newUser.setRememberToken(user.getRememberToken());
			newUser.setUpdatedAt(user.getUpdatedAt());
			newUser.setCreatedAt(user.getCreatedAt());

			result = new DataResult<>(HttpCode.OK.getCode(), MessageResponese.SUCCESSED, newUser);
		}
		return result;
	}

}
