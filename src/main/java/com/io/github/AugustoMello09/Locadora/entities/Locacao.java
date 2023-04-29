package com.io.github.AugustoMello09.Locadora.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
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
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataLocacao;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataDevolucao;

	public Locacao() {
	}

	public Locacao(User user, Filme filme, LocalDateTime dataLocacao, LocalDateTime dataDevolucao) {
		id.setUser(user);
		id.setFilme(filme);
		this.setDataLocacao(LocalDateTime.now());
		this.dataDevolucao = this.dataLocacao.plusDays(7);
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
