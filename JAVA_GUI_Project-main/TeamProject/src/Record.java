import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Record extends JFrame{
      static ArrayList<String> foodList;
      static String[] food;				//음식 저장 배열
      static String[] kcal;				//칼로리 저장 배열
      static String[] foodsave;			//콤보박스에 넣을 음식칼로리 정보 저장 배열(음식 정보)
      JComboBox<String> foodCombo;		//음식 콤보박스
      int index;						//선택한 콤보박스의 인덱스
      String[] user1;

      public Record(String[] user, int n){
            setTitle("식 사 기 록");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null); 
            Container c = getContentPane();
            c.setLayout(null);
            Color color = new Color(255, 204, 204);
            c.setBackground(color);
            user1 = user;

            //홈버튼
            ImageIcon img1 = new ImageIcon("C:\\Temp\\자바 팀프로젝트 (김예은, 안노아, 임지윤)팀\\home.png");//홈 이미지
            JButton homebtn = new JButton(img1);      //버튼 생성
            
            //콤보 박스
            foodList = new ArrayList<String>();
            Rfile();
            fSave();
            foodCombo = new JComboBox<String>(foodsave);
            foodCombo.setBackground(Color.WHITE);	//배경색 설정
            foodCombo.setFont(new Font("맑은 고딕", Font.PLAIN, 13));//폰트 지정

            //추가 버튼
            ImageIcon img2 = new ImageIcon("C:\\Temp\\자바 팀프로젝트 (김예은, 안노아, 임지윤)팀\\plus.png");//추가 이미지
            JButton plusbtn = new JButton(img2);      //버튼 생성
            
            //섭취한 음식
            JLabel eatlabel = new JLabel ("섭취한 음식");     //섭취한 음식 라벨
            TextArea eatfood = new TextArea(user[7]);//섭취한 음식 목록
            eatlabel.setFont(new Font("맑은 고딕", Font.BOLD, 13)); //폰트 지정
            eatfood.setFont(new Font("맑은 고딕", Font.PLAIN, 13));            

            //총 섭취량
            JLabel eatinglabel = new JLabel ("총 섭취량:  " + user[8] + "kcal",JLabel.RIGHT);//총 섭취량
            eatinglabel.setFont(new Font("맑은 고딕", Font.BOLD, 16));    //폰트 지정
        
            //크기 및 위치 지정
            homebtn.setBounds(0, 0, 35, 35);
            foodCombo.setBounds(120, 60, 350, 30);
            plusbtn.setBounds(490, 60, 30, 30);
            eatlabel.setBounds(90, 100, 100, 20);
            eatfood.setBounds(95, 125, 450, 200);
            eatinglabel.setBounds(330, 330, 200, 30);

            //추가
            c.add(homebtn);
            c.add(foodCombo);    c.add(plusbtn);
            c.add(eatlabel);     c.add(eatfood);
            c.add(eatinglabel);

            setSize(640, 400);  //화면 크기
            setVisible(true);  //보이도록
            setResizable(false); //화면 고정
            
            //추가 버튼 액션 리스너
            plusbtn.addActionListener(new ActionListener() {	
                public void actionPerformed(ActionEvent e) {
                	int sum;								//총 칼로리 값 계산 위한 sum변수
                 	if(kcal[index].equals("0")) {			//사용자 선택을 눌렀을 때(글씨 깨짐을 방지하기 위해 칼로리로 비교함)
                 		JOptionPane add = new JOptionPane();//추가하기 위한 팝업창
                 		String message1 = add.showInputDialog("섭취한 음식의 이름을 입력하시오.");
                 		String message2 = add.showInputDialog("섭취한 음식의 칼로리를 입력하시오.");
                 		eatfood.setText(eatfood.getText() + "\n" + message1);	//입력한 값 eatfood에 입력
                 		for(int i = 0; i<30-message1.length()-message2.length(); i++)
                 			eatfood.setText(eatfood.getText() + " ");			//길이 맞추기 위한 띄어쓰기
                 		eatfood.setText(eatfood.getText() + message2 + "kcal");	//칼로리 값 eatfood에 입력
                 		sum = Integer.parseInt(user[8]) + Integer.parseInt(message2);//sum에 값 더하기
                 	}
                 	else {									//사용자 선택이 아닌 음식을 선택했을 때
                 		eatfood.setText(eatfood.getText() + "\n" + foodsave[index]);//음식 값 eatfood에 입력
//                 		user[8] += Integer.toString(Integer.parseInt(kcal[index]));	//총 섭취량에 칼로리 더하기
                 		sum = Integer.parseInt(user[8]) + Integer.parseInt(kcal[index]);//sum에 값 더하기
                 	}
                 	user[8] = Integer.toString(sum);							//sum user에 저장하기
                 	eatinglabel.setText("총 섭취량:  " + user[8] + "kcal");		//총 섭취량 라벨 값 수정
                 	user[7] = eatfood.getText();								//user에 식사 기록 정보 저장하기
                 }
              });
            
            //선택한 값 알아내기 위한 액션 리스너 생성
            foodCombo.addActionListener(new ActionListener() {	
    			public void actionPerformed(ActionEvent e) {
    				JComboBox<String> cb = (JComboBox<String>)e.getSource();
    				index = cb.getSelectedIndex();				//선택한 값 번호 받는 인덱스
    			}
    		});
            homebtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new Home(user, n);
                    setVisible(false);
                 }	
              });
      }
      
      //음식 목록 파일 불러내는 함수
      public static void Rfile() {
 
            try {
            	 FileInputStream fileInputStream = new FileInputStream("C:\\Temp\\자바 팀프로젝트 (김예은, 안노아, 임지윤)팀\\foodfile.txt");
            	  InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            	  BufferedReader br = new BufferedReader(inputStreamReader);
                String line = "";
                while((line = br.readLine()) != null) {
                    foodList.add(line);	//음식 정보 리스트에 값 추가
                }
                    br.close();    
                } catch (Exception e) { }
        }
      
      //리스트의 내용들 음식,칼로리,음식정보 배열에 저장하기 위한 함수
      public static void fSave() {
    	  int foodLen = foodList.size();	//리스트 길이
    	  food = new String[foodLen];		//배열 생성
    	  kcal = new String[foodLen];		//배열 생성
    	  foodsave = new String[foodLen];	//배열 생성
    	  String[] tmp = new String[2];		//토큰 값 저장할 변수 배열		
    	  int tmpIndex;						//순서를 알기 위한 변수인덱스
    	  for(int i = 0; i<foodLen; i++) {	//리스트 횟수만큼 반복
    		  tmpIndex = 0;					//초기화
    		  StringTokenizer st = new StringTokenizer(foodList.get(i), "\t");//토큰 생성
    		  while(st.hasMoreTokens()) 	//토큰이 끝날때까지 반복
    			  tmp[tmpIndex++] = st.nextToken();//변수에 토큰 값 저장
    		  food[i] = tmp[0];				//음식 배열에 값 저장
    		  kcal[i] = tmp[1];				//칼로리 배열에 값 저장
    		  foodsave[i] = food[i];		//음식 정보 배열에 값 저장
    		  for(int j = 0; j<30-food[i].length()-kcal[i].length(); j++)
    			  foodsave[i] += " ";		//길이 맞추기 위한 띄어쓰기
    		  foodsave[i] += kcal[i] + "kcal";
    	  }
      }

}