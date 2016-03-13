package procalc.procalc;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by section11 on 12/03/16.
 */
public class RetrieveActivity extends Activity {
    private Allegation allegation;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences preferences = getSharedPreferences("allegation", Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = preferences.getString("Allegation", "");
        if(json.equals("")){
            allegation = null;
        }else {
            //Get the Recipes from Preferences
            allegation = gson.fromJson(json, new TypeToken<Allegation>() {
            }.getType());
        }
        if(allegation == null){

        }else {
            setContentView(R.layout.retrieveallegation);
            TextView whoTextView = (TextView) findViewById(R.id.whoTextView);
            whoTextView.setText(allegation.getWho());
        }
    }
}