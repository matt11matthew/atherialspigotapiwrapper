package me.matthewe.atherial.api.item;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matthew E on 2/4/2018.
 */
public class AtherialPage implements Page {
    private List<String> lineStringList;

    public AtherialPage() {
        this.lineStringList = new ArrayList<>();
    }

    @Override
    public Page addLine(String line) {
        this.lineStringList.add(line);
        return this;
    }

    @Override
    public Page removeLine(String line) {
        this.lineStringList.remove(line);
        return this;
    }

    @Override
    public String[] getLines() {
        String[] lines = new String[lineStringList.size()];
        for (int i = 0; i < lines.length; i++) {
            lines[i] = lineStringList.get(i);
        }
        return lines;
    }
}
