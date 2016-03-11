package com.example.vocabfun;



import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}

	//launches account activity
	public void clickAccount(View v) {
		Intent intent = new Intent(this, AccountActivity.class);
		startActivity(intent);
	}
	//launches game activity
	public void clickGame(View v) {
		Intent intent = new Intent(this, GameActivity.class);
		//intent.putExtra("packNum", wordPack);				
		startActivity(intent);
	}
	//launches history activity
	public void clickHistory(View v) {
		Intent intent = new Intent(this, HistoryActivity.class);			
		startActivity(intent);
	}
	//launches options activity
	public void clickOptions(View v) {
		Intent intent = new Intent(this, OptionsActivity.class);			
		startActivity(intent);
	}
	
	
	
	
	


}
