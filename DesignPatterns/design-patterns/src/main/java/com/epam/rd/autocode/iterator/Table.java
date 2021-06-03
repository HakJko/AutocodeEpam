package com.epam.rd.autocode.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Table implements Iterable<String> {
    private final String[] columns;
    private final int[] rows;

    public Table(String[] columns, int[] rows) {
        this.columns = columns;
        this.rows = rows;
    }

    @Override
    public Iterator<String> iterator() {
        return getTable().iterator();
    }

    private List<String> getTable() {
        List<String> table = new ArrayList<>();
        for (String column : columns) {
            for (int row : rows) {
                table.add(column + row);
            }
        }
        return table;
    }
}
