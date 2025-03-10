package pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.dto.ProductoDTO;import pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.services.ProductoService;
import pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.services.ServiceException;

@RestController
@RequestMapping("api/v1/productos")
public class ProductoController {

	private final ProductoService productosService;

	public ProductoController(final ProductoService productosService) {
		this.productosService = productosService;
	}

	@GetMapping
	public ResponseEntity<?> findAll() throws ServiceException {
		List<ProductoDTO> productos = productosService.findAll();
		if (productos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(productos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) throws ServiceException {

		Optional<ProductoDTO> optProducto = productosService.findById(id);
		if (optProducto.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(optProducto.get());
	}

	@GetMapping("/ids")
	public ResponseEntity<?> findById(@RequestParam("ids") List<Long> ids) throws ServiceException {

		List<ProductoDTO> productos = productosService.findByIds(ids);
		if (productos.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(productos);
	}

	/*
	 * @GetMapping("/by-razonSocial") public List<ProductoEntity>
	 * findByLikeRazonSocial(@RequestParam String razonSocial) { return
	 * productosService.findByLikeRazonSocial(razonSocial); }
	 * 
	 * 
	 * 
	 * @PostMapping public ProductoEntity save(@RequestBody ProductoEntity
	 * productosEntity) { return productosService.save(productosEntity); }
	 * 
	 * @PutMapping("/{id}") public ProductoEntity update(@PathVariable Long
	 * id, @RequestBody ProductoEntity productosEntity) { productosEntity.setId(id);
	 * return productosService.update(productosEntity); }
	 * 
	 * @DeleteMapping("/{id}") public void delete(@PathVariable Long id) {
	 * productosService.delete(id); }
	 */
}