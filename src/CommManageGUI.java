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
//회원 목록 페이지
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
		frame = new Frame("커뮤니티 관리");
		panel = new Panel[9];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new Panel();
		}
		
		lblMemInfo = new Label("전체 회원 관리");
		lblMemInfo.setFont(font1);
		lblId = new Label("ID");
		lblId.setFont(font2);
		lblName = new Label("이름");
		lblName.setFont(font2);
		lblLevel = new Label("회원등급");
		lblLevel.setFont(font2);
		lblEmail = new Label("메일주소");
		lblEmail.setFont(font2);
		lblJoinDate = new Label("가입일");
		lblJoinDate.setFont(font2);
		lblPost = new Label("게시글 수");
		lblPost.setFont(font2);
		lblMemComment = new Label("댓글 수"); //회원정보 댓글 수
		lblMemComment.setFont(font2);
		lblLogDate = new Label("최종 로그인 날짜");
		lblLogDate.setFont(font2);
		lblPostInfo = new Label("전체 게시글 관리");
		lblPostInfo.setFont(font1);
		lblPostNum = new Label("글번호");
		lblPostNum.setFont(font2);
		lblTitle = new Label("글제목");
		lblTitle.setFont(font2);
		lblPostDate = new Label("작성일");
		lblPostDate.setFont(font2);
		lblView = new Label("조회수");
		lblView.setFont(font2);
		lblPostComment = new Label("댓글 수"); //글정보 댓글 수 
		lblPostComment.setFont(font2);
		
		tfSearch = new TextField("");
		tfName = new TextField("");
		tfId = new TextField("");
		tfSearch.setPreferredSize(new Dimension(500,25));
		tfSearch.setFont(font2);
		
		btnPostList = new Button("글목록");
		btnPostList.setPreferredSize(new Dimension(200,50));
		btnPostList.setFont(font1);
		btnMemList = new Button("회원 목록");
		btnMemList.setPreferredSize(new Dimension(200,50));
		btnMemList.setFont(font1);
		btnMemRegister = new Button("회원 등록");
		btnMemRegister.setPreferredSize(new Dimension(200,50));
		btnMemRegister.setFont(font1);
		btnAdminLogin = new Button("관리자 로그아웃");
		btnAdminLogin.setPreferredSize(new Dimension(200,50));
		btnAdminLogin.setFont(font1);
		btnSearch = new Button("검색");
		btnSearch.setPreferredSize(new Dimension(100, 30));
		btnEdit = new Button("수정");
		btnEdit.setPreferredSize(new Dimension(80,30));
		btnDelete = new Button("삭제");
		btnDelete.setPreferredSize(new Dimension(80,30));
		
		cFilter = new Choice();
		cFilter.setPreferredSize(new Dimension(150,50));
		cFilter.add("선택");
		cFilter.addItem("ID");
		cFilter.addItem("등급");
		cFilter.addItem("이름");
		cFilter.addItem("메일주소");
		
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

		list.addActionListener(new ActionListener() { //더블클릭 이벤트 처리

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
				JOptionPane.showMessageDialog(null, "관리자 권한 로그아웃 되었습니다.");
				frame.dispose();
				new NotAInfoMemGUI();
			}
			
		});
		
		cFilter.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				
				filter = (String) e.getItem();
				if (filter.equals("선택")) filter = "all"; //선택 설정하고 검색하면 전체검색
				if (filter.equals("ID")) filter = "id";
				if (filter.equals("이름")) filter = "name";
				if (filter.equals("등급")) filter = "level";
				if (filter.equals("메일주소")) filter = "email";
				
				
				
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 검색 버튼 구현
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
//■★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆등록 삭제
		btnMemRegister.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.setVisible(false);
				new RegisterGUI();
			}
		});
	
		//리스트에 해당 레코드를 선택하면 tfId에 setText
		list.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				// 리스트의 항목들을 누르면 처리해줄 내용의 코드를 구현
				String str = list.getSelectedItem();
				StringTokenizer st = new StringTokenizer(str);
				tfName.setText(st.nextToken());
				tfId.setText(st.nextToken());

			}
		});
		//선택한 리스트 삭제 버튼
		btnDelete.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				String id = tfId.getText();
				if(list.getSelectedItem() == null) {
					JOptionPane.showMessageDialog(null, "선택된 회원이 없습니다.");
				} else {
					//삭제 여부
					Dialog deleteDial = new Dialog(frame, "삭제", true);
					deleteDial.addWindowListener(new WindowAdapter() {
						@Override
						public void windowClosing(WindowEvent e) {
							deleteDial.setVisible(false);
						}
					});
					deleteDial.setSize(280, 140);
					deleteDial.setLocation(((frame.getToolkit().getScreenSize()).width - deleteDial.getWidth())/2, ((frame.getToolkit().getScreenSize()).height - deleteDial.getHeight())/2);
					deleteDial.setLayout(new FlowLayout());
					Label deletemsg = new Label("회원 " + tfName.getText() + " 을/를 삭제할까요?");
					deletemsg.setFont(font2);
					Button btnOk = new Button("삭제");
					Button btnCancel = new Button("취소");
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
		//■★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆등록 삭제
		
	}
	// 회원정보 보기
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
	//회원정보 검색
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
//글 목록 페이지
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
		frame = new Frame("커뮤니티 관리");
		panel = new Panel[9];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new Panel();
		}
		
		lblMemInfo = new Label("전체 회원 관리");
		lblMemInfo.setFont(font1);
		lblId = new Label("ID");
		lblId.setFont(font2);
		lblName = new Label("이름");
		lblName.setFont(font2);
		lblLevel = new Label("회원등급");
		lblLevel.setFont(font2);
		lblEmail = new Label("메일주소");
		lblEmail.setFont(font2);
		lblJoinDate = new Label("가입일");
		lblJoinDate.setFont(font2);
		lblPost = new Label("게시글 수");
		lblPost.setFont(font2);
		lblComment = new Label("댓글");
		lblComment.setFont(font2);
		lblLogDate = new Label("최종 로그인 날짜");
		lblLogDate.setFont(font2);
		lblPostInfo = new Label("전체 게시글 관리");
		lblPostInfo.setFont(font1);
		lblPostNum = new Label("글번호");
		lblPostNum.setFont(font2);
		lblTitle = new Label("글제목");
		lblTitle.setFont(font2);
		lblPostDate = new Label("작성일");
		lblPostDate.setFont(font2);
		lblPostComment = new Label("댓글 수");
		lblPostComment.setFont(font2);
		lblView = new Label("조회수");
		lblView.setFont(font2);
		
		tfSearch = new TextField("");
		tfSearch.setPreferredSize(new Dimension(500,25));
		tfSearch.setFont(font2);
		tfNum = new TextField("");
		tfTitle = new TextField("");
		tfId = new TextField("");
		
		btnPostList = new Button("글목록");
		btnPostList.setPreferredSize(new Dimension(200,50));
		btnPostList.setFont(font1);
		btnMemList = new Button("회원 목록");
		btnMemList.setPreferredSize(new Dimension(200,50));
		btnMemList.setFont(font1);
		btnMemRegister = new Button("회원 등록");
		btnMemRegister.setPreferredSize(new Dimension(200,50));
		btnMemRegister.setFont(font1);
		btnAdminLogin = new Button("관리자 로그아웃");
		btnAdminLogin.setPreferredSize(new Dimension(200,50));
		btnAdminLogin.setFont(font1);
		btnSearch = new Button("검색");
		btnSearch.setPreferredSize(new Dimension(100, 30));
		btnEdit = new Button("수정");
		btnEdit.setPreferredSize(new Dimension(80,30));
		btnDelete = new Button("삭제");
		btnDelete.setPreferredSize(new Dimension(80,30));
		
		cFilter = new Choice();
		cFilter.setPreferredSize(new Dimension(150,50));
		cFilter.addItem("선택");
		cFilter.addItem("글번호");
		cFilter.addItem("글제목");
		cFilter.addItem("작성자ID");
		
		
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
				// 글목록버튼을 누르면
				frame.dispose();
				new InfoPostGUI();
				
			}
		});
		btnMemList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 회원목록버튼을 누르면

				frame.dispose();
				new InfoMemGUI();
			}
		});
		
		list.addActionListener(new ActionListener() { //더블클릭 이벤트 처리

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
				if (filter.equals("선택")) filter = "all";
				if (filter.equals("글번호")) filter = "pNum";
				if (filter.equals("글제목")) filter = "title";
				if (filter.equals("작성자ID")) filter = "id";
				
				
				
				
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 검색 버튼 구현
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
				JOptionPane.showMessageDialog(null, "관리자 권한 로그아웃 되었습니다.");
				frame.dispose();
				new NotAInfoPostGUI();
			}
			
		});
