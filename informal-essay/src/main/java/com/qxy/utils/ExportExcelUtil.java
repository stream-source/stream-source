package com.qxy.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:wx
 * @Date:2020/4/30 17:00
 */
public class ExportExcelUtil {

    private static final String RULE = "^//d+(//.//d+)?$";

    /**
     * 导出excel文件，表头为一维数组表示不用合并单元格
     *
     * @param sheetName
     * @param excelTitle
     * @param dataCollection
     * @param excelFields    如果为空：代表不是自定义导出；如过是自定义导出，则是配置的所有fields
     * @param <T>
     * @return
     */
    public static <T> HSSFWorkbook generateExcel(String sheetName, String[] excelTitle, Collection<T> dataCollection, String[] excelFields) {
        //创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        //创建一个Sheet表格工作空间
        HSSFSheet sheet = workbook.createSheet(sheetName);
        HSSFCellStyle style = workbook.createCellStyle();
        //设置表格默认宽度
        sheet.setDefaultColumnWidth(20);
        //设置表格的表头
        HSSFCell cellHeader;
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < excelTitle.length; i++) {
            //创建单元格表头
            cellHeader = row.createCell(i);
            cellHeader.setCellValue(new HSSFRichTextString(excelTitle[i]));
        }
        //匹配表头设置单元格的值
        setWorkBookValue(sheet, dataCollection, 0, style, excelFields);

