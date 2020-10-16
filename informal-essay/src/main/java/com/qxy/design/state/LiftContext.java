package com.qxy.design.state;


/**
 * @author wx
 * @date 2020/10/15 5:38 下午
 */
public class LiftContext {
    //定义当前电梯状态
    private LiftState liftState;

    public LiftState getLiftState() {
        return liftState;
    }

    public void setLiftState(LiftState liftState) {
        this.liftState = liftState;
        //通知各个实现类中
        this.liftState.setLiftContext(this);
    }

    /**
     * 环境类定义状态转化方法
     * @param state
     */
    public void changeState(String state) {
        switch (state) {
            case Constant.OPEN:
                this.setLiftState(new LiftOpen());
                break;
            case Constant.CLOSE:
                this.setLiftState(new LiftClose());
                break;
            case Constant.RUNNING:
                this.setLiftState(new LiftRunning());
                break;
            case Constant.STOP:
                this.setLiftState(new LiftStop());
                break;
            default:
                break;
        }
    }

    public void open() {
        this.liftState.openState();
    }

    public void close() {
        this.liftState.closeState();
    }

    public void running() {
        this.liftState.runningState();
    }

    public void stop() {
        this.liftState.stopState();
    }


    /**
     * 普通实现结构
     */
    public void liftState(String state) {

        if (Constant.OPEN.equals(state)) {
            //open
            //close
        } else if (Constant.CLOSE.equals(state)) {
            //open
            //close
            //running
            //stop
        } else if (Constant.RUNNING.equals(state)) {
            //running
            //stop
        } else if (Constant.STOP.equals(state)) {
            //open
            //close
            //running
            //stop
        }
    }
}
