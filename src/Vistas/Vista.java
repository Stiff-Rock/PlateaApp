package Vistas;

import Controlador.Controlador;
import Modelo.Modelo;

public interface Vista {
	public void setModelo(Modelo modelo);
	public void setControlador (Controlador controlador);
}