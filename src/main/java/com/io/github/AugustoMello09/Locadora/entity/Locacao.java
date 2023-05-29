package com.io.github.AugustoMello09.Locadora.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.io.github.AugustoMello09.Locadora.entities.enums.FormaPagamento;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="tb_locacao")
public class Locacao implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int qtd;
	
	private Integer formaPagamento;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataLocacao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataDevolucao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataMaxDevolucao;
	
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	@ManyToOne
	@JoinColumn(name = "filme_id", referencedColumnName = "id")
	private Filme filme;

	@OneToMany(mappedBy = "locacao", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Multa> multas = new ArrayList<>();
	
	public Locacao() {
	}

	public Locacao(Long id, int qtd, LocalDateTime dataDevolucao, User user, Filme filme,
			FormaPagamento formaPagamento) {
		this.id = id;
		this.qtd = qtd;
		this.user = user;
		this.filme = filme;
		this.dataDevolucao = dataDevolucao;
		this.setDataLocacao(LocalDateTime.now());
		this.dataMaxDevolucao = this.dataLocacao.plusDays(7);
		this.formaPagamento = (formaPagamento == null) ? 2 : formaPagamento.getCod();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public List<Multa> getMultas() {
		return multas;
	}

	public void setMultas(List<Multa> multas) {
		this.multas = multas;
	}

	public LocalDateTime getDataMaxDevolucao() {
		return dataMaxDevolucao;
	}

	public void setDataMaxDevolucao(LocalDateTime dataMaxDevolucao) {
		this.dataMaxDevolucao = dataMaxDevolucao;
	}

	public FormaPagamento getformaPagamento() {
		return FormaPagamento.toEnum(this.formaPagamento);
	}

	public void setformaPamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento.getCod();
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locacao other = (Locacao) obj;
		return Objects.equals(id, other.id);
	}

}
