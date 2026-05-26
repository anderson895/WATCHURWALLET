package b4a.example;


import anywheresoftware.b4a.B4AMenuItem;
import android.app.Activity;
import android.os.Bundle;
import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.B4AActivity;
import anywheresoftware.b4a.ObjectWrapper;
import anywheresoftware.b4a.objects.ActivityWrapper;
import java.lang.reflect.InvocationTargetException;
import anywheresoftware.b4a.B4AUncaughtException;
import anywheresoftware.b4a.debug.*;
import java.lang.ref.WeakReference;

public class home extends Activity implements B4AActivity{
	public static home mostCurrent;
	static boolean afterFirstLayout;
	static boolean isFirst = true;
    private static boolean processGlobalsRun = false;
	BALayout layout;
	public static BA processBA;
	BA activityBA;
    ActivityWrapper _activity;
    java.util.ArrayList<B4AMenuItem> menuItems;
	public static final boolean fullScreen = false;
	public static final boolean includeTitle = false;
    public static WeakReference<Activity> previousOne;
    public static boolean dontPause;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        mostCurrent = this;
		if (processBA == null) {
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.home");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (home).");
				p.finish();
			}
		}
        processBA.setActivityPaused(true);
        processBA.runHook("oncreate", this, null);
		if (!includeTitle) {
        	this.getWindow().requestFeature(android.view.Window.FEATURE_NO_TITLE);
        }
        if (fullScreen) {
        	getWindow().setFlags(android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN,   
        			android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
		
        processBA.sharedProcessBA.activityBA = null;
		layout = new BALayout(this);
		setContentView(layout);
		afterFirstLayout = false;
        WaitForLayout wl = new WaitForLayout();
        if (anywheresoftware.b4a.objects.ServiceHelper.StarterHelper.startFromActivity(this, processBA, wl, false))
		    BA.handler.postDelayed(wl, 5);

	}
	static class WaitForLayout implements Runnable {
		public void run() {
			if (afterFirstLayout)
				return;
			if (mostCurrent == null)
				return;
            
			if (mostCurrent.layout.getWidth() == 0) {
				BA.handler.postDelayed(this, 5);
				return;
			}
			mostCurrent.layout.getLayoutParams().height = mostCurrent.layout.getHeight();
			mostCurrent.layout.getLayoutParams().width = mostCurrent.layout.getWidth();
			afterFirstLayout = true;
			mostCurrent.afterFirstLayout();
		}
	}
	private void afterFirstLayout() {
        if (this != mostCurrent)
			return;
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.home");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.home", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (home) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (home) Resume **");
        processBA.raiseEvent(null, "activity_resume");
        if (android.os.Build.VERSION.SDK_INT >= 11) {
			try {
				android.app.Activity.class.getMethod("invalidateOptionsMenu").invoke(this,(Object[]) null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	public void addMenuItem(B4AMenuItem item) {
		if (menuItems == null)
			menuItems = new java.util.ArrayList<B4AMenuItem>();
		menuItems.add(item);
	}
	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		super.onCreateOptionsMenu(menu);
        try {
            if (processBA.subExists("activity_actionbarhomeclick")) {
                Class.forName("android.app.ActionBar").getMethod("setHomeButtonEnabled", boolean.class).invoke(
                    getClass().getMethod("getActionBar").invoke(this), true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (processBA.runHook("oncreateoptionsmenu", this, new Object[] {menu}))
            return true;
		if (menuItems == null)
			return false;
		for (B4AMenuItem bmi : menuItems) {
			android.view.MenuItem mi = menu.add(bmi.title);
			if (bmi.drawable != null)
				mi.setIcon(bmi.drawable);
            if (android.os.Build.VERSION.SDK_INT >= 11) {
				try {
                    if (bmi.addToBar) {
				        android.view.MenuItem.class.getMethod("setShowAsAction", int.class).invoke(mi, 1);
                    }
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			mi.setOnMenuItemClickListener(new B4AMenuItemsClickListener(bmi.eventName.toLowerCase(BA.cul)));
		}
        
		return true;
	}   
 @Override
 public boolean onOptionsItemSelected(android.view.MenuItem item) {
    if (item.getItemId() == 16908332) {
        processBA.raiseEvent(null, "activity_actionbarhomeclick");
        return true;
    }
    else
        return super.onOptionsItemSelected(item); 
}
@Override
 public boolean onPrepareOptionsMenu(android.view.Menu menu) {
    super.onPrepareOptionsMenu(menu);
    processBA.runHook("onprepareoptionsmenu", this, new Object[] {menu});
    return true;
    
 }
 protected void onStart() {
    super.onStart();
    processBA.runHook("onstart", this, null);
}
 protected void onStop() {
    super.onStop();
    processBA.runHook("onstop", this, null);
}
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       if (processBA.subExists("activity_windowfocuschanged"))
           processBA.raiseEvent2(null, true, "activity_windowfocuschanged", false, hasFocus);
    }
	private class B4AMenuItemsClickListener implements android.view.MenuItem.OnMenuItemClickListener {
		private final String eventName;
		public B4AMenuItemsClickListener(String eventName) {
			this.eventName = eventName;
		}
		public boolean onMenuItemClick(android.view.MenuItem item) {
			processBA.raiseEventFromUI(item.getTitle(), eventName + "_click");
			return true;
		}
	}
    public static Class<?> getObject() {
		return home.class;
	}
    private Boolean onKeySubExist = null;
    private Boolean onKeyUpSubExist = null;
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeydown", this, new Object[] {keyCode, event}))
            return true;
		if (onKeySubExist == null)
			onKeySubExist = processBA.subExists("activity_keypress");
		if (onKeySubExist) {
			if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK &&
					android.os.Build.VERSION.SDK_INT >= 18) {
				HandleKeyDelayed hk = new HandleKeyDelayed();
				hk.kc = keyCode;
				BA.handler.post(hk);
				return true;
			}
			else {
				boolean res = new HandleKeyDelayed().runDirectly(keyCode);
				if (res)
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
	private class HandleKeyDelayed implements Runnable {
		int kc;
		public void run() {
			runDirectly(kc);
		}
		public boolean runDirectly(int keyCode) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keypress", false, keyCode);
			if (res == null || res == true) {
                return true;
            }
            else if (keyCode == anywheresoftware.b4a.keywords.constants.KeyCodes.KEYCODE_BACK) {
				finish();
				return true;
			}
            return false;
		}
		
	}
    @Override
	public boolean onKeyUp(int keyCode, android.view.KeyEvent event) {
        if (processBA.runHook("onkeyup", this, new Object[] {keyCode, event}))
            return true;
		if (onKeyUpSubExist == null)
			onKeyUpSubExist = processBA.subExists("activity_keyup");
		if (onKeyUpSubExist) {
			Boolean res =  (Boolean)processBA.raiseEvent2(_activity, false, "activity_keyup", false, keyCode);
			if (res == null || res == true)
				return true;
		}
		return super.onKeyUp(keyCode, event);
	}
	@Override
	public void onNewIntent(android.content.Intent intent) {
        super.onNewIntent(intent);
		this.setIntent(intent);
        processBA.runHook("onnewintent", this, new Object[] {intent});
	}
    @Override 
	public void onPause() {
		super.onPause();
        if (_activity == null)
            return;
        if (this != mostCurrent)
			return;
		anywheresoftware.b4a.Msgbox.dismiss(true);
        if (!dontPause)
            BA.LogInfo("** Activity (home) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (home) Pause event (activity is not paused). **");
        if (mostCurrent != null)
            processBA.raiseEvent2(_activity, true, "activity_pause", false, activityBA.activity.isFinishing());		
        if (!dontPause) {
            processBA.setActivityPaused(true);
            mostCurrent = null;
        }

        if (!activityBA.activity.isFinishing())
			previousOne = new WeakReference<Activity>(this);
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        processBA.runHook("onpause", this, null);
	}

	@Override
	public void onDestroy() {
        super.onDestroy();
		previousOne = null;
        processBA.runHook("ondestroy", this, null);
	}
    @Override 
	public void onResume() {
		super.onResume();
        mostCurrent = this;
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (activityBA != null) { //will be null during activity create (which waits for AfterLayout).
        	ResumeMessage rm = new ResumeMessage(mostCurrent);
        	BA.handler.post(rm);
        }
        processBA.runHook("onresume", this, null);
	}
    private static class ResumeMessage implements Runnable {
    	private final WeakReference<Activity> activity;
    	public ResumeMessage(Activity activity) {
    		this.activity = new WeakReference<Activity>(activity);
    	}
		public void run() {
            home mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (home) Resume **");
            if (mc != mostCurrent)
                return;
		    processBA.raiseEvent(mc._activity, "activity_resume", (Object[])null);
		}
    }
	@Override
	protected void onActivityResult(int requestCode, int resultCode,
	      android.content.Intent data) {
		processBA.onActivityResult(requestCode, resultCode, data);
        processBA.runHook("onactivityresult", this, new Object[] {requestCode, resultCode});
	}
	private static void initializeGlobals() {
		processBA.raiseEvent2(null, true, "globals", false, (Object[])null);
	}
    public void onRequestPermissionsResult(int requestCode,
        String permissions[], int[] grantResults) {
        for (int i = 0;i < permissions.length;i++) {
            Object[] o = new Object[] {permissions[i], grantResults[i] == 0};
            processBA.raiseEventFromDifferentThread(null,null, 0, "activity_permissionresult", true, o);
        }
            
    }



public static void initializeProcessGlobals() {
             try {
                Class.forName(BA.applicationContext.getPackageName() + ".main").getMethod("initializeProcessGlobals").invoke(null, null);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
}
public anywheresoftware.b4a.keywords.Common __c = null;
public static anywheresoftware.b4a.objects.collections.List _expenselist = null;
public static anywheresoftware.b4a.objects.collections.List _categorylist = null;
public static anywheresoftware.b4a.sql.SQL _sql = null;
public anywheresoftware.b4a.objects.PanelWrapper _pnlmenu = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnmenu = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtstudentsname = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtallowance = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtdate = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtspent = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtbalance = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtamountexpenses = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spinnercategory = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnacceptexpenses = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtamountgoal = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnacceptgoal = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtgoal = null;
public static double _totalspent = 0;
public static double _balance = 0;
public static double _allowance = 0;
public static double _goalamount = 0;
public static String _goalname = "";
public b4a.example.dateutils _dateutils = null;
public b4a.example.main _main = null;
public b4a.example.signup _signup = null;
public b4a.example.myacc _myacc = null;
public b4a.example.expenses _expenses = null;
public b4a.example.goal _goal = null;
public b4a.example.account _account = null;
public b4a.example.admin _admin = null;
public b4a.example.editexpenses _editexpenses = null;
public b4a.example.editgoals _editgoals = null;
public b4a.example.dashboard _dashboard = null;
public b4a.example.starter _starter = null;
public b4a.example.xuiviewsutils _xuiviewsutils = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="home";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=1507328;
 //BA.debugLineNum = 1507328;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=1507329;
 //BA.debugLineNum = 1507329;BA.debugLine="sql = Main.sql";
_sql = mostCurrent._main._sql /*anywheresoftware.b4a.sql.SQL*/ ;
RDebugUtils.currentLine=1507330;
 //BA.debugLineNum = 1507330;BA.debugLine="Activity.LoadLayout(\"layhome\")";
mostCurrent._activity.LoadLayout("layhome",mostCurrent.activityBA);
RDebugUtils.currentLine=1507331;
 //BA.debugLineNum = 1507331;BA.debugLine="pnlmenu.LoadLayout(\"laymenu\")";
mostCurrent._pnlmenu.LoadLayout("laymenu",mostCurrent.activityBA);
RDebugUtils.currentLine=1507332;
 //BA.debugLineNum = 1507332;BA.debugLine="pnlmenu.Visible = False";
mostCurrent._pnlmenu.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1507334;
 //BA.debugLineNum = 1507334;BA.debugLine="If ExpenseList.IsInitialized = False Then";
if (_expenselist.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=1507335;
 //BA.debugLineNum = 1507335;BA.debugLine="ExpenseList.Initialize";
_expenselist.Initialize();
 };
RDebugUtils.currentLine=1507338;
 //BA.debugLineNum = 1507338;BA.debugLine="If CategoryList.IsInitialized = False Then";
if (_categorylist.IsInitialized()==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=1507339;
 //BA.debugLineNum = 1507339;BA.debugLine="CategoryList.Initialize";
_categorylist.Initialize();
 };
RDebugUtils.currentLine=1507342;
 //BA.debugLineNum = 1507342;BA.debugLine="spinnercategory.Add(\"Foods & Groceries\")";
mostCurrent._spinnercategory.Add("Foods & Groceries");
RDebugUtils.currentLine=1507343;
 //BA.debugLineNum = 1507343;BA.debugLine="spinnercategory.Add(\"Transpo\")";
mostCurrent._spinnercategory.Add("Transpo");
RDebugUtils.currentLine=1507344;
 //BA.debugLineNum = 1507344;BA.debugLine="spinnercategory.Add(\"Rent\")";
mostCurrent._spinnercategory.Add("Rent");
RDebugUtils.currentLine=1507345;
 //BA.debugLineNum = 1507345;BA.debugLine="spinnercategory.Add(\"School Supplies\")";
mostCurrent._spinnercategory.Add("School Supplies");
RDebugUtils.currentLine=1507346;
 //BA.debugLineNum = 1507346;BA.debugLine="spinnercategory.Add(\"School Projects\")";
mostCurrent._spinnercategory.Add("School Projects");
RDebugUtils.currentLine=1507348;
 //BA.debugLineNum = 1507348;BA.debugLine="DateTime.DateFormat = \"MMMM dd, yyyy\"";
anywheresoftware.b4a.keywords.Common.DateTime.setDateFormat("MMMM dd, yyyy");
RDebugUtils.currentLine=1507349;
 //BA.debugLineNum = 1507349;BA.debugLine="txtdate.Text = DateTime.Date(DateTime.Now)";
mostCurrent._txtdate.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.DateTime.Date(anywheresoftware.b4a.keywords.Common.DateTime.getNow())));
RDebugUtils.currentLine=1507351;
 //BA.debugLineNum = 1507351;BA.debugLine="txtspent.Text = \"0.00\"";
mostCurrent._txtspent.setText(BA.ObjectToCharSequence("0.00"));
RDebugUtils.currentLine=1507352;
 //BA.debugLineNum = 1507352;BA.debugLine="txtbalance.Text = \"0.00\"";
mostCurrent._txtbalance.setText(BA.ObjectToCharSequence("0.00"));
RDebugUtils.currentLine=1507354;
 //BA.debugLineNum = 1507354;BA.debugLine="txtspent.Enabled = False";
mostCurrent._txtspent.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1507355;
 //BA.debugLineNum = 1507355;BA.debugLine="txtbalance.Enabled = False";
mostCurrent._txtbalance.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1507356;
 //BA.debugLineNum = 1507356;BA.debugLine="txtdate.Enabled = False";
mostCurrent._txtdate.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1507358;
 //BA.debugLineNum = 1507358;BA.debugLine="txtallowance.Background = Null";
mostCurrent._txtallowance.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
RDebugUtils.currentLine=1507359;
 //BA.debugLineNum = 1507359;BA.debugLine="txtamountexpenses.Background = Null";
mostCurrent._txtamountexpenses.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
RDebugUtils.currentLine=1507360;
 //BA.debugLineNum = 1507360;BA.debugLine="txtgoal.Background = Null";
mostCurrent._txtgoal.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
RDebugUtils.currentLine=1507361;
 //BA.debugLineNum = 1507361;BA.debugLine="txtamountgoal.Background = Null";
mostCurrent._txtamountgoal.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
RDebugUtils.currentLine=1507363;
 //BA.debugLineNum = 1507363;BA.debugLine="ShowAllowance";
_showallowance();
RDebugUtils.currentLine=1507364;
 //BA.debugLineNum = 1507364;BA.debugLine="End Sub";
return "";
}
public static String  _showallowance() throws Exception{
RDebugUtils.currentModule="home";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showallowance", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showallowance", null));}
anywheresoftware.b4a.sql.SQL.CursorWrapper _c = null;
RDebugUtils.currentLine=2097152;
 //BA.debugLineNum = 2097152;BA.debugLine="Sub ShowAllowance";
RDebugUtils.currentLine=2097153;
 //BA.debugLineNum = 2097153;BA.debugLine="Dim c As Cursor";
_c = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
RDebugUtils.currentLine=2097154;
 //BA.debugLineNum = 2097154;BA.debugLine="c = sql.ExecQuery(\"SELECT SUM(amount) FROM tblallo";
_c = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(_sql.ExecQuery("SELECT SUM(amount) FROM tblallowance")));
RDebugUtils.currentLine=2097155;
 //BA.debugLineNum = 2097155;BA.debugLine="If c.RowCount > 0 Then";
if (_c.getRowCount()>0) { 
RDebugUtils.currentLine=2097156;
 //BA.debugLineNum = 2097156;BA.debugLine="c.Position = 0";
_c.setPosition((int) (0));
RDebugUtils.currentLine=2097157;
 //BA.debugLineNum = 2097157;BA.debugLine="Allowance = c.GetDouble2(0)";
_allowance = _c.GetDouble2((int) (0));
 }else {
RDebugUtils.currentLine=2097159;
 //BA.debugLineNum = 2097159;BA.debugLine="Allowance = 0";
_allowance = 0;
 };
RDebugUtils.currentLine=2097161;
 //BA.debugLineNum = 2097161;BA.debugLine="c.Close";
_c.Close();
RDebugUtils.currentLine=2097163;
 //BA.debugLineNum = 2097163;BA.debugLine="Balance = Allowance - TotalSpent";
_balance = _allowance-_totalspent;
RDebugUtils.currentLine=2097165;
 //BA.debugLineNum = 2097165;BA.debugLine="txtallowance.Text = NumberFormat(Allowance,1,2)";
mostCurrent._txtallowance.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.NumberFormat(_allowance,(int) (1),(int) (2))));
RDebugUtils.currentLine=2097166;
 //BA.debugLineNum = 2097166;BA.debugLine="txtspent.Text = NumberFormat(TotalSpent,1,2)";
mostCurrent._txtspent.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.NumberFormat(_totalspent,(int) (1),(int) (2))));
RDebugUtils.currentLine=2097167;
 //BA.debugLineNum = 2097167;BA.debugLine="txtbalance.Text = NumberFormat(Balance,1,2)";
