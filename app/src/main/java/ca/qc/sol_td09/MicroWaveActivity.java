package ca.qc.sol_td09;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MicroWaveActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView microLblTimer;
    private ImageView imageViewOption1;
    private ImageView imageViewOption2;
    private ImageView imageViewOption3;
    private ImageView imageViewOption4;
    private ImageView imageViewOption5;
    private ImageView imageViewOption6;
    private ImageView imageViewStart;
    private ImageView imageViewStopReset;

    ExecutorService service;
    TimerCountDownRunnable timerCountDownRunnable;
    Future<?> future;
    int nbClickStopResetOption = 0;
    int timeWhenPause = 0;
    String mode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_micro_wave);
        initView();
    }

    private void initView() {
        microLblTimer = (TextView) findViewById(R.id.micro_lbl_timer);
        imageViewOption1 = (ImageView) findViewById(R.id.imageViewOption1);
        imageViewOption2 = (ImageView) findViewById(R.id.imageViewOption2);
        imageViewOption3 = (ImageView) findViewById(R.id.imageViewOption3);
        imageViewOption4 = (ImageView) findViewById(R.id.imageViewOption4);
        imageViewOption5 = (ImageView) findViewById(R.id.imageViewOption5);
        imageViewOption6 = (ImageView) findViewById(R.id.imageViewOption6);
        imageViewStart = (ImageView) findViewById(R.id.imageViewStart);
        imageViewStopReset = (ImageView) findViewById(R.id.imageViewStopReset);

        imageViewOption1.setOnClickListener(this);
        imageViewOption2.setOnClickListener(this);
        imageViewOption3.setOnClickListener(this);
        imageViewOption4.setOnClickListener(this);
        imageViewOption5.setOnClickListener(this);
        imageViewOption6.setOnClickListener(this);
        imageViewStart.setOnClickListener(this);
        imageViewStopReset.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if (v.getId() != R.id.imageViewStopReset)
            nbClickStopResetOption = 0;
        else {
            if (mode.equals("")) { //c'est le 1er click
                mode = "PAUSE";
                nbClickStopResetOption++;
            } else if (mode.equals("PAUSE")) { //c'est le 2eme click
                mode = "RESET";
                nbClickStopResetOption = 0;
            }
        }
        //annuler l'ancienne tâche asynchrone
        if (future != null) {
            future.cancel(true);
        }
        //démarrer une  nouvelle tâche asynchrone
        service = Executors.newSingleThreadExecutor();
        if (v.getId() == R.id.imageViewOption1) {
            microLblTimer.setText(convertSecondsToDisplayFormat(10));
            timerCountDownRunnable = new TimerCountDownRunnable(this, 10, microLblTimer);
        }
        if (v.getId() == R.id.imageViewOption2) {
            microLblTimer.setText(convertSecondsToDisplayFormat(20));
            timerCountDownRunnable = new TimerCountDownRunnable(this, 20, microLblTimer);
        }
        if (v.getId() == R.id.imageViewOption3) {
            microLblTimer.setText(convertSecondsToDisplayFormat(30));
            timerCountDownRunnable = new TimerCountDownRunnable(this, 30, microLblTimer);
        }
        if (v.getId() == R.id.imageViewOption4) {
            microLblTimer.setText(convertSecondsToDisplayFormat(45));
            timerCountDownRunnable = new TimerCountDownRunnable(this, 45, microLblTimer);
        }
        if (v.getId() == R.id.imageViewOption5) {
            microLblTimer.setText(convertSecondsToDisplayFormat(60));
            timerCountDownRunnable = new TimerCountDownRunnable(this, 60, microLblTimer);
        }
        if (v.getId() == R.id.imageViewOption6) {
            microLblTimer.setText(convertSecondsToDisplayFormat(90));
            timerCountDownRunnable = new TimerCountDownRunnable(this, 90, microLblTimer);
        }
        if (v.getId() == R.id.imageViewStart) {
            //Log.d("START_TAG", "START IS CLICKED");
            if (mode.equals("PAUSE")) {
                timerCountDownRunnable = new TimerCountDownRunnable(this, timeWhenPause, microLblTimer);
                future = service.submit(timerCountDownRunnable);
                service.shutdown();
                mode = "";
            } else if (timerCountDownRunnable != null) {
                future = service.submit(timerCountDownRunnable);
                service.shutdown();
            }
        }
        if (v.getId() == R.id.imageViewStopReset) {
            if (mode.equals("RESET")) {
                microLblTimer.setText(convertSecondsToDisplayFormat(0));
                mode = "";
                timeWhenPause = 0;
            } else if (mode.equals("PAUSE")) {
                timeWhenPause = convertDisplayFormatToSeconds(microLblTimer.getText().toString());
            }
        }

    }

    private String convertSecondsToDisplayFormat(int totalSecs) {
        int minutes = (totalSecs % 3600) / 60;
        int seconds = totalSecs % 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    private int convertDisplayFormatToSeconds(String displayFormat) {
        int minutes = Integer.parseInt(displayFormat.substring(0, 2));
        int seconds = Integer.parseInt(displayFormat.substring(3));

        return minutes * 60 + seconds;
    }

}