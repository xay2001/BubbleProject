package com.pop.controller;

import com.pop.element.*;
import com.pop.manager.ElementManager;
import com.pop.manager.GameElement;
import com.pop.manager.GameLoad;
import com.pop.manager.MapManager;
import com.pop.show.GameJFrame;
import com.pop.show.GameMainJPanel;
import com.pop.show.GameOverJPanel;

import java.util.List;
import java.util.Map;

import javax.swing.*;




public class GamemainThread extends Thread {

	GameOverJPanel jp_over = new GameOverJPanel();

	private ElementManager em = ElementManager.getManager();// �õ�Ԫ�ع���������;
	private static int allTime = 600*1000; //10����

	int Level;
	boolean onePlay;
	GameMainJPanel MainJpanl;
	MusicPlayer musicPlayer=null;

	boolean Lose=false;
	boolean Win=false;

	public GamemainThread(int level, boolean one, GameMainJPanel MainJpanl){
		super();
		Level=level;
		this.onePlay=one;
		this.MainJpanl=MainJpanl;
	}
	@Override
	public void run() {
		//������Ҫ�����������
		musicPlayer=new MusicPlayer("sound/gameMusic_"+Level+".wav",true);
		musicPlayer.start();
		while (true) {// true�ɱ�Ϊ�������ƽ���
			// ��Ϸ��ʼ,������Ӧ����Դ����
			gameload();
			// ��Ϸ����
			gamerun();
			// ��Ϸ����
			gameover();
		}
	}

	private void gameload() {
		// TODO Auto-generated method stub

		// ʵʱ�����ͼ
		//System.out.println(Level);
		GameLoad.MapLoad(Level);

		// �������

		load();
	}

	public void load() {
//		ͼƬ����
//�ĸ��Ǳ꣺40, 20   670, 530   40,530  670, 20
		if (onePlay){
			ImageIcon icon1 = GameLoad.imgMap.get("1down1");
			Play obj1 = new Play(40, 20,"1", icon1);// ʵ�������1
			em.addElement(obj1, GameElement.PLAY);
			loadEnemy(3);
		}else{
			ImageIcon icon1 = GameLoad.imgMap.get("1down1");
			ImageIcon icon2 = GameLoad.imgMap.get("2down1");
			Play obj1 = new Play(40, 20,"1", icon1);// ʵ�������1
			Play obj2 = new Play(670, 530,"2", icon2);// ʵ�������2
			// ֱ�����
			em.addElement(obj2, GameElement.PLAY);
			em.addElement(obj1, GameElement.PLAY);
			loadEnemy(2);
		}
	}

	private void loadEnemy(int i) {//���ɵ��˺���
		//�ĸ��Ǳ꣺40, 20   670, 530   40,530  670, 20
		if (i==3){
			ImageIcon icon1 = GameLoad.imgMap.get("2down1");
			ImageIcon icon2 = GameLoad.imgMap.get("3down1");
			ImageIcon icon3 = GameLoad.imgMap.get("4down1");
			Enemy Enemy1 = new Enemy(40, 530,"2", icon1);// ʵ��������2
			Enemy Enemy2 = new Enemy(670, 20,"3", icon2);// ʵ��������3
			Enemy Enemy3 = new Enemy(670, 530,"4", icon2);// ʵ��������4
			// ֱ�����
			em.addElement(Enemy2, GameElement.ENEMY);
			em.addElement(Enemy1, GameElement.ENEMY);
			em.addElement(Enemy3, GameElement.ENEMY);
		}else{
			ImageIcon icon1 = GameLoad.imgMap.get("3down1");
			ImageIcon icon2 = GameLoad.imgMap.get("4down1");
			Enemy Enemy1 = new Enemy(40, 530,"3", icon1);// ʵ��������3
			Enemy Enemy2 = new Enemy(670, 20,"4", icon2);// ʵ��������4
			// ֱ�����
			em.addElement(Enemy2, GameElement.ENEMY);
			em.addElement(Enemy1, GameElement.ENEMY);
		}
	}

