package com.szakdolgozat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.szakdolgozat.dto.MessageDto;
import com.szakdolgozat.dto.MyMessagesDto;
import com.szakdolgozat.dto.NewMessageDto;
import com.szakdolgozat.dto.RequestMessageDto;
import com.szakdolgozat.service.MessageService;

@RestController
@PreAuthorize("hasAnyRole('ROLE_EMPLOYEE','ROLE_EMPLOYER')")
@RequestMapping("/message")
@CrossOrigin
public class MessageController {

	private MessageService messageService;

	@Autowired
	public MessageController(MessageService messageService) {
		this.messageService = messageService;
	}

	@PostMapping(path = "/new_message/{email}")
	public ResponseEntity<?> addMessage(@PathVariable(value = "email") String emailOfSender,
			@Valid @RequestBody NewMessageDto newMessageDto, BindingResult bindingResult) {
		messageService.addMessage(emailOfSender, newMessageDto);
		return new ResponseEntity(HttpStatus.CREATED);
	}

	@PostMapping(path = "/get_messages/{email}")
	public ResponseEntity<?> getMessages(@PathVariable(value = "email") String email,
			@Valid @RequestBody RequestMessageDto messageRequest, BindingResult bindingResult) {
		MessageDto messageDto = messageService.getMessages(email, messageRequest);
		return new ResponseEntity(messageDto, HttpStatus.CREATED);
	}

	@PostMapping(path = "/all_message/{email}")
	public ResponseEntity<?> getAllMyMessage(@PathVariable(value = "email") String email,
			@Valid @RequestBody RequestMessageDto messageRequest, BindingResult bindingResult) {
		List<MyMessagesDto> messageDto = messageService.getAllMyMessage(email, messageRequest);
		return new ResponseEntity(messageDto, HttpStatus.CREATED);
	}

}
