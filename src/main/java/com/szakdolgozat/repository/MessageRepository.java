package com.szakdolgozat.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.szakdolgozat.entity.Message;
import com.szakdolgozat.interfaces.GetOtherPersonId;

public interface MessageRepository extends JpaRepository<Message, Long> {

	@Query(value = "select distinct employer_id as id from messages where user_id=?1", nativeQuery = true)
	public Collection<GetOtherPersonId> findDistinctEmployerIdByUserId(Long id);

	@Query(value = "select distinct user_id as id from messages where employer_id=?1", nativeQuery = true)
	public Collection<GetOtherPersonId> findDistinctUserIdByEmployerId(Long id);

	public List<Message> findAllByUserId(Long Id);

	public List<Message> findAllByEmployerId(Long Id);

	public List<Message> findAllByUserIdAndEmployerIdOrderByDateOfSendingDesc(Long userId, Long employerId);

	public Message findFirstByUserIdAndEmployerIdOrderByDateOfSendingDesc(Long userId, Long employerId);

}
