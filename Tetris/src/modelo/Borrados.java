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

	// Borra la zona de la puntuación (fija)
	public int[][] borrarPuntuacion(int[][] matrizImagen) {
		for (int i = 120; i < 180; i++) {
			for (int j = 50; j < 350; j++) {
				matrizImagen[i][j] = matrizJuegoBase[i][j];
			}
		}
		return matrizImagen;
	}

	// Borra la pieza en su última posición conocida
	public int[][] borrarPieza(int[][] matrizFicha, int[][] matrizJuego) {
	    for (int i = 0; i < matrizFicha.length; i++) {
	        for (int j = 0; j < matrizFicha[0].length; j++) {
	            	if(matrizFicha[i][j]!=0)
	                matrizJuego[posicionFichaBorrarY +i][posicionFichaBorrarX + j] = matrizJuegoBase[posicionFichaBorrarY +i][posicionFichaBorrarX + j];
	            }
	        }
	    
	    return matrizJuego;
	}

	

}