package com.jotard.controller;

import com.jotard.structure.player.PlayerManager;

public interface GameModelObserver {
	public void addViewUpdater(GameViewUpdater o);
	public void removeViewUpdate(GameViewUpdater o);
	public void requestUpdateView();
	public void requestUpdateEndGame(PlayerManager pm);
	public void requestNotifyError(String message);
}
