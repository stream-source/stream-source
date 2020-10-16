package com.qxy.design.state;

/**
 * @author wx
 * @date 2020/10/15 4:56 下午
 */
public class LiftOpen extends LiftState {
    @Override
    public void openState() {
        System.out.println("电梯开门...");
    }

    @Override
    public void closeState() {
        super.liftContext.changeState(Constant.CLOSE);
        super.liftContext.close();
    }

    @Override
    public void runningState() {
    }

    @Override
    public void stopState() {
        //电梯开门，已经处于停止状态，无需执行其他操作
    }
}
