package com.jotard.controller;

import java.util.List;

import com.jotard.structure.card.Card;
import com.jotard.structure.player.PlayerManager;

public interface GameModelObservable {
	public void notifyPlayers(List<PlayerManager> pList, Card lastPlayedCard);
}
