package registr;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;

public class Display extends JPanel implements ActionListener
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Dimension d;
	private static int w;
	private static int h;
	private JDateChooser tfDob;
	int j=0;

	JCheckBox c[]; 
	JTable t;
	JButton b;
	JScrollPane sc;
	JTextField tt[];
	JComboBox<String> jc[];
	
	String s[]={"PersonName","Age","TelMobNo","Education","InstituteName","University","District","Taluka","City","Section","DOB"};
	private int count;
	private JMonthChooser m;
	@SuppressWarnings("unchecked")
	public Display()
	{
		setLayout(null);
		setBackground(new Color(255, 204, 51));
		t=new JTable();
		count=11;
		c=new JCheckBox[count];
		jc=new JComboBox[count];
		tt=new JTextField[count-1];
		
	
		int csize=110,combosize=50,tfsize=100,tfht=25;
		int pos=80+csize+combosize+tfsize;
		int space=40;
		for(int i=0;i<c.length;i++)
		{
			jc[i]=new JComboBox<String>();
			c[i]=new JCheckBox(s[i]);
		
			if(i!=(count-1))
			tt[i]=new JTextField();

			if(i%2==0)
			{
				j+=space;
				c[i].setBounds(20,j,csize,tfht);
			
				jc[i].setBounds(2*20+csize,j,50,tfht);
				if(i!=(count-1))
				tt[i].setBounds(csize+combosize+3*20,j,tfsize,tfht);
			}
			else 
			{
				c[i].setBounds(pos,j,csize,tfht);
				jc[i].setBounds(pos+csize+20,j,combosize,tfht);
				if(i!=(count-1))
				tt[i].setBounds(pos+csize+combosize+40,j,tfsize,tfht);
			}
			jc[i].addItem("=");
			jc[i].addItem("<>");
			if(i==1||i==(count-1))
			{
				jc[i].addItem("<");
				jc[i].addItem(">");
				jc[i].addItem("<=");
				jc[i].addItem(">=");
				
			}
			if(i!=(count-1))
			add(tt[i]);
			add(jc[i]);
			add(c[i]);
		}
		tfDob=new JDateChooser();
		tfDob.setBounds(csize+combosize+60,j,tfsize+tfsize/2,tfht);
		tfDob.setDateFormatString("dd/MM/yyyy");
		add(tfDob);
		b=new JButton("Done");
		b.setBounds(550,300,100,30);
		b.addActionListener(this);
		add(b);
		
		m=new JMonthChooser();
		m.setBounds(50,300,150,30);
		add(m);
		
		JButton bdaybtn=new JButton("Get BirthDay List");
		bdaybtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				displayBirthDayList();
				
			}
		});
		bdaybtn.setBounds(200,300,150,30);
		add(bdaybtn);
	}
	public void displayBirthDayList() {
		try
		{
			int month=m.getMonth()+1;
			/*Vector<String> v=new Vector<String>();
			
			v.add("ID");
			v.add("PersonName");
			v.add("TelMobNo");
			v.add("BirthDate");
			v.add("Taluka");
			v.add("city");
			
			String sel=v.get(0);
			for(int i=1;i<v.size();i++){
				if(v.get(i).equals("BirthDate"))
					sel+=",Format([DOB],'dd/mm/yyyy') as BirthDate";
				else
				sel+=","+v.get(i);
			}
			String sql="select "+sel+" from PersonalDetails where Month([DOB])="+month;
			//System.out.println(sql);
			Statement s=ConnectToDatabase.getS();
			ResultSet rs=s.executeQuery(sql);
			MyReport.getReport(v, rs);*/
			
			JasperReport report = JasperCompileManager.compileReport("D:\\study\\java\\Reports\\BirthDate.jrxml");
			//HashMap<String, String> params = new HashMap<String,String>();
			//params.put( "Month", ""+month );
			
			Map<String, Object> parameters = new HashMap<>();
			parameters.put("Month", ""+month);
			System.out.println(month);
			JasperPrint printedReport = JasperFillManager.fillReport(report, parameters, ConnectToDatabase.getCon());
			JasperViewer.viewReport(printedReport,false);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static void create()
	{
		JFrame f=new JFrame("select");
		f.setLocationRelativeTo(null);
		f.add(new Display());
		w=700;
		h=400;
		f.setSize(w,h);
		d=Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation(d.width/2-f.getWidth()/2,d.height/2-f.getHeight()/2);
		f.setVisible(true);
	}

	public void actionPerformed(ActionEvent e)
	{
		try
		{
			new Vector<Vector<String>>();
			Vector<String> v=new Vector<String>();
			ConnectToDatabase.getS();
			boolean flg=true;
			
			//int dtpos=-1;
			for(int i=0;i<c.length;i++)
			{
				if(c[i].isSelected())
				{
					flg=false;
					String action=c[i].getActionCommand();
					if(action.equals("DOB")){
						//dtpos=v.size()+1;
						action="BirthDate";
					}
					v.add(action);
				}
			}
			System.out.println(v);
			
			if(!flg)
			{
		
				String sel="";
				if(v.size()>0)
				{
				
					String str=v.get(0);
					if(str.equals("BirthDate"))
						str="Format([DOB],'dd/mm/yyyy') as BirthDate";
					sel=str;
				}
				for(int i=1;i<v.size();i++)
				{
					String str=v.get(i);
					if(str.equals("BirthDate"))
						str="Format([DOB],'dd/mm/yyyy') as BirthDate";
					sel=sel+","+str;
				}
				//half part of select 

				/*--------------------------------------------------*/
				String con="";
				Vector<String> attr=new Vector<String>();
				Vector<String> values=new Vector<String>();
				Vector<String> cond=new Vector<String>();

				for(int i=0;i<tt.length;i++)
				{
					String str=tt[i].getText();
					if(str!=null&str.length()>0)
					{
						
						attr.add(s[i]);
						values.add(str);
						String sc=(String)jc[i].getSelectedItem();
						cond.add(sc);
					}
				}
				java.util.Date date=tfDob.getDate();
				if(date!=null){
					Calendar cal=Calendar.getInstance();
					
					cal.setTime(date);
					int day=cal.get(Calendar.DAY_OF_MONTH);
					int month=cal.get(Calendar.MONTH);
					int year=cal.get(Calendar.YEAR);
					month++;
					
					String dt=""+day+"/"+month+"/"+year;
					attr.add("DOB");
					values.add(dt);
					cond.add((String)jc[count-1].getSelectedItem());
				}
				if(attr.size()>0)
				{
					if(attr.get(0).equals("DOB"))
						con=" where "+attr.elementAt(0)+cond.elementAt(0)+"#"+values.elementAt(0)+"#";
					else if(attr.get(0).equals("Age"))
						con=" where "+attr.elementAt(0)+cond.elementAt(0)+values.elementAt(0);
					else
						con=" where "+attr.elementAt(0)+cond.elementAt(0)+"'"+values.elementAt(0)+"'";
				}
				for(int i=1;i<attr.size();i++)
				{
					if(attr.get(i).equals("DOB"))
						con=" and "+attr.elementAt(i)+cond.elementAt(i)+"#"+values.elementAt(i)+"#";
					else if(attr.get(i).equals("Age"))
						con=" and "+attr.elementAt(i)+cond.elementAt(i)+values.elementAt(i);
					else
					con=con+" and "+ attr.elementAt(i) +cond.elementAt(i)+"'"+values.elementAt(i)+"'";
				}
				String sql="select "+ sel+" from PerSonalDetails"+con;
				System.out.println(sql);
				
				Statement s=ConnectToDatabase.getS();
				ResultSet rs=s.executeQuery(sql);
				
				//if(v.contains("format([DOB],'dd/mm/yyyy') as BirthDate"))
				/*{
					
					Collection<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
					
					while(rs.next())
					{
							ArrayList<String> tuple=new ArrayList<String>();
							for(int i=1;i<=v.size();i++){
							
								if(dtpos>0&&i==dtpos)
								tuple.add(""+rs.getDate(i));
								else
								tuple.add(""+rs.getString(i));
							}
							System.out.println(tuple);
							data.add(tuple);
					}
					//MyReport.getReport(v, data);
				}*/
				//else
					MyReport.getReport(v, rs);
				//MyReport.getReport(v, sql);
			}
			else
			{
				JOptionPane.showMessageDialog(this,"Select required attributs..");
			}
		}
		catch (Exception ee)
		{
			ee.printStackTrace();
			JOptionPane.showMessageDialog(null, "Details are missing in record","error",JOptionPane.WARNING_MESSAGE);
		}

	}
	public static void main(String[] args) 
	{
		ConnectToDatabase.main(null);
		Registration.setNimbus();
		create();	
	}
}
