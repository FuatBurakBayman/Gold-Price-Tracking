import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class ChooseScene implements Initializable{

    @FXML
    private JFXButton Bilezik_btn;

    @FXML
    private AnchorPane Ceyrek_Yeni_Determination_layer;

    @FXML
    private JFXButton CeyrekEski_btn;

    @FXML
    private JFXButton CeyrekYeni_btn;

    @FXML
    private TextField Ceyrek_Eski_Cost;

    @FXML
    private AnchorPane Ceyrek_Eski_Determination_layer;

    @FXML
    private TextField Ceyrek_Eski_profit;

    @FXML
    private TextField Ceyrek_Yeni_Cost;

    @FXML
    private TextField Ceyrek_Yeni_profit;

    @FXML
    private JFXButton Ceyrek_btn;

    @FXML
    private AnchorPane Ceyrek_layer;

    @FXML
    private TextField Eski_Tam_Cost;

    @FXML
    private TextField Eski_Tam_profit;

    @FXML
    private TextField Eski_Yarim_Cost;

    @FXML
    private TextField Eski_Yarim_profit;

    @FXML
    private JFXButton Kupe_btn;

    @FXML
    private JFXButton Pirlanta_btn;

    @FXML
    private JFXButton Takilar_btn;

    @FXML
    private JFXButton TamEski_btn;

    @FXML
    private JFXButton TamYeni_btn;

    @FXML
    private AnchorPane Tam_Eski_Determination_layer;

    @FXML
    private AnchorPane Tam_Yeni_Determination_layer;

    @FXML
    private JFXButton Tam_btn;

    @FXML
    private AnchorPane Tam_layer;

    @FXML
    private JFXButton YarimEski_btn;

    @FXML
    private JFXButton YarimYeni_btn;

    @FXML
    private AnchorPane Yarim_Eski_Determination_layer;

    @FXML
    private AnchorPane Yarim_Yeni_Determination_layer;

    @FXML
    private JFXButton Yarim_btn;

    @FXML
    private AnchorPane Yarim_layer;

    @FXML
    private TextField Yeni_Tam_Cost;

    @FXML
    private TextField Yeni_Tam_Profit;

    @FXML
    private TextField Yeni_Yarim_Cost;

    @FXML
    private TextField Yeni_Yarim_Profit;

    @FXML
    private JFXButton Ziynet_btn;

    @FXML
    private AnchorPane main_layer;

    @FXML
    private VBox nav_layer;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        main_layer.setVisible(true);
        nav_layer.setVisible(true);
        Ceyrek_layer.setVisible(false);
        Yarim_layer.setVisible(false);
        Tam_layer.setVisible(false);
        
    }

    @FXML
    void Bilezik_clicked(ActionEvent event) {

    }

    @FXML
    void Ceyrek_clicked(ActionEvent event) {
        Ceyrek_layer.setVisible(true);
        main_layer.setVisible(false);
    }

    @FXML
    void Kupe_clicked(ActionEvent event) {

    }

    @FXML
    void Pirlanta_clicked(ActionEvent event) {

    }

    @FXML
    void Takilar_clicked(ActionEvent event) {

    }

    @FXML
    void Tam_clicked(ActionEvent event) {

    }

    @FXML
    void Yarim_clicked(ActionEvent event) {

    }

    @FXML
    void Ziynet_clicked(ActionEvent event) {

    }

    @FXML
    void Ceyrek_eski(MouseEvent event) {
        Ceyrek_Eski_Determination_layer.setVisible(true);
        Ceyrek_Yeni_Determination_layer.setVisible(false);
    }

    @FXML
    void Ceyrek_yeni(MouseEvent event) {
        Ceyrek_Eski_Determination_layer.setVisible(false);
        Ceyrek_Yeni_Determination_layer.setVisible(true);
    }

}
