import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.swing.SwingUtilities;

import com.jotard.gui.Main;

public class Test {
	public static void main(String[] args) throws IOException, InvocationTargetException, InterruptedException {
		SwingUtilities.invokeLater(new Main());
	}
}
