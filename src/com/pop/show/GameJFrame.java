package com.pop.show;

import com.pop.manager.GameLoad;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;




/**
 * @˵�� ��Ϸ���� ��Ҫʵ�ֵĹ��ܣ��رգ���ʾ�������С��
 *  1.���󶨵�����
 *       2.������//�󶨵�controller
 *       3.��Ϸ���߳�����
 *       4.��ʾ����
 */
public class GameJFrame extends JFrame{
	public static int GameX =  780;//GAMEX
	public static int GameY =  715;
	private int i=0;
	private JPanel jPanel =new JPanel(); //�����
	private KeyListener  keyListener=null;//���̼���
	private MouseMotionListener mouseMotionListener=null; //������
	private MouseListener mouseListener=null;

	private static CardLayout cLayout=new CardLayout();
	private ShowObj jPanel_Child = null;//�����
	private static GameJFrame gj=null;
	public static synchronized GameJFrame getGameJFrame() {
		if(gj == null) {//�����ж�

			gj=new GameJFrame();
			gj.jPanel.setLayout(cLayout);
		}
		return gj;
	}

	public GameJFrame() {
		init();
	}
	public void init() {

		this.setSize(GameX, GameY); //���ô����С
		this.setTitle("Q��������");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�����˳����ҹر�
		this.setLocationRelativeTo(null);//��Ļ������ʾ
		// ��������ͼƬ
		GameLoad.imageLoad();
	}
	/*���岼��: ���Խ� �浵��������button   �������չ��*/
	public void addButton() {
//		this.setLayout(manager);//���ָ�ʽ��������ӿؼ�
	}	
	/**
	 * ��������
	 */
	public void start() {
		if(jPanel!=null) {
			this.add(jPanel);
		}
		if(keyListener !=null) {
			this.addKeyListener(keyListener);
		}

//		�����ˢ�ºͼ���

				
		this.setVisible(true);//��ʾ����
		
		while (true) {	
			jPanel.repaint();
			jPanel_Child.model();
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/*setע�룺�ȴ��ѧϰssm ͨ��set����ע�������ļ��ж�ȡ������;�������ļ�
	 * �е����ݸ�ֵΪ�������
	 * ����ע�룺��Ҫ��Ϲ��췽��
	 * spring ��ioc ���ж�����Զ����ɣ�����
	 * */
	public void setjPanel(ShowObj jp) {
		jPanel_Child=jp;
		this.jPanel.add(jp,Integer.toString(i));
		cLayout.show(this.jPanel,Integer.toString(i++));

		this.setVisible(false);
		this.setVisible(true);
	}
	public void setKeyListener(KeyListener keyListener) {
		this.keyListener = keyListener;
	}
	public void setMouseMotionListener(MouseMotionListener mouseMotionListener) {
		this.mouseMotionListener = mouseMotionListener;
	}
	public void setMouseListener(MouseListener mouseListener) {
		this.mouseListener = mouseListener;
	}
	/*public void setThead(Thread thead) {
		this.thead = thead;
	}*/

}





