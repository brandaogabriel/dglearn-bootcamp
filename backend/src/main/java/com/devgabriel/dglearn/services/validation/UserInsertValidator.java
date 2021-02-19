package com.devgabriel.dglearn.services.validation;

import com.devgabriel.dglearn.dto.UserInsertDTO;
import com.devgabriel.dglearn.entities.User;
import com.devgabriel.dglearn.repositories.UserRepository;
import com.devgabriel.dglearn.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class UserInsertValidator implements ConstraintValidator<UserInsertValid, UserInsertDTO> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(UserInsertValid ann) {
	}

	@Override
	public boolean isValid(UserInsertDTO dto, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		User user = userRepository.findByEmail(dto.getEmail());
		if (user != null)
			list.add(new FieldMessage("email", "E-mail já existe"));

		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}
