package ca.qc.sol_td09;

import android.app.Activity;
import android.widget.TextView;

public class TimerCountDownRunnable implements Runnable{

    Activity activity;
    int totalSecs;
    TextView lblTimer;
    boolean isLoop = true;

    public TimerCountDownRunnable(Activity activity, int totalSecs, TextView lblTimer) {
        this.activity = activity;
        this.totalSecs = totalSecs;
        this.lblTimer = lblTimer;
    }

    @Override
    public void run() {
        int timer = totalSecs;
        final int secondsInit  = timer;
        //init timer
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                lblTimer.setText(convertSecondsToDisplayFormat(secondsInit));
            }
        });
        while(isLoop && timer > 0){
            try {
                Thread.sleep(1000);
                timer--;
                final int secondsEnCours  = timer;
                //mise à jour d'affichage
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        lblTimer.setText(convertSecondsToDisplayFormat(secondsEnCours));
                    }
                });
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public void cancel(){
        isLoop = false;
    }

    private String convertSecondsToDisplayFormat(int totalSecs){
        int minutes = (totalSecs % 3600) / 60;
        int seconds = totalSecs % 60;

        return String.format("%02d:%02d",  minutes, seconds);
    }
}
