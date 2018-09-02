package net.itinajero.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.itinajero.app.model.Noticia;
import net.itinajero.app.repository.NoticiasRepository;

@Service
public class NoticiasServiceJPA  implements INoticiasService{

	@Autowired
	private NoticiasRepository noticiasRepo;
	
	@Override
	public void guardar(Noticia noticia) {
		
		noticiasRepo.save(noticia);
		
	}

	@Override
	public List<Noticia> buscarUltimas() {
		List<Noticia> noticias = noticiasRepo.findTop3ByEstatusOrderByIdDesc("Activa");		
		return noticias;
	}

}
