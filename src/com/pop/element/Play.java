package com.pop.element;

import java.awt.*;

import javax.swing.ImageIcon;


import com.pop.controller.MusicPlayer;
import com.pop.manager.*;
import com.pop.show.GameMainJPanel;

import static java.lang.Thread.sleep;


/*
* @����������࣬�ڲ�����Ҹ��ַ���������ը���ȣ�
* ע�⣬���ִ����icon�Ŀ���ȷ��Ϊ-1
* */
public class Play extends ElementObj {

	boolean upBoolean = false;
	boolean downBoolean = false;
	boolean leftBoolean = false;
	boolean rightBoolean = false;

	boolean fireNow = false;//����״̬

	boolean dieAndLive= true;//��������״̬�����ڲ�������������true��flase��

	public String getFxString() {
		return fxString;
	}

	//��Է����imageIndexһ����������յ�iconͼƬ��ʾ
	protected String fxString = "up";
	int imageIndex =1;
	int deadIndex  =1;//��������

	protected String playNumber = "1";//�Ǽ������,����set��get����


	int bombNum =1;// �ܹ�����ը���������Ŀ
	int bombNow =0;// ը��Ŀǰ����Ŀ
	int bombPower =1;//ը������
	int v = 3;// �ٶ�
	int beginx;
	int beginy;

	int score=10;//����


	public Play(int x, int y, String playNumber, ImageIcon icon) {
		this.setX(x);
		this.setY(y);
		this.setPlayNumber(playNumber);
		this.setIcon(icon);
		this.sethHit(20);
		this.setwHit(20);
		this.beginx=x;
		this.beginy=y;
	}

	//���������е�set��get����

	public boolean isDieAndLive() {
		return dieAndLive;
	}

	public void setDieAndLive(boolean dieAndLive) {
		setW(55);
		this.dieAndLive = dieAndLive;
	}

	public String getPlayNumber() {
		return playNumber;
	}

	public void setPlayNumber(String playNumber) {
		this.playNumber = playNumber;
	}

	public int getBombNum() {
		return bombNum;
	}

	public void setBombNum(int bombNum) {
		this.bombNum = bombNum;
	}

	public int getBombNow() {
		return bombNow;
	}

	public void setBombNow(int bombNow) {
		this.bombNow = bombNow;
	}

	public int getBombPower() {
		return bombPower;
	}

	public void setBombPower(int bombPower) {
		if (bombPower<5){
			this.bombPower = bombPower;
		}
	}

	public void setV(int v) {
		if (this.v<5){
			this.v = v;
		}
	}
	public int getV() { return v; }
	//���½���������Ҫ���߼�����
	/*
	 * model()���������е���Ҫ��������Ϸ��play�ཫ���������������ִ������˳��
	 * */
	@Override
	public void model() {
		// TODO Auto-generated method stub
		updateImage();
		move();
		behavior();
	}

	/*
	* �滭����
	* */
	@Override
	public void showElement(Graphics g) {
		try {
			g.drawImage(this.getIcon().getImage(), this.getX()+ElementObj.final_x, this.getY()+ElementObj.final_y, this.getW(), this.getH(), null);
//			if(playNumber.equals("1")){
//				g.setFont(new Font("Times New Roman", Font.BOLD, 20));
//				g.setColor(new Color(48,86,118));//��ɫ
//				g.drawString(getHp()+"",145,55+120*0+20);//����ֵ
//				g.drawString(bombNum+"",145,55+120*0+45);//ը����
//				g.drawString(bombPower+"",145,55+120*0+70);//ҩˮ
//				g.drawString(v+"",145,55+120*0+95);//�ٶ�
//				g.drawString("10",80,55+120*0+95);//����
//
//				g.setFont(new Font("Times New Roman", Font.BOLD, 20));
//				g.setColor(new Color(48,86,118));//��ɫ
//				g.drawString("1",145,55+120*1+20);//����ֵ
//				g.drawString("1",145,55+120*1+20);//����ֵ
//				g.drawString("1",145,55+120*1+45);//ը����
//				g.drawString("1",145,55+120*1+70);//ҩˮ
//				g.drawString("1",145,55+120*1+95);//�ٶ�
//				g.drawString("10",80,55+120*1+95);//����
//			}
//			if(playNumber.equals("2")){
				g.setFont(new Font("Times New Roman", Font.BOLD, 20));
				g.setColor(new Color(48,86,118));//��ɫ
				g.drawString(getHp()+"",145,55+120*(Integer.parseInt(playNumber)-1)+20);//����ֵ
				g.drawString(bombNum+"",145,55+120*(Integer.parseInt(playNumber)-1)+45);//ը����
				g.drawString(bombPower+"",145,55+120*(Integer.parseInt(playNumber)-1)+70);//ҩˮ
				g.drawString(v+"",145,55+120*(Integer.parseInt(playNumber)-1)+95);//�ٶ�
				g.drawString(getScore()+"",80,55+120*(Integer.parseInt(playNumber)-1)+95);//����
//			}
		}catch (Exception e){
			System.out.println(playNumber+" "+fxString+" "+imageIndex);
		}
	}

