package com.nicknackhacks.dailyburn.activity;

import java.util.List;

import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.signature.SignatureMethod;

import org.apache.http.impl.client.DefaultHttpClient;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.nicknackhacks.dailyburn.R;
import com.nicknackhacks.dailyburn.api.DietDao;
import com.nicknackhacks.dailyburn.model.DietGoal;

public class DietGoalsActivity extends Activity {

	private SharedPreferences pref;
	private DietDao dietDao;
	private List<DietGoal> goals;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.dietgoals);
		pref = this.getSharedPreferences("dbdroid", 0);
		String token = pref.getString("token", null);
		String secret = pref.getString("secret", null);
		CommonsHttpOAuthConsumer consumer = new CommonsHttpOAuthConsumer(
				getString(R.string.consumer_key),
				getString(R.string.consumer_secret), SignatureMethod.HMAC_SHA1);
		consumer.setTokenWithSecret(token, secret);
		dietDao = new DietDao(new DefaultHttpClient(), consumer);
		
		goals = dietDao.getDietGoals();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		
	}
}
