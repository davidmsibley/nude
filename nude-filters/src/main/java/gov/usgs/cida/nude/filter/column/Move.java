package gov.usgs.cida.nude.filter.column;

import com.google.common.collect.ImmutableList;
import gov.usgs.cida.nude.column.Column;
import gov.usgs.cida.nude.column.NudeRow;
import gov.usgs.cida.nude.filter.ColumnTransformer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author dmsibley
 */
public class Move implements ColumnTransformer {
	private static final Logger log = LoggerFactory.getLogger(Move.class);
	
	private final Column fromColumn;
	private final List<Action> actions;
	
	private Move(Column fromColumn) {
		this.fromColumn = fromColumn;
		this.actions = ImmutableList.<Action>of();
	}
	
	private Move(Column fromColumn, List<Action> action) {
		this.fromColumn = fromColumn;
		this.actions = action;
	}
	
	public static Move column(Column column) {
		return new Move(column);
	}
	
	public Move after(Column column) {
		return new Move(this.fromColumn, new ImmutableList.Builder<Action>().addAll(this.actions).add(new Action(RelativeTo.AFTER, column)).build());
	}
	
	public Move before(Column column) {
		return new Move(this.fromColumn, new ImmutableList.Builder<Action>().addAll(this.actions).add(new Action(RelativeTo.BEFORE, column)).build());
	}
	
	public Move as(Column column) {
		return new Move(this.fromColumn, new ImmutableList.Builder<Action>().addAll(this.actions).add(new Action(RelativeTo.SAME, column)).build());
	}

	@Override
	public NudeRow transform(NudeRow row) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	private enum RelativeTo {
		BEFORE,
		AFTER,
		SAME
	}
	
	private class Action {
		public final RelativeTo relative;
		public final Column col;
		
		public Action(RelativeTo relative, Column col) {
			this.relative = relative;
			this.col = col;
		}
		
		
	}
}
