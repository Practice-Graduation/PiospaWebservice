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
import com.baobang.piospa.entities.Staff;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.StaffRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;

/**
  * @author BaoBang
  * @Created Apr 26, 2018
  * 
  */
@RestController
@RequestMapping(RequestPath.STAFF_PATH)
public class StaffController {
	@Autowired
	StaffRepository mStaffRepository;
	
	/**
	 * @api {get} / Request Product Staff information
	 * @apiName getAll
	 * @apiGroup Staff
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list product Staff of the response
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all product Staffs")
	public DataResult<List<Staff>> getAll() {

		List<Staff> Staffs = mStaffRepository.findAll();

		return new DataResult<List<Staff>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, Staffs);
	}

	/**
	 * @api {get} /{staffId} Request Staff information
	 * @apiName getStaffById
	 * @apiGroup Staff
	 * 
	 * @apiParam {staffId} id Staff unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Staff} the Product Staff was got
	 * 
	 */
	@RequestMapping(//
			value = "/{staffId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get product Staff by id")
	public DataResult<Staff> getStaffById(@PathVariable(value = "staffId") int staffId){
		DataResult<Staff> result = new DataResult<>();
		Optional<Staff> option = mStaffRepository.findById(staffId);
		Staff Staff = option.get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(Staff);
		return result;
	}
	
	/**
	 * @api {post} / Create a new  Staff 
	 * @apiName createStaff
	 * @apiGroup Staff
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Staff} the new Staff was created
	 * 
	 */
	@RequestMapping(//
			value = {"", "/"}, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new Staff")
	public DataResult<Staff> CreateStaff(@RequestBody Staff staff){
		DataResult<Staff> result = new DataResult<>();
		staff.setStaffId(0);
		staff = mStaffRepository.save(staff);

		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		
		result.setData(staff);
		return result;
	}
	
	/**
	 * @api {put}/{staffId} update Staff by id 
	 * @apiName updateStaff
	 * @apiGroup Staff
	 * 
	 * @apiParam {staffId} id Product Staff unique ID.
	 * @apiBody {Staff} the info of user need to update
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Staff} the new Staff was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{staffId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Update product Staff by id")
	public DataResult<Staff> updateStaff(@PathVariable(value = "staffId") int staffId, @RequestBody Staff staff) {
		DataResult<Staff> result;
		Optional<Staff> option = mStaffRepository.findById(staffId);

		Staff newStaff = option.get();
		newStaff.setFullname(staff.getFullname());
		newStaff.setPhone(staff.getPhone());
		newStaff.setStaffAvatar(staff.getStaffAvatar());
		newStaff.setPassword(staff.getPassword());
		newStaff.setIsAdmin(staff.getIsAdmin());
	

		newStaff = mStaffRepository.save(newStaff);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, newStaff);

		return result;
	}

	/**
	 * @api {delete}/{staffId} delete Staff by id 
	 * @apiName deleteStaff
	 * @apiGroup Staff
	 * 
	 * @apiParam {staffId} id Staff unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Staff} the  Product Staff was deleted
	 * 
	 */
	@RequestMapping(//
			value = "/{staffId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Delete staff by id")
	public DataResult<Staff> deleteStaff(@PathVariable(value = "staffId") int staffId) {
		DataResult<Staff> dataResult = new DataResult<>();
		Staff staff = mStaffRepository.findById(staffId).get();
		mStaffRepository.deleteById(staffId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(staff);
		return dataResult;
	}
}