//■★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆회원 등록		
				btnMemRegister.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						frame.setVisible(false);
						new RegisterGUI();
					}
				});
//■★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆회원 등록
				//리스트에 해당 레코드를 선택하면 tfNum에 setText
				list.addItemListener(new ItemListener() {
					@Override
					public void itemStateChanged(ItemEvent e) {
						// 리스트의 항목들을 누르면
						String str = list.getSelectedItem();
						StringTokenizer st = new StringTokenizer(str);
						tfNum.setText(st.nextToken());
					}
				});
				//선택한 리스트 삭제 버튼
				btnDelete.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) { 
						String num = tfNum.getText();
						if (list.getSelectedItem() == null) { // 선택한 리스트가 없을 경우
							JOptionPane.showMessageDialog(null, "선택된 게시글이 없습니다.");
						} else { // 선택한 리스트가 있을 경우
							Dialog deleteDial = new Dialog(frame, "삭제", true); // 삭제 여부를 묻는 다이얼로그 창
							deleteDial.addWindowListener(new WindowAdapter() {
								@Override
								public void windowClosing(WindowEvent e) {
									deleteDial.setVisible(false);
								}
							});
							deleteDial.setSize(240, 140);
							deleteDial.setLocation(((frame.getToolkit().getScreenSize()).width - deleteDial.getWidth())/2, 												((frame.getToolkit().getScreenSize()).height - deleteDial.getHeight())/2);						
							deleteDial.setLayout(new FlowLayout());
							Label deletemsg = new Label("글번호 " + tfNum.getText() + " 을/를 삭제할까요?");
							deletemsg.setFont(font2);
							Button btnOk = new Button("삭제");
							Button btnCancel = new Button("취소");
							deleteDial.add(deletemsg);
							deleteDial.add(btnOk);
							btnOk.addActionListener(new ActionListener() { // 다이얼로그창의 삭제 버튼을 누르면
								@Override
								public void actionPerformed(ActionEvent e) {
									CommManageDAO dao = new CommManageDAO();
									dao.postdelete(num); // 게시글을 삭제하는 postdelete 메소드 호출
									frame.dispose(); // 기존 창 닫음
									new InfoPostGUI(); // 게시글 목록 창
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

// 회원 상세 페이지
class DetailMemGUI {
	Frame frame;
	Panel[] panel;
	Label lblMemDetail, lblName, lblId, lblLevel, lblEmail, lblJoinDate, lblPost, lblComment, lblLogDate,  
	lblPostDetail, lblPostNum, lblTitle, lblPostDate, lblView, lblContent;
	Button btnPostList,btnMemList, btnMemRegister, btnAdminLogin, btnSearch, btnEdit, btnDelete, btnBackMList, btnBackPList;
	TextField tfName, tfLevel, tfEmail, tfJoinDate, tfLogDate; 
	List listId, listPost, listMemComment;
	

	public DetailMemGUI() {
		frame = new Frame("커뮤니티 관리");
		panel = new Panel[20];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new Panel();
		}
		Font font1 = new Font("Default", Font.BOLD, 17);
		Font font2 = new Font("Default", Font.PLAIN, 15);
		lblMemDetail = new Label("상세 정보 페이지");
		lblMemDetail.setFont(font1);
		lblId = new Label("ID");
		lblId.setFont(font2);
		lblName = new Label("이름");
		lblName.setFont(font2);
		lblLevel = new Label("회원등급");
		lblLevel.setFont(font2);
		lblEmail = new Label("메일주소");
		lblEmail.setFont(font2);
		lblJoinDate = new Label("가입일");
		lblJoinDate.setFont(font2);
		lblPost = new Label("게시글 수");
		lblPost.setFont(font2);
		lblComment = new Label("댓글 수");
		lblComment.setFont(font2);
		lblLogDate = new Label("최종 로그인 날짜");
		lblLogDate.setFont(font2);
		lblPostDetail = new Label("게시글 페이지");
		lblPostDetail.setFont(font1);
		lblPostNum = new Label("글번호");
		lblPostNum.setFont(font2);
		lblTitle = new Label("글제목");
		lblTitle.setFont(font2);
		lblPostDate = new Label("작성일");
		lblPostDate.setFont(font2);
		lblView = new Label("조회수");
		lblView.setFont(font2);


		btnPostList = new Button("글목록");
		btnPostList.setPreferredSize(new Dimension(200,50));
		btnPostList.setFont(font1);
		btnMemList = new Button("회원 목록");
		btnMemList.setPreferredSize(new Dimension(200,50));
		btnMemList.setFont(font1);
		btnMemRegister = new Button("회원 등록");
		btnMemRegister.setPreferredSize(new Dimension(200,50));
		btnMemRegister.setFont(font1);
		btnAdminLogin = new Button("관리자 로그아웃");
		btnAdminLogin.setPreferredSize(new Dimension(200,50));
		btnAdminLogin.setFont(font1);
		btnSearch = new Button("검색");
		btnSearch.setPreferredSize(new Dimension(100, 30));
		btnEdit = new Button("수정");
		btnEdit.setPreferredSize(new Dimension(80,30));
		btnDelete = new Button("삭제");
		btnDelete.setPreferredSize(new Dimension(80,30));
		btnBackMList = new Button("목록");
		btnBackMList.setPreferredSize(new Dimension(80,30));
		btnBackPList = new Button("목록");
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
		

		//상단메뉴 버튼 기능
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
				JOptionPane.showMessageDialog(null, "관리자 권한 로그아웃 되었습니다.");
				frame.dispose();
				new NotAInfoMemGUI();
			}
		});
		
		//하단
		//수정 버튼
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] str = listId.getItems();
				String id = str[0];
				String name = tfName.getText(); //텍스트 필드에 입력한 것은 모두 문자열 처리됨
				String level = tfLevel.getText();
				String email = tfEmail.getText();
				String joinDate = tfJoinDate.getText();
				String logDate = tfLogDate.getText();
				
				CommManageDAO dao = new CommManageDAO();
				dao.update(id, name, level, email, joinDate, logDate); // 삽입해줄 쿼리문이 있는 메소드로 호출
			
				
			}
		});
		
		btnBackMList.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				new InfoMemGUI();
			}
		});
