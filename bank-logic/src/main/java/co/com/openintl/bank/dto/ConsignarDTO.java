package co.com.openintl.bank.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;


public class ConsignarDTO {

	@NotNull
	@Size(min = 19, max = 19 ) 
	private String cuenId;
	
	@NotEmpty
	@NotNull
	@Positive
	private BigDecimal valor;
	
	@NotEmpty
	@NotNull
	private String usuUsuario;

	public ConsignarDTO() {
		super();
	}

	public ConsignarDTO(String cuenId, BigDecimal valor, String usuUsuario) {
		super();
		this.cuenId = cuenId;
		this.valor = valor;
		this.usuUsuario = usuUsuario;
	}

	public String getCuenId() {
		return cuenId;
	}

	public void setCuenId(String cuenId) {
		this.cuenId = cuenId;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getUsuUsuario() {
		return usuUsuario;
	}

	public void setUsuUsuario(String usuUsuario) {
		this.usuUsuario = usuUsuario;
	}

}
