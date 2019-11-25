package co.com.openintl.bank.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.com.openintl.bank.domain.Usuario;
import co.com.openintl.bank.repository.TipoUsuarioRepository;
import co.com.openintl.bank.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepositorio;
	
	@Autowired
	private TipoUsuarioRepository tipoUsuarioRepositorio;
	
	@Autowired
	private Validator validator;
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Usuario save(Usuario entity) throws Exception {
		// TODO Auto-generated method stub
		validar(entity);
		if(usuarioRepositorio.findById(entity.getUsuUsuario()).isPresent()) {
			throw new Exception("Usuario ya existe");
		}
		
		if(!tipoUsuarioRepositorio.findById(entity.getTipoUsuario().getTiusId()).isPresent()) {
			throw new Exception("Tipo de Usuario no existe");
		}
		return usuarioRepositorio.save(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Usuario update(Usuario entity) throws Exception {
		validar(entity);
		if(!usuarioRepositorio.findById(entity.getUsuUsuario()).isPresent()) {
			throw new Exception("Usuario ya existe");
		}
		
		if(!tipoUsuarioRepositorio.findById(entity.getTipoUsuario().getTiusId()).isPresent()) {
			throw new Exception("Tipo de Usuario no existe");
		}
		return usuarioRepositorio.save(entity);
	}

	@Override
	public Optional<Usuario> findById(String id) {
		// TODO Auto-generated method stub
		return usuarioRepositorio.findById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void delete(Usuario entity) throws Exception {
		// TODO Auto-generated method stub
		validar(entity);
		if(!usuarioRepositorio.findById(entity.getUsuUsuario()).isPresent()) {
			throw new Exception("Usuario No existe");
		}
		
		entity = usuarioRepositorio.findById(entity.getUsuUsuario()).get();
		
		if(entity.getTransaccions().size()>0) {
			throw new Exception("Usuario Tiene transacciones asociadas");
		}
		
		usuarioRepositorio.delete(entity);

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(String id) throws Exception {
		// TODO Auto-generated method stub
		if(id ==null || id.isEmpty() || id.length()<0) {
			throw new Exception("Id no puede ser menor a nulo");
		}
		
		if(!findById(id).isPresent()) {
			throw new Exception("Usuario no existe");
		}
		
		delete(findById(id).get());
	}

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return usuarioRepositorio.findAll();
	}
	
	
	public void validar(Usuario usuario) throws Exception {
		try {
			if (usuario == null) {
				throw new Exception("El usuario es nulo");
			}
			Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);

			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();

				for (ConstraintViolation<Usuario> constraintViolation : constraintViolations) {
					strMessage.append(constraintViolation.getPropertyPath().toString());
					strMessage.append(" - ");
					strMessage.append(constraintViolation.getMessage());
					strMessage.append(". \n");
				}

				throw new Exception(strMessage.toString());
			}
		} catch (Exception e) {
			throw e;
		}
	}

}
