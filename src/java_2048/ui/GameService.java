package java_2048.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * ʵ����Ϸ��Ҫ��ʾ���ܺ���Ҫ���̴�����
 */
public class GameService {
	private Data data;// ��Ϸ�еķ�����Ϣ
	private int[][] gameMap;// �洢��Ϸ�ж�Ӧ��4*4���������ͼƬ���������
	public static final int SIZE = 100;// һ���α��������С

	// ��ʼ����Ա
	public GameService() {
		this.data = new Data();
		this.gameMap = new int[4][4];
		this.start();
	}

	/**
	 * ������Ϸ����ϵ����ݣ�1.������ͼƬ�Լ��������� 2.�α��������ڵ�����ͼƬ
	 * 
	 * @param g
	 *            ����; blockIndex
	 *            ����������ͼƬ������ţ��ܹ�1~11�ֱ�Ϊ��1����2��2����4��3����8��4����16��5����32��
	 *            6����64��7����128��8����256��9����512��10����1024��11����2048
	 */
	public void gamePaint(Graphics g) {
		data.drawData(g);// ��������������
		// ���α�������������ͼƬ����
		int blockIndex;// ��ʱ�����洢gameMap��������������ͼƬ�������
		for (int i = 0; i < 4; i++) {// i��ʾ��
			for (int j = 0; j < 4; j++) {// j��ʾ��
				if (gameMap[i][j] != 0) {
					blockIndex = gameMap[i][j];
					switch (blockIndex) {
					case 1:
						drawNumPic(g, Resource.img_2, j, i);
						break;
					case 2:
						drawNumPic(g, Resource.img_4, j, i);
						break;
					case 3:
						drawNumPic(g, Resource.img_8, j, i);
						break;
					case 4:
						drawNumPic(g, Resource.img_16, j, i);
						break;
					case 5:
						drawNumPic(g, Resource.img_32, j, i);
						break;
					case 6:
						drawNumPic(g, Resource.img_64, j, i);
						break;
					case 7:
						drawNumPic(g, Resource.img_128, j, i);
						break;
					case 8:
						drawNumPic(g, Resource.img_256, j, i);
						break;
					case 9:
						drawNumPic(g, Resource.img_512, j, i);
						break;
					case 10:
						drawNumPic(g, Resource.img_1024, j, i);
						break;
					case 11:
						drawNumPic(g, Resource.img_2048, j, i);
						break;

					}
				}
			}
		}
	}

	/**
	 * ���ݴ���Ļ��ʵȲ�������ͼƬ����
	 * 
	 * @param g
	 *            ����Ļ���
	 * @param img
	 *            ��Ҫ������ͼƬ����
	 * @param j
	 *            ����ͼƬ�ڸ��еĵڼ������ӣ���Ӧ��ά����gameMap[i][j]��jֵ
	 * @param i
	 *            ����ͼƬ�ڸ��еĵڼ������ӣ���Ӧ��ά����gameMap[i][j]��iֵ
	 */
	public void drawNumPic(Graphics g, Image img, int x, int y) {
		g.drawImage(img, Background.FG_X + x * SIZE,
				Background.FG_Y + y * SIZE, null);
	}

	// ��ʼ��Ϸ���ܣ���������߷�����0���������2������
	public void start() {
		gameMap = new int[4][4];
		data.setScore(0);
		data.setHighScore(0);
		newBlock();
		newBlock();

	}

	/*
	 * ��4*4�������λ�����ɷ��飬���λ���ϴ��ڷ����������ɣ�ֱ�����ظ�������ͼƬΪ2��4�����ɷ���4�ĸ���Ϊ1/8
	 */
	public void newBlock() {
		Random ran = new Random();
		int i, j;// ���ɷ���ĺ�������λ��
		do {// ��ȡ�����꣬����һ��0~3�������������ȷ�������ɷ����i��j��λ��
			i = ran.nextInt(4);
			j = ran.nextInt(4);
		} while (gameMap[i][j] != 0);// �������λ���Ա�ռ�ã������һ��λ��
		// ȷ������λ�ú�ȷ��������ɵ��Ƿ���ͼƬ2����4
		int index = ran.nextInt(8);// Ϊ�˽�����Ϸ�Ѷȣ����ɷ���4�ĸ���Ϊ1/8
		if (index != 0) {
			gameMap[i][j] = 1;
		} else {
			gameMap[i][j] = 2;
		}
	}

