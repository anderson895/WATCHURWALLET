
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

public class main implements IRemote{
	public static main mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public main() {
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
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("main"), "b4a.example.main");
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
		pcBA = new PCBA(this, main.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _sql = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL");
public static RemoteObject _usernamee = RemoteObject.createImmutable("");
public static RemoteObject _fname = RemoteObject.createImmutable("");
public static RemoteObject _lname = RemoteObject.createImmutable("");
public static RemoteObject _email = RemoteObject.createImmutable("");
public static RemoteObject _txtusername = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _txtpassword = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _btnlogin = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _btnsignup = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _dateutils = RemoteObject.declareNull("b4a.example.dateutils");
public static b4a.example.signup _signup = null;
public static b4a.example.myacc _myacc = null;
public static b4a.example.home _home = null;
public static b4a.example.expenses _expenses = null;
public static b4a.example.goal _goal = null;
public static b4a.example.account _account = null;
public static b4a.example.admin _admin = null;
public static b4a.example.editexpenses _editexpenses = null;
public static b4a.example.editgoals _editgoals = null;
public static b4a.example.dashboard _dashboard = null;
public static b4a.example.starter _starter = null;
public static b4a.example.xuiviewsutils _xuiviewsutils = null;
  public Object[] GetGlobals() {
		return new Object[] {"account",Debug.moduleToString(b4a.example.account.class),"Activity",main.mostCurrent._activity,"admin",Debug.moduleToString(b4a.example.admin.class),"btnLogin",main.mostCurrent._btnlogin,"btnSignup",main.mostCurrent._btnsignup,"dashboard",Debug.moduleToString(b4a.example.dashboard.class),"DateUtils",main.mostCurrent._dateutils,"editexpenses",Debug.moduleToString(b4a.example.editexpenses.class),"editgoals",Debug.moduleToString(b4a.example.editgoals.class),"email",main._email,"expenses",Debug.moduleToString(b4a.example.expenses.class),"fname",main._fname,"goal",Debug.moduleToString(b4a.example.goal.class),"home",Debug.moduleToString(b4a.example.home.class),"lname",main._lname,"myacc",Debug.moduleToString(b4a.example.myacc.class),"signup",Debug.moduleToString(b4a.example.signup.class),"sql",main._sql,"Starter",Debug.moduleToString(b4a.example.starter.class),"txtPassword",main.mostCurrent._txtpassword,"txtUsername",main.mostCurrent._txtusername,"usernamee",main._usernamee,"XUIViewsUtils",Debug.moduleToString(b4a.example.xuiviewsutils.class)};
}
}