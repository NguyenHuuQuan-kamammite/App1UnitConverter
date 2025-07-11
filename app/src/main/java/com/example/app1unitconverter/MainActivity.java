package com.example.app1unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText etConvertedValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etConvertedValue = findViewById(R.id.eValue2); // output field
    }

    public void onConvertClick(View v) {
        Spinner sp1 = findViewById(R.id.spinner);
        Spinner sp2 = findViewById(R.id.spinner2);
        EditText ed1 = findViewById(R.id.eValue1);

        String fromUnit = sp1.getSelectedItem().toString();
        String toUnit = sp2.getSelectedItem().toString();
        String inputText = ed1.getText().toString();

        if (inputText.isEmpty()) {
            ed1.setError("Please enter a value");
            return;
        }

        double value1;
        try {
            value1 = Double.parseDouble(inputText);
        } catch (NumberFormatException e) {
            ed1.setError("Invalid number");
            return;
        }

        double valueInMeters = toMeters(value1, fromUnit);
        double result = fromMeters(valueInMeters, toUnit);

        etConvertedValue.setText(String.format("%.4f", result));
    }

    private double toMeters(double value, String unit) {
        switch (unit) {
            case "Meter": return value;
            case "Millimeter": return value / 1000;
            case "Mile": return value / 0.000621371192;
            case "Foot": return value / 3.2808399;
            default: return 0;
        }
    }

    private double fromMeters(double value, String unit) {
        switch (unit) {
            case "Meter": return value;
            case "Millimeter": return value * 1000;
            case "Mile": return value * 0.000621371192;
            case "Foot": return value * 3.2808399;
            default: return 0;
        }
    }
}
