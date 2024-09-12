package com.streeta;

import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.net.Uri;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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

public class NumberActivity extends AppCompatActivity {
	
	private static final int SMS_PERMISSION_CODE = 1;
	private SmsManager smsManager;
	private SubscriptionManager subscriptionManager;
	
	private LinearLayout linear1;
	private LinearLayout linear6;
	private EditText edittext1;
	private LinearLayout linear7;
	private Button button1;
	
	private SharedPreferences link;
	private Intent main = new Intent();
	
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.number);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		linear1 = findViewById(R.id.linear1);
		linear6 = findViewById(R.id.linear6);
		edittext1 = findViewById(R.id.edittext1);
		linear7 = findViewById(R.id.linear7);
		button1 = findViewById(R.id.button1);
		link = getSharedPreferences("link", Activity.MODE_PRIVATE);
		
		button1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				if (edittext1.getText().toString().equals("") || (Double.parseDouble(edittext1.getText().toString()) < 1000000000)) {
					SketchwareUtil.showMessage(getApplicationContext(), "Number cannot be empty or less than 10 digits!");
				}
				else {
					link.edit().putString("number", edittext1.getText().toString()).commit();
					main.setClass(getApplicationContext(), MainActivity.class);
					startActivity(main);
					finish();
				}
			}
		});
	}
	
	private void initializeLogic() {
		if (_checkSmsPermission()) {
			button1.setBackground(new GradientDrawable() { public GradientDrawable getIns(int a, int b) { this.setCornerRadius(a); this.setColor(b); return this; } }.getIns((int)50, 0xFFAA00FF));
			if (!link.getString("number", "").equals("")) {
				main.setClass(getApplicationContext(), MainActivity.class);
				startActivity(main);
				finish();
			}
		}
	}
	
	public boolean _checkSmsPermission() {
		    if (ContextCompat.checkSelfPermission(NumberActivity.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
			        ActivityCompat.requestPermissions(NumberActivity.this, new String[]{Manifest.permission.SEND_SMS}, SMS_PERMISSION_CODE);
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