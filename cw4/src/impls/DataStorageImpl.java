package impls;

import java.io.Serializable;
import java.util.Map;

import iinterfaces.Contact;
import iinterfaces.DataStorage;
import iinterfaces.Meeting;
// DECOMISSIONED 

public class DataStorageImpl implements DataStorage, Serializable{

	// Fields
	// ------
	
	Map<Integer, Meeting> dsMeetingMap;
	Map<Integer, Contact> dsContactMap;
	
	// Constructor
	// -----------
	public DataStorageImpl(){
		
	}
	
	// Methods
	// -------
	
	@Override
	public Map<Integer, Meeting> getMeetingData() {
		return dsMeetingMap;
	}
	
	@Override
	public void setMeetingData(Map<Integer, Meeting> m) {
		dsMeetingMap = m;
	}

	@Override
	public Map<Integer, Contact> getContactData() {
		return dsContactMap;
	}

	@Override
	public void setContactData(Map<Integer, Contact> c) {
		dsContactMap = c;
		
	}

}
