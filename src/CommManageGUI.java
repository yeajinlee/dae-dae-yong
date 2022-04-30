import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.JOptionPane;


public class CommManageGUI {

	public static void main(String[] args) {
		NotAInfoMemGUI nimg = new NotAInfoMemGUI();
//		InfoMemGUI img = new InfoMemGUI();
//		InfoPostGUI ipg = new InfoPostGUI();
//		DetailMemGUI dmg = new DetailMemGUI();
//		DetailPostGUI dpg = new DetailPostGUI();	
	}

}
//ȸ�� ��� ������
class InfoMemGUI {
	Frame frame;
	Panel[] panel;
	Label lblMemInfo, lblId, lblName, lblLevel, lblEmail, lblJoinDate, lblPost, lblMemComment, 
	lblLogDate, lblPostNum, lblPostInfo, lblTitle, lblPostDate, lblView, lblPostComment;
	Button btnPostList, btnMemRegister, btnAdminLogin, btnSearch, btnEdit, btnDelete, btnMemList;
	TextField tfSearch, tfName,tfId;
	Choice cFilter;
	List list;
	Font font1 = new Font("Default", Font.BOLD, 17);
	Font font2 = new Font("Default", Font.PLAIN, 15);
	
	String filter = null;
	String keyword;
	
	public InfoMemGUI() {
		frame = new Frame("Ŀ�´�Ƽ ����");
		panel = new Panel[9];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new Panel();
		}
		
		lblMemInfo = new Label("��ü ȸ�� ����");
		lblMemInfo.setFont(font1);
		lblId = new Label("ID");
		lblId.setFont(font2);
		lblName = new Label("�̸�");
		lblName.setFont(font2);
		lblLevel = new Label("ȸ�����");
		lblLevel.setFont(font2);
		lblEmail = new Label("�����ּ�");
		lblEmail.setFont(font2);
		lblJoinDate = new Label("������");
		lblJoinDate.setFont(font2);
		lblPost = new Label("�Խñ� ��");
		lblPost.setFont(font2);
		lblMemComment = new Label("��� ��"); //ȸ������ ��� ��
		lblMemComment.setFont(font2);
		lblLogDate = new Label("���� �α��� ��¥");
		lblLogDate.setFont(font2);
		lblPostInfo = new Label("��ü �Խñ� ����");
		lblPostInfo.setFont(font1);
		lblPostNum = new Label("�۹�ȣ");
		lblPostNum.setFont(font2);
		lblTitle = new Label("������");
		lblTitle.setFont(font2);
		lblPostDate = new Label("�ۼ���");
		lblPostDate.setFont(font2);
		lblView = new Label("��ȸ��");
		lblView.setFont(font2);
		lblPostComment = new Label("��� ��"); //������ ��� �� 
		lblPostComment.setFont(font2);
		
		tfSearch = new TextField("");
		tfName = new TextField("");
		tfId = new TextField("");
		tfSearch.setPreferredSize(new Dimension(500,25));
		tfSearch.setFont(font2);
		
		btnPostList = new Button("�۸��");
		btnPostList.setPreferredSize(new Dimension(200,50));
		btnPostList.setFont(font1);
		btnMemList = new Button("ȸ�� ���");
		btnMemList.setPreferredSize(new Dimension(200,50));
		btnMemList.setFont(font1);
		btnMemRegister = new Button("ȸ�� ���");
		btnMemRegister.setPreferredSize(new Dimension(200,50));
		btnMemRegister.setFont(font1);
		btnAdminLogin = new Button("������ �α׾ƿ�");
		btnAdminLogin.setPreferredSize(new Dimension(200,50));
		btnAdminLogin.setFont(font1);
		btnSearch = new Button("�˻�");
		btnSearch.setPreferredSize(new Dimension(100, 30));
		btnEdit = new Button("����");
		btnEdit.setPreferredSize(new Dimension(80,30));
		btnDelete = new Button("����");
		btnDelete.setPreferredSize(new Dimension(80,30));
		
		cFilter = new Choice();
		cFilter.setPreferredSize(new Dimension(150,50));
		cFilter.add("����");
		cFilter.addItem("ID");
		cFilter.addItem("���");
		cFilter.addItem("�̸�");
		cFilter.addItem("�����ּ�");
		
		list = new List(12, false);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);;
			}
		});
	
		panel[0].add(new Label(""));
		panel[0].add(btnMemList);
		panel[0].add(new Label(""));
		panel[0].add(btnMemRegister);
		panel[0].add(new Label(""));
		panel[0].add(btnPostList);
		panel[0].add(new Label(""));
		panel[0].add(btnAdminLogin);
		panel[0].add(new Label(""));
		
		panel[1].add(lblMemInfo);
		
		panel[2].add(cFilter);
		panel[2].add(tfSearch);
		panel[2].add(btnSearch);
		
		panel[3].setLayout(new BorderLayout());
		panel[3].add(panel[0], "North");
		panel[3].add(panel[1], "Center");
		panel[3].add(panel[2], "South");

		panel[4].setLayout(new GridLayout(1, 8));
		panel[4].add(lblName);
		panel[4].add(lblId);
		panel[4].add(lblLevel);
		panel[4].add(lblEmail);
		panel[4].add(lblJoinDate);
		panel[4].add(lblPost);
		panel[4].add(lblMemComment);
		panel[4].add(lblLogDate);
		panel[4].setBackground(Color.LIGHT_GRAY);
		
		panel[5].setLayout(new BorderLayout());
		panel[5].add(list, "Center");
		
		panel[6].setLayout(new BorderLayout());
		panel[6].add(panel[4], "North");
		panel[6].add(panel[5], "Center");
		
		panel[7].add(btnDelete);
		
		panel[8].setLayout(new BorderLayout());
		panel[8].add(panel[3], "North");
		panel[8].add(panel[6], "Center");
		panel[8].add(panel[7], "South");
		
		frame.add(panel[8], "Center");
		frame.pack();
		frame.setResizable(false);
		frame.setLocation(((frame.getToolkit().getScreenSize()).width)/4, ((frame.getToolkit().getScreenSize()).height)/7);		
		frame.setSize(1000,800);
		frame.setVisible(true);
		displayMemInfo();

		list.addActionListener(new ActionListener() { //����Ŭ�� �̺�Ʈ ó��

			@Override
			public void actionPerformed(ActionEvent e) {
				String str = list.getSelectedItem();
				frame.setVisible(false);
				DetailMemGUI dmg = new DetailMemGUI();
				StringTokenizer st = new StringTokenizer(str);
				dmg.tfName.setText(st.nextToken());
				dmg.listId.add(st.nextToken());
				dmg.tfLevel.setText(st.nextToken());
				dmg.tfEmail.setText(st.nextToken());
				dmg.tfJoinDate.setText(st.nextToken());
				dmg.listPost.add(st.nextToken());
				dmg.listMemComment.add(st.nextToken());
				dmg.tfLogDate.setText(st.nextToken());
			}
		});
		
		btnPostList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		
				frame.dispose();
				new InfoPostGUI();
				
			}
		});
		btnMemList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				new InfoMemGUI();
			}
		});
		btnAdminLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "������ ���� �α׾ƿ� �Ǿ����ϴ�.");
				frame.dispose();
				new NotAInfoMemGUI();
			}
			
		});
		
		cFilter.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				filter = (String) e.getItem();
				if (filter.equals("����")) filter = "all"; //���� �����ϰ� �˻��ϸ� ��ü�˻�
				if (filter.equals("ID")) filter = "id";
				if (filter.equals("�̸�")) filter = "name";
				if (filter.equals("���")) filter = "level";
				if (filter.equals("�����ּ�")) filter = "email";
				
				
				
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �˻� ��ư ����
				keyword = tfSearch.getText();
				if (filter == null) {
					if (keyword == null) { 
						displayMemInfo();
					} else {
						filter = "all"; 
						displayMemSearch(filter, keyword);
					}
				} else {
					displayMemSearch(filter, keyword);
				}				
			}
		});
//��ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡٵ�� ����
		btnMemRegister.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				new RegisterGUI();
			}
		});
	
		//����Ʈ�� �ش� ���ڵ带 �����ϸ� tfId�� setText
		list.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// ����Ʈ�� �׸���� ������ ó������ ������ �ڵ带 ����
				String str = list.getSelectedItem();
				StringTokenizer st = new StringTokenizer(str);
				tfName.setText(st.nextToken());
				tfId.setText(st.nextToken());

			}
		});
		//������ ����Ʈ ���� ��ư
		btnDelete.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tfId.getText();
				if(list.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "���õ� ȸ���� �����ϴ�.");
				} else {
					//���� ����
					Dialog deleteDial = new Dialog(frame, "����", true);
					deleteDial.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							deleteDial.setVisible(false);
						}
					});
					deleteDial.setSize(280, 140);
					deleteDial.setLocation(((frame.getToolkit().getScreenSize()).width - deleteDial.getWidth())/2, ((frame.getToolkit().getScreenSize()).height - deleteDial.getHeight())/2);
					deleteDial.setLayout(new FlowLayout());
					Label deletemsg = new Label("ȸ�� " + tfName.getText() + " ��/�� �����ұ��?");
					deletemsg.setFont(font2);
					Button btnOk = new Button("����");
					Button btnCancel = new Button("���");
					deleteDial.add(deletemsg);
					deleteDial.add(btnOk);
					btnOk.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							CommManageDAO dao = new CommManageDAO();
							dao.memdelete(id);
							frame.dispose();
							new InfoMemGUI();
						}
					});
					deleteDial.add(btnCancel);
					btnCancel.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							deleteDial.setVisible(false);
						}
					});
					deleteDial.setVisible(true);
				}



			}
		});
		//��ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡٵ�� ����
		
	}
	// ȸ������ ����
	public void displayMemInfo() {
		list.removeAll();
		CommManageDAO dao = new CommManageDAO();
		ArrayList<CommManageDTO> allData = dao.memInfoselect();
		for (CommManageDTO dto : allData) {
			String name = dto.getName();
			String ID = dto.getID();
			int level = dto.getLevel();
			String email = dto.getEmail();
			String joinDate = dto.getJoinDate();
			int post = dto.getMemPost();
			int comment = dto.getMemComment();
			String logDate = dto.getLogDate();
			list.add(name+ "                 " + ID + "                           " + level + "                "  + email + "           "  + joinDate + "                    " + post + "                         " + comment + "                            " +logDate);
			list.setFont(font2);
		}
	}
	//ȸ������ �˻�
	public void displayMemSearch(String filter, String keyword) {
		list.removeAll();
		CommManageDAO dao = new CommManageDAO();
		ArrayList<CommManageDTO> allData = dao.memInfoSearch(filter, keyword);
		for (CommManageDTO dto : allData) {
			String name = dto.getName();
			String ID = dto.getID();
			int level = dto.getLevel();
			String email = dto.getEmail();
			String joinDate = dto.getJoinDate();
			int post = dto.getMemPost();
			int comment = dto.getMemComment();
			String logDate = dto.getLogDate();
			list.add(name+ "                 " + ID + "                           " + level + "                "  + email + "           "  + joinDate + "                    " + post + "                         " + comment + "                            " +logDate);
			list.setFont(font2);
		}
		
		
		
	}
	
}
//�� ��� ������
class InfoPostGUI {
	Frame frame;
	Panel[] panel;
	Label lblMemInfo, lblId, lblName, lblLevel, lblEmail, lblJoinDate, lblPost, lblComment, 
			lblLogDate, lblPostNum, lblPostInfo, lblTitle, lblPostDate, lblView, lblPostComment;
	Button btnPostList, btnMemRegister, btnAdminLogin, btnSearch, btnEdit, btnDelete, btnMemList;
	TextField tfSearch, tfNum, tfTitle, tfId;
	Choice cFilter;
	List list, list2;
	Font font1 = new Font("Default", Font.BOLD, 17);
	Font font2 = new Font("Default", Font.PLAIN, 15);
	String filter = null;
	String keyword = null;
	
	
	
	public InfoPostGUI() {
		frame = new Frame("Ŀ�´�Ƽ ����");
		panel = new Panel[9];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new Panel();
		}
		
		lblMemInfo = new Label("��ü ȸ�� ����");
		lblMemInfo.setFont(font1);
		lblId = new Label("ID");
		lblId.setFont(font2);
		lblName = new Label("�̸�");
		lblName.setFont(font2);
		lblLevel = new Label("ȸ�����");
		lblLevel.setFont(font2);
		lblEmail = new Label("�����ּ�");
		lblEmail.setFont(font2);
		lblJoinDate = new Label("������");
		lblJoinDate.setFont(font2);
		lblPost = new Label("�Խñ� ��");
		lblPost.setFont(font2);
		lblComment = new Label("���");
		lblComment.setFont(font2);
		lblLogDate = new Label("���� �α��� ��¥");
		lblLogDate.setFont(font2);
		lblPostInfo = new Label("��ü �Խñ� ����");
		lblPostInfo.setFont(font1);
		lblPostNum = new Label("�۹�ȣ");
		lblPostNum.setFont(font2);
		lblTitle = new Label("������");
		lblTitle.setFont(font2);
		lblPostDate = new Label("�ۼ���");
		lblPostDate.setFont(font2);
		lblPostComment = new Label("��� ��");
		lblPostComment.setFont(font2);
		lblView = new Label("��ȸ��");
		lblView.setFont(font2);
		
		tfSearch = new TextField("");
		tfSearch.setPreferredSize(new Dimension(500,25));
		tfSearch.setFont(font2);
		tfNum = new TextField("");
		tfTitle = new TextField("");
		tfId = new TextField("");
		
		btnPostList = new Button("�۸��");
		btnPostList.setPreferredSize(new Dimension(200,50));
		btnPostList.setFont(font1);
		btnMemList = new Button("ȸ�� ���");
		btnMemList.setPreferredSize(new Dimension(200,50));
		btnMemList.setFont(font1);
		btnMemRegister = new Button("ȸ�� ���");
		btnMemRegister.setPreferredSize(new Dimension(200,50));
		btnMemRegister.setFont(font1);
		btnAdminLogin = new Button("������ �α׾ƿ�");
		btnAdminLogin.setPreferredSize(new Dimension(200,50));
		btnAdminLogin.setFont(font1);
		btnSearch = new Button("�˻�");
		btnSearch.setPreferredSize(new Dimension(100, 30));
		btnEdit = new Button("����");
		btnEdit.setPreferredSize(new Dimension(80,30));
		btnDelete = new Button("����");
		btnDelete.setPreferredSize(new Dimension(80,30));
		
