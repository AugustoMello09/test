package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import com.io.github.AugustoMello09.Locadora.entity.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserPagedDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String name;
	private String email;

	public UserPagedDTO(User entity) {
		this.name = entity.getName();
		this.email = entity.getEmail();
	}

}
