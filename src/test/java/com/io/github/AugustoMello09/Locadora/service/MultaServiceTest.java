package com.io.github.AugustoMello09.Locadora.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
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
import com.io.github.AugustoMello09.Locadora.dto.MultaDTO;
import com.io.github.AugustoMello09.Locadora.dto.PagamentoComBoletoDTO;
import com.io.github.AugustoMello09.Locadora.dto.PagamentoComCartaoDTO;
import com.io.github.AugustoMello09.Locadora.dto.PagamentoComPixDTO;
import com.io.github.AugustoMello09.Locadora.entity.Multa;
import com.io.github.AugustoMello09.Locadora.entity.PagamentoComBoleto;
import com.io.github.AugustoMello09.Locadora.entity.PagamentoComCartao;
import com.io.github.AugustoMello09.Locadora.entity.PagamentoComPix;
import com.io.github.AugustoMello09.Locadora.repositories.MultaRepository;
import com.io.github.AugustoMello09.Locadora.repositories.PagamentoRepository;

@SpringBootTest
public class MultaServiceTest {

	private static final double PRESO = 19.0;

	private static final long ID = 1L;

	@Mock
	private MultaRepository repository;

	@Mock
	private PagamentoRepository pagamentoRepository;

	@InjectMocks
	private MultaService service;

	private Optional<Multa> optionalMulta;
	private Multa multa;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startMulta();
	}

	@Test
	public void whenFindByIdThenReturnMultaDTO() {
		when(repository.findById(anyLong())).thenReturn(optionalMulta);
		MultaDTO response = service.findById(ID);
		assertNotNull(response);
		assertEquals(MultaDTO.class, response.getClass());
		assertEquals(ID, response.getId());
		assertEquals(PRESO, response.getValor());
	}

	@Test
	public void whenFindByIdThenThrowObjectNotFoundException() {
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.findById(ID));
	}

	@Test
	void pagarMultaBoleto_WithValidMultaIdAndPositiveValor_ShouldSavePagamento() {
		Long multaId = ID;
		PagamentoComBoletoDTO pagamentoDTO = new PagamentoComBoletoDTO();
		pagamentoDTO.setValor(100.0);
		Multa multa = new Multa();
		multa.setId(multaId);
		multa.setValor(150.0);
		when(repository.findById(multaId)).thenReturn(Optional.of(multa));
		service.pagarMultaBoleto(multaId, pagamentoDTO);
		verify(pagamentoRepository, times(1)).save(any(PagamentoComBoleto.class));
	}

	@Test
	void pagarMultaCartao_WithInvalidMultaId_ShouldThrowObjectNotFoundException() {
		Long multaId = ID;
		PagamentoComCartaoDTO pagamentoDTO = new PagamentoComCartaoDTO();
		pagamentoDTO.setValor(100.0);
		when(repository.findById(multaId)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.pagarMultaCartao(multaId, pagamentoDTO));
	}

	@Test
	void pagarMultaCartao_WithValidMultaIdAndPositiveValor_ShouldSavePagamento() {
		Long multaId = ID;
		PagamentoComCartaoDTO pagamentoDTO = new PagamentoComCartaoDTO();
		pagamentoDTO.setValor(100.0);
		Multa multa = new Multa();
		multa.setId(multaId);
		multa.setValor(150.0);
		when(repository.findById(multaId)).thenReturn(Optional.of(multa));
		service.pagarMultaCartao(multaId, pagamentoDTO);
		verify(pagamentoRepository, times(1)).save(any(PagamentoComCartao.class));
	}

	@Test
	void pagarMultaPix_WithInvalidMultaId_ShouldThrowObjectNotFoundException() {
		Long multaId = ID;
		PagamentoComPixDTO pagamentoDTO = new PagamentoComPixDTO();
		pagamentoDTO.setValor(100.0);
		when(repository.findById(multaId)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.pagarMultaPix(multaId, pagamentoDTO));
	}

	@Test
	void pagarMultaPix_WithValidMultaIdAndPositiveValor_ShouldSavePagamento() {
		Long multaId = ID;
		PagamentoComPixDTO pagamentoDTO = new PagamentoComPixDTO();
		pagamentoDTO.setValor(100.0);
		Multa multa = new Multa();
		multa.setId(multaId);
		multa.setValor(150.0);
		when(repository.findById(multaId)).thenReturn(Optional.of(multa));
		service.pagarMultaPix(multaId, pagamentoDTO);
		verify(pagamentoRepository, times(1)).save(any(PagamentoComPix.class));
	}

	@Test
	void pagarMultaBoleto_WithInvalidMultaId_ShouldThrowObjectNotFoundException() {
		Long multaId = ID;
		PagamentoComBoletoDTO pagamentoDTO = new PagamentoComBoletoDTO();
		pagamentoDTO.setValor(100.0);
		when(repository.findById(multaId)).thenReturn(Optional.empty());
		assertThrows(ObjectNotFoundException.class, () -> service.pagarMultaBoleto(multaId, pagamentoDTO));
	}

	private void startMulta() {
		multa = new Multa(ID, PRESO, null);
		optionalMulta = Optional.of(multa);
	}
}
