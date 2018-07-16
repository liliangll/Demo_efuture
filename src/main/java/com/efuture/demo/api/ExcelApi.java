package com.efuture.demo.api;

import com.efuture.demo.annotation.LoginRequired;
import com.efuture.demo.mapper.DriverSchoolMapper;
import com.efuture.demo.model.DriverSchool;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.util.*;
@Slf4j
@RestController
@RequestMapping(value="/api/excel")
public class ExcelApi {
    @Autowired
    private DriverSchoolMapper driverSchoolMapper;


    public void setDriverSchoolMapper(DriverSchoolMapper driverSchoolMapper) {
        this.driverSchoolMapper = driverSchoolMapper;
    }
    @ApiOperation(value = "导出", notes = "导出驾校表信息")
    @LoginRequired
    @RequestMapping(value = "/UserExcelDownloads", method = RequestMethod.GET)
    public void downloadAllClassmate(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
               HSSFSheet sheet = workbook.createSheet("信息表");

        List<DriverSchool> driverSchool = driverSchoolMapper.selectAllUser();

        String fileName = "hello"  + ".xls";//设置要导出的文件的名字
        //新增数据行，并且设置单元格数据

        int rowNum = 1;

        String[] headers = { "驾校ID", "驾校名称", "驾校地址", "驾校电话","区域名称"};
        //headers表示excel表中第一行的表头

        HSSFRow row = sheet.createRow(0);
        //在excel表中添加表头

        for(int i=0;i<headers.length;i++){
            HSSFCell cell = row.createCell(i);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //在表中存放查询到的数据放入对应的列
        for (DriverSchool teacher : driverSchool) {
                HSSFRow row1 = sheet.createRow(rowNum);

                row1.createCell(0).setCellValue(teacher.getDid());
                row1.createCell(1).setCellValue(teacher.getDname());
                row1.createCell(2).setCellValue(teacher.getAddress());
                row1.createCell(3).setCellValue(teacher.getPhone());
                row1.createCell(4).setCellValue(teacher.getCityArea().getName());
                log.debug("------------Name-2-------------"+teacher.getCityArea().getName());
                 rowNum++;
        }
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);

    }
}
