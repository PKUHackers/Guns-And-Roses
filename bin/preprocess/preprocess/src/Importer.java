
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;


/**
 * import data from local file to HBase
 * 
 * @author R.J.Y
 * @version 1.0
 */
public class Importer {

	
	
	/**
	 * run the tool to import data from local file
	 * 
	 * @param filePath	-directory stores backed up data
	 * @param outputFile -output file
	 */
	public static void run(String filePath, String outputFile){
		// return if the directory is not exists
		File file = new File(filePath);
		if(!file.exists()){
			return;
		}
		
		importFromLocal(file, outputFile, "UTF-8");
	}
	
	
	
	
	/**
	 * import data from local file
	 * 
	 * @param fileName		-file name stores the data to be imported
	 * @param outputFile	-file to output
	 * @param encodeType	-encode type of the input file
	 */
	public static void importFromLocal(File file, String outputFile, String encodeType){
		// temporal result
		String result = "";
		String line = "";
		
		try{
			
			// create buffered reader
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), encodeType));
			
			News news = new News();
			
			// read
			line = br.readLine();
			while(line != null){
				result += line;
				
				// the end of a record
				if(line.endsWith("</News>")){
					news.fromXML(result);
					FileOperator.append(outputFile, news.toXML());
					result = "";
				}
				
				line = br.readLine();
			}
			
			
			// close
			br.close();
			
		} catch (UnsupportedEncodingException e) {
			System.out.println("In the Method importFromLocal: " + e.toString());
		} catch (FileNotFoundException e) {
			System.out.println("In the Method importFromLocal: " + e.toString());
		} catch (IOException e) {
			System.out.println("In the Method importFromLocal: " + e.toString());
		}
	}
	
	
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args){
		Importer.run(args[0], args[1]);
	}
}