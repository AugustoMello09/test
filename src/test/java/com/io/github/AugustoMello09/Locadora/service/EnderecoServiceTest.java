package com.io.github.AugustoMello09.Locadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.CidadeDTO;
import com.io.github.AugustoMello09.Locadora.dto.EnderecoDTO;
import com.io.github.AugustoMello09.Locadora.entity.Cidade;
import com.io.github.AugustoMello09.Locadora.entity.Endereco;
import com.io.github.AugustoMello09.Locadora.entity.Estado;
import com.io.github.AugustoMello09.Locadora.entity.User;
import com.io.github.AugustoMello09.Locadora.repositories.CidadeRepository;
import com.io.github.AugustoMello09.Locadora.repositories.EnderecoRepository;
import com.io.github.AugustoMello09.Locadora.repositories.UserRepository;

@SpringBootTest
public class EnderecoServiceTest {

	private static final String CEP = "38777012";

	private static final String BAIRRO = "Centro";

	private static final String COMPLEMENTO = "Sala 800";

	private static final String NUMERO = "105";

	private static final String RUA = "Avenida Matos";

	private static final String CIDADE = "Assis";

	private static final String ESTADO = "São Paulo";

	private static final String CPF = "123.123.123-78";

	private static final String EMAIL = "Teste@gmail.com";

	private static final String NOME = "José";

	private static final long ID = 1L;

	@Mock
	private EnderecoRepository repository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private CidadeRepository cidadeRepository;

	@InjectMocks
	private EnderecoService service;

	private Optional<Endereco> optionalEndereco;
	private Endereco endereco;

	private CidadeDTO cidadeDTO;

	private Cidade cidade;

	private Estado estado;

	private User user;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startEndereco();
	}

	@Test
	void whenFindByIdThenReturnEnderecoDTO() {
		when(repository.findById(anyLong())).thenReturn(optionalEndereco);
		EnderecoDTO response = service.findById(ID);
		assertNotNull(response);
		assertEquals(EnderecoDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(RUA, response.getLogradouro());
		assertEquals(NUMERO, response.getNumero());
		assertEquals(COMPLEMENTO, response.getComplemento());
		assertEquals(BAIRRO, response.getBairro());
		assertNotNull(response.getCidade());
		assertEquals(ID, response.getCidade().getId());
		assertEquals(CIDADE, response.getCidade().getName());
		assertNotNull(response.getCidade().getEstado());
		assertEquals(ID, response.getCidade().getEstado().getId());
		assertEquals(ESTADO, response.getCidade().getEstado().getName());
	}

	@Test
	void whenFindByIdThenThrowObjectNotFoundException() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findById(ID));
	}

	@Test
	void whenCreateEnderecoThenReturnEnderecoDTO() {
		Long cidadeId = ID;
		Long userId = ID;
		when(cidadeRepository.findById(anyLong())).thenReturn(Optional.of(cidade));
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(user));
		EnderecoDTO enderecoDTO = new EnderecoDTO(ID, RUA, NUMERO, COMPLEMENTO, BAIRRO, CEP, cidadeDTO);
		enderecoDTO.setCidade(cidadeDTO);
		when(repository.save(any(Endereco.class))).thenAnswer(invocation -> {
			Endereco savedEndereco = invocation.getArgument(0);
			savedEndereco.setId(ID);
			return savedEndereco;
		});
		EnderecoDTO response = service.create(enderecoDTO, userId, cidadeId);
		assertNotNull(response);
		assertEquals(EnderecoDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(RUA, response.getLogradouro());
		assertEquals(NUMERO, response.getNumero());
		assertEquals(COMPLEMENTO, response.getComplemento());
		assertEquals(BAIRRO, response.getBairro());
		assertEquals(CEP, response.getCep());
		assertNotNull(response.getCidade());
		assertEquals(cidadeId, response.getCidade().getId());
		assertEquals(CIDADE, response.getCidade().getName());
		verify(cidadeRepository).findById(cidadeId);
		verify(userRepository).findById(userId);
		verify(repository).save(any(Endereco.class));
	}

	private void startEndereco() {
		user = new User(ID, NOME, EMAIL, CPF);
		estado = new Estado(ID, ESTADO);
		cidade = new Cidade(ID, CIDADE, estado);
		endereco = new Endereco(ID, RUA, NUMERO, COMPLEMENTO, BAIRRO, CEP, cidade, user);
		optionalEndereco = Optional.of(endereco);
	}
}
