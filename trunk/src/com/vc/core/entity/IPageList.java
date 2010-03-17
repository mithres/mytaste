package com.vc.core.entity;

import java.io.Serializable;
import java.util.List;

public interface IPageList<E> extends Serializable{


    public abstract long getRecordTotal();

    public abstract List<E> getRecords();

    public abstract void setRecordTotal(long recordTotal);

    public abstract void setRecords(List<E> records);    

}
