import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class WebReader {
	
	/*returns a array list of charNames that are jobbed to the specified crew
	  throws an IOExpction if the crew name is invalid
	*/
	public ArrayList<String> readJobbers(int crewID) throws IOException{
		ArrayList<String> jobberList = new ArrayList<String>();
		// Make a URL to the web page
        URL url = new URL("http://obsidian.puzzlepirates.com/yoweb/crew/info.wm?crewid=" + crewID);

        // Get the input stream through URL Connection
        URLConnection con = url.openConnection();
        InputStream is =con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));	
        
        String line = null;
        
        //To get to Jobbing Pirates we need to look for the 3 places Jobbing pirates appears (it appears 2 times before we get to the info we want)
        int appearanceCount = 0;
        while((line = br.readLine()) != null) {
        	if(line.contains("Jobbing Pirate"))
        		appearanceCount++;
        	if(line.contains("Extended Public Statement"))
        		appearanceCount = -9999;
        	if(appearanceCount != 3)
        		continue;
        	if(line.contains("pirate.wm?classic=$classic&target=")) {
        	//	System.out.println(line);
        		int indexStart = line.indexOf("target=")+7;
        		int indexEnd = (line.substring(indexStart)).indexOf("\">");
        		String jobber = line.substring(indexStart, indexStart+indexEnd);
        		jobberList.add(jobber);
        	}
        }
        
		return jobberList;
	}
	
	/*reads the flag of the character given by charName
	  if the character has no associated flag this returns "Independent"
	  throws an IOException if the charName is invalid
	*/
    public String readFlagOfChar(String charName) throws IOException {
    	 // Make a URL to the web page
        URL url = new URL("http://obsidian.puzzlepirates.com/yoweb/pirate.wm?classic=true&target=" + charName);

        // Get the input stream through URL Connection
        URLConnection con = url.openConnection();
        InputStream is =con.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));

    	String line = null;
    	String flag = "Independent";
    	// read each line and write to System.out the flag that was found
    	while ((line = br.readLine()) != null) {
    		if(line.contains("flag ")) {
    			String searchString = "=true\">";
    			//Index Start occurs at the beginning of the flag name after the hyperlink to the flag page
    			int indexStart = line.indexOf(searchString);
    			// index End is the end html format after the flag name
    			int indexEnd = line.indexOf("</a></b></font>");
    			flag = line.substring(indexStart+searchString.length(), indexEnd);
    			break;
    		}
    	}
		return flag;
    }
}