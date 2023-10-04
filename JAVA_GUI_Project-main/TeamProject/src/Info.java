import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Info extends JFrame {
    int goalWeight;                  		//��ǥü��
    JTextField TnewW = new JTextField(5);   //���� �Է� �ؽ�Ʈ�ʵ�
    JTextField TnewT = new JTextField(5);   //Ű �Է� �ؽ�Ʈ�ʵ�

    public Info(String[] user, int n) {
        super("���� ������Ʈ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        Container c = getContentPane();
        c.setLayout(null);
        Color color = new Color(255, 204, 204);
        c.setBackground(color);       //���� ����
        Font font = new Font("���� ���", Font.BOLD, 12);		//�� ��Ʈ
        Font btnFont = new Font("���� ���", Font.PLAIN, 13); //��ư ��Ʈ
        Font btnFont2 = new Font("���� ���", Font.PLAIN, 11); //��ư ��Ʈ
        
        //Ȩ ��ư
        ImageIcon Homeicon = new ImageIcon("C:\\Temp\\�ڹ� ��������Ʈ (�迹��, �ȳ��, ������)��\\home.png"); //Ȩ �̹��� ����
        JButton Home = new JButton(Homeicon);           //Ȩ ��ư ����

        //�Էµ�
        JLabel LoldW = new JLabel("���� ������:             " + user[5] + " kg");   //���� ����
        JLabel LoldT = new JLabel("       ���� Ű:           " + user[4] + " cm");//���� Ű
        JLabel LnewW = new JLabel("���� ������: ");     //���� ����
        JLabel Lkg = new JLabel("kg");               //����
        JLabel LnewT = new JLabel("       ���� Ű: "); //���� Ű
        JLabel Lcm = new JLabel("cm");               //����
        LoldW.setFont(font);
        LoldT.setFont(font);
        LnewW.setFont(font);
        LnewT.setFont(font);
        TnewW.setFont(font);
        TnewT.setFont(font);
        Lkg.setFont(font);
        Lcm.setFont(font);
        
        //Ű �������� ��ư
        JButton stay = new JButton("���� ����");
        stay.setBackground(Color.DARK_GRAY);     //��ư ����
        stay.setForeground(Color.WHITE);         //���ڻ�
        stay.setFont(btnFont2);

        //�Է� �Ϸ� ��ư
        JButton end = new JButton("�Է� �Ϸ�");	//�Է� �Ϸ� ��ư ����
        end.setBackground(Color.DARK_GRAY);     //��ư ����
        end.setForeground(Color.WHITE);         //���ڻ�
        end.setFont(btnFont);					//��Ʈ ����

        //ũ�� �� ��ġ ����
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

        c.add(Home);                                //Ȩ ��ư
        c.add(LoldW);   c.add(LoldT);               //����,���� ����,Ű
        c.add(LnewW);   c.add(TnewW);   c.add(Lkg); //���� ����
        c.add(LnewT);   c.add(TnewT);   c.add(Lcm); //���� Ű
        c.add(stay);	c.add(end);					//�������� �� �Է¿Ϸ� ��ư

        setSize(640, 400); //ȭ�� ũ��
        setVisible(true);  //���̵���
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
        				JOptionPane.showMessageDialog(null,"�����մϴ�! ��ǥü���� �޼��ϼ̽��ϴ�!");	
        			}
        			setVisible(false);
        			new Home(user, n);
        		}
        		else
        			JOptionPane.showMessageDialog(null, "���� �Է����ּ���.", "ERROR!", JOptionPane.ERROR_MESSAGE);
        	}
        });
        
        Home.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new Home(user, n);
                setVisible(false);
             }	
          });
    }
  //���Ͽ� �����ϴ� �Լ�
	  public void updateFile(String user[]) {
		  try {
			 BufferedWriter out = new BufferedWriter(new FileWriter(("C:\\Temp\\�ڹ� ��������Ʈ (�迹��, �ȳ��, ������)��\\user" + user[10] + ".txt")));
			 user[4] = TnewT.getText(); //tf1�� ���� �޾� text1�� ���� -> user.txt�� ����� ����
			 user[5] = TnewW.getText(); //tf2�� ���� �޾� text2�� ���� -> user.txt�� ����� ����
			
			 //���� �Է�
			 for(int i = 0; i<user.length; i++)
				 out.write(user[i] + "\t");
			 
			 out.close();//��Ʈ�� �ݱ�
		  }catch(IOException e1) {	  }
	  }
}

