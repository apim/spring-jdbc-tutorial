package apim.github.tutorial;

import java.util.List;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookDAO {

	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	public DataSource getDataSource() {
		return dataSource;
	}

	@Resource
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void createBookTable() {
		String query = "create table book (isbn integer not null primary key, title varchar(45) not null, author varchar(45) not null)";
		jdbcTemplate.execute(query);
	}

	public void addBook(int isbn, String title, String author) {
		String query = "insert into book values(?,?,?)";
		jdbcTemplate.update(query, isbn, title, author);
	}

	public String getBookTitleByIsbn(int isbn) {
		String query = "select title from book where isbn=?";
		return jdbcTemplate.queryForObject(query, String.class, isbn);
	}

	public Book getBookByAuthor(String author) {
		String query = "select * from book where author=?";
		return jdbcTemplate.queryForObject(query, new Object[] { author }, new BookRowMapper());
	}

	public List<Book> getAllBooks() {
		String query = "select * from book";
		return jdbcTemplate.query(query, new BookRowMapper());
	}

}
