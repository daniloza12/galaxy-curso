
package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.service.client.feing;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.dto.ClienteDTO;

@Component
public class ClienteFeingServiceClient{

	private final ClienteFeingService clienteFeingService;
	
	private final ClienteFeingServiceBackupOne clienteFeingServiceBackupOne;

	private Long id;

	public ClienteFeingServiceClient(ClienteFeingService clienteFeingService,
			final ClienteFeingServiceBackupOne clienteFeingServiceBackupOne) {
		this.clienteFeingService = clienteFeingService;
		this.clienteFeingServiceBackupOne = clienteFeingServiceBackupOne;
	}

	@CircuitBreaker(name = "findById", fallbackMethod = "findByIdFallback")
	public ClienteDTO findById(@PathVariable(value = "id") Long id) {
		this.id = id;
		return clienteFeingService.findById(id).getBody();
	};


	@CircuitBreaker(name = "findByIdFallback", fallbackMethod = "findByIdFallbackMemory")
	public ClienteDTO findByIdFallback(Exception exception) { 
		return clienteFeingServiceBackupOne.findById(id);
	}
	
	public ClienteDTO findByIdFallbackMemory(Exception exception) { 
		return ClienteDTO.builder().id(99999L).razonSocial("Razon social demo - Feing - One").ruc("Sin ruc").build();
	}

}
