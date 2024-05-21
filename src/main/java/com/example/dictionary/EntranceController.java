package com.example.dictionary;

import base.Authentication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EntranceController implements Initializable {
    @FXML
    private ImageView passwordIconImage;

    private Authentication authentication;

    @FXML
    private Label loginLabel;

    @FXML
    private Label signupLabel;

    @FXML
    private Label loginStatusLabel;

    @FXML
    private Label signupStatusLabel;

    @FXML
    private PasswordField passwordField1;

    @FXML
    private PasswordField passwordField2;

    @FXML
    private TextField usernameField;

    @FXML
    private Button loginButton1;

    @FXML
    private Button signupButton1;

    @FXML
    private Button loginButton2;

    @FXML
    private Button signupButton2;

    @FXML
    private Tooltip loginToolTip1;

    @FXML
    private Tooltip signupToolTip1;

    @FXML
    private Tooltip loginToolTip2;

    @FXML
    private Tooltip signupToolTip2;

    @FXML
    private AnchorPane viewContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        signupToolTip1.setShowDelay(Duration.ZERO);
        signupToolTip2.setShowDelay(Duration.ZERO);
        loginToolTip1.setShowDelay(Duration.ZERO);
        loginToolTip2.setShowDelay(Duration.ZERO);
        authentication = new Authentication();
        loginView(new ActionEvent());
    }

    @FXML
    public void signUpAccount(ActionEvent actionEvent) {
        String userName = usernameField.getText();
        String password1 = passwordField1.getText();
        String password2 = passwordField2.getText();

        if (userName.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
            signupStatusLabel.setText("Vui lòng nhập đầy đủ thông tin.");
            return;
        }

        if (!password1.equals(password2)) {
            signupStatusLabel.setText("Mật khẩu không khớp.");
            return;
        }

        int result = authentication.registerUser(userName, password1);
        switch (result) {
            case 0:
                signupStatusLabel.setText("Đăng ký thành công!");
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("scene.fxml"));
                    AnchorPane mainPage = loader.load();
                    viewContainer.getChildren().setAll(mainPage);
                } catch (IOException e) {
                    signupStatusLabel.setText("Đã xảy ra lỗi khi tải trang chính.");
                }
                break;
            case 1:
                signupStatusLabel.setText("Tên đăng nhập đã tồn tại.");
                break;
            case 2:
                signupStatusLabel.setText("Mật khẩu không hợp lệ.");
                break;
            case 3:
                signupStatusLabel.setText("Tên đăng nhập không hợp lệ.");
                break;
            case -1:
                signupStatusLabel.setText("Đã xảy ra lỗi. Vui lòng thử lại.");
                break;
        }
    }

    @FXML
    public void logInAccount(ActionEvent actionEvent) {
        String userName = usernameField.getText();
        String password = passwordField1.getText();

        if (userName.isEmpty() || password.isEmpty()) {
            loginStatusLabel.setText("Vui lòng nhập cả tên đăng nhập và mật khẩu.");
            return;
        }

        int result = authentication.authenticateUser(userName, password);
        switch (result) {
            case 0:
                loginStatusLabel.setText("Đăng nhập thành công!");
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("scene.fxml"));
                    AnchorPane mainPage = loader.load();
                    viewContainer.getChildren().setAll(mainPage);
                } catch (IOException e) {
                    loginStatusLabel.setText("Đã xảy ra lỗi khi tải trang chính.");
                }
                break;
            case 1:
                loginStatusLabel.setText("Sai mật khẩu.");
                break;
            case 2:
                loginStatusLabel.setText("Tên đăng nhập không tồn tại.");
                break;
            default:
                loginStatusLabel.setText("Đã xảy ra lỗi. Vui lòng thử lại.");
                break;
        }
    }

    @FXML
    public void loginView(ActionEvent actionEvent) {
        loginLabel.setVisible(true);
        signupLabel.setVisible(false);
        loginStatusLabel.setVisible(true);
        signupStatusLabel.setVisible(false);
        passwordField1.setVisible(true);
        passwordField2.setVisible(false);
        usernameField.setVisible(true);
        loginButton2.setVisible(true);
        signupButton2.setVisible(false);
        passwordIconImage.setVisible(false);
    }

    @FXML
    public void signupView(ActionEvent actionEvent) {
        loginLabel.setVisible(false);
        signupLabel.setVisible(true);
        loginStatusLabel.setVisible(false);
        signupStatusLabel.setVisible(true);
        passwordField1.setVisible(true);
        passwordField2.setVisible(true);
        usernameField.setVisible(true);
        loginButton2.setVisible(false);
        signupButton2.setVisible(true);
        passwordIconImage.setVisible(true);
    }
}
