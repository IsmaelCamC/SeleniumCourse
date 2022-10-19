package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import TestComponents.BaseTest;

public class ExcelData extends BaseTest {
	public static void main(String[] args) throws IOException {
		ExcelData excel = new ExcelData();
		
		ArrayList<String> data = excel.getData("Purchase");
		
		System.out.println("Dato 1"+data.get(0));
		System.out.println("Dato 2"+data.get(1));
		System.out.println("Dato 3"+data.get(2));
		System.out.println("Dato 4"+data.get(3));

	}
	// Identify test case column by scanning the entire 1st row
	// Once column is identified then scan entire test case column to identify
	// specific test case row
	// After you grab test case row = pull all the data of that row and
	// feed into test

	public ArrayList<String> getData(String testcaseName) throws IOException {
		// fileInputStream argument
		ArrayList<String> a = new ArrayList<String>();
		File file = new File(System.getProperty("user.dir") + "//src/main/java/resources/exceldata.xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);

		int sheets = workbook.getNumberOfSheets();
		for (int i = 0; i < sheets; i++) {
			if (workbook.getSheetName(i).equalsIgnoreCase("Test Data")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				// Identify test case column by scanning the entire 1st row
				Iterator<Row> rows = sheet.iterator();// sheet is collection of rows
				Row firstrow = rows.next();
				Iterator<Cell> ce = firstrow.cellIterator();// row is collection of cells
				int k = 0;
				int column = 0;
				while (ce.hasNext()) {
					Cell value = ce.next();
					if (value.getStringCellValue().equalsIgnoreCase("Test Case")) {
						column = k;
					}
					k++;
				}
				//System.out.println(column);
				//// once coloumn is identified then scan entire testcase coloum to identify
				//// purcjhase testcase row
				while (rows.hasNext()) {
					Row r = rows.next();
					if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testcaseName)) {
						//// after you grab purchase testcase row = pull all the data of that row and
						//// feed into test
						Iterator<Cell> cv = r.cellIterator();
						while (cv.hasNext()) {
							Cell c = cv.next();
							if (c.getCellType() == CellType.STRING) {
								a.add(c.getStringCellValue());
							} else {
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						}
					}

				}

			}
		}
		return a;
	}

	

}

/*
 * import java.io.File; import java.io.FileInputStream; import
 * java.util.Iterator;
 * 
 * import org.apache.poi.ss.usermodel.Cell; import
 * org.apache.poi.ss.usermodel.Row; import
 * org.apache.poi.xssf.usermodel.XSSFSheet; import
 * org.apache.poi.xssf.usermodel.XSSFWorkbook;
 * 
 * public class ExcelData { public static void main(String[] args) { ExcelData
 * data = new ExcelData(); data.ExcelReading(); }
 * 
 * public static void ExcelReading() { try { FileInputStream file = new
 * FileInputStream( new File(System.getProperty("user.dir")+
 * "//src/main/java/resources/exceldata.xlsx")); XSSFWorkbook workbook = new
 * XSSFWorkbook(file); XSSFSheet sheet = workbook.getSheetAt(0); Iterator<Row>
 * rowIterator = sheet.iterator(); while (rowIterator.hasNext()) { Row row =
 * rowIterator.next(); Iterator<Cell> cellIterator = row.cellIterator();
 * 
 * while (cellIterator.hasNext()) { Cell cell = cellIterator.next(); switch
 * (cell.getCellType()) { case NUMERIC:
 * System.out.print(cell.getNumericCellValue() + " "); break; case STRING:
 * System.out.print(cell.getStringCellValue() + " "); break; } }
 * System.out.println(""); } file.close(); } catch (Exception e) {
 * e.printStackTrace(); } }
 * 
 * }
 */
