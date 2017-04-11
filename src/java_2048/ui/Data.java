package java_2048.ui;

import java.awt.Graphics;

/**
 * Data类用于储存游戏中的分数信息 定义了绘制得分、最高分计分板、分数数字的显示分数的功能
 */
public class Data {
	public static final int SCORE_X = 80;// 得分计分板图片的原点x坐标
	public static final int SCORE_Y = 20;// 得分计分板图片的原点y坐标
	public static final int HIGHSCORE_X = 280;// 最高分数计分板的原点x坐标
	public static final int HIGHSCORE_Y = 20;// 最高分数计分板的原点y坐标
	public static final int SIZE_NUM = 21;// 数字图片中一个数字占的像素大小是21（宽高都是）
	public static final int SIZE_SCORE = 140;// 分数与最高分图片宽度都是140像素

	private int score;
	private int highScore;

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getHighScore() {
		return highScore;
	}

	public void setHighScore(int highScore) {
		this.highScore = highScore;
	}

	// 实现计分板功能，包括绘制计分板背景图和得分、最高分数据显示功能
	public void drawData(Graphics g) {
		g.drawImage(Resource.img_score, SCORE_X, SCORE_Y, null);
		g.drawImage(Resource.img_highScore, HIGHSCORE_X, HIGHSCORE_Y, null);
		drawScore(g);
		drawHighScore(g);
	}

	// 显示得分数据
	private void drawScore(Graphics g) {
		String score_str = score + "";// 转成字符串，用于计算画出的字符串长度
		for (int i = 0; i < score_str.length(); i++) {
			int scoreBit = score_str.charAt(i) - '0';
			int numPos = scoreBit * SIZE_NUM;// 计算出字符在数字图片中的位置
			int mid = SCORE_X + SIZE_SCORE / 2;// 计算出计分板正中的横坐标值，score图片的原点x值加图片的一半
			/*
			 * drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer)
			 * 将img图片对象，根据源图片(即img)左上角（sx1，sy1）和右下角（sx2和sy2）两个坐标
			 * 截取出矩形图片，画在目标矩形上（根据左上角（dx1和dy1）坐标和右下角（dx2，dy2）定位）
			 */
			g.drawImage(
					Resource.img_num,
					// 中间点位置，再左移的画出字符长度的一半，得到字符串图片开始画的起始位置x值
					// 然后再根据是第i个字符右偏移i个字符图片长度（i从0开始）得到目标矩阵左上角x值
					mid - score_str.length() * SIZE_NUM / 2 + i * SIZE_NUM,
					SCORE_Y + 50, mid - score_str.length() * SIZE_NUM / 2 + i
							* SIZE_NUM + SIZE_NUM, SCORE_Y + 50 + SIZE_NUM,
					numPos, 0, numPos + SIZE_NUM, SIZE_NUM, null);
		}
	}

	// 显示最高分数据
	private void drawHighScore(Graphics g) {
		String highScore_str = highScore + "";// 转成字符串，用于计算画出的字符串长度
		for (int i = 0; i < highScore_str.length(); i++) {
			int bit = highScore_str.charAt(i) - '0';
			int numPos = bit * SIZE_NUM;// 计算出字符在数字图片中的位置
			int mid = HIGHSCORE_X + SIZE_SCORE / 2;// 计算出计分板正中的横坐标值，score图片的原点x值加图片的一半
			g.drawImage(Resource.img_num, mid - highScore_str.length()
					* SIZE_NUM / 2 + i * SIZE_NUM, HIGHSCORE_Y + 50, mid
					- highScore_str.length() * SIZE_NUM / 2 + i * SIZE_NUM
					+ SIZE_NUM, HIGHSCORE_Y + 50 + SIZE_NUM, numPos, 0, numPos
					+ SIZE_NUM, SIZE_NUM, null);
		}
	}
}
