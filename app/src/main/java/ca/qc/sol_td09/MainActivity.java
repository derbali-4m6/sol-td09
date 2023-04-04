package ca.qc.sol_td09;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainActivity extends AppCompatActivity {

    List<String> urls;
    ImageView imgPhoto;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgPhoto = findViewById(R.id.main_img_photo);
        position = 0;
        urls = new ArrayList<>();
        urls.add("http://www.sportbienetre.ca/images/accueil/Administrateur.jpg");
        urls.add("http://www.sportbienetre.ca/images/accueil/Parent.jpg");
        urls.add("http://www.sportbienetre.ca/images/accueil/Athletecentre.jpg");
        urls.add("http://www.sportbienetre.ca/images/accueil/Entraineur.jpg");
        urls.add("http://www.sportbienetre.ca/images/accueil/Officiel.jpg");

    }

    public void chargerImage(View view) {
        /*ExecutorService executorService = Executors.newSingleThreadExecutor();
        LoadImageRunnable thread = new LoadImageRunnable(urls.get(position), imgPhoto, this);
        executorService.execute(thread);*/
        Picasso.get().load(urls.get(position)).into(imgPhoto);
        position++;
        if(position == urls.size())
            position = 0;
        //executorService.shutdown();
    }

    public void allerAuMicroWave(View view) {
        startActivity(new Intent(this, MicroWaveActivity.class));
    }
}