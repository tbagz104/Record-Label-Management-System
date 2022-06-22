package main;
import java.sql.*;

public interface LoginLogout {
    public boolean login(String uid, String pwd) throws SQLException;
}
