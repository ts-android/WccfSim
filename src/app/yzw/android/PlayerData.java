package app.yzw.android;

public class PlayerData {
	private int m_id;
	private String m_ver;
	private String m_name;
	private String m_group;
	private String m_nationality;
	private String m_team;
	private String m_position;
	private Boolean m_selected = false;

	public PlayerData( int id, String ver, String name, String group, String nationality, String team, String position)
	{
		m_id = id;
		m_ver = ver;
		m_name = name;
		m_group = group;
		m_nationality = nationality;
		m_team = team;
		m_position = position;
	}

	public int GetId()
	{
		return m_id;
	}

	public String GetVer()
	{
		return m_ver;
	}

	public String GetName()
	{
		return m_name;
	}

	public String GetGroup()
	{
		return m_group;
	}

	public String GetNationality()
	{
		return m_nationality;
	}

	public String GetTeam()
	{
		return m_team;
	}

	public String GetPosition()
	{
		return m_position;
	}

	public Boolean GetSelectState()
	{
		return m_selected;
	}
	
	public void SetSelectState( Boolean state)
	{
		m_selected = state;
	}
}
