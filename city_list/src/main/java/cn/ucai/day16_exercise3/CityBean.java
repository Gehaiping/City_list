package cn.ucai.day16_exercise3;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/12/16.
 */

public class CityBean {

    String letter;
    List<String> cities;

    public String getLetter() {
        return letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public List<String> getCities() {
        return cities;
    }

    public void setCities(List<String> cities) {
        this.cities = cities;
    }

    @Override
    public String toString() {
        return "CityBean{" +
                "letter='" + letter + '\'' +
                ", cities=" + cities +
                '}';
    }
}