//■★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆등록 삭제		
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
						//삭제 여부를 묻는 다이얼로그 창
						Dialog deleteDial = new Dialog(frame, "삭제", true);
						deleteDial.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosing(WindowEvent e) {
								deleteDial.setVisible(false);
							}
						});
						deleteDial.setSize(280, 140);
						deleteDial.setLocation(((frame.getToolkit().getScreenSize()).width - deleteDial.getWidth())/2, 											  												((frame.getToolkit().getScreenSize()).height - deleteDial.getHeight())/2);						
						deleteDial.setLayout(new FlowLayout());
						Label deletemsg = new Label("회원 " + tfName.getText() + " 을/를 삭제할까요?");
						deletemsg.setFont(font2);
						Button btnOk = new Button("삭제");
						Button btnCancel = new Button("취소");
						deleteDial.add(deletemsg);
						deleteDial.add(btnOk);
						btnOk.addActionListener(new ActionListener() { // 다이얼로그 창에서 삭제 버튼을 누르면
							@Override
							public void actionPerformed(ActionEvent e) {
								CommManageDAO dao = new CommManageDAO();
								dao.memdelete(id); //회원을 삭제하는 memdelete 메소드 호출
								frame.dispose(); //삭제된 회원의 상세페이지는 닫힘
								new InfoMemGUI(); //회원 목록 페이지
							}
						});
						deleteDial.add(btnCancel); 
						btnCancel.addActionListener(new ActionListener() {// 다이얼로그 창에서 취소 버튼을 누르면
							
							@Override
							public void actionPerformed(ActionEvent e) {
								deleteDial.setVisible(false); // 다이얼로그 창만 닫음
							}
						});
						deleteDial.setVisible(true);
					}
				});
