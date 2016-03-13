package procalc.procalc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by section11 on 12/03/16.
 */
public class ControlActivity extends Activity {

    private Button newButton, retrieveButton;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.control);

        newButton = (Button) findViewById(R.id.newcontinueButton);
        retrieveButton = (Button) findViewById(R.id.retrieveButton);
        context = this;
        newButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, NewActivity.class);
                startActivity(intent);
            }
        });

        retrieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
