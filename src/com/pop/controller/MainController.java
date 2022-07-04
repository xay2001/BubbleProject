package com.pop.controller;

import com.pop.element.ElementObj;
import com.pop.element.Play;
import com.pop.manager.ElementManager;
import com.pop.manager.GameElement;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashSet;

import java.util.List;
import java.util.Set;



/*
 * @�������߳���
 * @ֻ���������ͷ�����Ϣ����
 * 
 * */
public class MainController implements KeyListener {

	// ���Ԫ�ع�����
	private ElementManager em;

	/*�ܷ�ͨ��һ����������¼���а��µļ�������ظ���������ֱ�ӽ���
	 * ͬʱ����1�ΰ��£���¼�������У���2���ж������з��С�
	 *       �ɿ���ֱ��ɾ�������еļ�¼��
	 * set����
	 * */
	private Set<Integer> set=new HashSet<Integer>();
	// controller�Ĺ�������
	public MainController() {
		super();
		em = ElementManager.getManager();// �õ�Ԫ�ع���������
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key=e.getKeyCode();
		if(set.contains(key)) { //�ж��������Ƿ��Ѿ�����,�����������
//			�������ֱ�ӽ�������
			return;
		}
		set.add(key);
		List<ElementObj> plays = em.getElementsByKey(GameElement.PLAY);
		for (int i = 0; i < plays.size(); i++) {
			Play obj = (Play) plays.get(i);
			obj.keyClick(true, e.getKeyCode());// ����play���ڲ�key������ʾ�������
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(!set.contains(e.getKeyCode())) {//�����������ڣ���ֹͣ
			return;
		}//����(�Ѿ������������)
		set.remove(e.getKeyCode());//�Ƴ�����
		List<ElementObj> plays = em.getElementsByKey(GameElement.PLAY);
		for (int i = 0; i < plays.size(); i++) {
			Play obj = (Play) plays.get(i);
			obj.keyClick(false, e.getKeyCode());// ����play���ڲ�key������ʾ�������
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
}
