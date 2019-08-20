package apim.github.tutorial;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestCode {

	public static void main(String args[]) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("/spring-context.xml");
		BookDAO dao = (BookDAO) ctx.getBean("bookDAO");
		dao.createBookTable();
		dao.addBook(101, "The Target", "David Baldacci");
		dao.addBook(202, "Hunger Games", "Suzanne Collins");
		System.out.println(dao.getBookTitleByIsbn(101));
		Book book = dao.getBookByAuthor("Suzanne Collins");
		System.out.println(book.getIsbn() + ", " + book.getTitle() + ", " + book.getAuthor());
		List<Book> books = dao.getAllBooks();
		for (Book bk : books) {
			System.out.println(bk.getIsbn() + ", " + bk.getTitle() + ", " + bk.getAuthor());
		}
		ctx.close();
	}

}