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

public class admin extends Activity implements B4AActivity{
	public static admin mostCurrent;
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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.admin");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (admin).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.admin");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.admin", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (admin) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (admin) Resume **");
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
		return admin.class;
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
            BA.LogInfo("** Activity (admin) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (admin) Pause event (activity is not paused). **");
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
            admin mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (admin) Resume **");
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
public static anywheresoftware.b4a.sql.SQL _sql = null;
public static anywheresoftware.b4a.sql.SQL.ResultSetWrapper _rs = null;
public b4a.example.b4xtable _b4xtable1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btndelete = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnupdate = null;
public anywheresoftware.b4a.agraham.dialogs.InputDialog _inpdlg = null;
public b4a.example.dateutils _dateutils = null;
public b4a.example.main _main = null;
public b4a.example.signup _signup = null;
public b4a.example.myacc _myacc = null;
public b4a.example.home _home = null;
public b4a.example.expenses _expenses = null;
public b4a.example.goal _goal = null;
public b4a.example.account _account = null;
public b4a.example.editexpenses _editexpenses = null;
public b4a.example.editgoals _editgoals = null;
public b4a.example.dashboard _dashboard = null;
public b4a.example.starter _starter = null;
public b4a.example.xuiviewsutils _xuiviewsutils = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="admin";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=4456448;
 //BA.debugLineNum = 4456448;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=4456449;
 //BA.debugLineNum = 4456449;BA.debugLine="If FirstTime Then";
if (_firsttime) { 
RDebugUtils.currentLine=4456450;
 //BA.debugLineNum = 4456450;BA.debugLine="sql.Initialize(File.DirInternal, \"saddbb.db\", Fa";
_sql.Initialize(anywheresoftware.b4a.keywords.Common.File.getDirInternal(),"saddbb.db",anywheresoftware.b4a.keywords.Common.False);
 };
RDebugUtils.currentLine=4456453;
 //BA.debugLineNum = 4456453;BA.debugLine="Activity.LoadLayout(\"AdminPanelLayout\")";
mostCurrent._activity.LoadLayout("AdminPanelLayout",mostCurrent.activityBA);
RDebugUtils.currentLine=4456455;
 //BA.debugLineNum = 4456455;BA.debugLine="B4XTable1.AddColumn(\"Username\", B4XTable1.COLUMN_";
mostCurrent._b4xtable1._addcolumn /*b4a.example.b4xtable._b4xtablecolumn*/ (null,"Username",mostCurrent._b4xtable1._column_type_text /*int*/ );
RDebugUtils.currentLine=4456456;
 //BA.debugLineNum = 4456456;BA.debugLine="B4XTable1.AddColumn(\"Password\", B4XTable1.COLUMN_";
mostCurrent._b4xtable1._addcolumn /*b4a.example.b4xtable._b4xtablecolumn*/ (null,"Password",mostCurrent._b4xtable1._column_type_text /*int*/ );
RDebugUtils.currentLine=4456457;
 //BA.debugLineNum = 4456457;BA.debugLine="B4XTable1.AddColumn(\"Role\", B4XTable1.COLUMN_TYPE";
mostCurrent._b4xtable1._addcolumn /*b4a.example.b4xtable._b4xtablecolumn*/ (null,"Role",mostCurrent._b4xtable1._column_type_text /*int*/ );
RDebugUtils.currentLine=4456459;
 //BA.debugLineNum = 4456459;BA.debugLine="ShowUsers";
_showusers();
RDebugUtils.currentLine=4456460;
 //BA.debugLineNum = 4456460;BA.debugLine="End Sub";
return "";
}
public static String  _showusers() throws Exception{
RDebugUtils.currentModule="admin";
if (Debug.shouldDelegate(mostCurrent.activityBA, "showusers", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "showusers", null));}
anywheresoftware.b4a.objects.collections.List _data = null;
Object[] _row = null;
RDebugUtils.currentLine=4653056;
 //BA.debugLineNum = 4653056;BA.debugLine="Sub ShowUsers";
RDebugUtils.currentLine=4653057;
 //BA.debugLineNum = 4653057;BA.debugLine="Dim data As List";
