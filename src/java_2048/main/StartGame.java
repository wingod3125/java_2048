package java_2048.main;

import java_2048.control.GameControl;
import java_2048.ui.Game_2048;

import javax.swing.JFrame;

public class StartGame {

	/**
	 * 该类用于加载游戏运行需要的资源，并显示游戏窗体界面
	 */
	public static void main(String[] args) {
		System.out.println("hello my friend!");
		JFrame frame = new JFrame();
		frame.setSize(500, 600);
		frame.setResizable(false);// 不可改变窗体大小
		frame.setLocationRelativeTo(null);// 设置位置居中
		frame.setTitle("Java_2048");// 设置标题
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 设置关闭窗口同时退出程序

		// 加载游戏面板的代码
		Game_2048 game = new Game_2048();
		frame.add(game);

		//添加监听功能
		GameControl control=new GameControl(game);
		frame.addKeyListener(control);
		
		frame.setVisible(true);// 显示窗体

	}

}
