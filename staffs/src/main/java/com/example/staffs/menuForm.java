package com.example.staffs;


import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;

public class menuForm {


   private static Connection conn;





    public void start(Stage stage) throws Exception {

        //menuController.setCon(conn);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("timestamp.fxml"));
        // fxmlLoader.setController(new Controller());
        Scene scene = new Scene(fxmlLoader.load(), 625, 475);
        stage.setTitle("Query Menu");
        stage.setScene(scene);
        menuController.setCon(conn);
        stage.show();
    }

    public static void setCon(Connection con){
        conn = con;
    }
}
