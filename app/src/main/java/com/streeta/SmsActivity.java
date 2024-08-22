package com.streeta;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import androidx.annotation.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import android.Manifest;
import android.telephony.SmsManager;
import androidx.core.app.ActivityCompat;
import android.telephony.SubscriptionInfo;
import android.content.pm.PackageManager;
import androidx.core.content.ContextCompat;
import android.telephony.SubscriptionManager;

public class SmsActivity extends AppCompatActivity {
	
	private String phone = "";
	private SmsManager smsManager;
	private static final int SMS_PERMISSION_CODE = 1;
	private SubscriptionManager subscriptionManager;
	
	private SharedPreferences link;
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.sms);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		link = getSharedPreferences("link", Activity.MODE_PRIVATE);
	}
	
	private void initializeLogic() {
		_checkReadPhoneStatePermission();
		phone = link.getString("number", "");
		if (_checkSmsPermission()) {
			_sendSMS(phone, link.getString("link", ""));
		}
	}
	
	public void _sendSMS(final String _phone, final String _message) {
		String phoneNumber = _phone;
		String message = _message;
		smsManager = SmsManager.getDefault();
		smsManager.sendTextMessage(phoneNumber, null, message, null, null);
		SketchwareUtil.showMessage(getApplicationContext(), "SMS sent!");
	}
	
	
	public boolean _checkSmsPermission() {
		    if (ContextCompat.checkSelfPermission(SmsActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
			        ActivityCompat.requestPermissions(SmsActivity.this, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
			        return false; // Permission denied
			    } else {
			        return true; // Permission granted
			    }
		    
		    }
	
	    @Override
	    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		        if (requestCode == SMS_PERMISSION_CODE) {
			            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				            } else {
				                showMessage("SMS Permission denied!");
				                finish();
				            }
			        }
		        
		                    if (requestCode == 1) {
			                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				                    // Permission granted
				                } else {
				                    // Permission denied
				                    showMessage("Read Phone State Permission denied!");
				                    finish();
				                }
			                }
	}
	
	
	public void _checkReadPhoneStatePermission() {
		   // Check if the user has granted the permission
		    int permissionStatus = ContextCompat.checkSelfPermission(SmsActivity.this, Manifest.permission.READ_PHONE_STATE);
		
		    // If the permission has not been granted, request it
		    if (permissionStatus != PackageManager.PERMISSION_GRANTED) {
			        ActivityCompat.requestPermissions(SmsActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
			    }
	}
	
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input) {
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels() {
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels() {
		return getResources().getDisplayMetrics().heightPixels;
	}
}