package com.vc.core.dao;

import javax.persistence.Query;


/**
 * 
 * @author Yaroslav Pogrebnyak
 *
 * Represents some data length and offset
 */
public class Offset {
	
	public static final int NO_LIMIT = -1;
	
	public static final Offset FULL = new Offset(0);
	public static final Offset FIRST = new Offset(0,1);
		
	protected int length;
	protected int offset;
	
	/**
	 * 
	 * @param offset starting position
	 * @param length if no length limit needed it should be used {@link #NO_LIMIT} constant
	 */
	public Offset(int offset, int length) {
		this.offset = offset;
		this.length = length;
	}
	
	public Offset(int offset) {
		this(offset, NO_LIMIT) ;
	}
		

	public void setLength(int length) {
		this.length = length;
	}
	
	public int getLength() {
		return length;
	}
	
	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	public int getOffset() {
		return offset;
	}
	
	/**
	 * Apply offset to the query
	 * @param query
	 * @param offset
	 */
	public static void applyTo(Query query, Offset offset) {
		query.setFirstResult(offset.getOffset());
		if (offset.getLength() >= 0) {
			query.setMaxResults(offset.getLength());
		}
	}
	
 
}
