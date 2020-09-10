package com.example.filedemo_8_1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.ImageView;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_PERMISSION_READ_STORAGE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        int permissionStatus = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permissionStatus== PackageManager.PERMISSION_GRANTED) {
            LoadImg();
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION_READ_STORAGE);

        }
    }

    private void LoadImg() {
        if (isExternalStorageReadable()) {

            File file = new File (Environment
                    .getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_DOWNLOADS),
                    "1.txt");
//            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
//            ImageView iv = findViewById(R.id.imageView);
//            iv.setImageBitmap(bitmap);
//
           File filelog = new File(getExternalFilesDir(null),
                   "log.txt");

            FileWriter writer = null;
          //  FileReader reader = null;
            try {
                writer = new FileWriter(filelog,true);
                //
                writer.write("app started123");

                //reader = new FileReader(filelog);


            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    writer.close();
                    //reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        }

    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }
    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


    @Override
    public void onRequestPermissionsResult (int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_PERMISSION_READ_STORAGE:
                if (grantResults.length>0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    LoadImg();
                } else {
                   // Toast
                    finish();
                }

        }
    }
}