	// ����
	public int moveLeft() {
		System.out.println("���Ʋ���");
		int isMove = 0;// �����ƶ����0Ϊδ�ƶ���1Ϊ�ƶ���
		for (int i = 0; i < 4; i++) {// gameMap[i][j] i��Ӧ����
			for (int j = 1; j < 4; j++) {// j��Ӧ��������1��ʼ��3��������һ�в���Ҫ����
				int mov_i = i;// ��ǰͼƬλ�õĺ�����
				int mov_j = j;// ��ǰͼƬλ�õ�������
				/*
				 * �����ǰ���ӵı��ֵ��Ϊ0�������һ�����ӱ��Ϊ0 ���ƶ�:
				 * 1.���ƶ���ǰͼƬλ�õ�������move_j���٣���move_jλ�õ�ͼƬ������ֵ��move_j-1λ�ã�
				 * move_jλ�õ�ͼƬ������Ϊ0
				 * 2.�������ǰͼƬλ�õ����������1����ʾͼƬ�������ƣ�����Ҫ�ƶ��󽲴���ǰ����λ�õ��������1
				 * 3.���ƶ����isMove��ֵΪ1����ʾ�и����ƶ��� 4.ѭ������3�����裬֪���ж�����������
				 */
				while (gameMap[mov_i][mov_j] != 0
						&& gameMap[mov_i][mov_j - 1] == 0) {
					gameMap[mov_i][mov_j - 1] = gameMap[mov_i][mov_j];
					gameMap[mov_i][mov_j] = 0;
					if (mov_j > 1) {
						mov_j--;
					}
					isMove = 1;
				}

			}
		}
		return isMove;
	}

	// ����
	public int moveRight() {
		System.out.println("���Ʋ���");
		int isMove = 0;// �����ƶ����0Ϊδ�ƶ���1Ϊ�ƶ���
		for (int i = 0; i < 4; i++) {// gameMap[i][j] i��Ӧ����
			for (int j = 2; j >= 0; j--) {// j��Ӧ����,��2��ʼ����0�����������в���Ҫ����
				int mov_i = i;// ��ǰͼƬλ�õĺ�����
				int mov_j = j;// ��ǰͼƬλ�õ�������
				/*
				 * �����ǰ���ӵı��ֵ��Ϊ0�����ұ�һ�����ӱ��Ϊ0 ���ƶ�:
				 * 1.���ƶ���ǰͼƬλ�õ�������move_j���ӣ���move_jλ�õ�ͼƬ������ֵ��move_j+1λ�ã�
				 * move_jλ�õ�ͼƬ������Ϊ0
				 * 2.�������ǰͼƬλ�õ�������С��2����ʾͼƬ�������ƣ�����Ҫ�ƶ��󽲴���ǰ����λ�õ��������1
				 * 3.���ƶ����isMove��ֵΪ1����ʾ�и����ƶ��� 4.ѭ������3�����裬֪���ж�����������
				 */
				while (gameMap[mov_i][mov_j] != 0
						&& gameMap[mov_i][mov_j + 1] == 0) {
					gameMap[mov_i][mov_j + 1] = gameMap[mov_i][mov_j];
					gameMap[mov_i][mov_j] = 0;
					if (mov_j < 2) {
						mov_j++;
					}
					isMove = 1;
				}

			}
		}
		return isMove;
	}

