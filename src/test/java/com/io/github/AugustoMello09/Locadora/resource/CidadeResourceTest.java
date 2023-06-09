package com.io.github.AugustoMello09.Locadora.resource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.io.github.AugustoMello09.Locadora.dto.CidadeDTO;
import com.io.github.AugustoMello09.Locadora.dto.EstadoDTO;
import com.io.github.AugustoMello09.Locadora.resources.CidadeResource;
import com.io.github.AugustoMello09.Locadora.service.CidadeService;

@SpringBootTest
public class CidadeResourceTest {

	private static final String ESTADO = "As";

	private static final String CIDADE = "São";

	private static final long ID = 1L;

	private CidadeDTO cidadeDTO;

	private EstadoDTO estadoDTO;

	@Mock
	private CidadeService service;

	@InjectMocks
	private CidadeResource resource;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		startCidade();
	}

	@Test
	void whenFindByIdThenReturnSuccess() {
		when(service.findById(anyLong())).thenReturn(cidadeDTO);
		ResponseEntity<CidadeDTO> response = resource.findById(ID);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(CidadeDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(CIDADE, response.getBody().getName());
		assertNotNull(response.getBody().getEstado());
		assertEquals(ID, response.getBody().getEstado().getId());
		assertEquals(ESTADO, response.getBody().getEstado().getName());
	}

	@Test
	public void testCreate() {
		Long estadoId = ID;
		when(service.create(cidadeDTO, estadoId)).thenReturn(cidadeDTO);
		ResponseEntity<CidadeDTO> response = resource.create(estadoId, cidadeDTO);
		assertNotNull(response);
		assertNotNull(response.getBody());
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(ResponseEntity.class, response.getClass());
		assertEquals(CidadeDTO.class, response.getBody().getClass());
		assertEquals(ID, response.getBody().getId());
		assertEquals(CIDADE, response.getBody().getName());
		assertNotNull(response.getBody().getEstado());
		assertEquals(ID, response.getBody().getEstado().getId());
		assertEquals(ESTADO, response.getBody().getEstado().getName());
		verify(service).create(cidadeDTO, estadoId);
	}

	@Test
	public void testDelete() {
		doNothing().when(service).delete(anyLong());
		ResponseEntity<Void> response = resource.delete(ID);
		assertNotNull(response);
		assertEquals(ResponseEntity.class, response.getClass());
		verify(service, times(1)).delete(anyLong());
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	private void startCidade() {
		estadoDTO = new EstadoDTO(ID, ESTADO);
		cidadeDTO = new CidadeDTO(ID, CIDADE, estadoDTO);
	}
}
