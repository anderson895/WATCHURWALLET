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

public class goal extends Activity implements B4AActivity{
	public static goal mostCurrent;
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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.goal");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (goal).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.goal");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.goal", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (goal) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (goal) Resume **");
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
		return goal.class;
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
            BA.LogInfo("** Activity (goal) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (goal) Pause event (activity is not paused). **");
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
            goal mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (goal) Resume **");
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
public anywheresoftware.b4a.objects.PanelWrapper _pnlmenu = null;
public anywheresoftware.b4a.objects.ButtonWrapper _button1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtaddedgoal1 = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtgoalcategory1 = null;
public anywheresoftware.b4a.objects.ProgressBarWrapper _progressbar1 = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnaddgoal = null;
public b4a.example.dateutils _dateutils = null;
public b4a.example.main _main = null;
public b4a.example.signup _signup = null;
public b4a.example.myacc _myacc = null;
public b4a.example.home _home = null;
public b4a.example.expenses _expenses = null;
public b4a.example.account _account = null;
public b4a.example.admin _admin = null;
public b4a.example.editexpenses _editexpenses = null;
public b4a.example.editgoals _editgoals = null;
public b4a.example.dashboard _dashboard = null;
public b4a.example.starter _starter = null;
public b4a.example.xuiviewsutils _xuiviewsutils = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="goal";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=3014656;
 //BA.debugLineNum = 3014656;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=3014657;
 //BA.debugLineNum = 3014657;BA.debugLine="sql = Main.sql";
_sql = mostCurrent._main._sql /*anywheresoftware.b4a.sql.SQL*/ ;
RDebugUtils.currentLine=3014658;
 //BA.debugLineNum = 3014658;BA.debugLine="Activity.LoadLayout(\"laygoal\")";
mostCurrent._activity.LoadLayout("laygoal",mostCurrent.activityBA);
RDebugUtils.currentLine=3014659;
 //BA.debugLineNum = 3014659;BA.debugLine="pnlmenu.LoadLayout(\"laymenu\")";
mostCurrent._pnlmenu.LoadLayout("laymenu",mostCurrent.activityBA);
RDebugUtils.currentLine=3014660;
 //BA.debugLineNum = 3014660;BA.debugLine="pnlmenu.Visible = False";
mostCurrent._pnlmenu.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3014661;
 //BA.debugLineNum = 3014661;BA.debugLine="End Sub";
return "";
}
public static String  _activity_resume() throws Exception{
RDebugUtils.currentModule="goal";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_resume", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_resume", null));}
RDebugUtils.currentLine=3080192;
 //BA.debugLineNum = 3080192;BA.debugLine="Sub Activity_Resume";
RDebugUtils.currentLine=3080193;
 //BA.debugLineNum = 3080193;BA.debugLine="LoadGoals";
_loadgoals();
RDebugUtils.currentLine=3080194;
 //BA.debugLineNum = 3080194;BA.debugLine="End Sub";
return "";
}
public static String  _loadgoals() throws Exception{
RDebugUtils.currentModule="goal";
if (Debug.shouldDelegate(mostCurrent.activityBA, "loadgoals", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "loadgoals", null));}
anywheresoftware.b4a.sql.SQL.CursorWrapper _c = null;
RDebugUtils.currentLine=3538944;
 //BA.debugLineNum = 3538944;BA.debugLine="Sub LoadGoals";
RDebugUtils.currentLine=3538945;
 //BA.debugLineNum = 3538945;BA.debugLine="Dim c As Cursor";
_c = new anywheresoftware.b4a.sql.SQL.CursorWrapper();
RDebugUtils.currentLine=3538946;
 //BA.debugLineNum = 3538946;BA.debugLine="c = sql.ExecQuery(\"SELECT category, goal_amount,";
_c = (anywheresoftware.b4a.sql.SQL.CursorWrapper) anywheresoftware.b4a.AbsObjectWrapper.ConvertToWrapper(new anywheresoftware.b4a.sql.SQL.CursorWrapper(), (android.database.Cursor)(_sql.ExecQuery("SELECT category, goal_amount, current_amount FROM tblgoal")));
RDebugUtils.currentLine=3538947;
 //BA.debugLineNum = 3538947;BA.debugLine="If c.RowCount > 0 Then";
