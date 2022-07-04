package com.pop.element;

import java.awt.Graphics;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


/**
 * @˵�� ����Ԫ�صĻ��ࡣ
 *
 */
public abstract class ElementObj {
	public static int final_x = 195;
	public static int final_y = 60;
	private int x;
	private int y;
	private int w;
	private int h;
	private ImageIcon icon;
	private int wHit;//ʵ����ײ���
	private int hHit;//ʵ����ײ�߶�

	public void sethHit(int hHit) {
		this.hHit = hHit;
	}

	public int gethHit() {
		return hHit;
	}

	public void setwHit(int wHit) {
		this.wHit = wHit;
	}

	public int getwHit() {
		return wHit;
	}

	//	���С������� ���ֱ�Ҫ��״ֵ̬�����磺�Ƿ�����.hp��
	private boolean islive=true;
	private int hp=1;
	
	public ElementObj() {	//���������ʵû�����ã�ֻ��Ϊ�̳е�ʱ�򲻱���д��	
	}
	/**
	 * @˵�� �������Ĺ��췽��; ���������ഫ�����ݵ�����
	 * @param x    ���Ͻ�X���꣬��������������xy�ĺ��嶼�������е�xy
	 * @param y    ���Ͻ�y���꣬��������������xy�ĺ��嶼�������е�xy
	 * @param w    w���
	 * @param h    h�߶�
	 * @param icon  ͼƬ
	 */
	public ElementObj(int x, int y, int w, int h, ImageIcon icon) {
		super();
		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;
		this.icon = icon;
		
	}
	/**
	 * @˵�� ���󷽷�����ʾԪ��
	 * @param g  ���� ���ڽ��л滭
	 */
	public abstract void showElement(Graphics g);
	
	
	protected void updateImage() {//���±���ͼƬ����
		
	} 
	protected void move() {//�ƶ�����
		
	}
	protected void behavior() {//������Ϊ��������ͬ���в�ͬʵ�֣�
		
	}
	public void die() {//������ʧ����
		
	}
	
	public void model(){
		updateImage();
		move();
		behavior();
	}
	

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getW() {
		return w;
	}
	public void setW(int w) {
		this.w = w;
	}
	public int getH() {
		return h;
	}
	public void setH(int h) {
		this.h = h;
	}
	public ImageIcon getIcon() {
		return icon;
	}
	public void setIcon(ImageIcon icon) {
		this.icon = icon;
	}
	public boolean getislive() {
		return islive;
	}
	public void setIslive(boolean islive) {
		this.islive = islive;
	}
	
	public Rectangle getRectangle() {//�������ײ������ҪԪ���Լ��ı��
		Rectangle myRectangle=new Rectangle(getX()*45,getY()*45,gethHit(),getwHit());
		return myRectangle;
	}
	
	public boolean pk(ElementObj obj) {
		return this.getRectangle().intersects(obj.getRectangle());
	}
	public int getHp() {
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
}










