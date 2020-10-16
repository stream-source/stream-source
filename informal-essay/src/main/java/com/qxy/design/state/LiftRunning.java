package com.qxy.design.state;

/**
 * @author wx
 * @date 2020/10/15 4:58 下午
 */
public class LiftRunning extends LiftState {
    @Override
    public void openState() {
    }

    @Override
    public void closeState() {
    }

    @Override
    public void runningState() {
        System.out.println("电梯运行中...");
    }

    @Override
    public void stopState() {
        super.liftContext.changeState(Constant.STOP);
        super.liftContext.stop();
    }
}
