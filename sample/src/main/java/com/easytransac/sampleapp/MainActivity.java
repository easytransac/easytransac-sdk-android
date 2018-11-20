package com.easytransac.sampleapp;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.easytransac.easytransac_sdk.EasyTransacSDK;

import java.util.Set;

public class MainActivity extends AppCompatActivity
{
	private final static String TAG = "EasyTransacSampleApp";
	private final static int RESULT_SDK = 0x200;

	private final static String API_KEY = "REPLACE_BY_YOUR_API_KEY";

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void actionDirectPayment(View view)
	{
		if (isPackageInstalled(EasyTransacSDK.EASYTRANSAC_PACKAGE_NAME, getPackageManager()))
		{
			Intent intent = new Intent();
			intent.setClassName(EasyTransacSDK.EASYTRANSAC_PACKAGE_NAME, EasyTransacSDK.EASYTRANSAC_CLASS_NAME);

			intent.putExtra(EasyTransacSDK.EXTRA_API_KEY, API_KEY);
			intent.putExtra(EasyTransacSDK.EXTRA_AMOUNT, 1.00);
			intent.putExtra(EasyTransacSDK.EXTRA_USE_3DS, false);
			intent.putExtra(EasyTransacSDK.EXTRA_DETECTION_METHOD, "nfc");
			intent.putExtra(EasyTransacSDK.EXTRA_CUSTOMER_EMAIL, "REPLACE_WITH_YOUR_CUSTOMER_EMAIL");

			startActivityForResult(intent, RESULT_SDK);
		}
		else
		{
			Toast.makeText(MainActivity.this, getString(R.string.app_not_installed), Toast.LENGTH_LONG).show();
		}
	}

	public void actionMultiplePayment(View view)
	{
		if (isPackageInstalled(EasyTransacSDK.EASYTRANSAC_PACKAGE_NAME, getPackageManager()))
		{
			Intent intent = new Intent();
			intent.setClassName(EasyTransacSDK.EASYTRANSAC_PACKAGE_NAME, EasyTransacSDK.EASYTRANSAC_CLASS_NAME);

			intent.putExtra(EasyTransacSDK.EXTRA_API_KEY, API_KEY);
			intent.putExtra(EasyTransacSDK.EXTRA_AMOUNT, 100.00);
			intent.putExtra(EasyTransacSDK.EXTRA_USE_3DS, false);
			intent.putExtra(EasyTransacSDK.EXTRA_DETECTION_METHOD, "nfc");
			intent.putExtra(EasyTransacSDK.EXTRA_CUSTOMER_EMAIL, "REPLACE_WITH_YOUR_CUSTOMER_EMAIL");
			intent.putExtra(EasyTransacSDK.EXTRA_MULTIPLE_PAYMENT, true);
			intent.putExtra(EasyTransacSDK.EXTRA_DOWN_PAYMENT, 40.00);
			intent.putExtra(EasyTransacSDK.EXTRA_MULTIPLE_PAYMENT_REPEAT, 3);

			startActivityForResult(intent, RESULT_SDK);
		}
		else
		{
			Toast.makeText(MainActivity.this, getString(R.string.app_not_installed), Toast.LENGTH_LONG).show();
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);

		if (data != null) dumpIntent(data);
	}

	/**
	 * Useful to debug the SDK answers
	 * @param i intent
	 */
	private void dumpIntent(Intent i)
	{
		Bundle bundle = i.getExtras();
		if (bundle != null)
		{
			Set<String> keys = bundle.keySet();
			for (String key : keys)
			{
				Log.e(TAG, "[" + key + "=" + bundle.get(key) + "]");
			}
		}
	}

	// https://stackoverflow.com/questions/18752202/check-if-application-is-installed-android
	private boolean isPackageInstalled(String packageName, PackageManager packageManager)
	{
		boolean found = true;
		try
		{
			packageManager.getPackageInfo(packageName, 0);
		}
		catch (PackageManager.NameNotFoundException e)
		{
			found = false;
		}

		return found;
	}
}
