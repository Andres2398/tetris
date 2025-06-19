package vista;
/**
 * Clase enum con todos los posibles numeros para la puntuacion 
 */
public enum Numero {
	NUMERO0("./img/Numeros/numero0.png"),
	NUMERO1("./img/Numeros/numero1.png"),
	NUMERO2("./img/Numeros/numero2.png"),
	NUMERO3("./img/Numeros/numero3.png"),
	NUMERO4("./img/Numeros/numero4.png"),
	NUMERO5("./img/Numeros/numero5.png"),
	NUMERO6("./img/Numeros/numero6.png"),
	NUMERO7("./img/Numeros/numero7.png"),
	NUMERO8("./img/Numeros/numero8.png"),
	NUMERO9("./img/Numeros/numero9.png");

	private final Imagen imagen;
	/**
	 * Constructor del numero
	 * @param ruta de la imagen 
	 */
	Numero(String ruta) {
		this.imagen = new Imagen(ruta);
		imagen.cerrar();
	}
	/**
	 * Metodo para recibir la imagen del numero
	 * @return retorna la imagen 
	 */
	public Imagen getImagen() {
		
		return imagen;
	} 

}
