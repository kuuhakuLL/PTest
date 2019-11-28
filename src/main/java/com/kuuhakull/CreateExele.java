package com.kuuhakull;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CreateExele {

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    CreateExele(ArrayList<Qwest> list, int rezalt, String test, String student) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();HSSFSheet sheet = workbook.createSheet("Employees sheet");

        int rownum = 0;
        Cell cell;
        Row row;
        HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum++);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellStyle(style);
        cell.setCellValue(student);
        row = sheet.createRow(rownum);
        cell.setCellStyle(style);
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("№");
        cell.setCellStyle(style);
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Ответ");
        cell.setCellStyle(style);
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Правильный ответ");
        cell.setCellStyle(style);
        for (Qwest quest : list) {
            rownum++;
            row = sheet.createRow(rownum);

            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(rownum);
            cell.setCellStyle(style);
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(quest.getUserAnswer());
            String correctAnswer="";
            cell.setCellStyle(style);
            for (String item: quest.getCorrectAnswer()){
                correctAnswer += item+"\n";
            }
            cell = row.createCell(2, CellType.STRING);
            cell.setCellValue(correctAnswer);
            cell.setCellStyle(style);
        }
        row = sheet.createRow(++rownum);
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Количество верных ответов:");
        cell.setCellStyle(style);
        cell = row.createCell(3, CellType.NUMERIC);
        cell.setCellValue(rezalt);
        cell.setCellStyle(style);

        File file = new File("/home/kuuhakull/education/Java/Testing/rezalts/" + test + ".xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());
    }

}
