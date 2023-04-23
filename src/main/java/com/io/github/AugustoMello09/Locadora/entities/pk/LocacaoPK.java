package com.io.github.AugustoMello09.Locadora.entities.pk;

import java.io.Serializable;
import java.util.Objects;

import com.io.github.AugustoMello09.Locadora.entities.Filme;
import com.io.github.AugustoMello09.Locadora.entities.User;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Embeddable
public class LocacaoPK implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ManyToOne
	@JoinColumn(name ="user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name ="filme_id")
	private Filme filme;

	public LocacaoPK() {
	}

	public LocacaoPK(User user, Filme filme) {
		super();
		this.user = user;
		this.filme = filme;
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

	@Override
	public int hashCode() {
		return Objects.hash(filme, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocacaoPK other = (LocacaoPK) obj;
		return Objects.equals(filme, other.filme) && Objects.equals(user, other.user);
	}

}
