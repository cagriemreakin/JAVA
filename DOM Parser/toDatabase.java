import java.io.*;
import java.sql.*;
import org.w3c.dom.*;
import javax.xml.parsers.*;
public class toDatabase{
public static void main(String[] args) { 
try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/a3_2", "root", "963369");
		Statement st=con.createStatement();
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		Document doc = docBuilder.parse (new File("C:\\Users\\CEA\\Desktop\\A3\\XML_File.xml"));
		doc.getDocumentElement().normalize();
		System.out.println ("Root element of the doc is " + doc.getDocumentElement().getNodeName());
		
		NodeList listOfExams = doc.getElementsByTagName("exam");
		NodeList listOfQuestions = doc.getElementsByTagName("question");
		NodeList listOfAnswer = doc.getElementsByTagName("answeroption");
		
		
		///FOR EXAM TABLE
		
		for(int s=0; s<listOfExams.getLength(); s++)
		{
			Node firstExamNode = listOfExams.item(s);
			if(firstExamNode.getNodeType() == Node.ELEMENT_NODE){
				Element firstExamElement = (Element)firstExamNode;
				
				NodeList enoList = firstExamElement.getElementsByTagName("eno");
				Element enoElement =(Element)enoList.item(0);
				NodeList enosList = enoElement.getChildNodes();
				String eno=((Node)enosList.item(0)).getNodeValue().trim();
				
				NodeList etitleList = firstExamElement.getElementsByTagName("etitle");
				Element etitleElement =(Element)etitleList.item(0);
				NodeList etitlesList = etitleElement.getChildNodes();
				String etitle= ((Node)etitlesList.item(0)).getNodeValue().trim();
				
				NodeList timeAllowed = firstExamElement.getElementsByTagName("timeAllowed");
				Element timeElement =(Element)timeAllowed.item(0);
				NodeList timeList = timeElement.getChildNodes();
				int time= Integer.parseInt(((Node)timeList.item(0)).getNodeValue());
				
				
				NodeList questions = firstExamElement.getElementsByTagName("numberOfQuestionsPerPage");
				Element questionsElement =(Element)questions.item(0);
				NodeList questionsElementLNList = questionsElement.getChildNodes();
				int question= Integer.parseInt(((Node)questionsElementLNList.item(0)).getNodeValue());
				
				int i=st.executeUpdate("insert into exam(eno,etitle,timeAllowed,numberOfQuestionsPerPage) values('"+eno+"','"+etitle+"','"+time+"','"+question+"')");
			}
		}
		
		
		
		///FOR QUESTION TABLE
		for(int s=0; s<listOfQuestions.getLength(); s++)
		{
			System.out.println(listOfQuestions.getLength());
			Node firstQuestionNode = listOfQuestions.item(s);
			if(firstQuestionNode.getNodeType() == Node.ELEMENT_NODE){
				Element firstQElement = (Element)firstQuestionNode;
				
				NodeList enoList = firstQElement.getElementsByTagName("eno");
				Element enoElement =(Element)enoList.item(0);
				NodeList enosList = enoElement.getChildNodes();
				String eno=((Node)enosList.item(0)).getNodeValue().trim();
				System.out.println(eno);
				
				NodeList qnoList = firstQElement.getElementsByTagName("qno");
				Element qnoElement =(Element)qnoList.item(0);
				NodeList qnosList = qnoElement.getChildNodes();
				String qno= ((Node)qnosList.item(0)).getNodeValue().trim();
				System.out.println(qno);
				
				NodeList qtextList = firstQElement.getElementsByTagName("qtext");
				Element qtextElement =(Element)qtextList.item(0);
				NodeList qtextsList = qtextElement.getChildNodes();
				String qtext= ((Node)qtextsList.item(0)).getTextContent();
				System.out.println(qtext);
				
				NodeList correctAnswerList = firstQElement.getElementsByTagName("correctAnswer");
				Element correctAnswerElement =(Element)correctAnswerList.item(0);
				NodeList correctAnswerElementList = correctAnswerElement.getChildNodes();
				String correctAnswer= ((Node)correctAnswerElementList.item(0)).getNodeValue().trim();
				System.out.println(correctAnswer);
				
				int i=st.executeUpdate("insert into question(eno,qno,qtext,correctAnswer) values('"+eno+"','"+qno+"','"+qtext+"','"+correctAnswer+"')");
		
			}
		}
		
		///FOR Answer TABLE
				for(int s=0; s<listOfAnswer.getLength(); s++)
				{
					System.out.println(listOfAnswer.getLength());
					Node firstAnswerNode = listOfAnswer.item(s);
					if(firstAnswerNode.getNodeType() == Node.ELEMENT_NODE){
						Element firstAElement = (Element)firstAnswerNode;
						
						NodeList enoList = firstAElement.getElementsByTagName("eno");
						Element enoElement =(Element)enoList.item(0);
						NodeList enosList = enoElement.getChildNodes();
						String eno=((Node)enosList.item(0)).getNodeValue().trim();
						
						NodeList qnoList = firstAElement.getElementsByTagName("qno");
						Element qnoElement =(Element)qnoList.item(0);
						NodeList qnosList = qnoElement.getChildNodes();
						String qno= ((Node)qnosList.item(0)).getNodeValue().trim();
						
						NodeList onoList = firstAElement.getElementsByTagName("ono");
						Element onoElement =(Element)onoList.item(0);
						NodeList onosList = onoElement.getChildNodes();
						String ono= ((Node)onosList.item(0)).getNodeValue().trim();
						
						
						NodeList optionTextList = firstAElement.getElementsByTagName("optionText");
						Element optionTextElement =(Element)optionTextList.item(0);
						NodeList optionTextElementList = optionTextElement.getChildNodes();
						String optionText= ((Node)optionTextElementList.item(0)).getNodeValue().trim();
						
						int i=st.executeUpdate("insert into answeroption(eno,qno,ono,optionText) values('"+eno+"','"+qno+"','"+ono+"','"+optionText+"')");
					}
				}
		System.out.println("Data is successfully inserted!");
		}catch (Exception err) {
		System.out.println(" " + err.getMessage ());
		}
	}
}