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

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserRepository mUserRepository;

	@RequestMapping(value = "/",
			method = RequestMethod.GET)
	public String getAll() {


		return "Hello Spring Boot and Heruko";
	}

	//
	// /**
	// *
	// * @return the list of users
	// *
	// */
	// @RequestMapping(//
	// method = RequestMethod.GET, //
	// produces = { MediaType.APPLICATION_JSON_VALUE,
	// MediaType.APPLICATION_XML_VALUE })
	// public DataResult<List<User>> getAll() {
	//
	// List<User> users = mUserRepository.findAll();
	//
	// return new DataResult<List<User>>(HttpCode.OK.getCode(),
	// MessageResponese.SUCCESSED, users);
	// }
	//
	// /**
	// * The method get user profile by ID from parameter
	// *
	// * @param usedId
	// * the ID of user
	// * @return the user by userId
	// *
	// */
	// @RequestMapping(//
	// value = "/{userId}", //
	// method = RequestMethod.GET, //
	// produces = { MediaType.APPLICATION_JSON_VALUE,
	// MediaType.APPLICATION_XML_VALUE })
	// public DataResult<User> getUserById(@PathVariable(value = "userId") int
	// userId) {
	// DataResult<User> result = new DataResult<>();
	// Optional<User> option = mUserRepository.findById(userId);
	// User user = null;
	// if (option == null) {
	// result.setMessage(MessageResponese.USER_NOT_FOUND);
	// result.setStatusCode(HttpCode.NO_CONTENT.getCode());
	// } else {
	// user = option.get();
	// result.setMessage(MessageResponese.SUCCESSED);
	// result.setStatusCode(HttpCode.OK.getCode());
	// }
	// result.setData(user);
	// return result;
	// }

	/**
	 * 
	 * @param usedId
	 *            the id of user
	 * @param user
	 *            user's info need to update
	 * @return the user was updated
	 *
	 */
	// @RequestMapping(//
	// value = "/{userId}", //
	// method = RequestMethod.PUT, //
	// produces = { MediaType.APPLICATION_JSON_VALUE,
	// MediaType.APPLICATION_XML_VALUE })
	// public DataResult<User> updateUser(@PathVariable(value = "userId") int
	// userId, @RequestBody User user) {
	// DataResult<User> result;
	// Optional<User> option = mUserRepository.findById(userId);
	// if (option == null) {
	// result = new DataResult<>(HttpCode.NOT_FOUND.getCode(),
	// MessageResponese.USER_NOT_FOUND, null);
	// } else {
	//
	// User newUser = option.get();
	//
	// newUser.setName(user.getName());
	// newUser.setEmail(user.getEmail());
	// newUser.setIsActive(user.getIsActive());
	// newUser.setPassword(user.getPassword());
	// newUser.setRememberToken(user.getRememberToken());
	// newUser.setUpdatedAt(user.getUpdatedAt());
	// newUser.setCreatedAt(user.getCreatedAt());
	//
	// result = new DataResult<>(HttpCode.OK.getCode(), MessageResponese.SUCCESSED,
	// newUser);
	// }
	// return result;
	// }

}
