package pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.dto.ClienteDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.dto.PedidoDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.entity.PedidoEntity;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.mapper.PedidoMapper;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.respository.PedidoRepository;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.service.client.cliente.ClienteClientDTO;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.service.client.cliente.rest.template.ClienteService;
import pe.edu.galaxy.galaxy.java.arq.ms.ms_negocio_gestion_pedido.service.client.feing.ClienteFeingServiceClient;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class PedidoServiceImpl implements PedidoService {

	private final PedidoRepository pedidoRepository;
	private final PedidoMapper pedidoMapper;
	private final ClienteService clienteService;
	//private final ClienteFeingServiceClient clienteService; 

	@Override
	public Optional<PedidoDTO> findById(Long id) throws PedidoException {
		String msg = String.format("No existe pedido con id => {}", id);
		PedidoEntity pedidoEntity = this.pedidoRepository.findById(id).orElseThrow(() -> new PedidoException(msg));
		PedidoDTO pedidoDTO = pedidoMapper.toDTO(pedidoEntity);
		// Call MS-Cliente
		/*
		ClienteClientDTO clienteClientDTO= clienteService.findById(id);
		if (!isNull(clienteClientDTO)) {
			ClienteDTO clienteDTO= ClienteDTO.builder().id(clienteClientDTO.getId()).razonSocial(clienteClientDTO.getRazonSocial()).ruc(clienteClientDTO.getRuc()).direccion(clienteClientDTO.getDireccion()).build();
			pedidoDTO.setCliente(clienteDTO);
		}*/
		pedidoDTO.setCliente(clienteService.findById(id));
		return Optional.ofNullable(pedidoDTO);
	}

	@Override
	public List<PedidoDTO> findAll() throws PedidoException {
		return pedidoMapper.toLstDTO(pedidoRepository.findAll());
	}

	@Override
	public Long save(PedidoDTO pedidoDTO) throws PedidoException {
		PedidoEntity pedidoEntity= pedidoRepository.save(pedidoMapper.toEntity(pedidoDTO));	
		if (isNull(pedidoEntity)) {
			throw new PedidoException("Error al resgitrar pedido");
		}
		return pedidoEntity.getId();
	}
}
