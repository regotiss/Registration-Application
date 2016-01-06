//By Sujata Regoti
//Date: 16 July 2015
package registr;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.swing.UIManager;

import com.toedter.calendar.JDateChooser;

public class Registration extends JPanel{

	
	private static Registration yuva;
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private static int w;
	private static int h;
	private JTextField tfName;
	public JTextField getTfName() {
		return tfName;
	}
	public void setTfName(JTextField tfName) {
		this.tfName = tfName;
	}
	private Statement s;

	private JTextField tfAge;
	private JTextField tfBloodG;
	private JTextArea taAddr;
	private JTextField tfMob;
	private JTextField tfEmail;
	private JTextField tfFb;
	private JTextField tfTwitter;
	private JPanel panel_1;
	private JTextField tfUniv;
	private JRadioButton rdbtnYes;
	private JRadioButton rdbtnNo;
	private JTextField tfInsti;
	private JTextField tfYrG;
	private JTextField tfOcc;
	private JTextField tfTel;
	private JTextField tfEdu;
	private JTextField tfDistrict;
	private JTextField tfTaluka;
	private JTextArea taAddrJ;
	private JTextField tfCity;
	private JTextField tfVoterG;
	private JTextField tfVoterId;
	private JTextField tfSection;
	private JTextField tfRegi;
	public JTextField getTfRegi() {
		return tfRegi;
	}
	public void setTfRegi(JTextField tfRegi) {
		this.tfRegi = tfRegi;
	}
	private JLabel photolbl;
	private JSplitPane jsp;
	private JPanel panelPhoto;
	protected JFileChooser fc;
	protected File file;
	private JDateChooser tfDob;
	private JButton btnClose;
	private JButton btnClear;
	private JButton btnDelete;
	private JButton btnSearch;
	private JButton btnModify;
	private JButton btnSubmit;
	private JButton btnGetReport;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					setNimbus();
					create();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static void setNimbus() {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Registration.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Registration.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Registration.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Registration.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		
	}
	
