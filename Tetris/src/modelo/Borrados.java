package modelo;

public class Borrados {
	private int[][] matrizJuegoBase;
	private int posicionFichaBorrarX;
	private int posicionFichaBorrarY;

	public int getPosicionFichaBorrarX() {
		return posicionFichaBorrarX;
	}

	public void setPosicionFichaBorrarX(int posicionFichaBorrarX) {
		this.posicionFichaBorrarX = posicionFichaBorrarX;
	}

	public int getPosicionFichaBorrarY() {
		return posicionFichaBorrarY;
	}

	public void setPosicionFichaBorrarY(int posicionFichaBorrarY) {
		this.posicionFichaBorrarY = posicionFichaBorrarY;
	}

	public void setMatrizJuegoBase(int[][] matrizJuegoBase) {
		this.matrizJuegoBase = matrizJuegoBase;
	}

	public int[][] borrarPuntuacion(int[][] matrizImagen) {
		for (int i = 120; i < 180; i++) {
			for (int j = 20; j < 350; j++) {
				matrizImagen[i][j] = matrizJuegoBase[i][j];

			}
		}

		return matrizImagen;
	}

	// Borra la pieza en su última posición conocida
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

	public int[][] eliminarLinea(int filaLogica, int[][] matrizImagenJuego) {
		final int pixelesPorFila = 51;
		final int offsetY = 223;
		final int offsetX = 75;
		final int ancho = 595 - offsetX;

		int totalFilasPixeles = matrizImagenJuego.length;
		int totalColumnas = matrizImagenJuego[0].length;

		// Calcular la fila en píxeles donde empieza la fila lógica a eliminar
		int filaPixelesInicio = offsetY + filaLogica * pixelesPorFila;
		
		//COMPLEJO ME HA LLEVADO VARIAS HORAS NO TOCAR YA FUNCIONA, EXPLICADO PARA QUE NO SE ME OLVIDE:
		// Mover hacia abajo todas las filas que están ARRIBA de la fila
		// eliminada
		// Empezamos desde la fila que se va a eliminar y vamos hacia arriba
		for (int filaDestino = filaPixelesInicio; filaDestino >= offsetY
				+ pixelesPorFila; filaDestino -= pixelesPorFila) {
			int filaOrigen = filaDestino - pixelesPorFila;

			// Copiar todos pixeles de esa fila
			for (int pixelY = 0; pixelY < pixelesPorFila; pixelY++) {
				int yDestino = filaDestino + pixelY;
				int yOrigen = filaOrigen + pixelY;

				if (yOrigen >= offsetY && yDestino < totalFilasPixeles) {
					for (int x = offsetX; x < offsetX + ancho && x < totalColumnas; x++) {
						matrizImagenJuego[yDestino][x] = matrizImagenJuego[yOrigen][x];
					}
				}
			}
		}
		
		//Limpiar la fila superior con el fondo base
		for (int pixelY = 0; pixelY < pixelesPorFila; pixelY++) {
			int y = offsetY + pixelY;
			if (y < totalFilasPixeles) {
				for (int x = offsetX; x < offsetX + ancho && x < totalColumnas; x++) {
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