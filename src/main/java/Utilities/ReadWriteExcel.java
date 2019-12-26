package Utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//How to read excel files using Apache POI
public class ReadWriteExcel {

	static FileInputStream fis;
	static XSSFWorkbook workbook;
	static XSSFSheet sheet;
	DataFormatter formatter = new DataFormatter();
	XSSFRow row;
	XSSFCell cell;

	
	public void readExcel(String filePath, String sheetName) throws Exception {

		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		int index = workbook.getSheetIndex(sheetName);		
		
		System.out.println("the sheet index before reading" +index);
		sheet = workbook.getSheetAt(index);
	}

	public Object[][] getUserData(String filePath, String sheetname) throws Exception {
		ReadWriteExcel rws = new ReadWriteExcel();
		rws.readExcel(filePath, sheetname);
		int rowCount = rws.getRowCount(sheetname);
		int colCount = rws.getColumnCount(sheetname);
		Object data[][] = new Object[rowCount][colCount];
		try {
		for (int i = 0 ; i < rowCount; i++) {

			for (int j = 0; j < colCount; j++) {

				data[i][j] = rws.readCell(i, j);
			}
		}
		}catch(Exception e) {
			System.out.println("Error while reading");
		}
		return data;
	}

	public String readCell(int rowNum, int cellNum) throws IOException {
	
		String cellData = "";
		row = sheet.getRow(rowNum);
		if (row == null)
			return cellData;

		cell = row.getCell(cellNum);

		 if (cell == null) {

			return cellData;
		} else {
			cellData = formatter.formatCellValue(cell);
		}
		return cellData;

	}

	public void WriteCell(String filePath,String sheetName,String result, int DR) throws Exception {
		
		fis = new FileInputStream(filePath);
		workbook = new XSSFWorkbook(fis);
		int index = workbook.getSheetIndex(sheetName);		
		System.out.println("the sheet index is "+index);
		
		sheet = workbook.getSheetAt(index);
		ReadWriteExcel rws = new ReadWriteExcel();
		if(DR==0) {
			int colCount=rws.getColumnCount(sheetName);
			sheet.getRow(DR).createCell(colCount-1).setCellValue("Result");
			
		}
		else
		{
			int colCount=rws.getColumnCount(sheetName);
			sheet.getRow(DR).createCell(colCount-1).setCellValue(result);
	
		}		
		FileOutputStream fout = new FileOutputStream(new File(filePath));

		// finally write content

		workbook.write(fout);

		// close the file
		workbook.close();
		fout.close();

	}
	
	public int getRowCount(String sheetName) {
		sheet = workbook.getSheet(sheetName);
		int rowCount = sheet.getLastRowNum() + 1;
		return rowCount;
	}

	public int getColumnCount(String sheetName)
	{
		sheet = workbook.getSheet(sheetName);
		row = sheet.getRow(0);
		int colCount = row.getLastCellNum();
		return colCount;
	}

}
