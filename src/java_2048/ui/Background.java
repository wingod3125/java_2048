package java_2048.ui;

import java.awt.Graphics;

/**
 * �����࣬�����˱����ʹα�����������ݣ��ͻ��Ʊ������α�������ķ���
 * 
 */
public class Background {
	// ����α�����������ֵ������ɳ�������Ϊ������λ���ǹ̶������
	public static final int FG_X = 50;// �α�������ԭ��xֵ
	public static final int FG_Y = 120;// �α�������ԭ��yֵ

	/**
	 * �������ʹα�������
	 * 
	 * @param g����
	 */
	public void drawBackground(Graphics g) {
		g.drawImage(Resource.img_bg, 0, 0, null);
		g.drawImage(Resource.img_fg, FG_X, FG_Y, null);
	}

}
