package pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.services;

import java.util.List;
import pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.dto.ProductoDTO;

public interface ProductoService extends GenericService<ProductoDTO>{
	
	List<ProductoDTO> findByIds(List<Long> ids) throws ServiceException;;
}
