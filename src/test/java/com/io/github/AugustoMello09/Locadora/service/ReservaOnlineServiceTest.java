package com.io.github.AugustoMello09.Locadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.ReservaOnlineDTO;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusReserva;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;
import com.io.github.AugustoMello09.Locadora.entity.ReservaOnline;
import com.io.github.AugustoMello09.Locadora.entity.User;
import com.io.github.AugustoMello09.Locadora.repositories.EstoqueRepository;
import com.io.github.AugustoMello09.Locadora.repositories.ReservaOnlineRepository;
import com.io.github.AugustoMello09.Locadora.repositories.UserRepository;

@SpringBootTest
public class ReservaOnlineServiceTest {

	private static final LocalDate DATA = LocalDate.now();

	private static final StatusReserva ATIVA = StatusReserva.ATIVA;

	private static final int QUANTIDADE = 1;

	private static final long ID = 1L;

	@Mock
	private ReservaOnlineRepository repository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private EstoqueRepository estoqueRepository;

	@InjectMocks
	private ReservaOnlineService service;
	

	private ReservaOnline reservaOnline;
	private Optional<ReservaOnline> optionalReservaOnline;
	
	

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startReservaOnline();
	}

	@Test
	public void whenFindByIdThenReturnReservaOnlineDTO() {
		when(repository.findById(anyLong())).thenReturn(optionalReservaOnline);
		ReservaOnlineDTO response = service.findReservaOnlineById(ID);
		assertNotNull(response);
		assertEquals(ReservaOnlineDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(QUANTIDADE, response.getQtdReservada());
		assertEquals(DATA, response.getDataReserva());
		assertEquals(ATIVA, response.getStatusReserva());
	}

	@Test
	public void whenFindReservaOnlineByIdThenThrowObjectNotFoundException() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findReservaOnlineById(ID));
	}

	@Test
	public void testCreate() {
		Long idUser = 1L;
		Long idEstoque = 2L;
		ReservaOnlineDTO objDto = new ReservaOnlineDTO();
		User user = new User();
		Estoque estoque = new Estoque();
		when(userRepository.findById(idUser)).thenReturn(Optional.of(user));
		when(estoqueRepository.findById(idEstoque)).thenReturn(Optional.of(estoque));
		ReservaOnlineDTO result = service.create(idUser, idEstoque, objDto);
		assertEquals(objDto.getId(), result.getId());
		assertEquals(objDto.getQtdReservada(), result.getQtdReservada());
		assertEquals(objDto.getDataReserva(), result.getDataReserva());
		verify(repository, times(1)).save(any(ReservaOnline.class));
	}

	@Test
	public void testCreate_UserNotFound() {
		Long idUser = 1L;
		Long idEstoque = 2L;
		ReservaOnlineDTO objDto = new ReservaOnlineDTO();
		when(userRepository.findById(idUser)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.create(idUser, idEstoque, objDto));
	}

	@Test
	public void testCreate_ReservaOnlineNotFound() {
		Long idUser = 1L;
		Long idEstoque = 2L;
		ReservaOnlineDTO objDto = new ReservaOnlineDTO();
		when(userRepository.findById(idUser)).thenReturn(Optional.of(new User()));
		when(estoqueRepository.findById(idEstoque)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.create(idUser, idEstoque, objDto));
	}

	@Test
	public void testCreate_QuantidadeExceeded() {
		Long idUser = 1L;
		Long idEstoque = 2L;
		ReservaOnlineDTO objDto = new ReservaOnlineDTO();
		objDto.setQtdReservada(10);
		User user = new User();
		Estoque estoque = new Estoque();
		estoque.setQuantidade(5);
		when(userRepository.findById(idUser)).thenReturn(Optional.of(user));
		when(estoqueRepository.findById(idEstoque)).thenReturn(Optional.of(estoque));
		assertThrows(DataIntegratyViolationException.class, () -> service.create(idUser, idEstoque, objDto));
	}

	@Test
	public void testCancelarReserva() {
	    Long id = ID;
	    Estoque estoque = new Estoque();
	    estoque.getReservasOnline().add(reservaOnline);
	    reservaOnline.setEstoque(estoque);
	    when(repository.findById(id)).thenReturn(Optional.of(reservaOnline));
	    service.cancelarReserva(id);
	    verify(repository, times(1)).findById(id);
	    verify(repository, times(1)).delete(reservaOnline);
	    assertFalse(estoque.getReservasOnline().contains(reservaOnline));
	}

	private void startReservaOnline() {
		reservaOnline = new ReservaOnline(ID, QUANTIDADE, DATA, null, null, ATIVA);
		optionalReservaOnline = Optional.of(reservaOnline);
	}

}
