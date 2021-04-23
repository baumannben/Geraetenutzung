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
    private ImageView fahrrad_1;
    @FXML
    private ImageView fahrrad_2;
    @FXML
    private ImageView fahrrad_3;
    @FXML
    private ImageView fahrrad_4;
    @FXML
    private ImageView fahrrad_5;
    @FXML
    private ImageView fahrrad_6;
    @FXML
    private ImageView fahrrad_7;
    @FXML
    private ImageView fahrrad_8;
    @FXML
    private ImageView hantelset_6;
    @FXML
    private ImageView hantelset_2;
    @FXML
    private ImageView hantelset_3;
    @FXML
    private ImageView hantelset_4;
    @FXML
    private ImageView hantelset_1;
    @FXML
    private ImageView hantelset_5;
    @FXML
    private ImageView beinpresse_1;
    @FXML
    private ImageView beinpresse_2;
    @FXML
    private ImageView beinpresse_3;
    @FXML
    private ImageView beinpresse_4;
    @FXML
    private ImageView beinpresse_5;
    @FXML
    private ImageView beinpresse_6;
    @FXML
    private ImageView hantelbank_1;
    @FXML
    private ImageView hantelbank_2;
    @FXML
    private ImageView hantelbank_3;
    @FXML
    private ImageView hantelbank_4;
    @FXML
    private ImageView hantelbank_5;
    @FXML
    private ImageView hantelbank_6;
    @FXML
    private ImageView latzug_1;
    @FXML
    private ImageView latzug_2;
    @FXML
    private ImageView latzug_3;
    @FXML
    private ImageView latzug_4;
    
    public double[] haltbarkeiten = {0.8,0.3,1.0,0.6};
    
    public ImageView[] fahrraeder;
    public ImageView[] latzuege;
    public ImageView[] hantelbaenke;
    public ImageView[] beinpressen;
    public ImageView[] hantelsets;
    
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fahrraeder  = new ImageView[]{fahrrad_1,fahrrad_2,fahrrad_3,fahrrad_4,fahrrad_5,fahrrad_6,fahrrad_7,fahrrad_8};
		initFahrraeder();
		latzuege = new ImageView[] {latzug_1,latzug_2,latzug_3,latzug_4};
		initLatzuege();
		hantelbaenke = new ImageView[] {hantelbank_1,hantelbank_2,hantelbank_3,hantelbank_4,hantelbank_5,hantelbank_6};
		initHantelbaenke();
		beinpressen = new ImageView[] {beinpresse_1,beinpresse_2,beinpresse_3,beinpresse_4,beinpresse_5,beinpresse_6};
		initBeinpressen();
		hantelsets = new ImageView[] {hantelset_1,hantelset_2,hantelset_3,hantelset_4,hantelset_5,hantelset_6};
		initHantelsets();
		
	}
	
	public void initFahrraeder() {
		for (int i = 0; i < haltbarkeiten.length; i++) {
			if (haltbarkeiten[i] < 1 && haltbarkeiten[i] >= 0.7) {
				try {
					Image j = new Image(new FileInputStream("C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-grun.png"));
					fahrraeder[i].setImage(j);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			else if (haltbarkeiten[i] < 0.7 && haltbarkeiten[i] > 0.3) {
				try {
					Image j = new Image(new FileInputStream("C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-gelb.png"));
					fahrraeder[i].setImage(j);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			else if (haltbarkeiten[i] <= 0.3) {
				try {
					Image j = new Image(new FileInputStream("C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-rot.png"));
					fahrraeder[i].setImage(j);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void initHantelsets() {
		for (int i = 0; i < haltbarkeiten.length; i++) {
			if (haltbarkeiten[i] < 1 && haltbarkeiten[i] >= 0.7) {
				try {
					Image j = new Image(new FileInputStream("C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-grun.png"));
					hantelsets[i].setImage(j);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			else if (haltbarkeiten[i] < 0.7 && haltbarkeiten[i] > 0.3) {
				try {
					Image j = new Image(new FileInputStream("C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-gelb.png"));
					hantelsets[i].setImage(j);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			else if (haltbarkeiten[i] <= 0.3) {
				try {
					Image j = new Image(new FileInputStream("C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-rot.png"));
					hantelsets[i].setImage(j);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void initLatzuege() {
		for (int i = 0; i < haltbarkeiten.length; i++) {
			if (haltbarkeiten[i] < 1 && haltbarkeiten[i] >= 0.7) {
				try {
					Image j = new Image(new FileInputStream("C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-grun.png"));
					latzuege[i].setImage(j);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			else if (haltbarkeiten[i] < 0.7 && haltbarkeiten[i] > 0.3) {
				try {
					Image j = new Image(new FileInputStream("C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-gelb.png"));
					latzuege[i].setImage(j);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			else if (haltbarkeiten[i] <= 0.3) {
				try {
					Image j = new Image(new FileInputStream("C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-rot.png"));
					latzuege[i].setImage(j);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void initHantelbaenke() {
		for (int i = 0; i < haltbarkeiten.length; i++) {
			if (haltbarkeiten[i] < 1 && haltbarkeiten[i] >= 0.7) {
				try {
					Image j = new Image(new FileInputStream("C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-grun.png"));
					hantelbaenke[i].setImage(j);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			else if (haltbarkeiten[i] < 0.7 && haltbarkeiten[i] > 0.3) {
				try {
					Image j = new Image(new FileInputStream("C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-gelb.png"));
					hantelbaenke[i].setImage(j);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			else if (haltbarkeiten[i] <= 0.3) {
				try {
					Image j = new Image(new FileInputStream("C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-rot.png"));
					hantelbaenke[i].setImage(j);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
	public void initBeinpressen() {
		for (int i = 0; i < haltbarkeiten.length; i++) {
			if (haltbarkeiten[i] < 1 && haltbarkeiten[i] >= 0.7) {
				try {
					Image j = new Image(new FileInputStream("C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-grun.png"));
					beinpressen[i].setImage(j);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			else if (haltbarkeiten[i] < 0.7 && haltbarkeiten[i] > 0.3) {
				try {
					Image j = new Image(new FileInputStream("C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-gelb.png"));
					beinpressen[i].setImage(j);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
			else if (haltbarkeiten[i] <= 0.3) {
				try {
					Image j = new Image(new FileInputStream("C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-rot.png"));
					beinpressen[i].setImage(j);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}
	}
    
    public void openDetails(MouseEvent event) {
    	String  name = event.getPickResult().getIntersectedNode().getId();
		Alert  details = new Alert(AlertType.INFORMATION);
		details.setTitle("Details");
		details.setHeaderText(name);	
		details.setContentText("Details:\nHaltbarkeit: "+event.getPickResult().getIntersectedNode().getId());
		details.show();
    }
}
