package com.example.permisospractica;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Switch camarasw, audiosw, ubicacionsw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        camarasw = findViewById(R.id.camaraSwitch);
        audiosw = findViewById(R.id.audioSwitch);
        ubicacionsw = findViewById(R.id.ubicacionSwitch);

        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId())
                {
                    case R.id.camaraSwitch:
                        permisos(Manifest.permission.CAMERA,1,camarasw);
                        break;
                    case R.id.audioSwitch:
                        permisos(Manifest.permission.RECORD_AUDIO,2,audiosw);
                        break;
                    case R.id.ubicacionSwitch:
                        permisos(Manifest.permission.ACCESS_FINE_LOCATION,3,ubicacionsw);
                        break;
                }
            }
        };

        camarasw.setOnClickListener(click);
        audiosw.setOnClickListener(click);
        ubicacionsw.setOnClickListener(click);

        statuspermiso(Manifest.permission.CAMERA,camarasw);
        statuspermiso(Manifest.permission.RECORD_AUDIO,audiosw);
        statuspermiso(Manifest.permission.ACCESS_FINE_LOCATION,ubicacionsw);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 1:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Permiso cámara aceptado", Toast.LENGTH_SHORT).show();
                    camarasw.setChecked(true);
                }
                else
                {
                    Toast.makeText(this, "Permiso cámara denegado", Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Permiso audio aceptado", Toast.LENGTH_SHORT).show();
                    audiosw.setChecked(true);
                }
                else
                {
                    Toast.makeText(this, "Permiso audio denegado", Toast.LENGTH_SHORT).show();

                }
                break;
            case 3:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this, "Permiso ubicacion aceptado", Toast.LENGTH_SHORT).show();
                    ubicacionsw.setChecked(true);
                }
                else
                {
                    Toast.makeText(this, "Permiso ubicacion denegado", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void permisos(String permiso, int codigo, Switch sw)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(ActivityCompat.checkSelfPermission(MainActivity.this, permiso) != PackageManager.PERMISSION_GRANTED)
            {
                if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permiso))
                {
                    String[] lp = new String[] {permiso};
                    ActivityCompat.requestPermissions(MainActivity.this,lp,codigo);
                    sw.setChecked(false);
                }
                else
                {
                    String[] lp = new String[] {permiso};
                    ActivityCompat.requestPermissions(MainActivity.this,lp,codigo);
                    sw.setChecked(false);
                }
            }
        }
    }
    public void statuspermiso(String permiso, Switch sw)
    {
       if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
       {
           if(ActivityCompat.checkSelfPermission(MainActivity.this, permiso) != PackageManager.PERMISSION_GRANTED)
           {
                sw.setChecked(false);
           }
           else
           {
               sw.setChecked(true);
           }
       }

    }


}
