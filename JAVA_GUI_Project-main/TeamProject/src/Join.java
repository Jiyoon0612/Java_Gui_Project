import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class Join extends JFrame implements noEmpty {
	JRadioButton rb1 = new JRadioButton("남자");
	JRadioButton rb2 = new JRadioButton("여자");
	JButton btn1 = new JButton("가입하기");
	JButton btn2 = new JButton("아이디 중복확인");
	int pressBtn2 = 0;
	
	//텍스트필드
	JTextField tf1 = new JTextField();
	JTextField tf2 = new JTextField();
	JTextField tf3 = new JTextField();
	JTextField tf4 = new JTextField();
	JTextField tf5 = new JTextField();
	JTextField tf6 = new JTextField();
	int rbFlag = 0;
	int rbIndex;
	int fileNum;
	 String r =" ";
	  Join(int n){ 
	     setTitle("회원 가입");
	     setDefaultCloseOperation(EXIT_ON_CLOSE); //종료했을때 메모리상에서도 사라지게 하기
		 setLocationRelativeTo(null); 
		 Container c = getContentPane();
		 Color color = new Color(255, 204, 204);
		 c.setBackground(color);
         c.setLayout(null);
		 Font font = new Font("맑은 고딕", Font.BOLD, 12);
		 fileNum = n;
	     
		 //라벨s
	     JLabel la1 = new JLabel("아이디");
	     JLabel la2 = new JLabel("비밀번호");
	     JLabel la3 = new JLabel("이름");
	     JLabel la4 = new JLabel("성별");
	     JLabel la5 = new JLabel("키");
	     JLabel la6 = new JLabel("현재 체중");
	     JLabel la7 = new JLabel("목표 체중");
		 la1.setFont(font);
		 la2.setFont(font);
		 la3.setFont(font);
		 la4.setFont(font);
		 la5.setFont(font);
		 la6.setFont(font);
		 la7.setFont(font);
	     
		 //라디오 버튼
		 rb1.setBackground(color);
		 rb2.setBackground(color);
		 rb1.setFont(font);
		 rb2.setFont(font);
	     ButtonGroup gb = new ButtonGroup();
	     gb.add(rb1);
    	 gb.add(rb2);
    	 rb1.addItemListener(new rbAction());
    	 rb2.addItemListener(new rbAction());
    	 
		 //버튼
		 btn1.setBackground(Color.DARK_GRAY);
		 btn1.setForeground(Color.WHITE);
		 btn1.addActionListener(new goLogin());
		 btn2.setBackground(Color.DARK_GRAY);
		 btn2.setForeground(Color.WHITE);
		 

		 //크기 및 위치 지정
	     la1.setBounds(145, 50, 120, 30);
	     la2.setBounds(135, 90, 120, 30);
	     la3.setBounds(155, 140, 120, 30);
	     la4.setBounds(155, 190, 120, 30);
	     la5.setBounds(165, 250, 120, 30);
	     la6.setBounds(130, 300, 120, 30);
	     la7.setBounds(130, 350, 120, 30);
	     tf1.setBounds(200, 50, 150, 30);
	     tf2.setBounds(200, 90, 150, 30);
	     tf3.setBounds(200, 140, 150, 30);
	     rb1.setBounds(210,190, 60, 30);
	     rb2.setBounds(290, 190,100, 30);
	     tf4.setBounds(200, 250, 150, 30);
	     tf5.setBounds(200, 300, 150, 30);
	     tf6.setBounds(200, 350, 150, 30);
	     btn1.setBounds(275, 410, 110, 30);
	     btn2.setBounds(115, 410, 150, 30);

	     c.add(la1);	c.add(la2);	c.add(la3);
	     c.add(la4);	c.add(la5);	c.add(la6);	c.add(la7);
	     c.add(tf1);	c.add(tf2);
         c.add(tf3);	c.add(tf4); c.add(tf5); c.add(tf6);
		 c.add(rb1);	c.add(rb2);
         c.add(btn1);	c.add(btn2);
	     
	     setSize(500,500);
	     setVisible(true);
		 setResizable(false);
		 
		//중복확인 버튼
		  btn2.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	if(tf1.getText().isEmpty())
	            		JOptionPane.showMessageDialog(null, "아이디를 입력해주십시오.");
	            	else {
	            		pressBtn2 = 1;
	            		int flag = 1;
	            		String[] user = new String[11];
	            		String[] splitedStr = null;
	            		for(int i = 0; i<=fileNum; i++) {	//파일별로 열어서 확인하기 위한 반복문
	            			File file = new File("C:\\Temp\\자바 팀프로젝트 (김예은, 안노아, 임지윤)팀\\user" + i + ".txt");
	            			try {
	            				FileReader fr = new FileReader(file);
	            				BufferedReader br = new BufferedReader(fr);
	            				String line = "";
	            				try {
	            					while((line = br.readLine()) != null) {
	            						splitedStr = line.split("\t");
	            						for(int j = 0; j<splitedStr.length; j++)
	            							splitedStr[j] = splitedStr[j].trim();
	            						for(int j = 0; j<splitedStr.length; j++)
	            							user[j] = splitedStr[j];
	            						for(int j = 0; j<splitedStr.length; j++)
	            						if(tf1.getText().equals(user[0])) {
	            							flag = 0;
	            							break;
	            						}
	            					}
	            					br.close();
	            				} catch(IOException a) {  }
	            			} catch(FileNotFoundException a) {  }
	            		}
	            		if(flag == 0) {
	            			JOptionPane.showMessageDialog(null, "중복된 아이디입니다.", "ERROR!", JOptionPane.ERROR_MESSAGE);
	            			pressBtn2 = 2;	}
	            		else
	            			JOptionPane.showMessageDialog(null, "사용가능한 아이디입니다.");
	            	}
	             }	
	          });
	  }		  
	  
	  //파일에 저장하는 함수
	  public void userFile() {
		  try {
			 BufferedWriter out = new BufferedWriter(new FileWriter(("C:\\Temp\\자바 팀프로젝트 (김예은, 안노아, 임지윤)팀\\user" + fileNum + ".txt")));
			 String text1 = tf1.getText(); //아이디
			 String text2 = tf2.getText(); //비밀번호
			 String text3 = tf3.getText(); //이름
			 String text4 = r;  		   //성별 
			 String text5 = tf4.getText(); //키
			 String text6 = tf5.getText(); //체중
			 String text7 = tf6.getText(); //목표체중
			
			 //파일 입력
			 out.write(text1 + "\t");	//아이디
			 out.write(text2 + "\t");	//비밀번호
			 out.write(text3 + "\t");	//이름
			 out.write(text4 + "\t");	//성별
			 out.write(text5 + "\t");	//키
			 out.write(text6 + "\t");	//체중
			 out.write(text7 + "\t");	//목표체중
			 out.write("" + "\t");		//섭취한 음식 목록
			 out.write("0" + "\t");		//섭취한 칼로리 목록
			 out.write("0" + "\t");		//물 기록
			 out.write(fileNum + "\t");	//파일 번호 기록
			 
			 out.newLine();
			 
			 out.close();//스트림 닫기
		  }catch(IOException e1) {
			  e1.printStackTrace();
		  }
	  }
	 
	//입력확인 클래스
	public boolean check() {
		  if(IdPw(tf1.getText().length(), tf2.getText().length())) {
			  if(!tf3.getText().isEmpty() && rbFlag == 1 && !tf4.getText().isEmpty() && !tf5.getText().isEmpty() && !tf6.getText().isEmpty())
				  return true;
			  else return false;
		  }
		  else
			  return false;
	 }
	
	  class rbAction implements ItemListener{	//성별 리스너
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.DESELECTED)
					rbFlag = 0;					//성별을 고르지 않았음을 알리는 플래그
				if(rb1.isSelected()) {			//남자를 선택했을 때
					rbFlag = 1; rbIndex = 0; }	//성별을 골랐음을 플래그에 저장 후 성별 값 변수에 저장
				else if(rb2.isSelected()) {		//여자를 선택했을 때
					rbFlag = 1; rbIndex = 1; }	//성별을 골랏음을 플래그에 저장 후 성별 값 변수에 저장
			}
		}

	  private class goLogin implements ActionListener { //로그인 버튼 눌렀을때 action
		public void actionPerformed(ActionEvent e){// 라디오 버튼 값 가져오기
			if(rb1.isSelected()) { // rb1이 선택되있을때
				r = rb1.getText(); //rb1의 text를 r에 가져온다
			}
			if(rb2.isSelected()) {// rb2가 선택되있을때
				r = rb2.getText();//rb2의 text를 r에가져온다
			}
			if(check()) {
				if(pressBtn2 == 1) {
				  	userFile();
				  	fileNum+=1;
				  	JOptionPane.showMessageDialog(null, "회원가입을 축하합니다.");
				  	new Login(fileNum);
				  	setVisible(false);
				}
				else if(pressBtn2 == 0)
					JOptionPane.showMessageDialog(null, "아이디 중복확인을 해주십시오.", "ERROR!", JOptionPane.ERROR_MESSAGE);
				else if(pressBtn2 == 2) {
					JOptionPane.showMessageDialog(null, "중복확인을 다시 해주십시오.", "ERROR!", JOptionPane.ERROR_MESSAGE);
					pressBtn2 = 0;
				}
			}
			else
				JOptionPane.showMessageDialog(null, "전부 입력해주세요.", "ERROR!", JOptionPane.ERROR_MESSAGE);
		} 
	  }
}