package com.example.cardiac_recoder_36_39;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cardiac_recoder_36_39.model.CardiacModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * This class is for showing the details of the
 */
public class ViewActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    //ArrayList<CardiacModel> dataArrayList;
    Gson gson;
    static String CARDIAC_MODEL = "CardiacModel";
    TextView date_view,time_view,systolic_view,diastolic_view,heartrate_view,comment_view;
    CardiacModel cardiacModel;
    Button edit_button;


    /**
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        date_view = findViewById(R.id.DateValue_view);
        time_view = findViewById(R.id.TimeValue_view);
        systolic_view = findViewById(R.id.SystolicValue_view);
        diastolic_view = findViewById(R.id.DiastolicValue_view);
        heartrate_view = findViewById(R.id.HeartRateValue_view);
        comment_view = findViewById(R.id.CommentValue_view);
        edit_button = findViewById(R.id.editButton);

        Intent intent = getIntent();
        int index = intent.getIntExtra("index",0);
        readData();

        cardiacModel = DataList.array.get(index);


        date_view.setText(cardiacModel.getDate().toString());
        time_view.setText(cardiacModel.getTime().toString());
        systolic_view.setText(cardiacModel.getSystolic().toString());
        diastolic_view.setText(cardiacModel.getDiastolic().toString());
        heartrate_view.setText(cardiacModel.getHeartRate().toString());
        comment_view.setText(cardiacModel.getComment().toString());

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewActivity.this, UpdateActivity.class);
                intent.putExtra("index",index);
                startActivity(intent);
                finish();
            }
        });
    }


    /**
     * This method is for getting the data from the intent
     */
    void getAndSetIntentData(){
        if(getIntent().hasExtra(CARDIAC_MODEL)){

        }
        else
        {
            Toast.makeText(this,"no data",Toast.LENGTH_SHORT).show();
        }

    }

    /**
     * This method is for reading the data from the shared preference
     */

    private void readData()
    {
        sharedPreferences = getSharedPreferences("Habib",MODE_PRIVATE);
        gson = new Gson();
        String jsonString = sharedPreferences.getString("Talha",null);
        Type type = new TypeToken<ArrayList<CardiacModel>>(){}.getType();
        DataList.array = gson.fromJson(jsonString,type);
        if(DataList.array ==null)
        {
            DataList.array = new ArrayList<>();
        }
    }


}