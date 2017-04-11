package java_2048.ui;

import java.awt.Graphics;

/**
 * Data�����ڴ�����Ϸ�еķ�����Ϣ �����˻��Ƶ÷֡���߷ּƷְ塢�������ֵ���ʾ�����Ĺ���
 */
public class Data {
	public static final int SCORE_X = 80;// �÷ּƷְ�ͼƬ��ԭ��x����
	public static final int SCORE_Y = 20;// �÷ּƷְ�ͼƬ��ԭ��y����
	public static final int HIGHSCORE_X = 280;// ��߷����Ʒְ��ԭ��x����
	public static final int HIGHSCORE_Y = 20;// ��߷����Ʒְ��ԭ��y����
	public static final int SIZE_NUM = 21;// ����ͼƬ��һ������ռ�����ش�С��21����߶��ǣ�
	public static final int SIZE_SCORE = 140;// ��������߷�ͼƬ��ȶ���140����

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

	// ʵ�ּƷְ幦�ܣ��������ƼƷְ屳��ͼ�͵÷֡���߷�������ʾ����
	public void drawData(Graphics g) {
		g.drawImage(Resource.img_score, SCORE_X, SCORE_Y, null);
		g.drawImage(Resource.img_highScore, HIGHSCORE_X, HIGHSCORE_Y, null);
		drawScore(g);
		drawHighScore(g);
	}

	// ��ʾ�÷�����
	private void drawScore(Graphics g) {
		String score_str = score + "";// ת���ַ��������ڼ��㻭�����ַ�������
		for (int i = 0; i < score_str.length(); i++) {
			int scoreBit = score_str.charAt(i) - '0';
			int numPos = scoreBit * SIZE_NUM;// ������ַ�������ͼƬ�е�λ��
			int mid = SCORE_X + SIZE_SCORE / 2;// ������Ʒְ����еĺ�����ֵ��scoreͼƬ��ԭ��xֵ��ͼƬ��һ��
			/*
			 * drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer)
			 * ��imgͼƬ���󣬸���ԴͼƬ(��img)���Ͻǣ�sx1��sy1�������½ǣ�sx2��sy2����������
			 * ��ȡ������ͼƬ������Ŀ������ϣ��������Ͻǣ�dx1��dy1����������½ǣ�dx2��dy2����λ��
			 */
			g.drawImage(
					Resource.img_num,
					// �м��λ�ã������ƵĻ����ַ����ȵ�һ�룬�õ��ַ���ͼƬ��ʼ������ʼλ��xֵ
					// Ȼ���ٸ����ǵ�i���ַ���ƫ��i���ַ�ͼƬ���ȣ�i��0��ʼ���õ�Ŀ��������Ͻ�xֵ
					mid - score_str.length() * SIZE_NUM / 2 + i * SIZE_NUM,
					SCORE_Y + 50, mid - score_str.length() * SIZE_NUM / 2 + i
							* SIZE_NUM + SIZE_NUM, SCORE_Y + 50 + SIZE_NUM,
					numPos, 0, numPos + SIZE_NUM, SIZE_NUM, null);
		}
	}

	// ��ʾ��߷�����
	private void drawHighScore(Graphics g) {
		String highScore_str = highScore + "";// ת���ַ��������ڼ��㻭�����ַ�������
		for (int i = 0; i < highScore_str.length(); i++) {
			int bit = highScore_str.charAt(i) - '0';
			int numPos = bit * SIZE_NUM;// ������ַ�������ͼƬ�е�λ��
			int mid = HIGHSCORE_X + SIZE_SCORE / 2;// ������Ʒְ����еĺ�����ֵ��scoreͼƬ��ԭ��xֵ��ͼƬ��һ��
			g.drawImage(Resource.img_num, mid - highScore_str.length()
					* SIZE_NUM / 2 + i * SIZE_NUM, HIGHSCORE_Y + 50, mid
					- highScore_str.length() * SIZE_NUM / 2 + i * SIZE_NUM
					+ SIZE_NUM, HIGHSCORE_Y + 50 + SIZE_NUM, numPos, 0, numPos
					+ SIZE_NUM, SIZE_NUM, null);
		}
	}
}
