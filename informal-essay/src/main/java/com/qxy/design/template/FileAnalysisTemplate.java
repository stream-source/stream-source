package com.qxy.design.template;

/**
 * @author wx
 * @date 2020/10/16 2:30 下午
 */
public abstract class FileAnalysisTemplate {

    public final void analysisFile() {
        //1.验证文件后缀
        validateFileSuffix();
        //2.解析文件内容
        analysisFileLine();
        //3.验证文件标题列
        validateFileTitle();
        //处理钩子方法
        if (specialFileColumn()) {
            handlerSpecialColumn();
        }
        //4.封装pojo
        createPojo();
        //5.交互数据库
        operateDataSource();
    }


    protected void validateFileSuffix() {
        System.out.println("验证文件后缀成功：文件后缀为.xls或者.xlsx");
    }

    protected void analysisFileLine() {
        System.out.println("文件解析成功：共用一套解析文件方法或者工具类");
    }

    protected abstract void validateFileTitle();

    protected abstract void createPojo();

    protected void operateDataSource() {
        System.out.println("数据入库成功");
    }

    /**
     * 是否特殊处理列数据
     * @return false
     */
    protected boolean specialFileColumn() {
        return false;
    }

    /**
     * 钩子方法：交给子类具体实现处理
     */
    protected void handlerSpecialColumn() {

    }


}
