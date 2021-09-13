package com.szakdolgozat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.szakdolgozat.service.UploadFileService;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin
@RequestMapping("/api/profile")
@RestController
public class UploadController {

	private UploadFileService uploadFileService;

	@Autowired
	UploadController(UploadFileService uploadFileService) {
		this.uploadFileService = uploadFileService;
	}

	@PostMapping("/uploadImage/{email}")
	public ResponseEntity<?> uploadImage(@PathVariable(value = "email") String email,
			@RequestPart(value = "file") MultipartFile file) {
		try {
			String response = this.uploadFileService.uploadImage(email, file);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PostMapping("/deleteImageName/{email}")
	public ResponseEntity<?> deleteImageNameFromDatabase(@PathVariable(value = "email") String email) {
		try {
			this.uploadFileService.deleteImageNameFromDatabase(email);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
