package registr;

import java.awt.Color;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Font;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Transparency;
import ar.com.fdvs.dj.domain.constants.VerticalAlign;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;


public class MyReport {

	public static void main(String[] args) {
		
		ConnectToDatabase.main(null);
		Vector<String> v=new Vector<String>();
		v.add("ID");
		v.add("PersonName");
		
		String sql="select ID,PersonName from PersonalDetails";
		getReport(v,sql);
	}

	public static void getReport(Vector<String> v, String sql) {
		try
		{
			DynamicReport dr = getDynamicReport(v);
			
			Statement s=ConnectToDatabase.getS();
			ResultSet rs=s.executeQuery(sql);
			JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), rs);
			JasperViewer.viewReport( jp, false );
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static void getReport(Vector<String> v, ResultSet rs) {
		try
		{
			DynamicReport dr = getDynamicReport(v);
			
			JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), rs);
			JasperViewer.viewReport( jp, false );
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static void getReport(Vector<String> v, Vector<Vector<String>> data) {
		try
		{
			DynamicReport dr = getDynamicReport(v);
			JasperPrint jp = DynamicJasperHelper.generateJasperPrint(dr, new ClassicLayoutManager(), 
					new JRBeanCollectionDataSource(data));
			JasperViewer.viewReport( jp, false );
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public static DynamicReport getDynamicReport(Vector<String> v) {
		
		Style headerStyle = createHeaderStyle();
	    Style detailTextStyle = createDetailTextStyle();        
	    //Style detailNumberStyle = createDetailNumberStyle();
	    
	    DynamicReportBuilder report=new DynamicReportBuilder();
	    
	    for(String attr:v){
	    	
	    	
	    	AbstractColumn column = createColumn(attr, String.class,attr, 30, headerStyle, detailTextStyle);
	    	
	    	//if(attr.equals("DOB"))
	    		//column = createColumn("Int([DOB])", java.sql.Date.class,"BirthDate", 30, headerStyle, detailTextStyle);
	    	report.addColumn(column);
	    }
	    StyleBuilder titleStyle=new StyleBuilder(true);
        titleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        titleStyle.setFont(new Font(20, Font._FONT_GEORGIA, true));
        
        StyleBuilder subTitleStyle=new StyleBuilder(true);
        subTitleStyle.setHorizontalAlign(HorizontalAlign.CENTER);
        subTitleStyle.setFont(new Font(Font.MEDIUM, Font._FONT_GEORGIA, true));
        
        report.setTitle("YuvaSena");
        report.setTitleStyle(titleStyle.build());
        report.setSubtitle("Details Of Registered Members");
        report.setSubtitleStyle(subTitleStyle.build());
        report.setUseFullPageWidth(true); 
       // report.setTemplateFile("D:\\study\\java\\Reports\\template1.jrxml");
        return report.build();
	}

	public static Style createDetailNumberStyle(){
        StyleBuilder sb=new StyleBuilder(true);
        sb.setFont(Font.VERDANA_MEDIUM);
        sb.setBorder(Border.DOTTED());        
        sb.setBorderColor(Color.BLACK);        
        sb.setTextColor(Color.BLACK);
        sb.setHorizontalAlign(HorizontalAlign.RIGHT);
        sb.setVerticalAlign(VerticalAlign.MIDDLE);
        sb.setPaddingRight(5);        
        return sb.build();
	}
	 public static Style createDetailTextStyle(){
	        StyleBuilder sb=new StyleBuilder(true);
	        sb.setFont(Font.VERDANA_MEDIUM);
	        sb.setBorder(Border.DOTTED());        
	        sb.setBorderColor(Color.BLACK);        
	        sb.setTextColor(Color.BLACK);
	        sb.setHorizontalAlign(HorizontalAlign.LEFT);
	        sb.setVerticalAlign(VerticalAlign.MIDDLE);
	        sb.setPaddingLeft(5);        
	        return sb.build();
	 }
	public static AbstractColumn createColumn(String property, Class<String> type,
			 String title, int width, Style headerStyle, Style detailStyle)
	            throws ColumnBuilderException {
	        AbstractColumn columnState = ColumnBuilder.getNew()
	                .setColumnProperty(property, type.getName()).setTitle(
	                        title).setWidth(Integer.valueOf(width))
	                .setStyle(detailStyle).setHeaderStyle(headerStyle).build();
	        return columnState;
	  }
	public static Style createHeaderStyle() {
		 
		 StyleBuilder sb=new StyleBuilder(false);
	     sb.setFont(Font.VERDANA_MEDIUM_BOLD);
	     sb.setBorder(Border.THIN());
	     sb.setBorderBottom(Border.PEN_2_POINT());
	     sb.setBorderColor(Color.RED);
	     sb.setBackgroundColor(Color.ORANGE);
	     sb.setTextColor(Color.BLACK);
	     sb.setHorizontalAlign(HorizontalAlign.CENTER);
	     sb.setVerticalAlign(VerticalAlign.MIDDLE);
	     sb.setTransparency(Transparency.OPAQUE);        
	     return sb.build(); 
		
		 
	 }

}
