package com.qfedu.house.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public final class CommonUtil {
	private static final String CHARS="1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";

	private static final int LEN=CHARS.length();
	private CommonUtil(){
		throw new AssertionError();
	}
	
	public static String generateVC(int len) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < len; ++i) {
			int index = (int) (Math.random() * LEN);
			sb.append(CHARS.charAt(index));
		}
		return sb.toString();
	}
	
	public static BufferedImage getImageFromString(String code,int width,int height){
		int size=(width-10)/code.length();
		BufferedImage bi=new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		//�õ�����,��ͼ�������Ļ���
		Graphics2D g=(Graphics2D) bi.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, width, height);
		//OCR��ѧ����ʶ��
		g.setFont(new Font("Arial", Font.BOLD, height*4/5));
		for (int i = 0; i < code.length(); i++) {
			g.setColor(Color.BLUE);
			String str = code.substring(i, i + 1);
			double theta=Math.PI/18*randomDouble(-1, 1);
			g.rotate(theta,10,20);
			g.drawString(str, 10+size*i, height-8);
			g.rotate(-theta,10,20);
		}
		return bi;
	}
	public static double randomDouble(double min,double max){
		return Math.random()*(max-min)+min;
	}
}
