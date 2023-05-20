import java.io.IOException;

import javax.swing.SwingUtilities;

import com.jotard.controller.GameController;

public class Test {
	public static void main(String[] args) throws IOException {
		SwingUtilities.invokeLater(new GameController());
	}
}
