package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import com.io.github.AugustoMello09.Locadora.entity.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserInsertDTO  extends UserDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String password;
	
	public UserInsertDTO(User entity) {
		password = entity.getPassword();
	}
}
