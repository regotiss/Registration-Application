package registr;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

public class SearchPane extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private static Registration yuva;
	private JTextField tfName;
	private JTextField tfDistrict;
	private JTextField tfAge;
	private JTextField tfTaluka;
	private JTextField tfVoterId;
	private JTextField tfCity;
	private JTextField tfContact;
	private JTextField tfEdu;
	private JTable table;
	private JScrollPane scrollPane;
	private JPanel panel;
	private Vector<Vector<String>> vv;
	private Vector<String> v;

	public SearchPane() {
		setBackground(new Color(255, 204, 0));
		setSize(800,600);
		setLayout(null);
		
		vv=new Vector<Vector<String>>();
		v=new Vector<String>();
		v.add("Reg. No.");
		v.add("PersonName");
		v.add("Age");
		v.add("Contact No");
		v.add("Occupation");
		v.add("Education");
	
		
		
		JLabel lblSearchRecord = new JLabel("Search Record");
		lblSearchRecord.setForeground(new Color(204, 0, 0));
		lblSearchRecord.setFont(new Font("Baskerville Old Face", Font.BOLD, 24));
		lblSearchRecord.setBounds(356, 42, 161, 39);
		add(lblSearchRecord);
		
		panel = new JPanel();
		panel.setBorder(UIManager.getBorder("TitledBorder.border"));
		panel.setBackground(new Color(255, 204, 51));
		panel.setBounds(36, 111, 729, 203);
		add(panel);
		panel.setLayout(null);
		
		tfName = new JTextField();
		tfName.setBounds(135, 25, 175, 29);
		panel.add(tfName);
		tfName.setColumns(10);
		List<String> l=getList("PersonName");
	
		AutoCompleteDecorator.decorate(tfName,l,false);
		
		tfDistrict = new JTextField();
		tfDistrict.setBounds(135, 65, 175, 29);
		panel.add(tfDistrict);
		AutoCompleteDecorator.decorate(tfDistrict,getList("District"),true);
		tfDistrict.setColumns(10);
		
		tfAge = new JTextField();
		tfAge.setBounds(436, 25, 175, 29);
		panel.add(tfAge);
		tfAge.setColumns(10);
		
		tfTaluka = new JTextField();
		tfTaluka.setBounds(436, 65, 175, 29);
		AutoCompleteDecorator.decorate(tfTaluka,getList("Taluka"),true);
		panel.add(tfTaluka);
		tfTaluka.setColumns(10);
		
		tfVoterId = new JTextField();
		tfVoterId.setBounds(135, 152, 175, 29);
		AutoCompleteDecorator.decorate(tfVoterId,getList("VoterNo"),true);
		panel.add(tfVoterId);
		tfVoterId.setColumns(10);
		
		tfCity = new JTextField();
		AutoCompleteDecorator.decorate(tfCity,getList("City"),true);
		tfCity.setBounds(135, 112, 175, 29);
		panel.add(tfCity);
		tfCity.setColumns(10);
		
		tfContact = new JTextField();
		AutoCompleteDecorator.decorate(tfContact,getList("TelMobNo"),true);
		tfContact.setBounds(436, 112, 175, 29);
		panel.add(tfContact);
		tfContact.setColumns(10);
		
		tfEdu = new JTextField();
		AutoCompleteDecorator.decorate(tfEdu,getList("Education"),true);
		tfEdu.setBounds(436, 152, 175, 29);
		panel.add(tfEdu);
		tfEdu.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Person Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(35, 31, 109, 16);
		panel.add(lblNewLabel_1);
		
		JLabel lblDistrict = new JLabel("District");
		lblDistrict.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblDistrict.setBounds(35, 71, 109, 16);
		panel.add(lblDistrict);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCity.setBounds(35, 118, 109, 16);
		panel.add(lblCity);
		
		JLabel lblVoterId = new JLabel("Voter ID");
		lblVoterId.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblVoterId.setBounds(35, 158, 109, 16);
		panel.add(lblVoterId);
		
		JLabel lblAge = new JLabel("Age");
		lblAge.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAge.setBounds(344, 31, 109, 16);
		panel.add(lblAge);
		
		JLabel lblTaluka = new JLabel("Taluka");
		lblTaluka.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblTaluka.setBounds(344, 71, 109, 16);
		panel.add(lblTaluka);
		
		JLabel lblContactNo = new JLabel("Contact No");
		lblContactNo.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblContactNo.setBounds(344, 118, 109, 16);
		panel.add(lblContactNo);
		
		JLabel lblEducation = new JLabel("Education");
		lblEducation.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblEducation.setBounds(344, 158, 109, 16);
		panel.add(lblEducation);
		
		JButton btnOk = new JButton("");
		btnOk.setBackground(new Color(255, 204, 51));
		btnOk.setIcon(new ImageIcon(SearchPane.class.getResource("/images/gobutton1.png")));
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				find();
				
			}
		});
		btnOk.setHorizontalTextPosition(SwingConstants.LEADING);
	
		btnOk.setBounds(630, 133, 59, 54);
		panel.add(btnOk);
		
		JLabel lblNewLabel = new JLabel("select checkbox and enter value in textarea by which you want to search");
		lblNewLabel.setFont(new Font("Baskerville Old Face", Font.BOLD, 16));
		lblNewLabel.setBounds(191, 92, 557, 14);
		add(lblNewLabel);
		
		scrollPane = new JScrollPane();
		scrollPane.setBorder(UIManager.getBorder("TitledBorder.border"));
		scrollPane.setBounds(34, 317, 736, 272);
		add(scrollPane);
		
		table = new JTable(vv,v);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				 int i=table.getSelectionModel().getLeadSelectionIndex();
				 yuva.getTfRegi().setText(""+vv.get(i).get(0));
				 yuva.showDetails();
			
			}
		});
		scrollPane.setViewportView(table);
	}
	private Vector<String> getList(String string) {
		
		Vector<String> v=new Vector<String>();
		try
		{
			Statement s=ConnectToDatabase.getS();
			s.execute("select distinct "+string+" from PerSonalDetails");
			ResultSet rs=s.getResultSet();
			while(rs.next()){
				v.add(rs.getString(1));
			}
		}
		catch(Exception e){}
		return v;
	}
	
	public void find() {
		ResultSet r=null;
		try
		{

			String sel="";
			Vector<String> attr=new Vector<String>();
			Vector<String> values=new Vector<String>();

			String str=tfAge.getText();
			if(str!=null&str.length()>0){
				attr.add("Age");
				values.add(str);
			}
			str=tfName.getText();
			if(str!=null&str.length()>0){
				attr.add("PersonName");
				values.add(str);
			}
			str=tfCity.getText();
			if(str!=null&str.length()>0){
				attr.add("City");
				values.add(str);
			}
			str=tfContact.getText();
			if(str!=null&str.length()>0){
				attr.add("TelMobNo");
				values.add(str);
			}
			str=tfDistrict.getText();
			if(str!=null&str.length()>0){
				attr.add("District");
				values.add(str);
			}
			str=tfVoterId.getText();
			if(str!=null&str.length()>0){
				attr.add("VoterNo");
				values.add(str);
			}
			str=tfTaluka.getText();
			if(str!=null&str.length()>0){
				attr.add("Taluka");
				values.add(str);
			}
			str=tfEdu.getText();
			if(str!=null&str.length()>0){
				attr.add("Education");
				values.add(str);
			}
			
			String arr[]={"distinct ID","PersonName","Age","TelMobNo","Occupation","Education"};
			String tbl="";
			for(int i=0;i<arr.length;i++){
				
				if(i==(arr.length-1))
					tbl+=arr[i]+" ";
				else
					tbl+=arr[i]+",";
			}
			
			if(attr.size()>0)
			{
				if(attr.elementAt(0).equals("Age"))
					sel="select "+ tbl+"from PerSonDetails where "+attr.elementAt(0)+"="+values.elementAt(0);
				else
					sel="select "+ tbl+"from PerSonalDetails where "+attr.elementAt(0)+"='"+values.elementAt(0)+"'";
			}
			for(int i=1;i<attr.size();i++)
			{
				if(attr.elementAt(i).equals("Age"))
					sel=sel+" and "+ attr.elementAt(i) + "="+values.elementAt(i);
				else
					sel=sel+" and "+ attr.elementAt(i) + "='"+values.elementAt(i)+"'";
			}
			Statement s=ConnectToDatabase.getS();
			

			s.execute(sel);
			r=s.getResultSet();
			vv=new Vector<Vector<String>>();
			Vector<String> vect=new Vector<String>();
			while(r.next()){
				for(int i=1;i<=v.size();i++){
					vect.add(r.getString(i));
				}
				vv.add(new Vector<String>(vect));
				vect.clear();
			}
			
			table=new JTable(vv,v);
			table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
				
				@Override
				public void valueChanged(ListSelectionEvent e) {
					 int i=table.getSelectionModel().getLeadSelectionIndex();
					 yuva.getTfRegi().setText(""+vv.get(i).get(0));
					 yuva.showDetails();	
				}
			});
			scrollPane.setViewportView(table);
		}
		catch(Exception e){}

		
	}
	public static void main(String args[]){
		
		ConnectToDatabase.main(null);
		Registration.setNimbus();
		create(null);
	}
	protected static void create(Registration s) {
		
		
		frame = new JFrame("YuvaSena Application Form");
		yuva=s;
		frame.setBounds(100,100,800,650);
		//frame.setIconImage(new ImageIcon(YuvaSena.class.getResource("/images/shiv-sena.png")).getImage());
		frame.getContentPane().add(new SearchPane());
		frame.setVisible(true);
	}
}
