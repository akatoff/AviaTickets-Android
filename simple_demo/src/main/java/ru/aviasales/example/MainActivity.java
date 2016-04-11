package ru.aviasales.example;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import ru.aviasales.core.AviasalesSDK;
import ru.aviasales.core.identification.IdentificationData;
import ru.aviasales.template.ads.AppodealManager;
import ru.aviasales.template.ui.fragment.AviasalesFragment;

public class MainActivity extends AppCompatActivity {

	private AviasalesFragment aviasalesFragment;

	// replace to your travel payout credentials
	private final static String TRAVEL_PAYOUTS_MARKER = "your_travel_payouts_marker";
	private final static String TRAVEL_PAYOUTS_TOKEN = "your_travel_payouts_token";
	private final static String APPODEAL_APP_KEY = "your_appodeal_app_key";

	private final static boolean SHOW_ADS_ON_START = true;
	private final static boolean SHOW_ADS_ON_WAITING_SCREEN = true;
	private final static boolean SHOW_ADS_ON_SEARCH_RESULTS = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		AviasalesSDK.getInstance().init(this, new IdentificationData(TRAVEL_PAYOUTS_MARKER, TRAVEL_PAYOUTS_TOKEN));
		setContentView(R.layout.activity_main);

		init();
	}

	private void init() {
		AppodealManager.getInstance().init(this, APPODEAL_APP_KEY, SHOW_ADS_ON_START, SHOW_ADS_ON_WAITING_SCREEN, SHOW_ADS_ON_SEARCH_RESULTS);
		initFragment();
	}

	private void initFragment() {
		FragmentManager fm = getSupportFragmentManager();

		aviasalesFragment = (AviasalesFragment) fm.findFragmentByTag(AviasalesFragment.TAG);

		if (aviasalesFragment == null) {
			aviasalesFragment = (AviasalesFragment) AviasalesFragment.newInstance();
		}

		FragmentTransaction fragmentTransaction = fm.beginTransaction();
		fragmentTransaction.replace(R.id.fragment_place, aviasalesFragment, AviasalesFragment.TAG);
		fragmentTransaction.commit();
	}

	@Override
	public void onBackPressed() {
		if (!aviasalesFragment.onBackPressed()) {
			super.onBackPressed();
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}
}
