package com.simbiose.excelgenerator.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    private static XSSFSheet excelWSheet;

    private static XSSFWorkbook excelWBook;

    private static XSSFCell cell;

    private static XSSFRow row;

    //This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method
    public static void setExcelFile(String path) throws Exception {

        try {

            // Open the Excel file
            FileInputStream ExcelFile = new FileInputStream(path);

            // Access the required test data sheet
            excelWBook = new XSSFWorkbook(ExcelFile);

        } catch (Exception e) {

            throw (e);

        }

    }

    public static void setSheetName(String sheetName) {
        excelWSheet = excelWBook.getSheet(sheetName);
    }

    //This method is to read the test data from the Excel cell, in this we are passing parameters as Row num and Col num
    public static String getCellData(int rowNum, int colNum) throws Exception {

        try {

            cell = excelWSheet.getRow(rowNum).getCell(colNum);
            String cellData = "";
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_NUMERIC:
                    cellData = String.valueOf((int) (cell.getNumericCellValue()));

                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        cellData = sdf.format(cell.getDateCellValue());
                    }

                    break;
                case Cell.CELL_TYPE_STRING:
                    cellData = cell.getStringCellValue().toString();
                    break;
                default:
                    cellData = "";
                    break;
            }

            //String CellData = Cell.getStringCellValue().toString();
            return cellData;

        } catch (Exception e) {

            return "";

        }

    }

    //This method is to write in the Excel cell, Row num and Col num are the parameters
    public static void setCellData(String result, int rowNum, int colNum) throws Exception {

        try {

            row = excelWSheet.getRow(rowNum);

            if (row == null) {
                row = excelWSheet.createRow(rowNum);
            }

            cell = row.getCell(colNum, row.RETURN_BLANK_AS_NULL);

            if (cell == null) {

                cell = row.createCell(colNum);

                cell.setCellValue(result);

            } else {

                cell.setCellValue(result);

            }

        } catch (Exception e) {

            throw (e);

        }

    }

    public static void saveData(String fileNameXls) {
        try {
            FileOutputStream fileOut = new FileOutputStream(/*Constant.Path_XLS + */fileNameXls);

            excelWBook.write(fileOut);

            fileOut.flush();

            fileOut.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static int getRowSize() {
        return excelWSheet.getLastRowNum() + 1;
    }

    public static void setDefaultSheet() {
        setSheetName("Entradas");
    }
    
    public static int getColSize() {
        Row r = excelWSheet.getRow(0);
        return r.getLastCellNum();
    }
    
    public static void createNewSheet(String sheetName) {
        excelWSheet = excelWBook.createSheet(sheetName);
    }

    public static void removeCell(int rowNum, int colNum) {
        try {
            row = excelWSheet.getRow(rowNum);

            cell = row.getCell(colNum, Row.RETURN_BLANK_AS_NULL);

            row.removeCell(cell);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
