package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Control;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;

public class Controller  implements Initializable{
    @FXML
    private Circle TopLeft_Beinpresse;
    @FXML
    private Circle TopRight_Hantelbank;
    @FXML
    private ImageView MiddleMiddle_Fahrraeder_1;
    @FXML
    private ImageView MiddleMiddle_Fahrraeder_2;
    @FXML
    private ImageView MiddleMiddle_Fahrraeder_3;
    @FXML
    private ImageView MiddleMiddle_Fahrraeder_4;
    @FXML
    private ImageView MiddleMiddle_Fahrraeder_5;
    @FXML
    private ImageView MiddleMiddle_Fahrraeder_6;
    @FXML
    private ImageView MiddleMiddle_Fahrraeder_7;
    @FXML
    private ImageView MiddleMiddle_Fahrraeder_8;
    @FXML
    private ImageView BottomLeft_Hantelset_6;
    @FXML
    private ImageView BottomLeft_Hantelset_3;
    @FXML
    private ImageView BottomLeft_Hantelset_4;
    @FXML
    private ImageView BottomLeft_Hantelset_1;
    @FXML
    private ImageView BottomLeft_Hantelset_2;
    @FXML
    private ImageView BottomLeft_Hantelset_5;
    public int haltbarkeit;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			Image i = new Image(new FileInputStream("C:\\Users\\deben\\OneDrive\\Desktop\\Eclipse Stuff\\Gerätenutzung\\src\\images\\Bike-gelb.png"));
			MiddleMiddle_Fahrraeder_1.setImage(i);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
    
    public void openDetails(MouseEvent event) {
    	String  name = event.getPickResult().getIntersectedNode().getId();
		Alert  details = new Alert(AlertType.INFORMATION);
		details.setTitle("Details");
		details.setHeaderText(name);	
		details.setContentText("Details");
		details.show();
    }


}