        return workbook;
    }

    /**
     * 导出excel，表头为二维数组，表示有合并单元格类型的表头
     *
     * @param sheetName
     * @param excelTitle
     * @param dataCollection
     * @param <T>
     * @return
     */
    public static <T> HSSFWorkbook exportMergeCell(String sheetName, String[][] excelTitle, Collection<T> dataCollection, String[] excelFields) {
        //创建一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFCellStyle style = workbook.createCellStyle();
        //创建一个Sheet表格工作空间
        HSSFSheet sheet = workbook.createSheet(sheetName);
        //合并数
        int mergerNum = 0;
        //给单元格设置值
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        for (int i = 0; i < excelTitle.length; i++) {
            HSSFRow row = sheet.createRow(i);
            row.setHeight((short) 400);
            for (int j = 0; j < excelTitle[i].length; j++) {
                HSSFCell cell = row.createCell(j);
                cell.setCellStyle(style);
                cell.setCellValue(excelTitle[i][j]);
            }
        }
        // 合并行时要跳过的行列
        Map<Integer, List<Integer>> map = new HashMap<>(16);
        //合并列
        for (int i = 0; i < excelTitle[excelTitle.length - 1].length; i++) {
            if ("".equals(excelTitle[excelTitle.length - 1][i])) {
                for (int j = excelTitle.length - 2; j >= 0; j--) {
                    if (!"".equals(excelTitle[j][i])) {
                        // 合并单元格
                        sheet.addMergedRegion(new CellRangeAddress(j, excelTitle.length - 1, i, i));
                        break;
                    } else {
                        if (map.containsKey(j)) {
                            List<Integer> list = map.get(j);
                            list.add(i);
                            map.put(j, list);
                        } else {
                            List<Integer> list = new ArrayList<>();
                            list.add(i);
                            map.put(j, list);
                        }
                    }
                }
            }
        }
        //合并行
        for (int i = 0; i < excelTitle.length - 1; i++) {
            for (int j = 0; j < excelTitle[i].length; j++) {
                List<Integer> list = map.get(i);
                if (list == null || (!list.contains(j))) {
                    if ("".equals(excelTitle[i][j])) {
                        mergerNum++;
                        if (mergerNum != 0 && j == (excelTitle[i].length - 1)) {
                            // 合并单元格
                            sheet.addMergedRegion(new CellRangeAddress(i, i, j - mergerNum, j));
                            mergerNum = 0;
                        }
                    } else {
                        if (mergerNum != 0) {
                            // 合并单元格
                            sheet.addMergedRegion(new CellRangeAddress(i, i, j - mergerNum - 1, j - 1));
                            mergerNum = 0;
                        }
                    }
                }
            }
        }
        setWorkBookValue(sheet, dataCollection, 1, style, excelFields);
        return workbook;
    }


    /**
     * excel 导出
     *
     * @param response
     * @param workbook
     * @param fileName
     * @throws IOException
     */
    public static void exportExcel(HttpServletResponse response, HSSFWorkbook workbook, String fileName) throws IOException {
        if (workbook != null) {
            response.reset();
            //指定下载的文件名
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
            StringBuilder sb = new StringBuilder();
            sb.append("attachment;filename=");
            try {
                sb.append(URLEncoder.encode(fileName, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                sb.append(fileName);
            }
            sb.append("_");
            sb.append(sdf.format(new Date()));
            sb.append(".xls");
            response.setHeader("Content-Disposition", sb.toString());
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            BufferedOutputStream bufferedOutput = null;
            try {
                bufferedOutput = new BufferedOutputStream(response.getOutputStream());
                workbook.write(bufferedOutput);
                bufferedOutput.flush();
            } catch (IOException e) {
                throw e;
            } finally {
                if (bufferedOutput != null) {
                    try {
                        bufferedOutput.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    /**
     * （根据自定义）把具体数据写入到excel中
     *
     * @param sheet
     * @param dataCollection
     * @param index
     * @param style
     * @param <T>
     */
    private static <T> void setWorkBookValue(HSSFSheet sheet, Collection<T> dataCollection, int index, HSSFCellStyle style, String[] excelFields) {
        T t;
        Object[] fields;
        HSSFRichTextString richTextString;
        Pattern pattern = Pattern.compile(RULE);
        Matcher matcher;
        String fieldName;
        String getMethodName;
        HSSFCell cell;
        HSSFRow row;
        Class tClass;
        Method getMethod;
        Object value;
        String textValue;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //遍历集合设置单元格值
        Iterator<T> it = dataCollection.iterator();

        //如果是自定义参数为true，否则false
        boolean selfDefined = false;
        if (excelFields != null && excelFields.length > 0) {
            selfDefined = true;
        }
        while (it.hasNext()) {
            //创建一行单元格
            index++;
            row = sheet.createRow(index);
            //获取数据
            t = it.next();
            if (selfDefined) {
                fields = excelFields;
            } else {
                //利用反射，根据JavaBean属性的先后顺序，动态调用getXxx()方法得到属性值
                fields = t.getClass().getDeclaredFields();
            }
            for (int i = 0; i < fields.length; i++) {
                cell = row.createCell(i);
                cell.setCellStyle(style);
                if (selfDefined) {
                    fieldName = (String) fields[i];
                } else {
                    //利用反射，根据JavaBean属性的先后顺序，动态调用getXxx()方法得到属性值
                    Field[] newFields = t.getClass().getDeclaredFields();
                    fieldName = newFields[i].getName();
                }
                getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                try {
                    tClass = t.getClass();
                    getMethod = tClass.getMethod(getMethodName, new Class[]{});
                    value = getMethod.invoke(t, new Object[]{});
                    if ("getCheckFlag".equals(getMethodName)) {
                        value = 1 == (Integer) value ? "正常" : "异常";
                    }
                    textValue = null;
                    if (!StringUtils.isEmpty(value)) {
                        //value进行类型转换
                        if (value instanceof Integer) {
                            cell.setCellValue((Integer) value);
                        } else if (value instanceof Float) {
                            textValue = String.valueOf(value);
                            cell.setCellValue(textValue);
                        } else if (value instanceof Double) {
                            textValue = String.valueOf(value);
                            cell.setCellValue(textValue);
                        } else if (value instanceof Long) {
                            cell.setCellValue((Long) value);
                        } else if (value instanceof Boolean) {
                            textValue = "是";
                            if (!(Boolean) value) {
                                textValue = "否";
                            }
                        } else if (value instanceof Date) {
                            textValue = sdf.format((Date) value);
                        } else {
                            // 其它数据类型都当作字符串简单处理
                            textValue = value.toString();
                        }
                        if (textValue != null) {
                            matcher = pattern.matcher(textValue);
                            if (matcher.matches()) {
                                // 是数字当作double处理
                                cell.setCellValue(Double.parseDouble(textValue));
                            } else {
                                richTextString = new HSSFRichTextString(textValue);
                                cell.setCellValue(richTextString);
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