	// ���Ѻ���
	public void keyClick(boolean puss, int dis) {
		//System.out.println(dis);
		if (puss) {
			if (this.playNumber.equals("1")) {
				switch (dis) {
				case 38:
					this.downBoolean=false;this.upBoolean=true;
					this.rightBoolean =false;this.leftBoolean=false;
					fxString = "up";
					break;
				case 40:
					this.rightBoolean =false;this.leftBoolean=false;
					this.downBoolean=true; this.upBoolean=false;
					fxString = "down";
					break;
				case 37:
					this.downBoolean=false;this.upBoolean=false;
					this.leftBoolean=true; this.rightBoolean =false;
					fxString = "left";
					break;
				case 39:
					this.rightBoolean =true;this.leftBoolean=false;
					this.upBoolean=false; this.downBoolean=false;
					fxString = "right";
					break;
				case 10:
					fireNow = true;
					break;
				default:
					break;
				}
			}
			else
			{
				switch (dis) {
					case 87:
						this.downBoolean=false;this.upBoolean=true;
						this.rightBoolean =false;this.leftBoolean=false;
						fxString = "up";
						break;
					case 83:
						this.rightBoolean =false;this.leftBoolean=false;
						this.downBoolean=true; this.upBoolean=false;
						fxString = "down";
						break;
					case 65:
						this.downBoolean=false;this.upBoolean=false;
						this.leftBoolean=true; this.rightBoolean =false;
						fxString = "left";
						break;
					case 68:
						this.rightBoolean =true;this.leftBoolean=false;
						this.upBoolean=false; this.downBoolean=false;
						fxString = "right";
						break;
					case 32:
						fireNow = true;
						break;
					default:
						break;
				}
			}
		} else {
			if (this.playNumber.equals("1")) {
				switch (dis) {
				case 38:
					this.upBoolean=false;
					break;
				case 40:
					 this.downBoolean=false;
					break;
				case 37:
					 this.leftBoolean=false;
					break;
				case 39:
					this.rightBoolean =false;
					break;
				case 10:
					fireNow = false;
					break;
				default:
					break;
				}
			}
			else
			{
				switch (dis) {
					case 87:
						this.upBoolean=false;
						break;
					case 83:
						this.downBoolean=false;
						break;
					case 65:
						this.leftBoolean=false;
						break;
					case 68:
						this.rightBoolean =false;
						break;
					case 32:
						fireNow = false;
						break;
					default:
						break;
				}
			}
		}
	}

	@Override
	// ��װ������ע�����߶�����Ҫ���ճ���ı�icon��������Ҫ���ϸ���ͼƬ��ʾ,Ҳ���Ǽ�¼��ǰ��ͼƬ�±ꡣ
	//��Է����imageindexһ����������յ�iconͼƬ��ʾ
	public void updateImage() {
		String iconkey= playNumber;
		switch (fxString) {
			case "up":
				iconkey=iconkey+"up"+ imageIndex;
				setW(63);
				setH(80);
				break;
			case "down":
				iconkey=iconkey+"down"+ imageIndex;
				setW(63);
				setH(80);
				break;
			case "left":
				iconkey=iconkey+"left"+ imageIndex;
				setW(51);
				setH(76);
				break;
			case "right":
				iconkey=iconkey+"right"+ imageIndex;
				setW(51);
				setH(76);
				break;
			default:
				break;
		}
		setIcon(GameLoad.imgMap.get(iconkey));//������Ӧ���ļ���һ�����ӣ�1 up 1(imageindex)
		//System.out.printf(iconkey+"\n");

		//ע�⵽���ƶ��Żᵼ��imageindex�ı仯�������move����������imageindex
	}

