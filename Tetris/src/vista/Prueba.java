package vista;

import java.util.Random;


public class Prueba {
	int puntuacion;
	private final static int PUNTUACIONFILA = 120;
	private int puntuacionColumna;
	private int posicionFichaY;
	private int posicionFichaX;
	private Imagen imagenPieza;
	private int[][] piezasRotadas;
	private int[][] matrizTableroVacio;
	private int[][] matrizTablero;

	public int[][] getMatrizTablero() {
		return matrizTablero;
	}

	public void setMatrizTablero(int[][] matrizTablero) {
		this.matrizTablero = matrizTablero;
	}

	public Prueba() {
		puntuacion = 6234;
		puntuacionColumna = 80;
		imagenPieza = null;
	}

	public int getPosicionFichaX() {
		return posicionFichaX;
	}

	public void setPosicionFichaX(int posicionFichaX) {
		this.posicionFichaX = posicionFichaX;
	}

	public int[][] getPiezasRotadas() {
		return piezasRotadas;
	}

	public int[][] getMatrizTableroVacio() {
		return matrizTableroVacio;
	}

	public void setMatrizTableroVacio(int[][] matrizTableroVacio) {
		this.matrizTableroVacio = matrizTableroVacio;
	}

	public void setPiezasRotadas(int[][] piezasRotadas) {
		this.piezasRotadas = piezasRotadas;
	}

	public Imagen getImagenPieza() {
		return imagenPieza;
	}

	public void setImagenPieza(Imagen imagenPieza) {
		this.imagenPieza = imagenPieza;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}

	public int getPuntuacionColumna() {
		return puntuacionColumna;
	}

	public void setPuntuacionColumna(int puntuacionColumna) {
		this.puntuacionColumna = puntuacionColumna;
	}

	private int[][] ProximaFicha() {
		Random r = new Random();
		int piezaRandom = r.nextInt(6);

		switch (piezaRandom) {
		case 0:
			imagenPieza = new Imagen("./img/PiezasTetris/PiezaCuadrado.png");
			break;
		case 1:
			imagenPieza = new Imagen("./img/PiezasTetris/PiezaI.png");
			break;
		case 2:
			imagenPieza = new Imagen("./img/PiezasTetris/PiezaJ.png");
			break;
		case 3:
			imagenPieza = new Imagen("./img/PiezasTetris/PiezaL.png");
			break;
		case 4:
			imagenPieza = new Imagen("./img/PiezasTetris/PiezaS.png");
			break;
		case 5:
			imagenPieza = new Imagen("./img/PiezasTetris/PiezaT.png");
			break;
		case 6:
			imagenPieza = new Imagen("./img/PiezasTetris/PiezaZ.png");
			break;

		}
		imagenPieza.cerrar();
		return imagenPieza.getArray2D();

	}

	public int[][] colocarImagenPieza(int[][] matriz, int[][] rotada) {

		int posicionY = 273;
		int posicionX = 180;
		setPosicionFichaX(posicionX);
		setPosicionFichaY(posicionY);
		for (int i = 0; i < rotada.length; i++) {
			posicionX = 180;
			for (int j = 0; j < rotada[0].length; j++) {
				matriz[posicionY][posicionX] = rotada[i][j];
				posicionX++;
			}
			posicionY++;
		}

		return matriz;
	}

	public int getPosicionFichaY() {
		return posicionFichaY;
	}

	public void setPosicionFichaY(int posicionFichaY) {
		this.posicionFichaY = posicionFichaY;
	}

	int[][] colocarPuntuacion(int[][] matriz) {
		int copia = puntuacion;
		int longitud = 0;

		// Contar cuántos dígitos tiene
		while (copia > 0) {
			copia /= 10;
			longitud++;
		}

		int punt = puntuacion; // usar copia para no modificar el original

		// Extraer dígitos de izquierda a derecha
		for (int i = longitud - 1; i >= 0; i--) {
			int divisor = (int) Math.pow(10, i);
			int digito = punt / divisor;
			System.out.println(digito);
			matriz = pintarNumero(digito, matriz);
			punt = punt % divisor;
			setPuntuacionColumna(getPuntuacionColumna() + 40);
		}

		return matriz;
	}

