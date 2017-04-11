package java_2048.ui;

import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;

import javax.swing.JOptionPane;

/**
 * 实现游戏主要显示功能和主要流程处理功能
 */
public class GameService {
	private Data data;// 游戏中的分数信息
	private int[][] gameMap;// 存储游戏中对应的4*4方格的数字图片的索引编号
	public static final int SIZE = 100;// 一个次背景方格大小

	// 初始化成员
	public GameService() {
		this.data = new Data();
		this.gameMap = new int[4][4];
		this.start();
	}

	/**
	 * 画出游戏面板上的内容：1.分数板图片以及分数数字 2.次背景方格内的数字图片
	 * 
	 * @param g
	 *            画笔; blockIndex
	 *            方块中数字图片索引编号，总共1~11分别为：1数字2、2数字4、3数字8、4数字16、5数字32、
	 *            6数字64、7数字128、8数字256、9数字512、10数字1024、11数字2048
	 */
	public void gamePaint(Graphics g) {
		data.drawData(g);// 画分数板区域功能
		// 画次背景方格内数字图片功能
		int blockIndex;// 临时变量存储gameMap遍历出来的数字图片索引编号
		for (int i = 0; i < 4; i++) {// i表示列
			for (int j = 0; j < 4; j++) {// j表示行
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
	 * 根据传入的画笔等参数画出图片数字
	 * 
	 * @param g
	 *            传入的画笔
	 * @param img
	 *            需要画出的图片对象
	 * @param j
	 *            数字图片在该行的第几个格子，对应二维数组gameMap[i][j]的j值
	 * @param i
	 *            数字图片在该列的第几个格子，对应二维数组gameMap[i][j]的i值
	 */
	public void drawNumPic(Graphics g, Image img, int x, int y) {
		g.drawImage(img, Background.FG_X + x * SIZE,
				Background.FG_Y + y * SIZE, null);
	}

	// 开始游戏功能，分数和最高分数归0，随机生成2个方块
	public void start() {
		gameMap = new int[4][4];
		data.setScore(0);
		data.setHighScore(0);
		newBlock();
		newBlock();

	}

	/*
	 * 在4*4方格随机位置生成方块，如果位置上存在方块则再生成，直到不重复，方块图片为2或4，生成方块4的概率为1/8
	 */
	public void newBlock() {
		Random ran = new Random();
		int i, j;// 生成方块的横纵坐标位置
		do {// 获取新坐标，产生一个0~3的随机数，用于确定新生成方块的i和j的位置
			i = ran.nextInt(4);
			j = ran.nextInt(4);
		} while (gameMap[i][j] != 0);// 如果方块位置以被占用，随机另一个位置
		// 确定生成位置后，确定随机生成的是方块图片2还是4
		int index = ran.nextInt(8);// 为了降低游戏难度，生成方块4的概率为1/8
		if (index != 0) {
			gameMap[i][j] = 1;
		} else {
			gameMap[i][j] = 2;
		}
	}

	// 左移
	public int moveLeft() {
		System.out.println("左移操作");
		int isMove = 0;// 定义移动标记0为未移动，1为移动过
		for (int i = 0; i < 4; i++) {// gameMap[i][j] i对应行数
			for (int j = 1; j < 4; j++) {// j对应列数，从1开始，3结束，第一列不需要左移
				int mov_i = i;// 当前图片位置的横坐标
				int mov_j = j;// 当前图片位置的纵坐标
				/*
				 * 如果当前格子的编号值不为0并且左边一个格子编号为0 左移动:
				 * 1.左移动则当前图片位置的纵坐标move_j减少，讲move_j位置的图片索引赋值给move_j-1位置，
				 * move_j位置的图片索引改为0
				 * 2.如果代表当前图片位置的纵坐标大于1，表示图片还能左移，还需要移动后讲代表当前格子位置的纵坐标减1
				 * 3.将移动标记isMove赋值为1，表示有格子移动过 4.循环上面3个步骤，知道判断条件不成立
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

	// 右移
	public int moveRight() {
		System.out.println("右移操作");
		int isMove = 0;// 定义移动标记0为未移动，1为移动过
		for (int i = 0; i < 4; i++) {// gameMap[i][j] i对应行数
			for (int j = 2; j >= 0; j--) {// j对应列数,从2开始，到0结束，第四列不需要右移
				int mov_i = i;// 当前图片位置的横坐标
				int mov_j = j;// 当前图片位置的纵坐标
				/*
				 * 如果当前格子的编号值不为0并且右边一个格子编号为0 右移动:
				 * 1.右移动则当前图片位置的纵坐标move_j增加，讲move_j位置的图片索引赋值给move_j+1位置，
				 * move_j位置的图片索引改为0
				 * 2.如果代表当前图片位置的纵坐标小于2，表示图片还能右移，还需要移动后讲代表当前格子位置的纵坐标加1
				 * 3.将移动标记isMove赋值为1，表示有格子移动过 4.循环上面3个步骤，知道判断条件不成立
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

	// 下移
	public int moveDown() {
		System.out.println("下移操作");
		int isMove = 0;// 定义移动标记0为未移动，1为移动过
		for (int i = 2; i >= 0; i--) {// gameMap[i][j] i对应行数,从2开始到0结束，第四行不需要下移动
			for (int j = 0; j < 4; j++) {// j对应列数
				int mov_i = i;// 当前图片位置的横坐标
				int mov_j = j;// 当前图片位置的纵坐标
				/*
				 * 如果当前格子的编号值不为0并且下边一个格子编号为0下移动:
				 * 1.下移动则当前图片位置的纵坐标move_i增加，讲move_i位置的图片索引赋值给move_i+1位置，
				 * move_i位置的图片索引改为0
				 * 2.如果代表当前图片位置的横坐标小于2，表示图片还能下移，还需要移动后将代表当前格子位置的横坐标加1
				 * 3.将移动标记isMove赋值为1，表示有格子移动过 4.循环上面3个步骤，知道判断条件不成立
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

	// 上移
	public int moveUp() {
		System.out.println("上移操作");
		int isMove = 0;// 定义移动标记0为未移动，1为移动过
		for (int i = 1; i < 4; i++) {// gameMap[i][j] i对应行数,从1开始到3结束，第一行不需要上移动
			for (int j = 0; j < 4; j++) {// j对应列数
				int mov_i = i;// 当前图片位置的横坐标
				int mov_j = j;// 当前图片位置的纵坐标
				/*
				 * 如果当前格子的编号值不为0并且上边一个格子编号为0上移动:
				 * 1.上移动则当前图片位置的纵坐标move_i减少，讲move_i位置的图片索引赋值给move_i-1位置，
				 * move_i位置的图片索引改为0
				 * 2.如果代表当前图片位置的横坐标大于1，表示图片还能上移，还需要移动后将代表当前格子位置的横坐标减1
				 * 3.将移动标记isMove赋值为1，表示有格子移动过 4.循环上面3个步骤，知道判断条件不成立
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

	// 左消除 在左消除之前调用了左移动，所以，格子都已经左移到左边
	public int removeLeft() {
		int isRemove = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {// 对应数字图片4列，为3的格子不需要尝试合并，右边没有格子了，且从值为0的格子开始左消
				/*
				 * 如果当前的格子索引编号值不为0并且右边一个格子索引编号等于当前格子索引编号 左消除：
				 * 1.左消除则当前图片位置的宗坐标mov_j位置的图片索引赋值增加1，mov_j+1位置的图片索引改为0
				 * 2.将消除标记isRemove赋值为1，表示有格子被消除过 3.循环上面2个步骤，直到判断条件不成立
				 */
				while (gameMap[i][j] != 0 && gameMap[i][j] == gameMap[i][j + 1]) {
					gameMap[i][j]++;// 合并后索引值加1
					gameMap[i][j + 1] = 0;// 右边方格图片消除
					bonus(gameMap[i][j]);
					isRemove = 1;
				}

			}
		}
		moveLeft();// 如一行内遇到两个消除，从值为0的格子开始左消除完后还要左移
		return isRemove;
	}

	// 右消除，键盘右方向键触发，左边的图片尝试往右边合并，在右边消除之前已经调用了右移动，所以，格子都已经右移到右边
	public int removeRight() {
		int isRemove = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 3; j >= 1; j--) {// 对应数字图片4列，为0的格子不需要尝试合并，左边没有格子了，且从值为3的格子开始右消
				/*
				 * 如果当前格子索引号值不为0并且左边一个格子索引编号等于当前格子索引编号则右消除
				 * 1.右消除则当前图片位置的纵坐标mov_j位置的图片索引值增加1，mov_j-1位置的图片索引改为0
				 * 2.将消除标记isRemove赋值为1，表示有格子被消除过 3.循环上面2个步骤，直到判断条件不成立
				 */
				while (gameMap[i][j] != 0 && gameMap[i][j] == gameMap[i][j - 1]) {
					gameMap[i][j]++;// 合并后索引值加1
					gameMap[i][j - 1] = 0;// 左边方格图片消除
					bonus(gameMap[i][j]);
					isRemove = 1;
				}
			}
		}
		moveRight();
		return isRemove;
	}

	// 上消除，键盘上方向键触发，下边的图片尝试往上边合并，因为在上边消除之前已经调用了上移动，所以，格子都已经上移到上边
	public int removeUp() {
		int isRemove = 0;
		for (int i = 0; i < 3; i++) {// 对应数字图片4行，为3的格子不需要尝试合并，下边没有格子了，且从值为0的格子开始上消
			for (int j = 0; j < 4; j++) {
				/*
				 * 如果当前格子索引号值不为0并且下边一个格子索引编号等于当前格子索引编号则上消除
				 * 1.上消除则当前图片位置的纵坐标mov_i位置的图片索引值增加1，mov_i+1位置的图片索引改为0
				 * 2.将消除标记isRemove赋值为1，表示有格子被消除过 3.循环上面2个步骤，直到判断条件不成立
				 */
				while (gameMap[i][j] != 0 && gameMap[i][j] == gameMap[i + 1][j]) {
					gameMap[i][j]++;// 合并后索引值加1
					gameMap[i + 1][j] = 0;// 下边方格图片消除
					bonus(gameMap[i][j]);
					isRemove = 1;
				}
			}
		}
		moveUp();
		return isRemove;
	}

	// 下消除，键盘下方向键触发，上边的图片尝试往下边合并，因为在下消除之前已经调用了下移动，所以，格子都已经下移到下边
	public int removeDown() {
		int isRemove = 0;
		for (int i = 3; i > 0; i--) {// 对应数字图片4行，为0的格子不需要尝试合并，上边没有格子了，且从值为3的格子开始下消
			for (int j = 0; j < 4; j++) {
				/*
				 * 如果当前格子索引号值不为0并且上边一个格子索引编号等于当前格子索引编号则下消除
				 * 1.上消除则当前图片位置的纵坐标mov_i位置的图片索引值增加1，mov_i-1位置的图片索引改为0
				 * 2.将消除标记isRemove赋值为1，表示有格子被消除过 3.循环上面2个步骤，直到判断条件不成立
				 */
				while (gameMap[i][j] != 0 && gameMap[i][j] == gameMap[i - 1][j]) {
					gameMap[i][j]++;// 合并后索引值加1
					gameMap[i - 1][j] = 0;// 上边方格图片消除
					bonus(gameMap[i][j]);
					isRemove = 1;
				}
			}
		}
		moveDown();
		return isRemove;
	}

	/*
	 * 计算当前分，分数增量为消除后的数字图片面值大小
	 */
	public void bonus(int num) {
		int score = data.getScore();
		score += Math.pow(2, num);// 计算2的num次方并累加给score
		data.setScore(score);
	}

	/*
	 * 更新最高分
	 */
	public void refreshHighscore() {
		int score = data.getScore();
		if (score > data.getHighScore()) {
			data.setHighScore(score);
		}
	}

	// 判断能否移动，true为可以，false为不可以
	public boolean canMove() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (i < 3) {
					if (gameMap[i][j] == gameMap[i + 1][j]) {
						return true;// 可以上消
					}
				}
				if (i > 0) {
					if (gameMap[i][j] == gameMap[i - 1][j]) {
						return true;// 可以下消
					}
				}
				if (j < 3) {
					if (gameMap[i][j] == gameMap[i][j + 1]) {
						return true;// 可以左消
					}
				}
				if (j > 0) {
					if (gameMap[i][j] == gameMap[i][j - 1]) {
						return true;// 可以右消
					}
				}

			}

		}
		return false;
	}

	/*
	 * 判断是否占满方格
	 */
	public boolean isFull() {
		boolean flag = true;// 临时变量用来存储是否占满了所有方格，占满了为true，未占满为false
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (gameMap[i][j] == 0) {// 索引标记如果有0则表示格子未占满
					flag = false;
				}
			}
		}
		if (flag && !canMove()) {// 占满了且不能移动
			return true;
		}
		return false;
	}

	// 重新开始
	public void restart() {
		gameMap = new int[4][4];
		data.setScore(0);
		newBlock();
		newBlock();
	}

	// 游戏结束
	public void isGameOver() {
		if (isFull()) {
			Object[] options = { "重新开始", "退出游戏" };
			/**
			 * JOptionPane.showOptionDialog(parentComponent, message, title,
			 * optionType, messageType, icon, options, initialValue);
			 * JOptionPane:弹出要求用户提供值或向其发出通知的标准对话框；
			 * showOptionDialog：询问一个确认问题，如yes/no/cacel；
			 * parentComponent：定义父对话框，设置null则默认frame用作父级；
			 * message：要置于对话框中的描述消息，类型是Object[]； title：对话框的标题
			 * optionType：定义在对话框的底部显示的选项按钮的类型YES_NO_OPTION
			 * messageType：定义message样式QUESTION_MESSAGE；
			 * icon：要置于对话框中的装饰性图标。设置为null图标的默认值由messageType参数确定
			 * options：将在对话框底部显示的选项按钮集合的更详细描述，参数类型是Object[]； initialValue：默认选择
			 */
			int choice = JOptionPane.showOptionDialog(null, "\n游戏结束!\n	分数为："
					+ data.getScore(), "游戏提示", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			refreshHighscore();// 更新最高分
			if (choice == 0) {
				restart();// 重新开始
			} else {
				System.exit(0);// 退出游戏
			}
		} else if (is2048()) {
			Object[] options = { "重新开始", "退出游戏" };
			int choice = JOptionPane.showOptionDialog(null, "恭喜大神达成2048",
					"游戏提示", JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			refreshHighscore();
			if (choice == 0) {
				restart();
			} else {
				System.exit(0);
			}
		}
	}

	// 判断是否达成2048
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
