package procalc.procalc;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by section11 on 12/03/16.
 */
public class MainActivity extends Activity {

    public static final String MyPREFERENCES = "MyPrefs" ;
    private SharedPreferences sharedpreferences = null;
    private Button submitButton;
    private EditText passCodeEditText;
    private Context context;
    private WebView webView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        String code = sharedpreferences.getString("secretCode", "");
        if(code.equals("")){
            setContentView(R.layout.activity_main);
            submitButton = (Button) findViewById(R.id.submitFirstTime);
            passCodeEditText = (EditText) findViewById(R.id.firstTimePassCodeEditText);
            webView = (WebView) findViewById(R.id.webViewDetails);
            String html = "<p><b>ProCalc (Protective Calculations)</b> is a tool survivors can use to gather the evidence necessary to build an order of protection case. The tool is disguised as a calculator for your safety.<p>" +
                    "<br>" +
                    "<p><b>How to get started:</b><br>" +
                    "Choose a number that will be easy for you to remember but difficult for others to guess. This number will be your pin. In the future you will need to enter this pin AND the equal sign to access the app.<br>" +
                    "{if you forget your pin you will not be able to access the information you have already entered into the app.}\n" +
                    "</p><br>" +
                    "<p><b>How does it work?</b><br>" +
                    "ProCalc provides you the opportunity to document instances of abuse and harassment that may be helpful in your domestic violence case. This app will give you the space to record information about when, where and what happened as well as attach any pictures, videos, or audio you have to your support your case." +
                    "</p><br>" +
                    "<p>" +
                    "<b>FAQs</b><br>" +
                    "Where is my information stored?" +
                    "The information is stored on your phone." +
                    "</p><br>" +
                    "<p>" +
                    "<b>How can I keep my information private?</b><bR>" +
                    "ProCalc will appear on your phone as an ordinary, useable calculator.ProCalc will not share or review any of the information you enter in the app. CAN YOU DELETE WHAT YOU'VE ENTERED? WHAT IS OUR PRIVACY POLICY?\n" +
                    "</p>" +
                    "<br>" +
                    "<p>" +
                    "<b>What if my phone is destroyed?</b><br>\n" +
                    "Your information will be lost unless you export the information off of your phone." +
                    "</p><br>" +
                    "<p><b>How can I export the information off of my phone (i.e. print it or send it to a third party)?</b><br>\n" +
                    "You will have the option to email the information collected by ProCalc to yourself, or to another individual you trust.Once it is sent to an email address, the information can be printed.\n" +
                    "</p>" +
                    "<p>" +
                    "<b>How do I add photos, audio recordings, and other files to ProCalc?</b><br>\n" +
                    "The app gives you space to write about an incident of domestic violence and then attach supporting evidence.You can upload photos and recordings from your phone, which you can then delete from your phone.The app will also allow you to take pictures straight from the app itself. " +
                    "</p>" +
                    "<br>" +
                    "<p>" +
                    "<b>What can I do with the evidence I collect?</b><br>" +
                    "You can show what you have documented to a domestic violence advocate, law enforcement or attorney.You can send the information you collected straight to an individual you trust.You can also use the evidence you put on ProCalc to assist you in your case if you go to court pro se (without an attorney)." +
                    "</p>";
            context = this;
            webView.loadData(html,"text/html", "utf-8");
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String newCode = passCodeEditText.getText().toString();
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString("secretCode", Integer.valueOf(newCode).toString());
                    editor.apply();
                    Intent intent = new Intent(context, CalculatorActivity.class);
                    startActivity(intent);
                }
            });
        }else{
            Intent intent = new Intent(this, CalculatorActivity.class);
            startActivity(intent);
        }

    }
}
