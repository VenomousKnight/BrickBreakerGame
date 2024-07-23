import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements ActionListener, KeyListener{
	
	private boolean play = false;
	private int score = 0;
	
	private int bricks = 21;
	private Timer timer;
	private int delay = 1;
	
	
	private int playerX = 310;
	private int ballX = 120;
	private int ballY = 350;
	
	private int ballxdir = -3;
	private int ballydir = -5;
	
	private MapGenerator map;
	
	
	public GamePlay() {
		
		map = new MapGenerator(3,7);
		this.addKeyListener(this);
		this.setFocusable(true);
		this.setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
		
	}
	
	public void paint(Graphics g) {
		//background
		g.setColor(Color.black);
		g.fillRect(1,1, 682, 592);
		
		//borders
		g.setColor(Color.yellow);
		g.fillRect(0,0,2,592);
		g.fillRect(0,0,692,3);
		g.fillRect(682,0,3,592);
		
		//Draw bricks
		map.draw((Graphics2D)g);
		
		//scores
		g.setColor(Color.white);
		g.setFont(new Font("Ariel", Font.BOLD, 20));
		g.drawString(""+ score, 590, 30);
		//the paddle
		
		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);
		
		//the ball
		g.setColor(Color.yellow);
		g.fillOval(ballX, ballY, 20, 20);
		
		if(bricks <= 0) {
			play = false;
			ballxdir = 0;
			ballydir = 0;
			
			g.setColor(Color.green);
			g.setFont(new Font("Ariel", Font.BOLD, 70));
			g.drawString("YOU WON" , 130, 350);
			
			g.setFont(new Font("Ariel", Font.BOLD, 20));
			g.drawString("press Enter to restart" , 240, 400);
		}
		
		if(ballY > 570) {
			play = false;
			ballxdir = 0;
			ballydir = 0;
			
			g.setColor(Color.red);
			g.setFont(new Font("Ariel", Font.BOLD, 70));
			g.drawString("GAME OVER" , 130, 350);
			
			g.setFont(new Font("Ariel", Font.BOLD, 20));
			g.drawString("press Enter to restart" , 240, 400);
		}
		
		g.dispose();
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_J) {
			if(playerX <= 10) playerX= 10;
			else moveLeft();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_L) {
			if(playerX >= 580) playerX= 580;
			else moveRight();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			play = true;
			score = 0;
			bricks = 21;
		    playerX = 310;
			ballX = 120;
		    ballY = 350;
		    ballxdir = -1;
		    ballydir = -3;
			
		    map = new MapGenerator(3, 7);
		    play = true;
		    
		    repaint();
			
		}
	}
	
	
	void moveLeft() {
		play = true;
		playerX-= 20;
	}
	
	void moveRight() {
		play = true;
		playerX+= 20;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		timer.start();
		
		if(play == true) {
			
			if(new Rectangle(ballX, ballY, 20, 20).intersects(new Rectangle(playerX, 550, 100, 8))) {
				
		        ballydir *= -1;
			}
			
			A:for(int i = 0; i < map.map.length; i++) {
				for(int j = 0; j < map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						int brickx = j * map.width + 80;
						int bricky = i * map.height + 50;
						
						int width = map.width;
						int height = map.height;
						
						Rectangle rect = new Rectangle(brickx, bricky, width, height);
						Rectangle ball = new Rectangle(ballX, ballY, 20, 20);
						Rectangle brick = rect;
						
						if(ball.intersects(brick)) {
							map.setValue(0, i, j);
							bricks--;
							score += 5;
							
							if(ballX + 19 <= brick.x || ballX + 1 >= brick.x + brick.width) 
								ballxdir *= -1;
							else ballydir *= -1;
							
							break A;
							
						}
						
					}
						
				}
			}
			ballX += ballxdir;
			ballY += ballydir;
			
			if(ballX < 0)ballxdir *= -1;
			if(ballY < 0)ballydir *= -1;
			if(ballY > 570)play = false;
			if(ballX > 664)ballxdir *= -1;
		}
		repaint();
		
	}
	

}
