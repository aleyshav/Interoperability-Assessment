package PMUPacketAnalyzer;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileWriter;

public class PMUDataXMLParserAndPacketAnalyzer {
	//public FileOutputStream fop = null;
	public File file;
	//public String content = "This is the text content";    
        FileWriter fileWriter = null;;//new PrintWriter("filename.txt");
        
        public int numOfPMUs=1;
        public int numOfPhasors=1;
        public int numOfAnalogValues=1;
        public int numOfDigitalStatus=1;
        public int dataFormatOfAnalogs=0;// 0- 16 bits integer, 1- 32bits floating point
        public int dataFormatOfDigital=0;  //0- 16 bits integer, 1- 32bits floating point 
        public int dataFormatOfPhasorCoordination=0;// 0- real and imaginary (dicar); 1- magnitude and angle (polar)
        public int dataFormatOfFREQAndDFREQ=0;// 0- 16 bits integer; 1- 32 bits floating point
        public String cmdName= "" ;
        public PMUDataXMLParserAndPacketAnalyzer() {
            
    	}



public void parserXMLFile(String xmlFileName){
	  try {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFileName);
		doc.getDocumentElement().normalize();

		//System.out.println("Root element:" + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName("pdml");

              System.out.println("Root Length=:"+nList.getLength());

		for (int temp = 0; temp < nList.getLength(); temp++) {

                 
		   Node nNode = nList.item(temp);
		   if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		      Element eElement = (Element) nNode;
                    System.out.println("Node Name=:"+eElement.getNodeName());
                    System.out.println("Root Element Done:================");
                    System.out.println("\n\n\n");
		   }
		}
	  } catch (Exception e) {
		e.printStackTrace();
	  }
}

private static String getTagValue(String sTag, Element eElement) {
	
    //NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
      NodeList nlList = eElement.getElementsByTagName(sTag).item(0).getChildNodes();
   
      Node nValue = (Node) nlList.item(0);

	return nValue.getNodeValue();
}



public String getElementTag(String xmlFileName, String elementTag){

	  //Element eElement;
	  String elementTagStr="";

	  try {

		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmlFileName);
		doc.getDocumentElement().normalize();

		System.out.println("Element Name:" + doc.getDocumentElement().getNodeName());
		NodeList nList = doc.getElementsByTagName(elementTag);
	        System.out.println("Node No. of Element=:"+nList.getLength());

		for (int temp = 0; temp < nList.getLength(); temp++) {

		   Node nNode = nList.item(temp);
		   if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		      Element eElement = (Element) nNode;
	  		  //elementTagStr= getTagValue(elementTag, eElement);
                        //System.out.println("elementTagStr=:"+elementTagStr);
                    System.out.println("No.=:"+temp);
                    System.out.println("Name=:"+eElement.getNodeName());  
                    System.out.println("Attr Name 0=:"+eElement.getAttributes().item(0));      
                    System.out.println("Attr Name 1=:"+eElement.getAttributes().item(1));      
                    System.out.println("Attr Name 2=:"+eElement.getAttributes().item(2));      
                    System.out.println("Attr Name 3=:"+eElement.getAttributes().item(3));                         
                    
                    System.out.println("Value=:"+eElement.getNodeValue());
                    //System.out.println("Node Name=:"+eElement.getNodeName());
		   }
                // System.out.println(doc.getDocumentElement().getNodeName()+"   Element done:=========================");
                 System.out.println("\n");
                 
		}
	  } catch (Exception e) {
		e.printStackTrace();
	  }

    // System.out.println("Element=:"+elementTagStr);
     return elementTagStr;
}









