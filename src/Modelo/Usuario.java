package Modelo;

import javax.swing.ImageIcon;

public class Usuario {
	private String nickname;
	private String apellido;
	private String nombre;
	private String cp;
	private String pwd;
	private String esAdmin;
	private ImageIcon foto;
	private String codPregunta;
	private String respuesta;

	public Usuario() {
		this.nickname = "";
		this.apellido = "";
		this.nombre = "";
		this.cp = "";
		this.pwd = "";
		this.esAdmin = "";
		this.foto = null;
		this.codPregunta = "";
		this.respuesta = "";
	}

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

	public String getEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(String esAdmin) {
		this.esAdmin = esAdmin;
	}

	public ImageIcon getFoto() {
		return foto;
	}

	public void setFoto(ImageIcon foto) {
		this.foto = foto;
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

	public void cargarDatos(String[] cargarUsuario) {
		this.nickname = cargarUsuario[0];
		this.apellido = cargarUsuario[1];
		this.nombre = cargarUsuario[2];
		this.cp = cargarUsuario[3];
		this.pwd = cargarUsuario[4];
		this.esAdmin = cargarUsuario[5];
//		this.foto = cargarUsuario[6];
		this.codPregunta = cargarUsuario[7];
		this.respuesta = cargarUsuario[8];

		System.out.println("Datos de usuario cargados: ");
	}

	public void mostrarUser() {
		System.out.println(nickname + " " + apellido + " " + nombre + " " + cp + " " + pwd + " " + esAdmin + " "
				+ codPregunta + " " + respuesta);
	}

}