mostCurrent._txtbalance.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.NumberFormat(_balance,(int) (1),(int) (2))));
RDebugUtils.currentLine=2097168;
 //BA.debugLineNum = 2097168;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="home";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=1572864;
 //BA.debugLineNum = 1572864;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=1572865;
 //BA.debugLineNum = 1572865;BA.debugLine="ShowAllowance";
_showallowance();
RDebugUtils.currentLine=1572866;
 //BA.debugLineNum = 1572866;BA.debugLine="End Sub";
return "";
}
public static String  _btnacceptexpenses_click() throws Exception{
RDebugUtils.currentModule="home";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnacceptexpenses_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnacceptexpenses_click", null));}
double _expense = 0;
String _category = "";
RDebugUtils.currentLine=1966080;
 //BA.debugLineNum = 1966080;BA.debugLine="Private Sub btnacceptexpenses_Click";
RDebugUtils.currentLine=1966082;
 //BA.debugLineNum = 1966082;BA.debugLine="If txtamountexpenses.Text = \"\" Then";
if ((mostCurrent._txtamountexpenses.getText()).equals("")) { 
RDebugUtils.currentLine=1966083;
 //BA.debugLineNum = 1966083;BA.debugLine="ToastMessageShow(\"Please enter expense amount.\",";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Please enter expense amount."),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1966084;
 //BA.debugLineNum = 1966084;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=1966087;
 //BA.debugLineNum = 1966087;BA.debugLine="If IsNumber(txtamountexpenses.Text) = False Then";
if (anywheresoftware.b4a.keywords.Common.IsNumber(mostCurrent._txtamountexpenses.getText())==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=1966088;
 //BA.debugLineNum = 1966088;BA.debugLine="ToastMessageShow(\"Numbers only.\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Numbers only."),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1966089;
 //BA.debugLineNum = 1966089;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=1966092;
 //BA.debugLineNum = 1966092;BA.debugLine="Dim Expense As Double";
_expense = 0;
RDebugUtils.currentLine=1966093;
 //BA.debugLineNum = 1966093;BA.debugLine="Expense = txtamountexpenses.Text";
_expense = (double)(Double.parseDouble(mostCurrent._txtamountexpenses.getText()));
RDebugUtils.currentLine=1966095;
 //BA.debugLineNum = 1966095;BA.debugLine="If Expense > Balance Then";
if (_expense>_balance) { 
RDebugUtils.currentLine=1966096;
 //BA.debugLineNum = 1966096;BA.debugLine="ToastMessageShow(\"Not enough balance.\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Not enough balance."),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1966097;
 //BA.debugLineNum = 1966097;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=1966100;
 //BA.debugLineNum = 1966100;BA.debugLine="TotalSpent = TotalSpent + Expense";
_totalspent = _totalspent+_expense;
RDebugUtils.currentLine=1966102;
 //BA.debugLineNum = 1966102;BA.debugLine="txtspent.Text = NumberFormat(TotalSpent,1,2)";
mostCurrent._txtspent.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.NumberFormat(_totalspent,(int) (1),(int) (2))));
RDebugUtils.currentLine=1966104;
 //BA.debugLineNum = 1966104;BA.debugLine="Balance = Allowance - TotalSpent";
_balance = _allowance-_totalspent;
RDebugUtils.currentLine=1966106;
 //BA.debugLineNum = 1966106;BA.debugLine="txtbalance.Text = NumberFormat(Balance,1,2)";
mostCurrent._txtbalance.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.NumberFormat(_balance,(int) (1),(int) (2))));
RDebugUtils.currentLine=1966108;
 //BA.debugLineNum = 1966108;BA.debugLine="Dim Category As String";
