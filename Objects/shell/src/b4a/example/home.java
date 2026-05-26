
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

public class home implements IRemote{
	public static home mostCurrent;
	public static RemoteObject processBA;
    public static boolean processGlobalsRun;
    public static RemoteObject myClass;
    public static RemoteObject remoteMe;
	public home() {
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
        anywheresoftware.b4a.pc.RapidSub.moduleToObject.put(new B4XClass("home"), "b4a.example.home");
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
		pcBA = new PCBA(this, home.class);
        main_subs_0.initializeProcessGlobals();
		return pcBA;
	}
public static RemoteObject __c = RemoteObject.declareNull("anywheresoftware.b4a.keywords.Common");
public static RemoteObject _expenselist = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
public static RemoteObject _categorylist = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
public static RemoteObject _sql = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL");
public static RemoteObject _pnlmenu = RemoteObject.declareNull("anywheresoftware.b4a.objects.PanelWrapper");
public static RemoteObject _btnmenu = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _txtstudentsname = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _txtallowance = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _txtdate = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _txtspent = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _txtbalance = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _txtamountexpenses = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _spinnercategory = RemoteObject.declareNull("anywheresoftware.b4a.objects.SpinnerWrapper");
public static RemoteObject _btnacceptexpenses = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _txtamountgoal = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _btnacceptgoal = RemoteObject.declareNull("anywheresoftware.b4a.objects.ButtonWrapper");
public static RemoteObject _txtgoal = RemoteObject.declareNull("anywheresoftware.b4a.objects.EditTextWrapper");
public static RemoteObject _totalspent = RemoteObject.createImmutable(0);
public static RemoteObject _balance = RemoteObject.createImmutable(0);
public static RemoteObject _allowance = RemoteObject.createImmutable(0);
public static RemoteObject _goalamount = RemoteObject.createImmutable(0);
public static RemoteObject _goalname = RemoteObject.createImmutable("");
public static RemoteObject _dateutils = RemoteObject.declareNull("b4a.example.dateutils");
public static b4a.example.main _main = null;
public static b4a.example.signup _signup = null;
public static b4a.example.myacc _myacc = null;
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
		return new Object[] {"account",Debug.moduleToString(b4a.example.account.class),"Activity",home.mostCurrent._activity,"admin",Debug.moduleToString(b4a.example.admin.class),"Allowance",home._allowance,"Balance",home._balance,"btnacceptexpenses",home.mostCurrent._btnacceptexpenses,"btnacceptgoal",home.mostCurrent._btnacceptgoal,"btnmenu",home.mostCurrent._btnmenu,"CategoryList",home._categorylist,"dashboard",Debug.moduleToString(b4a.example.dashboard.class),"DateUtils",home.mostCurrent._dateutils,"editexpenses",Debug.moduleToString(b4a.example.editexpenses.class),"editgoals",Debug.moduleToString(b4a.example.editgoals.class),"ExpenseList",home._expenselist,"expenses",Debug.moduleToString(b4a.example.expenses.class),"goal",Debug.moduleToString(b4a.example.goal.class),"GoalAmount",home._goalamount,"GoalName",home.mostCurrent._goalname,"Main",Debug.moduleToString(b4a.example.main.class),"myacc",Debug.moduleToString(b4a.example.myacc.class),"pnlmenu",home.mostCurrent._pnlmenu,"signup",Debug.moduleToString(b4a.example.signup.class),"spinnercategory",home.mostCurrent._spinnercategory,"sql",home._sql,"Starter",Debug.moduleToString(b4a.example.starter.class),"TotalSpent",home._totalspent,"txtallowance",home.mostCurrent._txtallowance,"txtamountexpenses",home.mostCurrent._txtamountexpenses,"txtamountgoal",home.mostCurrent._txtamountgoal,"txtbalance",home.mostCurrent._txtbalance,"txtdate",home.mostCurrent._txtdate,"txtgoal",home.mostCurrent._txtgoal,"txtspent",home.mostCurrent._txtspent,"txtstudentsname",home.mostCurrent._txtstudentsname,"XUIViewsUtils",Debug.moduleToString(b4a.example.xuiviewsutils.class)};
}
}