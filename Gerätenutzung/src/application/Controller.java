package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.sql.*;
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

	//Arrays für die Daten der Datenbank
	public int[] haltbarkeiten = new int[31];
	public String[] namen = new String[31];
	public String[] id = new String[31];
	public Date[] date = new Date[31];
	public int[] belastungsgrad = new int[31];
	public boolean[] belegt = new boolean[31];
	public int[] lebensdauer = new int[31];
	public Date[] kaufDatum = new Date[31];
	public String[] belegungsplan_ID = new String[31];

	//Arrays für die @FXML Image Views 
	public ImageView[] fahrraeder;
	public ImageView[] latzuege;
	public ImageView[] hantelbaenke;
	public ImageView[] beinpressen;
	public ImageView[] hantelsets;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		//Das sind die Geräte (als ImageViews) die im Grundriss angezeigt werden. Noch Hardgecoded!
		fahrraeder = new ImageView[] { fahrrad_1, fahrrad_2, fahrrad_3, fahrrad_4, fahrrad_5, fahrrad_6, fahrrad_7,fahrrad_8 };
		latzuege = new ImageView[] { latzug_1, latzug_2, latzug_3, latzug_4 };
		hantelbaenke = new ImageView[] { hantelbank_1, hantelbank_2, hantelbank_3, hantelbank_4, hantelbank_5,hantelbank_6 };
		beinpressen = new ImageView[] { beinpresse_1, beinpresse_2, beinpresse_3, beinpresse_4, beinpresse_5,beinpresse_6 };
		hantelsets = new ImageView[] { hantelset_1, hantelset_2, hantelset_3, hantelset_4, hantelset_5, hantelset_6 };
		readDB();
		giveID();
		initAll();
	}
	
	/**
	 * Hier werden die Daten aus der Datenbank ausgelesen und in jeweils passende Arrays gespeichert.
	 */
	public void readDB() {
		try {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			}
			catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
			Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@oracle.s-atiw.de:1521:atiwora", "FS192_bbaumann", "ben");
			Statement stmt = connection.createStatement();
			ResultSet results = stmt.executeQuery("SELECT x.*,x.ROWID FROM FS192_LTROESCH.TRAININGSGERAET x");
			
			int i = 0;
			while (results.next()){
				id[i] = results.getString(1);
				namen[i] = results.getString(3);
				belegt[i] = Boolean.parseBoolean(results.getString(4));
				lebensdauer[i] = Integer.parseInt(results.getString(5));
				haltbarkeiten[i] = Integer.parseInt(results.getString(7));
				belastungsgrad[i] = Integer.parseInt(results.getString(8));
				belegungsplan_ID[i]= results.getString(9);
				try {
					kaufDatum[i] = results.getDate(6);
					date[i] = results.getDate(2);
					}
				catch (Exception e) {
					e.printStackTrace();
				}
			i++;
			}
			connection.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	/**
	 * Hier werden die IDs aus der Datenbank den IDs von JavaFX zugewiesen
	 */
	public void giveID() {
		int n = 0;
		int o = 0;
		int p = 0;
		int q = 0;
		int r = 0;
		int t = 0;
		
		for (String s : namen) {
			if (t <= 29) {
				if (s.contains("Beinpresse")) {
					beinpressen[n].setId(id[t]);
					n++;
				}
				else if (s.contains("Hantelbank")) {
					hantelbaenke[o].setId(id[t]);
					o++;
				}
				else if (s.contains("Latzug")) {
					latzuege[p].setId(id[t]);
					p++;
				}
				else if (s.contains("Hanteln")) {
					hantelsets[q].setId(id[t]);
					q++;
				}
				else if (s.contains("Fahrrad")) {
					fahrraeder[r].setId(id[t]);
					r++;
				}
			}
			t++;
		}
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
	
	/**
	 * Hier wird die Haltbarkeit von den jeweiligen Fahrrädern ausgelesen und das entsprechende Image (Rot/Gelb/Grün) zugewiesen.
	 */
	public void initFahrraeder() {
		try {
			int k = 0;
			int l = 0;
			for (String s : namen) {
				if (l <= 29) {
					if (s.contains("Fahrrad")) {
						if (haltbarkeiten[l] < 100 && haltbarkeiten[l] >= 70) {

							Image j = new Image(new FileInputStream(
									"src\\images\\Bike-grun.png"));
							fahrraeder[k].setImage(j);
						} else if (haltbarkeiten[l] < 70 && haltbarkeiten[l] > 30) {

							Image j = new Image(new FileInputStream(
									"src\\images\\Bike-gelb.png"));
							fahrraeder[k].setImage(j);

						} else if (haltbarkeiten[l] <= 30) {

							Image j = new Image(new FileInputStream(
									"src\\images\\Bike-rot.png"));
							fahrraeder[k].setImage(j);
						}
						k++;
					}
				}
				l++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Wie initFahrrader()
	 */
	public void initHantelsets() {
		try {
			int k = 0;
			int l = 0;
			for (String s : namen) {
				if (l <= 29) {
					if (s.contains("Hanteln")) {
						if (haltbarkeiten[l] < 100 && haltbarkeiten[l] >= 70) {

							Image j = new Image(new FileInputStream(
									"src\\images\\Dumbell-grun.png"));
							hantelsets[k].setImage(j);
						} else if (haltbarkeiten[l] < 70 && haltbarkeiten[l] > 30) {

							Image j = new Image(new FileInputStream(
									"src\\images\\Dumbell-gelb.png"));
							hantelsets[k].setImage(j);

						} else if (haltbarkeiten[l] <= 30) {

							Image j = new Image(new FileInputStream(
									"src\\images\\Dumbell-rot.png"));
							hantelsets[k].setImage(j);
						}
						k++;
					}
				}
				l++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Wie initFahrrader()
	 */
	public void initLatzuege() {
		try {
			int k = 0;
			int l = 0;
			for (String s : namen) {
				if (l <= 29) {
					if (s.contains("Latzug")) {
						if (haltbarkeiten[l] < 100 && haltbarkeiten[l] >= 70) {

							Image j = new Image(new FileInputStream(
									"src\\images\\Latzug-grun.png"));
							latzuege[k].setImage(j);
						} else if (haltbarkeiten[l] < 70 && haltbarkeiten[l] > 30) {

							Image j = new Image(new FileInputStream(
									"src\\images\\Latzug-gelb.png"));
							latzuege[k].setImage(j);

						} else if (haltbarkeiten[l] <= 30) {

							Image j = new Image(new FileInputStream(
									"src\\images\\Latzug-rot.png"));
							latzuege[k].setImage(j);
						}
						k++;
					}
				}
				l++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Wie initFahrrader()
	 */
	public void initHantelbaenke() {
		try {
			int k = 0;
			int l = 0;
			for (String s : namen) {
				if (l <= 29) {
					if (s.contains("Hantelbank")) {
						if (haltbarkeiten[l] < 100 && haltbarkeiten[l] >= 70) {

							Image j = new Image(new FileInputStream(
									"src\\images\\Hantelbank-grun.png"));
							hantelbaenke[k].setImage(j);
						} else if (haltbarkeiten[l] < 70 && haltbarkeiten[l] > 30) {

							Image j = new Image(new FileInputStream(
									"src\\images\\Hantelbank-gelb.png"));
							hantelbaenke[k].setImage(j);

						} else if (haltbarkeiten[l] <= 30) {

							Image j = new Image(new FileInputStream(
									"src\\images\\Hantelbank-rot.png"));
							hantelbaenke[k].setImage(j);
						}
						k++;
					}
				}
				l++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Wie initFahrrader()
	 */
	public void initBeinpressen() {
		try {
			int k = 0;
			int l = 0;
			for (String s : namen) {
				if (l <= 29) {
					if (s.contains("Beinpresse")) {
						if (haltbarkeiten[l] < 100 && haltbarkeiten[l] >= 70) {
							
							Image j = new Image(new FileInputStream(
									"src\\images\\Legpress-grun.png"));
							beinpressen[k].setImage(j);
						} else if (haltbarkeiten[l] < 70 && haltbarkeiten[l] > 30) {

							Image j = new Image(new FileInputStream(
									"src\\images\\Legpress-gelb.png"));
							beinpressen[k].setImage(j);

						} else if (haltbarkeiten[l] <= 30) {

							Image j = new Image(new FileInputStream(
									"src\\images\\Legpress-rot.png"));
							beinpressen[k].setImage(j);
						}
						k++;
					}
				}
				l++;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Hier werden alle in der Datenbank gespeicherte Daten des angeklickten Geräts ausgegeben.
	 * 
	 * @param event MouseClick auf das jeweilige Gerät / ImageView
	 */
	public void openDetails(MouseEvent event) {
		String nodeID = event.getPickResult().getIntersectedNode().getId();
		Alert details = new Alert(AlertType.INFORMATION);
		details.setTitle("Details");
		int stelle;
		int t = 0;
		for (String s : id) {
			if (t <= 29) {
				if(s.equals(nodeID)) {
					stelle= t;
					details.setHeaderText("Gerätename: " + namen[stelle] + "\nID: " + id[stelle]);
					details.setContentText("Aktuelles Datum: " + date[stelle] + "\nHaltbarkeit: " + haltbarkeiten[stelle]+" %"
							+ "\nBelastungsgrad: " + belastungsgrad[stelle] + "\nBelegt? " + belegt[stelle]
							+ "\nLebensdauer: " + lebensdauer[stelle]+" Jahr/e" + "\nKaufdatum: " + kaufDatum[stelle] + "\nBelegungsplan ID: "
							+ belegungsplan_ID[stelle]);
					details.show();
				}
			}
			t++;
		}
		
	}
}
