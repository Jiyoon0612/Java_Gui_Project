import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Stratching extends JFrame {
    JLabel lTimerN = new JLabel();	//타이머 숫자 라벨
    JLabel lTimerB = new JLabel();	//타이머 막대 라벨
    Timer timerRN = new Timer();	//타이머 준비초 타이머
    Timer timerRB = new Timer();	//타이머 준비초 타이머
    Timer timerN = new Timer("10");		//타이머 숫자 타이머
    Timer timerB = new Timer();		//타이머 막대 타이머
    int imgSize = 4;				//이미지 크기 변수
    ImageIcon[] eIcon = new ImageIcon[imgSize];	//아이콘 배열
    JLabel[] exercise = new JLabel[imgSize];	//이미지 라벨 배열
    JButton bReady = new JButton("READY?");		//준비완료 버튼
    JLabel RorG = new JLabel();					//ready or go 라는 뜻을 가진 라벨
    int secN;	int readyN;			//숫자 타이머, 숫자 준비 타이머
    int sec;						//고정 시간
    int stageN, stageB = 0;			//단계 변수
    int Width, tWidth = 590;		//막대 타이머 길이 (변하는 값/고정)

    public Stratching(String[] user, int n) {
        super("스트레칭");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        Container c = getContentPane();
        c.setLayout(null);
        Color color = new Color(255, 204, 204);
        c.setBackground(color);

        //홈 버튼
        ImageIcon HomeIcon = new ImageIcon("C:\\Temp\\자바 팀프로젝트 (김예은, 안노아, 임지윤)팀\\home.png"); //홈 이미지 생성
        JButton Home = new JButton(HomeIcon);           //홈 버튼 생성

        //타이머 초
        lTimerN.setFont(lTimerN.getFont().deriveFont(50.0f)); //글자 크기
        lTimerN.setHorizontalAlignment(JLabel.CENTER);
        RorG.setFont(new Font("맑은 고딕", Font.BOLD, 20));
        RorG.setHorizontalAlignment(JLabel.CENTER);
        
        //준비완료
        bReady.setBackground(Color.DARK_GRAY);    			//버튼 배경색
        bReady.setForeground(Color.WHITE);         			//글자색
        bReady.setFont(new Font("맑은 고딕", Font.PLAIN, 13));//폰트 설정
        
        //스트레칭 이미지 생성
        for(int i = 0; i<imgSize; i++) {
        	eIcon[i] = new ImageIcon("C:\\Temp\\자바 팀프로젝트 (김예은, 안노아, 임지윤)팀\\s" + (i+1) + ".jpg");
        	exercise[i] = new JLabel(eIcon[i]);
        }

        //타이머 바
        lTimerB.setBackground(Color.DARK_GRAY);     //타이머 색 설정
        lTimerB.setHorizontalAlignment(JLabel.LEFT);//가운데 정렬
        lTimerB.setOpaque(true);                    //배경색 보이도록
        lTimerB.setPreferredSize(new Dimension(600, 40)); //크기 조절

        //크기 및 위치 지정
        Home.setBounds(0, 0, 35, 35);
        bReady.setBounds(270, 140, 100, 40);
        RorG.setBounds(40, 100, 100, 40);
        for(int i = 0; i<imgSize; i++)
        	exercise[i].setBounds(180, 15, 300, 296);
        lTimerN.setBounds(40, 250, 100, 50);
        lTimerB.setBounds(20, 320, 590, 50);

        c.add(Home);
        c.add(bReady);
        c.add(RorG);
        RorG.setVisible(false);
        for(int i = 0; i<imgSize; i++) {
        	c.add(exercise[i]);
        	exercise[i].setVisible(false);	}
        c.add(lTimerN);
        c.add(lTimerB);

        setSize(640, 400);                  //화면 크기
        setVisible(true);                   //보이도록
        setResizable(false);				//화면 고정
        
        //홈버튼
        Home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Home(user, n);
                setVisible(false);
             }	
          });
        
        //준비완료
        bReady.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bReady.setVisible(false);
                RorG.setVisible(true);
                readyN();	readyB();
          }	});
    }
    public void readyN() {
    	readyN = 3;					//준비 초 초기화
    	timerRN = new Timer();		//준비 타이머 생성
    	exercise[stageN].setVisible(true);//이미지 순서에 맞게 보이도록
    	RorG.setText("~준비~");
        timerRN.scheduleAtFixedRate(new TimerTask() {     
        	public  void run() {    // 실행
        		if(readyN > 0) {	//3,2,1초
        			lTimerN.setText(readyN + "");//프레임에 초가 보이도록
        			readyN -= 1;	//시간 감소
                	}
        		else {					//타이머가 끝나면
        			timerRN.cancel();	//준비 타이머 종료
                    startN();			//시작 초 함수 호출
            }   }
        }, 0, 1000);
    }
    public void startN() {
    	secN = 10;					//시작 초 초기화
    	timerN = new Timer();		//시작 타이머 생성
    	int nowStage = stageN;		//현재 단계
    	RorG.setText("!시작!");
    	timerN.scheduleAtFixedRate(new TimerTask() {     
    		public  void run() {				//실행
    			if(secN > 0) {					//1~10초
    				lTimerN.setText(secN+"");	//프레임에 초가 보이도록
    				secN -= 1;					//시간 감소
    			}
    			else {						//타이머가 끝나면
    				timerN.cancel();		//시작 타이머 종료
    				stageN++;				//다음 단계로
    				if(stageN < imgSize) {	//단계가 끝나기 전에는
    					readyN(); readyB();	//다시 준비단계 호출
    				}
    				else {					//단계가 끝나면
    					lTimerN.setVisible(false);
    					RorG.setVisible(false);
    					bReady.setVisible(true);//준비버튼이 다시 보이도록
    				}
    				exercise[nowStage].setVisible(false);//이미지 숨기기
    				lTimerB.setBounds(20, 320, 590, 50);//막대 크기 초기화
    		}	}
    	}, 0, 1000);
    }
    public void readyB() {
    	sec = 3;					//준비 초 초기화
    	Width = 590; tWidth = 590;	//길이 초기화
    	timerRB = new Timer();		//준비 타이머 생성
    	
        timerRB.scheduleAtFixedRate(new TimerTask() {     
        	public  void run() {    		//실행
        		if(Width > 0) {			//3,2,1초
        			lTimerB.setBounds(20, 320, Width, 50);//막대 크기 조정하기
        			Width -= 1;				//높이 줄이기
                }
        		else {					//타이머가 끝나면
        			timerRB.cancel();	//타이머 종료
                    startB();			//시작 막대 함수 호출
            }   }
        }, 0, 1000 / (tWidth / sec));	//막대가 부드럽게 줄어들기 위해 시간을 나눔
    }
    public void startB() {
    	sec = 10;					//시작 초 초기화
    	Width = 590; tWidth = 590;	//길이 초기화
    	timerB = new Timer();		//시작 타이머 생성
    	
    	timerB.scheduleAtFixedRate(new TimerTask() {     
    		public  void run() {			//실행
    			if(Width > 0) {				//1~10초
        			lTimerB.setBounds(20, 320, Width, 50);//막대 크기 조정하기
        			Width -= 1;				//높이 줄이기
                }
    			else {					//타이머가 끝나면
    				timerB.cancel();	//타이머 종료
    				stageB++;			//다음 단계로
    		}	}
    	}, 0, 1000 / (tWidth / sec));
    }
}
