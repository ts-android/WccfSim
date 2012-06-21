package app.yzw.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemClickListener;

public class digsearch extends Activity {

	private ArrayList<Integer> m_selectItemList = new ArrayList<Integer>();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchview);

        // ポジションリストの文言設定
        Spinner spn_position = (Spinner)findViewById(R.id.spn_pos);
        ArrayAdapter<String> pos_data = new ArrayAdapter<String>(
      	      this,
      	      android.R.layout.simple_spinner_item,
      	      getResources().getStringArray(R.array.pos_data));
        pos_data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_position.setAdapter(pos_data);

        // クラブリストの文言設定
        Spinner spn_club = (Spinner)findViewById(R.id.spn_club);
        ArrayAdapter<String> club_data = new ArrayAdapter<String>(
      	      this,
      	      android.R.layout.simple_spinner_item,
      	      getResources().getStringArray(R.array.club_data));
        club_data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_club.setAdapter(club_data);

        // グループリストの文言設定
        Spinner spn_group = (Spinner)findViewById(R.id.spn_group);
        ArrayAdapter<String> group_data = new ArrayAdapter<String>(
      	      this,
      	      android.R.layout.simple_spinner_item,
      	      getResources().getStringArray(R.array.group_data));
        group_data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_group.setAdapter(group_data);

        // 国籍リストの文言設定
        Spinner spn_nationality = (Spinner)findViewById(R.id.spn_nationality);
        ArrayAdapter<String> nationality_data = new ArrayAdapter<String>(
      	      this,
      	      android.R.layout.simple_spinner_item,
      	      getResources().getStringArray(R.array.nationality_data));
        nationality_data.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn_nationality.setAdapter(nationality_data);

        // ボタンのイベント設定
        Button btn_search = (Button)findViewById(R.id.btn_search);
        btn_search.setOnClickListener(_OnClickListenerHandle_Serch);

        Button btn_set = (Button)findViewById(R.id.btn_set);
        btn_set.setOnClickListener(_OnClickListenerHandle_Set);

        Button btn_ret = (Button)findViewById(R.id.btn_return);
        btn_ret.setOnClickListener(_OnClickListenerHandle_Ret);
   }


    private OnClickListener _OnClickListenerHandle_Set = new  OnClickListener()
    {
    	public void onClick(View v) {
    	    // 返却データの設定
    		Intent data = new Intent();
    		data.putExtra("value", m_selectItemList);
    		// 結果の返却
    		setResult(RESULT_OK, data);
    		// 検索画面の終了
    		finish();
    	}
    };

    private OnClickListener _OnClickListenerHandle_Ret = new  OnClickListener()
    {
    	public void onClick(View v) {
    		// 検索画面の終了
    		finish();
    	}
    };

    private OnClickListener _OnClickListenerHandle_Serch = new  OnClickListener()
    {
    	public void onClick(View v) {

    		ArrayList<PlayerData> data = new ArrayList<PlayerData>();
    		data.add(new PlayerData(1, "1", "A", "GK", "1", "1", "1"));
    		data.add(new PlayerData(2, "2", "B", "DF", "1", "1", "1"));
    		data.add(new PlayerData(3, "3", "C", "MF", "1", "1", "1"));
    		data.add(new PlayerData(4, "4", "D", "FW", "1", "1", "1"));

            PlayerAdapter arrayAdapter = new PlayerAdapter(digsearch.this,R.layout.playerlistview, data);

            ListView list_result = (ListView)findViewById(R.id.list_searchresult);
            list_result.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
            list_result.setAdapter(arrayAdapter);
            list_result.setOnItemClickListener(_OnItemClickListenerHandle);
            list_result.setSelection(0);
    	}
    };

    private OnItemClickListener _OnItemClickListenerHandle = new  OnItemClickListener()
    {
    	public void onItemClick(android.widget.AdapterView<?> arg0, View arg1, int arg2, long arg3)
    	{
            ListView listView = (ListView) arg0;
            int selectid = ((PlayerData) listView.getItemAtPosition(arg2)).GetId();
            // 選択IDが存在しなければ、リストに追加
            if( m_selectItemList.contains(selectid) == false )
            {
            	((PlayerData) listView.getItemAtPosition(arg2)).SetSelectState( true );
            	m_selectItemList.add(selectid);
            }
            else
            {
            	((PlayerData) listView.getItemAtPosition(arg2)).SetSelectState( false );
            	m_selectItemList.remove((Object)selectid);
            }
    	};
    };
}
