package net.itinajero.app.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import net.itinajero.app.model.Horario;
import net.itinajero.app.repository.HorariosRepository;

// Registramos esta clase como un Bean en nuestro Root ApplicationContext.
@Service
public class HorariosServiceJPA implements IHorariosService{
	
	// Inyectamos una instancia desde nuestro Root ApplicationContext.
    @Autowired
	private HorariosRepository horariosRepo;
	
	@Override
	public List<Horario> buscarPorIdPelicula(int idPelicula, Date fecha) {
		return horariosRepo.findByPelicula_IdAndFechaOrderByHora(idPelicula,fecha);		
	}

	@Override
	public void insertar(Horario horario) {
		horariosRepo.save(horario);
	}

	@Override
	public List<Horario> buscarTodos() {
		return horariosRepo.findAll();
	}
	
	@Override
	public Page<Horario> buscarTodos(Pageable page) {
		return horariosRepo.findAll(page);
	}

	@Override
	public void eliminar(int idHorario) {
		// horariosRepo.delete(idHorario); // Spring 4.3		
		horariosRepo.deleteById(idHorario);
	}

	@Override
	public Horario buscarPorId(int idHorario) {
		Optional<Horario> optional = horariosRepo.findById(idHorario);
		if (optional.isPresent())
			return optional.get();
		return null;
	}
}