	protected static void create() {
		
		frame = new JFrame("YuvaSena Application Form");
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment()
				.getDefaultScreenDevice();
		w = gd.getDisplayMode().getWidth();
		h = gd.getDisplayMode().getHeight();
	
		frame.setBounds(0, 0,w,h);
		//frame.setIconImage(new ImageIcon(YuvaSena.class.getResource("/images/flg1.png")).getImage());
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		yuva=new Registration();
		frame.getContentPane().add(yuva);
		frame.setVisible(true);
	}
	
	
	public Registration() {
		
		ConnectToDatabase.main(null);
		s=ConnectToDatabase.getS();
	
		
		setBorder(UIManager.getBorder("TitledBorder.border"));
		setBackground(new Color(255, 204, 0));
		setForeground(Color.BLACK);
		setLayout(null);
		
		
		setSize(1350,700);
		
		int lim1=2014,lim2=2016;
		Calendar cal=Calendar.getInstance();
		if(cal.get(Calendar.YEAR) >lim2)
		{
			lim1=cal.get(Calendar.YEAR);
			lim2=cal.get(Calendar.YEAR)+2;
		}
		JLabel lblYear = new JLabel("Year : "+lim1+"-"+lim2);
		lblYear.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lblYear.setBounds(863, 441, 148, 20);
		add(lblYear);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 204, 0));
		panel.setBorder(new TitledBorder(null, "Personal Details", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel.setBounds(20, 78, 808, 616);
		add(panel);
		panel.setLayout(null);
		
		JLabel lblFullName = new JLabel("Full Name :");
		lblFullName.setBounds(53, 39, 95, 20);
		panel.add(lblFullName);
		lblFullName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		
		tfName = new JTextField();
		tfName.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setDetails();
				
			}
		});
		tfName.setBounds(164, 36, 625, 28);
		panel.add(tfName);
		tfName.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date Of Birth :");
		lblDateOfBirth.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		lblDateOfBirth.setBounds(45, 76, 103, 16);
		panel.add(lblDateOfBirth);
		
		tfDob = new JDateChooser();
		tfDob.setDate((Calendar.getInstance()).getTime());
		tfDob.setDateFormatString("dd/MM/yyyy");
		tfDob.setBounds(164, 71, 169, 28);
		panel.add(tfDob);

		
		JLabel lblAge = new JLabel("Age :");
		lblAge.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lblAge.setBounds(345, 74, 47, 20);
		panel.add(lblAge);
		
		tfAge = new JTextField();
		tfAge.setBounds(392, 71, 122, 28);
		panel.add(tfAge);
		tfAge.setColumns(10);
		new SimpleDateFormat("dd-MM-YYYY");
		
		
		
		JLabel lblBloodGroup = new JLabel("Blood Group :");
		lblBloodGroup.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 16));
		lblBloodGroup.setBounds(542, 74, 113, 20);
		panel.add(lblBloodGroup);
		
		tfBloodG = new JTextField();
		tfBloodG.setColumns(10);
		tfBloodG.setBounds(667, 71, 122, 28);
		panel.add(tfBloodG);
		
		JLabel lblPermenantAddr = new JLabel("Permenant Addr  :");
		lblPermenantAddr.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		lblPermenantAddr.setBounds(20, 107, 133, 20);
		panel.add(lblPermenantAddr);
		
		taAddr = new JTextArea();
		
		JScrollPane jaddr=new JScrollPane(taAddr);
		jaddr.setBounds(164, 104, 625, 58);
		panel.add(jaddr);
		
		JLabel lblTelephonemobileNo = new JLabel("Telephone/Mobile No :");
		lblTelephonemobileNo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		lblTelephonemobileNo.setBounds(6, 172, 150, 20);
		panel.add(lblTelephonemobileNo);
		
		tfMob = new JTextField();
		tfMob.setBounds(164, 169, 179, 28);
		panel.add(tfMob);
		tfMob.setColumns(10);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 204, 0));
		panel_2.setBounds(20, 319, 777, 60);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setBounds(96, 3, 61, 20);
		panel_2.add(lblEmail);
		lblEmail.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		
		tfEmail = new JTextField();
		tfEmail.setBounds(153, 0, 624, 28);
		panel_2.add(tfEmail);
		tfEmail.setColumns(10);
		
		JLabel lblSocialNetworkingSite = new JLabel("Social Networking Site ");
		lblSocialNetworkingSite.setBounds(0, 35, 157, 20);
		panel_2.add(lblSocialNetworkingSite);
		lblSocialNetworkingSite.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		
		tfFb = new JTextField();
		tfFb.setBounds(192, 32, 266, 28);
		panel_2.add(tfFb);
		tfFb.setColumns(10);
		
		tfTwitter = new JTextField();
		tfTwitter.setBounds(490, 32, 287, 28);
		panel_2.add(tfTwitter);
		tfTwitter.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(156, 31, 24, 24);
		panel_2.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(Registration.class.getResource("/images/fb.png")));
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(464, 36, 24, 24);
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon(Registration.class.getResource("/images/twitter.png")));
		
		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 204, 0));
		panel_1.setBounds(20, 230, 769, 63);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		tfUniv = new JTextField();
		tfUniv.setBounds(518, 0, 255, 28);
		panel_1.add(tfUniv);
		tfUniv.setColumns(10);
		
		JLabel lblGraduationCertificate = new JLabel("Graduation Certificate :");
		lblGraduationCertificate.setBounds(32, 43, 171, 20);
		panel_1.add(lblGraduationCertificate);
		lblGraduationCertificate.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		
		rdbtnYes = new JRadioButton("Yes");
		rdbtnYes.setBounds(215, 44, 53, 18);
		panel_1.add(rdbtnYes);
		rdbtnYes.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		
		rdbtnNo = new JRadioButton("No");
		rdbtnNo.setBounds(293, 44, 62, 18);
		panel_1.add(rdbtnNo);
		rdbtnNo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));

		ButtonGroup bg=new ButtonGroup();
		bg.add(rdbtnYes);
		bg.add(rdbtnNo);
		
		JLabel lblUniversity = new JLabel("University :");
		lblUniversity.setBounds(436, 3, 83, 20);
		panel_1.add(lblUniversity);
		lblUniversity.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		
		JLabel lblCollegeName = new JLabel("Institute Name :");
		lblCollegeName.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		lblCollegeName.setBounds(32, 3, 111, 20);
		panel_1.add(lblCollegeName);
		
		tfInsti = new JTextField();
		tfInsti.setColumns(10);
		tfInsti.setBounds(144, 0, 280, 28);
		panel_1.add(tfInsti);
		
		JLabel lblYearOfGraduation = new JLabel("Year of Graduation :");
		lblYearOfGraduation.setBounds(379, 40, 150, 20);
		panel_1.add(lblYearOfGraduation);
		lblYearOfGraduation.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		
		tfYrG = new JTextField();
		tfYrG.setBounds(518, 35, 255, 28);
		panel_1.add(tfYrG);
		tfYrG.setColumns(10);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 204, 0));
		panel_3.setBounds(53, 400, 744, 95);
		panel.add(panel_3);
		panel_3.setLayout(null);
		
		
		taAddrJ = new JTextArea();
		JScrollPane jobaddr=new JScrollPane(taAddrJ);
		jobaddr.setBounds(119, 42, 625, 53);
		panel_3.add(jobaddr);
		
		JLabel lblAddressOfJob = new JLabel("Address of Job");
		lblAddressOfJob.setBounds(0, 45, 122, 20);
		panel_3.add(lblAddressOfJob);
		lblAddressOfJob.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		
		JLabel lblOccupation = new JLabel("Occupation :");
		lblOccupation.setBounds(26, 0, 91, 20);
		panel_3.add(lblOccupation);
		lblOccupation.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		
		tfOcc = new JTextField();
		tfOcc.setBounds(119, 0, 197, 28);
		panel_3.add(tfOcc);
		
		JLabel lblTelephoneNooffice = new JLabel(" Telephone No(Office):");
		lblTelephoneNooffice.setBounds(347, 0, 162, 20);
		panel_3.add(lblTelephoneNooffice);
		lblTelephoneNooffice.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		
		tfTel = new JTextField();
		tfTel.setBounds(503, 0, 241, 28);
		panel_3.add(tfTel);
		tfTel.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Office :");
		lblNewLabel_2.setBounds(48, 66, 55, 16);
		panel_3.add(lblNewLabel_2);
		lblNewLabel_2.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		
		tfEdu = new JTextField();
		tfEdu.setBounds(555, 169, 234, 28);
		panel.add(tfEdu);
		tfEdu.setColumns(10);
		
		JLabel lblEducation = new JLabel("Education :");
		lblEducation.setBounds(469, 174, 83, 20);
		panel.add(lblEducation);
		lblEducation.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 204, 0));
		panel_4.setBounds(20, 515, 769, 75);
		panel.add(panel_4);
		panel_4.setLayout(null);
		
		JLabel lblDistinct = new JLabel("District  :");
		lblDistinct.setBounds(79, 13, 73, 20);
		panel_4.add(lblDistinct);
		lblDistinct.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		
		tfDistrict = new JTextField();
		tfDistrict.setBounds(150, 10, 173, 28);
		panel_4.add(tfDistrict);
		tfDistrict.setColumns(10);
		
		JLabel lblTaluka = new JLabel("Taluka :");
		lblTaluka.setBounds(338, 13, 73, 20);
		panel_4.add(lblTaluka);
		lblTaluka.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		
		tfTaluka = new JTextField();
		tfTaluka.setBounds(405, 10, 179, 28);
		panel_4.add(tfTaluka);
		tfTaluka.setColumns(10);
		
		JLabel lblCity = new JLabel("City :");
		lblCity.setBounds(609, 13, 40, 20);
		panel_4.add(lblCity);
		lblCity.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		
		tfCity = new JTextField();
		tfCity.setBounds(647, 10, 122, 28);
		panel_4.add(tfCity);
		tfCity.setColumns(10);
		
		JLabel lblVotingListGroup = new JLabel("Voting List Group No :");
		lblVotingListGroup.setBounds(0, 50, 156, 20);
		panel_4.add(lblVotingListGroup);
		lblVotingListGroup.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		
		tfVoterG = new JTextField();
		tfVoterG.setBounds(150, 47, 173, 28);
		panel_4.add(tfVoterG);
		tfVoterG.setColumns(10);
		
		JLabel lblVoterId = new JLabel("Voter ID :");
		lblVoterId.setBounds(338, 50, 73, 20);
		panel_4.add(lblVoterId);
		lblVoterId.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		
		tfVoterId = new JTextField();
		tfVoterId.setBounds(405, 47, 179, 28);
		panel_4.add(tfVoterId);
		tfVoterId.setColumns(10);
		
		JLabel lblSection = new JLabel("Section :");
		lblSection.setBounds(585, 50, 83, 20);
		panel_4.add(lblSection);
		lblSection.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 14));
		
		tfSection = new JTextField();
		tfSection.setBounds(647, 47, 122, 28);
		panel_4.add(tfSection);
		tfSection.setColumns(10);
		
				
		
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(Registration.class.getResource("/images/registration2.png")));
		lblNewLabel_4.setBounds(915, 18, 305, 118);
		add(lblNewLabel_4);
		
		JLabel lblRegistrationNo = new JLabel("Registration No :");
		lblRegistrationNo.setForeground(new Color(204, 0, 0));
		lblRegistrationNo.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 20));
		lblRegistrationNo.setBounds(62, 41, 173, 25);
		add(lblRegistrationNo);

		tfRegi = new JTextField();
		tfRegi.setBounds(230, 36, 159, 28);
		add(tfRegi);
		tfRegi.setColumns(10);
		tfRegi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				showDetails();		
			}
		});
		
		JPanel panel_5 = new JPanel();
		panel_5.setBounds(853, 149, 471, 271);
		add(panel_5);
		panel_5.setLayout(new BorderLayout());
				
				JPanel panelButtons = new JPanel();
				panelButtons.setBorder(UIManager.getBorder("TitledBorder.border"));
				panelButtons.setBackground(Color.ORANGE);
				panelButtons.setPreferredSize(new Dimension(150,271));
				//panelButtons.setBounds(286, 42, 139, 119);
				//panel_5.add(panelButtons);
				panelButtons.setLayout(null);
		
				btnSubmit = new JButton("Save");
				btnSubmit.setIcon(new ImageIcon(Registration.class.getResource("/images/save.png")));
				btnSubmit.setBounds(38, 6, 125, 42);
				panelButtons.add(btnSubmit);
				btnSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						save();
					}
				});
				btnSubmit.setMnemonic('s');
				btnSubmit.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
				
				panelPhoto = new JPanel();
				panelPhoto.setBorder(UIManager.getBorder("TitledBorder.border"));
				panelPhoto.setBackground(Color.ORANGE);
				panelPhoto.setPreferredSize(new Dimension(250,271));
				//panel_5.add(panelPhoto);
				panelPhoto.setLayout(null);
				
				jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,panelPhoto,panelButtons);
				
				btnClear = new JButton("Clear");
				btnClear.setIcon(new ImageIcon(Registration.class.getResource("/images/Reset.png")));
				btnClear.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						clear();
					}
				});
				btnClear.setMnemonic('c');
				btnClear.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
				btnClear.setBounds(38, 174, 125, 42);
				panelButtons.add(btnClear);
				
				btnModify = new JButton("Update");
				btnModify.setIcon(new ImageIcon(Registration.class.getResource("/images/edit.png")));
				btnModify.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
				btnModify.setBounds(38, 48, 125, 42);
				panelButtons.add(btnModify);
				btnModify.setMnemonic('m');
				btnModify.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						if(!check())
						{
							JOptionPane.showMessageDialog(null,"Enter Registration Number and Other Details","Error",JOptionPane.WARNING_MESSAGE);
							return;
						}
						int res=JOptionPane.showConfirmDialog(null, "Are you sure to update record?");
						if(res!=1&&res!=2)
						{
							update();
							
						}
						
					}
				});
				
				btnSearch = new JButton("Search");
				btnSearch.setIcon(new ImageIcon(Registration.class.getResource("/images/edit_find.png")));
				btnSearch.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
				btnSearch.setBounds(38, 90, 125, 42);
				panelButtons.add(btnSearch);
				btnSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						
						clear();
						SearchPane.create(yuva);
	
					}
				});
				btnSearch.setMnemonic('e');
				
				btnClose = new JButton("Close");
				btnClose.setIcon(new ImageIcon(Registration.class.getResource("/images/close.png")));
				btnClose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							ConnectToDatabase.getCon().close();
							frame.setVisible(false);
						} catch (SQLException e1) {
						
							e1.printStackTrace();
						}
					}
				});
				btnClose.setMnemonic('l');
				btnClose.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
				btnClose.setBounds(38, 221, 125, 42);
				panelButtons.add(btnClose);
				
				btnDelete = new JButton("Delete");
				btnDelete.setIcon(new ImageIcon(Registration.class.getResource("/images/gnome_edit_delete.png")));
				btnDelete.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						delete();
					}
				});
				btnDelete.setMnemonic('d');
				btnDelete.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 17));
				btnDelete.setBounds(38, 132, 125, 42);
				panelButtons.add(btnDelete);
				
				
				//jsp.setBounds(39, 38, 450, 250);
				panel_5.add(jsp);
				photolbl = new JLabel("");
				photolbl.setBounds(54, 17, 148, 161);
				panelPhoto.add(photolbl);
				photolbl.setBorder(UIManager.getBorder("TitledBorder.border"));
				photolbl.setIcon(new ImageIcon(Registration.class.getResource("/images/photo.png")));
				
				fc = new JFileChooser();
				fc.addChoosableFileFilter(new ImageFilter());
		            fc.setAcceptAllFileFilterUsed(false);

			    //Add custom icons for file types.
		            fc.setFileView(new ImageFileView());

			    //Add the preview pane.
		            fc.setAccessory(new ImagePreview(fc));
				JButton btnBrowse = new JButton("Browse");
				btnBrowse.addActionListener(new ActionListener() {
				

					public void actionPerformed(ActionEvent e) {
						int returnVal = fc.showOpenDialog(Registration.this);
						if (returnVal == JFileChooser.APPROVE_OPTION) {
			                file = fc.getSelectedFile();
			                photolbl.setIcon(new ImageIcon(file.getAbsolutePath()));
						}
					}
				});
				btnBrowse.setBounds(87, 205, 90, 28);
				panelPhoto.add(btnBrowse);
				
				btnGetReport = new JButton("Get Report");
				btnGetReport.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Display.create();
					}
				});
				btnGetReport.setFont(new Font("SansSerif", Font.BOLD, 14));
				btnGetReport.setBounds(840, 638, 112, 44);
				add(btnGetReport);
				
				
				tfDob.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
					
					@Override
					public void propertyChange(PropertyChangeEvent evt) {
						
						Calendar cal=Calendar.getInstance();
						int curryear=cal.get(Calendar.YEAR);
						if(tfDob.getDate()!=null)
						cal.setTime(tfDob.getDate());
						int year=cal.get(Calendar.YEAR);
						
						int age=curryear-year;
						if(age>0)
						tfAge.setText(""+age);
					
					}
				});
		
	}
	

	public void showDetails() {
		
		
		try {
			s.execute("select * from PerSonalDetails where ID="+tfRegi.getText());
			ResultSet rs=s.getResultSet();
			showDetails(rs); 
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	}
	public void setDetails() {
		try{
			ResultSet rs=s.executeQuery("select * from PerSonalDetails where PersonName='"+tfName.getText()+"'");
			showDetails(rs);
		
		}catch(Exception e){}

		
	}
	
	
	
	public void showDetails(ResultSet rs) {
		try {
			
			boolean b=true;
			while(rs.next()){
				
				b=false;
				tfRegi.setText(rs.getString("ID"));
				tfName.setText(rs.getString("PersonName"));
				tfDob.getDateEditor().setDate(rs.getDate("DOB"));

				tfAge.setText(rs.getString("Age"));
				taAddr.setText(rs.getString("Address"));
				tfMob.setText(rs.getString("TelMobNo"));
				tfEdu.setText(rs.getString("Education"));
				tfInsti.setText(rs.getString("InstituteName"));
				tfUniv.setText(rs.getString("University"));
				tfYrG.setText(rs.getString("GraduatedYear"));
			
				if(rs.getString("HaveGraduationCertificate").equals("yes")){
					rdbtnYes.setSelected(true);
				}
				else
					rdbtnNo.setSelected(true);
				tfEmail.setText(rs.getString("Email"));
				tfFb.setText(rs.getString("Facebook"));
				
				tfTwitter.setText(rs.getString("Twitter"));
				taAddrJ.setText(rs.getString("JobAddress"));
				tfTel.setText(rs.getString("OfficeNo"));
				tfDistrict.setText(rs.getString("District"));
				tfTaluka.setText(rs.getString("Taluka"));
				tfCity.setText(rs.getString("City"));
				
				tfVoterG.setText(rs.getString("VoterGrpno"));
				tfVoterId.setText(rs.getString("VoterNo"));
				tfSection.setText(rs.getString("Section"));
				tfBloodG.setText(rs.getString("BloodGroup"));
				String r=rs.getString("photopath");
				if(r!=null)
				file=new File(r);
				photolbl.setIcon(new ImageIcon(file.getAbsolutePath()));
				tfOcc.setText(rs.getString("Occupation"));
			}
			if(b){
				JOptionPane.showMessageDialog(null,"Sorry,No Such Record Available");
				tfRegi.setText("");
			}
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}

		
	}
	protected void delete() {
		try {
			String s=tfRegi.getText();
			if(s!=null&&s.length()>0){
				int res=JOptionPane.showConfirmDialog(null, "Are you sure to delete record?");
				if(res!=1&&res!=2)
				{
						this.s.execute("delete from PerSonalDetails where ID="+tfRegi.getText());
						JOptionPane.showMessageDialog(null, "Record is Deleted");
				}
				clear();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Insert ID");
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	}
	protected void save() {
		try
		{
			ResultSet rs=ConnectToDatabase.getS().executeQuery("select * from PerSonalDetails where ID="+tfRegi.getText());
			if(rs.next())
			{
				JOptionPane.showMessageDialog(null, "Record Already Exist..try update option");
			}
			if(!check())
			{
				JOptionPane.showMessageDialog(null, "Enter Registration Number and Other Details");
				return;
			}
			int res=JOptionPane.showConfirmDialog(null, "Are you sure?");
				if(res!=1&&res!=2){

					String yesno;
					if(rdbtnYes.isSelected())
						yesno="yes";
					else
						yesno="no";
					
					Calendar cal=Calendar.getInstance();
					cal.setTime(tfDob.getDate());
					int day=cal.get(Calendar.DAY_OF_MONTH);
					int month=cal.get(Calendar.MONTH);
					int year=cal.get(Calendar.YEAR);
					month++;
					
					String date=""+day+"/"+month+"/"+year;
			
					//PreparedStatement psmt=con.prepareStatement("insert into item values(?,?,?,?)");
					//                                      1	2         3    4  5        6         7         8            9           10          11                         12      13    14          15      16      17         18     19   20        21       22    23
					//s.execute("insert into PerSonalDetails (ID,PersonName,DOB,Age,Address,TelMobNo,Education,InstituteName,University,GraduatedYear,HaveGraduationCertificate,Email,Facebook,Twitter,JobAddress,OfficeNo,District,Taluka,City,VoterGrpno,VoterNo,Section,BloodGroup) values("+
					String quer="insert into PerSonalDetails (ID,PersonName,DOB,Age,Address,TelMobNo,Education,InstituteName,University,GraduatedYear,HaveGraduationCertificate,Email,Facebook,Twitter,JobAddress,OfficeNo,District,Taluka,City,VoterGrpno,VoterNo,Section,BloodGroup,photopath,Occupation) values("+
						tfRegi.getText()+",'"+	//1
						tfName.getText()+"','"+	//2
						date+"',"+	//3
						tfAge.getText()+",'"+	//4
						taAddr.getText()+"','"+	//5
						tfMob.getText()+"','"+	//6
						tfEdu.getText()+"','"+	//7
						tfInsti.getText()+"','"+//8
						tfUniv.getText()+"','"+ //9
						tfYrG.getText()+"','"+	//10
						yesno+"','"+			//11
						tfEmail.getText()+"','"+//12
						tfFb.getText()+"','"+	//13
						tfTwitter.getText()+"','"+//14
						taAddrJ.getText()+"','"+//15
						tfTel.getText()+"','"+//16
						tfDistrict.getText()+"','"+//17
						tfTaluka.getText()+"','"+//18
						tfCity.getText()+"','"+//19
						tfVoterG.getText()+"','"+//20
						tfVoterId.getText()+"','"+//21
						tfSection.getText()+"','"+//22
						tfBloodG.getText()+"','"+
						file.getAbsolutePath()+"','"+
						tfOcc.getText()+"')";//23
				
					s.execute(quer);

				JOptionPane.showMessageDialog(null, "Saved SuccessFully");
				clear();
				}
				
		}
		/*catch(NullPointerException e){
			JOptionPane.showMessageDialog(null, "Insert Photo",
					"error", JOptionPane.ERROR_MESSAGE);
		}*/
		catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "error " + e1.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	public boolean check() {
		
		String s=tfRegi.getText();
		if(s.length()<=0)
			return false;
		s=tfName.getText();
		if(s.length()<=0)
			return false;
		return true;
		
	}
	protected void clear() {
				tfRegi.setText("");
				tfName.setText("");
				Calendar cal=Calendar.getInstance();
				

				tfDob.getDateEditor().setDate(cal.getTime());;
				tfAge.setText("");
				taAddr.setText("");
				tfMob.setText("");
				tfEdu.setText("");
				tfInsti.setText("");
				tfUniv.setText("");
				tfYrG.setText("");
				tfEmail.setText("");
				tfFb.setText("");
				tfOcc.setText("");
				
				tfTwitter.setText("");
				taAddrJ.setText("");
				tfTel.setText("");
				tfDistrict.setText("");
				tfTaluka.setText("");
				tfCity.setText("");
				
				tfVoterG.setText("");
				tfVoterId.setText("");
				tfSection.setText("");
				tfBloodG.setText("");
				photolbl.setIcon(new ImageIcon(Registration.class.getResource("/images/photo.png")));
		
	}
	public void update(){
		try
		{
		
			if(tfAge.getText().length()>0){
				s.execute("update PerSonalDetails set Age="+tfAge.getText()+" where ID="+tfRegi.getText());
			}
			if(tfBloodG.getText().length()>0){
				s.execute("update PerSonalDetails set BloodGroup='"+tfBloodG.getText()+"' where ID="+tfRegi.getText());
			}
			if(tfCity.getText().length()>0){
				s.execute("update PerSonalDetails set City='"+tfCity.getText()+"' where ID="+tfRegi.getText());
			}
			if(tfDistrict.getText().length()>0){
				s.execute("update PerSonalDetails set District='"+tfDistrict.getText()+"' where ID="+tfRegi.getText());
			}
			Calendar cal=Calendar.getInstance();
			cal.setTime(tfDob.getDate());
			int day=cal.get(Calendar.DAY_OF_MONTH);
			int month=cal.get(Calendar.MONTH);
			int year=cal.get(Calendar.YEAR);
			month++;
			String date=""+day+"/"+month+"/"+year;
			System.out.println(date);
			if(date.length()>0){
				s.execute("update PerSonalDetails set DOB='"+date+"' where ID="+tfRegi.getText());
			}
			if(tfEdu.getText().length()>0){
				s.execute("update PerSonalDetails set Education='"+tfEdu.getText()+"' where ID="+tfRegi.getText());
			}
			if(tfEmail.getText().length()>0){
				s.execute("update PerSonalDetails set Email='"+tfEmail.getText()+"' where ID="+tfRegi.getText());
			}
			if(tfFb.getText().length()>0){
				s.execute("update PerSonalDetails set Facebook='"+tfFb.getText()+"' where ID="+tfRegi.getText());
			}
			if(tfInsti.getText().length()>0){
				s.execute("update PerSonalDetails set InstituteName='"+tfInsti.getText()+"' where ID="+tfRegi.getText());
			}
			if(tfMob.getText().length()>0){
				s.execute("update PerSonalDetails set TelMobNo='"+tfMob.getText()+"' where ID="+tfRegi.getText());
			}
			if(tfName.getText().length()>0){
				s.execute("update PerSonalDetails set PersonName='"+tfName.getText()+"' where ID="+tfRegi.getText());
			}
			if(tfOcc.getText().length()>0){
				s.execute("update PerSonalDetails set Occupation='"+tfOcc.getText()+"' where ID="+tfRegi.getText());
			}
			
			if(tfSection.getText().length()>0){
				s.execute("update PerSonalDetails set Section='"+tfSection.getText()+"' where ID="+tfRegi.getText());
			}
			if(tfTaluka.getText().length()>0){
				s.execute("update PerSonalDetails set Taluka='"+tfTaluka.getText()+"' where ID="+tfRegi.getText());
			}
			if(tfTel.getText().length()>0){
				s.execute("update PerSonalDetails set OfficeNo='"+tfTel.getText()+"' where ID="+tfRegi.getText());
			}
			if(tfTwitter.getText().length()>0){
				s.execute("update PerSonalDetails set Twitter='"+tfTwitter.getText()+"' where ID="+tfRegi.getText());
			}
			if(tfUniv.getText().length()>0){
				s.execute("update PerSonalDetails set University='"+tfUniv.getText()+"' where ID="+tfRegi.getText());
			}
			if(tfVoterG.getText().length()>0){
				s.execute("update PerSonalDetails set VoterGrpno='"+tfVoterG.getText()+"' where ID="+tfRegi.getText());
			}
			if(tfVoterId.getText().length()>0){
				s.execute("update PerSonalDetails set VoterNo='"+tfVoterId.getText()+"' where ID="+tfRegi.getText());
			}
			
			if(tfYrG.getText().length()>0){
				s.execute("update PerSonalDetails set GraduatedYear='"+tfYrG.getText()+"' where ID="+tfRegi.getText());
			}
			if(taAddr.getText().length()>0){
				s.execute("update PerSonalDetails set Address='"+taAddr.getText()+"' where ID="+tfRegi.getText());
			}
			if(taAddrJ.getText().length()>0){
				s.execute("update PerSonalDetails set JobAddress='"+taAddrJ.getText()+"' where ID="+tfRegi.getText());
			}
			s.execute("update PerSonalDetails set photopath='"+file.getAbsolutePath()+"' where ID="+tfRegi.getText());
			if(rdbtnYes.isSelected())
				s.execute("update PerSonalDetails set HaveGraduationCertificate='yes' where ID="+tfRegi.getText());
			else if(rdbtnNo.isSelected())
				s.execute("update PerSonalDetails set HaveGraduationCertificate='no' where ID="+tfRegi.getText());
			
		
			JOptionPane.showMessageDialog(null, "Updated SuccessFully");
			clear();
		}
		catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, "error " + e1.getMessage(),
					"error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
}
