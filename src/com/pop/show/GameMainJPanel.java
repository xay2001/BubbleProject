package com.pop.show;

import com.pop.controller.GamemainThread;
import com.pop.element.ElementObj;
import com.pop.manager.ElementManager;
import com.pop.manager.GameElement;
import com.pop.manager.MapManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;


import javax.swing.*;


/**
 * @˵�� ��Ϸ����Ҫ���
 * @����˵�� ��Ҫ����Ԫ�ص���ʾ��ͬʱ���н����ˢ��(���߳�)
 *
 */
public class GameMainJPanel extends ShowObj{

	private static boolean isLose;
	private Thread thead=null;  //��Ϸ���߳�

	private ElementManager em;
	public static int w=767;
	public static int h=677;
	int Level;
	boolean onePlay;
	public GameMainJPanel(boolean onePlay) {
		init(onePlay);
	}

	public void init(boolean onePlay) {
//		this.setLayout(null);
//		JButton pause = new JButton();//˫����Ұ�ť
//		pause.setIcon(new ImageIcon("image/bg/pause.png"));
////		pause.setBounds(23, 55+110*5+35, 136, 42);
//		pause.setBounds(30,55+120*5+30, 136, 42);
//
//		this.add(pause);
//		this.setVisible(true);
		int max=4,min=1;
		int ran = (int) (Math.random()*(max-min)+min);
		this.Level=ran;
		this.onePlay=onePlay;
		thead=new GamemainThread(ran,onePlay,this);
		if(thead !=null) {
			thead.start();//�����߳�
		}
		em = ElementManager.getManager();//�õ�Ԫ�ع���������

	}
	
	@Override  //���ڻ滭��    Graphics ���� ר�����ڻ滭�ģ��ڴ˴���ÿ�μ�������ķ�Ӧ���������
	//��Ҫ�ṩ����Ҫ���ִ�к�����ʵ���Ǽ�����������֮����н�
	//����������Ͷ����ȣ���̬�������Զ�����
	public void paint(Graphics g) {
		super.paint(g);
		int w=1000;
		int h=830;
		GameJFrame.getGameJFrame().setSize(w,h);//��������С
		Map<GameElement, List<ElementObj>> all = em.getGameElements();

		//�˴������Ǳ���ͼƬ���趨�����Ըı�
		ImageIcon icon = new ImageIcon("image/mapbackground/"+Level+".png");
		g.drawImage(icon.getImage(), 195, 60,GameMainJPanel.w,GameMainJPanel.h,this);//�˴���������,ע���ȡ�Ŀ�Ⱥ͸߶�����Ϸmianjpanel�Ķ�������������

		//ע�⣬���ڷֿ�������������Ԫ�أ������Ҫ�ڴ���������ʾ

		//ʹ�ö�ά����ѭ����ʾ

		for (int dy = 0; dy < 15; dy++) { // ������
			for (int dx = 0; dx < 17; dx++) {
				if(MapManager.mapList[dy][dx]==null) {//�жϿ�ֵ
					continue;
				}
				MapManager.mapList[dy][dx].showElement(g);// ����ÿ�������ʾ
			}
		}
		ImageIcon imageIcon = new ImageIcon("image/bg/biankuang.png");//�߿�
		g.drawImage(imageIcon.getImage(), 0,0,w,h, null);

//		���ﵥԪͼƬ��С140 105
		ImageIcon play1  = new ImageIcon("image/bg/1.png");//���״̬��ʾ
		g.drawImage(play1.getImage(), 30,55,140,105, null);

		ImageIcon play2  = new ImageIcon("image/bg/2.png");//���״̬��ʾ
		g.drawImage(play2.getImage(), 30,55+120,140,105, null);

		ImageIcon play3  = new ImageIcon("image/bg/3.png");//���״̬��ʾ
		g.drawImage(play3.getImage(), 30,55+120*2,140,105, null);

		ImageIcon play4  = new ImageIcon("image/bg/4.png");//���״̬��ʾ
		g.drawImage(play4.getImage(), 30,55+120*3,140,105, null);

		ImageIcon level  = new ImageIcon("image/bg/level.png");//level״̬��ʾ
		g.drawImage(level.getImage(), 55,55+120*4-5,99,34, null);

		ImageIcon kuang1  = new ImageIcon("image/bg/kuang.png");//level��״̬��ʾ
		g.drawImage(kuang1.getImage(), 30,55+120*4+30,140,34, null);

		ImageIcon time  = new ImageIcon("image/bg/time.png");//time״̬��ʾ
		g.drawImage(time.getImage(), 55,55+120*5-55,99,34, null);

		ImageIcon kuang2  = new ImageIcon("image/bg/kuang.png");//time��״̬��ʾ
		g.drawImage(kuang2.getImage(), 30,55+120*5-20,140,34, null);

		ImageIcon pause  = new ImageIcon("image/bg/pause.png");//time��״̬��ʾ
		g.drawImage(pause.getImage(), 30,55+120*5+30,136,42, null);

		g.setFont(new Font("Times New Roman", Font.BOLD, 30));
		g.setColor(new Color(30,34,85));
		int allTime = GamemainThread.getAllTime()/1000;
		int munite = allTime / 60;
		int second = allTime % 60;
		String m;
		String s;
		if(munite < 10)
			m = "0" + Integer.toString(munite);
		else
			m = Integer.toString(munite);
		if(second<10)
			s = "0" + Integer.toString(second);
		else
			s = Integer.toString(second);
		g.drawString(m + ":" + s,65,55+120*5+6);
		g.drawString("1 / 5",70,55+120*4+55);

//		g.setFont(new Font("Times New Roman", Font.BOLD, 20));
//		g.setColor(new Color(51,98,55));//��
//		g.drawString("1",145,55+120*3+20);
//		g.drawString("1",145,55+120*3+45);
//		g.drawString("1",145,55+120*3+70);
//		g.drawString("1",145,55+120*3+95);
//		g.drawString("10",80,55+120*3+95);
//
//		g.setColor(new Color(96,61,28));//��
//		g.drawString("1",145,55+120*2+20);
//		g.drawString("1",145,55+120*2+45);
//		g.drawString("1",145,55+120*2+70);
//		g.drawString("1",145,55+120*2+95);
//		g.drawString("10",80,55+120*2+95);

		//ѭ����ʾ���
		for(GameElement ge:GameElement.values()) { //������
			List<ElementObj> list = all.get(ge);
			for(int i=0;i<list.size();i++) {
				ElementObj obj=list.get(i);
				obj.showElement(g);//������ҵ��Լ���show��������Լ�����ʾ
			}
		}
	}

	@Override
	protected void go() {

	}

	public void win() {//�ɹ�����
		GameJFrame.getGameJFrame().remove(this);isLose = false;
		GameJFrame.getGameJFrame().setjPanel(new GameOverJPanel());
	}
	public void lose() {//ʧ�ܺ���
		GameJFrame.getGameJFrame().remove(this);isLose = true;
		GameJFrame.getGameJFrame().setjPanel(new GameOverJPanel());
	}

	public static boolean isLose() {
		return isLose;
	}

}











