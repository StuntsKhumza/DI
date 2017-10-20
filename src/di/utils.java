package di;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFrame;

public class utils {

	public static String getXMLMessage(String filename){
		
		String response = "";
		
		
		try{
			
			 
			BufferedReader br = new BufferedReader(new FileReader(System.getProperty("user.dir") +  "/src/messages/file.txt"));
			
			try {
				
			    StringBuilder sb = new StringBuilder();
			    
			    String line = br.readLine();

			    while (line != null) {
			    	
			        sb.append(line);
			        
			        sb.append(System.lineSeparator());
			        
			        line = br.readLine();
			        
			    }
			    			    
			    response = sb.toString();
			    
			    return response;
			    
			} finally {
				
			    br.close();
			    
			}
			
		}catch(Exception ex){
			
			ex.printStackTrace();
			
		}
		
		return response;
		
	}
	
}
