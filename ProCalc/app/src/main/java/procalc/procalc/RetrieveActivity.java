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
    private TextView whoTextView, whenTextView, whereTextView, whatTextview;

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
            whoTextView = (TextView) findViewById(R.id.whoTextView);
            whereTextView = (TextView) findViewById(R.id.whereTextview);
            whenTextView = (TextView) findViewById(R.id.whenTextview);
            whatTextview = (TextView) findViewById(R.id.whatTextview);

            whoTextView.setText("Who: " + allegation.getWho());
            whereTextView.setText("Where " + allegation.getWhere());
            whenTextView.setText("When: " + allegation.getWhen());
            whatTextview.setText("What: " + allegation.getWhat());
        }
    }
}