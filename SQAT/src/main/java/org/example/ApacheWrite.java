package org.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ApacheWrite {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\offic\\IdeaProjects\\SQAT\\testfiles\\ExcelTest.xlsx";
        FileInputStream fs = new FileInputStream(path);

        Workbook wb = new XSSFWorkbook(fs);
        Sheet sheet1 = wb.getSheetAt(0);

        int lastRow = sheet1.getLastRowNum();
        for(int i=1; i<=lastRow; i++){
            Row row = sheet1.getRow(i);
            Cell cell = row.createCell(2);
            cell.setCellValue("STATUS");
        }

        FileOutputStream fos = new FileOutputStream(path);
        wb.write(fos);
        fos.close();
    }

}