	// ����
	public int moveDown() {
		System.out.println("���Ʋ���");
		int isMove = 0;// �����ƶ����0Ϊδ�ƶ���1Ϊ�ƶ���
		for (int i = 2; i >= 0; i--) {// gameMap[i][j] i��Ӧ����,��2��ʼ��0�����������в���Ҫ���ƶ�
			for (int j = 0; j < 4; j++) {// j��Ӧ����
				int mov_i = i;// ��ǰͼƬλ�õĺ�����
				int mov_j = j;// ��ǰͼƬλ�õ�������
				/*
				 * �����ǰ���ӵı��ֵ��Ϊ0�����±�һ�����ӱ��Ϊ0���ƶ�:
				 * 1.���ƶ���ǰͼƬλ�õ�������move_i���ӣ���move_iλ�õ�ͼƬ������ֵ��move_i+1λ�ã�
				 * move_iλ�õ�ͼƬ������Ϊ0
				 * 2.�������ǰͼƬλ�õĺ�����С��2����ʾͼƬ�������ƣ�����Ҫ�ƶ��󽫴���ǰ����λ�õĺ������1
				 * 3.���ƶ����isMove��ֵΪ1����ʾ�и����ƶ��� 4.ѭ������3�����裬֪���ж�����������
				 */
				while (gameMap[mov_i][mov_j] != 0
						&& gameMap[mov_i + 1][mov_j] == 0) {
					gameMap[mov_i + 1][mov_j] = gameMap[mov_i][mov_j];
					gameMap[mov_i][mov_j] = 0;
					if (mov_i < 2) {
						mov_i++;
					}
					isMove = 1;
				}

			}
		}
		return isMove;
	}

	// ����
	public int moveUp() {
		System.out.println("���Ʋ���");
		int isMove = 0;// �����ƶ����0Ϊδ�ƶ���1Ϊ�ƶ���
		for (int i = 1; i < 4; i++) {// gameMap[i][j] i��Ӧ����,��1��ʼ��3��������һ�в���Ҫ���ƶ�
			for (int j = 0; j < 4; j++) {// j��Ӧ����
				int mov_i = i;// ��ǰͼƬλ�õĺ�����
				int mov_j = j;// ��ǰͼƬλ�õ�������
				/*
				 * �����ǰ���ӵı��ֵ��Ϊ0�����ϱ�һ�����ӱ��Ϊ0���ƶ�:
				 * 1.���ƶ���ǰͼƬλ�õ�������move_i���٣���move_iλ�õ�ͼƬ������ֵ��move_i-1λ�ã�
				 * move_iλ�õ�ͼƬ������Ϊ0
				 * 2.�������ǰͼƬλ�õĺ��������1����ʾͼƬ�������ƣ�����Ҫ�ƶ��󽫴���ǰ����λ�õĺ������1
				 * 3.���ƶ����isMove��ֵΪ1����ʾ�и����ƶ��� 4.ѭ������3�����裬֪���ж�����������
				 */
				while (gameMap[mov_i][mov_j] != 0
						&& gameMap[mov_i - 1][mov_j] == 0) {
					gameMap[mov_i - 1][mov_j] = gameMap[mov_i][mov_j];
					gameMap[mov_i][mov_j] = 0;
					if (mov_i > 1) {
						mov_i--;
					}
					isMove = 1;
				}

			}
		}
		return isMove;
	}

