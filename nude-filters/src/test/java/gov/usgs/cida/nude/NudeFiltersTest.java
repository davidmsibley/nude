package gov.usgs.cida.nude;

import com.google.common.collect.ImmutableList;
import gov.usgs.cida.nude.column.Column;
import gov.usgs.cida.nude.column.NudeRow;
import gov.usgs.cida.nude.column.impl.SimpleColumn;
import gov.usgs.cida.nude.column.impl.ColumnGroupImpl;
import gov.usgs.cida.nude.filter.column.Drop;
import gov.usgs.cida.nude.filter.column.FormatDate;
import gov.usgs.cida.nude.filter.column.Move;
import java.sql.SQLException;
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
public class NudeFiltersTest {

	public NudeFiltersTest() {
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
		NudeSource expected = Nude.source(muxSourceExpected);
		
		NudeSource mux1 = Nude.source(muxSourceResultSet1);
		NudeSource mux2 = Nude.source(muxSourceResultSet2);
		NudeSource actual = mux1.mux(mux2);
		
		assertTrue(Nude.equals(expected.sink(), actual.sink()));
	}

	@Test
	public void ReorderColumnTransformTest() throws SQLException {
		NudeSource expected = Nude.source(dummy);
		NudeSource actual = Nude.source(dummy)
				.transform(Move.column(key).after(value));
		
		assertTrue(Nude.equals(expected.sink(), actual.sink()));
		
		expected = Nude.source(dummy);
		actual = Nude.source(dummy)
				.transform(Move.column(value).before(key));
		
		assertTrue(Nude.equals(expected.sink(), actual.sink()));
		
		expected = Nude.source(dummy);
		actual = Nude.source(dummy)
				.transform(Move.column(value).before(key))
				.transform(Move.column(key).before(value));
		
		assertTrue(Nude.equals(expected.sink(), actual.sink()));
		
		expected = Nude.source(dummy);
		actual = Nude.source(dummy)
				.transform(Move.column(key).before(key));
		
		assertTrue(Nude.equals(expected.sink(), actual.sink()));
	}

	@Ignore
	@Test
	public void RenameColumnTransformTest() throws SQLException {
		NudeSource expected = Nude.source(dummy);
		NudeSource actual = Nude.source(dummy)
				.transform(Move.column(key).as(newKey))
				.transform(Move.column(value).as(new SimpleColumn("thisISMYVALUE")));
		assertTrue(Nude.equals(expected.sink(), actual.sink()));
	}

	@Test
	public void DropColumnTransformTest() throws SQLException {
		NudeSource expected = Nude.source(dummy);
		NudeSource actual = Nude.source(dummy)
				.transform(Drop.column(value));
		assertTrue(Nude.equals(expected.sink(), actual.sink()));
	}

//	@Test
//	public void FormatTransformTest() throws SQLException {
//		NudeSource expected = Nude.source(dummy);
//		NudeSource actual = Nude.source(dummy)
//				.transform(FormatDate.column(key).as(ISODateTimeFormat.dateTimeNoMillis()))
//				.filter();
//		assertTrue(Nude.equals(expected.sink(), actual.sink()));
//	}

//	@Test
//	public void ConcatenationTransformTest() throws SQLException {
//		NudeFilter expected = Nude.source(dummy);
//		NudeFilter actual = Nude.source(dummy)
//				.transform(Concatenate.column(key).append("|"))
//				.transform(Concatenate.column(key).append(newKey))
//				.filter();
//		assertTrue(Nude.equals(expected, actual));
//	}

//	@Test
//	public void RegexTransformTest() throws SQLException {
//		NudeSource expected = Nude.source(dummy);
//		NudeSource actual = Nude.source(dummy)
//				.transform(Regex.column(key).pattern(Pattern.compile("(\\d+)")).getGroup(1))
//				.filter();
//		assertTrue(Nude.equals(expected.sink(), actual.sink()));
//	}
	
	public static final Column key = new SimpleColumn("key");
	public static final Column newKey = new SimpleColumn("newkey");
	public static final Column value = new SimpleColumn("value");
	public static final Iterable<NudeRow> muxSourceExpected = ResultSetUtils.createNudeRows(
			new ColumnGroupImpl(ImmutableList.<Column>of(key, value)),
			new String[][]{
				new String[]{"1", "a"},
				new String[]{"2", "b"},
				new String[]{"3", "c"}
			});
	public static Iterable<NudeRow> muxSourceResultSet1 = ResultSetUtils.createNudeRows(
			new ColumnGroupImpl(ImmutableList.<Column>of(key, value)),
			new String[][]{
				new String[]{"1", "a"},
				new String[]{"3", "c"}
			});
	public static Iterable<NudeRow> muxSourceResultSet2 = ResultSetUtils.createNudeRows(
			new ColumnGroupImpl(ImmutableList.<Column>of(key, value)),
			new String[][]{
				new String[]{"2", "b"}
			});
	
	public static Iterable<NudeRow> dummy = ResultSetUtils.createNudeRows(
			new ColumnGroupImpl(ImmutableList.<Column>of(key, value)),
			new String[][]{
				new String[]{"1", "a"},
				new String[]{"2", "b"},
				new String[]{"3", "c"}
			});
}
