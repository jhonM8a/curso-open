package co.com.openintl.bank.service;

import java.math.BigDecimal;

public interface TransaccionService {

	public Long retirar(String cuenId, BigDecimal valor, String usuUsuario) throws Exception;
	
	public Long consignacion(String cuenId, BigDecimal valor, String usuUsuario) throws Exception;

	public Long transferencia(String cuenIdOrigen, String cuenIdDestino ,BigDecimal valor, String usuUsuario) throws Exception;

}
