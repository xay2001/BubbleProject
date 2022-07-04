package com.pop.manager;


import com.pop.element.ElementObj;
import com.pop.element.Enemy;
import com.pop.element.Play;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @˵�� ������Ԫ�ع�������ר�Ŵ洢���е�Ԫ�أ�ͬʱ���ṩ����
 * 		������ͼ�Ϳ��ƻ�ȡ����
 */
public class ElementManager {
	/*
	 * String ��Ϊkey ƥ�����е�Ԫ��  play -> List<Object> listPlay
	 *                             enemy ->List<Object> listEnemy 
	 * ö�����ͣ�����map��key�������ֲ�һ������Դ�����ڻ�ȡ��Դ
	 * List��Ԫ�صķ��� Ӧ���� Ԫ�� ����
	 * ����Ԫ�ض����Դ�ŵ� map�����У���ʾģ��ֻ��Ҫ��ȡ�� ���map�Ϳ���
	 * ��ʾ���еĽ�����Ҫ��ʾ��Ԫ��(����Ԫ�ػ���� showElement())
	 */
	private Map<GameElement,List<ElementObj>> gameElements;
//	������һ��������
	public Map<GameElement, List<ElementObj>> getGameElements() {
		return gameElements;
	}
//	���Ԫ��(����ɼ���������)
	public void addElement(ElementObj obj,GameElement ge) {
//		List<ElementObj> list = gameElements.get(ge);
//		list.add(obj);
		gameElements.get(ge).add(obj);//��Ӷ��󵽼����У���keyֵ���д洢
	}
//	����key���� list���ϣ�ȡ��ĳһ��Ԫ��
	public List<ElementObj> getElementsByKey(GameElement ge){
		return gameElements.get(ge);
	}	

	private static ElementManager EM=null; //����
//	synchronized�߳���->��֤������ִ����ֻ��һ���߳�
	public static synchronized ElementManager getManager() {
		if(EM == null) {//�����ж�
			EM=new ElementManager();
		}
		return EM;
	}
	private ElementManager() {//˽�л����췽��
		init(); //ʵ��������
	}


	public void init() {//ʵ�������������
//		hashMap hashɢ��
		gameElements=new HashMap<GameElement,List<ElementObj>>();
//		��ÿ��Ԫ�ؼ��϶����뵽 map��
		for(GameElement ge:GameElement.values()) { //������
			gameElements.put(ge, new ArrayList<ElementObj>());
		}

	}

	public static void refresh() {
		Map<GameElement, List<ElementObj>> all = ElementManager.getManager().getGameElements();

		for (GameElement ge : GameElement.values()) { // ������
			List<ElementObj> list = all.get(ge);
			for (int i = 0; i < list.size(); i++) {
				list.remove(i--);
			}
		}
	}

}







