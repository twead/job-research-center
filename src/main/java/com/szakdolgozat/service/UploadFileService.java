package com.szakdolgozat.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.Storage;
import com.szakdolgozat.entity.User;
import com.szakdolgozat.exception.ApiRequestException;

@Service
public class UploadFileService {

	@Autowired
	private UserService userService;

	@Value("${firebase.config.location}")
	private String firebaseConfigLocation;
	@Value("${firebase.bucket.name}")
	private String firebaseBucketName;

	public String uploadImage(String email, MultipartFile multipartFile) {
		String fileName = "";
		try {
			User user = userService.findUserByEmail(email).get();
			File file = convertMultipartFileToFile(multipartFile);
			if (!multipartFile.getContentType().contains("image")) {
				throw new Exception("A megadott kiterjesztés nem megfelelő!");
			}
			fileName = email + "/images/" + multipartFile.getOriginalFilename();
			this.uploadImageToStorage(file, fileName);
			user.getEmployer().setPicture(multipartFile.getOriginalFilename());
			userService.saveUser(user);
			file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return fileName;
	}

	private void uploadImageToStorage(File file, String fileName) throws IOException {

		InputStream is = UploadFileService.class.getResourceAsStream(firebaseConfigLocation);
		BlobId blobId = BlobId.of(firebaseBucketName, fileName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).build();
		Credentials credentials = GoogleCredentials.fromStream(is);
		Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build()
				.getService();
		storage.create(blobInfo, Files.readAllBytes(file.toPath()));
	}

	private File convertMultipartFileToFile(MultipartFile file) throws IOException {
		File convertedFile = new File(file.getOriginalFilename());
		FileOutputStream fos = new FileOutputStream(convertedFile);
		fos.write(file.getBytes());
		fos.close();
		return convertedFile;
	}

	public void deleteImageNameFromDatabase(String email) {
		User user = userService.findUserByEmail(email).orElseThrow(() -> new ApiRequestException("Felhasználó nem található!"));
		user.getEmployer().setPicture(null);
		userService.saveUser(user);
	}

}
