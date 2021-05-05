package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.SimpleDateFormat;
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

	public int[] haltbarkeiten = new int[31];
	public String[] namen = new String[31];
	public String[] id = new String[31];
	public Date[] date = new Date[31];
	public int[] belastungsgrad = new int[31];
	public boolean[] belegt = new boolean[31];
//	public int[] haltbarkeiten;
//	public String[] namen;
//	public String[] id;
//	public Date[] date;
//	public int[] belastungsgrad;
//	public boolean[] belegt;

	public ImageView[] fahrraeder;
	public ImageView[] latzuege;
	public ImageView[] hantelbaenke;
	public ImageView[] beinpressen;
	public ImageView[] hantelsets;

	/**
	 * Hier werden zunächst einmal Image View Gruppen als Arrays gebildet welche
	 * später zur Zuordnung der Haltbarkeiten wichtig werden. Dann wird die CSV
	 * Datei mit den jeweiligen Daten ausgelesen und in Arrays gespeichert.
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		try {
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Connection con = DriverManager.getConnection("jdbc:oracle:thin:@oracle.s-atiw.de:1521:atiwora", "FS192_bbaumann", "ben");
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT x.*,x.ROWID FROM FS192_LTROESCH.TRAININGSGERAET x");
			int i = 0;
			while (rs.next()){
			id[i] = rs.getString(1);
			namen[i] = rs.getString(3);
			belegt[i] = Boolean.parseBoolean(rs.getString(4));
			//Lebensdauer 5
			//Kaufdatum 6
			haltbarkeiten[i] = Integer.parseInt(rs.getString(7));
			belastungsgrad[i] = Integer.parseInt(rs.getString(8));
			//Belegungsplan_ID 9
			try {
					date[i] = rs.getDate(2);
					
				} catch (Exception e) {
					System.out.println("Fehler in der Datenverarbeitung!");
					e.printStackTrace();
				}

			
			i++;
			}
			con.close();
		} catch (SQLException e1) {
			System.out.println("Verbindungsfehler!");
			e1.printStackTrace();
		}
		
		fahrraeder = new ImageView[] { fahrrad_1, fahrrad_2, fahrrad_3, fahrrad_4, fahrrad_5, fahrrad_6, fahrrad_7,
				fahrrad_8 };
		latzuege = new ImageView[] { latzug_1, latzug_2, latzug_3, latzug_4 };
		hantelbaenke = new ImageView[] { hantelbank_1, hantelbank_2, hantelbank_3, hantelbank_4, hantelbank_5,
				hantelbank_6 };
		beinpressen = new ImageView[] { beinpresse_1, beinpresse_2, beinpresse_3, beinpresse_4, beinpresse_5,
				beinpresse_6 };
		hantelsets = new ImageView[] { hantelset_1, hantelset_2, hantelset_3, hantelset_4, hantelset_5, hantelset_6 };
		/*try {
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
		}*/
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
			int l = 0;
			for (String s : namen) {
				if (l <= 29) {
					if (s.contains("Fahrrad")) {
						if (haltbarkeiten[l] < 100 && haltbarkeiten[l] >= 70) {

							Image j = new Image(new FileInputStream(
									"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-grun.png"));
							fahrraeder[k].setImage(j);
						} else if (haltbarkeiten[l] < 70 && haltbarkeiten[l] > 30) {

							Image j = new Image(new FileInputStream(
									"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-gelb.png"));
							fahrraeder[k].setImage(j);

						} else if (haltbarkeiten[l] <= 30) {

							Image j = new Image(new FileInputStream(
									"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Bike-rot.png"));
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

	public void initHantelsets() {
		try {
			int k = 0;
			int l = 0;
			for (String s : namen) {
				if (l <= 29) {
					if (s.contains("Hantelset")) {
						if (haltbarkeiten[l] < 100 && haltbarkeiten[l] >= 70) {

							Image j = new Image(new FileInputStream(
									"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Dumbell-grun.png"));
							hantelsets[k].setImage(j);
						} else if (haltbarkeiten[l] < 70 && haltbarkeiten[l] > 30) {

							Image j = new Image(new FileInputStream(
									"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Dumbell-gelb.png"));
							hantelsets[k].setImage(j);

						} else if (haltbarkeiten[l] <= 30) {

							Image j = new Image(new FileInputStream(
									"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Dumbell-rot.png"));
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

	public void initLatzuege() {
		try {
			int k = 0;
			int l = 0;
			for (String s : namen) {
				if (l <= 29) {
					if (s.contains("Latzug")) {
						if (haltbarkeiten[l] < 100 && haltbarkeiten[l] >= 70) {

							Image j = new Image(new FileInputStream(
									"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Latzug-grun.png"));
							latzuege[k].setImage(j);
						} else if (haltbarkeiten[l] < 70 && haltbarkeiten[l] > 30) {

							Image j = new Image(new FileInputStream(
									"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Latzug-gelb.png"));
							latzuege[k].setImage(j);

						} else if (haltbarkeiten[l] <= 30) {

							Image j = new Image(new FileInputStream(
									"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Latzug-rot.png"));
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

	public void initHantelbaenke() {
		try {
			int k = 0;
			int l = 0;
			for (String s : namen) {
				if (l <= 29) {
					if (s.contains("Hantelbank")) {
						if (haltbarkeiten[l] < 100 && haltbarkeiten[l] >= 70) {

							Image j = new Image(new FileInputStream(
									"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Hantelbank-grun.png"));
							hantelbaenke[k].setImage(j);
						} else if (haltbarkeiten[l] < 70 && haltbarkeiten[l] > 30) {

							Image j = new Image(new FileInputStream(
									"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Hantelbank-gelb.png"));
							hantelbaenke[k].setImage(j);

						} else if (haltbarkeiten[l] <= 30) {

							Image j = new Image(new FileInputStream(
									"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Hantelbank-rot.png"));
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

	public void initBeinpressen() {
		try {
			int k = 0;
			int l = 0;
			for (String s : namen) {
				if (l <= 29) {
					if (s.contains("Beinpresse")) {
						if (haltbarkeiten[l] < 100 && haltbarkeiten[l] >= 70) {

							Image j = new Image(new FileInputStream(
									"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Legpress-grun.png"));
							beinpressen[k].setImage(j);
						} else if (haltbarkeiten[l] < 70 && haltbarkeiten[l] > 30) {

							Image j = new Image(new FileInputStream(
									"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Legpress-gelb.png"));
							beinpressen[k].setImage(j);

						} else if (haltbarkeiten[l] <= 30) {

							Image j = new Image(new FileInputStream(
									"C:\\Users\\deben\\git\\Geraetenutzung\\Gerätenutzung\\src\\images\\Legpress-rot.png"));
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
	 * Hier werden die Daten der Geräte ausgegeben
	 * 
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
