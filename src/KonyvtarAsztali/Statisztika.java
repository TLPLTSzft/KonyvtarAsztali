package KonyvtarAsztali;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static java.lang.Integer.getInteger;
import static java.lang.Integer.parseInt;

public class Statisztika {
	
	private final ArrayList<Konyv> konyvek;
	
	public Statisztika() {
		konyvek = new ArrayList<>();
		beolvasasAdatbazisbol();
	}
	
	public void beolvasasAdatbazisbol() {
		String className = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/";
		String user = "root";
		String pass = "";
		String db = "library";
		String tbl = "books";
		try {
			Class.forName(className);
			Connection connection = DriverManager.getConnection(url + db, user, pass);
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from " + tbl);
			while (resultSet.next()) {
				int id = parseInt(resultSet.getString(1));
				String title = resultSet.getString(2);
				String author = resultSet.getString(3);
				int publish_year = parseInt(resultSet.getString(4));
				int page_count = parseInt(resultSet.getString(5));
				konyvek.add(new Konyv(id, title, author, publish_year, page_count));
			}
			connection.close();
		} catch (Exception e) {
			System.err.println("\nHIBA:\n" + e);
		}
	}
	
	public int hosszabb500Oldalnal() {
		int hosszabb500Oldalnal = 0;
		for (Konyv konyv : konyvek) {
			if (konyv.getPage_count() > 500) {
				hosszabb500Oldalnal++;
			}
		}
		return hosszabb500Oldalnal;
	}
	
	public String van1950nelRegebbi() {
		for (Konyv konyv : konyvek) {
			if (konyv.getPublish_year() < 1950) {
				return "Van";
			}
		}
		return "Nincs";
	}
	
	public String leghosszabbKonyv() {
		String string = "";
		int index = 0;
		int oldalSzam = 0;
		for (Konyv konyv : konyvek) {
			if (konyv.getPage_count() > oldalSzam) {
				index = konyv.getId() - 1;
				oldalSzam = konyv.getPage_count();
			}
		}
		return String.format("%s", konyvek.get(index));
	}
	
	public String legtobbKonyvSzerzoje() {
		int legtobbKonyv = 0;
		int dbKonyvSzerzonkent = 0;
		String szerzo = "";
		for (Konyv konyv : konyvek) {
			dbKonyvSzerzonkent = 0;
			szerzo = konyv.getAuthor();
			for (Konyv konyv1 : konyvek) {
				if (konyv1.getAuthor().equals(szerzo)) {
					dbKonyvSzerzonkent++;
				}
			}
			if (dbKonyvSzerzonkent > legtobbKonyv) {
				legtobbKonyv = dbKonyvSzerzonkent;
			}
		}
		return szerzo;
	}
	
	public String konyvekEgySorban() {
		String string = "";
		for (Konyv konyv : konyvek) {
			string += String.format("\n%s", konyv.konyvEgySorban());
		}
		return System.out.printf("%s", string).toString();
	}
	
	public String adottKonyvSzerzoje(String scanner) {
		String string = "Nincs ilyen könyv";
		for (Konyv konyv : konyvek) {
			if (konyv.getTitle().equals(scanner)) {
				return "A megadott könyv szerzője: " + konyv.getAuthor();
			}
		}
		return string;
	}
	
	@Override
	public String toString() {
		String string = "";
		for (Konyv konyv : konyvek) {
			string += String.format("\n%s", konyv.toString());
		}
		return System.out.printf("%s", string).toString();
	}
	
}
