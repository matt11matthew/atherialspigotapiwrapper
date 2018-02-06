package me.matthewe.atherial.api.item;

import org.bukkit.inventory.meta.BookMeta;

/**
 * Created by Matthew E on 2/4/2018.
 */
public class AtherialBookItem extends BookItem {
    @Override
    public BookItem title(String title) {
        if (this.itemStack != null) {
            BookMeta bookMeta = (BookMeta) this.itemStack.getItemMeta();
            bookMeta.setTitle(title);
            this.itemStack.setItemMeta(bookMeta);
        }
        return this;
    }

    @Override
    public BookItem author(String author) {
        if (this.itemStack != null) {
            BookMeta bookMeta = (BookMeta) this.itemStack.getItemMeta();
            bookMeta.setAuthor(author);
            this.itemStack.setItemMeta(bookMeta);
        }
        return this;
    }

    @Override
    public BookItem generation(Generation generation) {
        if (this.itemStack != null) {
            BookMeta bookMeta = (BookMeta) this.itemStack.getItemMeta();
            bookMeta.setGeneration(BookMeta.Generation.valueOf(generation.toString()));
            this.itemStack.setItemMeta(bookMeta);
        }
        return this;
    }

    @Override
    public BookItem pages(Page... pages) {
        if (this.itemStack != null) {
            BookMeta bookMeta = (BookMeta) this.itemStack.getItemMeta();
            for (Page page : pages) {
                AtherialPage atherialPage = (AtherialPage) page;
                bookMeta.addPage(atherialPage.getLines());
            }
            this.itemStack.setItemMeta(bookMeta);
        }
        return this;
    }
}
