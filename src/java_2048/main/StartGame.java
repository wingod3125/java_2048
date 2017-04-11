package java_2048.main;

import java_2048.control.GameControl;
import java_2048.ui.Game_2048;

import javax.swing.JFrame;

public class StartGame {

	/**
	 * �������ڼ�����Ϸ������Ҫ����Դ������ʾ��Ϸ�������
	 */
	public static void main(String[] args) {
		System.out.println("hello my friend!");
		JFrame frame = new JFrame();
		frame.setSize(500, 600);
		frame.setResizable(false);// ���ɸı䴰���С
		frame.setLocationRelativeTo(null);// ����λ�þ���
		frame.setTitle("Java_2048");// ���ñ���
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ���ùرմ���ͬʱ�˳�����

		// ������Ϸ���Ĵ���
		Game_2048 game = new Game_2048();
		frame.add(game);

		//��Ӽ�������
		GameControl control=new GameControl(game);
		frame.addKeyListener(control);
		
		frame.setVisible(true);// ��ʾ����

	}

}
