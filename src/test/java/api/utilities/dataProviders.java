package api.utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class dataProviders {

    private String filePath;
    private Workbook workbook;

    // Constructor to initialize ExcelUtility with file path
    public dataProviders(String filePath) throws IOException {
        this.filePath = filePath;
        FileInputStream fis = new FileInputStream(filePath); // Open the Excel file
        this.workbook = new XSSFWorkbook(fis); // Load workbook for .xlsx files
    }

    // Method to get all data from the sheet as a 2D Object array
    public Object[][] getAllData(String sheetName) {
    	
        Sheet sheet = workbook.getSheet(sheetName); // Get the sheet by name
        int rowCount = sheet.getPhysicalNumberOfRows(); // Get the number of rows
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells(); // Get the number of columns

        Object[][] data = new Object[rowCount - 1][colCount]; // Create a 2D array to store data

        for (int i = 1; i < rowCount; i++) { // Start from row 1 (skip header row)
            Row row = sheet.getRow(i);
            for (int j = 0; j < colCount; j++) { // Loop through columns
                Cell cell = row.getCell(j);
                if (cell != null) {
                    data[i - 1][j] = cell.toString(); // Store the cell data in the 2D array
                }
            }
        }
        return data;
    }

    // Method to get only the usernames from the Excel sheet
    public Object[][] getUsernames(String sheetName) {
        Sheet sheet = workbook.getSheet(sheetName); // Get the sheet by name
        int rowCount = sheet.getPhysicalNumberOfRows(); // Get the number of rows

        Object[][] data = new Object[rowCount - 1][1]; // 2D array with 1 column for usernames

        for (int i = 1; i < rowCount; i++) { // Start from row 1 (skip header row)
            Row row = sheet.getRow(i);
            Cell cell = row.getCell(0); // Assuming that the username is in the first column
            if (cell != null) {
                data[i - 1][0] = cell.toString(); // Store the username in the 2D array
            }
        }
        return data;
    }

    // Method to close the workbook after operations
    public void close() throws IOException {
        workbook.close(); // Close the workbook to release resources
    }
}