	private int[][] pintarNumero(int digito, int[][] matriz) {
		Imagen imgNumero = null;

		switch (digito) {
		case 0:
			imgNumero = new Imagen("./img/Numeros/numero0.png");
			break;
		case 1:
			imgNumero = new Imagen("./img/Numeros//numero1.png");
			break;
		case 2:
			imgNumero = new Imagen("./img/Numeros/numero2.png");
			break;
		case 3:
			imgNumero = new Imagen("./img/Numeros/numero3.png");
			break;
		case 4:
			imgNumero = new Imagen("./img/Numeros/numero4.png");
			break;
		case 5:
			imgNumero = new Imagen("./img/Numeros/numero5.png");
			break;
		case 6:
			imgNumero = new Imagen("./img/Numeros/numero6.png");
			break;
		case 7:
			imgNumero = new Imagen("./img/Numeros/numero7.png");
			break;
		case 8:
			imgNumero = new Imagen("./img/Numeros/numero8.png");
			break;
		case 9:
			imgNumero = new Imagen("./img/Numeros/numero9.png");
			break;
		}

		imgNumero.cerrar();

		int k = PUNTUACIONFILA;
		int f = puntuacionColumna;

		for (int i = 0; i < imgNumero.getArray2D().length; i++) {
			f = puntuacionColumna;
			for (int j = 0; j < imgNumero.getArray2D()[0].length; j++) {
				matriz[k][f] = imgNumero.getArray2D()[i][j];
				f++;
			}
			k++;
		}

		return matriz;
	}

	public int[][] rotar90Grados() {
		System.out.println("inicio");
		
		int [][] estadoActual = getImagenPieza().getArray2D();
		int[][] rotada = new int[estadoActual[0].length][estadoActual.length];
		int k = estadoActual.length -1;
		for (int i = 0; i < estadoActual.length; i++) {
			System.out.println("aaaa");
			for (int j = 0; j < estadoActual[0].length; j++) {
				rotada[j][k] = estadoActual[i][j];
			}
			k--;
		}
		System.out.println("fin");
		borrarPiezaActual();

		return rotada;

	}

	private void borrarPiezaActual() {
		int[][] matriz = getMatrizTablero();
		int[][] pieza = getImagenPieza().getArray2D();
		int posicionY = getPosicionFichaY();
		int posicionX = getPosicionFichaX();

		for (int i = 0; i <= pieza.length; i++) {
			for (int j = 0; j <= pieza[0].length; j++) {
				matriz[posicionY + i][posicionX + j] = getMatrizTableroVacio()[posicionY + i][posicionX + j];
			}
		}
		setMatrizTablero(matriz);
	}

	

	public static void main(String[] args) {

		Prueba pr = new Prueba();

		Imagen img = new Imagen("./img/Tableros/tierra.png");

		int[][] matriz = img.getArray2D();
		pr.setMatrizTablero(matriz);
		int[][] matrizCopia = new int[matriz.length][matriz[0].length];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				matrizCopia[i][j] = matriz[i][j];
			}
		}
		pr.setMatrizTableroVacio(matrizCopia);
		for (int i = 0; i < matriz[0].length; i++) {
			matriz[180][i] = -1;

		}
		int[][] matrizFicha;

		matrizFicha = pr.ProximaFicha();
		matriz = pr.colocarImagenPieza(matriz, matrizFicha);
		matriz = pr.colocarPuntuacion(matriz);
		
		
		
		
		for (int i = 85; i < 190; i++) {
			for (int j = 440; j < 565; j++) {
				matriz[i][j]=-1;
			}
		}
		
		img.setArray2D(matriz);
		img.ver();
		
		
//		matriz = pr.getMatrizTablero();
//		img.setArray2D(matriz);
//		img.ver();

		
		matrizFicha = pr.rotar90Grados();
	
		//img.setArray2D(pr.getMatrizTablero());
		//img.ver();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

		//matriz = pr.colocarImagenPieza(matriz, matrizFicha);
		for (int i = 120; i < 180; i++) {
			for (int j = 50; j < 350; j++) {
				matriz[i][j]=pr.getMatrizTableroVacio()[i][j];
			}
		}
		pr.puntuacion=454;
		matriz = pr.colocarPuntuacion(matriz);
		img.setArray2D(pr.getMatrizTablero());
		img.ver();
		System.out.println("holaaaaa");
	}
}