if (_c.getRowCount()>0) { 
RDebugUtils.currentLine=3538948;
 //BA.debugLineNum = 3538948;BA.debugLine="c.Position = 0";
_c.setPosition((int) (0));
RDebugUtils.currentLine=3538949;
 //BA.debugLineNum = 3538949;BA.debugLine="txtgoalcategory1.Text = c.GetString2(0)";
mostCurrent._txtgoalcategory1.setText(BA.ObjectToCharSequence(_c.GetString2((int) (0))));
RDebugUtils.currentLine=3538950;
 //BA.debugLineNum = 3538950;BA.debugLine="txtaddedgoal1.Text = c.GetDouble2(1)";
mostCurrent._txtaddedgoal1.setText(BA.ObjectToCharSequence(_c.GetDouble2((int) (1))));
RDebugUtils.currentLine=3538951;
 //BA.debugLineNum = 3538951;BA.debugLine="txtaddedgoal1.Text = c.GetDouble2(2)";
mostCurrent._txtaddedgoal1.setText(BA.ObjectToCharSequence(_c.GetDouble2((int) (2))));
RDebugUtils.currentLine=3538953;
 //BA.debugLineNum = 3538953;BA.debugLine="If c.GetDouble2(2) > 0 Then";
if (_c.GetDouble2((int) (2))>0) { 
RDebugUtils.currentLine=3538954;
 //BA.debugLineNum = 3538954;BA.debugLine="ProgressBar1.Progress = (c.GetDouble2(1) / c.Ge";
mostCurrent._progressbar1.setProgress((int) ((_c.GetDouble2((int) (1))/(double)_c.GetDouble2((int) (2)))*100));
 }else {
RDebugUtils.currentLine=3538956;
 //BA.debugLineNum = 3538956;BA.debugLine="ProgressBar1.Progress = 0";
mostCurrent._progressbar1.setProgress((int) (0));
 };
 };
RDebugUtils.currentLine=3538959;
 //BA.debugLineNum = 3538959;BA.debugLine="c.Close";
_c.Close();
RDebugUtils.currentLine=3538960;
 //BA.debugLineNum = 3538960;BA.debugLine="End Sub";
