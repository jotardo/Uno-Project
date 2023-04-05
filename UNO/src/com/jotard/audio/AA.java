package com.jotard.audio;

import java.io.IOException;
import java.io.InputStream;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.Control;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.SwingUtilities;

public class AA {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				InputStream i = AA.class
						.getResourceAsStream("/audio/dearly-beloved-kingdom-hearts-[music-box]-By-Tuna.wav");
				try {
					AudioInputStream audioStream = AudioSystem.getAudioInputStream(i);
					Clip clip = AudioSystem.getClip();
					clip.open(audioStream);
					clip.start();
					System.out.println(clip.isControlSupported(FloatControl.Type.VOLUME));
					while (clip.isOpen()) {
					}
					clip.stop();
					clip.close();
					audioStream.close();
				} catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