		cFilter = new Choice();
		cFilter.setPreferredSize(new Dimension(150,50));
		cFilter.addItem("����");
		cFilter.addItem("�۹�ȣ");
		cFilter.addItem("������");
		cFilter.addItem("�ۼ���ID");
		
		
		list = new List(12, false);
		list2 = new List(12,false);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);;
			}
		});
	
		panel[0].add(new Label(""));
		panel[0].add(btnMemList);
		panel[0].add(new Label(""));
		panel[0].add(btnMemRegister);
		panel[0].add(new Label(""));
		panel[0].add(btnPostList);
		panel[0].add(new Label(""));
		panel[0].add(btnAdminLogin);
		panel[0].add(new Label(""));
		
		panel[1].add(lblPostInfo);
		
		panel[2].add(cFilter);
		panel[2].add(tfSearch);
		panel[2].add(btnSearch);
		
		panel[3].setLayout(new BorderLayout());
		panel[3].add(panel[0], "North");
		panel[3].add(panel[1], "Center");
		panel[3].add(panel[2], "South");

		panel[4].setLayout(new GridLayout(1, 8));
		panel[4].add(lblPostNum);
		panel[4].add(lblTitle);
		panel[4].add(lblId);
		panel[4].add(lblPostDate);
		panel[4].add(lblView);
		panel[4].add(lblPostComment);
		panel[4].setBackground(Color.LIGHT_GRAY);
		
		panel[5].setLayout(new BorderLayout());
		panel[5].add(list, "Center");
		panel[6].setLayout(new BorderLayout());
		panel[6].add(panel[4], "North");
		panel[6].add(panel[5], "Center");
		
		panel[7].add(btnDelete);
		
		panel[8].setLayout(new BorderLayout());
		panel[8].add(panel[3], "North");
		panel[8].add(panel[6], "Center");
		panel[8].add(panel[7], "South");
		
		frame.add(panel[8], "Center");
		frame.pack();
		frame.setResizable(false);
		frame.setLocation(((frame.getToolkit().getScreenSize()).width)/4, ((frame.getToolkit().getScreenSize()).height)/7);
		frame.setSize(1000,800);
		frame.setVisible(true);
		displayPostInfo();
		
		
		btnPostList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �۸�Ϲ�ư�� ������
				frame.dispose();
				new InfoPostGUI();
				
			}
		});
		btnMemList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ȸ����Ϲ�ư�� ������

				frame.dispose();
				new InfoMemGUI();
			}
		});
		
		list.addActionListener(new ActionListener() { //����Ŭ�� �̺�Ʈ ó��

			@Override
			public void actionPerformed(ActionEvent e) {
				String str = list.getSelectedItem();
				frame.setVisible(false);
				
				DetailPostGUI dpg = new DetailPostGUI();
				String[] st = str.split("               ");
				dpg.listPostNum.add(st[0]);
				dpg.listTitle.add(st[1].trim());
				dpg.listId.add(st[3].trim());
				dpg.listPostDate.add(st[5].trim());
				dpg.listView.add(st[7].trim());
				dpg.listPostComment.add(st[9].trim());
				CommManageDAO dao = new CommManageDAO();
				DPNUM.dpnum = st[0];
				int pNum = Integer.parseInt(st[0]);
				dpg.displayDetailPost(dao.detailPostDisplay(pNum));
				CommManageDAO dao2 = new CommManageDAO();
				ArrayList<CommManageDTO> list = dao2.detailCommentDisplay(pNum);
				dpg.displayDetailComment(list);
				
					
			
			}
			
		});
		cFilter.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				filter = (String) e.getItem();
				if (filter.equals("����")) filter = "all";
				if (filter.equals("�۹�ȣ")) filter = "pNum";
				if (filter.equals("������")) filter = "title";
				if (filter.equals("�ۼ���ID")) filter = "id";
				
				
				
				
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �˻� ��ư ����
				keyword = tfSearch.getText();
				if (filter == null) {
					if (keyword == null) { 
						displayPostInfo();
					} else {
						filter = "all"; 
						displayPostSearch(filter, keyword);
					}
				} else {
					displayPostSearch(filter, keyword);
				}
				
				
			}
		});
		
		
		btnAdminLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "������ ���� �α׾ƿ� �Ǿ����ϴ�.");
				frame.dispose();
				new NotAInfoPostGUI();
			}
			
		});
//��ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ�ȸ�� ���		
				btnMemRegister.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						frame.setVisible(false);
						new RegisterGUI();
					}
				});
//��ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ�ȸ�� ���
				//����Ʈ�� �ش� ���ڵ带 �����ϸ� tfNum�� setText
				list.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						// ����Ʈ�� �׸���� ������
						String str = list.getSelectedItem();
						StringTokenizer st = new StringTokenizer(str);
						tfNum.setText(st.nextToken());
					}
				});
				//������ ����Ʈ ���� ��ư
				btnDelete.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) { 
						String num = tfNum.getText();
						if (list.getSelectedItem() == null) { // ������ ����Ʈ�� ���� ���
							JOptionPane.showMessageDialog(null, "���õ� �Խñ��� �����ϴ�.");
						} else { // ������ ����Ʈ�� ���� ���
							Dialog deleteDial = new Dialog(frame, "����", true); // ���� ���θ� ���� ���̾�α� â
							deleteDial.addWindowListener(new WindowAdapter() {
								@Override
								public void windowClosing(WindowEvent e) {
									deleteDial.setVisible(false);
								}
							});
							deleteDial.setSize(240, 140);
							deleteDial.setLocation(((frame.getToolkit().getScreenSize()).width - deleteDial.getWidth())/2, 												((frame.getToolkit().getScreenSize()).height - deleteDial.getHeight())/2);						
							deleteDial.setLayout(new FlowLayout());
							Label deletemsg = new Label("�۹�ȣ " + tfNum.getText() + " ��/�� �����ұ��?");
							deletemsg.setFont(font2);
							Button btnOk = new Button("����");
							Button btnCancel = new Button("���");
							deleteDial.add(deletemsg);
							deleteDial.add(btnOk);
							btnOk.addActionListener(new ActionListener() { // ���̾�α�â�� ���� ��ư�� ������
								@Override
								public void actionPerformed(ActionEvent e) {
									CommManageDAO dao = new CommManageDAO();
									dao.postdelete(num); // �Խñ��� �����ϴ� postdelete �޼ҵ� ȣ��
									frame.dispose(); // ���� â ����
									new InfoPostGUI(); // �Խñ� ��� â
								}
							});	
							deleteDial.add(btnCancel);
							btnCancel.addActionListener(new ActionListener() {
								@Override
								public void actionPerformed(ActionEvent e) {
									deleteDial.setVisible(false);
								}
							});
							deleteDial.setVisible(true);
						}


					}
				});
	
	
	}

	public void displayPostInfo() {
		
		list.removeAll(); 
		CommManageDAO dao = new CommManageDAO();
		ArrayList<CommManageDTO> allData = dao.postInfoselect();
		for (CommManageDTO dto : allData) {
			int postNum = dto.getPostNum();
			String title = dto.getTitle();
			String ID = dto.getID();
			String postDate = dto.getPostDate();
			int view = dto.getView();
			int postComment = dto.getPostComment();
			
			list.add(postNum+ "               "  + title+ "                              "  + ID + "                              "  + postDate+ "                              "  + view + "                              " + postComment);
			list.setFont(font2);
		
		}
	}
	public void displayPostSearch (String filter, String keyword) {
		list.removeAll();
		CommManageDAO dao = new CommManageDAO();
		ArrayList<CommManageDTO> allData = dao.postInfoSearch(filter, keyword);
		for (CommManageDTO dto : allData) {
			int postNum = dto.getPostNum();
			String title = dto.getTitle();
			String ID = dto.getID();
			String postDate = dto.getPostDate();
			int view = dto.getView();
			int postComment = dto.getPostComment();
			
			list.add(postNum+ "               "  + title+ "                              "  + ID + "                              "  + postDate+ "                              "  + view + "                              " + postComment);
			list.setFont(font2);
		}
		
	}
}

// ȸ�� �� ������
class DetailMemGUI {
	Frame frame;
	Panel[] panel;
	Label lblMemDetail, lblName, lblId, lblLevel, lblEmail, lblJoinDate, lblPost, lblComment, lblLogDate,  
	lblPostDetail, lblPostNum, lblTitle, lblPostDate, lblView, lblContent;
	Button btnPostList,btnMemList, btnMemRegister, btnAdminLogin, btnSearch, btnEdit, btnDelete, btnBackMList, btnBackPList;
	TextField tfName, tfLevel, tfEmail, tfJoinDate, tfLogDate; 
	List listId, listPost, listMemComment;
	

	public DetailMemGUI() {
		frame = new Frame("Ŀ�´�Ƽ ����");
		panel = new Panel[20];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new Panel();
		}
		Font font1 = new Font("Default", Font.BOLD, 17);
		Font font2 = new Font("Default", Font.PLAIN, 15);
		lblMemDetail = new Label("�� ���� ������");
		lblMemDetail.setFont(font1);
		lblId = new Label("ID");
		lblId.setFont(font2);
		lblName = new Label("�̸�");
		lblName.setFont(font2);
		lblLevel = new Label("ȸ�����");
		lblLevel.setFont(font2);
		lblEmail = new Label("�����ּ�");
		lblEmail.setFont(font2);
		lblJoinDate = new Label("������");
		lblJoinDate.setFont(font2);
		lblPost = new Label("�Խñ� ��");
		lblPost.setFont(font2);
		lblComment = new Label("��� ��");
		lblComment.setFont(font2);
		lblLogDate = new Label("���� �α��� ��¥");
		lblLogDate.setFont(font2);
		lblPostDetail = new Label("�Խñ� ������");
		lblPostDetail.setFont(font1);
		lblPostNum = new Label("�۹�ȣ");
		lblPostNum.setFont(font2);
		lblTitle = new Label("������");
		lblTitle.setFont(font2);
		lblPostDate = new Label("�ۼ���");
		lblPostDate.setFont(font2);
		lblView = new Label("��ȸ��");
		lblView.setFont(font2);


