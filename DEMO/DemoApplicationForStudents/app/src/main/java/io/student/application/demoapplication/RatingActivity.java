package io.student.application.demoapplication;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import io.student.application.demoapplication.util.CurrencyActions;

public class RatingActivity extends AppCompatActivity {
	CurrencyActions actions = new CurrencyActions();

	RatingBar ratingBar;
	Button submitRating;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rating);
		Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		addListeners();

		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "If you have any detailed feedback, send it to corp@corp.com.", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});
	}

	private void addListeners() {
		ratingBar = (RatingBar) findViewById(R.id.ratingBarStars);
		submitRating = (Button) findViewById(R.id.buttonRate);

		ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
			@Override
			public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

				Toast.makeText(RatingActivity.this,
						String.valueOf(ratingBar.getRating()),
						Toast.LENGTH_SHORT).show();
			}
		});

		submitRating.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TextView ratingTextView = (TextView) findViewById(R.id.textViewActRating);
				ratingTextView.setText(String.valueOf(ratingBar.getRating()));
			}
		});
	}

}
