package vista;

import java.util.Random;
import java.util.Scanner;

public class Interfaz {
	private Scanner sc;
	private Imagen imagenJuego;
	private int puntuacion;
	private final static int PUNTUACIONFILA = 120;
	private int puntuacionColumna;
	private int[][] matrizImagenJuego;
	private Imagen imagenPieza;
	private boolean matrizBoolean[][];

	public Imagen getImagenPieza() {
		return imagenPieza;
	}

	public void setImagenPieza(Imagen imagenPieza) {
		this.imagenPieza = imagenPieza;
	}

	public Interfaz() {
		sc = new Scanner(System.in);
		puntuacion = 0;
		puntuacionColumna = 80;

	}

	public boolean[][] getMatrizBoolean() {
		return matrizBoolean;
	}

	public void setMatrizBoolean(boolean[][] matrizBoolean) {
		this.matrizBoolean = matrizBoolean;
	}

	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
		puntuacionColumna = 80;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public int getPuntuacionColumna() {
		return puntuacionColumna;
	}

	public void setPuntuacionColumna(int puntuacionColumna) {
		this.puntuacionColumna = puntuacionColumna;
	}

	public static int getPuntuacionfila() {
		return PUNTUACIONFILA;
	}

	public int[][] getMatrizImagenJuego() {
		return matrizImagenJuego;
	}

	public void setMatrizImagenJuego(int[][] matrizImagenJuego) {
		this.matrizImagenJuego = matrizImagenJuego;
	}

	public void saludo() {
		System.out.println("Saludos.... pulse c para comenzar el juego");
		boolean buena = false;
		while (!buena) {
			String input = sc.nextLine();
			if (input.equals("c") || input.equals("C")) {
				buena = true;
			} else
				System.out.println("Caracter erroneo pulse c para comenzar");
		}

	}

	public void iniciarImagen() {
		imagenJuego = new Imagen("./img/Tableros/tierra.png");
		setMatrizImagenJuego(imagenJuego.getArray2D());

	}

	public void pintarPuntuacion() {
		borrarPuntuacion();
		int copia = puntuacion;
		int longitud = 0;
		if (copia == 0)
			longitud = 1;
		else {
			while (copia > 0) {
				copia /= 10;
				longitud++;
			}
		}
		int punt = puntuacion;

		for (int i = longitud - 1; i >= 0; i--) {
			int divisor = (int) Math.pow(10, i);
			int digito = punt / divisor;
			pintarNumero(digito);
			punt = punt % divisor;
			setPuntuacionColumna(getPuntuacionColumna() + 40);

		}
		setPuntuacionColumna(80);
	}

	private void borrarPuntuacion() {

	}

	private void pintarNumero(int digito) {

		int matriznumero[][] = null;
		switch (digito) {
		case 0:
			matriznumero = Numero.NUMERO0.getImagen().getArray2D();
			break;
		case 1:
			matriznumero = Numero.NUMERO1.getImagen().getArray2D();
			break;
		case 2:
			matriznumero = Numero.NUMERO2.getImagen().getArray2D();
			break;
		case 3:
			matriznumero = Numero.NUMERO3.getImagen().getArray2D();
			break;
		case 4:
			matriznumero = Numero.NUMERO4.getImagen().getArray2D();
			break;
		case 5:
			matriznumero = Numero.NUMERO5.getImagen().getArray2D();
			break;
		case 6:
			matriznumero = Numero.NUMERO6.getImagen().getArray2D();
			break;
		case 7:
			matriznumero = Numero.NUMERO7.getImagen().getArray2D();
			break;
		case 8:
			matriznumero = Numero.NUMERO8.getImagen().getArray2D();
			break;
		case 9:
			matriznumero = Numero.NUMERO9.getImagen().getArray2D();
			break;
		}
		int k = PUNTUACIONFILA;
		int f = puntuacionColumna;

		for (int i = 0; i < matriznumero.length; i++) {
			f = puntuacionColumna;
			for (int j = 0; j < matriznumero[0].length; j++) {
				matrizImagenJuego[k][f] = matriznumero[i][j];
				f++;
			}
			k++;
		}

	}

	public int[][] elegirPiezaRandom() {
		Random r = new Random();
		int piezaRandom = r.nextInt(7);

		switch (piezaRandom) {
		case 0:
			imagenPieza = Pieza.PIEZACUADRADO.getImagen();
			setMatrizBoolean(Pieza.PIEZACUADRADO.getForma());
			break;
		case 1:
			imagenPieza = Pieza.PIEZAI.getImagen();
			setMatrizBoolean(Pieza.PIEZAI.getForma());
			break;
		case 2:
			imagenPieza = Pieza.PIEZAJ.getImagen();
			setMatrizBoolean(Pieza.PIEZAJ.getForma());
			break;
		case 3:
			imagenPieza = Pieza.PIEZAL.getImagen();
			setMatrizBoolean(Pieza.PIEZAL.getForma());
			break;
		case 4:
			imagenPieza = Pieza.PIEZAS.getImagen();
			setMatrizBoolean(Pieza.PIEZAS.getForma());
			break;
		case 5:
			imagenPieza = Pieza.PIEZAT.getImagen();
			setMatrizBoolean(Pieza.PIEZAT.getForma());
			break;
		case 6:
			imagenPieza = Pieza.PIEZAZ.getImagen();
			setMatrizBoolean(Pieza.PIEZAZ.getForma());
			break;

		}

		return imagenPieza.getArray2D();

	}

	public void repintarImagen(int[][] matrizImagen) {
		imagenJuego.setArray2D(matrizImagen);
		imagenJuego.ver();
		System.out.println("hola");

	}

	public String pedirMovimientos() {
		boolean buena = false;
		String input="";
		while (!buena) {
			System.out.println(
					"Selecciona: \nA para mover a la izquierda \nD para mover a la derecha \nX para rotar a la izquierda \nC para rotar a la derecha \nS para mover hacia abajo");
			input =sc.nextLine();
			if(input.equals("A")||input.equals("a")||input.equals("D")||input.equals("d")||input.equals("X")||input.equals("x")||input.equals("C")||input.equals("c")||input.equals("S")||input.equals("s"))
				buena=true;
			else
				System.out.println("Caracter incorrecto recuerde: ");
		}
		return input;
	}

}
