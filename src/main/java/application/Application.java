package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.io.FileUtils;

import application.entities.Book;
import application.entities.Library;
import application.entities.Magazine;
import application.entities.Periodicity;

public class Application {
	static File file = new File("testo.txt");

	public static void main(String[] args) {
		Library book1 = new Book("Libro1", 2010, 100, "MeMedesimo", "Crime");
		Library book2 = new Book("Libro2", 2010, 229, "MeMedesimo", "Horror");
		Library book3 = new Book("Libro3", 2012, 122, "TuTuesimo", "Comedy");
		Library magazine1 = new Magazine("Magazine1", 2020, 133, Periodicity.MENSILE);
		Library magazine2 = new Magazine("Magazine2", 2023, 500, Periodicity.SETTIMANALE);
		Library magazine3 = new Magazine("Magazine3", 2010, 324, Periodicity.SETTIMANALE);

		List<Library> mediaList = new ArrayList<>();
		addElem(mediaList, book1);
		addElem(mediaList, book2);
		addElem(mediaList, book3);
		addElem(mediaList, magazine1);
		addElem(mediaList, magazine2);
		addElem(mediaList, magazine3);

		System.out.println("**********  1 **********");
		System.out.println("Lista libri/magazine: " + mediaList);

		removElem(mediaList, book1.getISBN());

		System.out.println("**********  2 **********");
		System.out.println("Lista libri/magazine dopo il remove: " + mediaList);

		System.out.println("**********  3 **********");
		Library foundTroughISBN = findTroughISBN(mediaList, magazine1.getISBN());
		if (foundTroughISBN != null) {
			System.out.println("Find ISBN: " + foundTroughISBN);
		} else {
			System.out.println("File non trovato!");
		}

		System.out.println("**********  4 **********");
		List<Library> foundTroughYear = findTroughYear(mediaList, 2010);
		if (foundTroughYear.size() > 0) {
			System.out.println("Find Year: " + foundTroughYear);
		} else {
			System.out.println("File non trovato!");
		}

		System.out.println("**********  5 **********");
		List<Book> authorFound = findAuthor(mediaList, "MeMedesimo");
		if (authorFound.size() > 0) {
			System.out.println("Find Author: " + authorFound);
		} else {
			System.out.println("File non trovato!");
		}

//		System.out.println("**********  6 **********");
//		try {
//			readFromPC(mediaList);
//		} catch (IOException e) {
//			System.out.println("File non trovato!");
//		}
	}

	public static void addElem(List<Library> list, Library item) {
		list.add(item);
		try {
			saveToPC(item);
		} catch (IOException e) {
			System.out.println("Elemento non trovato!");
		}
	}

	public static void removElem(List<Library> list, UUID id) {
		Iterator<Library> i = list.iterator();
		while (i.hasNext()) {
			Library curr = i.next();
			curr.getISBN().equals(id);
		}
	}

	public static Library findTroughISBN(List<Library> list, UUID id) {
		Library m = list.stream().filter(media -> media.getISBN().equals(id)).findAny().orElse(null);
		return m;
	}

	public static List<Library> findTroughYear(List<Library> list, int pubYear) {
		List<Library> p = list.stream().filter(media -> media.getPubblicationYear() == pubYear).toList();
		return p;
	}

	public static List<Book> findAuthor(List<Library> list, String author) {
		List<Book> l = list.stream().filter(m -> m instanceof Book && ((Book) m).getAuthor().equals(author))
				.map(m -> (Book) m).toList();
		return l;
	}

	public static void saveToPC(Library c) throws IOException {
		if (c instanceof Book) {
			String written = c.getISBN() + "@" + c.getTitle() + "@" + c.getPubblicationYear() + "@" + c.getPagesNumber()
					+ "@" + ((Book) c).getAuthor() + "@" + ((Book) c).getGenre() + "#";
			FileUtils.writeStringToFile(file, written, "UTF-8", true);
		} else {
			String writtenMagazine = c.getISBN() + "@" + c.getTitle() + "@" + c.getPubblicationYear() + "@"
					+ c.getPagesNumber() + "@" + ((Magazine) c).getPeriodicity() + "#";
			FileUtils.writeStringToFile(file, writtenMagazine, "UTF-8", true);
		}
	}

//	public static void readFromPC(List<Library> arrList) throws IOException {
//		arrList.clear();
//		if (file.exists()) {
//			String content = FileUtils.readFileToString(file, "UTF-8");
//			String[] separatedItems = content.split("#");
//			for (String string : separatedItems) {
//				String[] separatedList = string.split("@");
//				for (int i = 0; i < separatedList.length; i++) {
//					if (separatedList.length > 5) {
//						String title = separatedList[1];
//						int pubblicationYear = Integer.parseInt(separatedList[2]);
//						int pagesNumber = Integer.parseInt(separatedList[3]);
//						String author = separatedList[4];
//						String genre = separatedList[5];
//						Library b = new Book(title, pubblicationYear, pagesNumber, author, genre);
//						arrList.add(b);
//					} else {
//						String title = separatedList[1];
//						int pubblicationYear = Integer.parseInt(separatedList[2]);
//						int pagesNumber = Integer.parseInt(separatedList[3]);
//						Periodicity periodicity = Periodicity.valueOf(separatedList[4]);
//						Library m = new Magazine(title, pubblicationYear, pagesNumber, periodicity);
//						arrList.add(m);
//					}
//				}
//			}
//		} else {
//			System.out.println("Nessun file presente");
//		}
//		System.out.println("Sono un elenco di elementi letti da file e scritti in un ArrayList : " + arrList);
//	}
}
