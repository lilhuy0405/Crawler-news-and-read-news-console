package controller;

import entity.Article;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.WebView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.DatabaseModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class LayoutController implements Initializable {
    private DatabaseModel databaseModel;
    @FXML
    private ChoiceBox<String> choiceBoxCategory;

    @FXML
    private Label lblTableTitle;

    @FXML
    private TableView<Article> tableTopNews;

    @FXML
    private TableColumn<Article, String> colTitle;

    @FXML
    private TableColumn<Article, String> colDescription;

    @FXML
    private TableColumn<Article, java.util.Date> colPublicDate;

    @FXML
    private TableColumn<Article, String> colSource;

    @FXML
    void showArticleDetail(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Article selectedArticle = (Article) tableTopNews.getSelectionModel().getSelectedItem();
            if(selectedArticle == null) {
                System.err.println("null selected item in table");
                return;
            }
            String selectedUrl = selectedArticle.getUrl();
            //log to console
            System.out.println("opening webview with url: " + selectedUrl +" .....");
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("article-details.fxml"));
                Parent root = fxmlLoader.load();
                //get Controller
                ArticleDetailController articleDetailController = (ArticleDetailController) fxmlLoader.getController();
                //init webView
                WebView webView = articleDetailController.getWebViewArticle();
                webView.getEngine().load(selectedUrl);


                //init scene stage
                Scene scene = new Scene(root, 700, 800);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setTitle(selectedArticle.getTitle());
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.getIcons().add(new Image(new FileInputStream("app-icon.png")));
                //log to console
                System.out.println("open webview with url: " + selectedUrl + " successfully!" );
                stage.showAndWait();
            } catch (IOException e) {
//                e.printStackTrace();
                System.err.println("error when load article-detail.fxml " + e.getMessage());
            }

        }
    }

    public void initialize(URL location, ResourceBundle resources) {
        //init column in table
        this.colTitle.setCellValueFactory(new PropertyValueFactory<Article, String>("title"));
        this.colDescription.setCellValueFactory(new PropertyValueFactory<Article, String>("description"));
        this.colPublicDate.setCellValueFactory(new PropertyValueFactory<Article, Date>("publicDate"));
        this.colSource.setCellValueFactory(new PropertyValueFactory<Article, String>("source"));
        //init table style
        tableTopNews.getStylesheets().add(getClass().getClassLoader().getResource("table.css").toExternalForm());

        //init value for choice box
        this.choiceBoxCategory.getItems().add("Chính Trị");
        this.choiceBoxCategory.getItems().add("Xã Hội");
        this.choiceBoxCategory.getItems().add("Thời Sự");
        this.choiceBoxCategory.getItems().add("Giải Trí");
        this.choiceBoxCategory.getItems().add("Scandal");
        //set default value
        String defaultCategory = "Chính Trị";
        this.choiceBoxCategory.setValue(defaultCategory);
//        //load data
//        loadArticleToTable(defaultCategory);

    }


    public void getNews(ActionEvent event) {

        String selectedCategory = choiceBoxCategory.getValue();

        loadArticleToTable(selectedCategory);

    }

    private int getCategoryCode(String categoryString) {
        int code = -1;
        if (categoryString.equalsIgnoreCase("chính trị")) {
            code = 1;
        } else if (categoryString.equalsIgnoreCase("xã hội")) {
            code = 2;
        } else if (categoryString.equalsIgnoreCase("thời sự")) {
            code = 3;
        } else if (categoryString.equalsIgnoreCase("giải trí")) {
            code = 4;
        } else if (categoryString.equalsIgnoreCase("scandal")) {
            code = 5;
        } else {
            code = -1;
        }

        return code;
    }

    private void loadArticleToTable(String selectedCategory) {

        databaseModel = new DatabaseModel();

        if (selectedCategory == null) {
            System.err.println("null selection");
            return;
        }
        int categoryCode = getCategoryCode(selectedCategory);
        ArrayList<Article> topTenArticles = databaseModel.getTopTenNewsByCategory(categoryCode);


        if (categoryCode != -1) {
            //clear old table content
            this.tableTopNews.getItems().clear();
            //clear old table title content
            this.lblTableTitle.setText("");
            //add new table
            this.tableTopNews.getItems().addAll(topTenArticles);
            //log to console
            System.out.println("thêm 10 tin " + selectedCategory + " vào bảng thành công");
            //set table title
            this.lblTableTitle.setText("Top 10 tin " + selectedCategory + " mới nhất");

        } else {
            System.err.println("invalid code");
            return;
        }
    }
}
