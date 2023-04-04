package ca.qc.sol_td09;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MicroWaveActivity extends AppCompatActivity  implements View.OnClickListener {

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
        if(future !=null) {

            future.cancel(true);
            //timerCountDownRunnable.cancel();
        }

        service = Executors.newSingleThreadExecutor();
        if(v.getId() == R.id.imageViewOption1){
            timerCountDownRunnable = new TimerCountDownRunnable(this, 10, microLblTimer);
           // service.execute(timerCountDownRunnable);
            future = service.submit(timerCountDownRunnable);
            service.shutdown();
        }
        if(v.getId() == R.id.imageViewOption2){
            timerCountDownRunnable = new TimerCountDownRunnable(this, 20, microLblTimer);
            //service.execute(timerCountDownRunnable);
            future = service.submit(timerCountDownRunnable);
            service.shutdown();
        }
        if(v.getId() == R.id.imageViewOption3){
            timerCountDownRunnable = new TimerCountDownRunnable(this, 30, microLblTimer);
            service.execute(timerCountDownRunnable);
            service.shutdown();
        }

    }

}