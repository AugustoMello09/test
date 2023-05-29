package com.io.github.AugustoMello09.Locadora.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.dto.EnderecoDTO;
import com.io.github.AugustoMello09.Locadora.entity.Cidade;
import com.io.github.AugustoMello09.Locadora.entity.Endereco;
import com.io.github.AugustoMello09.Locadora.entity.User;
import com.io.github.AugustoMello09.Locadora.repositories.CidadeRepository;
import com.io.github.AugustoMello09.Locadora.repositories.EnderecoRepository;
import com.io.github.AugustoMello09.Locadora.repositories.UserRepository;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Transactional
	public EnderecoDTO findById(Long id) {
		Optional<Endereco> end = repository.findById(id);
		Endereco entity = end.orElseThrow(()-> new ObjectNotFoundException("Endereço Não encontrada"));
		return new EnderecoDTO(entity);
	}
	
	@Transactional
	public EnderecoDTO create(EnderecoDTO end, Long idUser, Long idCidade) {
		User user = userRepository.findById(idUser).orElseThrow(
				()-> new ObjectNotFoundException("Usuário Não encontrado"));
		Cidade cid = cidadeRepository.findById(idCidade).orElseThrow(
				()-> new ObjectNotFoundException("Cidade Não encontrada"));
		Endereco endereco = new Endereco();
		endereco.setId(end.getId());
		endereco.setLogradouro(end.getLogradouro());
		endereco.setNumero(end.getNumero());
		endereco.setComplemento(end.getComplemento());
		endereco.setBairro(end.getBairro());
		endereco.setCep(end.getCep());
		endereco.setUser(user);
		endereco.setCidade(cid);
		repository.save(endereco);
		return new EnderecoDTO(endereco);
	}

}
