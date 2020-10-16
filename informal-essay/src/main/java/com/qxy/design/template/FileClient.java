package com.qxy.design.template;

/**
 * @author wx
 * @date 2020/10/16 3:07 下午
 */
public class FileClient {
    public static void main(String[] args) {
        FileAnalysisTemplate aFileAnalysis = new ItemAFileAnalysis();
        aFileAnalysis.analysisFile();
        System.out.println("-----------");
        FileAnalysisTemplate bFileAnalysis = new ItemBFileAnalysis();
        bFileAnalysis.analysisFile();
    }
}
