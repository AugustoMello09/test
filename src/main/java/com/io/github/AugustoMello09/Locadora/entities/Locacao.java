package com.io.github.AugustoMello09.Locadora.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.io.github.AugustoMello09.Locadora.entities.pk.LocacaoPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name ="tb_locacao")
public class Locacao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private LocacaoPK id = new LocacaoPK();
	private LocalDateTime dataLocacao;
	private LocalDateTime dataDevolucao;

	public Locacao() {
	}

	public Locacao(User user, Filme filme, LocalDateTime dataLocacao, LocalDateTime dataDevolucao) {
		super();
		id.setUser(user);
		id.setFilme(filme);
		this.dataLocacao = dataLocacao;
		this.dataDevolucao = dataDevolucao;
	}

	public User getUser() {
		return id.getUser();
	}
	
	public void setUser(User user) {
		id.setUser(user);
	}
	
	public Filme getfilme() {
		return id.getFilme();
	}
	
	public void setfilme(Filme filme) {
		id.setFilme(filme);
	}

	public LocalDateTime getDataLocacao() {
		return dataLocacao;
	}

	public void setDataLocacao(LocalDateTime dataLocacao) {
		this.dataLocacao = dataLocacao;
	}

	public LocalDateTime getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(LocalDateTime dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

}
