package vista;

public enum PiezaPequena {
	PIEZAT("./img/PiezasPequeñas/PiezaT.png"), 
	PIEZACUADRADO("./img/PiezasPequeñas/PiezaCuadrado.png"), 
	PIEZAI("./img/PiezasPequeñas/PiezaI.png"), 
	PIEZAJ("./img/PiezasPequeñas/PiezaJ.png"), 
	PIEZAL("./img/PiezasPequeñas/PiezaL.png"), 
	PIEZAS("./img/PiezasPequeñas/PiezaS.png"), 
	PIEZAZ("./img/PiezasPequeñas/PiezaZ.png");

	private final Imagen imagen;

	/**
	 * Constructor de Pieza
	 * 
	 * @param ruta  de la imagen de cada pieza
	 * 
	 */
	PiezaPequena(String ruta) {
		this.imagen = new Imagen(ruta);
		imagen.cerrar();

	}

	/**
	 * Metodo para obtener la imagen
	 * 
	 * @return imagen de la pieza
	 */
	public Imagen getImagen() {

		return imagen;
	}

	

}
