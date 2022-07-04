package com.pop.manager;

import com.pop.controller.MusicPlayer;
import com.pop.element.ElementObj;
import com.pop.element.Mapobj;

import java.io.InputStream;
import java.util.*;

import javax.swing.ImageIcon;


/*
 * @������,������ļ�����ͼƬ
 * @������������ص�map��
 */
public class GameLoad {

	private static GameLoad elementLoader;
	
	private static ElementManager em=ElementManager.getManager();// �õ�Ԫ�ع���������

	public ElementObj[][] getMapBlock() {
		return mapBlock;
	}

	public void setMapBlock(ElementObj[][] mapBlock) {
		this.mapBlock = mapBlock;
	}

	private ElementObj  mapBlock[][]=new ElementObj[17][15];

	public static HashMap<String, ImageIcon> imgMap= new HashMap<String, ImageIcon>();

	//public static HashMap<String, MusicPlayer> soundMap = new HashMap<>();

	private Map<String, List<String>> gameInfoMap = new HashMap<>();//��Ϸ��Ϣ�ֵ�

	private Map<String, ImageIcon> imageMap = new HashMap<>();//ͼƬ�ֵ�
	
	private static Properties pro=new Properties();
	
	
	//����ͼƬ����
	public static void imageLoad() {
		String imgpro="com/pop/text/Gamedata.pro";
		ClassLoader classLoader=GameLoad.class.getClassLoader();
		InputStream imgs=classLoader.getResourceAsStream(imgpro);
		if (imgs==null) {
			System.out.println("ͼƬ�����ļ���ȡ����");
		}
		try {
			pro.clear();
			pro.load(imgs);
			Set<Object> keys=pro.keySet();
			for (Object key:keys) {
				String url = pro.getProperty(key.toString());
				//����image��map��
				imgMap.put(key.toString(), new ImageIcon(url));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ͼƬ�����ļ����ش���");
		}
		
	}

//	//������������
//	public static void soundLoad() {
//		String soundpro="com/pop/text/Soundata.pro";
//		ClassLoader classLoader=GameLoad.class.getClassLoader();
//		InputStream sounds=classLoader.getResourceAsStream(soundpro);
//		if (sounds==null) {
//			System.out.println("���������ļ���ȡ����");
//		}
//		try {
//			pro.clear();
//			pro.load(sounds);
//			Set<Object> keys=pro.keySet();
//			for (Object key:keys) {
//				String url = pro.getProperty(key.toString());
//				//����image��map��
//				soundMap.put(key.toString(), new MusicPlayer(url));
//			}
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("���������ļ����ش���");
//		}
//
//	}

	//���ɵ�ͼ�ຯ������Ҫ�������MapManager�е�maplist��ֵ
	public static void MapLoad(int mapID) {

		String mapName="com/pop/text/"+mapID+".map";
		ClassLoader classLoader=GameLoad.class.getClassLoader();
		InputStream mapStream=classLoader.getResourceAsStream(mapName);

		if (mapStream==null) {
			System.out.println("��ͼ�����ļ���ȡ����");
		}
		try {
			pro.clear();
			pro.load(mapStream);
			Enumeration<?> names=pro.propertyNames();
			while (names.hasMoreElements()) {
				String key = names.nextElement().toString();
				String [] arrsStrings=pro.getProperty(key).split(";");
				//�����key��Breakable���߲����ƻ���
				for (int i = 0; i < arrsStrings.length; i++) {//����ֱ����map���渳ֵ
					String []arr= arrsStrings[i].split(",");//�ļ��У���һ����x�ڶ�����y
					int y=(Integer.parseInt(arr[1]));
					int x=(Integer.parseInt(arr[0]));
					ElementObj mapElement= new Mapobj().build(key, arrsStrings[i]);
					MapManager.mapList[y][x]=mapElement;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("��ͼ�����ļ����ش���");
		}
	}

	//����ģʽ
	public static GameLoad getElementLoader() {
		if (elementLoader == null) {
			elementLoader = new GameLoad();
		}
		return elementLoader;
	}
	public Map<String, List<String>> getGameInfoMap() {
		return gameInfoMap;
	}
	public static HashMap<String, ImageIcon> getImgMap() {
		return imgMap;
	}
}

