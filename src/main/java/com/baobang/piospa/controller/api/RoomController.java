package com.baobang.piospa.controller.api;
/**
  * @author BaoBang
  * @Created May 13, 2018
  * 
  */

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

import com.baobang.piospa.entities.Room;
import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.repositories.RoomRepository;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

import io.swagger.annotations.ApiOperation;
@RestController
@RequestMapping(RequestPath.ROOM_PATH)
public class RoomController {

	@Autowired
	RoomRepository mRoomRepository;
	
	/**
	 * @api {get} / Request Room information
	 * @apiName getAll
	 * @apiGroup Room
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {array} the list rooms of the response
	 * 
	 */
	@RequestMapping(//
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get all Roomss")
	public DataResult<List<Room>> getAll() {

		List<Room> rooms = mRoomRepository.findAll();

		return new DataResult<List<Room>>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, rooms);
	}

	/**
	 *  
	 * @api {get} /{roomId} Request Room information
	 * @apiName getRoomById
	 * @apiGroup Room
	 * 
	 * @param {roomId} id Room unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Room} the Room was got
	 * 
	 */
	@RequestMapping(//
			value = "/{roomId}", //
			method = RequestMethod.GET, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Get Room by id")
	public DataResult<Room> getRoomById(@PathVariable(value = "roomId") int roomId) {
		DataResult<Room> result = new DataResult<>();
		Optional<Room> option = mRoomRepository.findById(roomId);
		Room Room = option.get();
		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());
		result.setData(Room);
		return result;
	}

	/**
	 * @api {post} / Create a new Room
	 * @apiName createRoom
	 * @apiGroup Room
	 * 
	 * @apiParam none
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Room} the new Room was created
	 * 
	 */
	@RequestMapping(//
			value = { "", "/" }, //
			method = RequestMethod.POST, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Create a new Room")
	public DataResult<Room> CreateRoom(@RequestBody Room room) {
		DataResult<Room> result = new DataResult<>();

		Date date = new Date();
		room.setCreatedAt(date);
		room.setUpdateAt(date);
		room = mRoomRepository.save(room);

		result.setMessage(MessageResponse.SUCCESSED);
		result.setStatusCode(HttpStatus.OK.value());

		result.setData(room);
		return result;
	}

	/**
	 * @api {put}/{roomId} update Room by id
	 * @apiName updateRoom
	 * @apiGroup Room
	 * 
	 * @apiParam {roomId} id Room unique ID.
	 * @apiBody {Room} the info of room need to update
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Room} the new Room was updated
	 * 
	 */
	@RequestMapping(//
			value = "/{roomId}", //
			method = RequestMethod.PUT, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Update Room by id")
	public DataResult<Room> updateRoom(@PathVariable(value = "roomId") int roomId,
			@RequestBody Room room) {
		DataResult<Room> result;
		Optional<Room> option = mRoomRepository.findById(roomId);
		Room temp = option.get();

		temp.setRoomName(room.getRoomName());
		temp.setIsActive(room.getIsActive());
		temp.setStartTime(room.getStartTime());
		temp.setEndTime(room.getEndTime());
		temp.setUpdateAt(new Date());
		temp.setUpdatedBy(room.getUpdatedBy());
		temp.setCreatedBy(room.getCreatedBy());

		temp = mRoomRepository.save(temp);

		result = new DataResult<>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, temp);

		return result;
	}

	/**
	 * @api {delete}/{roomId} delete Room by id
	 * @apiName deleteRoom
	 * @apiTime Room
	 * 
	 * @apiParam {roomId} id Room unique ID.
	 * 
	 * @apiSuccess {Integer} the status of the response
	 * @apiSuccess {String} the message of the response
	 * @apiSuccess {Room} the Room was deleted
	 * 
	 */
	@RequestMapping(//
			value = "/{roomId}", //
			method = RequestMethod.DELETE, //
			produces = { MediaType.APPLICATION_JSON_VALUE })
	@ApiOperation(value = "Delete Room by id")
	public DataResult<Room> deleteRoom(@PathVariable(value = "roomId") int roomId) {
		DataResult<Room> dataResult = new DataResult<>();
		Room room = mRoomRepository.findById(roomId).get();
		mRoomRepository.deleteById(roomId);
		dataResult.setMessage(MessageResponse.SUCCESSED);
		dataResult.setStatusCode(HttpStatus.OK.value());
		dataResult.setData(room);
		return dataResult;
	}
	
}
