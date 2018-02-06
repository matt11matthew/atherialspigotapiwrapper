package me.matthewe.atherial.api.item;

/**
 * Created by Matthew E on 2/4/2018.
 */
public abstract class BookItem extends AtherialItem {
    public abstract BookItem title(String title);
    public abstract BookItem author(String author);
    public abstract  BookItem generation(Generation generation);
    public abstract  BookItem pages(Page... pages);
}
