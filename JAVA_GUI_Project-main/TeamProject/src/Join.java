import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;

public class Join extends JFrame implements noEmpty {
	JRadioButton rb1 = new JRadioButton("����");
	JRadioButton rb2 = new JRadioButton("����");
	JButton btn1 = new JButton("�����ϱ�");
	JButton btn2 = new JButton("���̵� �ߺ�Ȯ��");
	int pressBtn2 = 0;
	
	//�ؽ�Ʈ�ʵ�
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
	     setTitle("ȸ�� ����");
	     setDefaultCloseOperation(EXIT_ON_CLOSE); //���������� �޸𸮻󿡼��� ������� �ϱ�
		 setLocationRelativeTo(null); 
		 Container c = getContentPane();
		 Color color = new Color(255, 204, 204);
		 c.setBackground(color);
         c.setLayout(null);
		 Font font = new Font("���� ���", Font.BOLD, 12);
		 fileNum = n;
	     
		 //��s
	     JLabel la1 = new JLabel("���̵�");
	     JLabel la2 = new JLabel("��й�ȣ");
	     JLabel la3 = new JLabel("�̸�");
	     JLabel la4 = new JLabel("����");
	     JLabel la5 = new JLabel("Ű");
	     JLabel la6 = new JLabel("���� ü��");
	     JLabel la7 = new JLabel("��ǥ ü��");
		 la1.setFont(font);
		 la2.setFont(font);
		 la3.setFont(font);
		 la4.setFont(font);
		 la5.setFont(font);
		 la6.setFont(font);
		 la7.setFont(font);
	     
		 //���� ��ư
		 rb1.setBackground(color);
		 rb2.setBackground(color);
		 rb1.setFont(font);
		 rb2.setFont(font);
	     ButtonGroup gb = new ButtonGroup();
	     gb.add(rb1);
    	 gb.add(rb2);
    	 rb1.addItemListener(new rbAction());
    	 rb2.addItemListener(new rbAction());
    	 
		 //��ư
		 btn1.setBackground(Color.DARK_GRAY);
		 btn1.setForeground(Color.WHITE);
		 btn1.addActionListener(new goLogin());
		 btn2.setBackground(Color.DARK_GRAY);
		 btn2.setForeground(Color.WHITE);
		 

		 //ũ�� �� ��ġ ����
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
		 
		//�ߺ�Ȯ�� ��ư
		  btn2.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	if(tf1.getText().isEmpty())
	            		JOptionPane.showMessageDialog(null, "���̵� �Է����ֽʽÿ�.");
	            	else {
	            		pressBtn2 = 1;
	            		int flag = 1;
	            		String[] user = new String[11];
	            		String[] splitedStr = null;
	            		for(int i = 0; i<=fileNum; i++) {	//���Ϻ��� ��� Ȯ���ϱ� ���� �ݺ���
	            			File file = new File("C:\\Temp\\�ڹ� ��������Ʈ (�迹��, �ȳ��, ������)��\\user" + i + ".txt");
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
	            			JOptionPane.showMessageDialog(null, "�ߺ��� ���̵��Դϴ�.", "ERROR!", JOptionPane.ERROR_MESSAGE);
	            			pressBtn2 = 2;	}
	            		else
	            			JOptionPane.showMessageDialog(null, "��밡���� ���̵��Դϴ�.");
	            	}
	             }	
	          });
	  }		  
	  
	  //���Ͽ� �����ϴ� �Լ�
	  public void userFile() {
		  try {
			 BufferedWriter out = new BufferedWriter(new FileWriter(("C:\\Temp\\�ڹ� ��������Ʈ (�迹��, �ȳ��, ������)��\\user" + fileNum + ".txt")));
			 String text1 = tf1.getText(); //���̵�
			 String text2 = tf2.getText(); //��й�ȣ
			 String text3 = tf3.getText(); //�̸�
			 String text4 = r;  		   //���� 
			 String text5 = tf4.getText(); //Ű
			 String text6 = tf5.getText(); //ü��
			 String text7 = tf6.getText(); //��ǥü��
			
			 //���� �Է�
			 out.write(text1 + "\t");	//���̵�
			 out.write(text2 + "\t");	//��й�ȣ
			 out.write(text3 + "\t");	//�̸�
			 out.write(text4 + "\t");	//����
			 out.write(text5 + "\t");	//Ű
			 out.write(text6 + "\t");	//ü��
			 out.write(text7 + "\t");	//��ǥü��
			 out.write("" + "\t");		//������ ���� ���
			 out.write("0" + "\t");		//������ Į�θ� ���
			 out.write("0" + "\t");		//�� ���
			 out.write(fileNum + "\t");	//���� ��ȣ ���
			 
			 out.newLine();
			 
			 out.close();//��Ʈ�� �ݱ�
		  }catch(IOException e1) {
			  e1.printStackTrace();
		  }
	  }
	 
	//�Է�Ȯ�� Ŭ����
	public boolean check() {
		  if(IdPw(tf1.getText().length(), tf2.getText().length())) {
			  if(!tf3.getText().isEmpty() && rbFlag == 1 && !tf4.getText().isEmpty() && !tf5.getText().isEmpty() && !tf6.getText().isEmpty())
				  return true;
			  else return false;
		  }
		  else
			  return false;
	 }
	
	  class rbAction implements ItemListener{	//���� ������
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange() == ItemEvent.DESELECTED)
					rbFlag = 0;					//������ ���� �ʾ����� �˸��� �÷���
				if(rb1.isSelected()) {			//���ڸ� �������� ��
					rbFlag = 1; rbIndex = 0; }	//������ ������� �÷��׿� ���� �� ���� �� ������ ����
				else if(rb2.isSelected()) {		//���ڸ� �������� ��
					rbFlag = 1; rbIndex = 1; }	//������ ������� �÷��׿� ���� �� ���� �� ������ ����
			}
		}

	  private class goLogin implements ActionListener { //�α��� ��ư �������� action
		public void actionPerformed(ActionEvent e){// ���� ��ư �� ��������
			if(rb1.isSelected()) { // rb1�� ���õ�������
				r = rb1.getText(); //rb1�� text�� r�� �����´�
			}
			if(rb2.isSelected()) {// rb2�� ���õ�������
				r = rb2.getText();//rb2�� text�� r�������´�
			}
			if(check()) {
				if(pressBtn2 == 1) {
				  	userFile();
				  	fileNum+=1;
				  	JOptionPane.showMessageDialog(null, "ȸ�������� �����մϴ�.");
				  	new Login(fileNum);
				  	setVisible(false);
				}
				else if(pressBtn2 == 0)
					JOptionPane.showMessageDialog(null, "���̵� �ߺ�Ȯ���� ���ֽʽÿ�.", "ERROR!", JOptionPane.ERROR_MESSAGE);
				else if(pressBtn2 == 2) {
					JOptionPane.showMessageDialog(null, "�ߺ�Ȯ���� �ٽ� ���ֽʽÿ�.", "ERROR!", JOptionPane.ERROR_MESSAGE);
					pressBtn2 = 0;
				}
			}
			else
				JOptionPane.showMessageDialog(null, "���� �Է����ּ���.", "ERROR!", JOptionPane.ERROR_MESSAGE);
		} 
	  }
}