	@Override
	// �ƶ�����
	public void move() {
		int dx=(getX()+getW()/2)/ MapManager.dxdy;//���ڿ�ȣ���Ҫ��ȡһ�볤�ȣ�֮�����x�����ȡ�е�λ�ã���֮��ͨ�������ж�����
		int dy=(getY()+getH())/ MapManager.dxdy;//���ڸ߶ȣ�ֱ�Ӽ�����Ҹ߶ȼ���
		if (this.leftBoolean) {
			try {
			if (MapManager.mapList[dy][dx-1]==null||!(MapManager.mapList[dy][dx] instanceof Bomb)){//���Ҫ�ߵĸ���ǰ����ը���Ͳ���
				this.setX(this.getX() - v);
			}}catch (Exception e){
				System.out.println("yuejueleft"+playNumber+getH()+"!!"+getW());
			}
			//ע�⵽imageindex���ֻ��24������˴ﵽ24֮����Ҫ��Ϊ1
			if(imageIndex <24){
				imageIndex++;//С��24������
			}else{//�����ڻ��ߴ���24ʱ������1
				imageIndex =1;
			}
		}
		if (this.upBoolean) {
			try {
			if (MapManager.mapList[dy-1][dx]==null||!(MapManager.mapList[dy][dx] instanceof Bomb)){//���Ҫ�ߵĸ���ǰ���ǿղ���û��ը������
				this.setY(this.getY() - v);
			}
			}catch (Exception e){
				System.out.println("yuejueup"+playNumber+getH()+"!!"+getW());
			}
			if(imageIndex <24){
				imageIndex++;//С��24������
			}else{//�����ڻ��ߴ���24ʱ������1
				imageIndex =1;
			}
		}
		if (this.rightBoolean ) {//����ı߽�ֱ��ʹ�ó�����ȡ
			try {
				if (MapManager.mapList[dy][dx+1]==null||!(MapManager.mapList[dy][dx] instanceof Bomb)){//���Ҫ�ߵĸ���ǰ��Ϊ�ղ��Ҳ���ը������
					this.setX(this.getX() + v);
				}
			}catch (Exception e){
				System.out.println("yuejuerig"+playNumber+getH()+"!!"+getW());
			}

			if(imageIndex <24){
				imageIndex++;//С��24������
			}else{//�����ڻ��ߴ���24ʱ������1
				imageIndex =1;
			}
		}
		if (this.downBoolean) {
			try {
				if (MapManager.mapList[dy+1][dx]==null||!(MapManager.mapList[dy][dx] instanceof Bomb)) {//���Ҫ�ߵĸ���ǰ����ը���Ͳ���
					this.setY(this.getY() + v);
				}
			}catch (Exception e){
				System.out.println("yuejuedown"+playNumber+" "+getH()+"!!"+getW());
			}


			if(imageIndex <24){
				imageIndex++;//С��24������
			}else{//�����ڻ��ߴ���24ʱ������1
				imageIndex =1;
			}
		}
	}


	@Override
	// ������Ϊ����������ը����bomb�ࣩ���������Ҫ����һ��ը��������Ϊը���Ĳ�����������bomb��֮���ٷ����ά�����в�����ը
	public void behavior() {

		//������Ҫͨ������õ�����ը�����ڵĸ��ӵط�
		// dx��dy���ǻ�����λ���������,
		int dx=(getX()+getW()/2)/MapManager.dxdy;//���ڿ�ȣ���Ҫ��ȡһ�볤�ȣ�֮�����x�����ȡ�е�λ�ã���֮��ͨ�������ж�����
		int dy=(getY()+getH())/ MapManager.dxdy;//���ڸ߶ȣ�ֱ�Ӽ�����Ҹ߶ȼ���
		if (fireNow &&MapManager.mapList[dy][dx]!=null) {
			//System.out.println("po");
			return;
		}
		//��Ӧλ�ò����ж���
		if (!fireNow || bombNow>=bombNum||MapManager.mapList[dy][dx]!=null) {
			return;
		}

		bombNow=bombNow+1;//����һ��ը������

		//System.out.print(" play "+ bombNow+"\n");

		MusicPlayer musicPlayer=new MusicPlayer("sound/addBomb.wav",false);
		musicPlayer.start();

		//System.out.print(dx+"  "+dy+"\n");
		Bomb myBomb = (Bomb) new Bomb().build(dx, dy, bombPower,this);
		//�������еĶ�Ӧλ�ò���һ��ը����
		MapManager.mapList[dy][dx]=myBomb;

		fireNow=false;//��Ϊ�񣬷�ֹȫ�Զ���ը
		score += bombNow*10;

	}
	//������е�����������ע����Ч�ĸı�

	public void die() {

		if (deadIndex>38){
			//�ڲ�����֮����Ҫ�ö�Ӧ���������
			if (getHp()-1>0){//��δ��������
				setX(beginx);
				setY(beginy);
				setHp(getHp()-1);
				dieAndLive=true;//�黹����״̬
				deadIndex=0;
				setIcon(GameLoad.imgMap.get(playNumber+"up"+ imageIndex));
//				System.out.println(getHp());
			}else{//��������
				setIslive(false);
			}
		}else{
			setIcon(GameLoad.imgMap.get(playNumber+"dead"+deadIndex));
			deadIndex++;
		}
	}

	public int getScore(){
		return score;
	}



	@Override
	public Rectangle getRectangle() {//���⴦��,�����xy��Ҫ��ɺ�ը�����Ƶ�xy
		Rectangle myRectangle=new Rectangle(getX()+25,getY()+64,gethHit(),getwHit());
		return myRectangle;
	}


}