_data = new anywheresoftware.b4a.objects.collections.List();
RDebugUtils.currentLine=4653058;
 //BA.debugLineNum = 4653058;BA.debugLine="data.Initialize";
_data.Initialize();
RDebugUtils.currentLine=4653060;
 //BA.debugLineNum = 4653060;BA.debugLine="rs = sql.ExecQuery(\"SELECT * FROM tblusers\")";
_rs = (anywheresoftware.b4a.sql.SQL.ResultSetWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.ResultSetWrapper(), (android.database.Cursor)(_sql.ExecQuery("SELECT * FROM tblusers")));
RDebugUtils.currentLine=4653062;
 //BA.debugLineNum = 4653062;BA.debugLine="Do While rs.NextRow";
while (_rs.NextRow()) {
RDebugUtils.currentLine=4653063;
 //BA.debugLineNum = 4653063;BA.debugLine="Dim row(3) As Object";
_row = new Object[(int) (3)];
{
int d0 = _row.length;
for (int i0 = 0;i0 < d0;i0++) {
_row[i0] = new Object();
}
}
;
RDebugUtils.currentLine=4653064;
 //BA.debugLineNum = 4653064;BA.debugLine="row(0) = rs.GetString(\"username\")";
_row[(int) (0)] = (Object)(_rs.GetString("username"));
RDebugUtils.currentLine=4653065;
 //BA.debugLineNum = 4653065;BA.debugLine="row(1) = rs.GetString(\"password\")";
_row[(int) (1)] = (Object)(_rs.GetString("password"));
RDebugUtils.currentLine=4653066;
 //BA.debugLineNum = 4653066;BA.debugLine="row(2) = rs.GetString(\"role\")";
_row[(int) (2)] = (Object)(_rs.GetString("role"));
RDebugUtils.currentLine=4653067;
 //BA.debugLineNum = 4653067;BA.debugLine="data.Add(row)";
_data.Add((Object)(_row));
 }
;
RDebugUtils.currentLine=4653070;
 //BA.debugLineNum = 4653070;BA.debugLine="rs.Close";
_rs.Close();
RDebugUtils.currentLine=4653071;
 //BA.debugLineNum = 4653071;BA.debugLine="B4XTable1.SetData(data)";
mostCurrent._b4xtable1._setdata /*anywheresoftware.b4a.keywords.Common.ResumableSubWrapper*/ (null,_data);
RDebugUtils.currentLine=4653072;
 //BA.debugLineNum = 4653072;BA.debugLine="End Sub";
