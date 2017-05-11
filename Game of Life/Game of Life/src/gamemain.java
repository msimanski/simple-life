import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;

public class gamemain extends JPanel {
	
	public static boolean paused = true;
	
	public static int[][] worldsize = new int[1000][1000]; // declare class variables
	public static world playspace = new world(worldsize);
	public static gamemain panel;
	
	public static Timer timer = new Timer(250, new ActionListener() { // set up timer thread

		@Override
		public void actionPerformed(ActionEvent e) {
			
			//System.out.println("Debug: timer ticked");
			playspace.updateWorldState();
			panel.repaint();
			
		}
		
	});

	@Override
	public void paintComponent(Graphics g) { // refresh screen, called by repaint()
		
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		//System.out.println("Debug: hit painter");
		
		for(int i = 1; i < 999; i++) {
			for(int j = 1; j < 999; j++) {
				if(playspace.getWorldStateAt(i, j) == 1) {
					g.drawRect(i, j, 1, 1);
				}
			}
		}
		
	}
	
	public static class listener implements KeyListener { // key listener, not working on Win-blows

		@Override
		public void keyTyped(KeyEvent e) {

			if(e.getKeyChar() == ' ') {
				if(paused) {
					timer.start();
					paused = false;
					System.out.println("Unpaused");
				}
				else {
					timer.stop();
					paused = true;
					System.out.println("Paused");
				}
			}
			
		}

		@Override
		public void keyPressed(KeyEvent e) {
			// unused
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// unused
			
		}		
	}
	
	public static class mouselistener implements MouseListener { // unused, mouse listener

		@Override
		public void mouseClicked(MouseEvent e) {
			
			System.out.println("Debug: got click");
			int x, y;
			x = e.getX();
			y = e.getY();
			playspace.placeCell(x, y);
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// unused
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// unused
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// unused
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// unused
			
		}
		
	}
	
public static void main(String[] args) { // initialization
		
	    JFrame gui = new JFrame();
            gui.setTitle("Game of Life");
            gui.setSize(1000, 1000);
            gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            panel = new gamemain();
            panel.addKeyListener(new listener());
            panel.addMouseListener(new mouselistener());
            panel.setBackground(Color.WHITE);
            panel.setOpaque(true);
            Container pane = gui.getContentPane();
            pane.add(panel);
            gui.setVisible(true);
            timer.start();
	
	}


}
