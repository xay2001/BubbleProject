package com.pop.element;

import com.pop.controller.MusicPlayer;
import com.pop.manager.GameLoad;
import com.pop.manager.MapManager;

import java.awt.*;

/*
* @����
* */
public class PropEffect extends ElementObj {

	int types;
	int propIndex =1;

	@Override
	public void showElement(Graphics g) {//չʾ���ߺ���
		try {
			g.drawImage(this.getIcon().getImage(), this.getX()* MapManager.dxdy+ElementObj.final_x, this.getY()* MapManager.dxdy-21+ElementObj.final_y, this.getW(), this.getH(), null);
		}catch (Exception e){
	//		System.out.println("������");
		}
	}
	//prop�ڵ�move��������Ϊ����������ֻ��Ϊ��������
	//���ߵĶ�����51֡
	public void move() {

		if(propIndex <51){
			propIndex++;//С��51������
		}else{//�����ڻ��ߴ���51ʱ������1
			propIndex =1;
		}
		setIcon(GameLoad.imgMap.get(types+"prop"+ propIndex));

	}
	//�˺������ս������ǵ�λ��xy
	public ElementObj build(int x,int y,int types) { ;

		setH(66);
		setW(51);
		setX(x);
		setY(y);
		sethHit(45);
		setwHit(45);
		this.types=types;
		setIcon(GameLoad.imgMap.get(types+"prop"+ propIndex));
		return this;
	}

	public void get(Play play){//�˺�������Ҽ������

	//	System.out.println("prop");
		MusicPlayer get=new MusicPlayer("sound/eatProp.wav",false);
		get.start();

		//����ͨ��types������ͬЧ��
		switch (types) {
			case 1://bombpower
				play.setBombPower(play.getBombPower()+1);
				break;
			case 2://fatser
				play.setV(play.getV()+1);
				break;
			case 3://morebomb
				play.setBombNum(play.getBombNum()+1);
				break;
			case 4://morelife
				play.setHp(play.getHp()+1);
				break;
			default:
				break;
		}

	}

	public Rectangle getRectangle() {//�������ײ������ҪԪ���Լ��ı��

		Rectangle myRectangle=new Rectangle(getX()*45,getY()*45,gethHit(),getwHit());
		return myRectangle;
	}

	public void die() {//�����������˷���ֻ���ڵ��߱�ը���ƻ�������²Żᱻʹ��
		MapManager.mapList[getY()][getX()]=null;
	}
}
