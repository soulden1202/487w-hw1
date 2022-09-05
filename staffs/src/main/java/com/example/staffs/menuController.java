package com.example.staffs;



import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class menuController {


    private static Connection conn;
    private ObservableList<studentTimeStamp> data;







    @FXML
    Button query;
    @FXML
    DatePicker from;
    @FXML
    DatePicker to;
    @FXML
    TextField studentId;
    @FXML
    TableView<studentTimeStamp> table;

    @FXML
    TableColumn<studentTimeStamp,String>index, sId, timeStamp;










    public static void setCon(Connection con){
       conn =con;
    }

    public void initialize(){
        index.setCellValueFactory(new PropertyValueFactory<>("index"));
        sId.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        timeStamp.setCellValueFactory(new PropertyValueFactory<>("timeStamp"));

    }


    public boolean isDbConnected(Connection con) {
        try {
            return con != null && !con.isClosed();
        } catch (SQLException ignored) {}

        return false;
    }



    public void queryAction(ActionEvent actionEvent) {


        if (isDbConnected(conn)) {

            data = FXCollections.observableArrayList();
            table.getItems().clear();
            ResultSet rs = null;
            try {
                if(from.getValue() == null && to.getValue() == null)
                {
                    if(studentId.getText().isEmpty()){

                        return;
                    }
                    else{

                        String SQL = "SELECT * from StudentLogs where studentid = '"+studentId.getText()+"'";
                        rs = conn.createStatement().executeQuery(SQL);
                    }
                }
                else{

                    if(studentId.getText().isEmpty()){
                        Date fromDate =  Date.valueOf(from.getValue());
                        Date toDate =  Date.valueOf(to.getValue().plusDays(1));
                        String SQL = "SELECT * FROM StudentLogs WHERE TimeLogs BETWEEN TO_DATE('"+ fromDate+"', 'YYYY-MM-DD') AND TO_DATE('"+ toDate+"', 'YYYY-MM-DD')" ;
                        rs = conn.createStatement().executeQuery(SQL);
                    }
                    else{
                        Date fromDate =  Date.valueOf(from.getValue());
                        Date toDate =  Date.valueOf(to.getValue().plusDays(1));
                        String SQL = "SELECT * FROM StudentLogs WHERE studentid = '"+studentId.getText()+"'" +" And TimeLogs BETWEEN TO_DATE('"+ fromDate+"', 'YYYY-MM-DD') AND TO_DATE('"+ toDate+"', 'YYYY-MM-DD')" ;
                        rs = conn.createStatement().executeQuery(SQL);
                    }

                }



                while (rs.next()) {
                    //Iterate Row
                    String[] dbdata = new String[10];
                    for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                        //Iterate Column
                        dbdata[i-1] =(rs.getString(i));
                    }
                    data.add(new studentTimeStamp(dbdata[0],dbdata[1], dbdata[2]));


                }
                table.setItems(data);
                table.refresh();




            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error on Building Data");
            }

        }

    }
}
