import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Record extends JFrame{
      static ArrayList<String> foodList;
      static String[] food;				//���� ���� �迭
      static String[] kcal;				//Į�θ� ���� �迭
      static String[] foodsave;			//�޺��ڽ��� ���� ����Į�θ� ���� ���� �迭(���� ����)
      JComboBox<String> foodCombo;		//���� �޺��ڽ�
      int index;						//������ �޺��ڽ��� �ε���
      String[] user1;

      public Record(String[] user, int n){
            setTitle("�� �� �� ��");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setLocationRelativeTo(null); 
            Container c = getContentPane();
            c.setLayout(null);
            Color color = new Color(255, 204, 204);
            c.setBackground(color);
            user1 = user;

            //Ȩ��ư
            ImageIcon img1 = new ImageIcon("C:\\Temp\\�ڹ� ��������Ʈ (�迹��, �ȳ��, ������)��\\home.png");//Ȩ �̹���
            JButton homebtn = new JButton(img1);      //��ư ����
            
            //�޺� �ڽ�
            foodList = new ArrayList<String>();
            Rfile();
            fSave();
            foodCombo = new JComboBox<String>(foodsave);
            foodCombo.setBackground(Color.WHITE);	//���� ����
            foodCombo.setFont(new Font("���� ���", Font.PLAIN, 13));//��Ʈ ����

            //�߰� ��ư
            ImageIcon img2 = new ImageIcon("C:\\Temp\\�ڹ� ��������Ʈ (�迹��, �ȳ��, ������)��\\plus.png");//�߰� �̹���
            JButton plusbtn = new JButton(img2);      //��ư ����
            
            //������ ����
            JLabel eatlabel = new JLabel ("������ ����");     //������ ���� ��
            TextArea eatfood = new TextArea(user[7]);//������ ���� ���
            eatlabel.setFont(new Font("���� ���", Font.BOLD, 13)); //��Ʈ ����
            eatfood.setFont(new Font("���� ���", Font.PLAIN, 13));            

            //�� ���뷮
            JLabel eatinglabel = new JLabel ("�� ���뷮:  " + user[8] + "kcal",JLabel.RIGHT);//�� ���뷮
            eatinglabel.setFont(new Font("���� ���", Font.BOLD, 16));    //��Ʈ ����
        
            //ũ�� �� ��ġ ����
            homebtn.setBounds(0, 0, 35, 35);
            foodCombo.setBounds(120, 60, 350, 30);
            plusbtn.setBounds(490, 60, 30, 30);
            eatlabel.setBounds(90, 100, 100, 20);
            eatfood.setBounds(95, 125, 450, 200);
            eatinglabel.setBounds(330, 330, 200, 30);

            //�߰�
            c.add(homebtn);
            c.add(foodCombo);    c.add(plusbtn);
            c.add(eatlabel);     c.add(eatfood);
            c.add(eatinglabel);

            setSize(640, 400);  //ȭ�� ũ��
            setVisible(true);  //���̵���
            setResizable(false); //ȭ�� ����
            
            //�߰� ��ư �׼� ������
            plusbtn.addActionListener(new ActionListener() {	
                public void actionPerformed(ActionEvent e) {
                	int sum;								//�� Į�θ� �� ��� ���� sum����
                 	if(kcal[index].equals("0")) {			//����� ������ ������ ��(�۾� ������ �����ϱ� ���� Į�θ��� ����)
                 		JOptionPane add = new JOptionPane();//�߰��ϱ� ���� �˾�â
                 		String message1 = add.showInputDialog("������ ������ �̸��� �Է��Ͻÿ�.");
                 		String message2 = add.showInputDialog("������ ������ Į�θ��� �Է��Ͻÿ�.");
                 		eatfood.setText(eatfood.getText() + "\n" + message1);	//�Է��� �� eatfood�� �Է�
                 		for(int i = 0; i<30-message1.length()-message2.length(); i++)
                 			eatfood.setText(eatfood.getText() + " ");			//���� ���߱� ���� ����
                 		eatfood.setText(eatfood.getText() + message2 + "kcal");	//Į�θ� �� eatfood�� �Է�
                 		sum = Integer.parseInt(user[8]) + Integer.parseInt(message2);//sum�� �� ���ϱ�
                 	}
                 	else {									//����� ������ �ƴ� ������ �������� ��
                 		eatfood.setText(eatfood.getText() + "\n" + foodsave[index]);//���� �� eatfood�� �Է�
//                 		user[8] += Integer.toString(Integer.parseInt(kcal[index]));	//�� ���뷮�� Į�θ� ���ϱ�
                 		sum = Integer.parseInt(user[8]) + Integer.parseInt(kcal[index]);//sum�� �� ���ϱ�
                 	}
                 	user[8] = Integer.toString(sum);							//sum user�� �����ϱ�
                 	eatinglabel.setText("�� ���뷮:  " + user[8] + "kcal");		//�� ���뷮 �� �� ����
                 	user[7] = eatfood.getText();								//user�� �Ļ� ��� ���� �����ϱ�
                 }
              });
            
            //������ �� �˾Ƴ��� ���� �׼� ������ ����
            foodCombo.addActionListener(new ActionListener() {	
    			public void actionPerformed(ActionEvent e) {
    				JComboBox<String> cb = (JComboBox<String>)e.getSource();
    				index = cb.getSelectedIndex();				//������ �� ��ȣ �޴� �ε���
    			}
    		});
            homebtn.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    new Home(user, n);
                    setVisible(false);
                 }	
              });
      }
      
      //���� ��� ���� �ҷ����� �Լ�
      public static void Rfile() {
 
            try {
            	 FileInputStream fileInputStream = new FileInputStream("C:\\Temp\\�ڹ� ��������Ʈ (�迹��, �ȳ��, ������)��\\foodfile.txt");
            	  InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
            	  BufferedReader br = new BufferedReader(inputStreamReader);
                String line = "";
                while((line = br.readLine()) != null) {
                    foodList.add(line);	//���� ���� ����Ʈ�� �� �߰�
                }
                    br.close();    
                } catch (Exception e) { }
        }
      
      //����Ʈ�� ����� ����,Į�θ�,�������� �迭�� �����ϱ� ���� �Լ�
      public static void fSave() {
    	  int foodLen = foodList.size();	//����Ʈ ����
    	  food = new String[foodLen];		//�迭 ����
    	  kcal = new String[foodLen];		//�迭 ����
    	  foodsave = new String[foodLen];	//�迭 ����
    	  String[] tmp = new String[2];		//��ū �� ������ ���� �迭		
    	  int tmpIndex;						//������ �˱� ���� �����ε���
    	  for(int i = 0; i<foodLen; i++) {	//����Ʈ Ƚ����ŭ �ݺ�
    		  tmpIndex = 0;					//�ʱ�ȭ
    		  StringTokenizer st = new StringTokenizer(foodList.get(i), "\t");//��ū ����
    		  while(st.hasMoreTokens()) 	//��ū�� ���������� �ݺ�
    			  tmp[tmpIndex++] = st.nextToken();//������ ��ū �� ����
    		  food[i] = tmp[0];				//���� �迭�� �� ����
    		  kcal[i] = tmp[1];				//Į�θ� �迭�� �� ����
    		  foodsave[i] = food[i];		//���� ���� �迭�� �� ����
    		  for(int j = 0; j<30-food[i].length()-kcal[i].length(); j++)
    			  foodsave[i] += " ";		//���� ���߱� ���� ����
    		  foodsave[i] += kcal[i] + "kcal";
    	  }
      }

}