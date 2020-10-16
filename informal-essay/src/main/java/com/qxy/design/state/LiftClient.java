package com.qxy.design.state;

/**
 * @author wx
 * @date 2020/10/15 10:05 下午
 */
public class LiftClient {
    public static void main(String[] args) {

        LiftContext liftContext = new LiftContext();
        liftContext.changeState(Constant.RUNNING);
        liftContext.open();
        liftContext.close();
        liftContext.running();
        liftContext.stop();

    }
}
