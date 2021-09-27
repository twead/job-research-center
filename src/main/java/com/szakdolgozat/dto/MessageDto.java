package com.szakdolgozat.dto;

import java.util.List;

import com.szakdolgozat.entity.Message;
import com.szakdolgozat.entity.User;

public class MessageDto {

	private User requester;
	private User otherPerson;
	private List<Message> messages;

	public User getRequester() {
		return requester;
	}

	public void setRequester(User requester) {
		this.requester = requester;
	}

	public User getOtherPerson() {
		return otherPerson;
	}

	public void setOtherPerson(User otherPerson) {
		this.otherPerson = otherPerson;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
