package com.lacklab.gmsbarcode;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

public class MainActivity extends AppCompatActivity {

    private final int PICK_BARCODE_CONTENT = 0;
    private TextView barcodeResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        barcodeResult = (TextView) findViewById(R.id.txt_barcode_result);
        if (ActivityCompat.checkSelfPermission(MainActivity.this,
                android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {android.Manifest.permission.CAMERA}, 1);
            return;
        }
    }

    public void scanBarcode(View view) {
        Intent intent = new Intent(this, ScanBarcodeActivity.class);
        startActivityForResult(intent, PICK_BARCODE_CONTENT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case PICK_BARCODE_CONTENT:
                if(requestCode== CommonStatusCodes.SUCCESS){
                    if(data!=null){
                        Barcode barcode = data.getParcelableExtra("barcode");
                        barcodeResult.setText("Barcode 結果 : " + barcode.displayValue);
                    } else {
                        barcodeResult.setText("掃不到BarCode");
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void getBarcodeContent(int resultCode, Intent data) {

    }
}
