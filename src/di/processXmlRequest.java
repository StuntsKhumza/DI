package di;

import java.io.Reader;
import java.io.StringReader;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

public class processXmlRequest {

	private utils ut = new utils();
	
	public static boolean processResponse(String msgType) {
		
		boolean status = false;
		
		try {
			
			String strMsg = utils.getXMLMessage("MT1Response.txt");
			
			Reader reader = new StringReader(strMsg);
			
			XMLInputFactory xmlfactory = XMLInputFactory.newInstance();
			
			XMLStreamReader xsr;
			
			xsr = xmlfactory.createXMLStreamReader(reader);
			
			
			String SuccessStatus = "";
			
			String sTag = null;
			
			if (msgType.equals("MT1")) 	{ sTag = "initialInstructionResponse"; 		}
			if (msgType.equals("MT40"))	{ sTag = "initialInstructionResponse"; 		}
			if (msgType.equals("MT66")) { sTag = "updateValuationStatusResponse"; 	}
			if (msgType.equals("MT67")) { sTag = "ficaStatusUpdateResponse"; 		}
			if (msgType.equals("MT73")) { sTag = "insuranceLeadsUpdateResponse"; 	}
			if (msgType.equals("MT75")) { sTag = "bondCancellationResponse"; 		}
			if (msgType.equals("MT93")) { sTag = "insurancePdfQuoteResponse"; 		}
			if (msgType.equals("MT201")) { sTag = "cancellationInstructionResponse"; }
			
			xsr.nextTag();
			
			while(!xsr.getLocalName().equals(sTag)) {
				
				System.out.println(xsr.getLocalName());
				
				xsr.nextTag();
				
			}
			
			while(xsr.hasNext()) {
								
				int next = xsr.next();
				
				if(next == XMLStreamReader.START_ELEMENT){
				

					if (msgType.equals("MT1") || msgType.equals("MT40") || msgType.equals("MT201")) {

						if (xsr.getLocalName().equals("status")) {
							
							SuccessStatus = xsr.getElementText();
							
						}
						
						if (xsr.getLocalName().equals("reason")) {
							
							xsr.getEventType();
							
							String Reason = xsr.getElementText();
							
						}
						
					} else {
						
						if (xsr.getLocalName().equals("name")) {
							
							SuccessStatus = xsr.getElementText();
							
						}	
					}
				}
			}

			if (SuccessStatus.equals("Success") || SuccessStatus.equals("true")) {
				
				status = true;
				
			}
			
		} catch (XMLStreamException e) {
			
			e.printStackTrace();
			
		}
		
		return status;
	}
	
}