//■★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆등록 삭제
	}
	
}

//게시글 페이지
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
		frame = new Frame("커뮤니티 관리");
		panel = new Panel[25];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new Panel();
		}
		Font font1 = new Font("Default", Font.BOLD, 17);
		Font font2 = new Font("Default", Font.PLAIN, 15);
		Font font3 = new Font("Default", Font.BOLD, 15);
		lblMemDetail = new Label("상세 정보 페이지");
		lblMemDetail.setFont(font1);
		lblId = new Label("ID");
		lblId.setFont(font2);
		lblName = new Label("이름");
		lblName.setFont(font2);
		lblLevel = new Label("회원등급");
		lblLevel.setFont(font2);
		lblEmail = new Label("메일주소");
		lblEmail.setFont(font2);
		lblJoinDate = new Label("가입일");
		lblJoinDate.setFont(font2);
		lblPost = new Label("게시글 수");
		lblPost.setFont(font2);
		lblComment = new Label("댓글 수");
		lblComment.setFont(font2);
		lblLogDate = new Label("최종 로그인 날짜");
		lblLogDate.setFont(font2);
		lblPostDetail = new Label("게시글 페이지");
		lblPostDetail.setFont(font1);
		lblPostNum = new Label("글번호");
		lblPostNum.setFont(font3);
		lblTitle = new Label("글제목");
		lblTitle.setFont(font3);
		lblPostDate = new Label("작성일");
		lblPostDate.setFont(font3);
		lblView = new Label("조회수");
		lblView.setFont(font3);
		lblContent = new Label("글 내용");
		lblContent.setFont(font3);
		lblPostComment = new Label("댓글 수");
		lblPostComment.setFont(font3);
		lblCommId = new Label("댓글 작성자");
		lblCommId.setFont(font3);
		lblCommDate = new Label("댓글 작성일");
		lblCommDate.setFont(font3);
		lblCommContent = new Label("댓글 내용");
		lblCommContent.setFont(font3);
		
		

		tfNum = new TextField();

		btnPostList = new Button("글목록");
		btnPostList.setPreferredSize(new Dimension(200,50));
		btnPostList.setFont(font1);
		btnMemList = new Button("회원 목록");
		btnMemList.setPreferredSize(new Dimension(200,50));
		btnMemList.setFont(font1);
		btnMemRegister = new Button("회원 등록");
		btnMemRegister.setPreferredSize(new Dimension(200,50));
		btnMemRegister.setFont(font1);
		btnAdminLogin = new Button("관리자 로그아웃");
		btnAdminLogin.setPreferredSize(new Dimension(200,50));
		btnAdminLogin.setFont(font1);
		btnSearch = new Button("검색");
		btnSearch.setPreferredSize(new Dimension(100, 30));
		btnEdit = new Button("수정");
		btnEdit.setPreferredSize(new Dimension(80,30));
		btnDelete = new Button("삭제");
		btnDelete.setPreferredSize(new Dimension(80,30));
		btnBackMList = new Button("목록");
		btnBackMList.setPreferredSize(new Dimension(80,30));
		btnBackPList = new Button("목록");
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
		
		
		//상단메뉴 버튼 기능
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
				JOptionPane.showMessageDialog(null, "관리자 권한 로그아웃 되었습니다.");
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
		
