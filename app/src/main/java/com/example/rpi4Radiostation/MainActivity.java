package com.example.rpi4Radiostation;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.rpi4Radiostation.databinding.ActivityMainBinding;
import com.example.rpi4Radiostation.models.GPIO;
import com.example.rpi4Radiostation.models.serialPort.SerialPortFinder;

public class MainActivity extends AppCompatActivity {

    GPIO gpio;
    private SerialPortFinder mSerialPortFinder;

    // Used to load the 'SerialPort' library on application startup.
    static {
        System.loadLibrary("SerialPort");
    }

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        gpio = new GPIO(17);
        gpio.activationPin();
        mSerialPortFinder = new SerialPortFinder();
    }

    public void on_led(View view) {
        gpio.setInOut("out");
        gpio.setState(1);
    }

    public void off_led(View view) {
        gpio.setState(0);
    }

    public void getSerialPorts(View view) {
        TextView devices = findViewById(R.id.devices);
        TextView allDevices = findViewById(R.id.allDevices);

        String[] entries = mSerialPortFinder.getAllDevices();
        String[] entryValues = mSerialPortFinder.getAllDevicesPath();

        devices.setText(entries[1]);
        allDevices.setText(entryValues[1]);
    }
}