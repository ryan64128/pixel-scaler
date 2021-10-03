package com.example;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class PixelScaler extends JPanel{
	private static final long serialVersionUID = 1L;
	final int SCALE = 4;
	private File[] files;
	
	private JFileChooser fileChooser;
	
	public PixelScaler() {
		try {
			this.fileChooser = new JFileChooser("images");
			fileChooser.setMultiSelectionEnabled(true);
			int result = fileChooser.showOpenDialog(this);
			if (result == JFileChooser.APPROVE_OPTION) {
				this.files = fileChooser.getSelectedFiles();
				String path = files[0].getCanonicalPath();
				int last = path.lastIndexOf("\\");
				String dirPath = path.substring(0, last) + "\\scaled";
				File f = new File(dirPath);
				if(!f.exists()) {
					f.mkdir();
				}
			}
			for (int i=0; i<files.length; i++) {
				String path = files[i].getCanonicalPath();
				Image img = new ImageIcon(path).getImage();
				BufferedImage bufImg = new BufferedImage(this.SCALE * img.getWidth(null), SCALE * img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
				Graphics2D graphics = (Graphics2D) bufImg.getGraphics();
				graphics.scale(SCALE, SCALE);
				graphics.drawImage(img, 0, 0, null);
				graphics.dispose();
				int last = path.lastIndexOf("\\");
				String outputPath = path.substring(0, last) + "\\scaled\\" + path.substring(last);
				ImageIO.write(bufImg, "png", new File(outputPath));
			}
//	        this.img = new ImageIcon("images/attack_1.png").getImage();
//
//	        this.image = new BufferedImage(SCALE * img.getWidth(null),
//	                                             SCALE * img.getHeight(null),
//	                                             BufferedImage.TYPE_INT_ARGB);
//
//	        Graphics2D grph = (Graphics2D) this.image.getGraphics();
//	        grph.scale(SCALE, SCALE);
//
//	        // everything drawn with grph from now on will get scaled.
//
//	        grph.drawImage(img, 0, 0, null);
//	        grph.dispose();
//
//	        ImageIO.write(image, "png", new File("images/attack_1_scaled.png"));
//			
			//this.image = ImageIO.read(new File("images/attack_1.png"));
			System.out.println("opened image file");
			
		}
		catch(IOException e) {
			System.out.println("error opening file..." + e);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i=0; i<files.length; i++) {
			Image img = getImage(files[i]);
			g.drawImage(img, i*100, 0, img.getWidth(null), img.getHeight(null), null);
		}
	}
	
	public Image getImage(File f) {
		try {
			return new ImageIcon(f.getCanonicalPath()).getImage();
		}
		catch(IOException e) {
			return null;
		}
	}
}
