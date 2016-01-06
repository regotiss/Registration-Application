package registr;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;

import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

public class Search extends JDialog {

	private static Search s;
	/**
	 * 
	 */
	
	Registration yuva;
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			create(null);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void create(Registration yuva) {
		s=new Search();
		s.yuva=yuva;
	}

	/**
	 * Create the dialog.
	 */
	public Search() {
		
		
		setBounds(100, 100, 330, 171);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.WHITE);
		contentPanel.setBorder(new TitledBorder(null, "Search By Name", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		comboBox = new JComboBox<String>(getNames());
		comboBox.setBounds(33, 30, 247, 26);
		AutoCompleteDecorator.decorate(comboBox);
		
		
		contentPanel.add(comboBox);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.WHITE);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						JTextField tf=yuva.getTfName();
						tf.setText(""+comboBox.getSelectedItem());
						yuva.setDetails();
						s.setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						s.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	public Vector<String> getNames() {
		
		Vector<String> names=new Vector<String>();
		try{
			
			Statement s=ConnectToDatabase.getS();
			ResultSet rs=s.executeQuery("select distinct PersonName from PerSonalDetails");
			while(rs.next()){
				
				names.add(rs.getString(1));
			}
		
		}catch(Exception e){}
		return names;
	}
	
}
