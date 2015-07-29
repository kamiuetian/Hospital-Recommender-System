package recommender.hospital.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import recommender.hospital.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EmergencyListAdapter extends BaseAdapter {
    private final ArrayList mData;

    public EmergencyListAdapter(HashMap<String, Float> map) {
        mData = new ArrayList();
        mData.addAll(map.entrySet());
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Map.Entry<String, Float> getItem(int position) {
        return (Map.Entry) mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO implement you own logic with ID
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View result=convertView;

        if (result == null) {
            result = LayoutInflater.from(parent.getContext()).inflate(R.layout.emergency_list_items, parent, false);
        } 

        Map.Entry<String, Float> item = getItem(position);

       TextView txtKey=(TextView) result.findViewById(R.id.txtKey);
       //TextView txtVal=(TextView) result.findViewById(R.id.txtValue);
      /// RatingBar ratebar=(RatingBar) result.findViewById(R.id.ratingBar1);
       txtKey.setText(item.getKey().toString());
       //txtVal.setText(item.getValue().toString());
      // ratebar.setRating(item.getValue()/2);
        return result;
    }
}