	private void gamerun() {
		// TODO Auto-generated method stub

		allTime = 600*1000;
		/*
		File file=new File("sound/ikdu3-p6wm8.mp3");
		Player player = null;
			try {
				player = new Player(new FileInputStream(file));
			} catch (FileNotFoundException | JavaLayerException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
	        try {
				player.play();
			} catch (JavaLayerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
      */

		while (true) {// true�ɱ�Ϊ�ؿ��ж�
			//���潫��ѭ������͵���
			Map<GameElement, List<ElementObj>> all = em.getGameElements();

			for (GameElement ge : GameElement.values()) { // ������
				List<ElementObj> list = all.get(ge);
				if(list.isEmpty()&&ge==GameElement.PLAY){//����б�Ϊ�գ�˵������Ѿ�ȫ����������Ҫ����ѭ��
					Lose=true;break;
				}
				if(list.isEmpty()&&ge==GameElement.ENEMY){//����б�Ϊ�գ�˵�������Ѿ�ȫ����������Ҫ����ѭ��
					Win=true;break;
				}
				if (ge==GameElement.ENEMY){
					for (int i = 0; i < list.size(); i++) {
						Enemy obj = (Enemy) list.get(i);//�����жϼ���״̬��֮���ж�����״̬
						if(!obj.isDieAndLive()){
							obj.die();
							if (!obj.getislive()) {//����״̬
								list.remove(i--);
							}
							continue;
						}
						obj.autoWalk();
						obj.model();// ����ÿ�����model
					}
				}else if (ge==GameElement.PLAY){
					for (int i = 0; i < list.size(); i++) {
						Play obj = (Play) list.get(i);//�����жϼ���״̬��֮���ж�����״̬
						if(!obj.isDieAndLive()){
							obj.die();
							if (!obj.getislive()) {//����״̬
								list.remove(i--);
							}
							continue;
						}
						obj.model();// ����ÿ�����model
					}
				}
			}

			if (Lose||Win){//��������
				break;
			}

			//ʹ���˶�ά������ʽ�ĵ�ͼ�������������ʾ��Ҫʹ�ö�ά����ѭ������
			for (int dy = 0; dy < 15; dy++) { // ������
				for (int dx = 0; dx < 17; dx++) {
					if(MapManager.mapList[dy][dx]==null) {//�жϿ�ֵ
						continue;
					}
					if (!MapManager.mapList[dy][dx].getislive()) {
						MapManager.mapList[dy][dx].die();//������ʹ��,������model�У���Լ�ظ��ж�
						//ע��die������ʱ������һ����Ԫ����ԭ�أ������Ҫ��Ҫ���ÿղ����������������
						continue;
					}
					MapManager.mapList[dy][dx].model();// ����ÿ�����model
				}
			}


			List<ElementObj> Plays = all.get(GameElement.PLAY);
			//������ά��������ײ
			for (int dy = 0; dy < 15; dy++) {
				for (int dx = 0; dx < 17; dx++) {
					if(MapManager.mapList[dy][dx]==null) {//�жϿ�ֵ
						continue;
					}
					if (MapManager.mapList[dy][dx] instanceof Mapobj){
						Elementpk(Plays, (Mapobj) MapManager.mapList[dy][dx]);
					}else if (MapManager.mapList[dy][dx] instanceof BombEffect){
						Elementpk(Plays, (BombEffect) MapManager.mapList[dy][dx]);
					} else if (MapManager.mapList[dy][dx] instanceof PropEffect){
						Elementpk(Plays, (PropEffect) MapManager.mapList[dy][dx]);
					}
				}
			}
			List<ElementObj> Enemy = all.get(GameElement.ENEMY);
			//������ά��������ײ
			for (int dy = 0; dy < 15; dy++) {
				for (int dx = 0; dx < 17; dx++) {
					if(MapManager.mapList[dy][dx]==null) {//�жϿ�ֵ
						continue;
					}
					if (MapManager.mapList[dy][dx] instanceof Mapobj){
						Elementpk(Enemy, (Mapobj) MapManager.mapList[dy][dx]);
					}else if (MapManager.mapList[dy][dx] instanceof BombEffect){
						Elementpk(Enemy, (BombEffect) MapManager.mapList[dy][dx]);
					} else if (MapManager.mapList[dy][dx] instanceof PropEffect){
						Elementpk(Enemy, (PropEffect) MapManager.mapList[dy][dx]);
					}
				}
			}

			allTime = allTime - 10;
			try {
				// ��Һ��ϰ�
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

    private void Elementpk(List<ElementObj> A, Mapobj B) {
        // TODO Auto-generated method stub
        for (int i = 0; i < A.size(); i++) {//��ѭ��ѭ��play�б�
            Play C = ((Play) A.get(i));//��ȡ�����
            if (C.pk(B)) {//�����ײ���ϰ�
                switch (C.getFxString()) {
                    case "up":
                        C.setY(C.getY() + C.getV());
                        break;
                    case "down":
                        C.setY(C.getY() - C.getV());
                        break;
                    case "left":
                        C.setX(C.getX() + C.getV());
                        break;
                    case "right":
                        C.setX(C.getX() - C.getV());
                        break;
                }
            }
        }
    }

	private void Elementpk(List<ElementObj> A, PropEffect B) {
		// TODO Auto-generated method stub
		for (int i = 0; i < A.size(); i++) {//��ѭ��ѭ��play�б�
			Play C = ((Play) A.get(i));//��ȡ�����
			if (C.pk(B)) {//�����ײ�˵���
				B.get(C);//�ı���������
				//������֮����Ҫ������������
				B.die();
			}
		}
	}
	private void Elementpk(List<ElementObj> A, BombEffect B) {
		// TODO Auto-generated method stub
		for (int i = 0; i < A.size(); i++) {//��ѭ��ѭ��play�б�
			Play C = ((Play) A.get(i));//��ȡ�����
			if (C.pk(B)) {//�����ײ��ը����Ч
				C.setDieAndLive(false);
			}
		}
	}

	private void gameover() {//�����ȫ������֮�󣬻ᵽ������
		// TODO Auto-generated method stub
		musicPlayer.stop();
		if (Win){
			MainJpanl.win();
		}else if(Lose){
			MainJpanl.lose();
		}
		this.stop();
		//GameJFrame.getGameJFrame().setjPanel(jp_over);
	}
	public static int getAllTime() {
		return allTime;
	}

}