	// ������ ��������֮ǰ���������ƶ������ԣ����Ӷ��Ѿ����Ƶ����
	public int removeLeft() {
		int isRemove = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {// ��Ӧ����ͼƬ4�У�Ϊ3�ĸ��Ӳ���Ҫ���Ժϲ����ұ�û�и����ˣ��Ҵ�ֵΪ0�ĸ��ӿ�ʼ����
				/*
				 * �����ǰ�ĸ����������ֵ��Ϊ0�����ұ�һ������������ŵ��ڵ�ǰ����������� ��������
				 * 1.��������ǰͼƬλ�õ�������mov_jλ�õ�ͼƬ������ֵ����1��mov_j+1λ�õ�ͼƬ������Ϊ0
				 * 2.���������isRemove��ֵΪ1����ʾ�и��ӱ������� 3.ѭ������2�����裬ֱ���ж�����������
				 */
				while (gameMap[i][j] != 0 && gameMap[i][j] == gameMap[i][j + 1]) {
					gameMap[i][j]++;// �ϲ�������ֵ��1
					gameMap[i][j + 1] = 0;// �ұ߷���ͼƬ����
					bonus(gameMap[i][j]);
					isRemove = 1;
				}

			}
		}
		moveLeft();// ��һ��������������������ֵΪ0�ĸ��ӿ�ʼ���������Ҫ����
		return isRemove;
	}

	// �������������ҷ������������ߵ�ͼƬ�������ұߺϲ������ұ�����֮ǰ�Ѿ����������ƶ������ԣ����Ӷ��Ѿ����Ƶ��ұ�
	public int removeRight() {
		int isRemove = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 3; j >= 1; j--) {// ��Ӧ����ͼƬ4�У�Ϊ0�ĸ��Ӳ���Ҫ���Ժϲ������û�и����ˣ��Ҵ�ֵΪ3�ĸ��ӿ�ʼ����
				/*
				 * �����ǰ����������ֵ��Ϊ0�������һ������������ŵ��ڵ�ǰ�������������������
				 * 1.��������ǰͼƬλ�õ�������mov_jλ�õ�ͼƬ����ֵ����1��mov_j-1λ�õ�ͼƬ������Ϊ0
				 * 2.���������isRemove��ֵΪ1����ʾ�и��ӱ������� 3.ѭ������2�����裬ֱ���ж�����������
				 */
				while (gameMap[i][j] != 0 && gameMap[i][j] == gameMap[i][j - 1]) {
					gameMap[i][j]++;// �ϲ�������ֵ��1
					gameMap[i][j - 1] = 0;// ��߷���ͼƬ����
					bonus(gameMap[i][j]);
					isRemove = 1;
				}
			}
		}
		moveRight();
		return isRemove;
	}

	// �������������Ϸ�����������±ߵ�ͼƬ�������ϱߺϲ�����Ϊ���ϱ�����֮ǰ�Ѿ����������ƶ������ԣ����Ӷ��Ѿ����Ƶ��ϱ�
	public int removeUp() {
		int isRemove = 0;
		for (int i = 0; i < 3; i++) {// ��Ӧ����ͼƬ4�У�Ϊ3�ĸ��Ӳ���Ҫ���Ժϲ����±�û�и����ˣ��Ҵ�ֵΪ0�ĸ��ӿ�ʼ����
			for (int j = 0; j < 4; j++) {
				/*
				 * �����ǰ����������ֵ��Ϊ0�����±�һ������������ŵ��ڵ�ǰ�������������������
				 * 1.��������ǰͼƬλ�õ�������mov_iλ�õ�ͼƬ����ֵ����1��mov_i+1λ�õ�ͼƬ������Ϊ0
				 * 2.���������isRemove��ֵΪ1����ʾ�и��ӱ������� 3.ѭ������2�����裬ֱ���ж�����������
				 */
				while (gameMap[i][j] != 0 && gameMap[i][j] == gameMap[i + 1][j]) {
					gameMap[i][j]++;// �ϲ�������ֵ��1
					gameMap[i + 1][j] = 0;// �±߷���ͼƬ����
					bonus(gameMap[i][j]);
					isRemove = 1;
				}
			}
		}
		moveUp();
		return isRemove;
	}

	// �������������·�����������ϱߵ�ͼƬ�������±ߺϲ�����Ϊ��������֮ǰ�Ѿ����������ƶ������ԣ����Ӷ��Ѿ����Ƶ��±�
	public int removeDown() {
		int isRemove = 0;
		for (int i = 3; i > 0; i--) {// ��Ӧ����ͼƬ4�У�Ϊ0�ĸ��Ӳ���Ҫ���Ժϲ����ϱ�û�и����ˣ��Ҵ�ֵΪ3�ĸ��ӿ�ʼ����
			for (int j = 0; j < 4; j++) {
				/*
				 * �����ǰ����������ֵ��Ϊ0�����ϱ�һ������������ŵ��ڵ�ǰ�������������������
				 * 1.��������ǰͼƬλ�õ�������mov_iλ�õ�ͼƬ����ֵ����1��mov_i-1λ�õ�ͼƬ������Ϊ0
				 * 2.���������isRemove��ֵΪ1����ʾ�и��ӱ������� 3.ѭ������2�����裬ֱ���ж�����������
				 */
				while (gameMap[i][j] != 0 && gameMap[i][j] == gameMap[i - 1][j]) {
					gameMap[i][j]++;// �ϲ�������ֵ��1
					gameMap[i - 1][j] = 0;// �ϱ߷���ͼƬ����
					bonus(gameMap[i][j]);
					isRemove = 1;
				}
			}
		}
		moveDown();
		return isRemove;
	}

	/*
	 * ���㵱ǰ�֣���������Ϊ�����������ͼƬ��ֵ��С
	 */
	public void bonus(int num) {
		int score = data.getScore();
		score += Math.pow(2, num);// ����2��num�η����ۼӸ�score
		data.setScore(score);
	}

	/*
	 * ������߷�
	 */
	public void refreshHighscore() {
		int score = data.getScore();
		if (score > data.getHighScore()) {
			data.setHighScore(score);
		}
	}

	// �ж��ܷ��ƶ���trueΪ���ԣ�falseΪ������
	public boolean canMove() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (i < 3) {
					if (gameMap[i][j] == gameMap[i + 1][j]) {
						return true;// ��������
					}
				}
				if (i > 0) {
					if (gameMap[i][j] == gameMap[i - 1][j]) {
						return true;// ��������
					}
				}
				if (j < 3) {
					if (gameMap[i][j] == gameMap[i][j + 1]) {
						return true;// ��������
					}
				}
				if (j > 0) {
					if (gameMap[i][j] == gameMap[i][j - 1]) {
						return true;// ��������
					}
				}

			}

		}
		return false;
	}

	/*
	 * �ж��Ƿ�ռ������
	 */
	public boolean isFull() {
		boolean flag = true;// ��ʱ���������洢�Ƿ�ռ�������з���ռ����Ϊtrue��δռ��Ϊfalse
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (gameMap[i][j] == 0) {// ������������0���ʾ����δռ��
					flag = false;
				}
			}
		}
		if (flag && !canMove()) {// ռ�����Ҳ����ƶ�
			return true;
		}
		return false;
	}

	// ���¿�ʼ
	public void restart() {
		gameMap = new int[4][4];
		data.setScore(0);
		newBlock();
		newBlock();
	}

	// ��Ϸ����
	public void isGameOver() {
		if (isFull()) {
			Object[] options = { "���¿�ʼ", "�˳���Ϸ" };
			/**
			 * JOptionPane.showOptionDialog(parentComponent, message, title,
			 * optionType, messageType, icon, options, initialValue);
			 * JOptionPane:����Ҫ���û��ṩֵ�����䷢��֪ͨ�ı�׼�Ի���
			 * showOptionDialog��ѯ��һ��ȷ�����⣬��yes/no/cacel��
			 * parentComponent�����常�Ի�������null��Ĭ��frame����������
			 * message��Ҫ���ڶԻ����е�������Ϣ��������Object[]�� title���Ի���ı���
			 * optionType�������ڶԻ���ĵײ���ʾ��ѡ�ť������YES_NO_OPTION
			 * messageType������message��ʽQUESTION_MESSAGE��
			 * icon��Ҫ���ڶԻ����е�װ����ͼ�ꡣ����Ϊnullͼ���Ĭ��ֵ��messageType����ȷ��
			 * options�����ڶԻ���ײ���ʾ��ѡ�ť���ϵĸ���ϸ����������������Object[]�� initialValue��Ĭ��ѡ��
			 */
			int choice = JOptionPane.showOptionDialog(null, "\n��Ϸ����!\n	����Ϊ��"
					+ data.getScore(), "��Ϸ��ʾ", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			refreshHighscore();// ������߷�
			if (choice == 0) {
				restart();// ���¿�ʼ
			} else {
				System.exit(0);// �˳���Ϸ
			}
		} else if (is2048()) {
			Object[] options = { "���¿�ʼ", "�˳���Ϸ" };
			int choice = JOptionPane.showOptionDialog(null, "��ϲ������2048",
					"��Ϸ��ʾ", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			refreshHighscore();
			if (choice == 0) {
				restart();
			} else {
				System.exit(0);
			}
		}
	}

	// �ж��Ƿ���2048
	public boolean is2048() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (gameMap[i][j] == 11)
					return true;
			}
		}
		return false;
	}
}
