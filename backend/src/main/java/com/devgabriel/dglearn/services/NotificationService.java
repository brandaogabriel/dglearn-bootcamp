package com.devgabriel.dglearn.services;

import com.devgabriel.dglearn.dto.NotificationDTO;
import com.devgabriel.dglearn.entities.Deliver;
import com.devgabriel.dglearn.entities.Notification;
import com.devgabriel.dglearn.entities.User;
import com.devgabriel.dglearn.observers.DeliverRevisionObserver;
import com.devgabriel.dglearn.repositories.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.Instant;

@Service
public class NotificationService implements DeliverRevisionObserver {

	@Autowired
	private NotificationRepository repository;

	@Autowired
	private DeliverService deliverService;

	@Autowired
	private AuthService authService;

	@PostConstruct
	private void initialize() {
		deliverService.subscribeDeliverRevisionObserver(this);
	}

	@Transactional(readOnly = true)
	public Page<NotificationDTO> notificationsForCurrentUser(boolean unreadOnly, Pageable pageable) {
		User user = authService.authenticated();
		Page<Notification> page = repository.find(user, unreadOnly, pageable);
		return page.map(NotificationDTO::new);
	}

	@Transactional
	public void saveDeliverNotification(Deliver deliver) {
		Long sectionId= deliver.getLesson().getSection().getId();
		Long resourceId = deliver.getLesson().getSection().getResource().getId();
		Long offerId = deliver.getEnrollment().getOffer().getId();
		String route = "/offers/" + offerId + "/resources/" + resourceId + "/sections/" + sectionId;

		User user = deliver.getEnrollment().getStudent();
		Notification notification = new Notification(null, deliver.getFeedback(), Instant.now(), false, route, user);
		repository.save(notification);
	}

	@Override
	public void onSaveRevision(Deliver deliver) {
		saveDeliverNotification(deliver);
	}
}
