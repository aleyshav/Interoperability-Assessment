package PMUPacketAnalyzer;

public class PMUPacketAnayzer {

	//public static void main(String[] args) {
		// TODO Auto-generated method stub

	//}


			
			public static String xmlDataFileName = 	"/Users/arv1/Downloads/Workspace/PMUIoPAnalyzer/src/PMUPacketAnalyzer/pmu4.xml";
			///Users/arv1/Downloads/Workspace/PMUIoPAnalyzer/src/PMUPacketAnalyzer
			
    
	
    // ptah= C:\Users\ysong\workspace\SynchrophasorPacketAnalyzer\test
    public static void main(String[] args) {
 
    	PMUDataXMLParserAndPacketAnalyzer  parserAndAnalyzer = new PMUDataXMLParserAndPacketAnalyzer ();
    	  System.out.println("Start Parsing and Analyzing IEEE C37.118-2005 Protocol==========================");
          parserAndAnalyzer.parsingAndAnalyzingSynphasorProtocol(xmlDataFileName); 
          System.out.println("End Parsing and Analyzing IEEE C37.118-2005 Protocol==========================");  
         
    }
	
	

}
