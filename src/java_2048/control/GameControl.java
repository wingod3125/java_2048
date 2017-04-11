package java_2048.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import java_2048.ui.GameService;
import java_2048.ui.Game_2048;

/*
 * �����࣬���ڴ�����̲������������������ƶ�������
 */
public class GameControl extends KeyAdapter {
	Game_2048 game;

	public GameControl(Game_2048 game) {
		this.game = game;
	}

	// ��Ӧ���̰��²���
	@Override
	public void keyPressed(KeyEvent e) {
		int isMove;// ��ʱ�����洢GameService�ƶ������ķ���ֵ��0��ʾû���ƶ�����1��ʾ�ƶ���
		int isRemove;// ��ʱ�����洢GameService������������ֵ��0��ʾû����������1��ʾ������
		int choice;// ��ʱ�����洢������ѡ�񴰿ڣ�ѡ��ť�ķ���ֵ
		GameService gameService = game.getGameService();
		switch (e.getKeyCode()) {
		case 37:// ��ӦС�������
			System.out.println("�����");
			isMove = gameService.moveLeft();
			isRemove = gameService.removeLeft();
			if (isMove == 1 || isRemove == 1) {// ����ƶ��ˣ��������µķ��飬���򲻲���
				gameService.newBlock();
			}
			break;
		case 38:// ��ӦС�����ϼ�
			System.out.println("�ϼ���");
			isMove = gameService.moveUp();
			isRemove = gameService.removeUp();
			if (isMove == 1 || isRemove == 1) {// ����ƶ��ˣ��������µķ��飬���򲻲���
				gameService.newBlock();
			}
			break;
		case 39:// ��ӦС�����Ҽ�
			System.out.println("�Ҽ���");
			isMove = gameService.moveRight();
			isRemove = gameService.removeRight();
			if (isMove == 1 || isRemove == 1) {// ����ƶ��ˣ��������µķ��飬���򲻲���
				gameService.newBlock();
			}
			break;
		case 40:// ��ӦС�����¼�
			System.out.println("�¼���");
			isMove = gameService.moveDown();
			isRemove = gameService.removeDown();
			if (isMove == 1 || isRemove == 1) {// ����ƶ��ˣ��������µķ��飬���򲻲���
				gameService.newBlock();
			}
			break;
		case 27:// ��Ӧ����esc��
			System.out.println("Esc����");
			// ���ѡ��OK������0��ѡ��CANCEL����2
			choice = JOptionPane.showConfirmDialog(null, "		�˳���Ϸ?", "��Ϸ��ʾ",
					JOptionPane.OK_CANCEL_OPTION);
			if (choice == 0) {
				System.exit(0);
			}
			break;
		case 112:// ��Ӧ����F1��
			System.out.println("F1");
			// ���ѡ��OK������0��ѡ��CANCEL����2
			choice = JOptionPane.showConfirmDialog(null, "		���¿�ʼ��Ϸ��", "��Ϸ��ʾ",
					JOptionPane.OK_CANCEL_OPTION);
			if (choice == 0) {
				gameService.refreshHighscore();
				gameService.restart();
			}
			break;

		}
		game.repaint();// �ƶ�����Ҫ������Ϸ���棬�������Game_2048��paint�����ػ�����
		gameService.isGameOver();
		game.repaint();
	}

}
