package pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.dto.ClienteDTO;
import pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.services.ClienteService;
import pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.services.ServiceException;

import static java.util.Objects.isNull;

@RestController
@RequestMapping("api/v1/clientes")
public class ClienteController {

	private final ClienteService clienteService;
	
	public ClienteController(ClienteService clienteService) {
		super();
		this.clienteService = clienteService;
	}

	@GetMapping
	public ResponseEntity<?> findAll() throws ServiceException {
		List<ClienteDTO> clientees = clienteService.findByLikeObject(ClienteDTO.builder().razonSocial("").build());
		if (clientees.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(clientees);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) throws ServiceException {
		Optional<ClienteDTO> optClienteDO = clienteService.findById(id);

		if (optClienteDO.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(optClienteDO.get());

	}
	
	@GetMapping("/by-razonSocial") 
	public ResponseEntity<?> findByRazonSocial(@RequestParam("razonSocial") String razonSocial) throws ServiceException {
		List<ClienteDTO> clientees = clienteService.findByLikeObject(ClienteDTO.builder().razonSocial(razonSocial).build());
		if (clientees.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(clientees);
	}
	
	@GetMapping("/by-ruc") 
	public ResponseEntity<?> findByRuc(@RequestParam("ruc") String ruc) throws ServiceException {
		Optional<ClienteDTO> optCliente = clienteService.findByRuc(ruc);
		if (optCliente.isEmpty() || optCliente.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(optCliente.get());
	}

	@PostMapping
	public ResponseEntity<?> save(@RequestBody ClienteDTO clienteDTO) throws ServiceException {
		ClienteDTO retClienteDTO = clienteService.save(clienteDTO);
		
		if (isNull(retClienteDTO)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(retClienteDTO); 
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id, @RequestBody ClienteDTO clienteDTO)
			throws ServiceException {
		clienteDTO.setId(id);
		ClienteDTO retClienteDTO = clienteService.update(clienteDTO);
		if (isNull(retClienteDTO)) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.status(HttpStatus.OK).body(retClienteDTO);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteLogic(@PathVariable("id") Long id) throws ServiceException {
		clienteService.delete(id);
		return ResponseEntity.ok().build();
	}
}