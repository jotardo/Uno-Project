package com.jotard.controller;

public interface GameControllerInterface {
	public void doPlayCard(int cardIndex);
	public void doDrawCard();
	public void doActivateWild(String color, int oldCardIndex);
	public void doActivateWildDraw4(String color, int oldCardIndex);
	public void doEndTurn();
	public void doPause();
	public void doResume();
	public void doRestart();
	public void doDestroyView();
}