public void parsingAndAnalyzingSynphasorProtocol(String xmlFileName){

	  System.out.println("Parser IEEE C37.118-2005 protocol==========================");
	 // String cmdName ;
	try {

	 file = new File("/Users/arv1/Downloads/Workspace/PMUIoPAnalyzer/src/PMUPacketAnalyzer/result.txt");
		
	
         fileWriter = new FileWriter(file);                        
          // if file doesnt exists, then create it
          if (!file.exists()) {
		file.createNewFile();
          }
 
        
           
          // parser protocol
		  try {
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(xmlFileName);
			doc.getDocumentElement().normalize();
			System.out.println("\n=========================protocol==========================");
                      
            fileWriter.write("=================IEEE C37.118-2005 standard-based synphasor packet analysis:=================\n");                          
			System.out.println("Element Name:" + doc.getDocumentElement().getNodeName());
			NodeList nList = doc.getElementsByTagName("proto");

			fileWriter.write("Total Num. of Elements=:"+nList.getLength()+"\n");
                      
            System.out.println("Node No. of Element=:"+nList.getLength());
            int noOfPhasorFrames=0;
            
			for (int temp = 0; temp < nList.getLength(); temp++) {
			   Node nNode = nList.item(temp);
			   if (nNode.getNodeType() == Node.ELEMENT_NODE) {

			     Element eElement = (Element) nNode; 
			     if(eElement.getAttribute("name").equals("synphasor"))
			      {       


                //fileWriter.write("No. of Phasor Frames:"+noOfPhasorFrames+"\n\n");
                //System.out.println("\nNo. of Phasor Frames:"+noOfPhasorFrames);                            
                                
              /*
				if(eElement.getAttribute("showname").indexOf("Data Frame")!= -1)
				{
                  fileWriter.write("****************************************Data Frame:****************************************\n");
				  System.out.println("\n****************************************Data Frame:****************************************");

                  parserDataFrame(eElement);
				}
				*/
                
                /*
				if(eElement.getAttribute("showname").indexOf("Configuration Frame 2")!= -1)
				{
                  fileWriter.write("****************************************Configuration Frame 2:****************************************\n");                                    
				  System.out.println("\n****************************************Configuration Frame 2:****************************************");
                  
                  //parserCFG2Frame(eElement);
				}
				*/
                
                /*
				if(eElement.getAttribute("showname").indexOf("Configuration Frame 1")!= -1)
				{
                  fileWriter.write("****************************************Configuration Frame 1:****************************************\n");                                    
				  System.out.println("\n****************************************Configuration Frame 1:****************************************");
 
                   //parserCFG1Frame(eElement);
				}  
				*/
                
                
				if(eElement.getAttribute("showname").indexOf("Command Frame")!= -1)
				{
                  // fileWriter.write("****************************************Command Frame:****************************************\n");                                    
				 // System.out.println("\n****************************************Command Frame:****************************************");

                 //parserCommandFrame(eElement);
				 // cmdName = "send HDR frame" ; 
					//Command: data transmission off (0x0001)
                  	// Command: data transmission on  (0x0002)
                  	// Command: send HDR frame        (0x0003)
                  	// Command: send CFG-1 frame      (0x0004)
                  	// Command: send CFG-2 frame      (0x0005)
					  parserSpecialCommandFrame(eElement, "send HDR frame");
					  //parserSpecialCommandFrame(eElement, "data transmission off");
					 // parserSpecialCommandFrame(eElement, "data transmission on");
					 // parserSpecialCommandFrame(eElement, "send CFG-1 frame");
					 // parserSpecialCommandFrame(eElement, "send CFG-2 frame");
				  // define cmdName "header command"
				}              
				
                
				if(eElement.getAttribute("showname").indexOf("Header Frame")!= -1)
				{
                  fileWriter.write("****************************************Header Frame:****************************************\n");                                   
				  System.out.println("\n\n\n\n\n****************************************Header Frame:*************************************");
                 
                  parserHeaderFrame(eElement);
				}     

                              noOfPhasorFrames++;
			      }

			   }

			}

		  } catch (Exception e) {
			e.printStackTrace();
		  } 
          
          
          // end parser protocol
          
          
           //fileWriter.flush();
           fileWriter.close();
	     System.out.println("Done");

	} catch (IOException e) {
			e.printStackTrace();
	} 
      
 
}  

