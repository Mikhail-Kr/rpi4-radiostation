package com.example.rpi4;

import androidx.appcompat.app.AppCompatActivity;
import com.pi4j.io.i2c.I2CBus;
import com.pi4j.io.i2c.I2CDevice;
import com.pi4j.io.i2c.I2CFactory;
import com.pi4j.io.i2c.I2CFactory.UnsupportedBusNumberException;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    GPIO gpio;
    int[] bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gpio = new GPIO(17);
        gpio.activationPin();
    }

    public void on_led(View view) {
        gpio.setInOut("out");
        gpio.setState(1);
    }

    public void off_led(View view) {
        gpio.setState(0);
    }

    public void get_i2c(View view) {
        try {
            bus = I2CFactory.getBusIds();
        } catch (IOException e) {
            e.printStackTrace();
        }
        TextView textView = findViewById(R.id.i2cText);
        textView.setText(bus.toString());
    }
}