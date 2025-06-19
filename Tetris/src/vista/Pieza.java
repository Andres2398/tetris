package vista;
/**
 * Clase enum con todas las posibles piezas del juego
 */
public enum Pieza {
	    PIEZAT("./img/PiezasTetris/PiezaT.png",
	    		new boolean[][] {
	            	{ false, true, false },
	            	{ true, true, true }
	        }
	    ),
	    PIEZACUADRADO("./img/PiezasTetris/PiezaCuadrado.png",
	    		new boolean[][] {
	    			{ true, true },
	    			{ true, true }
	        }
	    ),
	    PIEZAI("./img/PiezasTetris/PiezaI.png", 
	    		new boolean [][] {
	    			{true},
	    			{true},
	    			{true},
	    			{true},
	    	}
	    ),
	    PIEZAJ("./img/PiezasTetris/PiezaJ.png",
	    		new boolean [][] {
	    			{false, true},
	    			{false, true},
	    			{true, true}
	    	}
	    ),
	    PIEZAL("./img/PiezasTetris/PiezaL.png",
	    		new boolean [][] {
	    			{true, false},
	    			{true, false},
	    			{true, true},
	    				
	    	}
	    ),
	    PIEZAS("./img/PiezasTetris/PiezaS.png",
	    		new boolean [][] {
					{false,true,true},
					{true,true,false},	
	    	}
	    ),
	    PIEZAZ("./img/PiezasTetris/PiezaZ.png",
	    		new boolean [][] {
	    			{true,true,false},	
	    			{false,true,true},
	    	}
				
	    );
	    
	    
	

	private final Imagen imagen;
	
	private final boolean[][] forma;
	/**
	 * Constructor de Pieza 
	 * @param ruta de la imagen de cada pieza 
	 * @param forma matriz de boolean con la forma de cada pieza
	 */
	Pieza(String ruta, boolean[][] forma) {
		this.imagen =  new Imagen(ruta);
		imagen.cerrar();
		this.forma = forma;
	}
	/**
	 * Metodo para obtener la imagen 
	 * @return imagen de la pieza
	 */
	public Imagen getImagen() {
		
		return imagen;
	}
	/**
	 * metodo para obtener la matriz de boolean 
	 * @return matriz de boolean 
	 */
	public boolean[][] getForma() {
		return forma;
	}

}
