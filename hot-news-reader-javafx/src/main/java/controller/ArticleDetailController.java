package controller;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

public class ArticleDetailController {

    @FXML
    private WebView webViewArticle;

    public WebView getWebViewArticle() {
        return webViewArticle;
    }

    public void setWebViewArticle(WebView webViewArticle) {
        this.webViewArticle = webViewArticle;
    }
}
