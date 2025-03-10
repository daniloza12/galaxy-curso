package pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.mappers;

import java.util.List;
import java.util.Optional;

import pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.dto.ProductoDTO;
import pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.entity.ProductoEntity;

public interface ProductoMapper {
	

	ProductoDTO toDTO(ProductoEntity e);
	
	Optional<ProductoDTO> toOptDTO(ProductoEntity e);
	
	List<ProductoDTO> toDTO(List<ProductoEntity>lst);
	
}
