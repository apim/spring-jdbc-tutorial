package apim.github.tutorial;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BookRowMapper implements RowMapper<Book> {

	@Override
	public Book mapRow(ResultSet rs, int rowId) throws SQLException {
		Book book = new Book();
		book.setIsbn(rs.getInt(1));
		book.setTitle(rs.getString(2));
		book.setAuthor(rs.getString(3));
		return book;
	}

}
