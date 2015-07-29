package recommender.hospital.adapters;

import java.util.ArrayList;

import recommender.hospital.R;
import recommender.hospital.adapterdata.HospitalItem;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HospitalListAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<HospitalItem> hospitalItems;
	
	public HospitalListAdapter(Context context, ArrayList<HospitalItem> hospitalItems){
		this.context = context;
		this.hospitalItems = hospitalItems;
	}

	@Override
	public int getCount() {
		return hospitalItems.size();
	}

	@Override
	public Object getItem(int position) {		
		return hospitalItems.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.hospital_list_items, null);
        }
         
        ImageView imgIcon = (ImageView) convertView.findViewById(R.id.img_hospital);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.name_hospital);
      
        imgIcon.setImageResource(hospitalItems.get(position).getIcon());        
        txtTitle.setText(hospitalItems.get(position).getTitle());
        
        return convertView;
	}
}
