package com.easytransac.sampleapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.easytransac.easytransac_sdk.EasyTransacSDK;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

	private final static String TAG = "EasyTransacSampleApp";
	private final static int RESULT_SDK = 200;

	private final static String API_KEY = "YOUR_API_KEY==";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void actionSingleNfc(View view) {
		if (isPackageInstalled(EasyTransacSDK.EASYTRANSAC_PACKAGE_NAME, getPackageManager())) {
			Intent intent = new Intent();
			intent.setClassName(EasyTransacSDK.EASYTRANSAC_PACKAGE_NAME, EasyTransacSDK.EASYTRANSAC_CLASS_NAME);

			intent.putExtra(EasyTransacSDK.EXTRA_API_KEY, API_KEY);
			intent.putExtra(EasyTransacSDK.EXTRA_AMOUNT, 1.00);
			intent.putExtra(EasyTransacSDK.EXTRA_DEMO, true);
			intent.putExtra(EasyTransacSDK.EXTRA_USE_3DS, false);
			intent.putExtra(EasyTransacSDK.EXTRA_DETECTION_METHOD, "nfc");

			startActivityForResult(intent, RESULT_SDK);
		} else {
			Toast.makeText(MainActivity.this, getString(R.string.app_not_installed), Toast.LENGTH_LONG).show();
		}
	}

	public void actionSingleManual(View view) {
		if (isPackageInstalled(EasyTransacSDK.EASYTRANSAC_PACKAGE_NAME, getPackageManager())) {
			Intent intent = new Intent();
			intent.setClassName(EasyTransacSDK.EASYTRANSAC_PACKAGE_NAME, EasyTransacSDK.EASYTRANSAC_CLASS_NAME);

			intent.putExtra(EasyTransacSDK.EXTRA_API_KEY, API_KEY);
			intent.putExtra(EasyTransacSDK.EXTRA_AMOUNT, 2.99);
			intent.putExtra(EasyTransacSDK.EXTRA_DEMO, true);
			intent.putExtra(EasyTransacSDK.EXTRA_USE_3DS, true);
			intent.putExtra(EasyTransacSDK.EXTRA_DETECTION_METHOD, "manual");
			intent.putExtra(EasyTransacSDK.EXTRA_ORDER_ID, "120ABC");
			intent.putExtra(EasyTransacSDK.EXTRA_ORDER_DESCRIPTION, "Custom operation actionSingleManual");
			intent.putExtra(EasyTransacSDK.EXTRA_CUSTOMER_FIRSTNAME, "John");
			intent.putExtra(EasyTransacSDK.EXTRA_CUSTOMER_LASTNAME, "Doe");

			startActivityForResult(intent, RESULT_SDK);
		} else {
			Toast.makeText(MainActivity.this, getString(R.string.app_not_installed), Toast.LENGTH_LONG).show();
		}
	}

	public void actionMultipleFlash(View view) {
		if (isPackageInstalled(EasyTransacSDK.EASYTRANSAC_PACKAGE_NAME, getPackageManager())) {
			Intent intent = new Intent();
			intent.setClassName(EasyTransacSDK.EASYTRANSAC_PACKAGE_NAME, EasyTransacSDK.EASYTRANSAC_CLASS_NAME);

			intent.putExtra(EasyTransacSDK.EXTRA_API_KEY, API_KEY);
			intent.putExtra(EasyTransacSDK.EXTRA_AMOUNT, 100.00);
			intent.putExtra(EasyTransacSDK.EXTRA_DEMO, true);
			intent.putExtra(EasyTransacSDK.EXTRA_USE_3DS, true);
			intent.putExtra(EasyTransacSDK.EXTRA_DETECTION_METHOD, "flash");
			intent.putExtra(EasyTransacSDK.EXTRA_MULTIPLE_PAYMENT, true);
			intent.putExtra(EasyTransacSDK.EXTRA_CUSTOMER_FIRSTNAME, "John");
			intent.putExtra(EasyTransacSDK.EXTRA_CUSTOMER_LASTNAME, "Doe");
			intent.putExtra(EasyTransacSDK.EXTRA_DOWN_PAYMENT, 33.34);
			intent.putExtra(EasyTransacSDK.EXTRA_MULTIPLE_PAYMENT_REPEAT, 3);

			startActivityForResult(intent, RESULT_SDK);
		} else {
			Toast.makeText(MainActivity.this, getString(R.string.app_not_installed), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Log.d(TAG, "OnActivityResult");
		Toast.makeText(this, "Result code " + resultCode, Toast.LENGTH_SHORT).show();

		// compare the resultCode with EasytransacSDK.RESULT_CODE_XXXXX
		// compare the intent data keys with EasytransacSDK.RESULT_XXXXX
		if (data != null) {
			dumpIntent(data);
		}
	}

	/**
	 * Useful to debug the SDK answers
	 * @param i intent
	 */
	private void dumpIntent(Intent i) {
		Bundle bundle = i.getExtras();
		if (bundle != null) {
			Set<String> keys = bundle.keySet();
			StringBuilder output = new StringBuilder();
			for (String key : keys) {
				output.append("[").append(key).append("=").append(bundle.get(key)).append("]\n");
			}

			Log.d(TAG, output.toString());
			Toast.makeText(this, output.toString(), Toast.LENGTH_LONG).show();
		}
	}

	// https://stackoverflow.com/questions/18752202/check-if-application-is-installed-android
	private boolean isPackageInstalled(String packageName, PackageManager packageManager) {
		boolean found = true;
		try {
			packageManager.getPackageInfo(packageName, 0);
		} catch (PackageManager.NameNotFoundException e) {
			found = false;
		}

		return found;
	}
}
