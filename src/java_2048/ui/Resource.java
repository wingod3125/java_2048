package java_2048.ui;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * ���ݼ����࣬���ڼ��ؾ�̬��Դ����������2~����2048�������ʹα������񡢵÷ֺ���߷ּƷְ塢�������ֵ�pngͼƬ�ļ�
 * 
 */
public class Resource {
	public static Image img_num;// ����
	public static Image img_bg;// ����
	public static Image img_fg;// �α�������
	public static Image img_score;// �÷�
	public static Image img_highScore;// ��ߵ÷�
	public static Image img_2;
	public static Image img_4;
	public static Image img_8;
	public static Image img_16;
	public static Image img_32;
	public static Image img_64;
	public static Image img_128;
	public static Image img_256;
	public static Image img_512;
	public static Image img_1024;
	public static Image img_2048;

	static {// ��̬�����ڳ�ʼ����̬��Ա������һЩ��Դ���ļ�Ӧ���þ�̬���ؿ�
		img_num = getImg("res/num.png");
		img_bg = getImg("res/bg.png");
		img_fg = getImg("res/fg.png");
		img_score = getImg("res/score.png");
		img_highScore = getImg("res/highScore.png");
		img_2 = getImg("res/2.png");
		img_4 = getImg("res/4.png");
		img_8 = getImg("res/8.png");
		img_16 = getImg("res/16.png");
		img_32 = getImg("res/32.png");
		img_64 = getImg("res/64.png");
		img_128 = getImg("res/128.png");
		img_256 = getImg("res/256.png");
		img_512 = getImg("res/512.png");
		img_1024 = getImg("res/1024.png");
		img_2048 = getImg("res/2048.png");

	}

	/**
	 * ����ͼƬ���󵽳�����
	 * 
	 * @param imgName
	 *            ͼƬ��Դ·����ͼƬ��
	 * @return ���ص���ͼƬ����
	 */
	public static Image getImg(String imgName) {
		return new ImageIcon(imgName).getImage();
	}
}
