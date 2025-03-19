package model.services;

import db.DB;
import db.Exception.DbException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginAndPasswordMaker {
    private String login;
    private String password;

    public void makerLoginAndPassword(String firstName, String lastName, String dateOfBirth) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        int userId = 0;
        try {
            conn = DB.getConnection();
            st = conn.prepareStatement("SELECT LAST_INSERT_ID()");
            rs = st.executeQuery();
            userId = 0;
            if (rs.next()){
                userId = rs.getInt(1);
            }
            String earlyLastName = lastName.substring(0, 2);
            login = firstName + earlyLastName + userId;

            String earlyFirstName = firstName.substring(0,2);
            String formatDateOfBirth = dateOfBirth.replace("-", "");
            password = earlyFirstName + formatDateOfBirth;

            System.out.println(login);
            System.out.println(password);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
