package com.io.github.AugustoMello09.Locadora.dto;

import java.io.Serializable;

import com.io.github.AugustoMello09.Locadora.entity.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String authority;

	public RoleDTO() {
	}
	
	public RoleDTO(Role entity) {
		this.id = entity.getId();
		this.authority = entity.getAuthority();
	}
	
}