return "";
}
public static String  _btnaddgoal_click() throws Exception{
RDebugUtils.currentModule="goal";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnaddgoal_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnaddgoal_click", null));}
String _category = "";
double _maxamt = 0;
double _currentamt = 0;
RDebugUtils.currentLine=3473408;
 //BA.debugLineNum = 3473408;BA.debugLine="Private Sub btnaddgoal_Click";
RDebugUtils.currentLine=3473409;
 //BA.debugLineNum = 3473409;BA.debugLine="Dim category As String = txtgoalcategory1.Text";
_category = mostCurrent._txtgoalcategory1.getText();
RDebugUtils.currentLine=3473410;
 //BA.debugLineNum = 3473410;BA.debugLine="Dim maxAmt As Double";
_maxamt = 0;
RDebugUtils.currentLine=3473411;
 //BA.debugLineNum = 3473411;BA.debugLine="Dim currentAmt As Double";
_currentamt = 0;
RDebugUtils.currentLine=3473414;
 //BA.debugLineNum = 3473414;BA.debugLine="If IsNumber(txtaddedgoal1.Text) Then";
if (anywheresoftware.b4a.keywords.Common.IsNumber(mostCurrent._txtaddedgoal1.getText())) { 
RDebugUtils.currentLine=3473415;
 //BA.debugLineNum = 3473415;BA.debugLine="maxAmt = txtaddedgoal1.Text";
_maxamt = (double)(Double.parseDouble(mostCurrent._txtaddedgoal1.getText()));
 }else {
RDebugUtils.currentLine=3473417;
 //BA.debugLineNum = 3473417;BA.debugLine="Msgbox(\"Target amount must be a number\",\"\")";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Target amount must be a number"),BA.ObjectToCharSequence(""),mostCurrent.activityBA);
RDebugUtils.currentLine=3473418;
 //BA.debugLineNum = 3473418;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3473421;
 //BA.debugLineNum = 3473421;BA.debugLine="If IsNumber(txtaddedgoal1.Text) Then";
if (anywheresoftware.b4a.keywords.Common.IsNumber(mostCurrent._txtaddedgoal1.getText())) { 
RDebugUtils.currentLine=3473422;
 //BA.debugLineNum = 3473422;BA.debugLine="currentAmt = txtaddedgoal1.Text";
_currentamt = (double)(Double.parseDouble(mostCurrent._txtaddedgoal1.getText()));
 }else {
RDebugUtils.currentLine=3473424;
 //BA.debugLineNum = 3473424;BA.debugLine="currentAmt = 0   ' default kung walang laman";
_currentamt = 0;
 };
RDebugUtils.currentLine=3473427;
 //BA.debugLineNum = 3473427;BA.debugLine="If category = \"\" Or maxAmt <= 0 Then";
if ((_category).equals("") || _maxamt<=0) { 
RDebugUtils.currentLine=3473428;
 //BA.debugLineNum = 3473428;BA.debugLine="Msgbox(\"Please enter a valid category and target";
anywheresoftware.b4a.keywords.Common.Msgbox(BA.ObjectToCharSequence("Please enter a valid category and target amount"),BA.ObjectToCharSequence(""),mostCurrent.activityBA);
RDebugUtils.currentLine=3473429;
 //BA.debugLineNum = 3473429;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=3473432;
 //BA.debugLineNum = 3473432;BA.debugLine="sql.ExecNonQuery2(\"INSERT INTO tblgoal (category,";
_sql.ExecNonQuery2("INSERT INTO tblgoal (category, goal-amount, current_amount) VALUES (?, ?, ?)",anywheresoftware.b4a.keywords.Common.ArrayToList(new Object[]{(Object)(_category),(Object)(0),(Object)(_maxamt),(Object)(_currentamt)}));
RDebugUtils.currentLine=3473434;
 //BA.debugLineNum = 3473434;BA.debugLine="ToastMessageShow(\"Goal Added: \" & category & \" -";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Goal Added: "+_category+" - ₱"+BA.NumberToString(_currentamt)+"/"+BA.NumberToString(_maxamt)),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3473435;
 //BA.debugLineNum = 3473435;BA.debugLine="LoadGoals";
_loadgoals();
RDebugUtils.currentLine=3473437;
 //BA.debugLineNum = 3473437;BA.debugLine="txtgoalcategory1.Text = \"\"";
mostCurrent._txtgoalcategory1.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=3473438;
 //BA.debugLineNum = 3473438;BA.debugLine="txtaddedgoal1.Text = \"\"";
mostCurrent._txtaddedgoal1.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=3473439;
 //BA.debugLineNum = 3473439;BA.debugLine="txtaddedgoal1.Text = \"\"";
mostCurrent._txtaddedgoal1.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=3473440;
 //BA.debugLineNum = 3473440;BA.debugLine="End Sub";
return "";
}
public static String  _btnmenu_click() throws Exception{
RDebugUtils.currentModule="goal";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnmenu_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnmenu_click", null));}
RDebugUtils.currentLine=3145728;
 //BA.debugLineNum = 3145728;BA.debugLine="Private Sub btnmenu_Click";
RDebugUtils.currentLine=3145729;
 //BA.debugLineNum = 3145729;BA.debugLine="pnlmenu.Visible = True";
mostCurrent._pnlmenu.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=3145730;
 //BA.debugLineNum = 3145730;BA.debugLine="End Sub";
return "";
}
public static String  _labelaccount_click() throws Exception{
RDebugUtils.currentModule="goal";
if (Debug.shouldDelegate(mostCurrent.activityBA, "labelaccount_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "labelaccount_click", null));}
RDebugUtils.currentLine=3407872;
 //BA.debugLineNum = 3407872;BA.debugLine="Private Sub labelaccount_Click";
RDebugUtils.currentLine=3407873;
 //BA.debugLineNum = 3407873;BA.debugLine="pnlmenu.Visible = False";
mostCurrent._pnlmenu.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3407874;
 //BA.debugLineNum = 3407874;BA.debugLine="StartActivity(account)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._account.getObject()));
RDebugUtils.currentLine=3407875;
 //BA.debugLineNum = 3407875;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=3407876;
 //BA.debugLineNum = 3407876;BA.debugLine="End Sub";
return "";
}
public static String  _labelexpenses_click() throws Exception{
RDebugUtils.currentModule="goal";
if (Debug.shouldDelegate(mostCurrent.activityBA, "labelexpenses_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "labelexpenses_click", null));}
RDebugUtils.currentLine=3276800;
 //BA.debugLineNum = 3276800;BA.debugLine="Private Sub labelexpenses_Click";
RDebugUtils.currentLine=3276801;
 //BA.debugLineNum = 3276801;BA.debugLine="pnlmenu.Visible = False";
mostCurrent._pnlmenu.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3276802;
 //BA.debugLineNum = 3276802;BA.debugLine="StartActivity(expenses)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._expenses.getObject()));
RDebugUtils.currentLine=3276803;
 //BA.debugLineNum = 3276803;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=3276804;
 //BA.debugLineNum = 3276804;BA.debugLine="End Sub";
return "";
}
public static String  _labelgoal_click() throws Exception{
RDebugUtils.currentModule="goal";
if (Debug.shouldDelegate(mostCurrent.activityBA, "labelgoal_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "labelgoal_click", null));}
RDebugUtils.currentLine=3342336;
 //BA.debugLineNum = 3342336;BA.debugLine="Private Sub labelgoal_Click";
RDebugUtils.currentLine=3342337;
 //BA.debugLineNum = 3342337;BA.debugLine="pnlmenu.Visible = False";
mostCurrent._pnlmenu.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3342338;
 //BA.debugLineNum = 3342338;BA.debugLine="End Sub";
return "";
}
public static String  _labelhome_click() throws Exception{
RDebugUtils.currentModule="goal";
if (Debug.shouldDelegate(mostCurrent.activityBA, "labelhome_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "labelhome_click", null));}
RDebugUtils.currentLine=3211264;
 //BA.debugLineNum = 3211264;BA.debugLine="Private Sub labelhome_Click";
RDebugUtils.currentLine=3211265;
 //BA.debugLineNum = 3211265;BA.debugLine="pnlmenu.Visible = False";
mostCurrent._pnlmenu.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=3211266;
 //BA.debugLineNum = 3211266;BA.debugLine="StartActivity(home)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._home.getObject()));
RDebugUtils.currentLine=3211267;
 //BA.debugLineNum = 3211267;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=3211268;
 //BA.debugLineNum = 3211268;BA.debugLine="End Sub";
return "";
}
}