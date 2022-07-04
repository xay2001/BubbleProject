package com.pop.element;

import com.pop.manager.GameLoad;
import com.pop.manager.MapManager;
import com.pop.manager.Mapblock;

import java.awt.*;


public class Mapobj extends ElementObj{

	private Mapblock types;
	
	
	
	@Override
	public void showElement(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(this.getIcon().getImage(), this.getX()*45+ElementObj.final_x, this.getY()*45-21+ElementObj.final_y, this.getW(), this.getH(), null);
	}

	//���������õĽ�����һ����ͼ�п�ͨ���Ĳ���ͨ����Ԫ�ط��࣬��������ɵ�ʱ��ֻ��Ҫ�������������ɣ����ɿ�ʹ�����Ҳ������ʱֻ��һ����ע���ͨ�����ƻ�����Ҫ
	public ElementObj build(String Types,String string) {//С����,��Ҫ����������������
		// TODO Auto-generated method stub
		setW(46);
		setH(68);
		//���ÿ�͸�
		this.sethHit(45);
		this.setwHit(45);
		//��һ�����������жϿ�ͨ���벻��ͨ������
		switch (Types) {
			case "Breakable1":
				setIcon(GameLoad.imgMap.get(Types.toString()));types=Mapblock.Breakable1;setHp(1);
				break;
			case "Breakable2":
				setIcon(GameLoad.imgMap.get(Types.toString()));types=Mapblock.Breakable2;setHp(1);
				break;
			case "Breakable3":
				setIcon(GameLoad.imgMap.get(Types.toString()));types=Mapblock.Breakable3;setHp(2);
				break;
			case "Breakable4":
				setIcon(GameLoad.imgMap.get(Types.toString()));types=Mapblock.Breakable4;setHp(1);
				break;
			case "Breakable5":
				setIcon(GameLoad.imgMap.get(Types.toString()));types=Mapblock.Breakable5;setHp(1);
				setW(47);
				setH(52);
				break;
			case "Breakable6":
				setIcon(GameLoad.imgMap.get(Types.toString()));types=Mapblock.Breakable6;setHp(2);
				setW(47);
				setH(49);
				break;
			case "Breakable7":
				setIcon(GameLoad.imgMap.get(Types.toString()));types=Mapblock.Breakable7;setHp(1);
				setW(50);
				setH(67);
				break;
			case "Breakable8":
				setIcon(GameLoad.imgMap.get(Types.toString()));types=Mapblock.Breakable8;setHp(1);
				setW(50);
				setH(76);
				break;
			case "Breakable9":
				setIcon(GameLoad.imgMap.get(Types.toString()));types=Mapblock.Breakable9;setHp(1);
				setW(48);
				setH(74);
				break;
			case "UnBreakable1":
				setIcon(GameLoad.imgMap.get(Types.toString()));types=Mapblock.UnBreakable1;setHp(999);
				break;
			case "UnBreakable2":
				setIcon(GameLoad.imgMap.get(Types.toString()));types=Mapblock.UnBreakable2;setHp(999);
				break;
			case "UnBreakable3":
				setIcon(GameLoad.imgMap.get(Types.toString()));types=Mapblock.UnBreakable3;setHp(999);
				break;
			case "UnBreakable4":
				setIcon(GameLoad.imgMap.get(Types.toString()));types=Mapblock.UnBreakable4;setHp(999);
				break;
			case "UnBreakable5":
				setIcon(GameLoad.imgMap.get(Types.toString()));types=Mapblock.UnBreakable5;setHp(999);
				setW(57);
				setH(58);
				break;
			case "UnBreakable6":
				setIcon(GameLoad.imgMap.get(Types.toString()));types=Mapblock.UnBreakable6;setHp(999);
				setW(53);
				setH(79);
				break;
			case "UnBreakable7":
				setIcon(GameLoad.imgMap.get(Types.toString()));types=Mapblock.UnBreakable7;setHp(999);
				setW(53);
				setH(62);
				break;

			case "mid1":
				setIcon(GameLoad.imgMap.get(Types.toString()));types=Mapblock.mid1;setHp(999);
				setW(131);
				setH(142);
				this.sethHit(135);
				this.setwHit(135);
				break;
			case "mid2":
				setIcon(GameLoad.imgMap.get(Types.toString()));types=Mapblock.mid2;setHp(999);
				setW(136);
				setH(135);
				this.sethHit(135);
				this.setwHit(135);
				break;
			case "mid3":
				setIcon(GameLoad.imgMap.get(Types.toString()));types=Mapblock.mid3;setHp(999);
				setW(135);
				setH(150);
				this.sethHit(135);
				this.setwHit(135);
				break;
			default:
				break;
		}
		//�ڶ��������� �±� ����ʽ����Ҫ�任
		//�ļ��У���һ����x�ڶ�����y���±��У���һ����y�ڶ�����x
		String []arr=string.split(",");
		this.setX(Integer.parseInt(arr[0]));
		this.setY(Integer.parseInt(arr[1]));
		return this;
	}

	/*
	 * @�˺��������ж��ϰ�����
	 * */
	public Mapblock getTypes() {
		return types;
	}

	@Override
	public void setIslive(boolean islive) {
		// TODO Auto-generated method stub
		this.setHp(getHp()-1);
		if(this.getHp() >0) {
			return;
		}
		super.setIslive(islive);
	}

	@Override
	public void die(){//��������
		//�˴���Ҫ���ɵ���
		int max=15,min=1;//һ������е���
		int ran2 = (int) (Math.random()*(max-min)+min);
		if (ran2<9){//ը������
			PropEffect prop=null;
			if (ran2==1){//����
				prop= (PropEffect) new PropEffect().build(getX(),getY(),4);
			}else if(ran2<4){//2,3�ƶ��ٶ�
				prop= (PropEffect) new PropEffect().build(getX(),getY(),2);
			}else if(ran2<6){//4,5ը������
				prop= (PropEffect) new PropEffect().build(getX(),getY(),1);
			}else if (ran2<9) {//6,7,8ը������
				prop= (PropEffect) new PropEffect().build(getX(),getY(),3);
			}
			MapManager.mapList[getY()][getX()]=prop;
		}else{//ƨ��û��
			MapManager.mapList[getY()][getX()]=null;
		}
	}
	@Override
	public Rectangle getRectangle() {
		Rectangle myRectangle=new Rectangle(getX()*45,getY()*45,gethHit(),getwHit());
		return myRectangle;
	}

}