public void parserSpecialCommandFrame(Element eElement, String C37118Cmd) {
	try{
	       
		   
        //System.out.println("\n========================= Command Frame:====================================:");
	NodeList nList = eElement.getElementsByTagName("field");
        int totalNumOfFiled=nList.getLength();
      //System.out.println("Total Num. of Fields=:"+totalNumOfFiled);
        
        //fileWriter.write("Total Num. of Field=:"+nList.getLength()+"\n");                
        
        int NoOfField=0;
        String nameOfField="";
        
	for (int temp = 0; temp < nList.getLength(); temp++) {
	   Node nNode = nList.item(temp);
       // System.out.println("\n========================= Start Field:====================================:");                      

             if (nNode.getNodeType() == Node.ELEMENT_NODE) {

	     Element element = (Element) nNode; 
             NoOfField=temp;
          

             //System.out.println("temp=:"+temp);    
             if(NoOfField>=12)
             {
                 //System.out.println("\nNo. of Field=:"+NoOfField);   
                 //fileWriter.write("\nNo. of Field=:"+NoOfField+"\n");
             }
             int cmdType=0;
             
              switch (NoOfField) {
              	
                   case 13: nameOfField = "synphasor.command";

                   	if(element.getAttribute("name").equals("synphasor.command") )
                   	{
                          	   //string.contains(substring)
                          	// Command: data transmission off (0x0001)
                          	// Command: data transmission on  (0x0002)
                          	// Command: send HDR frame        (0x0003)
                          	// Command: send CFG-1 frame      (0x0004)
                          	// Command: send CFG-2 frame      (0x0005)
                                   if(element.getAttribute("showname").contains(C37118Cmd))
                                   {
                                  	 
                                  	 switch (C37118Cmd) {
                                       case "data transmission off":
                                           fileWriter.write("**************************************** Command data transmission off Frame:****************************************\n");                                    
                         				     System.out.println("\n**************************************** Command data transmission off Frame:****************************************");
                                      	 
                                      	 System.out.println("This is a command: data transmission off (0x0001)");
                                           break;
                                       case "data transmission on":
                                           fileWriter.write("**************************************** Command: data transmission on Frame:****************************************\n");                                    
                         				     System.out.println("\n**************************************** Command: data transmission on Frame:****************************************");
                                      	 
                                      	 System.out.println("This is a command: data transmission on  (0x0002)");
                                      	 break;
                                      	 
                                       case "send HDR frame":
                                      	 
                                           fileWriter.write("**************************************** Command: send HDR Frame:****************************************\n");                                    
                         				     System.out.println("\n****************************************  Command: send HDR Frame:****************************************");
                                      	 
                                      	 System.out.println("This is a command: send HDR frame        (0x0003)");      
                                      	 
                                           if(element.getAttribute("name").equals("synphasor.command") )
                                            {                               	   
                                            fileWriter.write("synphasor.command: "+"P"+"\n");
                                            fileWriter.write("value: "+element.getAttribute("value")+"\n");   
                                            fileWriter.write("Command name: "+element.getAttribute("showname")+"\n");                                          
                                            System.out.println("synphasor.command: "+"P");      
                                            System.out.println("synphasor.command: "+element.getAttribute("showname"));
                                            System.out.println("value: "+element.getAttribute("value"));
                                           }else
                                           {
                                            fileWriter.write("synphasor.command: "+"F"+"\n");
                                            fileWriter.write("value: "+element.getAttribute("value")+"\n");      
                                            fileWriter.write("Command name: "+element.getAttribute("showname")+"\n");                                              
                                            System.out.println("synphasor.command: "+"F");                
                                            System.out.println("synphasor.command: "+element.getAttribute("showname"));
                                            System.out.println("value: "+element.getAttribute("value"));                                                                           
                                           }	                                        	 
                                      	 break;
                                      	 
                                       case "send CFG-1 frame":
                                           fileWriter.write("**************************************** Command: send CFG-1 Frame:****************************************\n");                                    
                         				     System.out.println("\n**************************************** Command: send CFG-1 Frame:****************************************");
                                      	 
                                      	 System.out.println("This is a commad: send CFG-1 frame      (0x0004)");   
                                       	   

                                           	                                        	 
                                           break;
                                       case "send CFG-2 frame":
                                           fileWriter.write("**************************************** Command: send CFG-2Frame:****************************************\n");                                    
                         				     System.out.println("\n**************************************** Command: send CFG-2Frame:****************************************");
                                      	 
                                      	 System.out.println("This is a commad: send CFG-2 frame      (0x0005)");
                                           break;

                                       default:
                                           break;
                                  	 } 
                                   }

                          	
                           }
                           else
                           {
                                fileWriter.write("synphasor.command: "+"F"+"\n");
                                fileWriter.write("value: "+element.getAttribute("value")+"\n");      
                                fileWriter.write("Command name: "+element.getAttribute("showname")+"\n");                                              
                                System.out.println("synphasor.command: "+"F");                
                                System.out.println("synphasor.command: "+element.getAttribute("showname"));
                                System.out.println("value: "+element.getAttribute("value"));                                        
                           }                  
                            break; 

                   default: nameOfField = "invalid";
                            break;
               }                         
                            
   //System.out.println("\n========================= End Field:====================================:");                
             
           }//end-if
        }// end-for
        
} catch (IOException e) {
		e.printStackTrace();
} 

}

