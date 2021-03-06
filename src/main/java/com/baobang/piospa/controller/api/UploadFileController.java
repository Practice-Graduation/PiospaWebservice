package com.baobang.piospa.controller.api;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.baobang.piospa.model.DataResult;
import com.baobang.piospa.utils.ConvertCharacterUtils;
import com.baobang.piospa.utils.MessageResponse;
import com.baobang.piospa.utils.RequestPath;

/**
 * @author BaoBang
 * @Created Jul 13, 2018
 * 
 */
@RestController
@RequestMapping(RequestPath.UPLOAD_PATH)
public class UploadFileController {

	private static final String UPLOAD_DIRECTORY = "uploads";

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public DataResult<String> uploadImage2(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		// Thời Gian Upload
		Calendar calendar = Calendar.getInstance();
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);

		// Thư mục gốc upload file.
		String uploadRootPath = request.getServletContext().getRealPath(UPLOAD_DIRECTORY) + File.separator + year;
		System.out.println("uploadRootPath=" + uploadRootPath);

		File uploadRootDir = new File(uploadRootPath);

		// Tạo thư mục gốc upload nếu nó không tồn tại.
		if (!uploadRootDir.exists()) {
			uploadRootDir.mkdirs();
		}

		// Tên file gốc tại Client.
		String name = file.getOriginalFilename();
		System.out.println("Client File Name = " + name);
		String fileName = year + "-" + month + "-" + day + "-" + hour + "-" + minute + "-" + second + "-" + name;
		String fileSlug = ConvertCharacterUtils.toURLFriendly(fileName);

		if (name != null && name.length() > 0) {
			try {
				// Tạo file tại Server.
				File serverFile = new File(uploadRootDir.getAbsolutePath() + File.separator + fileSlug);

				BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
				stream.write(file.getBytes());
				stream.close();
				String path = ServletUriComponentsBuilder.fromCurrentContextPath().path("/" + UPLOAD_DIRECTORY + "/")
						.path(year + "/" + fileSlug).toUriString();
				

				return new DataResult<String>(HttpStatus.OK.value(), MessageResponse.SUCCESSED, path);
			} catch (Exception e) {
				System.out.println("Error Write file: " + name);
			}
		}

		return new DataResult<String>(HttpStatus.NOT_FOUND.value(), MessageResponse.NOT_FOUND, "");

	}
}
