package com.qxy.design.state;

/**
 * @author wx
 * @date 2020/10/15 4:57 下午
 */
public class LiftStop extends LiftState {
    @Override
    public void openState() {
        super.liftContext.changeState(Constant.OPEN);
        super.liftContext.open();
    }

    @Override
    public void closeState() {
    }

    @Override
    public void runningState() {
        super.liftContext.changeState(Constant.RUNNING);
        super.liftContext.running();
    }

    @Override
    public void stopState() {
        System.out.println("电梯停止...");
    }
}