//■★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆회원 등록		
				btnMemRegister.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) {
				
						new RegisterGUI();
					}
				});
//■★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆회원 등록	
				//선택한 리스트 삭제 버튼
				btnDelete.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) 
					{ // 삭제하기 버튼 누름
						Dialog deleteDial = new Dialog(frame, "삭제", true); // 삭제 여부를 묻는 다이얼로그 창
						deleteDial.addWindowListener(new WindowAdapter() {
							@Override
							public void windowClosing(WindowEvent e) {
								deleteDial.setVisible(false);
							}
						});
						deleteDial.setSize(260, 140);
						deleteDial.setLocation(((frame.getToolkit().getScreenSize()).width - deleteDial.getWidth())/2, 											((frame.getToolkit().getScreenSize()).height - deleteDial.getHeight())/2);						
						deleteDial.setLayout(new FlowLayout());
						// 게시글 목록에서 더블클릭해서 상세 페이지로 전환할 때 해당 게시글 번호를 담은 전역변수 dpnum
						Label deletemsg = new Label("글번호 " + DPNUM.dpnum + "을/를 삭제할까요?");
						deletemsg.setFont(font2);
						Button btnOk = new Button("삭제");
						Button btnCancel = new Button("취소");
						deleteDial.add(deletemsg);
						deleteDial.add(btnOk);
						btnOk.addActionListener(new ActionListener() { // 다이얼로그 창에서 삭제 버튼을 눌렀을 때 
							@Override
							public void actionPerformed(ActionEvent e) {
								CommManageDAO dao = new CommManageDAO();
								dao.postdelete(DPNUM.dpnum); // 게시글을 삭제하는 postdelete 메소드에 게시글번호를 매개변수로 호출
								frame.dispose(); // 삭제된 게시글의 상세페이지는 닫힘
								new InfoPostGUI(); // 게시글 목록 창
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
//관리자 권한 없는 회원 목록 페이지
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
		NAMframe = new Frame("커뮤니티 관리");
		panel = new Panel[9];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new Panel();
		}
		
		lblMemInfo = new Label("전체 회원 관리");
		lblMemInfo.setFont(font1);
		lblId = new Label("ID");
		lblId.setFont(font2);
		lblName = new Label("이름");
		lblName.setFont(font2);
		lblLevel = new Label("회원등급");
		lblLevel.setFont(font2);
		lblEmail = new Label("메일주소");
		lblEmail.setFont(font2);
		lblJoinDate = new Label("가입일");
		lblJoinDate.setFont(font2);
		lblPost = new Label("게시글 수");
		lblPost.setFont(font2);
		lblMemComment = new Label("댓글 수"); //회원정보 댓글 수
		lblMemComment.setFont(font2);
		lblLogDate = new Label("최종 로그인 날짜");
		lblLogDate.setFont(font2);
		lblPostInfo = new Label("전체 게시글 관리");
		lblPostInfo.setFont(font1);
		lblPostNum = new Label("글번호");
		lblPostNum.setFont(font2);
		lblTitle = new Label("글제목");
		lblTitle.setFont(font2);
		lblPostDate = new Label("작성일");
		lblPostDate.setFont(font2);
		lblView = new Label("조회수");
		lblView.setFont(font2);
		lblPostComment = new Label("댓글 수"); //글정보 댓글 수 
		lblPostComment.setFont(font2);
		
		tfSearch = new TextField("");
		tfName = new TextField("");
		tfId = new TextField("");
		tfSearch.setPreferredSize(new Dimension(500,25));
		tfSearch.setFont(font2);
		
		btnPostList = new Button("글목록");
		btnPostList.setPreferredSize(new Dimension(200,50));
		btnPostList.setFont(font1);
		btnMemList = new Button("회원 목록");
		btnMemList.setPreferredSize(new Dimension(200,50));
		btnMemList.setFont(font1);
		btnMemRegister = new Button("회원 등록");
		btnMemRegister.setPreferredSize(new Dimension(200,50));
		btnMemRegister.setFont(font1);
		btnAdminLogin = new Button("관리자 로그인");
		btnAdminLogin.setPreferredSize(new Dimension(200,50));
		btnAdminLogin.setFont(font1);
		btnSearch = new Button("검색");
		btnSearch.setPreferredSize(new Dimension(100, 30));
		btnEdit = new Button("수정");
		btnEdit.setPreferredSize(new Dimension(80,30));
		btnDelete = new Button("삭제");
		btnDelete.setPreferredSize(new Dimension(80,30));
		
		cFilter = new Choice();
		cFilter.setPreferredSize(new Dimension(150,50));
		cFilter.add("선택");
		cFilter.addItem("ID");
		cFilter.addItem("등급");
		cFilter.addItem("이름");
		cFilter.addItem("메일주소");
		
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
				JOptionPane.showMessageDialog(null, "관리자 로그인이 필요합니다.");
				
			}
		});
		btnDelete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "관리자 로그인이 필요합니다.");
				
			}
		});

		
		cFilter.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
		
				filter = (String) e.getItem();
				if (filter.equals("선택")) filter = "all"; //선택 설정하고 검색하면 전체검색
				if (filter.equals("ID")) filter = "id";
				if (filter.equals("이름")) filter = "name";
				if (filter.equals("등급")) filter = "level";
				if (filter.equals("메일주소")) filter = "email";
				
				
				
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 검색 버튼 구현
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
		// 회원정보 보기
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
	//회원정보 검색
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
// 관리자 권한 없는 글 목록 화면
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
		NAPframe = new Frame("커뮤니티 관리");
		panel = new Panel[9];
		for (int i = 0; i < panel.length; i++) {
			panel[i] = new Panel();
		}
		
		lblMemInfo = new Label("전체 회원 관리");
		lblMemInfo.setFont(font1);
		lblId = new Label("ID");
		lblId.setFont(font2);
		lblName = new Label("이름");
		lblName.setFont(font2);
		lblLevel = new Label("회원등급");
		lblLevel.setFont(font2);
		lblEmail = new Label("메일주소");
		lblEmail.setFont(font2);
		lblJoinDate = new Label("가입일");
		lblJoinDate.setFont(font2);
		lblPost = new Label("게시글 수");
		lblPost.setFont(font2);
		lblComment = new Label("댓글");
		lblComment.setFont(font2);
		lblLogDate = new Label("최종 로그인 날짜");
		lblLogDate.setFont(font2);
		lblPostInfo = new Label("전체 게시글 관리");
		lblPostInfo.setFont(font1);
		lblPostNum = new Label("글번호");
		lblPostNum.setFont(font2);
		lblTitle = new Label("글제목");
		lblTitle.setFont(font2);
		lblPostDate = new Label("작성일");
		lblPostDate.setFont(font2);
		lblPostComment = new Label("댓글 수");
		lblPostComment.setFont(font2);
		lblView = new Label("조회수");
		lblView.setFont(font2);
		
		tfSearch = new TextField("");
		tfSearch.setPreferredSize(new Dimension(500,25));
		tfSearch.setFont(font2);
		tfNum = new TextField("");
		tfTitle = new TextField("");
		tfId = new TextField("");
		
		btnPostList = new Button("글목록");
		btnPostList.setPreferredSize(new Dimension(200,50));
		btnPostList.setFont(font1);
		btnMemList = new Button("회원 목록");
		btnMemList.setPreferredSize(new Dimension(200,50));
		btnMemList.setFont(font1);
		btnMemRegister = new Button("회원 등록");
		btnMemRegister.setPreferredSize(new Dimension(200,50));
		btnMemRegister.setFont(font1);
		btnAdminLogin = new Button("관리자 로그인");
		btnAdminLogin.setPreferredSize(new Dimension(200,50));
		btnAdminLogin.setFont(font1);
		btnSearch = new Button("검색");
		btnSearch.setPreferredSize(new Dimension(100, 30));
		btnEdit = new Button("수정");
		btnEdit.setPreferredSize(new Dimension(80,30));
		btnDelete = new Button("삭제");
		btnDelete.setPreferredSize(new Dimension(80,30));
		
		cFilter = new Choice();
		cFilter.setPreferredSize(new Dimension(150,50));
		cFilter.addItem("선택");
		cFilter.addItem("글번호");
		cFilter.addItem("글제목");
		cFilter.addItem("작성자ID");
		
		
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
				// 글목록버튼을 누르면
				NAPframe.dispose();
				new NotAInfoPostGUI();
				
			}
		});
		btnMemList.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 회원목록버튼을 누르면

				NAPframe.dispose();
				new NotAInfoMemGUI();
			}
		});
		btnMemRegister.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "관리자 로그인이 필요합니다.");

			}
		});
		btnDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "관리자 로그인이 필요합니다.");

			}
		});


		cFilter.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {

				filter = (String) e.getItem();
				if (filter.equals("선택")) filter = "all";
				if (filter.equals("글번호")) filter = "pNum";
				if (filter.equals("글제목")) filter = "title";
				if (filter.equals("작성자ID")) filter = "id";

				
				
				
			}
		});
		
		btnSearch.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// 검색 버튼 구현
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

