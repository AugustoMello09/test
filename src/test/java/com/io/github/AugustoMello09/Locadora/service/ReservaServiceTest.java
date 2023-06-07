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
import com.io.github.AugustoMello09.Locadora.dto.ReservaDTO;
import com.io.github.AugustoMello09.Locadora.entities.enums.StatusReserva;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;
import com.io.github.AugustoMello09.Locadora.entity.Reserva;
import com.io.github.AugustoMello09.Locadora.entity.User;
import com.io.github.AugustoMello09.Locadora.repositories.EstoqueRepository;
import com.io.github.AugustoMello09.Locadora.repositories.ReservaRepository;
import com.io.github.AugustoMello09.Locadora.repositories.UserRepository;

@SpringBootTest
public class ReservaServiceTest {

	private static final LocalDate DATA = LocalDate.now();

	private static final StatusReserva ATIVA = StatusReserva.ATIVA;

	private static final int QUANTIDADE = 1;

	private static final long ID = 1L;

	@Mock
	private ReservaRepository repository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private EstoqueRepository estoqueRepository;

	@InjectMocks
	private ReservaService service;
	
	private Reserva reserva;
	private Optional<Reserva> optionalReserva;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startReserva();
	}

	@Test
	public void whenFindByIdThenReturnReservaDTO() {
		when(repository.findById(anyLong())).thenReturn(optionalReserva);
		ReservaDTO response = service.findReservaById(ID);
		assertNotNull(response);
		assertEquals(ReservaDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(QUANTIDADE, response.getQtdReservada());
		assertEquals(DATA, response.getDataReserva());
		assertEquals(ATIVA, response.getStatusReserva());
	}

	@Test
	public void whenFindReservaByIdThenThrowObjectNotFoundException() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findReservaById(ID));
	}

	@Test
	public void testCreate() {
		Long idUser = ID;
		Long idEstoque = 2L;
		ReservaDTO objDto = new ReservaDTO();
		User user = new User();
		Estoque estoque = new Estoque();
		when(userRepository.findById(idUser)).thenReturn(Optional.of(user));
		when(estoqueRepository.findById(idEstoque)).thenReturn(Optional.of(estoque));
		ReservaDTO result = service.create(idUser, idEstoque, objDto);
		assertEquals(objDto.getId(), result.getId());
		assertEquals(objDto.getQtdReservada(), result.getQtdReservada());
		assertEquals(objDto.getDataReserva(), result.getDataReserva());
		verify(repository, times(1)).save(any(Reserva.class));
	}

	@Test
	public void testCreate_UserNotFound() {
		Long idUser = 1L;
		Long idEstoque = 2L;
		ReservaDTO objDto = new ReservaDTO();
		when(userRepository.findById(idUser)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.create(idUser, idEstoque, objDto));
	}

	@Test
	public void testCreate_ReservaNotFound() {
		Long idUser = 1L;
		Long idEstoque = 2L;
		ReservaDTO objDto = new ReservaDTO();
		when(userRepository.findById(idUser)).thenReturn(Optional.of(new User()));
		when(estoqueRepository.findById(idEstoque)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.create(idUser, idEstoque, objDto));
	}

	@Test
	public void testCreate_QuantidadeExceeded() {
		Long idUser = 1L;
		Long idEstoque = 2L;
		ReservaDTO objDto = new ReservaDTO();
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
	    Reserva reserva = new Reserva();
	    Estoque estoque = new Estoque();
	    estoque.getReservas().add(reserva);
	    reserva.setEstoque(estoque);
	    when(repository.findById(id)).thenReturn(Optional.of(reserva));
	    service.cancelarReserva(id);
	    verify(repository, times(1)).findById(id);
	    verify(repository, times(1)).delete(reserva);
	    assertFalse(estoque.getReservas().contains(reserva));
	}

	private void startReserva() {
		reserva = new Reserva(ID, QUANTIDADE, DATA, null, null, ATIVA);
		optionalReserva = Optional.of(reserva);
	}
}
