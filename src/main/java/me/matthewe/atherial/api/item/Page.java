package me.matthewe.atherial.api.item;

/**
 * Created by Matthew E on 2/4/2018.
 */
public interface Page {
    Page addLine(String line);

    Page removeLine(String line);

    String[] getLines();
}
