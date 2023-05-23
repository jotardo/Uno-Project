package com.jotard.controller;

public interface GameModelObserver {
	public void addObservable(GameModelObservable o);
	public void removeObservable(GameModelObservable o);
	public void notifyBoardDraw();
}
