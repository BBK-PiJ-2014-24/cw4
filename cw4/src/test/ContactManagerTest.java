package test;

import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;
import static org.hamcrest.CoreMatchers.*;
import iinterfaces.Contact;
import iinterfaces.ContactManager;
import iinterfaces.FutureMeeting;
import iinterfaces.Meeting;
import impls.ContactImpl;
import impls.ContactManagerImpl;
import impls.FutureMeetingImpl;
import impls.MeetingImpl;
import impls.PastMeetingImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ContactManagerTest {

	
	// Declarations
	// ------------
	Calendar calPast;
	Calendar dudDate;
	Calendar calFut;
	Calendar badDate;
	Calendar mar14;
	Calendar apr14;
	Calendar may14;
	Calendar jun14;
	Calendar jul14;
	Calendar aug14;
	Calendar mar15;
	Calendar apr15;
	Calendar may15;
	Calendar jun15;
	Calendar jul15;
	Calendar aug15;
	SimpleDateFormat sdf;
	Contact harry;
	Contact jill;
	Contact jill2;
	Contact jack;
	Contact jim;
	Contact sophie;
	Contact gertrude;
	Contact alan;
	Contact guy;
	Contact mark;
	Set<Contact> contactSet;
	Set<Contact> emptyContactSet;
	Set<Contact>jimJillSet;
	Set<Contact>harrySophieSet;
	Set<Contact> fullContactSet;
	
	List<Calendar> jimMeetingList;
	List<Calendar> harryMeetingList;
	
	ContactManagerImpl cm;
	FutureMeetingImpl futMeetGood;
	FutureMeetingImpl futMeetBad;
	PastMeetingImpl pastMeetGood;
	PastMeetingImpl pastMeetBad;
	Meeting m;
	
	FutureMeeting meetingMar15;
	FutureMeeting meetingApr15;
	FutureMeeting meetingMay15;
	FutureMeeting meetingJun15;
	FutureMeeting meetingJul15;
	FutureMeeting meetingAug15;
	
	String notes;
	String badNotes;
	
	
	
	@Before
	public void setUp(){
		// Set Date()
		// ----------
		calPast = new GregorianCalendar(2014, 8, 24, 12, 05);
		calFut = new GregorianCalendar(2018, 11, 25, 16, 37);
		sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm");
		badDate = null;
		mar14 = new GregorianCalendar(2014, 3, 23, 12, 05);
		apr14 = new GregorianCalendar(2014, 4, 23, 12, 05);
		may14 = new GregorianCalendar(2014, 5, 23, 12, 05);
		jun14 = new GregorianCalendar(2014, 6, 23, 12, 05);
		jul14 = new GregorianCalendar(2014, 7, 23, 12, 05);
		aug14 = new GregorianCalendar(2014, 8, 23, 12, 05);
		
		mar15 = new GregorianCalendar(2015, 3, 23, 12, 05);
		apr15 = new GregorianCalendar(2015, 4, 23, 12, 05);
		may15 = new GregorianCalendar(2015, 5, 23, 12, 05);
		jun15 = new GregorianCalendar(2015, 6, 23, 12, 05);
		jul15 = new GregorianCalendar(2015, 7, 23, 12, 05);
		aug15 = new GregorianCalendar(2015, 8, 23, 12, 05);
		dudDate = new GregorianCalendar(2022, 8, 23, 12, 05);
				
		// Create Contacts
		// ------------
		 harry = new ContactImpl("Harry","Likes a Drink");
		 jill = new ContactImpl("Jill","Likes White Wine");
		 jill2 = new ContactImpl("Jill","Likes Red Wine");
		 jack = new ContactImpl("Jack", "Teetotal");
		 jim = new ContactImpl("Jim", "BUSY MAN");
		 sophie = new ContactImpl("Sophie", "Angel");
		 gertrude = new ContactImpl("Gertrude", "NOT ON CONTACT LIST");
		 alan = new ContactImpl("Alan", "Big Guy");
		 guy = new ContactImpl("Guy", "Slim Guy");
		 mark = new ContactImpl("Mark", "Shifty");
		 
		
		// Create Set of Contacts
		// ----------------------
		contactSet = new HashSet<Contact>();
		contactSet.add(harry);
		contactSet.add(jill);
		contactSet.add(jack);
	
		emptyContactSet = null;
		
		jimJillSet = new HashSet<Contact>();
		jimJillSet.add(jill);
		jimJillSet.add(jim);
		
		harrySophieSet = new HashSet<Contact>();
		harrySophieSet.add(harry);
		harrySophieSet.add(sophie);
		
		fullContactSet = new HashSet<Contact>();
		contactSet.add(jill);
		contactSet.add(jill2);
		contactSet.add(jack);
		contactSet.add(jim);
		contactSet.add(harry);
		contactSet.add(sophie);
		contactSet.add(alan);
		contactSet.add(guy);
		contactSet.add(mark);
		
		
		
		// Instant ContactManager
		// ----------------------
		cm = new ContactManagerImpl();
		
		// Instant Meetings
		// ----------------

		 
		 // MeetingList
		 // -----------



		// Notes
		// -----
		
		notes = "Meeting went well";
		badNotes = "";
	}
	
	@Rule
	public ExpectedException ex = ExpectedException.none();
	
	
	
	@Test
	// Tests for addFutureMeeting()
	// ----------------------------
	public void testAddFutureMeeting() {
		List<Integer> idContainer = new ArrayList<Integer>();  // Container for ID's in random Check
		
		for(int i=0; i<900;i++){  // Testing Range and Uniqueness of IDs
			int id = cm.addFutureMeeting(contactSet, calFut);
			
			assertTrue("test addFutureMeeting() -  min id: ", id >= 1000000);	// test id min number
			assertTrue("test addFutureMeeting() - max id: ", id <= 10000000); // test id max number
			assertFalse("test ID Uniquenes: ", idContainer.contains(id));  // test uniqueness
			idContainer.add(id);
		}
		// System.out.println("The end");
	}
	
	@Test
	public void testEXAddFutureMeeting(){
		ex.expect(IllegalArgumentException.class);
		cm.addFutureMeeting(contactSet, calPast);
	}
	
	@Test
	// Tests for getFutureMeeting(id)
	// ------------------------------
	public void testGetFutureMeeting(){
		int id = cm.addFutureMeeting(contactSet, calFut);
		futMeetGood = new FutureMeetingImpl(id, contactSet, calFut);  // FutureMeeting w/Future Date
		assertEquals("test getFutureMeeting() - Meeting ID Check: ", futMeetGood.getId(), cm.getFutureMeeting(id).getId());
		assertEquals("test getFutureMeeting() - Meeting Contacts Check: ", futMeetGood.getContacts(), cm.getFutureMeeting(id).getContacts());
		assertEquals("test getFutureMeeting() - Meeting Date Check: ", futMeetGood.getDate(), cm.getFutureMeeting(id).getDate());
	}
	
	@Test
	public void testExGetFutureMeeting(){
		int id = 1234;
		assertEquals("test getFutureMeeting() - Check Bad id", null, cm.getMeeting(id));	
	}
	
	@Test
	// Test for addNewPastMeeting()
	// ----------------------------
	public void testExAddNewPastMeeting1(){
			ex.expect(IllegalArgumentException.class);
			cm.addNewPastMeeting(emptyContactSet, calPast, notes);  // test for emptyContactSet
	}
	
	@Test
	public void testExAddNewPastMeeting2(){
			ex.expect(NullPointerException.class);
			cm.addNewPastMeeting(contactSet, badDate, notes);  // test for BadDate
	}
	@Test
	public void testExAddNewPastMeeting3(){
			ex.expect(NullPointerException.class);
			cm.addNewPastMeeting(contactSet, calPast, badNotes);  // test for BadNotes
	}
	
	@Test
	// Test for getPastMeeting(id)
	// ---------------------------
	public void testGetNewPastMeeting(){
		cm.addNewPastMeeting(contactSet, calPast, notes);
		int id = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		pastMeetGood = new PastMeetingImpl(id, contactSet, calPast, notes);  // PastMeeting w/Past Date
		assertEquals("test getPastMeeting() - Meeting ID Check: ", pastMeetGood.getId(), cm.getPastMeeting(id).getId());
		assertEquals("test getPastMeeting() - Meeting Contacts Check: ", pastMeetGood.getContacts(), cm.getPastMeeting(id).getContacts());
		assertEquals("test getPastMeeting() - Meeting Date Check: ", pastMeetGood.getDate(), cm.getPastMeeting(id).getDate());
		assertEquals("test getPastMeeting() - Meeting Notes Check: ", pastMeetGood.getNotes(), cm.getPastMeeting(id).getNotes());
	}
	
	@Test
	public void testExGetNewPastMeeting1(){
		cm.addNewPastMeeting(contactSet, calFut, notes);
		int id = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		assertEquals("test getPastMeeting() - Check Bad Date", null, cm.getPastMeeting(id));	
	}
	
	@Test
	public void testExGetNewPastMeeting2(){
		int id = 1234; 
		assertEquals("test getPastMeeting() - Check Bad id", null, cm.getPastMeeting(id));	
	}
	
	@Test
	// Test for getMeeting()
	// ---------------------
	public void testGetMeeting(){
		int id = cm.addFutureMeeting(contactSet, calFut);
		futMeetGood = new FutureMeetingImpl(id, contactSet, calFut);  // FutureMeeting w/Future Date
		assertEquals("test getMeeting() - FutMeeting ID Check: ", futMeetGood.getId(), cm.getMeeting(id).getId());
		assertEquals("test getMeeting() - FutMeeting Contacts Check: ", futMeetGood.getContacts(), cm.getMeeting(id).getContacts());
		assertEquals("test getMeeting() - FutMeeting Date Check: ", futMeetGood.getDate(), cm.getMeeting(id).getDate());
		
		cm.addNewPastMeeting(contactSet, calPast, notes);
		id = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		pastMeetGood = new PastMeetingImpl(id, contactSet, calPast, notes);  // PastMeeting w/Past Date
		assertEquals("test getMeeting() - PastMeeting ID Check: ", pastMeetGood.getId(), cm.getPastMeeting(id).getId());
		assertEquals("test getMeeting() - PastMeeting Contacts Check: ", pastMeetGood.getContacts(), cm.getPastMeeting(id).getContacts());
		assertEquals("test getMeeting() - PastMeeting Date Check: ", pastMeetGood.getDate(), cm.getPastMeeting(id).getDate());
		assertEquals("test getMeeting() - PastMeeting Notes Check: ", pastMeetGood.getNotes(), cm.getPastMeeting(id).getNotes());	
	}
	@Test
	public void testExGetMeeting1(){
		int id = 1234;
		assertEquals("test getMeeting() - Check Bad id", null, cm.getMeeting(id));	
	}
	
	
	@Test
	// test addNewContact()
	// --------------------
	public void testAddGetNewContact(){
		Contact Stefan = new ContactImpl("Stefan", "cub scout");
		cm.addNewContact("Stefan", "cub scout");
		assertEquals("test Single addNewContact - Size of  : ", 1, cm.getContactMapSize());
	}
	
	public void testExAddGetNewContact1(){
		ex.expect(NullPointerException.class);
		cm.addNewContact("Stefan", "");   // No Notes
	}
	
	public void testExAddGetNewContact2(){
		ex.expect(NullPointerException.class);
		cm.addNewContact("Stefan", null);   // null Notes
	}	
	
	public void testExAddGetNewContact3(){
		ex.expect(NullPointerException.class);
		cm.addNewContact("", "cub scout");   // No name
	}
	
	public void testExAddGetNewContact4(){
		ex.expect(NullPointerException.class);
		cm.addNewContact(null, "cub scout");   // No name
	}	
	
	@Test
	// test getContacts()
	// ---------------------
	public void testGetContacts1(){
		cm.addNewContact("Harry","Likes a Drink");  // Harry to be Found with this get method
		int id = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		cm.addNewContact("Paul","policeman"); // Padding for the Contact List
		cm.addNewContact("Portia","sweet girl"); // Padding for the Contact List
		Set<Contact> foundContacts = cm.getContacts(id);  // return type is Set
		// Convert set =>toArray
		Contact[] contactArray = (Contact[]) foundContacts.toArray(new Contact[foundContacts.size()]); // Convert to array
		assertEquals("test getContacts() - single contact Name", "Harry", contactArray[0].getName());
		assertEquals("test getContacts() - single contact Notes", "Likes a Drink", contactArray[0].getNotes());
	}
	
	@Test
	// getContacts(ids ...);
	public void testGetContacts2(){
		// Load Some Contacts into ContactManager
		cm.addNewContact("Harry","Likes a Drink");  // Harry to be Found with this get method
		int id1 = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		cm.addNewContact("Paul","policeman"); // Padding for the Contact List
		int id2 = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		cm.addNewContact("Portia","sweet girl"); // Padding for the Contact List
		int id3 = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		
		// Create a parallel test set of contacts 
		Set<Contact> testSet = new HashSet<Contact>();
		Contact harry = new ContactImpl(id1,"Harry","Likes a Drink");
		Contact paul = new ContactImpl(id2,"Paul","policeman");
		Contact portia = new ContactImpl(id3,"Portia","sweet girl");
		testSet.add(harry);
		testSet.add(paul);
		testSet.add(portia);
		
		List<String> names = new ArrayList<String>();
		names.add("Harry");
		names.add("Paul");
		names.add("Portia");
		
		Set<Contact> foundContacts = cm.getContacts(id1,id2,id3);  // return type is Set
		for(Contact i : foundContacts){
			assertTrue("testGetContacts - multiple Contacts - Check Names", names.contains(i.getName()));
		}
		
		List<String> listNotes = new ArrayList<String>();
		listNotes.add("Likes a Drink");
		listNotes.add("policeman");
		listNotes.add("sweet girl");
		
		for(Contact i : foundContacts){
			assertTrue("testGetContacts - multiple Contacts - Check Notes", listNotes.contains(i.getNotes()));
		}
	}
	
	@Test
	public void testExGetContacts(){ // TEST FOR INVALID ID
		cm.addNewContact("Harry","Likes a Drink");  // Harry to be Found with this get method
		int id1 = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		cm.addNewContact("Paul","policeman"); // Padding for the Contact List
		int id2 = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		cm.addNewContact("Portia","sweet girl"); // Padding for the Contact List
		int id3 = cm.getLastIdUpdate(); // Retrieve the randomly generated ID
		
		ex.expect(IllegalArgumentException.class);
		cm.getContacts(id1,id2,id3,999);
	}
	
	@Test
	// getContacts(String name)
	// ------------------------
	public void testGetContactsNames(){
		 cm.addNewContact("Harry","Likes a Drink");
		 cm.addNewContact("Jill","Likes White Wine");
		 cm.addNewContact("Jill","Likes Red Wine");
		 cm.addNewContact("Jack", "Teetotal");
		 cm.addNewContact("Jim", "BUSY MAN");
		 cm.addNewContact("Sophie", "Angel");
		 cm.addNewContact("Alan", "Big Guy");
		 cm.addNewContact("Guy", "Slim Guy");
		 cm.addNewContact("Mark", "Shifty");
		 cm.addNewContact("Jill","Likes Whisky");
		
		 
		 List<String> names = new ArrayList<String>();
			names.add("Jill");
			names.add("Jill");
			names.add("Jill");
			
			Set<Contact> foundContacts = cm.getContacts("Jill");	
		 for(Contact i : foundContacts){
			assertTrue("testGetContacts - Same Names", names.contains(i.getName()));
		 }
	}
	
	 @Test
	 public void testExGetContactsNames(){
		 ex.expect(IllegalArgumentException.class);
		 String s = null;
		 cm.getContacts(s);
	 }
	 		
	@Test
	// Test for getFutureMeetingList(Contact)
	// --------------------------------------
	public void testGetFutureMeetingContact(){
		
		 cm.addNewContact("Harry","Likes a Drink");   //add contacts to ContactManager & get IDs
		 int idHarry = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes White Wine");
		 int idJill1 = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes Red Wine");
		 int idJill2 = cm.getLastIdUpdate();
		 cm.addNewContact("Jack", "Teetotal");
		 cm.addNewContact("Jim", "BUSY MAN");
		 int idJim = cm.getLastIdUpdate();
		 cm.addNewContact("Sophie", "Angel");
		 int idSophie = cm.getLastIdUpdate();
		 
		 Set<Contact> harrySet = cm.getContacts(idHarry);  // Create a Contact for Harry
		 Set<Contact> jimSet= cm.getContacts(idJim);  // Create a Contact for Jim
		 
		 Contact harry=null;
		 for(Contact i : harrySet){
			 harry = i;
		 }
		 Contact jim = null;
		 for(Contact i : jimSet){
			 jim = i;
		 }
		 
		 jimJillSet = cm.getContacts(idJim, idJill1, idJill2);  // Create Contact Sets for meetings
		 harrySophieSet = cm.getContacts(idHarry, idSophie);
		
		 cm.addNewPastMeeting(jimJillSet, calPast,"Meeting went well");  // add BAD Past meeting to ContactManager
		 cm.addFutureMeeting(jimJillSet, mar15);  // add Future meetings to ContactManager
		 cm.addFutureMeeting(jimJillSet, apr15);
		 cm.addFutureMeeting(jimJillSet, may15);
		 cm.addFutureMeeting(jimJillSet, jun15);
		 cm.addFutureMeeting(harrySophieSet, jul15);
		 cm.addFutureMeeting(harrySophieSet, aug15);
		 
		 jimMeetingList = new ArrayList<Calendar>();    // Create a Test List for Contacts Jim, harry
		 jimMeetingList.add(mar15);
		 jimMeetingList.add(apr15);
		 jimMeetingList.add(may15);
		 jimMeetingList.add(jun15);
		  
		 harryMeetingList = new ArrayList<Calendar>(); // Incorrect Date Order
		 harryMeetingList.add(aug15);
		 harryMeetingList.add(jul15);
		 
		 
		 List<Meeting> findJimList = cm.getFutureMeetingList(jim);
		 List<Meeting> findHarryList = cm.getFutureMeetingList(harry);
		 
		 /*
		 Calendar cal;
		 SimpleDateFormat myDateFormat = new SimpleDateFormat("dd/MM/yy hh:mm");
		 for(int i=0; i<4; i++){
			 cal = findJimList.get(i).getDate();
			 myDateFormat.setCalendar(cal);
			 String s = myDateFormat.format(cal.getTime());
			 System.out.println("Jim Found " + s);
		 }
		 
		 for(int i=0; i<4; i++){
			 cal = jimMeetingList.get(i);
			 myDateFormat.setCalendar(cal);
			 String s = myDateFormat.format(cal.getTime());
			 System.out.println("Jim Set " + s);
		 }
		 */
		 
		 int k=0;
		 for(Meeting i : findJimList){
			 assertTrue("test getFutureMeeting(Contact) for Jim: ", jimMeetingList.contains(i.getDate()));
			 assertTrue("test Date Order getFutureMeeting(Contact) for Jim: ", jimMeetingList.get(k).equals(i.getDate()));
			 k++;
		 }
		 k=0;
		 for(Meeting i : findHarryList){
			 assertTrue("test getFutureMeeting(Contact) for Harry: ", harryMeetingList.contains(i.getDate()));
			 assertFalse("test Wrong Date Order getFutureMeeting(Contact) for Harry: ", harryMeetingList.get(k).equals(i.getDate()));
			 k++;
		 }
		 
	}
	
	@Test
	public void testEXGetFutureMeetingContact(){
		ex.expect(IllegalArgumentException.class);
		cm.getFutureMeetingList(gertrude);
	}
	
	@Test
	// getFutureMeetingList(Calendar)
	// ------------------------------
	public void testgetFutureMeetingListContact(){
		
		cm.addNewContact("Harry","Likes a Drink");   //add contacts to ContactManager & get IDs
		 int idHarry = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes White Wine");
		 int idJill1 = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes Red Wine");
		 int idJill2 = cm.getLastIdUpdate();
		 cm.addNewContact("Jack", "Teetotal");
		 cm.addNewContact("Jim", "BUSY MAN");
		 int idJim = cm.getLastIdUpdate();
		 cm.addNewContact("Sophie", "Angel");
		 int idSophie = cm.getLastIdUpdate();
		 
		 
		 jimJillSet = cm.getContacts(idJim, idJill1, idJill2);  // Create Contact Sets for meetings
		 harrySophieSet = cm.getContacts(idHarry, idSophie);
		
		
		 cm.addNewPastMeeting(jimJillSet, calPast,"Meeting went well");  // add BAD Past meeting to ContactManager
		 cm.addFutureMeeting(jimJillSet, mar15);  // add Future meetings to ContactManager
		 cm.addFutureMeeting(harrySophieSet, mar15);
		 cm.addFutureMeeting(jimJillSet, apr15);
		 cm.addFutureMeeting(jimJillSet, may15);
		 cm.addFutureMeeting(jimJillSet, jun15);
		 cm.addFutureMeeting(harrySophieSet, jun15);
		 cm.addFutureMeeting(harrySophieSet, aug15);
		 
		 List<Calendar> mar15MeetingList = new ArrayList<Calendar>();    // Create a Test List for Mar 15
		 mar15MeetingList.add(mar15);
		 mar15MeetingList.add(mar15);
		  
		 List<Calendar> jun15MeetingList = new ArrayList<Calendar>(); // Create a Test List for Jun 15
		 jun15MeetingList.add(jun15);
		 jun15MeetingList.add(jun15);
		 
		 
		 List<Meeting> findMar15Meetings = cm.getFutureMeetingList(mar15);
		 List<Meeting> findJun15Meetings = cm.getFutureMeetingList(jun15);
		 
		 
		 
		 for(Meeting i : findMar15Meetings){
			 assertTrue("test getFutureMeeting(Calendar) for Mar15: ", mar15MeetingList.contains(i.getDate()));
		 }
		 
		 for(Meeting i : findJun15Meetings){
			 assertTrue("test getFutureMeeting(Calendar) for Jun15: ", jun15MeetingList.contains(i.getDate()));
		 }
	}
	
	@Test
	public void testExgetFutureMeetingListContact(){
		List<Meeting> noMeetings = cm.getFutureMeetingList(dudDate);
		assertEquals("test getFutureMeeting(Calendar) for DudDate: ", 0, noMeetings.size() ); // Test for No Entries
	}
	// ££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££££
	
	@Test
	// Test for getPASTeMeetingList(Contact)
	// --------------------------------------
	public void testGetPastMeetingContact(){
		
		 cm.addNewContact("Harry","Likes a Drink");   //add contacts to ContactManager & get IDs
		 int idHarry = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes White Wine");
		 int idJill1 = cm.getLastIdUpdate();
		 cm.addNewContact("Jill","Likes Red Wine");
		 int idJill2 = cm.getLastIdUpdate();
		 cm.addNewContact("Jack", "Teetotal");
		 cm.addNewContact("Jim", "BUSY MAN");
		 int idJim = cm.getLastIdUpdate();
		 cm.addNewContact("Sophie", "Angel");
		 int idSophie = cm.getLastIdUpdate();
		 
		 Set<Contact> harrySet = cm.getContacts(idHarry);  // Create a Contact for Harry
		 Set<Contact> jimSet= cm.getContacts(idJim);  // Create a Contact for Jim
		 
		 Contact harry=null;
		 for(Contact i : harrySet){
			 harry = i;
		 }
		 Contact jim = null;
		 for(Contact i : jimSet){
			 jim = i;
		 }
		 
		 jimJillSet = cm.getContacts(idJim, idJill1, idJill2);  // Create Contact Sets for meetings
		 harrySophieSet = cm.getContacts(idHarry, idSophie);
		
		 cm.addFutureMeeting(jimJillSet, calFut);  // add BAD Past meeting to ContactManager
		 cm.addNewPastMeeting(jimJillSet, mar14, "Meeting went well");  // add Future meetings to ContactManager
		 cm.addNewPastMeeting(jimJillSet, apr14,"Meeting went well");
		 cm.addNewPastMeeting(jimJillSet, may14, "Meeting went well");
		 cm.addNewPastMeeting(jimJillSet, jun14, "Meeting went well");
		 cm.addNewPastMeeting(harrySophieSet, jul14, "Meeting went well");
		 cm.addNewPastMeeting(harrySophieSet, aug14, "Meeting went well");
		 
		 jimMeetingList = new ArrayList<Calendar>();    // Create a Test List for Contacts Jim, harry
		 jimMeetingList.add(mar14);
		 jimMeetingList.add(apr14);
		 jimMeetingList.add(may14);
		 jimMeetingList.add(jun14);
		  
		 harryMeetingList = new ArrayList<Calendar>(); // Incorrect Date Order
		 harryMeetingList.add(aug14);
		 harryMeetingList.add(jul14);
		 
		 
		 List<Meeting> findJimList = cm.getFutureMeetingList(jim);
		 List<Meeting> findHarryList = cm.getFutureMeetingList(harry);
		 
		 /*
		 Calendar cal;
		 SimpleDateFormat myDateFormat = new SimpleDateFormat("dd/MM/yy hh:mm");
		 for(int i=0; i<4; i++){
			 cal = findJimList.get(i).getDate();
			 myDateFormat.setCalendar(cal);
			 String s = myDateFormat.format(cal.getTime());
			 System.out.println("Jim Found " + s);
		 }
		 
		 for(int i=0; i<4; i++){
			 cal = jimMeetingList.get(i);
			 myDateFormat.setCalendar(cal);
			 String s = myDateFormat.format(cal.getTime());
			 System.out.println("Jim Set " + s);
		 }
		 */
		 
		 int k=0;
		 for(Meeting i : findJimList){
			 assertTrue("test getPastMeeting(Contact) for Jim: ", jimMeetingList.contains(i.getDate()));
			 assertTrue("test Date Order getPastMeeting(Contact) for Jim: ", jimMeetingList.get(k).equals(i.getDate()));
			 k++;
		 }
		 k=0;
		 for(Meeting i : findHarryList){
			 assertTrue("test getPastMeeting(Contact) for Harry: ", harryMeetingList.contains(i.getDate()));
			 assertFalse("test Wrong Date Order getPastMeeting(Contact) for Harry: ", harryMeetingList.get(k).equals(i.getDate()));
			 k++;
		 }
	}
	
	@Test
	public void testEXGetPastMeetingContact(){
		ex.expect(IllegalArgumentException.class);
		cm.getFutureMeetingList(gertrude);
	}
		
	
	
	
	
	
	
}
