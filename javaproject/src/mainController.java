import java.util.ResourceBundle;
import java.io.IOException;
import java.net.URL;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class mainController implements Initializable{

    @FXML
    private JFXButton add_left_bottom_product_1;

    @FXML
    private JFXButton add_left_bottom_product_2;

    @FXML
    private JFXButton add_left_bottom_product_3;

    @FXML
    private JFXButton add_left_bottom_product_4;

    @FXML
    private JFXButton add_top_product_1;

    @FXML
    private JFXButton add_top_product_2;

    @FXML
    private JFXButton add_top_product_3;

    @FXML
    private JFXButton add_top_product_4;

    @FXML
    private JFXButton add_top_product_5;

    @FXML
    private Label current_form;

    @FXML
    private JFXButton current_prices_btn;

    @FXML
    private AnchorPane dashboard_one;

    @FXML
    private AnchorPane dashboard_one1;

    @FXML
    private AnchorPane dashboard_one11;

    @FXML
    private AnchorPane dashboard_one111;

    @FXML
    private AnchorPane dashboard_one2;

    @FXML
    private AnchorPane dashboard_one3;

    @FXML
    private AnchorPane dashboard_one31;

    @FXML
    private AnchorPane dashboard_one311;

    @FXML
    private AnchorPane dashboard_one3111;

    @FXML
    private Label date_time;

    @FXML
    private JFXButton home_btn;

    @FXML
    private JFXButton logout_btn;

    @FXML
    private Label nav_adminID;

    @FXML
    private Label nav_username;

    @FXML
    private JFXButton price_btn;

    @FXML
    private JFXButton settings_btn;

    @FXML
    private Circle top_profile;

    @FXML
    private Label top_username;

    @FXML
    private AnchorPane layer_available_products;

    @FXML
    private AnchorPane layer_main;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        layer_available_products.setVisible(false);
        layer_main.setVisible(true);
    }

    
    @FXML
    void show_product(ActionEvent event) {
                    // Yeni sahneyi yükleme
            Parent newRoot;
            try {
                newRoot = FXMLLoader.load(getClass().getResource("ChooseScene.fxml"));

                Stage newStage = new Stage();
                newStage.setTitle("Choose Product");
                newStage.setScene(new Scene(newRoot));
                newStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

}
