package com.vc.core.entity;

import java.util.ArrayList;
import java.util.List;

public class PageListImpl<E> implements IPageList<E> {

    private static final long serialVersionUID = 8332174002476722533L;

    private List<E> records = new ArrayList<E>();

    private long recordTotal;

    @Override
    public long getRecordTotal() {
        return this.recordTotal;
    }

    @Override
    public List<E> getRecords() {
        return this.records;
    }

    @Override
    public void setRecordTotal(long recordTotal) {
        this.recordTotal = recordTotal;
    }

    @Override
    public void setRecords(List<E> records) {
        this.records = records;
    }

    /**
     * The <code>IPageList</code> without total record count.  
     * @author Valery Gorbunov <a href=mailto:vgorbunov@comodo.com.ua>vgorbunov@comodo.com.ua</a>
     *
     * @param <E>
     */
    public static class LightPageList<E> extends PageListImpl<E> {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1337796920299686410L;

		/* (non-Javadoc)
		 * @see com.comodo.hopsurf.core.entity.PageListImpl#getRecordTotal()
		 */
		@Override
		public long getRecordTotal() {
			throw new UnsupportedOperationException("getRecordTotal() not supported");
		}

		/* (non-Javadoc)
		 * @see com.comodo.hopsurf.core.entity.PageListImpl#setRecordTotal(long)
		 */
		@Override
		public void setRecordTotal(long recordTotal) {
			throw new UnsupportedOperationException("setRecordTotal() not supported");
		}
		
    } 
}
