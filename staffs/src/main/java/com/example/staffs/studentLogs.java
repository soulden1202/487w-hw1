package com.example.staffs;



import oracle.jdbc.pool.OracleDataSource;

import java.sql.Connection;
import java.sql.Statement;
import java.util.Scanner;

class logs {
    public static void main(String[] args) throws Exception {

        System.out.print("Connecting to the database...");

        OracleDataSource ods = new OracleDataSource();

        ods.setURL("jdbc:oracle:thin:@h3oracle.ad.psu.edu:1521/orclpdb.ad.psu.edu");
        ods.setUser("hdn5047");
        ods.setPassword("Altina1202");

//        char[] password = System.console().readPassword("Password: ");
//        ods.setPassword(String.valueOf(password));

        Connection conn = ods.getConnection();
        System.out.println("connected.");

        Statement stmt = conn.createStatement();
        Scanner sc = new Scanner(System.in);
        System.out.println("Scan Your ID: ");

        String cardInfor = sc.nextLine();

        String temp = cardInfor.split("=", 2)[1];
        String temp2 = temp.split("%", 2)[0];
        String studentID = temp2.split("A", 2)[1];

        try {
            stmt.execute("insert into Studentlogs(StudentId) values('" + studentID + "')");
            System.out.println("Added to logs");
        } catch (Exception e) {
            System.out.println("Errors");
        }

    }
}