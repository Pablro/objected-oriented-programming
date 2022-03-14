package breakout.gui;

import static org.junit.jupiter.api.Assertions.*;

import java.awt.EventQueue;

import javax.swing.JFrame;

import org.junit.jupiter.api.Test;

import breakout.BreakoutState;
import breakout.GameMap;

class BreakoutApplicationTest {
	public static final String initMap = """
			##########
			##########
			##########
			##########
			     o

			     =

			""";
	@Test
	void test() {
		BreakoutState state = GameMap.createStateFromDescription(initMap);
		EventQueue.invokeLater(() -> {
			GameView mazeView = new GameView(state);
			JFrame frame = new JFrame("Pac-Man");
			frame.getContentPane().add(mazeView);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		});
	}

		
	}