		btnPostList = new Button("�۸��");
		btnPostList.setPreferredSize(new Dimension(200,50));
		btnPostList.setFont(font1);
		btnMemList = new Button("ȸ�� ���");
		btnMemList.setPreferredSize(new Dimension(200,50));
		btnMemList.setFont(font1);
		btnMemRegister = new Button("ȸ�� ���");
		btnMemRegister.setPreferredSize(new Dimension(200,50));
		btnMemRegister.setFont(font1);
		btnAdminLogin = new Button("������ �α׾ƿ�");
		btnAdminLogin.setPreferredSize(new Dimension(200,50));
		btnAdminLogin.setFont(font1);
		btnSearch = new Button("�˻�");
		btnSearch.setPreferredSize(new Dimension(100, 30));
		btnEdit = new Button("����");
		btnEdit.setPreferredSize(new Dimension(80,30));
		btnDelete = new Button("����");
		btnDelete.setPreferredSize(new Dimension(80,30));
		btnBackMList = new Button("���");
		btnBackMList.setPreferredSize(new Dimension(80,30));
		btnBackPList = new Button("���");
		btnBackPList.setPreferredSize(new Dimension(80,30));


		tfName = new TextField();
		tfName.setFont(font2);
		listId = new List(1, false);
		listId.setFont(font2);
		tfLevel = new TextField();
		tfLevel.setFont(font2);
		tfEmail = new TextField();
		tfEmail.setFont(font2);
		tfJoinDate = new TextField();
		tfJoinDate.setFont(font2);
		listPost = new List(1, false); 
		listPost.setFont(font2);
		listMemComment = new List(1, false);
		listMemComment.setFont(font2);
		tfLogDate = new TextField();
		tfLogDate.setFont(font2);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	
		panel[0].add(new Label(""));
		panel[0].add(btnMemList);
		panel[0].add(new Label(""));
		panel[0].add(btnMemRegister);
		panel[0].add(new Label(""));
		panel[0].add(btnPostList);
		panel[0].add(new Label(""));
		panel[0].add(btnAdminLogin);
		panel[0].add(new Label(""));

		panel[1].add(lblMemDetail);


		panel[3].setLayout(new BorderLayout());
		panel[3].add(panel[0], "North");
		panel[3].add(panel[1], "Center");

		panel[4].setLayout(new GridLayout(8, 1));
		panel[4].add(lblName);
		panel[4].add(lblId);
		panel[4].add(lblLevel);
		panel[4].add(lblEmail);
		panel[4].add(lblJoinDate);
		panel[4].add(lblPost);
		panel[4].add(lblComment);
		panel[4].add(lblLogDate);
		panel[4].setBackground(Color.LIGHT_GRAY);

		panel[5].setLayout(new GridLayout(8, 1));
		panel[5].add(tfName);
		panel[5].add(listId);
		panel[5].add(tfLevel);
		panel[5].add(tfEmail);
		panel[5].add(tfJoinDate);
		panel[5].add(listPost);
		panel[5].add(listMemComment);
		panel[5].add(tfLogDate);
		


		panel[6].setLayout(new BorderLayout());
		panel[6].add(panel[4], "West");
		panel[6].add(panel[5], "Center");

		panel[7].add(btnEdit);
		panel[7].add(btnDelete);
		panel[7].add(btnBackMList);

		panel[8].setLayout(new BorderLayout());
		panel[8].add(panel[3], "North");
		panel[8].add(panel[6], "Center");
		panel[8].add(panel[7], "South");

		frame.add(panel[8], "Center");
		frame.pack();
		frame.setResizable(false);
		frame.setLocation(((frame.getToolkit().getScreenSize()).width)/4, ((frame.getToolkit().getScreenSize()).height)/7);
		frame.setSize(1000,800);
		frame.setVisible(true);
		

		//��ܸ޴� ��ư ���
		btnPostList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				new InfoPostGUI();
				
			}
		});
		btnMemList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				new InfoMemGUI();
			}
		});
		btnAdminLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "������ ���� �α׾ƿ� �Ǿ����ϴ�.");
				frame.dispose();
				new NotAInfoMemGUI();
			}
		});
		
		//�ϴ�
		//���� ��ư
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] str = listId.getItems();
				String id = str[0];
				String name = tfName.getText(); //�ؽ�Ʈ �ʵ忡 �Է��� ���� ��� ���ڿ� ó����
				String level = tfLevel.getText();
				String email = tfEmail.getText();
				String joinDate = tfJoinDate.getText();
				String logDate = tfLogDate.getText();
				
				CommManageDAO dao = new CommManageDAO();
				dao.update(id, name, level, email, joinDate, logDate); // �������� �������� �ִ� �޼ҵ�� ȣ��
			
				
			}
		});
		
		btnBackMList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new InfoMemGUI();
			}
		});
//��ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡٵ�� ����		
				btnMemRegister.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) {
						
						new RegisterGUI();				
					}
				});
			
				btnDelete.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String[] str = listId.getItems();
						String id = str[0];
						//���� ���θ� ���� ���̾�α� â
						Dialog deleteDial = new Dialog(frame, "����", true);
						deleteDial.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosing(WindowEvent e) {
								deleteDial.setVisible(false);
							}
						});
						deleteDial.setSize(280, 140);
						deleteDial.setLocation(((frame.getToolkit().getScreenSize()).width - deleteDial.getWidth())/2, 											  												((frame.getToolkit().getScreenSize()).height - deleteDial.getHeight())/2);						
						deleteDial.setLayout(new FlowLayout());
						Label deletemsg = new Label("ȸ�� " + tfName.getText() + " ��/�� �����ұ��?");
						deletemsg.setFont(font2);
						Button btnOk = new Button("����");
						Button btnCancel = new Button("���");
						deleteDial.add(deletemsg);
						deleteDial.add(btnOk);
						btnOk.addActionListener(new ActionListener() { // ���̾�α� â���� ���� ��ư�� ������
							@Override
							public void actionPerformed(ActionEvent e) {
								CommManageDAO dao = new CommManageDAO();
								dao.memdelete(id); //ȸ���� �����ϴ� memdelete �޼ҵ� ȣ��
								frame.dispose(); //������ ȸ���� ���������� ����
								new InfoMemGUI(); //ȸ�� ��� ������
							}
						});
						deleteDial.add(btnCancel); 
						btnCancel.addActionListener(new ActionListener() {// ���̾�α� â���� ��� ��ư�� ������
							
							@Override
							public void actionPerformed(ActionEvent e) {
								deleteDial.setVisible(false); // ���̾�α� â�� ����
							}
						});
						deleteDial.setVisible(true);
					}
				});
//��ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡٵ�� ����
	}
	
}

//�Խñ� ������
class DetailPostGUI {
	Frame frame;
	Panel[] panel;
	Label lblMemDetail, lblName, lblId, lblLevel, lblEmail, lblJoinDate, lblPost, lblComment, lblLogDate,  
	lblPostDetail, lblTitle, lblPostNum, lblPostDate, lblView, lblContent, lblPostComment, lblCommId, lblCommDate, lblCommContent;
	Button btnPostList,btnMemList, btnMemRegister, btnAdminLogin, btnSearch, btnEdit, btnDelete, btnBackMList, btnBackPList;
	List listName, listId, listLevel, listEmail, listJoinDate, listPost, listComment, listLogDate,
	listPostNum, listTitle, listPostDate, listContent, listView, listPostComment;
	TextField tfNum;
	
	

