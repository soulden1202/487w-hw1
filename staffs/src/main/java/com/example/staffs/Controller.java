package com.example.staffs;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.SQLException;



public class Controller {

    public static OracleDataSource ods;
    public static Connection con;
    private menuForm menuForm;
    @FXML Label results;
    @FXML TextField username;
    @FXML PasswordField password;
    @FXML Button closeBtn;
    @FXML Button loginBtn;


    public void initialize() throws Exception {
        menuForm = new menuForm();

    }




    @FXML
    protected void closeAction() {

        Platform.exit();
        System.exit(0);

    }

    public static void startConnection() throws SQLException {
        ods = new OracleDataSource();
        ods.setURL("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu");

    }


    @FXML
    protected void loginAction()  {

        ods.setUser(username.getText());
        ods.setPassword(password.getText());


        try{
            con = ods.getConnection();

            if(isDbConnected(con))
            {
                results.setText("Connected");
                results.setTextFill(Color.color(0,1,0));
                Stage stage = (Stage) loginBtn.getScene().getWindow();

                menuForm.setCon(con);
                menuForm.start(stage);
            }

        }
        catch (Exception e)
        {
            System.out.println(e);
        }


        if(!isDbConnected(con)){

            results.setText("Failed to connect");
            results.setTextFill(Color.color(1,0,0));
            username.setText("");
            password.setText("");

        }

    }


    public boolean isDbConnected(Connection con) {
        try {
            return con != null && !con.isClosed();
        } catch (SQLException ignored) {}

        return false;
    }
}