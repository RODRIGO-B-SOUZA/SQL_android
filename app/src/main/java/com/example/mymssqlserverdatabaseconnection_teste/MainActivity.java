package com.example.mymssqlserverdatabaseconnection_teste;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends AppCompatActivity {

    private TextView textView;
    TextView timerText;
    Button stopStartButton;

    private static String ip = "192.168.1.111";
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver";
    private static String database = "SENSOR_MOTOR";
    private static String username = "testeandroid";
    private static String password = "@teste_";
    private static String url = "jdbc:jtds:sqlserver://"+ip+":"+port+"/"+database;

    private Connection connection = null;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;
    private TextView textView7;
    private TextView textView8;
    private TextView textView9;
    private TextView textView10;
    private TextView textView0;
    private TextView Horimetro;



    Timer timer;
    TimerTask timerTask;
    Double time = 0.0;

    boolean timerStarted = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        textView9 = findViewById(R.id.textView9);
        textView10 = findViewById(R.id.textView10);
        textView0 = findViewById(R.id.textView0);
        Horimetro = findViewById(R.id.Horimetro);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Classes);
            connection = DriverManager.getConnection(url, username,password);
            textView0.setText("CONECTADO!");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            textView0.setText("ERRO!");
        } catch (SQLException e) {
            e.printStackTrace();
            textView0.setText("FALHA!");
        }
        timer = new Timer();

        timerText = (TextView) findViewById(R.id.timerText);
        stopStartButton = (Button) findViewById(R.id.startStopButton);
    }
    public void sqlButton(View view){
        if (connection!=null){
            Statement statement = null;
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT TOP 1 cast(FVALUE as numeric(5,2)) FROM DATA WHERE ID_SENSOR=1 ORDER BY DATETIME DESC");

                while (resultSet.next()){
                    textView.setText(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT TOP 1 cast(FVALUE as numeric(5,2)) FROM DATA WHERE ID_SENSOR=2 ORDER BY DATETIME DESC");

                while (resultSet.next()){
                    textView2.setText(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT TOP 1 cast(FVALUE as numeric(8,2)) FROM DATA WHERE ID_SENSOR=3 ORDER BY DATETIME DESC");

                while (resultSet.next()){
                    textView3.setText(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT TOP 1 cast(FVALUE as numeric(8,2)) FROM DATA WHERE ID_SENSOR=4 ORDER BY DATETIME DESC");

                while (resultSet.next()){
                    textView4.setText(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT TOP 1 cast(FVALUE as numeric(10,2)) FROM DATA WHERE ID_SENSOR=5 ORDER BY DATETIME DESC");

                while (resultSet.next()){
                    textView5.setText(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT TOP 1 cast(FVALUE as numeric(5,2)) FROM DATA WHERE ID_SENSOR=7 ORDER BY DATETIME DESC");

                while (resultSet.next()){
                    textView7.setText(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT TOP 1 cast(IVALUE as numeric(10,2)) FROM DATA WHERE ID_SENSOR=8 ORDER BY DATETIME DESC");

                while (resultSet.next()){
                    textView8.setText(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT TOP 1 cast(IVALUE as numeric(5,2)) FROM DATA WHERE ID_SENSOR=9 ORDER BY DATETIME DESC");

                while (resultSet.next()){
                    textView9.setText(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT TOP 1 cast(IVALUE as numeric(5,2)) FROM DATA WHERE ID_SENSOR=10 ORDER BY DATETIME DESC");

                while (resultSet.next()){
                    textView10.setText(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT CONVERT(VARCHAR, ROUND(SUM(FVALUE)/3600,2)) + ' h' FROM DATA WHERE ID_SENSOR=12");

                while (resultSet.next()){
                    Horimetro.setText(resultSet.getString(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        else {
            textView0.setText("Connection is null");
        }
    }


    public void startStopTapped(View view)
    {

        Statement statement = null;
        if(timerStarted == false)
        {
            timerStarted = true;
            setButtonUI("Parar Contagem");

            startTimer();
        }
        else
        {
            timerStarted = false;
            setButtonUI("Iniciar Contagem");

            timerTask.cancel();
            int resposta = 0;
            try {


            PreparedStatement pst = connection.prepareStatement("INSERT INTO DATA (FVALUE, ID_SENSOR)" + "VALUES (?,12)");
                pst.setString(1, getTimerText());

                resposta = pst.executeUpdate();


                if(timerTask != null)
                {
                    timerTask.cancel();
                    //setButtonUI("START", R.color.green);
                    time = 0.0;
                    timerStarted = false;
                    timerText.setText(formatTime(0,0,0));

                }


            }catch (Exception e){
                e.getMessage();

            }

        }
    }

    private void startTimer()
    {
        timerTask = new TimerTask()
        {
            @Override
            public void run()
            {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        time++;
                        timerText.setText(getTimerText());
                    }
                });
            }

        };
        timer.scheduleAtFixedRate(timerTask, 0 ,1000);
    }

    private String getTimerText()
    {
        int rounded = (int) Math.round(time);

        int seconds = ((rounded % 86400) % 3600) % 60;
        int minutes = ((rounded % 86400) % 3600) / 60;
        int hours = ((rounded % 86400) / 3600);

        //return formatTime(seconds, minutes, hours);
        return String.format("%06d",rounded);
    }

    private void setButtonUI(String start)
    {
        stopStartButton.setText(start);
    }

    private String formatTime(int seconds, int minutes, int hours)
    {
        //return String.format("%02d",hours) + " : " + String.format("%02d",minutes) + " : " + String.format("%02d",seconds);
        return String.format("%02d",hours) +  String.format("%02d",minutes) + String.format("%02d",seconds);

    }
}