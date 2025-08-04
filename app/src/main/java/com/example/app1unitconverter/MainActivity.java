package com.example.app1unitconverter;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    EditText etConvertedValue;

    // Conversion factors to meters
    private final HashMap<String, Double> toMeterMap = new HashMap<>();
    private final HashMap<String, Double> fromMeterMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etConvertedValue = findViewById(R.id.eValue2);

        initializeConversionMaps();
    }

    private void initializeConversionMaps() {
        // Conversion factors to meters
        toMeterMap.put("Kilometer", 1000.0);
        toMeterMap.put("Meter", 1.0);
        toMeterMap.put("Centimeter", 0.01);
        toMeterMap.put("Millimeter", 0.001);
        toMeterMap.put("Mile", 1609.344);
        toMeterMap.put("Foot", 0.3048);
        toMeterMap.put("Inch", 0.0254);
        toMeterMap.put("Yard", 0.9144);
        toMeterMap.put("Nautical Mile", 1852.0);

        // Conversion factors from meters
        for (String unit : toMeterMap.keySet()) {
            fromMeterMap.put(unit, 1.0 / toMeterMap.get(unit));
        }
    }

    public void onConvertClick(View v) {
        Spinner spFrom = findViewById(R.id.spinner);
        Spinner spTo = findViewById(R.id.spinner2);
        EditText edInput = findViewById(R.id.eValue1);

        String fromUnit = spFrom.getSelectedItem().toString();
        String toUnit = spTo.getSelectedItem().toString();
        String inputText = edInput.getText().toString().trim();

        if (inputText.isEmpty()) {
            edInput.setError("Please enter a value");
            return;
        }

        double inputValue;
        try {
            inputValue = Double.parseDouble(inputText);
        } catch (NumberFormatException e) {
            edInput.setError("Invalid number");
            return;
        }

        if (!toMeterMap.containsKey(fromUnit) || !fromMeterMap.containsKey(toUnit)) {
            Toast.makeText(this, "Unsupported unit conversion", Toast.LENGTH_SHORT).show();
            return;
        }

        double valueInMeters = inputValue * toMeterMap.get(fromUnit);
        double convertedValue = valueInMeters * fromMeterMap.get(toUnit);

        DecimalFormat df = new DecimalFormat("#,##0.####"); // auto remove trailing 0s
        etConvertedValue.setText(df.format(convertedValue));
    }
}
