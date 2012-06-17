package app.yzw.android;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class PlayerAdapter extends ArrayAdapter<PlayerData> {
	private int mFieldId;
	private LayoutInflater mInflater;

	public PlayerAdapter(Context context, int textViewResourceId, List<PlayerData> objects) {
		super(context, textViewResourceId, objects);
		mFieldId = textViewResourceId;
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public View getView(int position, View convertView, ViewGroup parent)
	{
		View view;
		TextView txt_cardno;
		TextView txt_cardver;
		TextView txt_name;
		TextView txt_group;
		TextView txt_nationality;
		TextView txt_team;
		TextView txt_pos;
		if (convertView == null) {
			view = mInflater.inflate(mFieldId, parent, false);
		}
		else
		{
			view = convertView;
		}

		txt_cardno = (TextView)view.findViewById(R.id.txt_listcardno);
		txt_cardver = (TextView)view.findViewById(R.id.txt_listcardver);
		txt_name = (TextView)view.findViewById(R.id.txt_listname);
		txt_team = (TextView)view.findViewById(R.id.txt_listclub);
		txt_group = (TextView)view.findViewById(R.id.txt_listgroup);
		txt_nationality = (TextView)view.findViewById(R.id.txt_listnationality);
		txt_pos = (TextView)view.findViewById(R.id.txt_listpos);

		PlayerData pdata = this.getItem(position);
		txt_cardno.setText(Integer.toString((pdata.GetId())));
		txt_cardver.setText(pdata.GetVer());
		txt_name.setText(pdata.GetName());
		txt_team.setText(pdata.GetTeam());
		txt_group.setText(pdata.GetGroup());
		txt_nationality.setText(pdata.GetNationality());
		txt_pos.setText(pdata.GetPosition());
		return view;
	}

}
