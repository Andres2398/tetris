package modelo;

/**
 * Clase dedicada a borrar partes del tablero de la matriz de la imagen
 */
public class Borrados {
	private int[][] matrizJuegoBase;
	private int posicionFichaBorrarX;
	private int posicionFichaBorrarY;

	// Getters/Setters
	/**
	 * Metood para establecer la posicion de las columnas desde la que borrar
	 * 
	 * @param posicionFichaBorrarX int que representa la posicion desde la que
	 *                             borrar
	 */
	public void setPosicionFichaBorrarX(int posicionFichaBorrarX) {
		this.posicionFichaBorrarX = posicionFichaBorrarX;
	}

	/**
	 * Metood para establecer la posicion de las fila desde la que borrar
	 * 
	 * @param posicionFichaBorrarY int que representa la posicion desde la que
	 *                             borrar
	 */
	public void setPosicionFichaBorrarY(int posicionFichaBorrarY) {
		this.posicionFichaBorrarY = posicionFichaBorrarY;
	}

	/**
	 * Metood para establecer la matriz vacia del tablero para asi poder borrar
	 * 
	 * @param matrizJuegoBase matriz de pixeles vacio
	 */
	public void setMatrizJuegoBase(int[][] matrizJuegoBase) {
		this.matrizJuegoBase = matrizJuegoBase;
	}

	/**
	 * Metodo para borrar la puntuacion
	 * 
	 * @param matrizImagen matriz de la imagen con la que se esta jugando
	 * @return la matriz imagen con los pixeles de la puntuacion borrados
	 */
	public int[][] borrarPuntuacion(int[][] matrizImagen) {
		for (int i = 120; i < 180; i++) {
			for (int j = 20; j < 350; j++) {
				matrizImagen[i][j] = matrizJuegoBase[i][j];

			}
		}

		return matrizImagen;
	}

	public int[][] borrarPiezaPequena(int[][] matrizImagen) {
		for (int i = 85; i < 190; i++) {
			for (int j = 440; j < 565; j++) {
				matrizImagen[i][j] = matrizJuegoBase[i][j];
			}
		}
		return matrizImagen;
	}

	/**
	 * Borra la pieza en su ultima posicion
	 * 
	 * @param matrizFicha matriz de int con los pixeles de la ficha
	 * @param matrizJuego matriz de int con los pixeles de toda la imagen
	 * @return matriz de la imagen con la pieza borrada
	 */
	public int[][] borrarPieza(int[][] matrizFicha, int[][] matrizJuego) {
		for (int i = 0; i < matrizFicha.length; i++) {
			for (int j = 0; j < matrizFicha[0].length; j++) {
				if (matrizFicha[i][j] != 0)
					matrizJuego[posicionFichaBorrarY + i][posicionFichaBorrarX
							+ j] = matrizJuegoBase[posicionFichaBorrarY + i][posicionFichaBorrarX + j];
			}
		}

		return matrizJuego;
	}

	/**
	 * Meotdo para borrar la linea actual y bajar las demas hacia abajo
	 * 
	 * @param fila              que se quiere borrar
	 * @param matrizImagenJuego matriz de la imagen de juego
	 * @return matriz de la imgaen de juego con las filas ya borradas y bajadas
	 */
	public int[][] eliminarLinea(int fila, int[][] matrizImagenJuego) {
		int pixelesPorFila = 51;
		int desfaseY = 223;
		int desfaseX = 75;
		int ancho = 595 - desfaseX;

		int totalFilasPixeles = matrizImagenJuego.length;
		int totalColumnas = matrizImagenJuego[0].length;

		int filaPixelesInicio = desfaseY + fila * pixelesPorFila;

		// COMPLEJO ME HA LLEVADO VARIAS HORAS NO TOCAR YA FUNCIONA, EXPLICADO PARA QUE
		// NO SE ME OLVIDE:
		// Mover hacia abajo todas las filas que están ARRIBA de la fila
		// eliminada
		// Empezamos desde la fila que se va a eliminar y vamos hacia arriba
		for (int i = filaPixelesInicio; i >= desfaseY + pixelesPorFila; i -= pixelesPorFila) {
			int filaOrigen = i - pixelesPorFila;

			// Copiar todos pixeles de esa fila
			for (int j = 0; j < pixelesPorFila; j++) {
				int yDestino = i + j;
				int yOrigen = filaOrigen + j;

				if (yOrigen >= desfaseY && yDestino < totalFilasPixeles) {
					for (int x = desfaseX; x < desfaseX + ancho && x < totalColumnas; x++) {
						matrizImagenJuego[yDestino][x] = matrizImagenJuego[yOrigen][x];
					}
				}
			}
		}

		// Limpiar la fila superior con el fondo base
		for (int j = 0; j < pixelesPorFila; j++) {
			int y = desfaseY + j;
			if (y < totalFilasPixeles) {
				for (int x = desfaseX; x < desfaseX + ancho && x < totalColumnas; x++) {
					matrizImagenJuego[y][x] = matrizJuegoBase[y][x];
				}
			}
		}

		// Limpiar la parte inferior del tablero
		// del juego aunque creo que ya no hace falta mejor no tocarlo.
		for (int i = 935; i < matrizImagenJuego.length; i++) {
			for (int j = 0; j < matrizImagenJuego[0].length; j++) {
				matrizImagenJuego[i][j] = matrizJuegoBase[i][j];
			}
		}

		return matrizImagenJuego;
	}
}