	DetailPostGUI() {
		frame = new Frame("Ŀ�´�Ƽ ����");
		panel = new Panel[25];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new Panel();
		}
		Font font1 = new Font("Default", Font.BOLD, 17);
		Font font2 = new Font("Default", Font.PLAIN, 15);
		Font font3 = new Font("Default", Font.BOLD, 15);
		lblMemDetail = new Label("�� ���� ������");
		lblMemDetail.setFont(font1);
		lblId = new Label("ID");
		lblId.setFont(font2);
		lblName = new Label("�̸�");
		lblName.setFont(font2);
		lblLevel = new Label("ȸ�����");
		lblLevel.setFont(font2);
		lblEmail = new Label("�����ּ�");
		lblEmail.setFont(font2);
		lblJoinDate = new Label("������");
		lblJoinDate.setFont(font2);
		lblPost = new Label("�Խñ� ��");
		lblPost.setFont(font2);
		lblComment = new Label("��� ��");
		lblComment.setFont(font2);
		lblLogDate = new Label("���� �α��� ��¥");
		lblLogDate.setFont(font2);
		lblPostDetail = new Label("�Խñ� ������");
		lblPostDetail.setFont(font1);
		lblPostNum = new Label("�۹�ȣ");
		lblPostNum.setFont(font3);
		lblTitle = new Label("������");
		lblTitle.setFont(font3);
		lblPostDate = new Label("�ۼ���");
		lblPostDate.setFont(font3);
		lblView = new Label("��ȸ��");
		lblView.setFont(font3);
		lblContent = new Label("�� ����");
		lblContent.setFont(font3);
		lblPostComment = new Label("��� ��");
		lblPostComment.setFont(font3);
		lblCommId = new Label("��� �ۼ���");
		lblCommId.setFont(font3);
		lblCommDate = new Label("��� �ۼ���");
		lblCommDate.setFont(font3);
		lblCommContent = new Label("��� ����");
		lblCommContent.setFont(font3);
		
		

		tfNum = new TextField();

		btnPostList = new Button("�۸��");
		btnPostList.setPreferredSize(new Dimension(200,50));
		btnPostList.setFont(font1);
		btnMemList = new Button("ȸ�� ���");
		btnMemList.setPreferredSize(new Dimension(200,50));
		btnMemList.setFont(font1);
		btnMemRegister = new Button("ȸ�� ���");
		btnMemRegister.setPreferredSize(new Dimension(200,50));
		btnMemRegister.setFont(font1);
		btnAdminLogin = new Button("������ �α׾ƿ�");
		btnAdminLogin.setPreferredSize(new Dimension(200,50));
		btnAdminLogin.setFont(font1);
		btnSearch = new Button("�˻�");
		btnSearch.setPreferredSize(new Dimension(100, 30));
		btnEdit = new Button("����");
		btnEdit.setPreferredSize(new Dimension(80,30));
		btnDelete = new Button("����");
		btnDelete.setPreferredSize(new Dimension(80,30));
		btnBackMList = new Button("���");
		btnBackMList.setPreferredSize(new Dimension(80,30));
		btnBackPList = new Button("���");
		btnBackPList.setPreferredSize(new Dimension(80,30));


		listId = new List(1, false);
		listId.setFont(font2);
		listComment = new List(5, false);
		listComment.setFont(font2);
		listPostNum = new List(1,false);
		listPostNum.setFont(font2);
		listTitle = new List(1, false);
		listTitle.setFont(font2);
		
		listPostDate = new List(1,false);
		listPostDate.setFont(font2);
		listView = new List(1, false);
		listView.setFont(font2);
		listContent = new List(10, false);
		listContent.setFont(font2);
		listPostComment = new List(1, false);
		listPostComment.setFont(font2);
		
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		panel[1].add(new Label(""));
		panel[1].add(btnMemList);
		panel[1].add(new Label(""));
		panel[1].add(btnMemRegister);
		panel[1].add(new Label(""));
		panel[1].add(btnPostList);
		panel[1].add(new Label(""));
		panel[1].add(btnAdminLogin);
		panel[1].add(new Label(""));

		panel[2].add(lblPostDetail);

		panel[3].setLayout(new BorderLayout());
		panel[3].add(panel[1], "North");
		panel[3].add(panel[2], "Center");
	
		panel[4].setLayout(new BorderLayout());
		panel[4].add(new Label(""), "North");
		panel[4].add(lblTitle, "West");
		panel[4].add(listTitle, "Center");
		panel[4].add(new Label(""), "South");
		
		
		panel[5].setLayout(new GridLayout(2, 1));
		panel[5].add(lblPostNum);
		panel[5].add(lblId);
		
		
		panel[6].setLayout(new GridLayout(2, 1));
		panel[6].add(listPostNum);
		panel[6].add(listId);
		
		panel[7].setLayout(new GridLayout(2, 1));
		panel[7].add(lblPostDate);
		panel[7].add(lblView);
		
		panel[8].setLayout(new GridLayout(2, 1));
		panel[8].add(listPostDate);
		panel[8].add(listView);
		
		panel[9].setLayout(new BorderLayout());
		panel[9].add(panel[5], "West");
		panel[9].add(panel[6], "Center");
		panel[9].add(new Label(""), "East");
		
		panel[10].setLayout(new BorderLayout());
		panel[10].add(panel[7], "West");
		panel[10].add(panel[8], "Center");
		
		panel[11].setLayout(new GridLayout(1, 2));
		panel[11].add(panel[9]);
		panel[11].add(panel[10]);
		
		panel[12].setLayout(new BorderLayout());
		panel[12].add(panel[4], "North");
		panel[12].add(panel[11], "Center");
		panel[12].add(new Label(""), "South");
		
		panel[13].setLayout(new GridLayout(7, 1));
		panel[13].add(lblContent);
		panel[13].add(new Label(""));
		panel[13].add(new Label(""));
		panel[13].add(new Label(""));
		panel[13].add(new Label(""));
		panel[13].add(new Label(""));
		panel[13].add(lblPostComment);
		
		panel[14].setLayout(new BorderLayout());
		panel[14].add(listContent, "North");
		panel[14].add(new Label(""), "Center");
		panel[14].add(panel[22], "South");
		
		panel[22].setLayout(new GridLayout(1, 7));
		panel[22].add(listPostComment);
		panel[22].add(new Label(""));
		panel[22].add(new Label(""));
		panel[22].add(new Label(""));
		panel[22].add(new Label(""));
		panel[22].add(new Label(""));
		panel[22].add(new Label(""));
		
		panel[15].setLayout(new BorderLayout());
		panel[15].add(panel[13], "West");
		panel[15].add(panel[14], "Center");
		
		panel[16].setLayout(new BorderLayout());
		panel[16].add(panel[12], "North");
		panel[16].add(panel[15], "Center");
		
		panel[17].setLayout(new GridLayout(2, 5));
		panel[17].add(new Label(""));
		panel[17].add(new Label(""));
		panel[17].add(new Label(""));
		panel[17].add(new Label(""));
		panel[17].add(new Label(""));
		panel[17].add(lblCommId);
		panel[17].add(lblCommDate);
		panel[17].add(lblCommContent);
		panel[17].add(new Label(""));
		panel[17].add(new Label(""));

		panel[18].setLayout(new GridLayout(1, 1));
		panel[18].add(listComment);
		
		panel[0].add(btnDelete);
		panel[0].add(btnBackPList);
		
		panel[19].setLayout(new BorderLayout());
		panel[19].add(panel[3], "North");
		panel[19].add(panel[16], "Center");
		
		panel[20].setLayout(new BorderLayout());
		panel[20].add(panel[17], "North");
		panel[20].add(panel[18], "Center");
		
		panel[21].setLayout(new BorderLayout());
		panel[21].add(panel[19], "North");
		panel[21].add(panel[20], "Center");
		panel[21].add(panel[0], "South");
		
		frame.pack();
		frame.add(panel[21]);
		frame.setResizable(false);
		frame.setLocation(((frame.getToolkit().getScreenSize()).width)/4, ((frame.getToolkit().getScreenSize()).height)/7);
		frame.setSize(1000,800);
		frame.setVisible(true);
		
		
		//��ܸ޴� ��ư ���
		btnPostList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
		
				frame.dispose();
				new InfoPostGUI();
				
			}
		});
		btnMemList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				frame.dispose();
				new InfoMemGUI();
			}
		});
		
		btnAdminLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "������ ���� �α׾ƿ� �Ǿ����ϴ�.");
				frame.dispose();
				new NotAInfoPostGUI();
			}
			
		});
		btnBackPList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				frame.setVisible(false);
				new InfoPostGUI();
				
			}
		});
		
//��ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ�ȸ�� ���		
				btnMemRegister.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) {
				
						new RegisterGUI();
					}
				});
//��ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ�ȸ�� ���	
				//������ ����Ʈ ���� ��ư
				btnDelete.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) 
					{ // �����ϱ� ��ư ����
						Dialog deleteDial = new Dialog(frame, "����", true); // ���� ���θ� ���� ���̾�α� â
						deleteDial.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosing(WindowEvent e) {
								deleteDial.setVisible(false);
							}
						});
						deleteDial.setSize(260, 140);
						deleteDial.setLocation(((frame.getToolkit().getScreenSize()).width - deleteDial.getWidth())/2, 											((frame.getToolkit().getScreenSize()).height - deleteDial.getHeight())/2);						
						deleteDial.setLayout(new FlowLayout());
						// �Խñ� ��Ͽ��� ����Ŭ���ؼ� �� �������� ��ȯ�� �� �ش� �Խñ� ��ȣ�� ���� �������� dpnum
						Label deletemsg = new Label("�۹�ȣ " + DPNUM.dpnum + "��/�� �����ұ��?");
						deletemsg.setFont(font2);
						Button btnOk = new Button("����");
						Button btnCancel = new Button("���");
						deleteDial.add(deletemsg);
						deleteDial.add(btnOk);
						btnOk.addActionListener(new ActionListener() { // ���̾�α� â���� ���� ��ư�� ������ �� 
							@Override
							public void actionPerformed(ActionEvent e) {
								CommManageDAO dao = new CommManageDAO();
								dao.postdelete(DPNUM.dpnum); // �Խñ��� �����ϴ� postdelete �޼ҵ忡 �Խñ۹�ȣ�� �Ű������� ȣ��
								frame.dispose(); // ������ �Խñ��� ���������� ����
								new InfoPostGUI(); // �Խñ� ��� â
							}
						});
						deleteDial.add(btnCancel);
						btnCancel.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								deleteDial.setVisible(false);
							}
						});
						deleteDial.setVisible(true);
					}
				});
				
				
	}
	public void displayDetailPost(String postContent) {
		listContent.add(postContent);
	}
	
	
	public void displayDetailComment(ArrayList<CommManageDTO> list) {
		listComment.removeAll();
		for (CommManageDTO dto : list) {
			String commId = dto.getCommId();
			String commDate = dto.getCommDate();
			String commContent = dto.getCommContent();
			listComment.add("     " + commId + "                                     " + commDate + "                                 " + commContent);
			
		}
	}
	
	
}
//������ ���� ���� ȸ�� ��� ������
class NotAInfoMemGUI {
	static Frame NAMframe;
	Panel[] panel;
	Label lblMemInfo, lblId, lblName, lblLevel, lblEmail, lblJoinDate, lblPost, lblMemComment, 
	lblLogDate, lblPostNum, lblPostInfo, lblTitle, lblPostDate, lblView, lblPostComment;
	Button btnPostList, btnMemRegister, btnAdminLogin, btnSearch, btnEdit, btnDelete, btnMemList;
	TextField tfSearch, tfName,tfId;
	Choice cFilter;
	List list;
	Font font1 = new Font("Default", Font.BOLD, 17);
	Font font2 = new Font("Default", Font.PLAIN, 15);
	
	String filter = null;
	String keyword;
	
	public NotAInfoMemGUI() {
		NAMframe = new Frame("Ŀ�´�Ƽ ����");
		panel = new Panel[9];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new Panel();
		}
		
		lblMemInfo = new Label("��ü ȸ�� ����");
		lblMemInfo.setFont(font1);
		lblId = new Label("ID");
		lblId.setFont(font2);
		lblName = new Label("�̸�");
		lblName.setFont(font2);
		lblLevel = new Label("ȸ�����");
		lblLevel.setFont(font2);
		lblEmail = new Label("�����ּ�");
		lblEmail.setFont(font2);
		lblJoinDate = new Label("������");
		lblJoinDate.setFont(font2);
		lblPost = new Label("�Խñ� ��");
		lblPost.setFont(font2);
		lblMemComment = new Label("��� ��"); //ȸ������ ��� ��
		lblMemComment.setFont(font2);
		lblLogDate = new Label("���� �α��� ��¥");
		lblLogDate.setFont(font2);
		lblPostInfo = new Label("��ü �Խñ� ����");
		lblPostInfo.setFont(font1);
		lblPostNum = new Label("�۹�ȣ");
		lblPostNum.setFont(font2);
		lblTitle = new Label("������");
		lblTitle.setFont(font2);
		lblPostDate = new Label("�ۼ���");
		lblPostDate.setFont(font2);
		lblView = new Label("��ȸ��");
		lblView.setFont(font2);
		lblPostComment = new Label("��� ��"); //������ ��� �� 
		lblPostComment.setFont(font2);
		
		tfSearch = new TextField("");
		tfName = new TextField("");
		tfId = new TextField("");
		tfSearch.setPreferredSize(new Dimension(500,25));
		tfSearch.setFont(font2);
		
		btnPostList = new Button("�۸��");
		btnPostList.setPreferredSize(new Dimension(200,50));
		btnPostList.setFont(font1);
		btnMemList = new Button("ȸ�� ���");
		btnMemList.setPreferredSize(new Dimension(200,50));
		btnMemList.setFont(font1);
		btnMemRegister = new Button("ȸ�� ���");
		btnMemRegister.setPreferredSize(new Dimension(200,50));
		btnMemRegister.setFont(font1);
		btnAdminLogin = new Button("������ �α���");
		btnAdminLogin.setPreferredSize(new Dimension(200,50));
		btnAdminLogin.setFont(font1);
		btnSearch = new Button("�˻�");
		btnSearch.setPreferredSize(new Dimension(100, 30));
		btnEdit = new Button("����");
		btnEdit.setPreferredSize(new Dimension(80,30));
		btnDelete = new Button("����");
		btnDelete.setPreferredSize(new Dimension(80,30));
		
		cFilter = new Choice();
		cFilter.setPreferredSize(new Dimension(150,50));
		cFilter.add("����");
		cFilter.addItem("ID");
		cFilter.addItem("���");
		cFilter.addItem("�̸�");
		cFilter.addItem("�����ּ�");
		
