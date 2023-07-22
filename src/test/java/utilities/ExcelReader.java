package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
public static void main(String args[]) throws IOException
	
	{
		System.out.println("Excel Operations");
		//Creating object of file class to open xlsx file
	
		File f = new File("C:/Users/mural/eclipse-workspace/Restassured/src/test/resources/datafile");
		
		//Create an object of FileInputStream class to read excel file
		
		FileInputStream fis = new FileInputStream(f);
		XSSFWorkbook excelWorkbook = new XSSFWorkbook(fis);
		XSSFSheet excelSheet = excelWorkbook.getSheetAt(0);
		int rows = excelSheet.getPhysicalNumberOfRows();
		System.out.println("Total rows:"+rows);
		int cols = excelSheet.getRow(0).getPhysicalNumberOfCells();
		System.out.println("Total colums:"+cols);
		String data[][]= new String[rows][cols];
		XSSFCell cell;
		for(int i =0 ; i< rows;i++)
		{
			for(int j=0;j<cols;j++)
			{
				cell = excelSheet.getRow(i).getCell(j);
				String cellContents=cell.getStringCellValue(); //String.valueOf(i)
				data[i][j] = cellContents;
				System.out.println(data[i][j]);
			}
		}
		excelWorkbook.close();
		fis.close();	
	}

}
