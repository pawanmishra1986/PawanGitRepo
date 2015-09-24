package com.euro.generator;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Pawan Mishra
 *
 */
public class ExcelGenerator {

	private JSONArray json;
	
	public ExcelGenerator(JSONArray json){
		this.json = json;
	}
	public void createExcel(){
	try {
		FileOutputStream fileOut = new FileOutputStream("GoEuroTest.xls");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet worksheet = workbook.createSheet("City Deatils");
		createHeader(worksheet);
		createData(worksheet,json);

		workbook.write(fileOut);
		fileOut.flush();
		fileOut.close();
	} catch (FileNotFoundException e) {
		System.out.println("Unable to create file : " + e.getMessage());
	} catch (IOException ex) {
		System.out.println("Unable to create file : " + ex.getMessage());
	}
	}

	private void createHeader(HSSFSheet worksheet){
		HSSFRow row1 = worksheet.createRow((short) 0);
		HSSFCell idCell = row1.createCell((short) 0);
		idCell.setCellValue("ID");

		HSSFCell nameCell = row1.createCell((short) 1);
		nameCell.setCellValue("Name");

		HSSFCell typeCell = row1.createCell((short) 2);
		typeCell.setCellValue("Type");

		HSSFCell latCell = row1.createCell((short) 3);
		latCell.setCellValue("Latitude");
		
		HSSFCell lonCell = row1.createCell((short) 4);
		lonCell.setCellValue("Longitude");
	}
	
	private void createData(HSSFSheet worksheet,JSONArray json){
		if(json != null){
			
			for(int i=0;i < json.length();i++){
				JSONObject obj = (JSONObject)json.get(i);
				HSSFRow row1 = worksheet.createRow((short) i+1);

				HSSFCell idCell = row1.createCell((short) 0);
				idCell.setCellValue(obj.getInt("_id"));

				HSSFCell nameCell = row1.createCell((short) 1);
				nameCell.setCellValue(obj.getString("name"));

				HSSFCell typeCell = row1.createCell((short) 2);
				typeCell.setCellValue(obj.getString("type"));
				
				JSONObject geoObject = obj.getJSONObject("geo_position");
				HSSFCell latCell = row1.createCell((short) 3);
				latCell.setCellValue(geoObject.getDouble("latitude"));
				
				HSSFCell lonCell = row1.createCell((short) 4);
				lonCell.setCellValue(geoObject.getDouble("longitude"));
			}
		}
		
	}
}
