package vista;

import java.awt.image.BufferedImage;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Imagen extends JFrame {

	private BufferedImage image;
	private int ancho;
	private int alto;

	/**
	 * Constructs a new, initially invisible window in the default size.
	 * <p>
	 * If there is a security manager set, it is invoked to check
	 * {@code AWTPermission("showWindowWithoutWarningBanner")}. If that check fails
	 * with a {@code SecurityException} then a warning banner is created.
	 *
	 * @exception HeadlessException when {@code GraphicsEnvironment.isHeadless()}
	 *                              returns {@code true}
	 *
	 * @see java.awt.GraphicsEnvironment#isHeadless
	 * 
	 *      Window() throws HeadlessException { GraphicsEnvironment.checkHeadless();
	 *      init((GraphicsConfiguration)null); }
	 */
	public Imagen(String file) {
		this.setTitle("Trabajando con imagenes");

		try {
			this.image = ImageIO.read(new File(file));
			this.ancho = this.image.getWidth();
			this.alto = this.image.getHeight();
		} catch (IOException var3) {
			var3.printStackTrace();
		}

		this.setDefaultCloseOperation(3);
		this.setSize(this.ancho, this.alto);
		this.setResizable(false);
		this.setVisible(true);
	}

	public void setImagen(String file) {
		
		try {
			this.image = ImageIO.read(new File(file));
			this.image = ImageIO.read(new File(file));
			this.ancho = this.image.getWidth();
			this.alto = this.image.getHeight();
		} catch (IOException var3) {
			var3.printStackTrace();
		}
		this.setDefaultCloseOperation(3);
		this.setSize(this.ancho, this.alto);
		this.setResizable(false);
		this.setVisible(true);
	}


	/**
	 * Constructs a new, initially invisible window in default size with the
	 * specified {@code GraphicsConfiguration}.
	 * <p>
	 * If there is a security manager, then it is invoked to check
	 * {@code AWTPermission("showWindowWithoutWarningBanner")} to determine whether
	 * or not the window must be displayed with a warning banner.
	 *
	 * @param gc the {@code GraphicsConfiguration} of the target screen device. If
	 *           {@code gc} is {@code null}, the system default
	 *           {@code GraphicsConfiguration} is assumed
	 * @exception IllegalArgumentException if {@code gc} is not from a screen device
	 * @exception HeadlessException        when
	 *                                     {@code GraphicsEnvironment.isHeadless()}
	 *                                     returns {@code true}
	 *
	 * @see java.awt.GraphicsEnvironment#isHeadless
	 * 
	 *      Window(GraphicsConfiguration gc) { init(gc); }
	 */
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(this.image, 0, 0, (ImageObserver) null);
	}

	/**
	 * {@inheritDoc}
	 * <p>
	 * The method changes the geometry-related data. Therefore, the native windowing
	 * system may ignore such requests, or it may modify the requested data, so that
	 * the {@code Window} object is placed and sized in a way that corresponds
	 * closely to the desktop settings.
	 */
	@Override
	public void setLocation(Point p) {
		super.setLocation(p);
	}

	/**
	 * Makes the Window visible. If the Window and/or its owner are not yet
	 * displayable, both are made displayable. The Window will be validated prior to
	 * being made visible. If the Window is already visible, this will bring the
	 * Window to the front.
	 * 
	 * @see Component#isDisplayable
	 * @see #toFront
	 * 
	 *      {@link #setVisible(boolean)}.
	 */
	public void ver() {
		this.repaint();
	}

	/**
	 * Returns the sequence of images to be displayed as the icon for this window.
	 * <p>
	 * This method returns a copy of the internally stored list, so all operations
	 * on the returned object will not affect the window's behavior.
	 *
	 * @return the copy of icon images' list for this window, or empty list if this
	 *         window doesn't have icon images.
	 * @see #setIconImages
	 * @see #setIconImage(Image)
	 *
	 */
	@SuppressWarnings("unused")
	public java.util.List<Image> getIconImages() {
		java.util.List<Image> icons = null;
		if (icons == null || icons.size() == 0) {
			return new ArrayList<Image>();
		}
		return new ArrayList<Image>(icons);
	}

	public int[][] getArray2D() {
		int[] array = new int[this.ancho * this.alto];
		this.image.getRGB(0, 0, this.ancho, this.alto, array, 0, this.ancho);
		int[][] m = new int[this.alto][this.ancho];

		for (int i = 0; i < this.alto; ++i) {
			for (int j = 0; j < this.ancho; ++j) {
				m[i][j] = array[this.ancho * i + j];
			}
		}

		return m;
	}

	/**
	 * Sets the image to be displayed as the icon for this window.
	 * <p>
	 * This method can be used instead of {@link #setIconImages setIconImages()} to
	 * specify a single image as a window's icon.
	 * <p>
	 * The following statement:
	 * 
	 * <pre>
	 * setIconImage(image);
	 * </pre>
	 * 
	 * is equivalent to:
	 * 
	 * <pre>
	 * ArrayList&lt;Image&gt; imageList = new ArrayList&lt;Image&gt;();
	 * imageList.add(image);
	 * setIconImages(imageList);
	 * </pre>
	 * <p>
	 * Note : Native windowing systems may use different images of differing
	 * dimensions to represent a window, depending on the context (e.g. window
	 * decoration, window list, taskbar, etc.). They could also use just a single
	 * image for all contexts or no image at all.
	 *
	 * @param image the icon image to be displayed.
	 * @see #setIconImages
	 * @see #getIconImages()
	 * 
	 */
	public void setIconImage(Image image) {
		ArrayList<Image> imageList = new ArrayList<Image>();
		if (image != null) {
			imageList.add(image);
		}
		setIconImages(imageList);
	}

	public void setArray2D(int[][] m) {
		int[] array = new int[this.ancho * this.alto];

		for (int i = 0; i < this.alto; ++i) {
			for (int j = 0; j < this.ancho; ++j) {
				array[this.ancho * i + j] = m[i][j];
			}
		}

		this.image.setRGB(0, 0, this.ancho, this.alto, array, 0, this.ancho);
	}

	public int getAncho() {
		return this.ancho;
	}

	public int getAlto() {
		return this.alto;
	}

	public void cerrar() {
		this.setVisible(false);
	}

	/**
	 * Set the cursor image to a specified cursor.
	 * <p>
	 * The method may have no visual effect if the Java platform implementation
	 * and/or the native system do not support changing the mouse cursor shape.
	 * 
	 * @param cursor One of the constants defined by the {@code Cursor} class. If
	 *               this parameter is null then the cursor for this window will be
	 *               set to the type Cursor.DEFAULT_CURSOR.
	 * @see Component#getCursor
	 * @see Cursor
	 * 
	 */
	public void setCursor(Cursor cursor) {
		if (cursor == null) {
			cursor = Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
		}
		super.setCursor(cursor);
	}
}
