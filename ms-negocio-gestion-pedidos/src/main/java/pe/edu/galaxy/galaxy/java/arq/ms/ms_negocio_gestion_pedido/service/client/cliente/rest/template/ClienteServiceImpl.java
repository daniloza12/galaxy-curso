package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.service.client.cliente.rest.template;

import java.util.List;
import java.util.Objects;
import org.bouncycastle.crypto.RuntimeCryptoException;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import lombok.extern.slf4j.Slf4j;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.dto.ClienteDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.service.client.cliente.ClienteClientDTO;

import  static java.util.Objects.isNull;

@Slf4j
@Component
public class ClienteServiceImpl implements ClienteService {

	// Clasica - No aplica

	/*
	 * private String url="http://localhost:8082/v1/clientes/";
	 * 
	 * private final RestTemplate restTemplate;
	 * 
	 * 
	 * @Override public ClienteDTO findById(Long id) { ResponseEntity<ClienteDTO>
	 * clienteDTO= restTemplate.getForEntity(url+id, ClienteDTO.class);
	 * log.info("clienteDTO  ClienteServiceImpl {}",clienteDTO); return
	 * clienteDTO.getBody(); }
	 */

	// Optima
	private final RestTemplate restTemplate;

	private final DiscoveryClient discoveryClient; // discovery

	private final String ms_name = "ms-negocio-gestion-clientes"; // MS-NEGOCIO-GESTION-CLIENTES
	
	//Resillense
	private final CircuitBreaker circuitBreaker;

	private String url = "";

	public ClienteServiceImpl(RestTemplate restTemplate, 
			DiscoveryClient discoveryClient,
			CircuitBreakerFactory<?, ?> circuitBreakerFactory
			) {
		this.restTemplate = restTemplate;
		this.discoveryClient = discoveryClient;
		this.circuitBreaker = circuitBreakerFactory.create(ms_name);
		
	}

	/*
	@Override
	public ClienteClientDTO findById(Long id) {
		
		 //URI: http://localhost:8082
		  this.url=this.getURI()+"/v1/clientes/"+id;
		  
		  log.info("url {}",url);
		  
		  ResponseEntity<ClienteClientDTO> clienteDTO= restTemplate.getForEntity(url,ClienteClientDTO.class);
		  
		  log.info("clienteDTO  ClienteServiceImpl {}",clienteDTO); return
		  clienteDTO.getBody();
	}*/
	
	public ClienteDTO findById(Long id) {
		
		 //URI: http://localhost:8082
		/*
		  this.url=this.getURI()+"/v1/clientes/"+id;
		  
		  log.info("url {}",url);
		  
		  ResponseEntity<ClienteDTO> clienteDTO= restTemplate.getForEntity(url,ClienteDTO.class);
		  
		  log.info("clienteDTO  ClienteServiceImpl {}",clienteDTO); return
		  clienteDTO.getBody();
		  */
		return circuitBreaker.run(
				() -> {

					// Plan A
					this.url=this.getURI()+"/v1/clientes/"+id;
					ResponseEntity<ClienteDTO> clienteDTO = restTemplate.getForEntity(url, ClienteDTO.class);
		
					if (!isNull(clienteDTO)) {
						return clienteDTO.getBody();
					}
					return null;
				},
				// Plan B // Primer Error
				throwable -> getClienteDTO());
	}
	
	
	private ClienteDTO getClienteDTO() {
		 return ClienteDTO.builder().id(0L).razonSocial("Por definir").ruc("000-00000").direccion("").build();
	}
	
	private String getURI() throws RuntimeException{

		if (Objects.isNull(discoveryClient)) {
			log.info("discoveryClient is null");
			throw new RuntimeCryptoException("Error al obtener el DiscoveryClient");
		}

		List<ServiceInstance> instances = discoveryClient.getInstances(ms_name);

		if (Objects.isNull(instances) || instances.isEmpty()) {
			throw new RuntimeCryptoException("Error al obtener instancias");
		}

		log.info("instances.get(0).getHost() => {}", instances.get(0).getHost());

		String uri = instances.get(0).getUri().toString();
		log.info("uri => {}", uri);

		return uri;
	}

}
