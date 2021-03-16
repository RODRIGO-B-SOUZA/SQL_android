package com.example.mymssqlserverdatabaseconnection_teste;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    private TextView textView;

    private static String ip = "192.168.0.20";
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
        }
        else {
            textView0.setText("Connection is null");
        }
    }
}