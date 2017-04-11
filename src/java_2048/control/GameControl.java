package java_2048.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import java_2048.ui.GameService;
import java_2048.ui.Game_2048;

/*
 * 控制类，用于处理键盘操作，包括上下左右移动和消除
 */
public class GameControl extends KeyAdapter {
	Game_2048 game;

	public GameControl(Game_2048 game) {
		this.game = game;
	}

	// 响应键盘按下操作
	@Override
	public void keyPressed(KeyEvent e) {
		int isMove;// 临时变量存储GameService移动方法的返回值，0表示没有移动过，1表示移动了
		int isRemove;// 临时变量存储GameService消除方法返回值，0表示没有消除过，1表示消除了
		int choice;// 临时变量存储弹出的选择窗口，选择按钮的返回值
		GameService gameService = game.getGameService();
		switch (e.getKeyCode()) {
		case 37:// 响应小键盘左键
			System.out.println("左键盘");
			isMove = gameService.moveLeft();
			isRemove = gameService.removeLeft();
			if (isMove == 1 || isRemove == 1) {// 如果移动了，则生成新的方块，否则不操作
				gameService.newBlock();
			}
			break;
		case 38:// 响应小键盘上键
			System.out.println("上键盘");
			isMove = gameService.moveUp();
			isRemove = gameService.removeUp();
			if (isMove == 1 || isRemove == 1) {// 如果移动了，则生成新的方块，否则不操作
				gameService.newBlock();
			}
			break;
		case 39:// 响应小键盘右键
			System.out.println("右键盘");
			isMove = gameService.moveRight();
			isRemove = gameService.removeRight();
			if (isMove == 1 || isRemove == 1) {// 如果移动了，则生成新的方块，否则不操作
				gameService.newBlock();
			}
			break;
		case 40:// 响应小键盘下键
			System.out.println("下键盘");
			isMove = gameService.moveDown();
			isRemove = gameService.removeDown();
			if (isMove == 1 || isRemove == 1) {// 如果移动了，则生成新的方块，否则不操作
				gameService.newBlock();
			}
			break;
		case 27:// 响应键盘esc键
			System.out.println("Esc键盘");
			// 如果选择OK，返回0，选择CANCEL返回2
			choice = JOptionPane.showConfirmDialog(null, "		退出游戏?", "游戏提示",
					JOptionPane.OK_CANCEL_OPTION);
			if (choice == 0) {
				System.exit(0);
			}
			break;
		case 112:// 响应键盘F1键
			System.out.println("F1");
			// 如果选择OK，返回0，选择CANCEL返回2
			choice = JOptionPane.showConfirmDialog(null, "		重新开始游戏？", "游戏提示",
					JOptionPane.OK_CANCEL_OPTION);
			if (choice == 0) {
				gameService.refreshHighscore();
				gameService.restart();
			}
			break;

		}
		game.repaint();// 移动后需要更新游戏界面，尽快调用Game_2048的paint方法重画界面
		gameService.isGameOver();
		game.repaint();
	}

}