public void parserHeaderFrame(Element eElement)
{
   try{
       
              //System.out.println("\n==========================Header Frame:====================================:");
		NodeList nList = eElement.getElementsByTagName("field");

              int totalNumOfFiled=nList.getLength();
	        //System.out.println("Total Num. of Field=:"+nList.getLength());
              //fileWriter.write("Num. of Field=:"+nList.getLength()+"\n");     
              
              int NoOfField=0;
              String nameOfField="";
              
		for (int temp = 12; temp < nList.getLength(); temp++) {
		   Node nNode = nList.item(temp);
		   if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		     Element element = (Element) nNode; 
                   //System.out.println("\n========================= Field:====================================:");  
                   
                   //System.out.println("temp=:"+temp);  
                   NoOfField=temp;
                   if(NoOfField>=12)
                   {
                   System.out.println("\nNo. of Field=:"+NoOfField);  
                   fileWriter.write("\nNo. of Field=:"+NoOfField+"\n");
                   }
                   //fileWriter.write("\n");                     
                   
                    switch (NoOfField) {

                         case 12: nameOfField = "DataHeader Frame";
                                   if(element.getAttribute("show").equals("DataHeader Frame") && element.getAttribute("size").equals("6") && element.getAttribute("pos").equals("68") )
                                 {
                                      fileWriter.write("DataHeader Frame: "+"P"+"\n");
                                      fileWriter.write("DataHeader Frame:"+element.getAttribute("show")+"\n");                                        
                                      System.out.println("DataHeader Frame: "+"P");    
                                      //System.out.println("DataHeader Frame: "+element.getAttribute("show"));     
                                      System.out.println("DataHeader Framevalue: "+element.getAttribute("value"));                                             
                                 }
                                 else
                                 {
                                      fileWriter.write("DataHeader Frame: "+"F"+"\n");
                                      fileWriter.write("DataHeader Frame: "+element.getAttribute("show")+"\n");
                                      
                                      System.out.println("DataHeader Frame: "+"F");               
                                      //System.out.println("DataHeader Frame: "+element.getAttribute("show"));    
                                      System.out.println("command value: "+element.getAttribute("value"));                                             
                                 }                  
                                  break;    
                      /*   case 13: nameOfField = "checksum";
                                   if( element.getAttribute("size").equals("2") && element.getAttribute("pos").equals("74"))
                                 {
                                      fileWriter.write("Checksum: "+"P"+"\n");
                                      System.out.println("Checksum: "+"P");         
                                      //System.out.println("Checksum: "+element.getAttribute("show"));
                                      System.out.println("value: "+element.getAttribute("value"));                                        
                                 }
                                 else
                                 {
                                      fileWriter.write("Checksum: "+"F"+"\n");
                                      System.out.println("Checksum: "+"F");           
                                      //System.out.println("Checksum: "+element.getAttribute("shown"));
                                      System.out.println("value: "+element.getAttribute("value"));                                        
                                 }                  
                                  break;    
                                  */                            
                         default: nameOfField = "invalid";
                                  break;
                     }  
                                            
                                  
         //System.out.println("\n========================= End Field:====================================:");       
                            
                 }//end-if
              }// end-for
              


	} catch (IOException e) {
			e.printStackTrace();
	}  
}



