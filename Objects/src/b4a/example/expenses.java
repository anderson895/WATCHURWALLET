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

public class expenses extends Activity implements B4AActivity{
	public static expenses mostCurrent;
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
			processBA = new anywheresoftware.b4a.ShellBA(this.getApplicationContext(), null, null, "b4a.example", "b4a.example.expenses");
			processBA.loadHtSubs(this.getClass());
	        float deviceScale = getApplicationContext().getResources().getDisplayMetrics().density;
	        BALayout.setDeviceScale(deviceScale);
            
		}
		else if (previousOne != null) {
			Activity p = previousOne.get();
			if (p != null && p != this) {
                BA.LogInfo("Killing previous instance (expenses).");
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
		activityBA = new BA(this, layout, processBA, "b4a.example", "b4a.example.expenses");
        
        processBA.sharedProcessBA.activityBA = new java.lang.ref.WeakReference<BA>(activityBA);
        anywheresoftware.b4a.objects.ViewWrapper.lastId = 0;
        _activity = new ActivityWrapper(activityBA, "activity");
        anywheresoftware.b4a.Msgbox.isDismissing = false;
        if (BA.isShellModeRuntimeCheck(processBA)) {
			if (isFirst)
				processBA.raiseEvent2(null, true, "SHELL", false);
			processBA.raiseEvent2(null, true, "CREATE", true, "b4a.example.expenses", processBA, activityBA, _activity, anywheresoftware.b4a.keywords.Common.Density, mostCurrent);
			_activity.reinitializeForShell(activityBA, "activity");
		}
        initializeProcessGlobals();		
        initializeGlobals();
        
        BA.LogInfo("** Activity (expenses) Create " + (isFirst ? "(first time)" : "") + " **");
        processBA.raiseEvent2(null, true, "activity_create", false, isFirst);
		isFirst = false;
		if (this != mostCurrent)
			return;
        processBA.setActivityPaused(false);
        BA.LogInfo("** Activity (expenses) Resume **");
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
		return expenses.class;
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
            BA.LogInfo("** Activity (expenses) Pause, UserClosed = " + activityBA.activity.isFinishing() + " **");
        else
            BA.LogInfo("** Activity (expenses) Pause event (activity is not paused). **");
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
            expenses mc = mostCurrent;
			if (mc == null || mc != activity.get())
				return;
			processBA.setActivityPaused(false);
            BA.LogInfo("** Activity (expenses) Resume **");
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
public anywheresoftware.b4a.objects.PanelWrapper _pnlmenu = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnmenu = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txttotweekexp = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listexpenses = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtsplitbill = null;
public anywheresoftware.b4a.objects.SpinnerWrapper _spinnergroup = null;
public anywheresoftware.b4a.objects.ButtonWrapper _btnevensplit = null;
public anywheresoftware.b4a.objects.ListViewWrapper _listsplit = null;
public anywheresoftware.b4a.objects.EditTextWrapper _txtsplitwith = null;
public b4a.example.dateutils _dateutils = null;
public b4a.example.main _main = null;
public b4a.example.signup _signup = null;
public b4a.example.myacc _myacc = null;
public b4a.example.home _home = null;
public b4a.example.goal _goal = null;
public b4a.example.account _account = null;
public b4a.example.admin _admin = null;
public b4a.example.editexpenses _editexpenses = null;
public b4a.example.editgoals _editgoals = null;
public b4a.example.dashboard _dashboard = null;
public b4a.example.starter _starter = null;
public b4a.example.xuiviewsutils _xuiviewsutils = null;
public static String  _activity_create(boolean _firsttime) throws Exception{
RDebugUtils.currentModule="expenses";
if (Debug.shouldDelegate(mostCurrent.activityBA, "activity_create", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "activity_create", new Object[] {_firsttime}));}
RDebugUtils.currentLine=2293760;
 //BA.debugLineNum = 2293760;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
RDebugUtils.currentLine=2293761;
 //BA.debugLineNum = 2293761;BA.debugLine="Activity.LoadLayout(\"layexpenses\")";
mostCurrent._activity.LoadLayout("layexpenses",mostCurrent.activityBA);
RDebugUtils.currentLine=2293763;
 //BA.debugLineNum = 2293763;BA.debugLine="pnlmenu.LoadLayout(\"laymenu\")";
mostCurrent._pnlmenu.LoadLayout("laymenu",mostCurrent.activityBA);
RDebugUtils.currentLine=2293764;
 //BA.debugLineNum = 2293764;BA.debugLine="pnlmenu.Visible = False";
mostCurrent._pnlmenu.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2293766;
 //BA.debugLineNum = 2293766;BA.debugLine="txttotweekexp.Enabled = False";
mostCurrent._txttotweekexp.setEnabled(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2293768;
 //BA.debugLineNum = 2293768;BA.debugLine="LoadExpenses";
_loadexpenses();
RDebugUtils.currentLine=2293769;
 //BA.debugLineNum = 2293769;BA.debugLine="LoadSpinnerCategories";
_loadspinnercategories();
RDebugUtils.currentLine=2293771;
 //BA.debugLineNum = 2293771;BA.debugLine="txtsplitbill.Background = Null";
mostCurrent._txtsplitbill.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
RDebugUtils.currentLine=2293772;
 //BA.debugLineNum = 2293772;BA.debugLine="txtsplitwith.Background = Null";
mostCurrent._txtsplitwith.setBackground((android.graphics.drawable.Drawable)(anywheresoftware.b4a.keywords.Common.Null));
RDebugUtils.currentLine=2293774;
 //BA.debugLineNum = 2293774;BA.debugLine="End Sub";
return "";
}
public static String  _loadexpenses() throws Exception{
RDebugUtils.currentModule="expenses";
if (Debug.shouldDelegate(mostCurrent.activityBA, "loadexpenses", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "loadexpenses", null));}
double _total = 0;
int _i = 0;
double _expense = 0;
String _category = "";
RDebugUtils.currentLine=2359296;
 //BA.debugLineNum = 2359296;BA.debugLine="Sub LoadExpenses";
RDebugUtils.currentLine=2359297;
 //BA.debugLineNum = 2359297;BA.debugLine="Dim Total As Double = 0";
_total = 0;
RDebugUtils.currentLine=2359299;
 //BA.debugLineNum = 2359299;BA.debugLine="listexpenses.Clear";
mostCurrent._listexpenses.Clear();
RDebugUtils.currentLine=2359300;
 //BA.debugLineNum = 2359300;BA.debugLine="If home.ExpenseList.IsInitialized = False Or home";
if (mostCurrent._home._expenselist /*anywheresoftware.b4a.objects.collections.List*/ .IsInitialized()==anywheresoftware.b4a.keywords.Common.False || mostCurrent._home._expenselist /*anywheresoftware.b4a.objects.collections.List*/ .getSize()==0) { 
RDebugUtils.currentLine=2359301;
 //BA.debugLineNum = 2359301;BA.debugLine="ToastMessageShow(\"No expenses yet\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("No expenses yet"),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2359302;
 //BA.debugLineNum = 2359302;BA.debugLine="txttotweekexp.Text = \"₱0.00\"";
mostCurrent._txttotweekexp.setText(BA.ObjectToCharSequence("₱0.00"));
RDebugUtils.currentLine=2359303;
 //BA.debugLineNum = 2359303;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=2359306;
 //BA.debugLineNum = 2359306;BA.debugLine="For i = 0 To home.ExpenseList.Size - 1";
{
final int step8 = 1;
final int limit8 = (int) (mostCurrent._home._expenselist /*anywheresoftware.b4a.objects.collections.List*/ .getSize()-1);
_i = (int) (0) ;
for (;_i <= limit8 ;_i = _i + step8 ) {
RDebugUtils.currentLine=2359308;
 //BA.debugLineNum = 2359308;BA.debugLine="Dim Expense As Double = home.ExpenseList.Get(i)";
_expense = (double)(BA.ObjectToNumber(mostCurrent._home._expenselist /*anywheresoftware.b4a.objects.collections.List*/ .Get(_i)));
RDebugUtils.currentLine=2359309;
 //BA.debugLineNum = 2359309;BA.debugLine="Dim Category As String = home.CategoryList.Get(i";
_category = BA.ObjectToString(mostCurrent._home._categorylist /*anywheresoftware.b4a.objects.collections.List*/ .Get(_i));
RDebugUtils.currentLine=2359311;
 //BA.debugLineNum = 2359311;BA.debugLine="Total = Total + Expense";
_total = _total+_expense;
RDebugUtils.currentLine=2359313;
 //BA.debugLineNum = 2359313;BA.debugLine="listexpenses.AddSingleLine( _         Category &";
mostCurrent._listexpenses.AddSingleLine(BA.ObjectToCharSequence(_category+" - ₱"+anywheresoftware.b4a.keywords.Common.NumberFormat(_expense,(int) (1),(int) (2))));
 }
};
RDebugUtils.currentLine=2359318;
 //BA.debugLineNum = 2359318;BA.debugLine="txttotweekexp.Text = \"₱\" & NumberFormat(Total,1,2";
mostCurrent._txttotweekexp.setText(BA.ObjectToCharSequence("₱"+anywheresoftware.b4a.keywords.Common.NumberFormat(_total,(int) (1),(int) (2))));
RDebugUtils.currentLine=2359319;
 //BA.debugLineNum = 2359319;BA.debugLine="End Sub";
return "";
}
public static String  _loadspinnercategories() throws Exception{
RDebugUtils.currentModule="expenses";
if (Debug.shouldDelegate(mostCurrent.activityBA, "loadspinnercategories", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "loadspinnercategories", null));}
RDebugUtils.currentLine=2424832;
 //BA.debugLineNum = 2424832;BA.debugLine="Sub LoadSpinnerCategories";
RDebugUtils.currentLine=2424833;
 //BA.debugLineNum = 2424833;BA.debugLine="spinnergroup.Clear";
mostCurrent._spinnergroup.Clear();
RDebugUtils.currentLine=2424835;
 //BA.debugLineNum = 2424835;BA.debugLine="spinnergroup.Add(\"Foods & Groceries\")";
mostCurrent._spinnergroup.Add("Foods & Groceries");
RDebugUtils.currentLine=2424836;
 //BA.debugLineNum = 2424836;BA.debugLine="spinnergroup.Add(\"Transpo\")";
mostCurrent._spinnergroup.Add("Transpo");
RDebugUtils.currentLine=2424837;
 //BA.debugLineNum = 2424837;BA.debugLine="spinnergroup.Add(\"Rent\")";
mostCurrent._spinnergroup.Add("Rent");
RDebugUtils.currentLine=2424838;
 //BA.debugLineNum = 2424838;BA.debugLine="spinnergroup.Add(\"School Supplies\")";
mostCurrent._spinnergroup.Add("School Supplies");
RDebugUtils.currentLine=2424839;
 //BA.debugLineNum = 2424839;BA.debugLine="spinnergroup.Add(\"School Projects\")";
mostCurrent._spinnergroup.Add("School Projects");
RDebugUtils.currentLine=2424840;
 //BA.debugLineNum = 2424840;BA.debugLine="End Sub";
return "";
}
public static String  _btnevensplit_click() throws Exception{
RDebugUtils.currentModule="expenses";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnevensplit_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnevensplit_click", null));}
double _totalbill = 0;
int _persons = 0;
double _share = 0;
String _category = "";
RDebugUtils.currentLine=2818048;
 //BA.debugLineNum = 2818048;BA.debugLine="Private Sub btnevensplit_Click";
RDebugUtils.currentLine=2818049;
 //BA.debugLineNum = 2818049;BA.debugLine="If txtsplitbill.Text = \"\" Or txtsplitwith.Text =";
if ((mostCurrent._txtsplitbill.getText()).equals("") || (mostCurrent._txtsplitwith.getText()).equals("")) { 
RDebugUtils.currentLine=2818050;
 //BA.debugLineNum = 2818050;BA.debugLine="ToastMessageShow(\"Complete split information.\",";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Complete split information."),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2818051;
 //BA.debugLineNum = 2818051;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=2818054;
 //BA.debugLineNum = 2818054;BA.debugLine="If IsNumber(txtsplitbill.Text) = False Then";
if (anywheresoftware.b4a.keywords.Common.IsNumber(mostCurrent._txtsplitbill.getText())==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=2818055;
 //BA.debugLineNum = 2818055;BA.debugLine="ToastMessageShow(\"Bill must be number.\", False)";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Bill must be number."),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2818056;
 //BA.debugLineNum = 2818056;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=2818059;
 //BA.debugLineNum = 2818059;BA.debugLine="If IsNumber(txtsplitwith.Text) = False Then";
if (anywheresoftware.b4a.keywords.Common.IsNumber(mostCurrent._txtsplitwith.getText())==anywheresoftware.b4a.keywords.Common.False) { 
RDebugUtils.currentLine=2818060;
 //BA.debugLineNum = 2818060;BA.debugLine="ToastMessageShow(\"Persons must be number.\", Fals";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Persons must be number."),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2818061;
 //BA.debugLineNum = 2818061;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=2818064;
 //BA.debugLineNum = 2818064;BA.debugLine="Dim TotalBill As Double";
_totalbill = 0;
RDebugUtils.currentLine=2818065;
 //BA.debugLineNum = 2818065;BA.debugLine="Dim Persons As Int";
_persons = 0;
RDebugUtils.currentLine=2818066;
 //BA.debugLineNum = 2818066;BA.debugLine="Dim Share As Double";
_share = 0;
RDebugUtils.currentLine=2818067;
 //BA.debugLineNum = 2818067;BA.debugLine="Dim Category As String";
_category = "";
RDebugUtils.currentLine=2818069;
 //BA.debugLineNum = 2818069;BA.debugLine="TotalBill = txtsplitbill.Text";
_totalbill = (double)(Double.parseDouble(mostCurrent._txtsplitbill.getText()));
RDebugUtils.currentLine=2818070;
 //BA.debugLineNum = 2818070;BA.debugLine="Persons = txtsplitwith.Text";
_persons = (int)(Double.parseDouble(mostCurrent._txtsplitwith.getText()));
RDebugUtils.currentLine=2818072;
 //BA.debugLineNum = 2818072;BA.debugLine="If Persons <= 0 Then";
if (_persons<=0) { 
RDebugUtils.currentLine=2818073;
 //BA.debugLineNum = 2818073;BA.debugLine="ToastMessageShow(\"Persons must be greater than 0";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Persons must be greater than 0."),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2818074;
 //BA.debugLineNum = 2818074;BA.debugLine="Return";
if (true) return "";
 };
RDebugUtils.currentLine=2818077;
 //BA.debugLineNum = 2818077;BA.debugLine="Share = TotalBill / Persons";
_share = _totalbill/(double)_persons;
RDebugUtils.currentLine=2818079;
 //BA.debugLineNum = 2818079;BA.debugLine="Category = spinnergroup.SelectedItem";
_category = mostCurrent._spinnergroup.getSelectedItem();
RDebugUtils.currentLine=2818081;
 //BA.debugLineNum = 2818081;BA.debugLine="listsplit.AddSingleLine( _     Category & \" - \" &";
mostCurrent._listsplit.AddSingleLine(BA.ObjectToCharSequence(_category+" - "+BA.NumberToString(_persons)+" Person - ₱"+anywheresoftware.b4a.keywords.Common.NumberFormat(_totalbill,(int) (1),(int) (2))+" - ₱"+anywheresoftware.b4a.keywords.Common.NumberFormat(_share,(int) (1),(int) (2))+" each"));
RDebugUtils.currentLine=2818087;
 //BA.debugLineNum = 2818087;BA.debugLine="ToastMessageShow(\"Bill successfully split.\", Fals";
anywheresoftware.b4a.keywords.Common.ToastMessageShow(BA.ObjectToCharSequence("Bill successfully split."),anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2818089;
 //BA.debugLineNum = 2818089;BA.debugLine="txtsplitbill.Text = \"\"";
mostCurrent._txtsplitbill.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=2818090;
 //BA.debugLineNum = 2818090;BA.debugLine="txtsplitwith.Text = \"\"";
mostCurrent._txtsplitwith.setText(BA.ObjectToCharSequence(""));
RDebugUtils.currentLine=2818091;
 //BA.debugLineNum = 2818091;BA.debugLine="End Sub";
return "";
}
public static String  _btnmenu_click() throws Exception{
RDebugUtils.currentModule="expenses";
if (Debug.shouldDelegate(mostCurrent.activityBA, "btnmenu_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "btnmenu_click", null));}
RDebugUtils.currentLine=2490368;
 //BA.debugLineNum = 2490368;BA.debugLine="Private Sub btnmenu_Click";
RDebugUtils.currentLine=2490369;
 //BA.debugLineNum = 2490369;BA.debugLine="pnlmenu.Visible = True";
mostCurrent._pnlmenu.setVisible(anywheresoftware.b4a.keywords.Common.True);
RDebugUtils.currentLine=2490370;
 //BA.debugLineNum = 2490370;BA.debugLine="End Sub";
return "";
}
public static String  _labelaccount_click() throws Exception{
RDebugUtils.currentModule="expenses";
if (Debug.shouldDelegate(mostCurrent.activityBA, "labelaccount_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "labelaccount_click", null));}
RDebugUtils.currentLine=2752512;
 //BA.debugLineNum = 2752512;BA.debugLine="Private Sub labelaccount_Click";
RDebugUtils.currentLine=2752513;
 //BA.debugLineNum = 2752513;BA.debugLine="pnlmenu.Visible = False";
mostCurrent._pnlmenu.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2752514;
 //BA.debugLineNum = 2752514;BA.debugLine="StartActivity(account)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._account.getObject()));
RDebugUtils.currentLine=2752515;
 //BA.debugLineNum = 2752515;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=2752516;
 //BA.debugLineNum = 2752516;BA.debugLine="End Sub";
return "";
}
public static String  _labelexpenses_click() throws Exception{
RDebugUtils.currentModule="expenses";
if (Debug.shouldDelegate(mostCurrent.activityBA, "labelexpenses_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "labelexpenses_click", null));}
RDebugUtils.currentLine=2621440;
 //BA.debugLineNum = 2621440;BA.debugLine="Private Sub labelexpenses_Click";
RDebugUtils.currentLine=2621441;
 //BA.debugLineNum = 2621441;BA.debugLine="pnlmenu.Visible = False";
mostCurrent._pnlmenu.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2621442;
 //BA.debugLineNum = 2621442;BA.debugLine="End Sub";
return "";
}
public static String  _labelgoal_click() throws Exception{
RDebugUtils.currentModule="expenses";
if (Debug.shouldDelegate(mostCurrent.activityBA, "labelgoal_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "labelgoal_click", null));}
RDebugUtils.currentLine=2686976;
 //BA.debugLineNum = 2686976;BA.debugLine="Private Sub labelgoal_Click";
RDebugUtils.currentLine=2686977;
 //BA.debugLineNum = 2686977;BA.debugLine="pnlmenu.Visible = False";
mostCurrent._pnlmenu.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2686978;
 //BA.debugLineNum = 2686978;BA.debugLine="StartActivity(goal)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._goal.getObject()));
RDebugUtils.currentLine=2686979;
 //BA.debugLineNum = 2686979;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=2686980;
 //BA.debugLineNum = 2686980;BA.debugLine="End Sub";
return "";
}
public static String  _labelhome_click() throws Exception{
RDebugUtils.currentModule="expenses";
if (Debug.shouldDelegate(mostCurrent.activityBA, "labelhome_click", false))
	 {return ((String) Debug.delegate(mostCurrent.activityBA, "labelhome_click", null));}
RDebugUtils.currentLine=2555904;
 //BA.debugLineNum = 2555904;BA.debugLine="Private Sub labelhome_Click";
RDebugUtils.currentLine=2555905;
 //BA.debugLineNum = 2555905;BA.debugLine="pnlmenu.Visible = False";
mostCurrent._pnlmenu.setVisible(anywheresoftware.b4a.keywords.Common.False);
RDebugUtils.currentLine=2555906;
 //BA.debugLineNum = 2555906;BA.debugLine="StartActivity(home)";
anywheresoftware.b4a.keywords.Common.StartActivity(processBA,(Object)(mostCurrent._home.getObject()));
RDebugUtils.currentLine=2555907;
 //BA.debugLineNum = 2555907;BA.debugLine="Activity.Finish";
mostCurrent._activity.Finish();
RDebugUtils.currentLine=2555908;
 //BA.debugLineNum = 2555908;BA.debugLine="End Sub";
return "";
}
}