package KonyvtarAsztali;

import java.util.Scanner;

class MysqlCon {
	public static void main(String[] args) {
		Statisztika statisztika = new Statisztika();
//		statisztika.konyvekEgySorban();
//		statisztika.toString();
		System.out.printf("\n500 oldalnál hosszabb könyvek száma: %2d", statisztika.hosszabb500Oldalnal());
		System.out.printf("\n%s 1950-nél régebbi könyv", statisztika.van1950nelRegebbi());
		System.out.printf("\nA leghosszabb könyv: %s", statisztika.leghosszabbKonyv());
		System.out.printf("\nA legtöbb könyvvel rendelkező szerző: %s", statisztika.legtobbKonyvSzerzoje());
		System.out.printf("\nAdjon meg egy könyv címet: ");
		Scanner scanner = new Scanner(System.in);
		String string = statisztika.adottKonyvSzerzoje(scanner.nextLine());
		System.out.printf("%s", string);
		
		System.out.println();
	}
}
