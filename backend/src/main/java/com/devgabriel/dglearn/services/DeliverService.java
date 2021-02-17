package com.devgabriel.dglearn.services;

import com.devgabriel.dglearn.dto.DeliverRevisionDTO;
import com.devgabriel.dglearn.entities.Deliver;
import com.devgabriel.dglearn.repositories.DeliverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class DeliverService {

	@Autowired
	private DeliverRepository repository;


	@PreAuthorize("hasAnyRole('INSTRUCTOR', 'ADMIN')")
	@Transactional
	public void saveRevision(Long id, DeliverRevisionDTO dto) {
		Deliver deliver = repository.getOne(id);
		deliver.setStatus(dto.getStatus());
		deliver.setFeedback(dto.getFeedback());
		deliver.setCorrectCount(dto.getCorrectCount());
		repository.save(deliver);
	}

}
