import java.io.IOException;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.TreeMap;


public class JobbingMain {

	private static final int crewID = 5001229; //ID number of the crew we are looking at the jobbers for

	
	TreeMap<String, Integer> jobbingTable = new TreeMap<String, Integer>();
	int longestFlagName = 0;
	WebReader reader = new WebReader();
	public static void main(String[] args) throws IOException, InterruptedException {
		JobbingMain main = new JobbingMain();
		ArrayList<String> jobberList = main.reader.readJobbers(crewID);
		for(int i = 0; i < jobberList.size(); i++) {
			Thread.sleep(2000);
			String flag = main.reader.readFlagOfChar(jobberList.get(i));
			System.out.println(flag);
			main.addTableEntries(flag);
		}
		System.out.println("done reading");
		main.printTable();
	}
	
	//Counts number of jobbers from each flag
	public void addTableEntries(String flag) {
		if(flag.length() > longestFlagName)
			longestFlagName = flag.length();
		if(jobbingTable.containsKey(flag) == false)
			jobbingTable.put(flag, 1);
		else
			jobbingTable.put(flag, jobbingTable.get(flag)+1);
	}

	//prints the jobbing table to the console	
	public void printTable() {
		int totalJobbers = 0;
		//each line must be longestFlagName + 2 in size
		for(Entry<String, Integer> entry : jobbingTable.entrySet()) {
			String line = entry.getKey();
			for(int len = line.length(); len < longestFlagName+2;len++)
				line += " ";
			line += entry.getValue();
			System.out.println(line);
			totalJobbers += entry.getValue();
		}
		System.out.println("Total = " + totalJobbers);
	}
	
}
