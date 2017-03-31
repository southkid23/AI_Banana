package phase_2;

import java.awt.Graphics;
import java.awt.Color;

import javax.swing.JComponent;
import javax.swing.JFrame;

import java.util.*;

public class MyCanvas extends JComponent {

	private List<String> pop11 = new ArrayList<String>();

	public void paint(Graphics g) {
		for (int x = 20; x <= 300; x+=20) {
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(0, x, 300, x);
		}

		for (int y = 20; y <= 300; y+=20) {
			g.setColor(Color.LIGHT_GRAY);
			g.drawLine(y, 0, y, 300);	
		}

		for (int i = 0; i<pop11.size() ;i+=2) {
			int x = Integer.parseInt(pop11.get(i));
			int y = Integer.parseInt(pop11.get(i+1));
			g.setColor(Color.BLACK);
			// Reduced by 5 to center cities in the canvas
			g.drawOval (x-5, y-5, 10, 10);
		}

		int j = 0;
		int x1, y1, x2, y2;
		for (int i = 0; i<pop11.size()-2;i+=2) {
			j = i + 2;

			// City one
			x1 = Integer.parseInt(pop11.get(i));
			y1 = Integer.parseInt(pop11.get(i+1));

			// City Two
			if (j > pop11.size()){
				x2 = Integer.parseInt(pop11.get(0));
				y2 = Integer.parseInt(pop11.get(0));
			} else {
				x2 = Integer.parseInt(pop11.get(j));
				y2 = Integer.parseInt(pop11.get(j+1));
			}
			
			g.setColor(Color.RED);
			g.drawLine(x1, y1, x2, y2);
		}

		x1 = Integer.parseInt(pop11.get(j));
		y1 = Integer.parseInt(pop11.get(j+1));
		
		x2 = Integer.parseInt(pop11.get(0));
		y2 = Integer.parseInt(pop11.get(1));

		g.drawLine(x1, y1, x2, y2);
	}

	public void setCoordinates(List<String> pop) {
		pop11 = pop;
	}
}