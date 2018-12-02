package seleniumTestNGinterview;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingDataFromExcel {

	public String[][] tabArray = null;
	public XSSFWorkbook ExcelWBook;
	public XSSFSheet ExcelWSheet;
	public XSSFCell Cell;
	public XSSFRow Row;

	@BeforeTest
	public void beforeTest() {
		System.out.println("***** Excel Data Provider Test Started *****");
	}

	@AfterTest
	public void afterTest() {
		System.out.println("***** Excel Data Provider Test Finished *****");
	}

	@Test(dataProvider = "excelDataProvider")
	public void TestMethod(String userName, String userPassword) {

		System.out.println("***** TEST Method Invoked*****");

		System.out.println("User Name is : " + userName);
		System.out.println("Password is : " + userPassword);

		System.out.println("***** TEST Method Completed*****");
	}

	@DataProvider
	public Object[][] excelDataProvider() {

		try {
			return getTableArray("..\\..\\..\\Workspace\\MyStudy_Space\\FilePath\\ExcelDataProvider.xlsx", "Authentication Data");
		} catch (Exception e) {
			e.printStackTrace();
			return tabArray;
		}

	}

	public Object[][] getTableArray(String FilePath, String SheetName) throws Exception {

		try {

			FileInputStream ExcelFile = new FileInputStream(new File(FilePath));

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(SheetName);

			int startRow = 1;
			int startCol = 1;
			int totalRows = ExcelWSheet.getLastRowNum();
			int totalCols = 2;

			int ci, cj;

			tabArray = new String[totalRows][totalCols];

			ci = 0;

			for (int i = startRow; i <= totalRows; i++, ci++) {

				cj = 0;

				for (int j = startCol; j <= totalCols; j++, cj++) {

					tabArray[ci][cj] = getCellData(i, j);

				}

			}

		}

		catch (FileNotFoundException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		catch (IOException e) {

			System.out.println("Could not read the Excel sheet");

			e.printStackTrace();

		}

		return (tabArray);

	}

	@SuppressWarnings({ "deprecation", "static-access" })
	public String getCellData(int RowNum, int ColNum) throws Exception {

		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

		if (Cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			Double d = Cell.getNumericCellValue();
			return d.toString();
		} else if (Cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			Boolean b = Cell.getBooleanCellValue();
			return b.toString();
		} else {
		}
		return Cell.getStringCellValue();
	}
}
