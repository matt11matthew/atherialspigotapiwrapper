package me.matthewe.atherial.api.modules.buycraft.listings;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;

/**
 * Created by Matthew E on 2/6/2018.
 */
public class Listings implements Iterable<Listings.Category> {
    private Category[] categories;

    public Category[] getCategories() {
        return categories;
    }

    @Override
    public Iterator<Category> iterator() {
        return Arrays.asList(categories).iterator();
    }

    @Override
    public void forEach(Consumer<? super Category> action) {
        Arrays.asList(categories).forEach(action);
    }

    public static class Category {
        private int id;
        private int order;
        private String name;
        private BuyCraftPackage[] packages;
        @JsonProperty("only_subcategories")
        private boolean onlySubcategories;
        @JsonProperty("gui_item")
        private int guiItem;

        @JsonProperty("subcategories")
        private SubCategory[] subCategories;

        public int getId() {
            return id;
        }

        public int getOrder() {
            return order;
        }

        public String getName() {
            return name;
        }

        public boolean isOnlySubcategories() {
            return onlySubcategories;
        }

        public SubCategory[] getSubCategories() {
            return subCategories;
        }

        public BuyCraftPackage[] getPackages() {
            return packages;
        }

        public int getGuiItem() {
            return guiItem;
        }

        public static class SubCategory {
            private int id;
            private int order;
            private String name;
            @JsonProperty("gui_item")
            private int guiItem;

            private BuyCraftPackage[] packages;

            public int getId() {
                return id;
            }

            public int getOrder() {
                return order;
            }

            public String getName() {
                return name;
            }

            public int getGuiItem() {
                return guiItem;
            }

            public BuyCraftPackage[] getPackages() {
                return packages;
            }
        }
    }

    public static class BuyCraftPackage {
        private int id;
        private int order;
        private String name;
        private Sale sale;
        //@JsonProperty("gui_item")
        //private int guiItem;
        private double price;

        public int getId() {
            return id;
        }

        public int getOrder() {
            return order;
        }

        public String getName() {
            return name;
        }

        public Sale getSale() {
            return sale;
        }

       // public int getGuiItem() {
       //     return guiItem;
       // }

        public double getPrice() {
            return price;
        }

        public class Sale {
            private boolean active;
            private int discount;

            public boolean isActive() {
                return active;
            }

            public int getDiscount() {
                return discount;
            }
        }
    }
}
