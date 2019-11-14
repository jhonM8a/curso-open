package co.com.openintl.bank.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class RetirarDTO {

	@NotNull
	@Size(min = 19, max = 19)
	private String cuenId;

	
	@NotNull
	@Positive
	private BigDecimal valor;

	
	@NotNull
	private String usuUsuario;

	public RetirarDTO() {
		super();
	}



	public RetirarDTO(@NotNull @Size(min = 19, max = 19) String cuenId, @NotNull @Positive BigDecimal valor,
			@NotNull String usuUsuario) {
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
