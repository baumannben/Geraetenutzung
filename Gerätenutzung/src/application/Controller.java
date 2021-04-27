package application;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class Controller implements Initializable {
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

	public int[] haltbarkeiten = new int[31];
	public String[] namen = new String[31];
	public int[] id = new int[31];
	public Date[] date = new Date[31];
	public int[] belastungsgrad = new int[31];
	public boolean[] belegt = new boolean[31];

	public ImageView[] fahrraeder;
	public ImageView[] latzuege;
	public ImageView[] hantelbaenke;
	public ImageView[] beinpressen;
	public ImageView[] hantelsets;
	/**
	 * Hier werden zunächst einmal Image View Gruppen als Arrays
	 * gebildet welche später zur Zuordnung der Haltbarkeiten wichtig werden.
	 * Dann wird die CSV Datei mit den jeweiligen Daten ausgelesen und in
	 * Arrays gespeichert.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fahrraeder = new ImageView[] { fahrrad_1, fahrrad_2, fahrrad_3, fahrrad_4, fahrrad_5, fahrrad_6, fahrrad_7,
				fahrrad_8 };
		latzuege = new ImageView[] { latzug_1, latzug_2, latzug_3, latzug_4 };
		hantelbaenke = new ImageView[] { hantelbank_1, hantelbank_2, hantelbank_3, hantelbank_4, hantelbank_5,
				hantelbank_6 };
		beinpressen = new ImageView[] { beinpresse_1, beinpresse_2, beinpresse_3, beinpresse_4, beinpresse_5,
				beinpresse_6 };
		hantelsets = new ImageView[] { hantelset_1, hantelset_2, hantelset_3, hantelset_4, hantelset_5, hantelset_6 };
		try {
			BufferedReader br = new BufferedReader(new FileReader(
					"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\application\\Geratenutzung.csv"));
			String line = "";
			String splitBy = ";";
			int i = 0;
			SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
			while ((line = br.readLine()) != null) {
				String[] geraete = line.split(splitBy);
				System.out.println("ID: " + geraete[0] + " Name: " + geraete[1] + " Datum/Uhrzeit: " + geraete[2]
						+ " Alter: " + geraete[3] + " Haltbarkeit: " + geraete[4] + " Belastungsgrad: " + geraete[5]
						+ " Momentan belegt?: " + geraete[6]);
				if (i >= 1) {
					haltbarkeiten[i] = Integer.parseInt(geraete[4]);
					namen[i] = geraete[1];
					id[i] = Integer.parseInt(geraete[0]);
					belastungsgrad[i] = Integer.parseInt(geraete[5]);
					belegt[i] = Boolean.parseBoolean(geraete[6]);
					try {
						date[i] = formatter.parse(geraete[2]);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
				i++;
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		initAll();
	}
	/**
	 * Initialisiert alle Image-Views mit den zu ihrer Haltbarkeit passenden Images
	 */
	public void initAll() {
		initBeinpressen();
		initFahrraeder();
		initHantelbaenke();
		initHantelsets();
		initLatzuege();
	}
	public void initFahrraeder() {
		try {
			int k = 0;
			for (int i = 1; i < 8; i++) {
				if (haltbarkeiten[i] < 100 && haltbarkeiten[i] >= 70) {

					Image j = new Image(new FileInputStream(
							"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-grun.png"));
					fahrraeder[k].setImage(j);
				} else if (haltbarkeiten[i] < 70 && haltbarkeiten[i] > 30) {

					Image j = new Image(new FileInputStream(
							"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-gelb.png"));
					fahrraeder[k].setImage(j);

				} else if (haltbarkeiten[i] <= 30) {

					Image j = new Image(new FileInputStream(
							"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-rot.png"));
					fahrraeder[k].setImage(j);
				}
				k++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void initHantelsets() {
		try {
			int k = 0;
			for (int i = 19; i < 25; i++) {
				if (haltbarkeiten[i] < 100 && haltbarkeiten[i] >= 70) {

					Image j = new Image(new FileInputStream(
							"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Dumbell-grun.png"));
					hantelsets[k].setImage(j);
				} else if (haltbarkeiten[i] < 70 && haltbarkeiten[i] > 30) {

					Image j = new Image(new FileInputStream(
							"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Dumbell-gelb.png"));
					hantelsets[k].setImage(j);

				} else if (haltbarkeiten[i] <= 30) {

					Image j = new Image(new FileInputStream(
							"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Dumbell-rot.png"));
					hantelsets[k].setImage(j);
				}
				k++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void initLatzuege() {
		try {
			int k = 0;
			for (int i = 15; i < 19; i++) {
				if (haltbarkeiten[i] < 100 && haltbarkeiten[i] >= 70) {

					Image j = new Image(new FileInputStream(
							"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Latzug-grun.png"));
					latzuege[k].setImage(j);
				} else if (haltbarkeiten[i] < 70 && haltbarkeiten[i] > 30) {

					Image j = new Image(new FileInputStream(
							"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Latzug-gelb.png"));
					latzuege[k].setImage(j);

				} else if (haltbarkeiten[i] <= 30) {

					Image j = new Image(new FileInputStream(
							"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Latzug-rot.png"));
					latzuege[k].setImage(j);
				}
				k++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void initHantelbaenke() {
		try {
			int k = 0;
			for (int i = 9; i < 15; i++) {
				if (haltbarkeiten[i] < 100 && haltbarkeiten[i] >= 70) {

					Image j = new Image(new FileInputStream(
							"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Hantelbank-grun.png"));
					hantelbaenke[k].setImage(j);
				} else if (haltbarkeiten[i] < 70 && haltbarkeiten[i] > 30) {

					Image j = new Image(new FileInputStream(
							"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Hantelbank-gelb.png"));
					hantelbaenke[k].setImage(j);

				} else if (haltbarkeiten[i] <= 30) {

					Image j = new Image(new FileInputStream(
							"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Hantelbank-rot.png"));
					hantelbaenke[k].setImage(j);
				}
				k++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void initBeinpressen() {
		try {
			int k = 0;
			for (int i = 25; i < 31; i++) {
				if (haltbarkeiten[i] < 100 && haltbarkeiten[i] >= 70) {

					Image j = new Image(new FileInputStream(
							"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Legpress-grun.png"));
					beinpressen[k].setImage(j);
				} else if (haltbarkeiten[i] < 70 && haltbarkeiten[i] > 30) {

					Image j = new Image(new FileInputStream(
							"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Legpress-gelb.png"));
					beinpressen[k].setImage(j);

				} else if (haltbarkeiten[i] <= 30) {

					Image j = new Image(new FileInputStream(
							"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Legpress-rot.png"));
					beinpressen[k].setImage(j);
				}
				k++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Hier werden die Daten der Geräte ausgegeben
	 * @param event MouseClick auf das jeweilige Gerät
	 */
	public void openDetails(MouseEvent event) {
		int nodeID = Integer.parseInt(event.getPickResult().getIntersectedNode().getId());
		Alert details = new Alert(AlertType.INFORMATION);
		details.setTitle("Details");
		details.setHeaderText("Gerätename: " + namen[nodeID] + "\nID: " + id[nodeID]);
		details.setContentText("Date: " + date[nodeID] + "\nHaltbarkeit: " + haltbarkeiten[nodeID]
				+ "\nBelastungsgrad: " + belastungsgrad[nodeID] + "\nBelegt? " + belegt[nodeID]);
		details.show();
	}
}
