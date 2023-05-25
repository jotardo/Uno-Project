package com.jotard.controller;

public interface GameModelObserver {
	public void addViewUpdater(GameViewUpdater o);
	public void removeViewUpdate(GameViewUpdater o);
	public void requestUpdateView();
}
