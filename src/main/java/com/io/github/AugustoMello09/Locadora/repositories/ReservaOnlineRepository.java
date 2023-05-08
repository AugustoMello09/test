package com.io.github.AugustoMello09.Locadora.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.io.github.AugustoMello09.Locadora.entities.Reserva;

@Repository
public interface ReservaOnlineRepository extends JpaRepository<Reserva, Long> {

}
