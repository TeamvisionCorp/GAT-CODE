package cn.gateside.gattmg.extents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gateside.autotesting.Lib.excelservice.ExcelReader;
import com.gateside.autotesting.Lib.excelservice.ExtensionType;




public class ExcelExtents {

	private static ExcelReader excelReader = new ExcelReader();
	private static ExtensionType extType = null;

	/**
	 * @param cell   get excel value
	 * @return String
	 */
	public static String getValue(XSSFCell cell) {
		String value = "";
		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			value = cell.getRichStringCellValue().getString();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
				java.util.Date date = cell.getDateCellValue();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				value = format.format(date);
			} else {
				value = String.valueOf(cell.getNumericCellValue());
			}
			break;
		case Cell.CELL_TYPE_BOOLEAN:
			value = " " + cell.getBooleanCellValue();
			break;
		case Cell.CELL_TYPE_FORMULA:
			value = cell.getCellFormula();
			break;
		default:

		}
		return value;
	}

	/**
	 * 
	 * @param wholeFilePath  excle file path 
	 * @return String list
	 * @throws FileNotFoundException ex
	 * @throws IOException ex
	 */
	@SuppressWarnings("deprecation")
	public static List<String> getSheetNameList(String wholeFilePath) throws FileNotFoundException, IOException {
		List<String> sheetNameList = new ArrayList<String>();
		
		if (wholeFilePath.endsWith(".xls")) {
			HSSFWorkbook workBook = null;
			HSSFSheet workSheet = null;
			workBook = new HSSFWorkbook(new FileInputStream(new File(wholeFilePath)));
			for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
				workSheet = workBook.getSheetAt(i);
				sheetNameList.add(workSheet.getSheetName());
			}
		} else if(wholeFilePath.endsWith(".xlsx")){
			XSSFWorkbook workBook = null;
			String workSheet = null;
			workBook = new XSSFWorkbook(wholeFilePath);
			for (int i = 0; i < workBook.getNumberOfSheets(); i++) {
				workSheet = workBook.getSheetName(i);
				sheetNameList.add(workSheet);
			}
		}
		return sheetNameList;
	}

	/**
	 * 
	 * @param filePath Excel file path
	 * @param eachSheetName excel sheet name
	 * @return Integer sheet row
	 */
	public static Integer getSheetRowCounts(String filePath, String eachSheetName) {
		List<List<String>> allData = new ArrayList<List<String>>();
		
		if(filePath.endsWith(".xls")){
			extType = ExtensionType.XLS;
			allData = excelReader.readAllData(filePath, eachSheetName);
		}else if(filePath.endsWith(".xlsx")){
			extType = ExtensionType.XLSX;
			allData = excelReader.readAllData(filePath, eachSheetName);
		}
		return allData.size();
	}

	/**
	 * 
	 * @param filePath excel file path
	 * @param sheetName  excel sheet name
	 * @param rowIndex excel row index
	 * @return Object
	 */
	public static Object getSheetRow(String filePath, String sheetName, Integer rowIndex) {	
		Object workRow = null;
		
		if(filePath.endsWith(".xls")){
			extType = ExtensionType.XLS;
			Object workBook = excelReader.getWorkBook(filePath, extType);
			Object workSheet = excelReader.getWorkSheet(workBook, sheetName, extType);
			HSSFSheet hssfworkSheet = (HSSFSheet) workSheet;
			workRow = hssfworkSheet.getRow(rowIndex);
		} else if(filePath.endsWith(".xlsx")){
			extType = ExtensionType.XLSX;
			Object workBook = excelReader.getWorkBook(filePath, extType);
			Object workSheet = excelReader.getWorkSheet(workBook, sheetName, extType);
			XSSFSheet xssfWorkSheet = (XSSFSheet) workSheet;
			workRow = xssfWorkSheet.getRow(rowIndex);
		} else {
			workRow = null;
		}
		return workRow;
	}
	
	/*
	 * @param workRow  excel row
	 * @param extType xls xlsx
	 * @return  String
	 */
	@SuppressWarnings("deprecation")
	public static String getFirstColElement(String filePath, Object workRow) throws Exception {
		String firstCell = null;
		
		if(filePath.endsWith(".xls")){
			extType = ExtensionType.XLS;
			HSSFRow hssfRow = (HSSFRow) workRow;
			firstCell = excelReader.getValue(hssfRow.getCell(hssfRow.getFirstCellNum()));
		} else if(filePath.endsWith(".xlsx")){
			extType = ExtensionType.XLSX;
			XSSFRow xssfRow = (XSSFRow) workRow;
			firstCell = getValue(xssfRow.getCell(xssfRow.getFirstCellNum()));
		} else {
			firstCell = null;
		}
		return firstCell;
	}
}
