package com.example;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class PixelScalerMain extends JFrame {
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			PixelScalerMain app = new PixelScalerMain();
			app.setVisible(true);
		});
	}
	
	public PixelScalerMain() {
		setSize(650, 500);
        setTitle("Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
	}
}
