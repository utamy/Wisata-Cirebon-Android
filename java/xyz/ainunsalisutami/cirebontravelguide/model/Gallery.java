package xyz.ainunsalisutami.cirebontravelguide.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Ainun on 13/05/2016.
 */
public class Gallery {
    private int icon;
    private String name;
    private int background;
    private List<String> interests = new ArrayList<>();

    public Gallery(int icon, String name, int background, String... interest) {
        this.icon = icon;
        this.name = name;
        this.background = background;
        interests.addAll(Arrays.asList(interest));
    }
    public int getIcon() {
        return icon;
    }
    public String getName() {
        return name;
    }
    public int getBackground() {
        return background;
    }
    public List<String> getInterests() {
        return interests;
    }
}
