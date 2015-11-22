package com.request.core.hook;

import java.util.TimerTask;

/**
 *
 * @author user
 *
 */
public abstract class HookAdapter extends TimerTask implements Hookable {

    /**
     * Hook que se ejecuta antes de realizar la peticion
     */
    @Override
    public void beforeDo() {
    }

    @Override
    public void run() {
        beforeDo();
        start();
        afterDo();
    }

    /**
     * Hook que se ejecuta despues de realizar la peticion
     */
    @Override
    public void afterDo() {
    }

    /**
     * Hook que se ejecuta siempre, donde debe ir el codigo que se desea hookear
     */
    public abstract void start();

}
