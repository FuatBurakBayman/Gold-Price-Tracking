import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Stop;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.scene.Node;


public class loginController implements Initializable {

    @FXML
    private JFXButton Admin_scene_button;

    @FXML
    private JFXButton User_scene_button;

    @FXML
    private JFXButton Sign_scene_button;

    @FXML
    private PasswordField Login_password_field;

    @FXML
    private JFXButton Login_scene_button;

    @FXML
    private Label Password_forget;

    @FXML
    private JFXButton Register_Sing_In_button;

    @FXML
    private AnchorPane account_layer;

    @FXML
    private VBox layer1;

    @FXML
    private VBox layer2;

    @FXML
    private VBox layer3;

    @FXML
    private TextField login_Username;

    @FXML
    private JFXButton login_button;

    @FXML
    private Label login_button_message;

    @FXML
    private Label login_button_message1;

    @FXML
    private AnchorPane menu_layer;

    @FXML
    private StackPane stackPane;

    @FXML
    private VBox layer_admin;

    @FXML
    private JFXButton Back_to_layer;

    @FXML
    private JFXCheckBox login_checkBox;

    @FXML
    private TextField First_Name;

    @FXML
    private TextField Last_Name;

    @FXML
    private TextField login_showPassword;
    
    @FXML
    private TextField Register_email;

    @FXML
    private TextField Register_username;

    @FXML
    private PasswordField Register_password;

    @FXML
    private Label messagelabel;

    @FXML
    private TextField Admin_Username;

    @FXML
    private JFXCheckBox Admin_checkBox;

    @FXML
    private PasswordField Admin_password_field;

    @FXML
    private TextField Admin_shadowpassword;

    @FXML
    private Label Admin_button_message;

    @FXML
    private TextField Admin_Name;

    @FXML
    private TextField Admin_username_register;

    @FXML
    private TextField Admin_email;

    @FXML
    private TextField Admin_lastName;

    @FXML
    private PasswordField Admin_password;

    @FXML
    private JFXButton Admin_Sing_In_button;

    @FXML
    private Label messagelabel_admin;

    @FXML
    private VBox Admin_sign_in_layer;

    @FXML
    private JFXButton admin_sign_in;

    @FXML
    private PasswordField Admin_ID;

    private Connection connection;

    private PreparedStatement prepare;

    private ResultSet result;

