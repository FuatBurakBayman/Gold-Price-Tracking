import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CompanyAskController extends loginController {

    @FXML
    private TextField Company_Name;

    @FXML
    private TextField Company_Name_Find;

    @FXML
    private TextField Company_Tel;

    @FXML
    private JFXButton Sign_In_Company;

    @FXML
    private FontAwesomeIcon explanation;
    
    @FXML
    private Label messagelabel;

    private AlertMessage alert = new AlertMessage();

    @FXML
    void Sign_In_Company_clicked(ActionEvent event) throws IOException{
        if(Company_Name.getText().isEmpty() || Company_Tel.getText().isEmpty() || Company_Name_Find.getText().isEmpty()){
            messagelabel.setText("Lütfen boşlukları dolduralım");
        }   
        
        Company_Tel.setTextFormatter(new TextFormatter<String>(change -> {
            String newText = change.getControlNewText();
            if (newText.matches("\\d*")) {
                return change;
            }
            return null;
        }));


        String Company_name = Company_Name.getText();
        String Company_tel = Company_Tel.getText();
        String Company_Name_find = Company_Name_Find.getText();
        String sqlAlter = "ALTER TABLE admin_users ADD Company_name VARCHAR(50), Company_tel VARCHAR(50), Company_Name_find VARCHAR(50)";
        String sqlInsert = "INSERT INTO admin_users (Company_name, Company_tel, Company_Name_find) VALUES (?, ?, ?)";

        Connection connection = null;
        PreparedStatement prepare = null;
        PreparedStatement prepareAlter = null;

        try{

            connection = Database.connectionDB();

            prepareAlter = connection.prepareStatement(sqlAlter);
            prepareAlter.executeUpdate();

            // Kullanıcı adı, telefon veya şirket ismi kontrolü
            String sqlCheck = "SELECT * FROM admin_users WHERE Company_name = ? OR Company_tel = ? OR Company_Name_find = ?";
            PreparedStatement statementCheck = connection.prepareStatement(sqlCheck);
            statementCheck.setString(1, Company_name);
            statementCheck.setString(2, Company_tel);
            statementCheck.setString(3, Company_Name_find);

            ResultSet resultSet = statementCheck.executeQuery();
            if (resultSet.next()) {
                alert.errorMessage("Username , telefon numarası yada şirket ismi zaten kullanılıyor");
                return;
            }

            prepare = connection.prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
            prepare.setString(1, Company_name);
            prepare.setString(2, Company_tel);
            prepare.setString(3, Company_Name_find);

            int affectedRows = prepare.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Kullanıcı ekleme başarısız, etkilenen satır yok.");
            }

            messagelabel.setText("Kayıt başarılı!");

                
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
}