return "";
}
public static String  _activity_pause(boolean _userclosed) throws Exception{
RDebugUtils.currentModule="admin";
RDebugUtils.currentLine=4587520;
 //BA.debugLineNum = 4587520;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
RDebugUtils.currentLine=4587522;
 //BA.debugLineNum = 4587522;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="admin";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=4521984;
 //BA.debugLineNum = 4521984;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=4521985;
 //BA.debugLineNum = 4521985;BA.debugLine="ShowUsers";
_showusers();
RDebugUtils.currentLine=4521986;
 //BA.debugLineNum = 4521986;BA.debugLine="End Sub";
return "";
}
public static String  _btnback_click() throws Exception{
RDebugUtils.currentModule="admin";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnback_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnback_click", null));}
RDebugUtils.currentLine=4849664;
 //BA.debugLineNum = 4849664;BA.debugLine="Private Sub btnBack_Click";
RDebugUtils.currentLine=4849665;
 //BA.debugLineNum = 4849665;BA.debugLine="StartActivity(dashboard)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._dashboard.getObject()));
RDebugUtils.currentLine=4849666;
 //BA.debugLineNum = 4849666;BA.debugLine="End Sub";
return "";
}
public static String  _btndelete_click() throws Exception{
RDebugUtils.currentModule="admin";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btndelete_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btndelete_click", null));}
String _uname = "";
int _res = 0;
anywheresoftware.b4a.sql.SQL.CursorWrapper _c = null;
int _confirm = 0;
RDebugUtils.currentLine=4718592;
 //BA.debugLineNum = 4718592;BA.debugLine="Private Sub btnDelete_Click";
RDebugUtils.currentLine=4718594;
 //BA.debugLineNum = 4718594;BA.debugLine="Dim uname As String";
_uname = "";
RDebugUtils.currentLine=4718595;
 //BA.debugLineNum = 4718595;BA.debugLine="inpdlg.Input = \"\"";
mostCurrent._inpdlg.setInput("");
RDebugUtils.currentLine=4718597;
 //BA.debugLineNum = 4718597;BA.debugLine="Dim res As Int = inpdlg.Show(\"Enter username to d";
_res = mostCurrent._inpdlg.Show("Enter username to delete","Delete User","OK","Cancel","",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
RDebugUtils.currentLine=4718598;
 //BA.debugLineNum = 4718598;BA.debugLine="uname = inpdlg.Input";
_uname = mostCurrent._inpdlg.getInput();
RDebugUtils.currentLine=4718600;
 //BA.debugLineNum = 4718600;BA.debugLine="If res = DialogResponse.POSITIVE Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
RDebugUtils.currentLine=4718602;
 //BA.debugLineNum = 4718602;BA.debugLine="Dim c As Cursor";
_c = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
RDebugUtils.currentLine=4718603;
 //BA.debugLineNum = 4718603;BA.debugLine="c = sql.ExecQuery2(\"SELECT * FROM tblusers WHERE";
_c = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(_sql.ExecQuery2("SELECT * FROM tblusers WHERE username = ?",new String[]{_uname})));
RDebugUtils.currentLine=4718605;
 //BA.debugLineNum = 4718605;BA.debugLine="If c.RowCount > 0 Then";
if (_c.getRowCount()>0) { 
RDebugUtils.currentLine=4718607;
 //BA.debugLineNum = 4718607;BA.debugLine="Dim confirm As Int";
_confirm = 0;
RDebugUtils.currentLine=4718608;
 //BA.debugLineNum = 4718608;BA.debugLine="confirm = Msgbox2(\"Delete this user?\", \"Confirm";
_confirm = anywheresoftware.b4a.keywords.Common.Msgbox2(BA.ObjectToCharSequence("Delete this user?"),BA.ObjectToCharSequence("Confirm"),"Yes","","No",(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null),mostCurrent.activityBA);
RDebugUtils.currentLine=4718610;
 //BA.debugLineNum = 4718610;BA.debugLine="If confirm = DialogResponse.POSITIVE Then";
if (_confirm==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
RDebugUtils.currentLine=4718611;
 //BA.debugLineNum = 4718611;BA.debugLine="sql.ExecNonQuery2(\"DELETE FROM tblusers WHERE";
_sql.ExecNonQuery2("DELETE FROM tblusers WHERE username = ?",anywheresoftware.b4a.keywords.Common.ArrayToList(new String[]{_uname}));
RDebugUtils.currentLine=4718612;
 //BA.debugLineNum = 4718612;BA.debugLine="ShowUsers";
_showusers();
RDebugUtils.currentLine=4718613;
 //BA.debugLineNum = 4718613;BA.debugLine="ToastMessageShow(\"User Deleted\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("User Deleted"),anywheresoftware.b4a.keywords.Common.False);
 };
 }else {
RDebugUtils.currentLine=4718617;
 //BA.debugLineNum = 4718617;BA.debugLine="Msgbox(\"User not found\", \"\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("User not found"),BA.ObjectToCharSequence(""),mostCurrent.activityBA);
 };
 };
RDebugUtils.currentLine=4718621;
 //BA.debugLineNum = 4718621;BA.debugLine="End Sub";
