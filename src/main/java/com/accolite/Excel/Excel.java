package com.accolite.Excel;

import com.accolite.Entity.RatesData;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class Excel
{
    RatesData ratesdata;
    Workbook workbook;
    Sheet sheet;
    OutputStream output;
    public Excel(RatesData ratesdata)
    {
        this.ratesdata=ratesdata;
        try
        {
            File f = new File("CurrencySheet.xlsx");
            if (!f.exists())
            {
                OutputStream fout = new FileOutputStream("CurrencySheet.xlsx");
                workbook =new XSSFWorkbook();
                sheet = workbook.createSheet("exchangeData");
            }
            else
            {
                if (f.exists())
                {
                    workbook = new XSSFWorkbook(new FileInputStream("CurrencySheet.xlsx"));
                    sheet=workbook.getSheetAt(0);
                }
            }
            output = new FileOutputStream("CurrencySheet.xlsx");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        int lastrow = sheet.getLastRowNum();
        if(lastrow == -1)
        {
            writeHeader();
        }
        writeToFile();
    }
    private void writeHeader()
    {
        String[] headers = {"Base_Currency","Converted_Currency","Rate","Create_TS"};

        Row row = sheet.createRow(0);
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        Row headerRow=sheet.createRow(0);
        for(int i=0;i<headers.length;i++)
        {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(style);
        }
    }
    private void writeToFile()
    {
        int rowCount=sheet.getLastRowNum();
            Row row = sheet.createRow(++rowCount);
            row.createCell(0).setCellValue(ratesdata.getBase());
            row.createCell(1).setCellValue("USD");
            row.createCell(2).setCellValue(ratesdata.getRates().getUSD());
            row.createCell(3).setCellValue(ratesdata.getDate().toString());
        try
        {
            workbook.write(output);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }
}
