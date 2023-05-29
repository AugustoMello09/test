package com.io.github.AugustoMello09.Locadora.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.LocacaoDTO;
import com.io.github.AugustoMello09.Locadora.entity.Estoque;
import com.io.github.AugustoMello09.Locadora.entity.Filme;
import com.io.github.AugustoMello09.Locadora.entity.Locacao;
import com.io.github.AugustoMello09.Locadora.entity.Multa;
import com.io.github.AugustoMello09.Locadora.entity.User;
import com.io.github.AugustoMello09.Locadora.repositories.EstoqueRepository;
import com.io.github.AugustoMello09.Locadora.repositories.FilmeRepository;
import com.io.github.AugustoMello09.Locadora.repositories.LocacaoRepository;
import com.io.github.AugustoMello09.Locadora.repositories.UserRepository;

@Service	
public class LocacaoService {
	
	@Autowired
	private LocacaoRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FilmeRepository filmeRepository;
	
	@Autowired
	private EstoqueRepository estoqueRepository;

	@Transactional
	public LocacaoDTO findById(Long id) {
		Optional<Locacao> locacao = repository.findById(id);
		Locacao entity = locacao.orElseThrow(()-> new ObjectNotFoundException("Locacao não encontrada"));
		return new LocacaoDTO(entity);
	}
	
	@Transactional
	public LocacaoDTO create(Long idUser, Long idFilme, LocacaoDTO obj) {
		User user = userRepository.findById(idUser).orElseThrow(
				()-> new ObjectNotFoundException("Usuário não encontrado"));
		Filme filme = filmeRepository.findById(idFilme).orElseThrow(
				()-> new ObjectNotFoundException("Filme não encontrado"));
		Locacao locacao = new Locacao();
		locacao.setUser(user);
		locacao.setFilme(filme);
		locacao.setQtd(obj.getQtd());
		locacao.setformaPamento(obj.getFormaPagamento());
		locacao.setDataLocacao(LocalDateTime.now());
		locacao.setDataDevolucao(null);
		locacao.setDataMaxDevolucao(locacao.getDataLocacao().plusDays(7));
		Estoque estoque = filme.getEstoque();
		estoque.setQtd(estoque.getQuantidade() - locacao.getQtd());
		filmeRepository.save(filme);
		repository.save(locacao);
		return new LocacaoDTO(locacao);
	}
	
	@Transactional
	public void devolver(Long idLocacao, LocacaoDTO dto) {
		Locacao entity = repository.findById(idLocacao).orElseThrow(
				()-> new ObjectNotFoundException("Locacao não encontrada"));
		LocalDateTime dataDevolucao = LocalDateTime.now().plusDays(8);
		entity.setDataDevolucao(dataDevolucao);
		LocalDateTime dataMaxDevolucao = entity.getDataMaxDevolucao();
        if (dataMaxDevolucao != null && dataDevolucao.isAfter(dataMaxDevolucao)) {
            Double multaValor = 100.0;
            Multa multa = new Multa();
            multa.setValor(multaValor);
            multa.setLocacao(entity);
            entity.getMultas().add(multa);
        }
		repository.save(entity);
		int quantidadeDevolvida = dto.getQtd();
		Filme filme = entity.getFilme();
		Estoque estoque = filme.getEstoque();
		estoque.setQtd(estoque.getQuantidade() + quantidadeDevolvida);
		estoqueRepository.save(estoque);	
	}

	
}
