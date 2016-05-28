package A3;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.dom.DOMSource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import com.mysql.jdbc.DatabaseMetaData;
import com.mysql.jdbc.Statement;

public class toXML {

  public static void main(String args[]) throws Exception 
  {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.newDocument();
    Element results = doc.createElement("Assignment3");
    doc.appendChild(results);
    Class.forName("com.mysql.jdbc.Driver");
    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/a3", "root", "963369");
    int numOfTables=0;
    
    try{  
    DatabaseMetaData md = (DatabaseMetaData) con.getMetaData();
    ResultSet r = md.getTables(null, null, "%", null);
   
    	while (r.next()) {
     // System.out.println(r.getString(3));
      numOfTables++;
    	}
    } catch (SQLException e) {
    e.printStackTrace();
    }
    //traverse all the tables in DB
    for(int count=0;count<numOfTables;count++)
    {
	    ResultSet rs;
	  int rowCount=0;   int j=0;
	    if(count==0)
	    {
	    	rs = con.createStatement().executeQuery("select * from exam");
	    	Statement stmt = (Statement) con.createStatement();

	    	ResultSet r = stmt.executeQuery("SELECT COUNT(*) AS COUNT FROM exam");

	    	 while(r.next()) {
	    	    rowCount=r.getInt("COUNT");
	    	 }
	    	 System.out.println(rowCount);
	    	 j=0;
	    }
	    else if(count==1)
	    {
	    	rs = con.createStatement().executeQuery("select * from question ");
	    	Statement stmt = (Statement) con.createStatement();

	    	ResultSet r = stmt.executeQuery("SELECT COUNT(*) AS COUNT FROM question");

	    	 while(r.next()) {
	    	    rowCount=r.getInt("COUNT");
	    	 }
	    	System.out.println(rowCount);
	    	 j++;
	    }
	    else{
	    	rs = con.createStatement().executeQuery("select * from answeroption");
	    	Statement stmt = (Statement) con.createStatement();

	    	ResultSet r = stmt.executeQuery("SELECT COUNT(*) AS COUNT FROM answeroption");

	    	 while(r.next()) {
	    	    rowCount=r.getInt("COUNT");
	    	 }
	    	System.out.println(rowCount);
	    	 j++;
	    }
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int columnCount = rsmd.getColumnCount();
	   
	  
	    while (rs.next()) {
	    	
	     Element row = doc.createElement(rs.getMetaData().getTableName(j+1));
	    results.appendChild(row);
	    
	      for (int i = 1; i <= columnCount; i++) {
	        String columnName = rsmd.getColumnName(i);
	        Object value = rs.getObject(i);
	        Element node = doc.createElement(columnName);
	        
	        if(value==null)
	        	value="";
	    
	        node.appendChild(doc.createTextNode(value.toString()));
	        row.appendChild(node);
	      }
	      results.appendChild(row);
	    }
		    DOMSource source = new DOMSource(doc);
		    TransformerFactory tf = TransformerFactory.newInstance();
		    Transformer t = tf.newTransformer();
		    t.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
		    t.setOutputProperty(OutputKeys.INDENT, "yes");
			t.setOutputProperty(OutputKeys.METHOD, "xml");
			t.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1");
		    StringWriter sw = new StringWriter();
		    StreamResult result = new StreamResult(sw);
		    t.transform(source, result);
	    
	    if(count==numOfTables-1)
	    {
		    System.out.println(sw.toString());
		    PrintWriter writer = new PrintWriter("XML_File.xml", "UTF-8");
		    writer.println(sw.toString());
		    writer.close();
	    }
    }
    con.close();
  }
}

   
    