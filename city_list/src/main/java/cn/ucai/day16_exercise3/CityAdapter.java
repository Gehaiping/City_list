package cn.ucai.day16_exercise3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CityAdapter extends BaseAdapter {

    Context mContext;
    List<CityBean> cities;

    public CityAdapter(Context mContext, List<CityBean> letters) {
        this.mContext = mContext;
        this.cities = letters;
    }

    @Override
    public int getCount() {
        return cities == null ? 0 : cities.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return cities.get(i);
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.item_city, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        CityBean bean = cities.get(position);
        holder.letter.setText(bean.getLetter());
        holder.list.setAdapter(new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, bean.getCities()));

        return view;
    }

    class ViewHolder {
        TextView letter;
        NoScrollListView list;

        public ViewHolder(View itemView) {
            letter = (TextView) itemView.findViewById(R.id.cityLetter);
            list = (NoScrollListView) itemView.findViewById(R.id.cityList);
        }
    }
}
