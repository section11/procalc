package procalc.procalc;

import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by section11 on 12/03/16.
 */
public class NewActivity extends Activity {

    private RelativeLayout selectDefinitions, newAllegation;
    private Spinner statesSpinner;
    private TextView definitions;
    private Button nextButton, saveButton, addPhoto;
    private GridView gridView;
    private EditText whoEditText, whenEditText, whereEditText, whatEditText;
    private static int RESULT_LOAD_IMAGE = 1;

    private String imageEncoded;
    private List<String> imagesEncodedList;
    private ArrayList<Uri> mArrayUri;
    private Gson gson;
    private Context context;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newcase);
        final Resources res = getResources();
        final String[] states = res.getStringArray(R.array.states_array);

        gson = new Gson();
        context = this;

        selectDefinitions = (RelativeLayout) findViewById(R.id.selectDefinitionsLayout);
        newAllegation = (RelativeLayout) findViewById(R.id.newAllegations);
        definitions = (TextView) findViewById(R.id.definitionsTextView);
        statesSpinner = (Spinner) findViewById(R.id.states_spinner);
        nextButton = (Button) findViewById(R.id.nextButton);
        addPhoto = (Button) findViewById(R.id.addPhoto);
        saveButton = (Button) findViewById(R.id.addAllegation);
        whoEditText = (EditText) findViewById(R.id.whoEditText);
        whenEditText = (EditText) findViewById(R.id.whenEditText);
        whereEditText = (EditText) findViewById(R.id.whereEditText);
        whatEditText = (EditText) findViewById(R.id.whatEditText);

        gridView = (GridView) findViewById(R.id.gridView);


        ArrayAdapter<CharSequence> statesAdapter = ArrayAdapter.createFromResource(this, R.array.states_array, android.R.layout.simple_spinner_item);
        statesSpinner.setAdapter(statesAdapter);
        statesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (states[position].equals("IL")) {
                    definitions.setText(Html.fromHtml(res.getString(R.string.ILDef)));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectDefinitions.setVisibility(View.GONE);
                newAllegation.setVisibility(View.VISIBLE);
            }
        });
        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), RESULT_LOAD_IMAGE);
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String who, when, where, what;
                who = whoEditText.getText().toString();
                when = whenEditText.getText().toString();
                where = whereEditText.getText().toString();
                what = whatEditText.getText().toString();
                ArrayList<Bitmap> bitmapPhotos = new ArrayList<Bitmap>();
                ArrayList<List<Byte>> bitmapBytes = new ArrayList<List<Byte>>();
                ArrayList<String> base64s = new ArrayList<String>();
                String b64;
                /*for (int i = 0; i < mArrayUri.size();i++){

                    Bitmap bm = null;
                    try {
                        bm = MediaStore.Images.Media.getBitmap(getContentResolver(), mArrayUri.get(i));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);

                    //added lines
                    bm.recycle();
                    bm = null;

                    //added lines
                    byte[] b = baos.toByteArray();
                    b64=Base64.encodeToString(b, Base64.DEFAULT);
                    base64s.add(b64);
                    /*
                    try {
                        InputStream image_stream = getContentResolver().openInputStream(mArrayUri.get(i));
                        //bitmapPhotos.add(BitmapFactory.decodeStream(image_stream));
                        Bitmap bm = BitmapFactory.decodeStream(image_stream);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object
                        byte[] b = baos.toByteArray();
                        List<Byte> bytesList = new ArrayList<Byte>();
                        for(byte by : b) {
                            bytesList.add(new Byte(by));
                        }
                        bitmapBytes.add(bytesList);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
                */
                Allegation allegation = new Allegation(who, when, where, what, base64s);
                SharedPreferences preferences = getSharedPreferences("allegation", Context.MODE_PRIVATE);
                Gson gson = new Gson();

                SharedPreferences.Editor prefsEditor = preferences.edit();

                //Parse the list to Json and save it
                String allegationToJson= gson.toJson(allegation, new TypeToken<Allegation>() {
                }.getType());
                prefsEditor.putString("Allegation", allegationToJson);
                prefsEditor.commit();
                Toast.makeText(context, "You saved with success", Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                final String[] filePathColumn = {MediaStore.Images.Media.DATA, MediaStore.Images.Media._ID};
                imagesEncodedList = new ArrayList<String>();
                if (data.getData() != null) {

                    Uri mImageUri = data.getData();

                    // Get the cursor
                    Cursor cursor = getContentResolver().query(mImageUri,
                            filePathColumn, null, null, null);
                    // Move to first row
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    imageEncoded = cursor.getString(columnIndex);
                    cursor.close();

                } else {
                    if (data.getClipData() != null) {
                        ClipData mClipData = data.getClipData();
                        mArrayUri = new ArrayList<Uri>();
                        for (int i = 0; i < mClipData.getItemCount(); i++) {
                            ClipData.Item item = mClipData.getItemAt(i);
                            Uri uri = item.getUri();
                            mArrayUri.add(uri);
                        }
                        Log.v("LOG_TAG", "Selected Images" + mArrayUri.size());
                        GridAdapter gridAdapter = new GridAdapter(this, R.layout.grid_item_layout, mArrayUri);
                        gridView.setAdapter(gridAdapter);
                    }
                }
            } else {
                Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    public static String encodeTobase64(Bitmap image) {
        Bitmap immagex=image;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] b = baos.toByteArray();
        String imageEncoded = Base64.encodeToString(b, Base64.DEFAULT);

        Log.e("LOOK", imageEncoded);
        return imageEncoded;
    }

}
