import entity.Article;
import helper.ConnectionHelper;
import helper.ConvertHelper;
import model.DatabaseModel;
import view.ConsoleView;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        new ConsoleView().generateMenu();
    }
}
