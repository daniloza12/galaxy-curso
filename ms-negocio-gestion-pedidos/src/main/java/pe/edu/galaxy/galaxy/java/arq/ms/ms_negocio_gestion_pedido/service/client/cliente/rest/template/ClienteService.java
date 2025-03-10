package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.service.client.cliente.rest.template;

import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.dto.ClienteDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.service.client.cliente.ClienteClientDTO;

public interface ClienteService {

	//ClienteClientDTO findById(Long id);
	ClienteDTO findById(Long id);
}
