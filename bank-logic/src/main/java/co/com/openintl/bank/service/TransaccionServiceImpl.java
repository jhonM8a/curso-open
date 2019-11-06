package co.com.openintl.bank.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class TransaccionServiceImpl implements TransaccionService {

	@Override
	public Long retirar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long consignacion(String cuenId, BigDecimal valor, String usuUsuario) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long transferencia(String cuenIdOrigen, String cuenIdDestino, BigDecimal valor, String usuUsuario)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
