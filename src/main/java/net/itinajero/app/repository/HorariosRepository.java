package net.itinajero.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.itinajero.app.model.Horario;

public interface HorariosRepository extends JpaRepository<Horario, Integer> {

}
