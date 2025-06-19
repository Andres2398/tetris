package modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class LogicaTest {
	private Logica logica;
	  @BeforeEach
	    void setUp() {
	         logica = new Logica();
	    }
	  
	  
	  
	    @Test
	    void testInicialPuntuacionEsCero() {
	        assertEquals(0, logica.getPuntuacion());
	    }

	    @Test
	    void testSetYGetPuntuacion() {
	        logica.setPuntuacion(100);
	        assertEquals(100, logica.getPuntuacion());
	    }

	    @Test
	    void testPiezaNoEstaFijaAlInicio() {
	        assertFalse(logica.isPiezaFija());
	    }

	    @Test
	    void testResetPiezaFija() {
	        logica.resetPiezaFija();
	        assertFalse(logica.isPiezaFija());
	    }

	    @Test
	    void testSetAndGetMatrizPiezaActual() {
	        int[][] pieza = {
	            {1, 1},
	            {1, 1}
	        };
	        logica.setMatrizPiezaActual(pieza);
	        assertArrayEquals(pieza, logica.getMatrizPiezaActual());
	    }

	    @Test
	    void testColocarYPintarPieza() {
	        int[][] juego = new int[1000][700]; 
	        int[][] pieza = {
	            {1, 1},
	            {1, 1}
	        };
	        logica.setMatrizPiezaActual(pieza);
	        int[][] actualizado = logica.pintarFicha(juego);

	        assertEquals(1, actualizado[223][285]);
	        assertEquals(1, actualizado[223][286]);
	        assertEquals(1, actualizado[224][285]);
	        assertEquals(1, actualizado[224][286]);
	    }

	    @Test
	    void testComprobarFin() {
	        // Supone que Tablero.fin() devuelve false al inicio
	        assertFalse(logica.comprobarFin());
	    }

	    @Test
	    void testBorrarPiezaPequenaYpintar() {
	        int[][] juego = new int[200][500];
	        int[][] pieza = {
	            {2, 2},
	            {2, 2}
	        };
	        int[][] resultado = logica.pintarPiezaPequena(pieza, juego);
	        assertEquals(2, resultado[85][440]);
	        assertEquals(2, resultado[86][440]);
	    }
	}