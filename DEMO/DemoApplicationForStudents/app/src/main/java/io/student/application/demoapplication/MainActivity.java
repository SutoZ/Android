package io.student.application.demoapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import io.student.application.demoapplication.util.CurrencyActions;
import io.student.application.demoapplication.util.CurrencyList;


/**
 * Created by Gergely_Agnecz on 12/22/2016.
 */

public class MainActivity extends Activity implements View.OnClickListener {
	CurrencyActions actions = new CurrencyActions();

	int changeRate;
	int result;

	EditText editText;
	SeekBar seekBar;
	ImageView imageView;
	Spinner spinner;
	Button button;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editText = (EditText) findViewById(R.id.editTextValue);
		seekBar = (SeekBar) findViewById(R.id.seekBarAmount);
		imageView = (ImageView) findViewById(R.id.imageViewMoney);
		spinner = (Spinner) findViewById(R.id.currencySpinner);
		button= (Button)findViewById(R.id.calculateButton);

		button.setOnClickListener(MainActivity.this);

		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
											   int progress = 1;

											   @Override
											   public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
												   progress = progressValue;
											   }

											   @Override
											   public void onStartTrackingTouch(SeekBar seekBar) {
											   }

											   @Override
											   public void onStopTrackingTouch(SeekBar seekBar) {
												   try {
													   editText.setText(Integer.toString(progress));
												   } catch (Exception ex) {
												   }
											   }
										   }
		);

		editText.addTextChangedListener(new TextWatcher() {
											@Override
											public void beforeTextChanged(CharSequence s, int start, int count, int after) {
											}

											@Override
											public void onTextChanged(CharSequence s, int start, int before, int count) {
											}

											@Override
											public void afterTextChanged(Editable s) {
												try {
													seekBar.setProgress(Integer.parseInt(s.toString()));
												} catch (Exception ex) {
												}
											}
										}

		);
	}

	private void updateResultView(int result) {
		TextView resultView = (TextView) findViewById(R.id.textViewResult);
		resultView.setText(Integer.toString(result) + " HUF");
	}

	protected String getValueOfTheSeekbar(View view) {
		int seekValue = seekBar.getProgress();
		String value = Integer.toString(seekValue);
		return value;
	}

	@Override
	public void onClick(View v) {
		int value = Integer.parseInt(getValueOfTheSeekbar(v));
		String spinnerCurrency = spinner.getSelectedItem().toString();
		if ("USD".equals(spinnerCurrency)) {
			changeRate = CurrencyList.USD.getChangeRate();
			imageView.setImageResource(R.drawable.usd);
		}
		if ("YEN".equals(spinnerCurrency)) {
			changeRate = CurrencyList.YEN.getChangeRate();
			imageView.setImageResource(R.drawable.yen);
		}
		if ("EURO".equals(spinnerCurrency)) {
			changeRate = CurrencyList.EURO.getChangeRate();
			imageView.setImageResource(R.drawable.euro);
		}
		result = actions.calculateChange(value, changeRate);
		updateResultView(result);
	}

	public void openRatingView(View v){
		Intent intent = new Intent(this, RatingActivity.class);
		startActivity(intent);
	}
}