		list = new List(12, false);
		NAMframe.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);;
			}
		});
	
		panel[0].add(new Label(""));
		panel[0].add(btnMemList);
		panel[0].add(new Label(""));
		panel[0].add(btnMemRegister);
		panel[0].add(new Label(""));
		panel[0].add(btnPostList);
		panel[0].add(new Label(""));
		panel[0].add(btnAdminLogin);
		panel[0].add(new Label(""));
		
		panel[1].add(lblMemInfo);
		
		panel[2].add(cFilter);
		panel[2].add(tfSearch);
		panel[2].add(btnSearch);
		
		panel[3].setLayout(new BorderLayout());
		panel[3].add(panel[0], "North");
		panel[3].add(panel[1], "Center");
		panel[3].add(panel[2], "South");

		panel[4].setLayout(new GridLayout(1, 8));
		panel[4].add(lblName);
		panel[4].add(lblId);
		panel[4].add(lblLevel);
		panel[4].add(lblEmail);
		panel[4].add(lblJoinDate);
		panel[4].add(lblPost);
		panel[4].add(lblMemComment);
		panel[4].add(lblLogDate);
		panel[4].setBackground(Color.LIGHT_GRAY);
		
		panel[5].setLayout(new BorderLayout());
		panel[5].add(list, "Center");
		
		panel[6].setLayout(new BorderLayout());
		panel[6].add(panel[4], "North");
		panel[6].add(panel[5], "Center");
		
		//panel[7].add(btnEdit);
		panel[7].add(btnDelete);
		
		panel[8].setLayout(new BorderLayout());
		panel[8].add(panel[3], "North");
		panel[8].add(panel[6], "Center");
		panel[8].add(panel[7], "South");
		
		NAMframe.add(panel[8], "Center");
		NAMframe.pack();
		NAMframe.setResizable(false);
		NAMframe.setLocation(((NAMframe.getToolkit().getScreenSize()).width)/4, ((NAMframe.getToolkit().getScreenSize()).height)/7);
		NAMframe.setSize(1000,800);
		NAMframe.setVisible(true);
		displayMemInfo();

		
		btnPostList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				//frame.dispose();

				NAMframe.dispose();
				new NotAInfoPostGUI();
				//setVisible(false);
			}
		});
		btnMemList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				NAMframe.dispose();
				new NotAInfoMemGUI();
			}
		});
		btnAdminLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				DPNUM.frameDivision = 1 ;
				new LoginGUI();
				
				
			}
			
		});
		
		btnMemRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "������ �α����� �ʿ��մϴ�.");
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "������ �α����� �ʿ��մϴ�.");
				
			}
		});

		
		cFilter.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
		
				filter = (String) e.getItem();
				if (filter.equals("����")) filter = "all"; //���� �����ϰ� �˻��ϸ� ��ü�˻�
				if (filter.equals("ID")) filter = "id";
				if (filter.equals("�̸�")) filter = "name";
				if (filter.equals("���")) filter = "level";
				if (filter.equals("�����ּ�")) filter = "email";
				
				
				
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �˻� ��ư ����
				keyword = tfSearch.getText();
				if (filter == null) {
					if (keyword == null) { 
						displayMemInfo();
					} else {
						filter = "all"; 
						displayMemSearch(filter, keyword);
					}
				} else {
					displayMemSearch(filter, keyword);
				}				
			}
		});
	}
	public void displayMemInfo() {
		// ȸ������ ����
		list.removeAll();
		CommManageDAO dao = new CommManageDAO();
		ArrayList<CommManageDTO> allData = dao.memInfoselect();
		for (CommManageDTO dto : allData) {
			String name = dto.getName();
			String ID = dto.getID();
			int level = dto.getLevel();
			String email = dto.getEmail();
			String joinDate = dto.getJoinDate();
			int post = dto.getMemPost();
			int comment = dto.getMemComment();
			String logDate = dto.getLogDate();
			list.add(name+ "                 " + ID + "                           " + level + "                "  + email + "           "  + joinDate + "                    " + post + "                         " + comment + "                            " +logDate);
			list.setFont(font2);
		}
	}
	//ȸ������ �˻�
	public void displayMemSearch(String filter, String keyword) {
		list.removeAll();
		CommManageDAO dao = new CommManageDAO();
		ArrayList<CommManageDTO> allData = dao.memInfoSearch(filter, keyword);
		for (CommManageDTO dto : allData) {
			String name = dto.getName();
			String ID = dto.getID();
			int level = dto.getLevel();
			String email = dto.getEmail();
			String joinDate = dto.getJoinDate();
			int post = dto.getMemPost();
			int comment = dto.getMemComment();
			String logDate = dto.getLogDate();
			list.add(name+ "                 " + ID + "                           " + level + "                "  + email + "           "  + joinDate + "                    " + post + "                         " + comment + "                            " +logDate);
			list.setFont(font2);
		}
		
		
		
	}
	
}
// ������ ���� ���� �� ��� ȭ��
class NotAInfoPostGUI {
	static Frame NAPframe;
	Panel[] panel;
	Label lblMemInfo, lblId, lblName, lblLevel, lblEmail, lblJoinDate, lblPost, lblComment, 
			lblLogDate, lblPostNum, lblPostInfo, lblTitle, lblPostDate, lblView, lblPostComment;
	Button btnPostList, btnMemRegister, btnAdminLogin, btnSearch, btnEdit, btnDelete, btnMemList;
	TextField tfSearch, tfNum, tfTitle, tfId;
	Choice cFilter;
	List list, list2;
	Font font1 = new Font("Default", Font.BOLD, 17);
	Font font2 = new Font("Default", Font.PLAIN, 15);
	String filter = null;
	String keyword = null;
	
	
	
	public NotAInfoPostGUI() {
		NAPframe = new Frame("Ŀ�´�Ƽ ����");
		panel = new Panel[9];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new Panel();
		}
		
		lblMemInfo = new Label("��ü ȸ�� ����");
		lblMemInfo.setFont(font1);
		lblId = new Label("ID");
		lblId.setFont(font2);
		lblName = new Label("�̸�");
		lblName.setFont(font2);
		lblLevel = new Label("ȸ�����");
		lblLevel.setFont(font2);
		lblEmail = new Label("�����ּ�");
		lblEmail.setFont(font2);
		lblJoinDate = new Label("������");
		lblJoinDate.setFont(font2);
		lblPost = new Label("�Խñ� ��");
		lblPost.setFont(font2);
		lblComment = new Label("���");
		lblComment.setFont(font2);
		lblLogDate = new Label("���� �α��� ��¥");
		lblLogDate.setFont(font2);
		lblPostInfo = new Label("��ü �Խñ� ����");
		lblPostInfo.setFont(font1);
		lblPostNum = new Label("�۹�ȣ");
		lblPostNum.setFont(font2);
		lblTitle = new Label("������");
		lblTitle.setFont(font2);
		lblPostDate = new Label("�ۼ���");
		lblPostDate.setFont(font2);
		lblPostComment = new Label("��� ��");
		lblPostComment.setFont(font2);
		lblView = new Label("��ȸ��");
		lblView.setFont(font2);
		
		tfSearch = new TextField("");
		tfSearch.setPreferredSize(new Dimension(500,25));
		tfSearch.setFont(font2);
		tfNum = new TextField("");
		tfTitle = new TextField("");
		tfId = new TextField("");
		
		btnPostList = new Button("�۸��");
		btnPostList.setPreferredSize(new Dimension(200,50));
		btnPostList.setFont(font1);
		btnMemList = new Button("ȸ�� ���");
		btnMemList.setPreferredSize(new Dimension(200,50));
		btnMemList.setFont(font1);
		btnMemRegister = new Button("ȸ�� ���");
		btnMemRegister.setPreferredSize(new Dimension(200,50));
		btnMemRegister.setFont(font1);
		btnAdminLogin = new Button("������ �α���");
		btnAdminLogin.setPreferredSize(new Dimension(200,50));
		btnAdminLogin.setFont(font1);
		btnSearch = new Button("�˻�");
		btnSearch.setPreferredSize(new Dimension(100, 30));
		btnEdit = new Button("����");
		btnEdit.setPreferredSize(new Dimension(80,30));
		btnDelete = new Button("����");
		btnDelete.setPreferredSize(new Dimension(80,30));
		