//관리자 로그인 창
class LoginGUI extends Frame {
	Frame frame;
	Panel[] panel;
	Label lblID, lblPassWD;
	TextField tfID, tfPassWD;
	Button btnLogin;
	
	
	public LoginGUI() {
		frame = new Frame("관리자 로그인");
		panel = new Panel[6];
		for(int i=0; i<panel.length; i++) {
			panel[i] = new Panel();
		}
		lblID = new Label("아이디");
		lblPassWD = new Label("비밀번호");
		tfID = new TextField("");
		tfPassWD = new TextField("");
		tfPassWD.setEchoChar('*'); // 비밀번호가 입력되는 텍스트 필드의 문자를 *로 변환
		btnLogin = new Button("로그인");
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
				// 버튼을 누르면 수행해줄 코드를 구현
				String id = tfID.getText();
				String PassWD = tfPassWD.getText(); //tfAge에 입력한 것은 모두 문자열 처리됨
				
				CommManageDAO dao = new CommManageDAO();
				// 삽입해줄 쿼리문이 있는 login메소드 호출
				int x = dao.login(id, PassWD); // 로그인에 성공하면 1을 반환, 그 값을 x에 저장
				if(x == 1) { // 로그인에 성공하면
					frame.dispose(); // 1.로그인 창을 닫음
					// 2. 로그인 하려는 창을 닫음
					if(DPNUM.frameDivision == 1 ) NotAInfoMemGUI.NAMframe.dispose(); 
					if(DPNUM.frameDivision == 2 ) NotAInfoPostGUI.NAPframe.dispose();
					new InfoMemGUI(); // 3. 로그인된 화면을 불러옴
				}
			}
		});
	}
}

//■★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆회원 등록
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
		
		frame = new Frame("회원 등록");
		panel = new Panel[9];
		for(int i=0; i<panel.length; i++)
		{
			panel[i] = new Panel();
		}
		lblId = new Label("이름");
		lblName = new Label("아이디");
		lblLevel = new Label("회원 등급");
		lblEmail = new Label("메일주소");
		tfId = new TextField("");
		tfName = new TextField("");
		tfLevel = new TextField("");
		tfEmail = new TextField("");

		btnSave = new Button("저장");
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
//■★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆회원 등록
