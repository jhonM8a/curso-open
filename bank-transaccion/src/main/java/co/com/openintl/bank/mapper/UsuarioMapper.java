package co.com.openintl.bank.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import co.com.openintl.bank.domain.Usuario;
import co.com.openintl.bank.dto.UsuarioDTO;

@Mapper
public interface UsuarioMapper {

	@Mapping(source="tipoUsuario.tiusId", target = "tiusId")
	UsuarioDTO enityToDTO(Usuario usuario);
	
	@Mapping(target="tipoUsuario.tiusId",  source= "tiusId")
	Usuario dtoToEntity(UsuarioDTO usuarioDTO);
	
	List<Usuario> toUsuarios(List<UsuarioDTO> usuariosDTO);
	
	List<UsuarioDTO> toUsuariosDTO(List<Usuario> usuarios);
	
}
