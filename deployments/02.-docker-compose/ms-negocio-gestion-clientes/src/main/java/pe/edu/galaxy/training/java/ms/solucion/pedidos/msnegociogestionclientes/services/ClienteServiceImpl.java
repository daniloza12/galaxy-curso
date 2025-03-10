package pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.dto.ClienteDTO;
import pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.entity.ClienteEntity;
import pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.mappers.ClienteMapper;
import pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.repository.ClienteRepository;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class ClienteServiceImpl implements ClienteService {

	private final ClienteRepository clienteRepository;
	private final ClienteMapper clienteMapper;

	@Override
	public List<ClienteDTO> findAll() throws ServiceException {
		return this.clienteMapper.toDTO(this.clienteRepository.findAll());
	}

	@Override
	public List<ClienteDTO> findByLikeObject(ClienteDTO clienteDTO) throws ServiceException {
		return clienteRepository.findByLikeRazonSocial("%" + clienteDTO.getRazonSocial() + "%").stream()
				.map(p -> clienteMapper.toDTO(p)).toList();

	}

	@Override
	public ClienteDTO save(ClienteDTO clienteDTO) throws ServiceException {
		return clienteMapper.toDTO(clienteRepository.save(clienteMapper.toEntity(clienteDTO)));
	}

	@Override
	public ClienteDTO update(ClienteDTO clienteDTO) throws ServiceException {

		Long id = clienteDTO.getId();

		ClienteEntity oClienteEntity = clienteRepository.findById(id)
				.orElseThrow(() -> new RuntimeException(String.format("No existe cliente con el id=%s", id)));
		if (!isNull(oClienteEntity)) {
			oClienteEntity.setRazonSocial(clienteDTO.getRazonSocial());
			oClienteEntity.setRuc(clienteDTO.getRuc());
			oClienteEntity.setDireccion(clienteDTO.getDireccion());
			oClienteEntity.setCorreo(clienteDTO.getCorreo());
			oClienteEntity.setTelefono(clienteDTO.getTelefono());
			oClienteEntity.setEstado(clienteDTO.getEstado());

			return clienteMapper.toDTO(clienteRepository.save(oClienteEntity));
		} else {
			return clienteMapper.toDTO(clienteRepository.save(clienteMapper.toEntity(clienteDTO)));
		}

	}

	@Override
	public void delete(Long id) throws ServiceException {

		Optional<ClienteEntity> optClienteEntity = clienteRepository.findById(id);

		if (!((isNull(optClienteEntity) || optClienteEntity.isEmpty()))) {
			ClienteEntity clienteEntity = optClienteEntity.get();
			clienteEntity.setEstado("0");
			clienteRepository.save(clienteEntity);
		}
	}

	@Override
	public Optional<ClienteDTO> findById(Long id) throws ServiceException {
		Optional<ClienteEntity> optClienteEntity = clienteRepository.findById(id);

		if (isNull(optClienteEntity) || optClienteEntity.isEmpty()) {
			return Optional.empty();
		}

		return Optional.ofNullable(clienteMapper.toDTO(optClienteEntity.get()));

	}

	@Override
	public Optional<ClienteDTO> findByRuc(String ruc) throws ServiceException {
		Optional<ClienteEntity> optClienteEntity = clienteRepository.findByRuc(ruc);

		if (isNull(optClienteEntity) || optClienteEntity.isEmpty()) {
			return Optional.empty();
		}

		return Optional.ofNullable(clienteMapper.toDTO(optClienteEntity.get()));

	}

}