		cFilter = new Choice();
		cFilter.setPreferredSize(new Dimension(150,50));
		cFilter.addItem("����");
		cFilter.addItem("�۹�ȣ");
		cFilter.addItem("������");
		cFilter.addItem("�ۼ���ID");
		
		
		list = new List(12, false);
		list2 = new List(12,false);
		NAPframe.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);;
			}
		});
	
		panel[0].add(new Label(""));
		panel[0].add(btnMemList);
		panel[0].add(new Label(""));
		panel[0].add(btnMemRegister);
		panel[0].add(new Label(""));
		panel[0].add(btnPostList);
		panel[0].add(new Label(""));
		panel[0].add(btnAdminLogin);
		panel[0].add(new Label(""));
		
		panel[1].add(lblPostInfo);
		
		panel[2].add(cFilter);
		panel[2].add(tfSearch);
		panel[2].add(btnSearch);
		
		panel[3].setLayout(new BorderLayout());
		panel[3].add(panel[0], "North");
		panel[3].add(panel[1], "Center");
		panel[3].add(panel[2], "South");

		panel[4].setLayout(new GridLayout(1, 8));
		panel[4].add(lblPostNum);
		panel[4].add(lblTitle);
		panel[4].add(lblId);
		panel[4].add(lblPostDate);
		panel[4].add(lblView);
		panel[4].add(lblPostComment);
		panel[4].setBackground(Color.LIGHT_GRAY);
		
		panel[5].setLayout(new BorderLayout());
		panel[5].add(list, "Center");
		panel[6].setLayout(new BorderLayout());
		panel[6].add(panel[4], "North");
		panel[6].add(panel[5], "Center");
		
		panel[7].add(btnDelete);
		
		panel[8].setLayout(new BorderLayout());
		panel[8].add(panel[3], "North");
		panel[8].add(panel[6], "Center");
		panel[8].add(panel[7], "South");
		
		NAPframe.add(panel[8], "Center");
		NAPframe.pack();
		NAPframe.setResizable(false);
		NAPframe.setLocation(((NAPframe.getToolkit().getScreenSize()).width)/4, ((NAPframe.getToolkit().getScreenSize()).height)/7);
		NAPframe.setSize(1000,800);
		NAPframe.setVisible(true);
		displayPostInfo();
		
		
		btnPostList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// �۸�Ϲ�ư�� ������
				NAPframe.dispose();
				new NotAInfoPostGUI();
				
			}
		});
		btnMemList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ȸ����Ϲ�ư�� ������

				NAPframe.dispose();
				new NotAInfoMemGUI();
			}
		});
		btnMemRegister.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "������ �α����� �ʿ��մϴ�.");

			}
		});
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "������ �α����� �ʿ��մϴ�.");

			}
		});


		cFilter.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				filter = (String) e.getItem();
				if (filter.equals("����")) filter = "all";
				if (filter.equals("�۹�ȣ")) filter = "pNum";
				if (filter.equals("������")) filter = "title";
				if (filter.equals("�ۼ���ID")) filter = "id";

				
				
				
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// �˻� ��ư ����
				keyword = tfSearch.getText();
				if (filter == null) {
					if (keyword == null) { 
						displayPostInfo();
					} else {
						filter = "all"; 
						displayPostSearch(filter, keyword);
					}
				} else {
					displayPostSearch(filter, keyword);
				}
				
				
			}
		});
		
		
		btnAdminLogin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				DPNUM.frameDivision = 2 ;
				new LoginGUI();
			}
			
		});


	}

	public void displayPostInfo() {
		
		list.removeAll();
		CommManageDAO dao = new CommManageDAO();
		ArrayList<CommManageDTO> allData = dao.postInfoselect();
		for (CommManageDTO dto : allData) {
			int postNum = dto.getPostNum();
			String title = dto.getTitle();
			String ID = dto.getID();
			String postDate = dto.getPostDate();
			int view = dto.getView();
			int postComment = dto.getPostComment();
			
			list.add(postNum+ "               "  + title+ "                              "  + ID + "                              "  + postDate+ "                              "  + view + "                              " + postComment);
			list.setFont(font2);
		
		}
	}
	public void displayPostSearch (String filter, String keyword) {
		list.removeAll();
		CommManageDAO dao = new CommManageDAO();
		ArrayList<CommManageDTO> allData = dao.postInfoSearch(filter, keyword);
		for (CommManageDTO dto : allData) {
			int postNum = dto.getPostNum();
			String title = dto.getTitle();
			String ID = dto.getID();
			String postDate = dto.getPostDate();
			int view = dto.getView();
			int postComment = dto.getPostComment();
			list.add(postNum+ "               "  + title+ "                              "  + ID + "                              "  + postDate+ "                              "  + view + "                              " + postComment);
			list.setFont(font2);
		}
		
	}
}

//������ �α��� â
class LoginGUI extends Frame {
	Frame frame;
	Panel[] panel;
	Label lblID, lblPassWD;
	TextField tfID, tfPassWD;
	Button btnLogin;
	
	
	public LoginGUI() {
		frame = new Frame("������ �α���");
		panel = new Panel[6];
		for(int i=0; i<panel.length; i++) {
			panel[i] = new Panel();
		}
		lblID = new Label("���̵�");
		lblPassWD = new Label("��й�ȣ");
		tfID = new TextField("");
		tfPassWD = new TextField("");
		tfPassWD.setEchoChar('*'); // ��й�ȣ�� �ԷµǴ� �ؽ�Ʈ �ʵ��� ���ڸ� *�� ��ȯ
		btnLogin = new Button("�α���");
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setPreferredSize(new Dimension(80,30));
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				frame.dispose();
			}
		});
		
		panel[1].setLayout(new GridLayout(2,1));
		panel[1].add(lblID);
		panel[1].add(lblPassWD);
		panel[2].setLayout(new GridLayout(2,1));
		panel[2].add(tfID);
		panel[2].add(tfPassWD);
		panel[3].setLayout(new GridLayout(1,2));
		panel[3].add(panel[1]);
		panel[3].add(panel[2]);
		
		panel[4].setLayout(new BorderLayout());
		panel[4].add(new Label(" "),"West");
		panel[4].add(btnLogin,"Center");
		panel[4].add(new Label(" "),"East");
		panel[5].setLayout(new BorderLayout());
		panel[5].add(new Label(" "),"North");
		panel[5].add(new Label(" "),"West");
		panel[5].add(panel[3],"Center");
		panel[5].add(panel[4],"East");
		panel[5].add(new Label(" "),"South");
		
		frame.add(panel[5], "Center");
		frame.pack();
		frame.setResizable(false);
		frame.setLocation(((frame.getToolkit().getScreenSize()).width - frame.getWidth())/2, ((frame.getToolkit().getScreenSize()).height - frame.getHeight())/4);
		frame.setSize(300,150);
		frame.setVisible(true);
		
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// ��ư�� ������ �������� �ڵ带 ����
				String id = tfID.getText();
				String PassWD = tfPassWD.getText(); //tfAge�� �Է��� ���� ��� ���ڿ� ó����
				
				CommManageDAO dao = new CommManageDAO();
				// �������� �������� �ִ� login�޼ҵ� ȣ��
				int x = dao.login(id, PassWD); // �α��ο� �����ϸ� 1�� ��ȯ, �� ���� x�� ����
				if(x == 1) { // �α��ο� �����ϸ�
					frame.dispose(); // 1.�α��� â�� ����
					// 2. �α��� �Ϸ��� â�� ����
					if(DPNUM.frameDivision == 1 ) NotAInfoMemGUI.NAMframe.dispose(); 
					if(DPNUM.frameDivision == 2 ) NotAInfoPostGUI.NAPframe.dispose();
					new InfoMemGUI(); // 3. �α��ε� ȭ���� �ҷ���
				}
			}
		});
	}
}

//��ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ�ȸ�� ���
class RegisterGUI extends InfoMemGUI
{
	Frame frame;
	Panel[] panel;
	Label lblId, lblPassWD,lblName,lblLevel,lblEmail;
	TextField tfId, tfPassWD, tfName, tfLevel, tfEmail;
	Button btnSave;
	List list;

	public RegisterGUI() 
	{
		
		frame = new Frame("ȸ�� ���");
		panel = new Panel[9];
		for(int i=0; i<panel.length; i++)
		{
			panel[i] = new Panel();
		}
		lblId = new Label("�̸�");
		lblName = new Label("���̵�");
		lblLevel = new Label("ȸ�� ���");
		lblEmail = new Label("�����ּ�");
		tfId = new TextField("");
		tfName = new TextField("");
		tfLevel = new TextField("");
		tfEmail = new TextField("");

		btnSave = new Button("����");
		btnSave.setBackground(Color.WHITE);
		btnSave.setPreferredSize(new Dimension(80,30));

		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e) 
			{
				frame.dispose();
			}
		});

		panel[1].setLayout(new GridLayout(4,1));
		panel[1].add(lblId);
		panel[1].add(lblName);
		panel[1].add(lblLevel);
		panel[1].add(lblEmail);
		panel[2].setLayout(new GridLayout(4,1));
		panel[2].add(tfId);
		panel[2].add(tfName);
		panel[2].add(tfLevel);
		panel[2].add(tfEmail);
		panel[3].setLayout(new GridLayout(1,2));
		panel[3].add(panel[1]);
		panel[3].add(panel[2]);

		panel[4].setLayout(new BorderLayout());
		panel[4].add(new Label(" "),"West");
		panel[4].add(btnSave,"Center");
		panel[4].add(new Label(" "),"East");
		panel[5].setLayout(new BorderLayout());
		panel[5].add(new Label(" "),"North");
		panel[5].add(new Label(" "),"West");
		panel[5].add(panel[3],"Center");
		panel[5].add(panel[4],"East");
		panel[5].add(new Label(" "),"South");


		frame.add(panel[5], "Center");
		frame.pack();
		frame.setResizable(false);
		frame.setLocation(((frame.getToolkit().getScreenSize()).width - frame.getWidth())/2, ((frame.getToolkit().getScreenSize()).height - frame.getHeight())/4);
		frame.setSize(300,150);
		frame.setVisible(true);

		btnSave.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{					
				String name = tfName.getText();
				String id = tfId.getText();
				String level = tfLevel.getText();
				String email = tfEmail.getText();

				CommManageDAO dao = new CommManageDAO();
				dao.insert(id, name, level, email);
				frame.dispose();
				displayMemInfo();
				
				
			}			
		});	
		
	}
}
class DPNUM{
    static String dpnum;
    static int frameDivision;
}
//��ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ١ڡ�ȸ�� ���
