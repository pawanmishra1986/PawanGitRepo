import org.json.JSONArray;

import com.euro.fetch.ExtractDataFromUrl;
import com.euro.generator.ExcelGenerator;

/**
 * @author Pawan Mishra
 *
 */
public class EntryPoint {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String cityname = args[0];
		ExtractDataFromUrl edfu = new ExtractDataFromUrl(cityname);
		JSONArray jsonobject = edfu.fetchAndExtractData();
		ExcelGenerator generateExcel = new ExcelGenerator(jsonobject);
		generateExcel.createExcel();
	}

}
