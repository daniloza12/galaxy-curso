package pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.mappers;

import java.util.List;
import pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.dto.ClienteDTO;
import pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.entity.ClienteEntity;

public interface ClienteMapper {

	ClienteDTO toDTO(ClienteEntity e);
	
	ClienteEntity toEntity(ClienteDTO d);
	
	List<ClienteDTO> toDTO(List<ClienteEntity>lst);
	
}
