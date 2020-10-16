package com.qxy.design.state;

/**
 * @author wx
 * @date 2020/10/15 4:54 下午
 */
public abstract class LiftState {

    protected LiftContext liftContext;

    public void setLiftContext(LiftContext liftContext) {
        this.liftContext = liftContext;
    }

    abstract void openState();

    abstract void closeState();

    abstract void runningState();

    abstract void stopState();
}
