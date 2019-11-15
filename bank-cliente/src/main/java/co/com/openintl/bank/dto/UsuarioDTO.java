package co.com.openintl.bank.dto;

import java.math.BigDecimal;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UsuarioDTO {
	@Id
	@NotNull
	private String usuUsuario;
	
	@NotNull
	@Size(min =1, max =1)
	private String activo;
	
	@NotNull
	private String clave;

	@NotNull
	private BigDecimal identificacion;
	
	@NotNull
	@Size(min = 1,max = 50)
	private String nombre;

	@NotNull
	private Long tiusId;

	public UsuarioDTO(@NotNull String usuUsuario, @NotNull @Size(min = 1, max = 1) String activo, @NotNull String clave,
			@NotNull BigDecimal identificacion, @NotNull @Size(min = 1, max = 50) String nombre, @NotNull Long tiusId) {
		super();
		this.usuUsuario = usuUsuario;
		this.activo = activo;
		this.clave = clave;
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.tiusId = tiusId;
	}

	public UsuarioDTO() {
		super();
	}

	public String getUsuUsuario() {
		return usuUsuario;
	}

	public void setUsuUsuario(String usuUsuario) {
		this.usuUsuario = usuUsuario;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public BigDecimal getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(BigDecimal identificacion) {
		this.identificacion = identificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Long getTiusId() {
		return tiusId;
	}

	public void setTiusId(Long tiusId) {
		this.tiusId = tiusId;
	}
	
}
