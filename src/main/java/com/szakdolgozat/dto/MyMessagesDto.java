package com.szakdolgozat.dto;

import com.szakdolgozat.entity.Message;
import com.szakdolgozat.entity.User;

public class MyMessagesDto {

	private User requester;
	private User otherPerson;
	private Message lastMessage;

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

	public Message getLastMessage() {
		return lastMessage;
	}

	public void setLastMessage(Message lastMessage) {
		this.lastMessage = lastMessage;
	}

}
