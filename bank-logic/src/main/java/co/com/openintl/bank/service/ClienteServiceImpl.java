package co.com.openintl.bank.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.com.openintl.bank.domain.Cliente;
import co.com.openintl.bank.repository.ClienteRepository;
import co.com.openintl.bank.repository.TipoDocumentoRepository;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;

	@Autowired
	private Validator validator;

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Cliente save(Cliente entity) throws Exception {
		// TODO Auto-generated method stub
		validar(entity);
		if (clienteRepository.findById(entity.getClieId()).isPresent()) {
			throw new Exception("Cliente ya existe");
		}
		if (tipoDocumentoRepository.findById(entity.getTipoDocumento().getTdocId()).isPresent() == false) {
			throw new Exception("Tipo de documento no existe");
		}
		return clienteRepository.save(entity);
	}

	@Override
	public Optional<Cliente> findById(Long id) {
		// TODO Auto-generated method stub
		return clienteRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)

	public void delete(Cliente entity) throws Exception {
		// TODO Auto-generated method stub
		validar(entity);

		if (!clienteRepository.findById(entity.getClieId()).isPresent()) {
			throw new Exception("Cliente No existe");
		}

		entity = clienteRepository.findById(entity.getClieId()).get();

		if (entity.getCuentaRegistradas().size() > 0) {
			throw new Exception("Cliente Tiene cuentas asociadas");
		}

		if (entity.getCuentas().size() > 0) {
			throw new Exception("Cliente Tiene cuentas asociadas");
		}

		clienteRepository.delete(entity);
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteById(Long id) throws Exception {
		// TODO Auto-generated method stub
		if (id == null || id < 1) {
			throw new Exception("Id no puede ser menor a nulo");
		}
		if (!findById(id).isPresent()) {
			throw new Exception("Cliente no existe");
		}

		delete(findById(id).get());
	}

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return clienteRepository.findAll();
	}

	@Override
	public Cliente update(Cliente entity) throws Exception {
		// TODO Auto-generated method stub
		validar(entity);
		if (!clienteRepository.findById(entity.getClieId()).isPresent()) {
			throw new Exception("Cliente No existe");
		}
		if (tipoDocumentoRepository.findById(entity.getTipoDocumento().getTdocId()).isPresent() == false) {
			throw new Exception("Tipo de documento no existe");
		}
		return clienteRepository.save(entity);
	}

	public void validar(Cliente cliente) throws Exception {
		try {
			if (cliente == null) {
				throw new Exception("El cliente es nulo");
			}
			Set<ConstraintViolation<Cliente>> constraintViolations = validator.validate(cliente);

			if (constraintViolations.size() > 0) {
				StringBuilder strMessage = new StringBuilder();

				for (ConstraintViolation<Cliente> constraintViolation : constraintViolations) {
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
