package com.myapplicationdev.android.demofilereadwriting;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

public class MainActivity extends AppCompatActivity {

    String folderLocation;
    Button btnWrite, btnRead;
    TextView tv;

    //UI handlers to be defined
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnWrite = findViewById(R.id.btnWrite);
        btnRead = findViewById(R.id.btnRead);
        tv = findViewById(R.id.tv);

        //Folder creation
        folderLocation = getFilesDir().getAbsolutePath() + "/MyFolder";
        File folder = new File(folderLocation);

        if (folder.exists() == false){
            boolean result = folder.mkdir();
            if (result = true){
                Log.d("File Read/Write", "Folder created");
            }
        }
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (folder.exists() == true){
                    try {
                        folderLocation = getFilesDir().getAbsolutePath() + "/MyFolder";
                        File targetFile_I = new File(folderLocation, "data.txt");
                        FileWriter writer_I = new FileWriter(targetFile_I, true);
                        writer_I.write("test data" + "\n");
                                writer_I.flush();
                        writer_I.close();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Failed to write!", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
            }
        });

        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                folderLocation = getFilesDir().getAbsolutePath() + "/MyFolder";
                File targetFile = new File(folderLocation, "data.txt");
                if (targetFile.exists() == true){
                    String data ="";
                    try {
                        FileReader reader = new FileReader(targetFile);
                        BufferedReader br = new BufferedReader(reader);
                        String line = br.readLine();
                        while (line != null){
                            data += line + "\n";
                            line = br.readLine();
                            tv.setText(data);
                        }
                        br.close();
                        reader.close();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, "Failed to read!", Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    Log.d("Content", data);
                }
            }
        });
    }
}









