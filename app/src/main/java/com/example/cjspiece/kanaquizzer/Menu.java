package com.example.cjspiece.kanaquizzer;

// This class is used to establish the menu items for the recycler

public class Menu {
    private String name;
    private int imageResourceId;
    private String click;

    public static final Menu[] menus = {new Menu("Hiragana Quiz", R.drawable.hiragana),
            new Menu("Katakana Quiz", R.drawable.katakana),
            new Menu("Hiragana List", R.drawable.hirachart),
            new Menu("Katakana List", R.drawable.katachart),
            new Menu("Statistics", R.drawable.trophy)};

    private Menu(String name, int imageResourceId) {
        this.name = name;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}
