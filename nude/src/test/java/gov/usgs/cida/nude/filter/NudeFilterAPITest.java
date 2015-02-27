package gov.usgs.cida.nude.filter;

import gov.usgs.cida.nude.column.Column;
import gov.usgs.cida.nude.column.SimpleColumn;
import gov.usgs.cida.nude.filter.api.Concatenate;
import gov.usgs.cida.nude.filter.api.Drop;
import gov.usgs.cida.nude.filter.api.FormatDate;
import gov.usgs.cida.nude.filter.api.Regex;
import gov.usgs.cida.nude.filter.api.Reorder;
import gov.usgs.cida.nude.resultset.ResultSetUtils;
import gov.usgs.cida.nude.resultset.inmemory.IteratorWrappingResultSet;
import gov.usgs.cida.nude.resultset.inmemory.TableRow;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;
import org.joda.time.format.ISODateTimeFormat;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

/**
 *
 * @author dmsibley
 */
public class NudeFilterAPITest {

	public NudeFilterAPITest() {
	}

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}
	
	@Test
	public void MuxSourceTransformTest() throws SQLException {
		ResultSet expected = rs(muxSourceExpected);
		ResultSet actual = Nude.source(rs(muxSourceResultSet1))
				.source(rs(muxSourceResultSet2))
				.filter();
		assertTrue(ResultSetUtils.checkEqualRows(expected, actual));
	}

	@Test
	public void ReorderColumnTransformTest() throws SQLException {
		ResultSet expected = rs(dummy);
		ResultSet actual = Nude.source(rs(dummy))
				.transform(Reorder.column(key).after(value))
				.filter();
		assertTrue(ResultSetUtils.checkEqualRows(expected, actual));
		
		expected = rs(dummy);
		actual = Nude.source(rs(dummy))
				.transform(Reorder.column(value).before(key))
				.filter();
		assertTrue(ResultSetUtils.checkEqualRows(expected, actual));
		
		expected = rs(dummy);
		actual = Nude.source(rs(dummy))
				.transform(Reorder.column(value).before(key))
				.transform(Reorder.column(key).before(value))
				.filter();
		assertTrue(ResultSetUtils.checkEqualRows(expected, actual));
		
		expected = rs(dummy);
		actual = Nude.source(rs(dummy))
				.transform(Reorder.column(key).before(key))
				.filter();
		assertTrue(ResultSetUtils.checkEqualRows(expected, actual));
	}

	@Ignore
	@Test
	public void RenameColumnTransformTest() throws SQLException {
		ResultSet expected = rs(dummy);
//		NudeSource actual = Nude
//				
//				.source(rs(dummy))
//				.transform(Rename.column(key).as(newKey))
//				.transform(Rename.column(value).as(new SimpleColumn("thisISMYVALUE")))
//				
//				.source()
//				.mux()
//				.source()
//				.finish();
//		assertTrue(ResultSetUtils.checkEqualRows(expected, actual));
	}

	@Test
	public void DropColumnTransformTest() throws SQLException {
		ResultSet expected = rs(dummy);
		ResultSet actual = Nude.source(rs(dummy))
				.transform(Drop.column(value))
				.filter();
		assertTrue(ResultSetUtils.checkEqualRows(expected, actual));
	}

	@Test
	public void FormatTransformTest() throws SQLException {
		ResultSet expected = rs(dummy);
		ResultSet actual = Nude.source(rs(dummy))
				.transform(FormatDate.column(key).as(ISODateTimeFormat.dateTimeNoMillis()))
				.filter();
		assertTrue(ResultSetUtils.checkEqualRows(expected, actual));
	}

	@Test
	public void ConcatenationTransformTest() throws SQLException {
		ResultSet expected = rs(dummy);
		ResultSet actual = Nude.source(rs(dummy))
				.transform(Concatenate.column(key).append("|"))
				.transform(Concatenate.column(key).append(newKey))
				.filter();
		assertTrue(ResultSetUtils.checkEqualRows(expected, actual));
	}

	@Test
	public void RegexTransformTest() throws SQLException {
		ResultSet expected = rs(dummy);
		ResultSet actual = Nude.source(rs(dummy))
				.transform(Regex.column(key).pattern(Pattern.compile("(\\d+)")).getGroup(1))
				.filter();
		assertTrue(ResultSetUtils.checkEqualRows(expected, actual));
	}
	
	public static ResultSet rs(Iterable<TableRow> iterable) {
		return new IteratorWrappingResultSet(iterable.iterator());
	}
	
	public static String keyName = "key";
	public static Column key = new SimpleColumn(keyName);
	public static String newKeyName = "newkey";
	public static Column newKey = new SimpleColumn(newKeyName);
	public static String valueName = "value";
	public static Column value = new SimpleColumn(valueName);
	public static Iterable<TableRow> muxSourceExpected = ResultSetUtils.createTableRows(
			new String[]{keyName, valueName},
			new String[][]{
				new String[]{"1", "a"},
				new String[]{"2", "b"},
				new String[]{"3", "c"}
			});
	public static Iterable<TableRow> muxSourceResultSet1 = ResultSetUtils.createTableRows(
			new String[]{keyName, valueName},
			new String[][]{
				new String[]{"1", "a"},
				new String[]{"3", "c"}
			});
	public static Iterable<TableRow> muxSourceResultSet2 = ResultSetUtils.createTableRows(
			new String[]{keyName, valueName},
			new String[][]{
				new String[]{"2", "b"}
			});
	
	public static Iterable<TableRow> dummy = ResultSetUtils.createTableRows(
			new String[]{keyName, valueName},
			new String[][]{
				new String[]{"1", "a"},
				new String[]{"2", "b"},
				new String[]{"3", "c"}
			});
}
