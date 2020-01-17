package com.excilys.cdb.pagination;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Page<E> {
	
	private int pageID = 1;
    private int pagesAvailable;
    private List<E> pageItems = new ArrayList<E>();

    public void setPageID(int pageID) {
        this.pageID = pageID;
    }

    public void setPagesAvailable(int pagesAvailable) {
        this.pagesAvailable = pagesAvailable;
    }

    public void setPageItems(List<E> pageItems) {
        this.pageItems = pageItems;
    }

    public int getPageID() {
        return pageID;
    }

    public int getPagesAvailable() {
        return pagesAvailable;
    }

    public List<E> getPageItems() {
        return pageItems;
    }
}

