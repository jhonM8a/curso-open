package co.com.openintl.bank.mapper;


import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.openintl.bank.domain.Cliente;
import co.com.openintl.bank.dto.ClienteDTO;

@Mapper
public interface ClienteMapper {

	@Mapping(source = "tipoDocumento.tdocId", target="tdocId") 
	ClienteDTO entityToDTO(Cliente cliente);
	
	@Mapping(target = "tipoDocumento.tdocId", source="tdocId") 
	Cliente dtoToEntity(ClienteDTO clienteDTO);
	
	List<Cliente> toClientes(List<ClienteDTO> clientesDTO);
	
	List<ClienteDTO> toClientesDTO(List<Cliente> clientes);
}
