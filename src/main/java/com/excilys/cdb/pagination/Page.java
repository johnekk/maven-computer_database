package com.excilys.cdb.pagination;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class Page<E> {
	
	private int actualPageNumber = 1;
    private int pagesAvailable;
    private List<E> pageItems = new ArrayList<E>();

    public void setActualPageNumber(int actualPageNumber) {
        this.actualPageNumber = actualPageNumber;
    }

    public void setPagesAvailable(int pagesAvailable) {
        this.pagesAvailable = pagesAvailable;
    }

    public void setPageItems(List<E> pageItems) {
        this.pageItems = pageItems;
    }

    public int getActualPageNumber() {
        return actualPageNumber;
    }

    public int getPagesAvailable() {
        return pagesAvailable;
    }

    public List<E> getPageItems() {
        return pageItems;
    }
}

