package com.io.github.AugustoMello09.Locadora.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.io.github.AugustoMello09.Locadora.entities.pk.LocacaoPK;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_locacao")
public class Locacao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@EmbeddedId
	private LocacaoPK id = new LocacaoPK();

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataLocacao;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataDevolucao;

	@OneToMany(mappedBy = "locacao")
	private List<Multa> multas = new ArrayList<>();

	@OneToMany(mappedBy = "locacao")
	private List<Pagamento> pagamentos = new ArrayList<>();

	public Locacao() {
	}

	public Locacao(User user, Filme filme, LocalDateTime dataLocacao) {
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

	public List<Multa> getMultas() {
		return multas;
	}

	public void setMultas(List<Multa> multas) {
		this.multas = multas;
	}

	public List<Pagamento> getPagamentos() {
		return pagamentos;
	}

	public void setPagamentos(List<Pagamento> pagamentos) {
		this.pagamentos = pagamentos;
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
