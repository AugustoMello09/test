package com.io.github.AugustoMello09.Locadora.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.io.github.AugustoMello09.Locadora.Services.exception.DataIntegratyViolationException;
import com.io.github.AugustoMello09.Locadora.Services.exception.ObjectNotFoundException;
import com.io.github.AugustoMello09.Locadora.entities.Cidade;
import com.io.github.AugustoMello09.Locadora.entities.Endereco;
import com.io.github.AugustoMello09.Locadora.entities.User;
import com.io.github.AugustoMello09.Locadora.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	@Autowired
	private UserService userService;

	@Autowired
	private CidadeService cidadeService;

	@Transactional(readOnly = true)
	public Endereco findById(Long id) {
		Optional<Endereco> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Endereço não encontrada"));
	}
	
	@Transactional
	public Endereco create(Endereco end, Long idUser, Long idCidade) {
		User user = userService.findById(idUser);
		Cidade cid = cidadeService.findById(idCidade);
		Endereco endereco = new Endereco();
		endereco.setLogradouro(end.getLogradouro());
		endereco.setNumero(end.getNumero());
		endereco.setComplemento(end.getComplemento());
		endereco.setBairro(end.getBairro());
		endereco.setCep(end.getBairro());
		endereco.setUser(user);
		endereco.setCidade(cid);
		return repository.save(endereco);
	}

	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegratyViolationException("Não pode deletar Endereço associado com usuário");
		}
	}

}
