package net.sf.adtn.ui;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class HomeScreenActivity extends Activity implements OnClickListener {

	private Button visible = null, settings = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.HORIZONTAL);
		layout.setGravity(Gravity.CENTER);
		layout.setLayoutParams(new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.MATCH_PARENT,
				LinearLayout.LayoutParams.MATCH_PARENT));
		visible = new Button(this);
		visible.setText("Make Visible");
		visible.setOnClickListener(this);
		layout.addView(visible);
		settings = new Button(this);
		settings.setText("Settings");
		settings.setOnClickListener(this);
		layout.addView(settings);
		setContentView(layout);
	}

	@Override
	public void onActivityResult(int request, int result, Intent data) {
		if(result != RESULT_CANCELED) visible.setText(String.valueOf(result));
	}

	public void onClick(View v) {
		if(v == visible) {
			Intent i = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
			i.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 3600);
			startActivityForResult(i, 0);
		} else if(v == settings) {
			startActivity(new Intent(Settings.ACTION_BLUETOOTH_SETTINGS));
		}
	}
}