public void parserChecksum(int NoOfField, String nameOfField,  Element element)
{
      final int checksum=37;//calculation
       try{
              //System.out.println("\n==========================checksum:====================================:");     
                    switch (NoOfField) {
                            
                         case checksum: nameOfField = "Checksum";
                                   if(element.getAttribute("size").equals("2") && element.getAttribute("pos").equals("134"))
                                 {
                                      fileWriter.write("\nChecksum: "+"P"+"\n");
                                      System.out.println("Checksum: "+"P");         
                                      System.out.println("Checksum: "+element.getAttribute("show"));
                                      System.out.println("value: "+element.getAttribute("value"));                                        
                                 }
                                 else
                                 {
                                      fileWriter.write("\nChecksum: "+"F"+"\n");
                                      System.out.println("Checksum: "+"F");           
                                      System.out.println("Checksum: "+element.getAttribute("show"));
                                      System.out.println("value: "+element.getAttribute("value"));                                        
                                 }                  
                                  break;                                     
                         default: nameOfField = "invalid";
                                  break;
                     }                  

	} catch (IOException e) {
			e.printStackTrace();
	} 
  
}  











//***************************//
public void parserCommandFrame(Element eElement)
{
   try{
       
   
              //System.out.println("\n========================= Command Frame:====================================:");
		NodeList nList = eElement.getElementsByTagName("field");
              int totalNumOfFiled=nList.getLength();
	        //System.out.println("Total Num. of Fields=:"+totalNumOfFiled);
              
              //fileWriter.write("Total Num. of Field=:"+nList.getLength()+"\n");                
              
              int NoOfField=0;
              String nameOfField="";
              
		for (int temp = 0; temp < nList.getLength(); temp++) {
		   Node nNode = nList.item(temp);
             // System.out.println("\n========================= Start Field:====================================:");                      

                   if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		     Element element = (Element) nNode; 
                   NoOfField=temp;
                

                   //System.out.println("temp=:"+temp);    
                   if(NoOfField>=12)
                   {
                   System.out.println("\nNo. of Field=:"+NoOfField);   
                   fileWriter.write("\nNo. of Field=:"+NoOfField+"\n");
                   }
                   int cmdType=0;
                   
                    switch (NoOfField) {

                         case 12: nameOfField = "Command data";
                                   if(element.getAttribute("show").equals("Command data") && element.getAttribute("size").equals("2") && element.getAttribute("pos").equals("68") )
                                 {
                                     if(Integer.parseInt(element.getAttribute("value"),16)>=0 || Integer.parseInt(element.getAttribute("value"),16)<=7)
                                     {
                                      fileWriter.write("synphasor.frtype: "+"P"+"\n");
                                      fileWriter.write("value: "+element.getAttribute("value")+"\n");                                          
                                      System.out.println("synphasor.frtype: "+"P");    
                                      System.out.println("synphasor.frtype: "+element.getAttribute("showname"));     
                                      System.out.println("value: "+element.getAttribute("value")); 
                                     }else
                                     {
                                      fileWriter.write("synphasor.frtype: "+"F"+"\n");
                                      fileWriter.write("value: "+element.getAttribute("value")+"\n");        
                                      fileWriter.write("Command name: "+element.getAttribute("showname")+"\n");                                               
                                      System.out.println("synphasor.frtype: "+"F");               
                                      System.out.println("synphasor.frtype: "+element.getAttribute("showname"));    
                                      System.out.println("value: "+element.getAttribute("value"));                                         
                                     }
                                      
                                 }
                                 else
                                 {
                                      fileWriter.write("synphasor.frtype: "+"F"+"\n");
                                      fileWriter.write("value: "+element.getAttribute("value")+"\n");        
                                      fileWriter.write("Command name: "+element.getAttribute("showname")+"\n");                                               
                                      System.out.println("synphasor.frtype: "+"F");               
                                      System.out.println("synphasor.frtype: "+element.getAttribute("showname"));    
                                      System.out.println("value: "+element.getAttribute("value"));                                             
                                 }                  
                                  break;    
                         case 13: nameOfField = "synphasor.command";
                                   if(element.getAttribute("name").equals("synphasor.command") && element.getAttribute("size").equals("2") && element.getAttribute("pos").equals("68") )
                                 {
                                     if(Integer.parseInt(element.getAttribute("value"),16)>=0 || Integer.parseInt(element.getAttribute("value"),16)<=7 )
                                     {
                                      fileWriter.write("synphasor.command: "+"P"+"\n");
                                      fileWriter.write("value: "+element.getAttribute("value")+"\n");   
                                      fileWriter.write("Command name: "+element.getAttribute("showname")+"\n");                                          
                                      System.out.println("synphasor.command: "+"P");      
                                      System.out.println("synphasor.command: "+element.getAttribute("showname"));
                                      System.out.println("value: "+element.getAttribute("value"));
                                     }else
                                     {
                                      fileWriter.write("synphasor.command: "+"F"+"\n");
                                      fileWriter.write("value: "+element.getAttribute("value")+"\n");      
                                      fileWriter.write("Command name: "+element.getAttribute("showname")+"\n");                                              
                                      System.out.println("synphasor.command: "+"F");                
                                      System.out.println("synphasor.command: "+element.getAttribute("showname"));
                                      System.out.println("value: "+element.getAttribute("value"));                                                                           
                                     }
                                 }
                                 else
                                 {
                                      fileWriter.write("synphasor.command: "+"F"+"\n");
                                      fileWriter.write("value: "+element.getAttribute("value")+"\n");      
                                      fileWriter.write("Command name: "+element.getAttribute("showname")+"\n");                                              
                                      System.out.println("synphasor.command: "+"F");                
                                      System.out.println("synphasor.command: "+element.getAttribute("showname"));
                                      System.out.println("value: "+element.getAttribute("value"));                                        
                                 }                  
                                  break; 
                         case 14: nameOfField = "checksum";
                                   if(element.getAttribute("size").equals("2") && element.getAttribute("pos").equals("70"))
                                 {
                                      fileWriter.write("Checksum: "+"P"+"\n");
                                      fileWriter.write("value: "+element.getAttribute("value")+"\n");                                          
                                      System.out.println("Checksum: "+"P");         
                                      System.out.println("Checksum: "+element.getAttribute("showname"));
                                      System.out.println("value: "+element.getAttribute("value"));                                        
                                 }
                                 else
                                 {
                                      fileWriter.write("Checksum: "+"F"+"\n");
                                      System.out.println("Checksum: "+"F");           
                                      System.out.println("Checksum: "+element.getAttribute("showname"));
                                      System.out.println("value: "+element.getAttribute("value"));                                        
                                 }                  
                                  break;                                
                         default: nameOfField = "invalid";
                                  break;
                     }                         
                                  
         //System.out.println("\n========================= End Field:====================================:");                
                   
                 }//end-if
              }// end-for
              
	} catch (IOException e) {
			e.printStackTrace();
	} 

 
} 

public void parserField(Element element)
{
              System.out.println("\n==========================Field Frame:====================================:");
		NodeList nList = element.getElementsByTagName("field");
	        System.out.println("No. of Field=:"+nList.getLength());
              System.out.println("SubField:*****************************************************************************");     
		for (int temp = 0; temp < nList.getLength(); temp++) {
		   Node nNode = nList.item(temp);
		   if (nNode.getNodeType() == Node.ELEMENT_NODE) {

		     Element subElement = (Element) nNode; 
                      
                   System.out.println("Field Name=:"+subElement.getAttribute("name"));                           
                   System.out.println("showname=:"+subElement.getAttribute("showname"));  
                   System.out.println("size=:"+subElement.getAttribute("size"));      
                   System.out.println("pos=:"+subElement.getAttribute("pos"));  
                   System.out.println("show=:"+subElement.getAttribute("show"));      
                   System.out.println("value=:"+subElement.getAttribute("value"));        
                   System.out.println("unmaskedvalue=:"+subElement.getAttribute("unmaskedvalue"));                       
                   System.out.println("");                      
                   
                 }//end-if
              }// end-for 
}
}