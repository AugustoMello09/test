package com.io.github.AugustoMello09.Locadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.RoleDTO;
import com.io.github.AugustoMello09.Locadora.dto.UserDTO;
import com.io.github.AugustoMello09.Locadora.dto.UserDTOUpdate;
import com.io.github.AugustoMello09.Locadora.dto.UserPagedDTO;
import com.io.github.AugustoMello09.Locadora.entity.Role;
import com.io.github.AugustoMello09.Locadora.entity.User;
import com.io.github.AugustoMello09.Locadora.repositories.RoleRepository;
import com.io.github.AugustoMello09.Locadora.repositories.UserRepository;

@SpringBootTest
public class UserServiceTest {

	private static final String AUT = "AUT";

	private static final String CPF = "123.123.123-78";

	private static final String EMAIL = "Teste@gmail.com";

	private static final String NOME = "José";

	private static final long ID = 1L;

	@Mock
	private UserRepository repository;

	@Mock
	private RoleRepository roleRepository;

	@InjectMocks
	private UserService service;

	private User user;
	private UserDTO userDTO;
	private Optional<User> optionalUser;

	private Optional<Role> optionalRole;
	private Role role;
	private RoleDTO roleDTO;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startUser();
	}

	@Test
	void whenFindByIdThenReturnUserDTO() {
		when(repository.findById(anyLong())).thenReturn(optionalUser);
		UserDTO response = service.findById(ID);
		assertNotNull(response);
		assertEquals(UserDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(CPF, response.getCpf());

	}

	@Test
	void whenFindByIdThenThrowObjectNotFoundException() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findById(ID));
	}

	@Test
	void whenFindAllPagedThenReturnPageOfUserDTO() {
		List<User> users = Arrays.asList(new User(ID, NOME, EMAIL, CPF),
				new User(2L, "a", "email@aaa.com", "123.456.456.48"));
		Page<User> usersPage = new PageImpl<>(users);
		when(repository.findAll(any(Pageable.class))).thenReturn(usersPage);
		Page<UserPagedDTO> result = service.findAllPaged(PageRequest.of(0, 5));
		assertNotNull(result);
		assertEquals(2, result.getTotalElements());
		assertEquals(2, result.getContent().size());
		for (UserPagedDTO userDTO : result) {
			assertEquals(UserPagedDTO.class, userDTO.getClass());
		}
	}

	@Test
	void whenFindAllPagedThenReturnEmptyPage() {
		Page<User> userPage = new PageImpl<>(Collections.emptyList());
		when(repository.findAll(any(Pageable.class))).thenReturn(userPage);
		Page<UserPagedDTO> result = service.findAllPaged(PageRequest.of(0, 10));
		assertNotNull(result);
		assertTrue(result.isEmpty());
	}

	@Test
	public void testCreate() {
		when(repository.save(any(User.class))).thenReturn(user);
		when(roleRepository.findById(anyLong())).thenReturn(optionalRole);
		UserDTO response = service.create(userDTO);
		assertNotNull(response);
		assertEquals(ID, response.getId());
		assertEquals(NOME, response.getName());
		assertEquals(EMAIL, response.getEmail());
		assertEquals(CPF, response.getCpf());
		assertNotNull(response.getRoles());
		verify(repository, times(1)).save(any(User.class));
		verify(roleRepository, times(1)).findById(ID);

	}

	@Test
	void whenUpdateUserThenReturnUserDTO() {
		Long id = 1L;
		UserDTOUpdate objDto = new UserDTOUpdate("Novo Nome", "novoemail@gmail.com");
		User user = new User(id, "Nome Antigo", "emailantigo@gmail.com", "123456789");
		when(repository.findById(id)).thenReturn(Optional.of(user));
		when(repository.save(any(User.class))).thenReturn(user);
		UserDTO result = service.update(objDto, id);
		assertNotNull(result);
		assertEquals(id, result.getId());
		assertEquals(objDto.getName(), result.getName());
		assertEquals(objDto.getEmail(), result.getEmail());
		verify(repository, times(1)).findById(id);
		verify(repository, times(1)).save(any(User.class));
	}

	@Test
	void whenDeleteUserWithAssociatedDataThenThrowDataIntegrityViolationException() {
		Long id = ID;
		when(repository.findById(id)).thenReturn(optionalUser);
		doThrow(DataIntegrityViolationException.class).when(repository).deleteById(ID);
		assertThrows(DataIntegratyViolationException.class, () -> service.delete(ID));
	}

	@Test
	void whenDeleteUserThenDeleteSuccessfully() {
		Long userId = ID;
		when(repository.findById(userId)).thenReturn(optionalUser);
		service.delete(userId);
		verify(repository).findById(userId);
		verify(repository).deleteById(userId);
	}

	private void startUser() {

		roleDTO = new RoleDTO(ID, AUT);
		userDTO = new UserDTO(ID, NOME, EMAIL, CPF, null, new HashSet<>());
		userDTO.getRoles().add(roleDTO);
		role = new Role(ID, AUT);
		user = new User(ID, NOME, EMAIL, CPF);
		user.setRoles(new HashSet<>()); // Initialize the roles set in the User object
		user.getRoles().add(role); // Add the role to the user's roles set
		optionalUser = Optional.of(user);
		optionalRole = Optional.of(role);
	}
}
