package com.devgabriel.dglearn.dto;

import com.devgabriel.dglearn.services.validation.UserInsertValid;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@UserInsertValid
public class UserInsertDTO extends UserDTO {
	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Campo senha obrigatório")
	@Size(min = 3, max = 20, message = "Campo senha deve ter entre 3 e 20 caracteres")
	private String password;

	public UserInsertDTO() {
		super();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
