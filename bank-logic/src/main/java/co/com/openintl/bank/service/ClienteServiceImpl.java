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
@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private TipoDocumentoRepository tipoDocumentoRepository;
	
	
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Cliente save(Cliente entity) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Cliente> findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)

	public void delete(Cliente entity) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)

	public void deleteById(Long id) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Cliente> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
