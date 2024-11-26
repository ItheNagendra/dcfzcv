package api.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

    private String filePath;
    private Workbook workbook;

    public ExcelUtility(String filePath) throws IOException {
        this.filePath = filePath;
        FileInputStream fis = new FileInputStream(filePath);
        this.workbook = new XSSFWorkbook(fis);
    }

    // Read data from Excel
    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        Cell cell = row.getCell(colNum);
        return cell.getStringCellValue();
    }

    // Write data to Excel
    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }
        Cell cell = row.createCell(colNum);
        cell.setCellValue(data);

        // Write the changes to the file
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
    }

    // Get the number of rows in a sheet
    public int getRowCount(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName);
        return sheet.getPhysicalNumberOfRows();
    }

    // Get the number of columns in a row
    public int getColumnCount(String sheetName, int rowNum) {
        Sheet sheet = workbook.getSheet(sheetName);
        Row row = sheet.getRow(rowNum);
        return row.getPhysicalNumberOfCells();
    }

    // Close the workbook
    public void close() throws IOException {
        workbook.close();
    }

    public static void main(String[] args) throws IOException {
        // Example usage:
        String excelFilePath = "path/to/your/excel/file.xlsx";
        ExcelUtility excelUtility = new ExcelUtility(excelFilePath);

        // Read data
        String data = excelUtility.getCellData("Sheet1", 1, 1);
        System.out.println("Data from cell: " + data);

        // Write data
        excelUtility.setCellData("Sheet1", 1, 2, "New Data");

        // Get row and column count
        int rowCount = excelUtility.getRowCount("Sheet1");
        int colCount = excelUtility.getColumnCount("Sheet1", 1);
        System.out.println("Rows: " + rowCount + ", Columns: " + colCount);

        // Close the workbook
        excelUtility.close();
    }
}
