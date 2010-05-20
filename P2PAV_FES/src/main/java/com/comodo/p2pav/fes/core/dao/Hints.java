/**
 * 
 */
package com.comodo.p2pav.fes.core.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.hibernate.Query;

/**
 * JPA query hints
 * 
 * @author Ammen
 * @see HintConstants
 */
public class Hints extends Offset implements Serializable {

	private static final long serialVersionUID = 4188404084579334606L;

	/**
	 * Set hints and offset to query.
	 * 
	 * @param query
	 * @param hints
	 */
	public static void applyHintsTo(Query query, Hints hints) {
		applyTo(query, hints);
	}

	/**
	 * Hints map
	 */
	protected Map<String, Object> hints = new HashMap<String, Object>();

	public Hints() {
		super(0);
	}

	public Hints(Hints hints) {
		super(hints.offset, hints.length);
		if (hints.hints != null) {
			this.hints = new HashMap<String, Object>(hints.hints);
		}
	}

	/**
	 * @param offset
	 */
	public Hints(int offset) {
		super(offset);
	}

	/**
	 * @param offset
	 * @param length
	 */
	public Hints(int offset, int length) {
		super(offset, length);
	}

	public Hints(int offset, int length, Map<String, Object> hints) {
		super(offset, length);
		this.hints = hints;
	}

	public Map<String, Object> getHints() {
		return hints;
	}

	public void setHints(Map<String, Object> hints) {
		this.hints = hints;
	}

	public void setHintParameters(String name, Object value) {
		hints.put(name, value);
	}

	@Override
	public String toString() {
		return "offset " + offset + ", length " + length + ", hints " + hints;
	}
}
