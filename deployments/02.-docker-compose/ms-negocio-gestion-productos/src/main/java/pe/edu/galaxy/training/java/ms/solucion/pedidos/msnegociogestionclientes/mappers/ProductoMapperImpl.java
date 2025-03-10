package pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.mappers;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;
import pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.dto.ProductoDTO;
import pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.entity.ProductoEntity;

@Component
public class ProductoMapperImpl implements ProductoMapper {

	@Override
	public Optional<ProductoDTO> toOptDTO(ProductoEntity e) {
		return Optional.ofNullable(toDTO(e));
	}

	@Override
	public List<ProductoDTO> toDTO(List<ProductoEntity> lst) {
		return lst.stream().map(e -> toDTO(e)).toList();
	}
	
	@Override
	public ProductoDTO toDTO(ProductoEntity e) {
		return ProductoDTO.builder().id(e.getId()).nombre(e.getNombre()).precio(e.getPrecio())
				.stock(e.getStock()).estado(e.getEstado()).build();
	}

}