return "";
}
public static String  _btnupdate_click() throws Exception{
RDebugUtils.currentModule="admin";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnupdate_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnupdate_click", null));}
String _uname = "";
int _res = 0;
anywheresoftware.b4a.sql.SQL.CursorWrapper _c = null;
String _newpass = "";
String _newrole = "";
RDebugUtils.currentLine=4784128;
 //BA.debugLineNum = 4784128;BA.debugLine="Private Sub btnUpdate_Click";
RDebugUtils.currentLine=4784130;
 //BA.debugLineNum = 4784130;BA.debugLine="Dim uname As String";
_uname = "";
RDebugUtils.currentLine=4784131;
 //BA.debugLineNum = 4784131;BA.debugLine="inpdlg.Input = \"\"";
mostCurrent._inpdlg.setInput("");
RDebugUtils.currentLine=4784133;
 //BA.debugLineNum = 4784133;BA.debugLine="Dim res As Int = inpdlg.Show(\"Enter username to u";
_res = mostCurrent._inpdlg.Show("Enter username to update","Update User","OK","Cancel","",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
RDebugUtils.currentLine=4784134;
 //BA.debugLineNum = 4784134;BA.debugLine="uname = inpdlg.Input";
_uname = mostCurrent._inpdlg.getInput();
RDebugUtils.currentLine=4784136;
 //BA.debugLineNum = 4784136;BA.debugLine="If res = DialogResponse.POSITIVE Then";
if (_res==anywheresoftware.b4a.keywords.Common.DialogResponse.POSITIVE) { 
RDebugUtils.currentLine=4784138;
 //BA.debugLineNum = 4784138;BA.debugLine="Dim c As Cursor";
_c = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
RDebugUtils.currentLine=4784139;
 //BA.debugLineNum = 4784139;BA.debugLine="c = sql.ExecQuery2(\"SELECT * FROM tblusers WHERE";
_c = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(_sql.ExecQuery2("SELECT * FROM tblusers WHERE username = ?",new String[]{_uname})));
RDebugUtils.currentLine=4784141;
 //BA.debugLineNum = 4784141;BA.debugLine="If c.RowCount > 0 Then";
if (_c.getRowCount()>0) { 
RDebugUtils.currentLine=4784143;
 //BA.debugLineNum = 4784143;BA.debugLine="inpdlg.Input = \"\"";
mostCurrent._inpdlg.setInput("");
RDebugUtils.currentLine=4784144;
 //BA.debugLineNum = 4784144;BA.debugLine="inpdlg.Show(\"Enter new password\", \"Update\", \"OK";
mostCurrent._inpdlg.Show("Enter new password","Update","OK","","",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
RDebugUtils.currentLine=4784145;
 //BA.debugLineNum = 4784145;BA.debugLine="Dim newpass As String = inpdlg.Input";
_newpass = mostCurrent._inpdlg.getInput();
RDebugUtils.currentLine=4784147;
 //BA.debugLineNum = 4784147;BA.debugLine="inpdlg.Input = \"\"";
mostCurrent._inpdlg.setInput("");
RDebugUtils.currentLine=4784148;
 //BA.debugLineNum = 4784148;BA.debugLine="inpdlg.Show(\"Enter role (admin/user)\", \"Update\"";
mostCurrent._inpdlg.Show("Enter role (admin/user)","Update","OK","","",mostCurrent.activityBA,(android.graphics.Bitmap)(anywheresoftware.b4a.keywords.Common.Null));
RDebugUtils.currentLine=4784149;
 //BA.debugLineNum = 4784149;BA.debugLine="Dim newrole As String = inpdlg.Input";
_newrole = mostCurrent._inpdlg.getInput();
RDebugUtils.currentLine=4784151;
 //BA.debugLineNum = 4784151;BA.debugLine="sql.ExecNonQuery2(\"UPDATE tblusers SET password";
_sql.ExecNonQuery2("UPDATE tblusers SET password=?, role=? WHERE username=?",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(_newpass),(Object)(_newrole),(Object)(_uname)}));
RDebugUtils.currentLine=4784153;
 //BA.debugLineNum = 4784153;BA.debugLine="ShowUsers";
_showusers();
RDebugUtils.currentLine=4784154;
 //BA.debugLineNum = 4784154;BA.debugLine="ToastMessageShow(\"User Updated\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("User Updated"),anywheresoftware.b4a.keywords.Common.False);
 }else {
RDebugUtils.currentLine=4784157;
 //BA.debugLineNum = 4784157;BA.debugLine="Msgbox(\"User not found\", \"\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("User not found"),BA.ObjectToCharSequence(""),mostCurrent.activityBA);
 };
 };
RDebugUtils.currentLine=4784161;
 //BA.debugLineNum = 4784161;BA.debugLine="End Sub";
return "";
}
}