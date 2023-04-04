package ca.qc.sol_td09;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoadImageRunnable implements  Runnable{
    String path;
    ImageView imageResource;
    Activity activity;

    public LoadImageRunnable(String path, ImageView imageResource, Activity activity) {
        this.path = path;
        this.imageResource = imageResource;
        this.activity = activity;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream stream = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(stream);
            //afficher le bitmap dans l'ImageView
            activity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    imageResource.setImageBitmap(bitmap);
                }
            });
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (IOException e){
            throw new RuntimeException(e);
            //Toast.makeText(activity.getApplicationContext(), "Attention Image non trouvée", Toast.LENGTH_LONG).show();
        }
    }
}
