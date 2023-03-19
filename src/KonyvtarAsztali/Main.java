package KonyvtarAsztali;

class MysqlCon {
	public static void main(String[] args) {
		Statisztika statisztika = new Statisztika();
		statisztika.toString();
		System.out.printf("\n500 oldalnál hosszabb könyvek száma: %2d", statisztika.hosszabb500Oldalnal());
		System.out.println();
	}
}
