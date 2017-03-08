package com.lab1.Filter;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LessTimeFilter implements Filter {

    private Long lastModified;

    public LessTimeFilter(long lastModified) {
        this.lastModified = lastModified;
    }

    @Override
    public int hashCode() {
        return lastModified.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof GreaterTimeFilter && this.hashCode() == obj.hashCode());
    }

    @Override
    public String toString() {
        return "<" + lastModified;
    }

    @Override
    public boolean check(File file) {
        return file.lastModified() < lastModified;
    }
}