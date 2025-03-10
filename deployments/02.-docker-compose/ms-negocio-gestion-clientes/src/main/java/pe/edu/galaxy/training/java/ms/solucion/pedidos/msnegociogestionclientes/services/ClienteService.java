package pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.services;

import java.util.Optional;

import pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.dto.ClienteDTO;

public interface ClienteService extends GenericService<ClienteDTO>{
	
	Optional<ClienteDTO> findByRuc (String ruc) throws ServiceException;

}
