package cn.ucai.day16_exercise3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView letter_list, city_list;
    String[] letters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    List<CityBean> cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        city_list = (ListView) findViewById(R.id.city_list);
        letter_list = (ListView) findViewById(R.id.letter_list);
        //初始化字母索引列表
        letter_list.setAdapter(new ArrayAdapter<String>(this, R.layout.item_letter, letters));
        //获取城市数据
        cityList = getCity();
        //初始化城市列表
        city_list.setAdapter(new CityAdapter(this, cityList));
        //设置字母索引点击事件与城市列表的交互
        letter_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String letter = letters[position];
                Toast.makeText(MainActivity.this, letter, Toast.LENGTH_SHORT).show();
                for (int i = 0; i < cityList.size(); i++) {
                    CityBean bean = cityList.get(i);
                    if (bean.getLetter().equalsIgnoreCase(letter)) {
                        city_list.setSelection(i);
                    }
                }
            }
        });
    }

    private List<CityBean> getCity() {
        InputStream in = getResources().openRawResource(R.raw.city);
        List<CityBean> cityList = null;
        CityBean bean = null;
        List<String> cities = null;
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(in, "utf-8");
            for (int event = XmlPullParser.START_DOCUMENT; event != XmlPullParser.END_DOCUMENT; event = parser.next()) {
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:
                        cityList = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:
                        String tag = parser.getName();
                        if ("city".equals(tag)) {
                            bean = new CityBean();
                            String letter = parser.getAttributeValue(null, "letter");
                            bean.setLetter(letter);
                            cities = new ArrayList<>();
                        } else if ("item".equals(tag)) {
                            cities.add(parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        String name = parser.getName();
                        if ("city".equals(name)) {
                            bean.setCities(cities);
                            cityList.add(bean);
                        }
                        break;
                }
            }
            for (CityBean cityBean : cityList) {
                Log.i("main", cityBean.toString());
                return cityList;
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return cityList;
    }
}
