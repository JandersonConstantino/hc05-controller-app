package com.jandersonconstantino.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;

import java.util.Set;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;

import com.jandersonconstantino.myapplication.Services.BluetoothService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Set;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    BluetoothService bluetoothService;
    final String BTN_UP_SERIAL = "1";
    final String BTN_DOWN_SERIAL = "2";
    final String BTN_LEFT_SERIAL = "3";
    final String BTN_RIGHT_SERIAL = "4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnUp = (Button) findViewById(R.id.buttonUp);
        Button btnDown = (Button) findViewById(R.id.buttonDown);
        Button btnLeft = (Button) findViewById(R.id.buttonLeft);
        Button btnRight = (Button) findViewById(R.id.buttonRight);

        try {
            initBluetoothService();
            setupButton(btnUp, BTN_UP_SERIAL);
            setupButton(btnDown, BTN_DOWN_SERIAL);
            setupButton(btnLeft, BTN_LEFT_SERIAL);
            setupButton(btnRight, BTN_RIGHT_SERIAL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void initBluetoothService() throws IOException {
        bluetoothService = new BluetoothService();
        bluetoothService.open();
    }

    void setupButton(Button btn, String message) {
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    try {
                        Log.d("Pressed", message);
                        bluetoothService.write(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return false;
            }
        });
    }
}