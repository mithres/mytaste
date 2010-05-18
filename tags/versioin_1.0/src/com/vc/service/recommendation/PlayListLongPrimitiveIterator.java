package com.vc.service.recommendation;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

import org.apache.mahout.cf.taste.impl.common.LongPrimitiveIterator;

public class PlayListLongPrimitiveIterator implements LongPrimitiveIterator {

	private List<Long> list = null;

	private Iterator<Long> ite = null;

	public PlayListLongPrimitiveIterator(List<Long> list) {
		this.list = list;
		ite = list.iterator();
	}

	@Override
	public void skip(int n) {
		for (int i = 0; i < n; i++) {
			if (ite.hasNext()) {
				ite.next();
			}
		}
	}

	@Override
	public long nextLong() {
		return ite.next();
	}

	@Override
	public long peek() {
		if (!ite.hasNext()) {
			throw new NoSuchElementException();
		}
		return ite.next();
	}

	@Override
	public boolean hasNext() {
		return ite.hasNext();
	}

	@Override
	public Long next() {
		return nextLong();
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}

}
