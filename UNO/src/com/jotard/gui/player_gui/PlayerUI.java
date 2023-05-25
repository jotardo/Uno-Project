package com.jotard.gui.player_gui;

import javax.swing.JPanel;

import com.jotard.structure.player.PlayerManager;

public abstract class PlayerUI extends JPanel {

	private static final long serialVersionUID = 1L;
	public abstract void updateDisplay(PlayerManager playerManager);

}
