import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

interface noEmpty {
	public boolean check();
	public default boolean IdPw(int id, int pw) {
		if(id != 0 && pw != 0)
			return true;
		else
			return false;
	}
}

public class Login extends JFrame implements noEmpty {
  //텍스트 필드
  JTextField tf1= new JTextField(10);
  JPasswordField pw1 = new JPasswordField(20);
  int n;
  String[] user = new String[11];
  public Login(int n) {
     setTitle("로그인");
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     Container c = getContentPane();
     setLocationRelativeTo(null);
     c.setLayout(null);
     c.setBackground(new Color(255, 204, 204));
     Font font = new Font("맑은 고딕", Font.BOLD, 12);
     this.n = n;
     
     //라벨
     JLabel la1 = new JLabel("아이디");
     JLabel la2 = new JLabel("비밀번호");
     la1.setFont(font);
     la2.setFont(font);
     ImageIcon logoicon = new ImageIcon("C:\\Temp\\자바 팀프로젝트 (김예은, 안노아, 임지윤)팀\\logo.png");
     JLabel logo = new JLabel(logoicon);

     //버튼
     JButton btn1 = new JButton("회원 가입");
     JButton btn2 = new JButton("로그인");
     btn1.addActionListener(new goJoin());
     btn2.addActionListener(new goLogin());
     btn1.setFont(font);
     btn2.setFont(font);
     btn1.setBackground(Color.DARK_GRAY);
     btn2.setBackground(Color.DARK_GRAY);
     btn1.setForeground(Color.WHITE);
     btn2.setForeground(Color.WHITE);
 
     //크기 및 위치 지정
     logo.setBounds(190, 30, 150, 100);
     la1.setBounds(130, 160, 80, 30);
     la2.setBounds(125, 210, 80, 30);
     tf1.setBounds(190, 160, 200, 30);
     pw1.setBounds(190, 210, 200, 30);
     btn2.setBounds(420, 150, 80, 100);
     btn1.setBounds(510, 320, 100, 30);

     c.add(logo);
     c.add(la1);
     c.add(tf1);
     c.add(la2); c.add(pw1);
     c.add(btn1); c.add(btn2);

     setVisible(true);
     setSize(640, 400);
     setResizable(false);
  }
  public boolean check() {
	  String pw = new String(pw1.getPassword());
	  if(IdPw(tf1.getText().length(), pw.length()))
		  return true;
	  else
		  return false;
  }
  
  //로그인 확인 함수
  public boolean loginRight() {
	  int pass = 0;					//가입성공 플래그
	  String[] splitedStr = null;	//
	  for(int i = 0; i<=n; i++) {	//파일별로 열어서 확인하기 위한 반복문
		  File file = new File("C:\\Temp\\자바 팀프로젝트 (김예은, 안노아, 임지윤)팀\\user" + i + ".txt");
		  try {
			  FileReader fr = new FileReader(file);
			  BufferedReader br = new BufferedReader(fr);
			  String line = "";
			  try {
				  while((line = br.readLine()) != null) {
					  int passId = line.indexOf(tf1.getText());
					  int passPw = line.indexOf(pw1.getText());
					  if(passId != -1 && passPw != -1) {	//로그인 성공
						  splitedStr = line.split("\t");
						  for(int j = 0; j<splitedStr.length; j++)
							  splitedStr[j] = splitedStr[j].trim();
						  for(int j = 0; j<splitedStr.length; j++) {
							  user[j] = splitedStr[j];
						  }
						  pass = 1;
						  JOptionPane.showMessageDialog(null, "로그인을 축하합니다.");
						  return true; }
					  }
				  br.close();
			  } catch(IOException e) {  }
		  } catch(FileNotFoundException e) {  }
	  }
	  if(pass == 0) { JOptionPane.showMessageDialog(null, "실패했습니다."); }
	  return false;
  }
  
  private class goLogin implements ActionListener {
    public void actionPerformed(ActionEvent e){
    	if(check()) {
    		if(loginRight()) {
    			new Home(user, n);
    			setVisible(false);
    		}
    	}
    	else
    		JOptionPane.showMessageDialog(null, "전부 입력해주세요.", "ERROR!", JOptionPane.ERROR_MESSAGE);
    }
  }

  private class goJoin implements ActionListener {
      public void actionPerformed(ActionEvent e){
          new Join(n);
          setVisible(false);
      }
    }

public static void main(String[] args){
	int n;
	int i = 0;
	while(true) {
		try{
		    FileReader fr = new FileReader("C:\\Temp\\자바 팀프로젝트 (김예은, 안노아, 임지윤)팀\\user" + i + ".txt");
		}catch (FileNotFoundException e) {
			n = i;
			break;
		}
		i++;
	}
	new Login(n);
  }
}