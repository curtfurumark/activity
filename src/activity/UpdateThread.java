/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package activity;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author User
 */
public class UpdateThread implements Runnable{
    private IActivity activity;
    private boolean running = false;
    public UpdateThread(IActivity activity){
        this.activity = activity;
    }
    @Override
    public void run() {
        running = true;
        while (running){
            try {
                activity.update();
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(UpdateThread.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void stop(){
        running = false;
    }
}
