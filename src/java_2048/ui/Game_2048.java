package java_2048.ui;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * ��Ϸ��ʾ��壬������ʾ��Ϸ��������
 */
public class Game_2048 extends JPanel {
	/**
	 * 
	 */
	Background background;
	GameService gameService;

	public Game_2048() {
		background = new Background();
		gameService = new GameService();
	}

	@Override
	public void paint(Graphics g) {
		background.drawBackground(g);
		gameService.gamePaint(g);
	}

	public GameService getGameService() {
		return gameService;
	}

	public void setGameService(GameService gameService) {
		this.gameService = gameService;
	}

}
