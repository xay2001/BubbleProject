package com.pop.element;


import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import com.pop.controller.MusicPlayer;
import com.pop.manager.*;
import com.pop.show.GameMainJPanel;


/*
* @ը���࣬��������ɵ�ը�������
* @bomb������һ������ ������������ �������� ������ʱ�򽫻ᰴһ�����ڲ����� �������� ������������ը��Ч�������Լ���ԭ����ʧ
* ע�⣬������������������xy�ĺ��嶼�������е�xy
* */
public class Bomb extends ElementObj {


	Play host=null;//��������

	int power=1;
	int time=4;//ը������
	int bombIndex=1;
	ImageIcon a=GameLoad.imgMap.get("bomb1");

	@Override
	public void showElement(Graphics g) {//չʾը������
		// TODO Auto-generated method stub
		//ע�⣬����ը��ͼƬ������������Ҫ�ٰ�ը�������ƶ�һ����ֵ geth-dxdy=66-45=21�����ǵ�Ŀ���ǵײ����������ڵ�����
		g.drawImage(this.getIcon().getImage(), this.getX()* MapManager.dxdy+ElementObj.final_x, this.getY()* MapManager.dxdy-21+ElementObj.final_y, this.getW(), this.getH(), null);
	}
	//bomb�ڵ�move��������Ϊ����������ֻ��Ϊ��������
	//ը����ը������27֡
	public void move() {

		if(bombIndex<27){
			bombIndex++;//С��27������
		}else{//�����ڻ��ߴ���27ʱ������1,�������ݵ�����һ
			bombIndex=1;
			time=time-1;
		}
		setIcon(GameLoad.imgMap.get("bomb"+bombIndex));
		if (time<=0) {
			setIslive(false);
		}
	}
	//�˺������ս������ǵ�λ��xy��ը�����е�xy���������
	public ElementObj build(int x,int y,int bombPower,Play host) {

		this.host=host;
		this.power=bombPower;

		setH(66);
		setW(51);
		setX(x);
		setY(y);
		sethHit(45);
		setwHit(45);
		//System.out.print(getX()+"  "+getY()+"\n");

		setIcon(a);
		return this;
	}

	public void die() {//��������,���ά���������һ������,������󽫻Ქ�Ŷ���(���˶���)��ע�⣬����������ɶ������������ǵ���ʱ��������
		// TODO Auto-generated method stub
		//System.out.print(" bomb "+ host.bombNow+"\n");
		int now=host.getBombNow();
		now=now-1;
		host.setBombNow(now);//ը����������

		MusicPlayer bomb=new MusicPlayer("sound/bomb.wav",false);
		bomb.start();


		//�����������ı�ը��Ч
		ElementObj bombEffect=new BombEffect().build (getX(), getY(),59,59,"center","center");
		MapManager.mapList[getY()][getX()]=bombEffect;

		//������Ҫ����ը���������Զ�ά�����е�   x-power x+power y-power y+power  �������ν����ϰ���鿴
		see( true,getY(),getX());
		see( false,getY(),getX());

	}

