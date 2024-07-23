import javax.swing.JFrame;

public class MyFrame extends JFrame {
	
	GamePlay play = new GamePlay();
	
	MyFrame(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(700, 600);
		this.setResizable(false);
		this.setTitle("Brick Breaker");
		this.add(play);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
	}

}
