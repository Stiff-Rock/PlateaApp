package Modelo;

public class Usuario {
	private String nickname;
	private String apellido;
	private String nombre;
	private String cp;
	private String pwd;
	private String esAdmin;
	// private Image foto;
	private String codPregunta;
	private String respuesta;

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCp() {
		return cp;
	}

	public void setCp(String cp) {
		this.cp = cp;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String isEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(String esAdmin) {
		this.esAdmin = esAdmin;
	}

	public String getCodPregunta() {
		return codPregunta;
	}

	public void setCodPregunta(String codPregunta) {
		this.codPregunta = codPregunta;
	}

	public String getRespuesta() {
		return respuesta;
	}

	public void setRespuesta(String respuesta) {
		this.respuesta = respuesta;
	}
}