	//�˺���Ϊ���ܺ���������die����������Ч
	private void see(Boolean yfixedOrnot,int yIndex,int xIndex){
		if (yfixedOrnot){//�̶�y
			//ע��ĩβ��Ч���м���Ч��ͬ�����ѭ����Ҫ����һ����λ������ж�һ��ĩβ��Ч

			//ע��ʹ����ɢʽ�ж�
			boolean stop=false;//�Ƿ��ܵ��谭�жϱ����������ж��Ƿ���Ҫ���ĩβ��Ч
			int dx;
			//System.out.println(power);
			for (dx = xIndex+1; dx <= xIndex+power-1; dx++) {//�����ж�
				if(MapManager.mapList[yIndex][dx]==null) {//�жϿ�ֵ
					//������Ч
					ElementObj bombEffect=new BombEffect().build (dx, yIndex,41,37,"middle","right");
					MapManager.mapList[yIndex][dx]=bombEffect;
				}else{
					if (!(MapManager.mapList[yIndex][dx] instanceof BombEffect)){//�������ը����Ч�Ͳ���ҪҪ�ÿ���
						MapManager.mapList[yIndex][dx].setIslive(false);
					}
					stop=true;
					break;
				}
			}

			//���ĩβ��Ч,��stopΪ�������²���Ҫ���,���û�б��谭������Ψ��̫Сû���������ѭ����һ��������
			//ע��ĩβҲ�ᵼ���ƻ�����Ҫ���ڲ������ж�
			if (!stop) {
				if(MapManager.mapList[yIndex][dx]==null){
					ElementObj bombEffect = new BombEffect().build(dx, yIndex, 31, 37, "end", "right");
					MapManager.mapList[yIndex][dx] = bombEffect;
				}else{
					if (!(MapManager.mapList[yIndex][dx] instanceof BombEffect)){//�������ը����Ч�Ͳ���ҪҪ�ÿ���
						MapManager.mapList[yIndex][dx].setIslive(false);
					}
				}
			}

			dx = xIndex-1;
			stop=false;

			for (; dx >= xIndex-power+1; dx--) {//�����ж�
				if(MapManager.mapList[yIndex][dx]==null) {//�жϿ�ֵ
					//������Ч
					ElementObj bombEffect=new BombEffect().build (dx, yIndex,41,37,"middle","left");
					MapManager.mapList[yIndex][dx]=bombEffect;
				}else{
					if (!(MapManager.mapList[yIndex][dx] instanceof BombEffect)){//�������ը����Ч�Ͳ���ҪҪ�ÿ���
						MapManager.mapList[yIndex][dx].setIslive(false);
					}
					stop=true;
					break;
				}
			}
			if (!stop){//���ĩβ��Ч
				if(MapManager.mapList[yIndex][dx]==null) {
				ElementObj bombEffect=new BombEffect().build (dx, yIndex,31,37,"end","left");
				MapManager.mapList[yIndex][dx]=bombEffect;
				}else{
					if (!(MapManager.mapList[yIndex][dx] instanceof BombEffect)){//�������ը����Ч�Ͳ���ҪҪ�ÿ���
						MapManager.mapList[yIndex][dx].setIslive(false);
					}
				}
			}

		}else{//�̶�x
			boolean stop=false;//�Ƿ��ܵ��谭�жϱ����������ж��Ƿ���Ҫ���ĩβ��Ч
			int dy;
			for (dy = yIndex+1; dy <= yIndex+power-1; dy++) {//�����ж�
				if(MapManager.mapList[dy][xIndex]==null) {//�жϿ�ֵ
					//������Ч
					ElementObj bombEffect=new BombEffect().build (xIndex, dy,41,37,"middle","down");
					MapManager.mapList[dy][xIndex]=bombEffect;
				}else{
					if (!(MapManager.mapList[dy][xIndex] instanceof BombEffect)){//�������ը����Ч�Ͳ���ҪҪ�ÿ���
						MapManager.mapList[dy][xIndex].setIslive(false);
					}
					stop=true;
					break;
				}
			}
			if (!stop){//���ĩβ��Ч
				if(MapManager.mapList[dy][xIndex]==null){
					ElementObj bombEffect=new BombEffect().build (xIndex,dy,31,37,"end","down");
					MapManager.mapList[dy][xIndex]=bombEffect;
				}else{
					if (!(MapManager.mapList[dy][xIndex] instanceof BombEffect)){//�������ը����Ч�Ͳ���ҪҪ�ÿ���
						MapManager.mapList[dy][xIndex].setIslive(false);
					}

				}

			}

			dy = yIndex-1;
			stop=false;

			for (; dy >= yIndex-power+1; dy--) {//�����ж�
				if(MapManager.mapList[dy][xIndex]==null) {//�жϿ�ֵ
					//������Ч
					ElementObj bombEffect=new BombEffect().build (xIndex, dy,41,37,"middle","up");
					MapManager.mapList[dy][xIndex]=bombEffect;
				}else{
					if (!(MapManager.mapList[dy][xIndex] instanceof BombEffect)){//�������ը����Ч�Ͳ���ҪҪ�ÿ���
						MapManager.mapList[dy][xIndex].setIslive(false);
					}
					stop=true;
					break;
				}
			}
			if (!stop){//���ĩβ��Ч
				if(MapManager.mapList[dy][xIndex]==null) {
				ElementObj bombEffect=new BombEffect().build (xIndex, dy,31,37,"end","up");
				MapManager.mapList[dy][xIndex]=bombEffect;
				} else{
					if (!(MapManager.mapList[dy][xIndex] instanceof BombEffect)){//�������ը����Ч�Ͳ���ҪҪ�ÿ���
						MapManager.mapList[dy][xIndex].setIslive(false);
					}
				}
			}

		}

	}
}
