import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class DataStorage {
	private TreeMap<String, List<Integer>> sessionMap = new TreeMap<String, List<Integer>>();
	private int longestFlagName = 0;
	private int interval = 0;
	
	private void addFlagJobber(String flag) {
		if(flag.length() > longestFlagName)
			longestFlagName = flag.length();
		if(sessionMap.containsKey(flag) == false){
			ArrayList<Integer> list = new ArrayList<Integer>();
			for(int i = 0; i < interval; i++)
				list.add(0);
			list.add(1);
			sessionMap.put(flag, list);
		}
		else
			sessionMap.get(flag).set(interval, sessionMap.get(flag).get(interval)+1); //adds 1 jobber to the flag at the current interval
	}
	
	public void nextInterval() {
		interval++;
	
	}
	
//	private void newInterval(TreeMap<>)
	
}
