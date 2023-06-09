package com.jotard.controller;

import java.util.List;

import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

public interface GameViewUpdater {
	public void receiveViewUpdate(List<PlayerManager> pList, Card lastPlayedCard, boolean normalOrder);
	public void receiveEndGameUpdate(PlayerManager pm);
	public void receiveNotifyError(String message);
	public void receiveNotifyStatus(String message);
}
