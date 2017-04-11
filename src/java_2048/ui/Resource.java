package java_2048.ui;

import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * 数据加载类，用于加载静态资源，包括方块2~方块2048、背景和次背景方格、得分和最高分计分板、分数数字的png图片文件
 * 
 */
public class Resource {
	public static Image img_num;// 分数
	public static Image img_bg;// 背景
	public static Image img_fg;// 次背景方格
	public static Image img_score;// 得分
	public static Image img_highScore;// 最高得分
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

	static {// 精态块用于初始化静态成员，对于一些资源类文件应当用精态加载块
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
	 * 加载图片对象到程序中
	 * 
	 * @param imgName
	 *            图片资源路径和图片名
	 * @return 加载到的图片对象
	 */
	public static Image getImg(String imgName) {
		return new ImageIcon(imgName).getImage();
	}
}
