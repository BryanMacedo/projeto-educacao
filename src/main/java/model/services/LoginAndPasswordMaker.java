package model.services;

import db.DB;
import db.Exception.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class LoginAndPasswordMaker {

    public void makerLoginAndPassword(String firstName, String lastName, String dateOfBirth) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        int userId = 0;
        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT Id FROM users WHERE FirstName = ? ");
            st.setString(1, firstName);
            rs = st.executeQuery();

            if (rs.next()) {
                userId = rs.getInt("Id");
            }


            String earlyLastName = lastName.substring(0, 2);
            String login = firstName + earlyLastName + userId;

            String earlyFirstName = firstName.substring(0,2);
            String formatDateOfBirth = dateOfBirth.replace("-", "");
            String password = earlyFirstName + formatDateOfBirth;



            System.out.println(login);
            System.out.println(password);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }
}
