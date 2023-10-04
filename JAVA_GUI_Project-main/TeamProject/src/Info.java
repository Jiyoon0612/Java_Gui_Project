import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Info extends JFrame {
    int goalWeight;                  		//목표체중
    JTextField TnewW = new JTextField(5);   //무게 입력 텍스트필드
    JTextField TnewT = new JTextField(5);   //키 입력 텍스트필드

    public Info(String[] user, int n) {
        super("정보 업데이트");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        Container c = getContentPane();
        c.setLayout(null);
        Color color = new Color(255, 204, 204);
        c.setBackground(color);       //배경색 설정
        Font font = new Font("맑은 고딕", Font.BOLD, 12);		//라벨 폰트
        Font btnFont = new Font("맑은 고딕", Font.PLAIN, 13); //버튼 폰트
        Font btnFont2 = new Font("맑은 고딕", Font.PLAIN, 11); //버튼 폰트
        
        //홈 버튼
        ImageIcon Homeicon = new ImageIcon("C:\\Temp\\자바 팀프로젝트 (김예은, 안노아, 임지윤)팀\\home.png"); //홈 이미지 생성
        JButton Home = new JButton(Homeicon);           //홈 버튼 생성

        //입력들
        JLabel LoldW = new JLabel("이전 몸무게:             " + user[5] + " kg");   //이전 무게
        JLabel LoldT = new JLabel("       이전 키:           " + user[4] + " cm");//이전 키
        JLabel LnewW = new JLabel("현재 몸무게: ");     //현재 무게
        JLabel Lkg = new JLabel("kg");               //단위
        JLabel LnewT = new JLabel("       현재 키: "); //현재 키
        JLabel Lcm = new JLabel("cm");               //단위
        LoldW.setFont(font);
        LoldT.setFont(font);
        LnewW.setFont(font);
        LnewT.setFont(font);
        TnewW.setFont(font);
        TnewT.setFont(font);
        Lkg.setFont(font);
        Lcm.setFont(font);
        
        //키 변동없음 버튼
        JButton stay = new JButton("변동 없음");
        stay.setBackground(Color.DARK_GRAY);     //버튼 배경색
        stay.setForeground(Color.WHITE);         //글자색
        stay.setFont(btnFont2);

        //입력 완료 버튼
        JButton end = new JButton("입력 완료");	//입력 완료 버튼 생성
        end.setBackground(Color.DARK_GRAY);     //버튼 배경색
        end.setForeground(Color.WHITE);         //글자색
        end.setFont(btnFont);					//폰트 설정

        //크기 및 위치 지정
        Home.setBounds(0, 0, 35, 35);
        LoldW.setBounds(245, 60, 180, 50);
        LoldT.setBounds(242, 100, 180, 50);
        LnewW.setBounds(245, 170, 100, 50);
        TnewW.setBounds(336, 185, 42, 25);
        Lkg.setBounds(380, 170, 80, 50);
        LnewT.setBounds(242, 210, 100, 50);
        TnewT.setBounds(336, 220, 42, 25);
        Lcm.setBounds(380, 210, 80, 50);
        stay.setBounds(415, 218, 90, 30);
        end.setBounds(250, 300, 140, 40);

        c.add(Home);                                //홈 버튼
        c.add(LoldW);   c.add(LoldT);               //이전,현재 무게,키
        c.add(LnewW);   c.add(TnewW);   c.add(Lkg); //현재 무게
        c.add(LnewT);   c.add(TnewT);   c.add(Lcm); //현재 키
        c.add(stay);	c.add(end);					//변동없음 및 입력완료 버튼

        setSize(640, 400); //화면 크기
        setVisible(true);  //보이도록
        setResizable(false);
       
        stay.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		TnewT.setText(user[4]);
        	}
        });
        
        end.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if(!TnewT.getText().isEmpty() && !TnewW.getText().isEmpty()) {
        			int goalWeight;
        			user[5] = TnewW.getText();
        			user[4] = TnewT.getText();
        			updateFile(user);
        			goalWeight = Integer.valueOf(user[6]) - Integer.valueOf(user[5]);
        			if(goalWeight == 0) {
        				JOptionPane.showMessageDialog(null,"축하합니다! 목표체중을 달성하셨습니다!");	
        			}
        			setVisible(false);
        			new Home(user, n);
        		}
        		else
        			JOptionPane.showMessageDialog(null, "전부 입력해주세요.", "ERROR!", JOptionPane.ERROR_MESSAGE);
        	}
        });
        
        Home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Home(user, n);
                setVisible(false);
             }	
          });
    }
  //파일에 저장하는 함수
	  public void updateFile(String user[]) {
		  try {
			 BufferedWriter out = new BufferedWriter(new FileWriter(("C:\\Temp\\자바 팀프로젝트 (김예은, 안노아, 임지윤)팀\\user" + user[10] + ".txt")));
			 user[4] = TnewT.getText(); //tf1의 값을 받아 text1에 저장 -> user.txt에 출력할 문자
			 user[5] = TnewW.getText(); //tf2의 값을 받아 text2에 저장 -> user.txt에 출력할 문자
			
			 //파일 입력
			 for(int i = 0; i<user.length; i++)
				 out.write(user[i] + "\t");
			 
			 out.close();//스트림 닫기
		  }catch(IOException e1) {	  }
	  }
}

