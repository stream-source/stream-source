package com.qxy.design.template;

/**
 * @author wx
 * @date 2020/10/16 3:10 下午
 */
public class ItemBFileAnalysis extends FileAnalysisTemplate{
    @Override
    protected void validateFileTitle() {
        System.out.println("验证项目B的标题列是否缺失");
    }

    @Override
    protected void createPojo() {
        System.out.println("创建项B对象");
    }
}
