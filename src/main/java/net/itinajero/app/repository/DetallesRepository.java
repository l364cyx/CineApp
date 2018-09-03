package net.itinajero.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.itinajero.app.model.Detalle;
@Repository
public interface DetallesRepository extends JpaRepository<Detalle, Integer> {

}