    private AlertMessage alert = new AlertMessage();

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
    );


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (layer1 != null) {
            layer1.setVisible(true);
        } else {
            System.err.println("layer1 is null");
        }

        if (layer2 != null) {
            layer2.setVisible(false);
        } else {
            System.err.println("layer2 is null");
        }

        if (layer3 != null) {
            layer3.setVisible(true);
        } else {
            System.err.println("layer3 is null");
        }

        if (layer_admin != null) {
            layer_admin.setVisible(false);
        } else {
            System.err.println("layer_admin is null");
        }

        if (Back_to_layer != null) {
            Back_to_layer.setVisible(false);
        } else {
            System.err.println("Back_to_layer is null");
        }

        if (Admin_sign_in_layer != null) {
            Admin_sign_in_layer.setVisible(false);
        } else {
            System.err.println("Admin_sign_in_layer is null");
        }
    }

    public static boolean isValidEmail(String email) {
        if (email == null) {
            return false;
        }
        Matcher matcher = EMAIL_PATTERN.matcher(email);
        return matcher.matches();
    }

    public void registerAccount() {

    String FirstName = First_Name.getText();
    String LastName = Last_Name.getText();
    String Email = Register_email.getText();
    String Username = Register_username.getText();
    String Password = Register_password.getText();

    try {
        if (Password.length() < 8) {
            messagelabel.setText("8 karakter üstünde bir şifre giriniz");
            return;
        } else if (Username.length() < 4) {
            messagelabel.setText("Kullanıcı adı en az 4 karakter olmalı");
            return;
        } else if (!isValidEmail(Email)) {
            messagelabel.setText("Geçerli bir e-posta adresi giriniz");
            return;
        } else {

            Connection connection = Database.connectionDB();
            String sql = "INSERT INTO Users (First_Name, Last_Name, Email, Username, Password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, FirstName);
            statement.setString(2, LastName);
            statement.setString(3, Email);
            statement.setString(4, Username);
            statement.setString(5, Password);
            statement.executeUpdate();

            String sqlCheck = "SELECT * FROM Users WHERE Username = ? OR Email = ?";
            PreparedStatement statementCheck = connection.prepareStatement(sqlCheck);
            statementCheck.setString(1, Username);
            statementCheck.setString(2, Email);
            ResultSet resultSet = statementCheck.executeQuery();
            if (resultSet.next()) {
                alert.errorMessage("Kullanıcı adı veya e-posta adresi zaten kullanılıyor");
                return;
            }

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);
                System.out.println("Yeni kullanıcı ID'si: " + userId);
            }

            messagelabel.setText("Kayıt başarılı!");

            MouseEvent event = new MouseEvent(MouseEvent.MOUSE_CLICKED, 0, 0, 0, 0, MouseButton.PRIMARY, 1, true, true, true, true, true, true, true, true, true, true, null);
            login_clicked(event);

        }    
        

    } catch (SQLException e) {
        e.printStackTrace();
        messagelabel.setText("Kayıt başarısız: " + e.getMessage());
    } finally {
        try {
            if (prepare != null) prepare.close();
            if (connection != null) connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}   

    @FXML
    boolean Sign_In_clicked(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(1));
        slide.setNode(menu_layer);

        slide.setToX(account_layer.getWidth());
        slide.play();

        TranslateTransition slideAccount = new TranslateTransition();
        slideAccount.setDuration(Duration.seconds(1));
        slideAccount.setNode(account_layer);

        slideAccount.setToX(-menu_layer.getWidth());
        slideAccount.play();

        layer1.setVisible(false);
        layer2.setVisible(true);
        layer3.setVisible(true);
        layer_admin.setVisible(false);
        Back_to_layer.setVisible(false);
        Admin_sign_in_layer.setVisible(false);

        slide.setOnFinished((e -> {
            Login_scene_button.getStyleClass().remove("login_button_clicked");
            Login_scene_button.getStyleClass().add("login_button");

            Sign_scene_button.getStyleClass().remove("sign_button");
            Sign_scene_button.getStyleClass().add("sign_button_clicked");

            Admin_scene_button.getStyleClass().remove("Admin_button_clicked");
            Admin_scene_button.getStyleClass().add("Admin_button");

            admin_sign_in.getStyleClass().remove("Admin_button_Sign_In_clicked");
            admin_sign_in.getStyleClass().add("Admin_button_Sign_In");
        }));

        return false;
    }

    @FXML
    boolean login_clicked(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(1));
        slide.setNode(menu_layer);
        slide.setToX(0);
        slide.play();

        TranslateTransition slideAccount = new TranslateTransition();
        slideAccount.setDuration(Duration.seconds(1));
        slideAccount.setNode(account_layer);

        slideAccount.setToX(0);
        slideAccount.play();

        layer1.setVisible(true);
        layer2.setVisible(false);
        layer3.setVisible(true);
        layer_admin.setVisible(false);
        Back_to_layer.setVisible(false);
        Admin_sign_in_layer.setVisible(false);

        slide.setOnFinished((e -> {
            Login_scene_button.getStyleClass().remove("login_button");
            Login_scene_button.getStyleClass().add("login_button_clicked");

            Sign_scene_button.getStyleClass().remove("sign_button_clicked");
            Sign_scene_button.getStyleClass().add("sign_button");

            Admin_scene_button.getStyleClass().remove("Admin_button_clicked");
            Admin_scene_button.getStyleClass().add("Admin_button");

            admin_sign_in.getStyleClass().remove("Admin_button_Sign_In_clicked");
            admin_sign_in.getStyleClass().add("Admin_button_Sign_In");
        }));

        return false;
    }

    public void login_button_On_Action(ActionEvent e) {


        if (login_Username.getText().isEmpty() || Login_password_field.getText().isEmpty()) {
            login_button_message.setText("Lütfen kullanıcı adı veya şifrenizi giriniz");
        } else {

            String sql = "SELECT * FROM  Users WHERE Username = ? AND Password = ?";

            connection = Database.connectionDB();

            try {

                if(!login_showPassword.isVisible()){
                    if(!login_showPassword.getText().equals(Login_password_field.getText())){
                        login_showPassword.setText(Login_password_field.getText());
                    }else{
                        if(!login_showPassword.getText().equals(Login_password_field.getText())){
                            Login_password_field.setText(login_showPassword.getText());
                        }
                    }
                }

                prepare = connection.prepareStatement(sql);
                prepare.setString(1, login_Username.getText());
                prepare.setString(2, Login_password_field.getText());
                result = prepare.executeQuery();

                if (result.next()){
                    int userID = result.getInt("UserID");
                    login_button_message.setText("giriş başarılı");
                    System.out.println("giriş yapan kullanıcı id:" + userID );
                    
                }else{
                    login_button_message.setText("giriş başarısız");
                
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            } finally {
                try {
                    if (prepare != null) prepare.close();
                    if (connection != null) connection.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }

        }
    }

    
    public void loginShowPassword() {
        if(login_checkBox.isSelected()){
            login_showPassword.setText(Login_password_field.getText());
            login_showPassword.setVisible(true);    
            Login_password_field.setVisible(false);
        }else{
            Login_password_field.setText(login_showPassword.getText());
            login_showPassword.setVisible(false);
            Login_password_field.setVisible(true);
        }
    }

    @FXML
    boolean admin_clicked(MouseEvent event) {
        if(Admin_sign_in_layer.isVisible()){
            TranslateTransition slide = new TranslateTransition();
            slide.setDuration(Duration.seconds(1));
            slide.setNode(menu_layer);

            slide.setToX(0);
            slide.play();

            TranslateTransition slideAccount = new TranslateTransition();
            slideAccount.setDuration(Duration.seconds(1));
            slideAccount.setNode(account_layer);

            slideAccount.setToX(0);
            slideAccount.play();

            layer1.setVisible(false);
            layer2.setVisible(false);
            layer3.setVisible(true);
            layer_admin.setVisible(true);
            Back_to_layer.setVisible(false);
            Admin_sign_in_layer.setVisible(false);

            slide.setOnFinished((e -> {
                Login_scene_button.getStyleClass().remove("login_button");
                Login_scene_button.getStyleClass().add("login_button_clicked");

                Sign_scene_button.getStyleClass().remove("sign_button_clicked");
                Sign_scene_button.getStyleClass().add("sign_button");

                Admin_scene_button.getStyleClass().remove("Admin_button_clicked");
                Admin_scene_button.getStyleClass().add("Admin_button");

                admin_sign_in.getStyleClass().remove("Admin_button_Sign_In_clicked");
                admin_sign_in.getStyleClass().add("Admin_button_Sign_In");
            }));
                return false;
        }else{


        TranslateTransition slideAccount_up;
        if (layer1.isVisible()) {
            slideAccount_up = new TranslateTransition();
            slideAccount_up.setDuration(Duration.seconds(1));
            slideAccount_up.setNode(layer1);
            slideAccount_up.setToY(-account_layer.getHeight());
        } else {
            slideAccount_up = new TranslateTransition();
            slideAccount_up.setDuration(Duration.seconds(1));
            slideAccount_up.setNode(layer2);
            slideAccount_up.setToY(-account_layer.getHeight());
        }

        layer_admin.setVisible(true);
        

        TranslateTransition slideAdmin = new TranslateTransition();
        slideAdmin.setDuration(Duration.seconds(1.3));
        slideAdmin.setNode(layer_admin);
        slideAdmin.setFromY(account_layer.getHeight());
        slideAdmin.setToY(0);

        slideAccount_up.setOnFinished((e -> {
            layer1.setVisible(false);
            layer2.setVisible(false);
            Back_to_layer.setVisible(true);
            Admin_sign_in_layer.setVisible(false);
   
        }));

        slideAdmin.setOnFinished((e -> {
            Login_scene_button.getStyleClass().remove("login_button_clicked");
            Login_scene_button.getStyleClass().add("login_button");

            Sign_scene_button.getStyleClass().remove("sign_button_clicked");
            Sign_scene_button.getStyleClass().add("sign_button");

            Admin_scene_button.getStyleClass().remove("Admin_button");
            Admin_scene_button.getStyleClass().add("Admin_button_clicked");

            admin_sign_in.getStyleClass().remove("Admin_button_Sign_In_clicked");
            admin_sign_in.getStyleClass().add("Admin_button_Sign_In");
        }));

        ParallelTransition parallelTransition = new ParallelTransition(slideAccount_up, slideAdmin);
        parallelTransition.play();

        return false;
        }
    }


    public static String generateRandomID() {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "@#$%&";

        String allCharacters = upperCaseLetters + lowerCaseLetters + specialCharacters;
        StringBuilder randomID = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(allCharacters.length());
            randomID.append(allCharacters.charAt(index));
        }

        return ("AID-"+randomID.toString());
    }


    @FXML
    void AdminAccount(ActionEvent event) throws IOException{
            String Admin_FirstName = Admin_Name.getText();
            String admin_lastname = Admin_lastName.getText();
            String admin_email = Admin_email.getText();
            String Admin_Username = Admin_username_register.getText();
            String Admin_Password = Admin_password.getText();

            String sql = "INSERT INTO admin_users (admin_id, Admin_FirstName, admin_lastname, admin_email, admin_username, admin_password) VALUES (?, ?, ?, ?, ?, ?)";
            String sqlCheck = "SELECT * FROM admin_users WHERE admin_email = ? OR admin_username = ?";

            Connection connection = null;
            PreparedStatement prepare = null;
            PreparedStatement prepareCheck = null;
            ResultSet resultSetCheck = null;
            ResultSet generatedKeys = null;
            String randomID = generateRandomID();
            try {
                if (Admin_Password.length() < 8) {
                    messagelabel.setText("8 karakter üstünde bir şifre giriniz");
                    return;
                } else if (Admin_Username.length() < 4) {
                    messagelabel.setText("Kullanıcı adı en az 4 karakter olmalı");
                    return;
                } else if (!isValidEmail(admin_email)) {
                    messagelabel.setText("Geçerli bir e-posta adresi giriniz");
                    return;
                } else {

                connection = Database.connectionDB();

                prepareCheck = connection.prepareStatement(sqlCheck);
                prepareCheck.setString(1, admin_email);
                prepareCheck.setString(2, Admin_Username);
                resultSetCheck = prepareCheck.executeQuery();
        
                if (resultSetCheck.next()) {
                    alert.errorMessage("Kullanıcı adı veya e-posta adresi zaten kullanılıyor");
                    return;
                }

                prepare = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                prepare.setString(1, randomID);
                prepare.setString(2, Admin_FirstName);
                prepare.setString(3, admin_lastname);
                prepare.setString(4, admin_email);
                prepare.setString(5, Admin_Username);
                prepare.setString(6, Admin_Password);
                
                int affectedRows = prepare.executeUpdate();
            
                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }
            
                generatedKeys = prepare.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    System.out.println("Yeni kullanıcı ID'si: " + id);
                }
            
                messagelabel_admin.setText("Kayıt başarılı!");
            
                alert.successMessage("Senin admin id: "+randomID +" sakın kaybetmeyin bir yere not alınız yoksa emeğiniz boşa gidebilir");

                Parent scene3Parent = FXMLLoader.load(getClass().getResource("CompanyAsk.fxml"));

                Scene scene = new Scene(scene3Parent);

                String css = this.getClass().getResource("SignInCompany.css").toExternalForm();
                scene.getStylesheets().add(css);

                Stage NewWindow = new Stage();
                NewWindow.initStyle(StageStyle.DECORATED);
                NewWindow.setScene(scene);
                NewWindow.show();

                Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                currentWindow.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (generatedKeys != null) generatedKeys.close();
                    if (prepare != null) prepare.close();
                    if (connection != null) connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
    }

    
    @FXML
    public void Admin_button_on_Action(ActionEvent event) throws IOException{
        if(Admin_Username.getText().isEmpty() || Admin_password_field.getText().isEmpty () || Admin_ID.getText().isEmpty()){
            Admin_button_message.setText("Lütfen kullanıcı adı veya şifrenizi giriniz yada id nizi doğru giriniz");
        }else{
                String sql = "SELECT * FROM admin_users WHERE admin_username = ? AND admin_password = ? AND admin_id = ?";
    

                connection = Database.connectionDB();
            try {
                    
                if(!Admin_shadowpassword.isVisible()){
                    if(!Admin_shadowpassword.getText().equals(Admin_password_field.getText())){
                            Admin_shadowpassword.setText(Admin_password_field.getText());
                    }else{
                        if(!Admin_shadowpassword.getText().equals(Admin_password_field.getText())){
                            Admin_password_field.setText(Admin_shadowpassword.getText());
                        }
                    }
                }        
                    prepare = connection.prepareStatement(sql);
                    prepare.setString(1, Admin_Username.getText());
                    prepare.setString(2, Admin_password_field.getText());
                    prepare.setString(3, Admin_ID.getText());
                    result = prepare.executeQuery();
                    
                    if(result.next()){
                        int adminID = result.getInt("id");
                        Admin_button_message.setText("giriş başarılı");
                        System.out.println("Giriş yapan kullanıcı id: " + adminID);

                        Parent scene2Parent = FXMLLoader.load(getClass().getResource("MainScene.fxml"));

                        Scene scene = new Scene(scene2Parent);

                        String css = this.getClass().getResource("application.css").toExternalForm();
                        scene.getStylesheets().add(css);

                        Stage newWindow = new Stage();
                        newWindow.initStyle(StageStyle.DECORATED);
                        newWindow.setScene(scene);
                        newWindow.show();

                        Stage currentWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        currentWindow.close();

                    }else{
                        Admin_button_message.setText("giriş başarısız!");
                    }



                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (prepare != null) prepare.close();
                        if (connection != null) connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
        }  
    }
    
    @FXML
    void AdminShowPassword(ActionEvent event) {
        if(Admin_checkBox.isSelected()){
            Admin_shadowpassword.setText(Admin_password_field.getText());
            Admin_shadowpassword.setVisible(true);    
            Admin_password_field.setVisible(false);
        }else{
            Admin_password_field.setText(Admin_shadowpassword.getText());
            Admin_shadowpassword.setVisible(false);
            Admin_password_field.setVisible(true);
        }
    }

    
    @FXML
    void admin_Sign_in_clicked(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(1));
        slide.setNode(menu_layer);

        slide.setToX(account_layer.getWidth());
        slide.play();

        TranslateTransition slideAccount = new TranslateTransition();
        slideAccount.setDuration(Duration.seconds(1));
        slideAccount.setNode(account_layer);

        slideAccount.setToX(-menu_layer.getWidth());
        slideAccount.play();

        layer1.setVisible(false);
        layer2.setVisible(false);
        layer3.setVisible(true);
        layer_admin.setVisible(false);
        Back_to_layer.setVisible(true);
        Admin_sign_in_layer.setVisible(true);

        slide.setOnFinished((e -> {
            Login_scene_button.getStyleClass().remove("login_button_clicked");
            Login_scene_button.getStyleClass().add("login_button");

            Sign_scene_button.getStyleClass().remove("sign_button_clicked");
            Sign_scene_button.getStyleClass().add("sign_button");

            Admin_scene_button.getStyleClass().remove("Admin_button_clicked");
            Admin_scene_button.getStyleClass().add("Admin_button");

            admin_sign_in.getStyleClass().remove("Admin_button_Sign_In");
            admin_sign_in.getStyleClass().add("Admin_button_Sign_In_clicked");

        }));

    }

    @FXML
    boolean back_to_layer_clicked(MouseEvent event) {
        // Admin katmanını aşağı kaydır
        TranslateTransition slideAdmin_down = new TranslateTransition();
        slideAdmin_down.setDuration(Duration.seconds(1));
        slideAdmin_down.setNode(layer_admin);
        slideAdmin_down.setToY(account_layer.getHeight());

        // Hangi katmanın görünür olduğuna bağlı olarak animasyonu ayarla
        TranslateTransition slideAccount_down = null;
        if (login_clicked(event)) {
            slideAccount_down = new TranslateTransition();
            slideAccount_down.setDuration(Duration.seconds(1.3));
            slideAccount_down.setNode(layer1);
            slideAccount_down.setToY(0);
        } else if (Sign_In_clicked(event)) {
            slideAccount_down = new TranslateTransition();
            slideAccount_down.setDuration(Duration.seconds(1.3));
            slideAccount_down.setNode(layer2);
            slideAccount_down.setToY(0);
        } else {
            slideAccount_down = new TranslateTransition();
            slideAccount_down.setDuration(Duration.seconds(1.3));
            slideAccount_down.setNode(layer1);
            slideAccount_down.setToY(0);
        }

        // Paralel animasyonları oynat
        if (slideAccount_down != null) {
            ParallelTransition parallelTransition = new ParallelTransition(slideAccount_down, slideAdmin_down);
            parallelTransition.setOnFinished(e -> {
                // Animasyon bitiminde gerekli UI güncellemelerini yap
                layer_admin.setVisible(false);
                Back_to_layer.setVisible(false);

                if (login_clicked(event)) {
                    Login_scene_button.getStyleClass().remove("login_button");
                    Login_scene_button.getStyleClass().add("login_button_clicked");

                    Sign_scene_button.getStyleClass().remove("sign_button_clicked");
                    Sign_scene_button.getStyleClass().add("sign_button");

                    Admin_scene_button.getStyleClass().remove("Admin_button_clicked");
                    Admin_scene_button.getStyleClass().add("Admin_button");

                    admin_sign_in.getStyleClass().remove("Admin_button_Sign_In_clicked");
                    admin_sign_in.getStyleClass().add("Admin_button_Sign_In");
                } else if (Sign_In_clicked(event)) {
                    Login_scene_button.getStyleClass().remove("login_button_clicked");
                    Login_scene_button.getStyleClass().add("login_button");

                    Sign_scene_button.getStyleClass().remove("sign_button");
                    Sign_scene_button.getStyleClass().add("sign_button_clicked");

                    Admin_scene_button.getStyleClass().remove("Admin_button_clicked");
                    Admin_scene_button.getStyleClass().add("Admin_button");

                    admin_sign_in.getStyleClass().remove("Admin_button_Sign_In_clicked");
                    admin_sign_in.getStyleClass().add("Admin_button_Sign_In");
                }
            });

            parallelTransition.play();
        }

        return true;
    }
}
