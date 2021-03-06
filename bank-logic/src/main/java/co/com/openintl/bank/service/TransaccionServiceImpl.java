package co.com.openintl.bank.service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.com.openintl.bank.domain.Cuenta;
import co.com.openintl.bank.domain.TipoTransaccion;
import co.com.openintl.bank.domain.Transaccion;
import co.com.openintl.bank.domain.Usuario;
import co.com.openintl.bank.repository.CuentaRepository;
import co.com.openintl.bank.repository.TipoTransaccionRepository;
import co.com.openintl.bank.repository.TransaccionRepository;
import co.com.openintl.bank.repository.UsuarioRepository;

@Service
public class TransaccionServiceImpl implements TransaccionService {

	@Autowired
	private CuentaRepository cuentaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private TipoTransaccionRepository tipoTransaccionRepository;
	
	@Autowired
	private TransaccionRepository transaccionRepository;
	
	private final static String CUENTA_BANCO = "9999-9999-9999-9999";
	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long retirar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {
		
		validar(cuenId, valor, usuUsuario);
		Cuenta cuenta = cuentaRepository.findById(cuenId).get();
		Usuario usuario = usuarioRepository.findById(usuUsuario).get();
		
		if(cuenta.getSaldo().compareTo(valor)==-1){
			throw new Exception("Esta en rojo bbe...saldo paila");
		}
		
		Transaccion transaccion = new Transaccion();
		transaccion.setCuenta(cuenta);
		transaccion.setFecha(new Timestamp(System.currentTimeMillis()));
		transaccion.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		transaccion.setTranId(null);
		
		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(1L).get();
		transaccion.setTipoTransaccion(tipoTransaccion);
		transaccion.setUsuario(usuario);
		transaccion.setUsuCreador(usuUsuario);
		transaccion.setUsuModificador(usuUsuario);
		transaccion.setValor(valor);
		
		cuenta.setSaldo(cuenta.getSaldo().subtract(valor));
		cuentaRepository.save(cuenta);
		transaccion = transaccionRepository.save(transaccion);
		
		return transaccion.getTranId();
	}



	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long consignacion(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {
		validar(cuenId, valor, usuUsuario);		
		Cuenta cuenta = cuentaRepository.findById(cuenId).get();
		Usuario usuario = usuarioRepository.findById(usuUsuario).get();

		
		Transaccion transaccion = new Transaccion();
		transaccion.setCuenta(cuenta);
		transaccion.setFecha(new Timestamp(System.currentTimeMillis()));
		transaccion.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		transaccion.setTranId(null);
		
		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(2L).get();
		transaccion.setTipoTransaccion(tipoTransaccion);
		transaccion.setUsuario(usuario);
		transaccion.setUsuCreador(usuUsuario);
		transaccion.setUsuModificador(usuUsuario);
		transaccion.setValor(valor);
		
		cuenta.setSaldo(cuenta.getSaldo().add(valor));
		cuentaRepository.save(cuenta);
		transaccion = transaccionRepository.save(transaccion);
		
		return transaccion.getTranId();
	}

	@Override
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Long transferencia(String cuenIdOrigen, String cuenIdDestino, BigDecimal valor, String usuUsuario)
			throws Exception {
		
		if(cuenIdDestino==null || cuenIdOrigen ==null) {
			throw new Exception("# de cuentas no validos");
			
		}
		if( cuenIdDestino.trim().equals(cuenIdOrigen)) {
			throw new Exception("# de cuentas no validos, son validos");
		}
		
		retirar(cuenIdOrigen, valor, usuUsuario);
		
		consignacion(cuenIdDestino, valor, usuUsuario);
		
		retirar(cuenIdOrigen, new BigDecimal(2000), usuUsuario);
		
		consignacion(CUENTA_BANCO, new BigDecimal(2000), usuUsuario);

		Cuenta cuenta = cuentaRepository.findById(cuenIdOrigen).get();
		Usuario usuario = usuarioRepository.findById(usuUsuario).get();

		
		Transaccion transaccion = new Transaccion();
		transaccion.setCuenta(cuenta);
		transaccion.setFecha(new Timestamp(System.currentTimeMillis()));
		transaccion.setFechaCreacion(new Timestamp(System.currentTimeMillis()));
		transaccion.setTranId(null);
		
		TipoTransaccion tipoTransaccion = tipoTransaccionRepository.findById(3L).get();
		transaccion.setTipoTransaccion(tipoTransaccion);
		transaccion.setUsuario(usuario);
		transaccion.setUsuCreador(usuUsuario);
		transaccion.setUsuModificador(usuUsuario);
		transaccion.setValor(valor);
		
		transaccion = transaccionRepository.save(transaccion);
		
		return transaccion.getTranId();
	}
	
	private void validar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {
		if(cuenId==null || cuenId.trim().equals("")) {
			throw new Exception("# de cuenta Obligatorio papi");
		}
		
		if(valor==null || valor.compareTo(BigDecimal.ZERO)<=0) {
			throw new Exception("Valor no es correcto");
		}
		
		if(usuUsuario==null || usuUsuario.isEmpty()) {
			throw new Exception("Usuario no valido");

		}
		
		Optional<Cuenta> cuenta = cuentaRepository.findById(cuenId);
		
		if(!cuenta.isPresent()) {
			throw new Exception("Cuenta no existe");
		}
		
		if(cuenta.get().getActiva().equals("N")) {
			throw new Exception("La cuenta esta inactiva");

		}
		
		Optional<Usuario> usuario = usuarioRepository.findById(usuUsuario);
		
		if(!usuario.isPresent()) {
			throw new Exception("Usuario no existe");
		}
		
		if(usuario.get().getActivo().equals("N")) {
			throw new Exception("Usuario inactivo");

		}
	}

}
