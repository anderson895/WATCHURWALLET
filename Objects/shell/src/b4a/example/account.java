
package b4a.example;

import java.io.IOException;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.PCBA;
import anywheresoftware.b4a.pc.RDebug;
import anywheresoftware.b4a.pc.RemoteObject;
import anywheresoftware.b4a.pc.RDebug.IRemote;
import anywheresoftware.b4a.pc.Debug;
import anywheresoftware.b4a.pc.B4XTypes.B4XClass;
import anywheresoftware.b4a.pc.B4XTypes.DeviceClass;

public class account implements IRemote{
	public static account mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public account() {
		mostCurrent = this;
	}
    public RemoteObject getRemoteMe() {
        return remoteMe;    
    }
    
	public static void main (String[] args) throws Exception {
		new RDebug(args[0], Integer.parseInt(args[1]), Integer.parseInt(args[2]), args[3]);
		RDebug.INSTANCE.waitForTask();

	}
    static {
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("account"), "b4a.example.account");
	}

public boolean isSingleton() {
		return true;
	}
     public static RemoteObject getObject() {
		return myClass;
	 }

	public RemoteObject activityBA;
	public RemoteObject _activity;
    private PCBA pcBA;

	public PCBA create(Object[] args) throws ClassNotFoundException{
		processBA = (RemoteObject) args[1];
		activityBA = (RemoteObject) args[2];
		_activity = (RemoteObject) args[3];
        anywheresoftware.b4a.keywords.Common.Density = (Float)args[4];
        remoteMe = (RemoteObject) args[5];
		pcBA = new PCBA(this, account.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _sql = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL");
public static RemoteObject _pnlmenu = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _button1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _lblfullname = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _lblemail = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _lblusername = RemoteObject.declareNull("anywheresoftware.b4a.objects.LabelWrapper");
public static RemoteObject _btneditexpenses = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _listview1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.ListViewWrapper");
public static RemoteObject _edittext1 = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _dateutils = RemoteObject.declareNull("b4a.example.dateutils");
public static b4a.example.main _main = null;
public static b4a.example.signup _signup = null;
public static b4a.example.myacc _myacc = null;
public static b4a.example.home _home = null;
public static b4a.example.expenses _expenses = null;
public static b4a.example.goal _goal = null;
public static b4a.example.admin _admin = null;
public static b4a.example.editexpenses _editexpenses = null;
public static b4a.example.editgoals _editgoals = null;
public static b4a.example.dashboard _dashboard = null;
public static b4a.example.starter _starter = null;
public static b4a.example.xuiviewsutils _xuiviewsutils = null;
  public Object[] GetGlobals() {
		return new Object[] {"Activity",account.mostCurrent._activity,"admin",Debug.moduleToString(b4a.example.admin.class),"btneditexpenses",account.mostCurrent._btneditexpenses,"Button1",account.mostCurrent._button1,"dashboard",Debug.moduleToString(b4a.example.dashboard.class),"DateUtils",account.mostCurrent._dateutils,"editexpenses",Debug.moduleToString(b4a.example.editexpenses.class),"editgoals",Debug.moduleToString(b4a.example.editgoals.class),"EditText1",account.mostCurrent._edittext1,"expenses",Debug.moduleToString(b4a.example.expenses.class),"goal",Debug.moduleToString(b4a.example.goal.class),"home",Debug.moduleToString(b4a.example.home.class),"lblemail",account.mostCurrent._lblemail,"lblfullname",account.mostCurrent._lblfullname,"lblusername",account.mostCurrent._lblusername,"ListView1",account.mostCurrent._listview1,"Main",Debug.moduleToString(b4a.example.main.class),"myacc",Debug.moduleToString(b4a.example.myacc.class),"pnlmenu",account.mostCurrent._pnlmenu,"signup",Debug.moduleToString(b4a.example.signup.class),"sql",account._sql,"Starter",Debug.moduleToString(b4a.example.starter.class),"XUIViewsUtils",Debug.moduleToString(b4a.example.xuiviewsutils.class)};
}
}