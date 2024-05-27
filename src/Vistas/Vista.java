package Vistas;

import Controlador.Controlador;
import Modelo.Modelo;
import Modelo.Usuario;

public interface Vista {
	public void setModelo(Modelo modelo);
	public void setControlador (Controlador controlador);
	public void setUsuario(Usuario user);
}
