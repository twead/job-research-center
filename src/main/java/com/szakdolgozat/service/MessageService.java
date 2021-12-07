package com.szakdolgozat.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szakdolgozat.dto.MessageDto;
import com.szakdolgozat.dto.MyMessagesDto;
import com.szakdolgozat.dto.NewMessageDto;
import com.szakdolgozat.dto.RequestMessageDto;
import com.szakdolgozat.entity.Message;
import com.szakdolgozat.entity.User;
import com.szakdolgozat.exception.ApiRequestException;
import com.szakdolgozat.interfaces.GetOtherPersonId;
import com.szakdolgozat.repository.MessageRepository;
import com.szakdolgozat.repository.UserRepository;

@Service
public class MessageService {

	private MessageRepository messageRepository;
	private UserRepository userRepository;
	private UserService userService;

	@Autowired
	public MessageService(UserService userService, MessageRepository messageRepository, UserRepository userRepository) {
		this.userService = userService;
		this.messageRepository = messageRepository;
		this.userRepository = userRepository;
	}

	public void addMessage(String emailOfSender, NewMessageDto newMessageDto) {
		Message message = new Message(newMessageDto.getMessage());

		User sender = userService.findUserByEmail(emailOfSender)
				.orElseThrow(() -> new ApiRequestException("Nem létezik a felhasználó!"));
		User receiver = userService.findUserById(newMessageDto.getId())
				.orElseThrow(() -> new ApiRequestException("Nem létezik a felhasználó!"));

		message.setDateOfSending(new Date());

		if (!newMessageDto.getIsEmployer()) {
			message.setUser(sender);
			message.setEmployer(receiver.getEmployer());
			message.setFromEmployer(false);
			sender.getMessages().add(message);
			receiver.getEmployer().getMessages().add(message);

		} else {
			message.setUser(receiver);
			message.setEmployer(sender.getEmployer());
			message.setFromEmployer(true);
			receiver.getMessages().add(message);
			sender.getEmployer().getMessages().add(message);
		}

		messageRepository.save(message);
		userService.saveUser(sender);
		userService.saveUser(receiver);
	}

	public MessageDto getMessages(String email, RequestMessageDto messageRequest) {
		MessageDto message = new MessageDto();
		List<Message> messages = new ArrayList<>();

		User requester = userRepository.findByEmail(email)
				.orElseThrow(() -> new ApiRequestException("A keresett felhasználó nem található!"));
		User otherPerson = userService.findUserById(messageRequest.getId())
				.orElseThrow(() -> new ApiRequestException("Nem létezik a felhasználó!"));

		if (!messageRequest.getIsEmployer()) {
			messages = messageRepository.findAllByUserIdAndEmployerIdOrderByDateOfSendingDesc(requester.getId(),
					otherPerson.getEmployer().getId());
		} else {
			messages = messageRepository.findAllByUserIdAndEmployerIdOrderByDateOfSendingDesc(otherPerson.getId(),
					requester.getEmployer().getId());
		}
		message.setMessages(messages);
		message.setRequester(requester);
		message.setOtherPerson(otherPerson);
		return message;
	}

	public List<MyMessagesDto> getAllMyMessage(String email, RequestMessageDto messageRequest) {
		List<MyMessagesDto> messages = new ArrayList<>();
		Collection<GetOtherPersonId> idOfOtherPeople = new ArrayList<>();

		User requester = userRepository.findByEmail(email)
				.orElseThrow(() -> new ApiRequestException("A keresett felhasználó nem található!"));

		if (!messageRequest.getIsEmployer()) {
			idOfOtherPeople = messageRepository.findDistinctEmployerIdByUserId(requester.getId());
		} else {
			idOfOtherPeople = messageRepository.findDistinctUserIdByEmployerId(requester.getEmployer().getId());
		}

		idOfOtherPeople.forEach(row -> {
			Message currentMessage = new Message();
			MyMessagesDto currentMessageDto = new MyMessagesDto();
			if (!messageRequest.getIsEmployer()) {
				currentMessage = messageRepository
						.findFirstByUserIdAndEmployerIdOrderByDateOfSendingDesc(requester.getId(), row.getId());
				currentMessageDto.setRequester(currentMessage.getUser());
				currentMessageDto.setOtherPerson(currentMessage.getEmployer().getUser());
			} else {
				currentMessage = messageRepository.findFirstByUserIdAndEmployerIdOrderByDateOfSendingDesc(row.getId(),
						requester.getEmployer().getId());
				currentMessageDto.setRequester(currentMessage.getEmployer().getUser());
				currentMessageDto.setOtherPerson(currentMessage.getUser());
			}
			currentMessageDto.setLastMessage(currentMessage);
			messages.add(currentMessageDto);
		});

		return messages;
	}

}
