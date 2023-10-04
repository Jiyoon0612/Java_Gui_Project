import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Timer;
import java.util.TimerTask;

public class Stratching extends JFrame {
    JLabel lTimerN = new JLabel();	//Ÿ�̸� ���� ��
    JLabel lTimerB = new JLabel();	//Ÿ�̸� ���� ��
    Timer timerRN = new Timer();	//Ÿ�̸� �غ��� Ÿ�̸�
    Timer timerRB = new Timer();	//Ÿ�̸� �غ��� Ÿ�̸�
    Timer timerN = new Timer("10");		//Ÿ�̸� ���� Ÿ�̸�
    Timer timerB = new Timer();		//Ÿ�̸� ���� Ÿ�̸�
    int imgSize = 4;				//�̹��� ũ�� ����
    ImageIcon[] eIcon = new ImageIcon[imgSize];	//������ �迭
    JLabel[] exercise = new JLabel[imgSize];	//�̹��� �� �迭
    JButton bReady = new JButton("READY?");		//�غ�Ϸ� ��ư
    JLabel RorG = new JLabel();					//ready or go ��� ���� ���� ��
    int secN;	int readyN;			//���� Ÿ�̸�, ���� �غ� Ÿ�̸�
    int sec;						//���� �ð�
    int stageN, stageB = 0;			//�ܰ� ����
    int Width, tWidth = 590;		//���� Ÿ�̸� ���� (���ϴ� ��/����)

    public Stratching(String[] user, int n) {
        super("��Ʈ��Ī");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        Container c = getContentPane();
        c.setLayout(null);
        Color color = new Color(255, 204, 204);
        c.setBackground(color);

        //Ȩ ��ư
        ImageIcon HomeIcon = new ImageIcon("C:\\Temp\\�ڹ� ��������Ʈ (�迹��, �ȳ��, ������)��\\home.png"); //Ȩ �̹��� ����
        JButton Home = new JButton(HomeIcon);           //Ȩ ��ư ����

        //Ÿ�̸� ��
        lTimerN.setFont(lTimerN.getFont().deriveFont(50.0f)); //���� ũ��
        lTimerN.setHorizontalAlignment(JLabel.CENTER);
        RorG.setFont(new Font("���� ���", Font.BOLD, 20));
        RorG.setHorizontalAlignment(JLabel.CENTER);
        
        //�غ�Ϸ�
        bReady.setBackground(Color.DARK_GRAY);    			//��ư ����
        bReady.setForeground(Color.WHITE);         			//���ڻ�
        bReady.setFont(new Font("���� ���", Font.PLAIN, 13));//��Ʈ ����
        
        //��Ʈ��Ī �̹��� ����
        for(int i = 0; i<imgSize; i++) {
        	eIcon[i] = new ImageIcon("C:\\Temp\\�ڹ� ��������Ʈ (�迹��, �ȳ��, ������)��\\s" + (i+1) + ".jpg");
        	exercise[i] = new JLabel(eIcon[i]);
        }

        //Ÿ�̸� ��
        lTimerB.setBackground(Color.DARK_GRAY);     //Ÿ�̸� �� ����
        lTimerB.setHorizontalAlignment(JLabel.LEFT);//��� ����
        lTimerB.setOpaque(true);                    //���� ���̵���
        lTimerB.setPreferredSize(new Dimension(600, 40)); //ũ�� ����

        //ũ�� �� ��ġ ����
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

        setSize(640, 400);                  //ȭ�� ũ��
        setVisible(true);                   //���̵���
        setResizable(false);				//ȭ�� ����
        
        //Ȩ��ư
        Home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Home(user, n);
                setVisible(false);
             }	
          });
        
        //�غ�Ϸ�
        bReady.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                bReady.setVisible(false);
                RorG.setVisible(true);
                readyN();	readyB();
          }	});
    }
    public void readyN() {
    	readyN = 3;					//�غ� �� �ʱ�ȭ
    	timerRN = new Timer();		//�غ� Ÿ�̸� ����
    	exercise[stageN].setVisible(true);//�̹��� ������ �°� ���̵���
    	RorG.setText("~�غ�~");
        timerRN.scheduleAtFixedRate(new TimerTask() {     
        	public  void run() {    // ����
        		if(readyN > 0) {	//3,2,1��
        			lTimerN.setText(readyN + "");//�����ӿ� �ʰ� ���̵���
        			readyN -= 1;	//�ð� ����
                	}
        		else {					//Ÿ�̸Ӱ� ������
        			timerRN.cancel();	//�غ� Ÿ�̸� ����
                    startN();			//���� �� �Լ� ȣ��
            }   }
        }, 0, 1000);
    }
    public void startN() {
    	secN = 10;					//���� �� �ʱ�ȭ
    	timerN = new Timer();		//���� Ÿ�̸� ����
    	int nowStage = stageN;		//���� �ܰ�
    	RorG.setText("!����!");
    	timerN.scheduleAtFixedRate(new TimerTask() {     
    		public  void run() {				//����
    			if(secN > 0) {					//1~10��
    				lTimerN.setText(secN+"");	//�����ӿ� �ʰ� ���̵���
    				secN -= 1;					//�ð� ����
    			}
    			else {						//Ÿ�̸Ӱ� ������
    				timerN.cancel();		//���� Ÿ�̸� ����
    				stageN++;				//���� �ܰ��
    				if(stageN < imgSize) {	//�ܰ谡 ������ ������
    					readyN(); readyB();	//�ٽ� �غ�ܰ� ȣ��
    				}
    				else {					//�ܰ谡 ������
    					lTimerN.setVisible(false);
    					RorG.setVisible(false);
    					bReady.setVisible(true);//�غ��ư�� �ٽ� ���̵���
    				}
    				exercise[nowStage].setVisible(false);//�̹��� �����
    				lTimerB.setBounds(20, 320, 590, 50);//���� ũ�� �ʱ�ȭ
    		}	}
    	}, 0, 1000);
    }
    public void readyB() {
    	sec = 3;					//�غ� �� �ʱ�ȭ
    	Width = 590; tWidth = 590;	//���� �ʱ�ȭ
    	timerRB = new Timer();		//�غ� Ÿ�̸� ����
    	
        timerRB.scheduleAtFixedRate(new TimerTask() {     
        	public  void run() {    		//����
        		if(Width > 0) {			//3,2,1��
        			lTimerB.setBounds(20, 320, Width, 50);//���� ũ�� �����ϱ�
        			Width -= 1;				//���� ���̱�
                }
        		else {					//Ÿ�̸Ӱ� ������
        			timerRB.cancel();	//Ÿ�̸� ����
                    startB();			//���� ���� �Լ� ȣ��
            }   }
        }, 0, 1000 / (tWidth / sec));	//���밡 �ε巴�� �پ��� ���� �ð��� ����
    }
    public void startB() {
    	sec = 10;					//���� �� �ʱ�ȭ
    	Width = 590; tWidth = 590;	//���� �ʱ�ȭ
    	timerB = new Timer();		//���� Ÿ�̸� ����
    	
    	timerB.scheduleAtFixedRate(new TimerTask() {     
    		public  void run() {			//����
    			if(Width > 0) {				//1~10��
        			lTimerB.setBounds(20, 320, Width, 50);//���� ũ�� �����ϱ�
        			Width -= 1;				//���� ���̱�
                }
    			else {					//Ÿ�̸Ӱ� ������
    				timerB.cancel();	//Ÿ�̸� ����
    				stageB++;			//���� �ܰ��
    		}	}
    	}, 0, 1000 / (tWidth / sec));
    }
}
