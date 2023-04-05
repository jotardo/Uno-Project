package com.jotard.gui;

import javax.swing.JPanel;

public abstract class Menu extends JPanel{

	private static final long serialVersionUID = 1L;
	
	public abstract void makeEvents();

	public abstract void killEvents();

}
