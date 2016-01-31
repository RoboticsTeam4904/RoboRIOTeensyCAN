package org.botprovoking.can;

/**
 * Class for interfacing with a Teensy running
 * TeensyNeoPixelCAN code.
 *
 */
public abstract class CustomNeoPixels extends CustomCAN {
	protected byte R;
	protected byte G;
	protected byte B;
	protected int mode;
	protected int progress;
	
	/**
	 * Constructor
	 * ID should be between 0x600 and 0x700.
	 * 
	 * @param name
	 * @param id
	 */
	public CustomNeoPixels(String name, int id) {
		super(name, id);
		R = 0;
		G = 0;
		B = 0;
		mode = 0;
		progress = 0;
	}
	
	protected final void setMode(int mode) { // People should be forced to overwrite this with a more user friendly mode system
		this.mode = mode;
	}
	
	/**
	 * Sets the color of the pattern.
	 * Values are 0-255.
	 * 
	 * @param R
	 * @param G
	 * @param B
	 */
	public void setColor(int R, int G, int B) {
		this.R = (byte) R;
		this.G = (byte) G;
		this.B = (byte) B;
	}
	
	/**
	 * Sets the progress of the pattern.
	 * Values are 0 to 32768.
	 * 
	 * @param progress
	 */
	public void setProgress(int progress) {
		this.progress = progress;
	}
	
	/**
	 * Writes LED pattern to Teensy.
	 */
	public void update() {
		super.write(new byte[] {B, G, R, 0x00, (byte) (progress >> 8), (byte) (progress & 0xFF), (byte) (mode >> 8), (byte) (mode & 0xFF)});
		super.read();
	}
}
