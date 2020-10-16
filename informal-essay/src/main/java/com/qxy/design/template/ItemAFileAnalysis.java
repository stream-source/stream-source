package com.qxy.design.template;

/**
 * @author wx
 * @date 2020/10/16 3:08 下午
 */
public class ItemAFileAnalysis extends FileAnalysisTemplate{
    @Override
    protected void validateFileTitle() {
        System.out.println("验证项目A的标题列是否缺失");
    }

    @Override
    protected void createPojo() {
        System.out.println("创建项目A对象");
    }

    @Override
    protected boolean specialFileColumn() {
        return true;
    }

    @Override
    protected void handlerSpecialColumn() {
        System.out.println("特殊处理：处理项目名字");
    }
}
