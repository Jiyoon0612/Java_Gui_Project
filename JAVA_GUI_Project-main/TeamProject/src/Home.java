import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;
import java.util.Random;

public class Home extends JFrame {
   int water;
   String goalWeight;
   
   public Home(String[] user, int n) { 
        setTitle("Home");  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        Container c = getContentPane();
        c.setLayout(null);
        Color color = new Color(255,204,204);//color 색깔 설정
        c.setBackground(color);	//바탕색 color로 설정
        Font nameFont = new Font("맑은 고딕", Font.PLAIN, 17);  //폰트 설정
        Font goalFont = new Font("맑은 고딕", Font.BOLD, 25);   //폰트 설정
        Font btnFont = new Font("맑은 고딕", Font.PLAIN, 13);    //폰트 설정
        Font logoutFont = new Font("맑은 고딕", Font.PLAIN, 10);    //폰트 설정
      
        //목표체중
        goalWeight = Integer.toString(Integer.valueOf(user[5]) - Integer.valueOf(user[6]));
        
        //라벨들
        JLabel lName = new JLabel(user[2] + "님");
        Double cm = Double.parseDouble(user[4]) * 0.01;
        Double bmi = Double.parseDouble(user[5]) / (cm*cm);
        bmi = Math.round(bmi*100)/100.0;
        JLabel lBmi = new JLabel("BMI = " + bmi + " ");
        JLabel lGoal = new JLabel("-목표 체중까지 " + goalWeight + "kg-",JLabel.CENTER);//레이블 추가:위치 가운데 설정
        lName.setFont(nameFont);     //폰트 설정
        lBmi.setFont(btnFont);		 //폰트 설정
        lGoal.setFont(goalFont);     //폰트 설정
        if(bmi<18.5) {				 //저체중일때
        	lBmi.setText(lBmi.getText() + "저체중");
        	lBmi.setForeground(new Color(173, 255, 47));	//GreenYellow
        }
        else if(18.5<=bmi && bmi<23){//정상일때
        	lBmi.setText(lBmi.getText() + "정상");
        	lBmi.setForeground(new Color(65, 105, 255));	//RoyalBlue
        }
        else if(23<=bmi && bmi<25){	//과체중일때
        	lBmi.setText(lBmi.getText() + "과체중");
        	lBmi.setForeground(new Color(138, 43, 226));	//BlueViolet
        }
        else if(25<=bmi && bmi<30){	//비만일때
        	lBmi.setText(lBmi.getText() + "비만");
        	lBmi.setForeground(new Color(255, 0, 255));		//Magenta
        }
        else if(30<=bmi){			//고도비만일때
        	lBmi.setText(lBmi.getText() + "고도비만");
        	lBmi.setForeground(new Color(255, 20, 147));	//DeepPink
        }
        
        //물 마시기
        water = Integer.parseInt(user[9]);
        ImageIcon[] iconWater = new ImageIcon[9];for(int i = 0; i<=8; i++) {
        	iconWater[i] = new ImageIcon("C:\\Temp\\자바 팀프로젝트 (김예은, 안노아, 임지윤)팀\\cup\\" + i*250 + ".png");
        }
        
        //버튼들
        JButton bRecord = new JButton("식사 기록");
        JButton bWater = new JButton("<HTML><center>물 마시기<br><b>"+water+"ml</b></center></HTML>", iconWater[water/250]);
        bWater.setVerticalTextPosition(JButton.BOTTOM);
        bWater.setHorizontalTextPosition(JButton.CENTER);
        JButton bInfo = new JButton("정보 입력");
        JButton bSay = new JButton("다이어트 명언"); 
        JButton bStrat = new JButton("스트레칭"); 
        JButton bLogout = new JButton("로그아웃");
        bRecord.setFont(btnFont);
        bWater.setFont(btnFont);
        bInfo.setFont(btnFont);
        bSay.setFont(btnFont);
        bStrat.setFont(btnFont);
        bLogout.setFont(logoutFont);
        bRecord.setBackground(Color.DARK_GRAY); bRecord.setForeground(Color.WHITE);
        bWater.setBackground(Color.DARK_GRAY);  bWater.setForeground(Color.WHITE);
        bInfo.setBackground(Color.DARK_GRAY);   bInfo.setForeground(Color.WHITE);
        bSay.setBackground(Color.DARK_GRAY);    bSay.setForeground(Color.WHITE);
        bStrat.setBackground(Color.DARK_GRAY);  bStrat.setForeground(Color.WHITE);
        bLogout.setBackground(Color.DARK_GRAY);	bLogout.setForeground(Color.WHITE);

        //크기 및 위치 조정
        lName.setBounds(550, 5, 100, 40);
        lBmi.setBounds(490, 40, 140, 40);
        lGoal.setBounds(160, 80, 330, 50);
        bRecord.setBounds(100,140,130,60);
        bWater.setBounds(250,140,140,150);
        bInfo.setBounds(410,140,130,60);
        bSay.setBounds(100,230,130,60);
        bStrat.setBounds(410,230,130,60);
        bLogout.setBounds(0, 0, 80, 30);
         
        c.add(lName);
        c.add(lBmi);
        c.add(lGoal);
        c.add(bRecord);   
        c.add(bWater);
        c.add(bInfo);
        c.add(bSay);
        c.add(bStrat);
        c.add(bLogout);
      
        //액션 리스너
        bRecord.addActionListener(new ActionListener() {   //식사기록
            public void actionPerformed(ActionEvent e) {
               new Record(user, n);
               setVisible(false);
            }
         });
        bWater.addActionListener(new ActionListener() {   //물마시기
            public void actionPerformed(ActionEvent e) {
               JButton b = (JButton)e.getSource();
               water += 250;
               user[9] = Integer.toString(water);
               b.setText("<HTML><center>물 마시기<br><b>"+water+"ml</b></center></HTML>");
               if(water<=2000) {
            	   b.setIcon(iconWater[water/250]);
            	   if(water == 2000) 
            		   JOptionPane.showMessageDialog(null, "오늘 기준량에 도달하셨습니다! 축하드립니다! ٩( *˙0˙*)۶");
               }
               else if(water>2000)
            	   b.setIcon(iconWater[8]);
               updateFile(user, water);
            }	
         });
        bSay.addActionListener(new ActionListener() {	//다이어트 명언
	    	public void actionPerformed(ActionEvent e) {
	    	String[] m = new String[] {"먹어봤자 내가 아는 그 맛이다.","인생을 걸고 다이어트를 해라.","하루에 3분 거울 앞에서 냉철한 자기관리의 시간을 가져라.",
	    		"음식은 맛있을수록 칼로리도 높아지는것이다.","세상에서 가장 안전한 성형은 다이어트다.","인생은 살이 쪘을때와 안쪘을때로 나뉜다."
	    		,"세상의 변화된 시선과 대우를 꿈꿔라","나를 배부르게 한 것들이 나를 파괴한다.","죽을만큼 운동하고 죽지않을 만큼 먹었어요.","세끼 다 먹으면 살쪄요."};
	    	Random r =new Random();
	    	JOptionPane.showMessageDialog(null, m[r.nextInt(m.length)], "다이어트 명언", JOptionPane.INFORMATION_MESSAGE);
	    	}	
        });
        bInfo.addActionListener(new ActionListener() {   //정보입력
            public void actionPerformed(ActionEvent e) {
               new Info(user, n);
               setVisible(false);
            }
         });
         bStrat.addActionListener(new ActionListener() {   //스트레칭
            public void actionPerformed(ActionEvent e) {
               new Stratching(user, n);
               setVisible(false);
            }	
         });
         bLogout.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        		new Login(n);
        		setVisible(false);
        	 }
         });
      
        setSize(640,400);   //크기
        setVisible(true);   //보이도록
        setResizable(false);//화면크기 고정
   }
   public void updateFile(String user[], int water) {
		  try {
			 BufferedWriter out = new BufferedWriter(new FileWriter(("C:\\Temp\\자바 팀프로젝트 (김예은, 안노아, 임지윤)팀\\user" + user[10] + ".txt")));
			 user[9] = water+""; //tf1의 값을 받아 text1에 저장 -> user.txt에 출력할 문자
			
			 //파일 입력
			 for(int i = 0; i<user.length; i++)
				 out.write(user[i] + "\t");
			 
			 out.close();//스트림 닫기
		  }catch(IOException e1) {	  }
	  }
}