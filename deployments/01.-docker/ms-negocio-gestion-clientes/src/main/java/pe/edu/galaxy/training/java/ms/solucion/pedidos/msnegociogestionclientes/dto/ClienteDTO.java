package pe.edu.galaxy.training.java.ms.solucion.pedidos.msnegociogestionclientes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClienteDTO{

	private Long id;
	
	private String razonSocial;
	
	private String ruc;
	
	private String direccion;
	
	private String telefono;

	private String correo;

	private String estado;

}