_category = "";
RDebugUtils.currentLine=1966109;
 //BA.debugLineNum = 1966109;BA.debugLine="Category = spinnercategory.SelectedItem";
_category = mostCurrent._spinnercategory.getSelectedItem();
RDebugUtils.currentLine=1966111;
 //BA.debugLineNum = 1966111;BA.debugLine="ExpenseList.Add(Expense)";
_expenselist.Add((Object)(_expense));
RDebugUtils.currentLine=1966112;
 //BA.debugLineNum = 1966112;BA.debugLine="CategoryList.Add(Category)";
_categorylist.Add((Object)(_category));
RDebugUtils.currentLine=1966114;
 //BA.debugLineNum = 1966114;BA.debugLine="ToastMessageShow(\"Expense added to \" & Category,";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Expense added to "+_category),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1966116;
 //BA.debugLineNum = 1966116;BA.debugLine="txtamountexpenses.Text = \"\"";
mostCurrent._txtamountexpenses.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=1966118;
 //BA.debugLineNum = 1966118;BA.debugLine="ShowAllowance";
_showallowance();
RDebugUtils.currentLine=1966119;
 //BA.debugLineNum = 1966119;BA.debugLine="End Sub";
return "";
}
public static String  _btnmenu_click() throws Exception{
RDebugUtils.currentModule="home";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnmenu_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnmenu_click", null));}
RDebugUtils.currentLine=1638400;
 //BA.debugLineNum = 1638400;BA.debugLine="Private Sub btnmenu_Click";
RDebugUtils.currentLine=1638401;
 //BA.debugLineNum = 1638401;BA.debugLine="pnlmenu.Visible = True";
mostCurrent._pnlmenu.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=1638402;
 //BA.debugLineNum = 1638402;BA.debugLine="End Sub";
return "";
}
public static String  _labelaccount_click() throws Exception{
RDebugUtils.currentModule="home";
if (Debug.shouldDelegate(mostCurrent.activityBA, "labelaccount_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "labelaccount_click", null));}
RDebugUtils.currentLine=1900544;
 //BA.debugLineNum = 1900544;BA.debugLine="Private Sub labelaccount_Click";
RDebugUtils.currentLine=1900545;
 //BA.debugLineNum = 1900545;BA.debugLine="pnlmenu.Visible = False";
mostCurrent._pnlmenu.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1900546;
 //BA.debugLineNum = 1900546;BA.debugLine="StartActivity(account)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._account.getObject()));
RDebugUtils.currentLine=1900547;
 //BA.debugLineNum = 1900547;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=1900548;
 //BA.debugLineNum = 1900548;BA.debugLine="End Sub";
return "";
}
public static String  _labelexpenses_click() throws Exception{
RDebugUtils.currentModule="home";
if (Debug.shouldDelegate(mostCurrent.activityBA, "labelexpenses_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "labelexpenses_click", null));}
RDebugUtils.currentLine=1769472;
 //BA.debugLineNum = 1769472;BA.debugLine="Private Sub labelexpenses_Click";
RDebugUtils.currentLine=1769473;
 //BA.debugLineNum = 1769473;BA.debugLine="pnlmenu.Visible = False";
mostCurrent._pnlmenu.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1769474;
 //BA.debugLineNum = 1769474;BA.debugLine="StartActivity(expenses)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._expenses.getObject()));
RDebugUtils.currentLine=1769475;
 //BA.debugLineNum = 1769475;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=1769476;
 //BA.debugLineNum = 1769476;BA.debugLine="End Sub";
return "";
}
public static String  _labelgoal_click() throws Exception{
RDebugUtils.currentModule="home";
if (Debug.shouldDelegate(mostCurrent.activityBA, "labelgoal_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "labelgoal_click", null));}
RDebugUtils.currentLine=1835008;
 //BA.debugLineNum = 1835008;BA.debugLine="Private Sub labelgoal_Click";
RDebugUtils.currentLine=1835009;
 //BA.debugLineNum = 1835009;BA.debugLine="pnlmenu.Visible = False";
mostCurrent._pnlmenu.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1835010;
 //BA.debugLineNum = 1835010;BA.debugLine="StartActivity(goal)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._goal.getObject()));
RDebugUtils.currentLine=1835011;
 //BA.debugLineNum = 1835011;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=1835012;
 //BA.debugLineNum = 1835012;BA.debugLine="End Sub";
return "";
}
public static String  _labelhome_click() throws Exception{
RDebugUtils.currentModule="home";
if (Debug.shouldDelegate(mostCurrent.activityBA, "labelhome_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "labelhome_click", null));}
RDebugUtils.currentLine=1703936;
 //BA.debugLineNum = 1703936;BA.debugLine="Private Sub labelhome_Click";
RDebugUtils.currentLine=1703937;
 //BA.debugLineNum = 1703937;BA.debugLine="pnlmenu.Visible = False";
mostCurrent._pnlmenu.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=1703938;
 //BA.debugLineNum = 1703938;BA.debugLine="End Sub";
return "";
}
public static String  _txtallowance_textchanged(String _old,String _new) throws Exception{
RDebugUtils.currentModule="home";
if (Debug.shouldDelegate(mostCurrent.activityBA, "txtallowance_textchanged", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "txtallowance_textchanged", new Object[] {_old,_new}));}
RDebugUtils.currentLine=2031616;
 //BA.debugLineNum = 2031616;BA.debugLine="Private Sub txtallowance_TextChanged (Old As Strin";
RDebugUtils.currentLine=2031617;
 //BA.debugLineNum = 2031617;BA.debugLine="If New = \"\" Then";
if ((_new).equals("")) { 
RDebugUtils.currentLine=2031618;
 //BA.debugLineNum = 2031618;BA.debugLine="txtbalance.Text = \"0.00\"";
mostCurrent._txtbalance.setText(BA.ObjectToCharSequence("0.00"));
RDebugUtils.currentLine=2031619;
 //BA.debugLineNum = 2031619;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=2031622;
 //BA.debugLineNum = 2031622;BA.debugLine="If IsNumber(New) Then";
if (anywheresoftware.b4a.keywords.Common.IsNumber(_new)) { 
RDebugUtils.currentLine=2031623;
 //BA.debugLineNum = 2031623;BA.debugLine="Allowance = New";
_allowance = (double)(Double.parseDouble(_new));
RDebugUtils.currentLine=2031624;
 //BA.debugLineNum = 2031624;BA.debugLine="Balance = Allowance - TotalSpent";
_balance = _allowance-_totalspent;
RDebugUtils.currentLine=2031625;
 //BA.debugLineNum = 2031625;BA.debugLine="txtbalance.Text = NumberFormat(Balance,1,2)";
mostCurrent._txtbalance.setText(BA.ObjectToCharSequence(anywheresoftware.b4a.keywords.Common.NumberFormat(_balance,(int) (1),(int) (2))));
 };
RDebugUtils.currentLine=2031627;
 //BA.debugLineNum = 2031627;BA.debugLine="End Sub";
return "";
}
}