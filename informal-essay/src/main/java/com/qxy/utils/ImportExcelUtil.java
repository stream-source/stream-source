package com.qxy.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * excel文件解析
 *
 * @author qxy
 */
public class ImportExcelUtil {

    private static final String EXCEL_XLS_SUFFIX = ".xls";

    private static final String EXCEL_XLSX_SUFFIX = ".xlsx";

    private static Logger logger = LoggerFactory.getLogger(ImportExcelUtil.class);

    private ImportExcelUtil() {
    }

    /**
     * 读入excel文件，解析后返回
     *
     * @param filePath
     * @param sheelNum      sheel编号，从0开始
     * @param firstRowIndex 起始行，从0开始，如果第一行是列名，不读，则firstRowIndex为1
     * @throws IOException
     */
    public static List<List<String>> readExcel(String filePath, int sheelNum, int firstRowIndex) throws IOException {
        List<List<String>> list = new ArrayList<>(16);
        Workbook wb = null;
        try {
            File excel = new File(filePath);
            // 判断文件是否存在
            if (excel.isFile() && excel.exists()) {
                // .是特殊字符，需要转义！！！！！
                String[] split = excel.getName().split("\\.");
                // 根据文件后缀（xls/xlsx）进行判断
                if ("xls".equals(split[1])) {
                    try (FileInputStream fis = new FileInputStream(excel)) {
                        wb = new HSSFWorkbook(fis);
                    }
                } else if ("xlsx".equals(split[1])) {
                    wb = new XSSFWorkbook(excel);
                } else {
                    return list;
                }
                // 开始解析
                // 读取sheet 0
                Sheet sheet = wb.getSheetAt(sheelNum);
                int lastRowIndex = sheet.getLastRowNum();
                // 遍历行
                for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int lastCellIndex = row.getLastCellNum();
                        List<String> cellList = new ArrayList<>();
                        // 遍历列
                        for (int cIndex = 0; cIndex < lastCellIndex; cIndex++) {
                            Cell cell = row.getCell(cIndex);
                            if (cell != null) {
                                cellList.add(cell.toString());
                            } else {
                                cellList.add("");
                            }
                        }
                        list.add(cellList);
                    }
                }
            } else {
                return list;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (wb != null) {
                wb.close();
            }
        }
        return list;
    }

    public static List<Map<Integer, Object>> readExcelContent(String filepath) throws Exception {
        return readExcelContent(filepath, null, null);
    }


    /**
     * 读取Excel数据内容
     *
     * @param rowIndex    指定行号
     * @param columnIndex 指定列号
     * @return Map 包含单元格数据内容的Map对象
     */
    public static List<Map<Integer, Object>> readExcelContent(String filepath, Integer rowIndex, Integer columnIndex) throws Exception {
        List<Map<Integer, Object>> returnList = new LinkedList<>();
        Workbook wb = null;
        Sheet sheet;
        Row row;
        try {
            InputStream is = new FileInputStream(filepath);
            if (filepath.endsWith(EXCEL_XLS_SUFFIX)) {
                wb = new HSSFWorkbook(is);
            } else if (filepath.endsWith(EXCEL_XLSX_SUFFIX)) {
                wb = new XSSFWorkbook(is);
            }

            if (wb == null) {
                throw new Exception("Workbook对象为空！");
            }

            sheet = wb.getSheetAt(0);
            //解析文件总行数、总列数
            int rowNum = rowIndex != null ? rowIndex : sheet.getLastRowNum();
            row = sheet.getRow(0);
            int colNum = columnIndex != null ? columnIndex : row.getLastCellNum();

            //循环列
            for (int colIndex = colNum; colIndex > 0; colIndex--) {
                Cell cell = row.getCell(colIndex);
                if (cell != null && !"".equals(cell.toString())) {
                    colNum = colIndex;
                    break;
                }
            }
            logger.info("have data col:{}", colNum);

            // 正文内容应该从第二行开始,第一行为表头的标题
            for (int i = 0; i <= rowNum; i++) {
                row = sheet.getRow(i);
                int j = 0;

                int size = (int) (colNum / .75f) + 1;
                //存储单元格数据
                Map<Integer, Object> cellValue = new LinkedHashMap<>(size);

                if (row == null) {
                    continue;
                }

                while (j <= colNum) {
                    Cell cell = row.getCell(j);
                    String value = "";
                    //日期单元格需格式化日期
                    if (cell != null) {
                        if (cell.getCellType() == CellType.NUMERIC) {
                            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                                Date d = cell.getDateCellValue();
                                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                value = formatter.format(d);
                            } else if (cell.toString().contains("E")) {
                                DecimalFormat nf = new DecimalFormat("0");
                                value = nf.format(cell.getNumericCellValue());
                            } else {
                                value = cell.toString().endsWith(".0") ? cell.toString().replace(".0", "") : cell.toString().trim();
                            }
                        } else if (cell.getCellType() == CellType.FORMULA) {
                            value = String.valueOf(cell.getNumericCellValue());
                        } else {
                            value = cell.toString().trim();
                        }
                    }
                    cellValue.put(j, value);
                    j++;
                }
                Set set = cellValue.entrySet();
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    if (!"".equals(entry.getValue())) {
                        returnList.add(cellValue);
                        break;
                    }
                }
            }
            wb.close();
        } catch (FileNotFoundException e) {
            logger.error("FileNotFoundException", e);
        } catch (IOException e) {
            logger.error("IOException", e);
        } finally {
            if (wb != null) {
                wb.close();
            }
        }
        return returnList;
    }

}