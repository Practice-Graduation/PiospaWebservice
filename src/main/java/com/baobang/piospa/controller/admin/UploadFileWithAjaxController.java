package com.baobang.piospa.controller.admin;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.baobang.piospa.model.UploadForm;
import com.baobang.piospa.utils.ConvertCharacterUtils;

@Controller
@RequestMapping(value = "/ajax/upload")
public class UploadFileWithAjaxController {

	private static final String UPLOAD_DIRECTORY = "uploads";

	// Phương thức này được gọi mỗi lần có Submit.
	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		Object target = dataBinder.getTarget();
		if (target == null) {
			return;
		}
		System.out.println("Target=" + target);

		if (target.getClass() == UploadForm.class) {
			// Đăng ký để chuyển đổi giữa các đối tượng multipart thành byte[]
			dataBinder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		}
	}

	// POST: Sử lý Upload
	@ResponseBody
	@RequestMapping(value = "/one-file", method = RequestMethod.POST)
	public String uploadOneFileHandlerPOST(HttpServletRequest request, Model model,
			@RequestParam("file") MultipartFile file) {
		return doUpload(request, model, file);
	}

	private String doUpload(HttpServletRequest request, Model model, MultipartFile file) {
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

				return ServletUriComponentsBuilder.fromCurrentContextPath().path("/" + UPLOAD_DIRECTORY + "/")
						.path(year + "/" + fileSlug).toUriString();
			} catch (Exception e) {
				System.out.println("Error Write file: " + name);
			}
		}
		return "";
	}
}
