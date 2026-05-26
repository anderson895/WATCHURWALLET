package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class goal_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (goal) ","goal",5,goal.mostCurrent.activityBA,goal.mostCurrent,23);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.goal.remoteMe.runUserSub(false, "goal","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 23;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 24;BA.debugLine="sql = Main.sql";
Debug.ShouldStop(8388608);
goal._sql = goal.mostCurrent._main._sql /*RemoteObject*/ ;
 BA.debugLineNum = 25;BA.debugLine="Activity.LoadLayout(\"laygoal\")";
Debug.ShouldStop(16777216);
goal.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("laygoal")),goal.mostCurrent.activityBA);
 BA.debugLineNum = 26;BA.debugLine="pnlmenu.LoadLayout(\"laymenu\")";
Debug.ShouldStop(33554432);
goal.mostCurrent._pnlmenu.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("laymenu")),goal.mostCurrent.activityBA);
 BA.debugLineNum = 27;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(67108864);
goal.mostCurrent._pnlmenu.runMethod(true,"setVisible",goal.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 28;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (goal) ","goal",5,goal.mostCurrent.activityBA,goal.mostCurrent,30);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.goal.remoteMe.runUserSub(false, "goal","activity_resume");}
 BA.debugLineNum = 30;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 31;BA.debugLine="LoadGoals";
Debug.ShouldStop(1073741824);
_loadgoals();
 BA.debugLineNum = 32;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnaddgoal_click() throws Exception{
try {
		Debug.PushSubsStack("btnaddgoal_Click (goal) ","goal",5,goal.mostCurrent.activityBA,goal.mostCurrent,61);
if (RapidSub.canDelegate("btnaddgoal_click")) { return b4a.example.goal.remoteMe.runUserSub(false, "goal","btnaddgoal_click");}
RemoteObject _category = RemoteObject.createImmutable("");
RemoteObject _maxamt = RemoteObject.createImmutable(0);
RemoteObject _currentamt = RemoteObject.createImmutable(0);
 BA.debugLineNum = 61;BA.debugLine="Private Sub btnaddgoal_Click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 62;BA.debugLine="Dim category As String = txtgoalcategory1.Text";
Debug.ShouldStop(536870912);
_category = goal.mostCurrent._txtgoalcategory1.runMethod(true,"getText");Debug.locals.put("category", _category);Debug.locals.put("category", _category);
 BA.debugLineNum = 63;BA.debugLine="Dim maxAmt As Double";
Debug.ShouldStop(1073741824);
_maxamt = RemoteObject.createImmutable(0);Debug.locals.put("maxAmt", _maxamt);
 BA.debugLineNum = 64;BA.debugLine="Dim currentAmt As Double";
Debug.ShouldStop(-2147483648);
_currentamt = RemoteObject.createImmutable(0);Debug.locals.put("currentAmt", _currentamt);
 BA.debugLineNum = 67;BA.debugLine="If IsNumber(txtaddedgoal1.Text) Then";
Debug.ShouldStop(4);
if (goal.mostCurrent.__c.runMethod(true,"IsNumber",(Object)(goal.mostCurrent._txtaddedgoal1.runMethod(true,"getText"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 68;BA.debugLine="maxAmt = txtaddedgoal1.Text";
Debug.ShouldStop(8);
_maxamt = BA.numberCast(double.class, goal.mostCurrent._txtaddedgoal1.runMethod(true,"getText"));Debug.locals.put("maxAmt", _maxamt);
 }else {
 BA.debugLineNum = 70;BA.debugLine="Msgbox(\"Target amount must be a number\",\"\")";
Debug.ShouldStop(32);
goal.mostCurrent.__c.runVoidMethodAndSync ("Msgbox",(Object)(BA.ObjectToCharSequence("Target amount must be a number")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable(""))),goal.mostCurrent.activityBA);
 BA.debugLineNum = 71;BA.debugLine="Return";
Debug.ShouldStop(64);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 74;BA.debugLine="If IsNumber(txtaddedgoal1.Text) Then";
Debug.ShouldStop(512);
if (goal.mostCurrent.__c.runMethod(true,"IsNumber",(Object)(goal.mostCurrent._txtaddedgoal1.runMethod(true,"getText"))).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 75;BA.debugLine="currentAmt = txtaddedgoal1.Text";
Debug.ShouldStop(1024);
_currentamt = BA.numberCast(double.class, goal.mostCurrent._txtaddedgoal1.runMethod(true,"getText"));Debug.locals.put("currentAmt", _currentamt);
 }else {
 BA.debugLineNum = 77;BA.debugLine="currentAmt = 0   ' default kung walang laman";
Debug.ShouldStop(4096);
_currentamt = BA.numberCast(double.class, 0);Debug.locals.put("currentAmt", _currentamt);
 };
 BA.debugLineNum = 80;BA.debugLine="If category = \"\" Or maxAmt <= 0 Then";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean("=",_category,BA.ObjectToString("")) || RemoteObject.solveBoolean("k",_maxamt,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 81;BA.debugLine="Msgbox(\"Please enter a valid category and target";
Debug.ShouldStop(65536);
goal.mostCurrent.__c.runVoidMethodAndSync ("Msgbox",(Object)(BA.ObjectToCharSequence("Please enter a valid category and target amount")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable(""))),goal.mostCurrent.activityBA);
 BA.debugLineNum = 82;BA.debugLine="Return";
Debug.ShouldStop(131072);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 85;BA.debugLine="sql.ExecNonQuery2(\"INSERT INTO tblgoal (category,";
Debug.ShouldStop(1048576);
goal._sql.runVoidMethod ("ExecNonQuery2",(Object)(BA.ObjectToString("INSERT INTO tblgoal (category, goal-amount, current_amount) VALUES (?, ?, ?)")),(Object)(goal.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("Object",new int[] {4},new Object[] {(_category),RemoteObject.createImmutable((0)),(_maxamt),(_currentamt)})))));
 BA.debugLineNum = 87;BA.debugLine="ToastMessageShow(\"Goal Added: \" & category & \" -";
Debug.ShouldStop(4194304);
goal.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Goal Added: "),_category,RemoteObject.createImmutable(" - ₱"),_currentamt,RemoteObject.createImmutable("/"),_maxamt))),(Object)(goal.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 88;BA.debugLine="LoadGoals";
Debug.ShouldStop(8388608);
_loadgoals();
 BA.debugLineNum = 90;BA.debugLine="txtgoalcategory1.Text = \"\"";
Debug.ShouldStop(33554432);
goal.mostCurrent._txtgoalcategory1.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 91;BA.debugLine="txtaddedgoal1.Text = \"\"";
Debug.ShouldStop(67108864);
goal.mostCurrent._txtaddedgoal1.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 92;BA.debugLine="txtaddedgoal1.Text = \"\"";
Debug.ShouldStop(134217728);
goal.mostCurrent._txtaddedgoal1.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 93;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnmenu_click() throws Exception{
try {
		Debug.PushSubsStack("btnmenu_Click (goal) ","goal",5,goal.mostCurrent.activityBA,goal.mostCurrent,34);
if (RapidSub.canDelegate("btnmenu_click")) { return b4a.example.goal.remoteMe.runUserSub(false, "goal","btnmenu_click");}
 BA.debugLineNum = 34;BA.debugLine="Private Sub btnmenu_Click";
Debug.ShouldStop(2);
 BA.debugLineNum = 35;BA.debugLine="pnlmenu.Visible = True";
Debug.ShouldStop(4);
goal.mostCurrent._pnlmenu.runMethod(true,"setVisible",goal.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 36;BA.debugLine="End Sub";
Debug.ShouldStop(8);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 15;BA.debugLine="Private pnlmenu As Panel";
goal.mostCurrent._pnlmenu = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 16;BA.debugLine="Private Button1 As Button";
goal.mostCurrent._button1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private txtaddedgoal1 As EditText";
goal.mostCurrent._txtaddedgoal1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private txtgoalcategory1 As EditText";
goal.mostCurrent._txtgoalcategory1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private ProgressBar1 As ProgressBar";
goal.mostCurrent._progressbar1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ProgressBarWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private btnaddgoal As Button";
goal.mostCurrent._btnaddgoal = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _labelaccount_click() throws Exception{
try {
		Debug.PushSubsStack("labelaccount_Click (goal) ","goal",5,goal.mostCurrent.activityBA,goal.mostCurrent,54);
if (RapidSub.canDelegate("labelaccount_click")) { return b4a.example.goal.remoteMe.runUserSub(false, "goal","labelaccount_click");}
 BA.debugLineNum = 54;BA.debugLine="Private Sub labelaccount_Click";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 55;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(4194304);
goal.mostCurrent._pnlmenu.runMethod(true,"setVisible",goal.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 56;BA.debugLine="StartActivity(account)";
Debug.ShouldStop(8388608);
goal.mostCurrent.__c.runVoidMethod ("StartActivity",goal.processBA,(Object)((goal.mostCurrent._account.getObject())));
 BA.debugLineNum = 57;BA.debugLine="Activity.Finish";
Debug.ShouldStop(16777216);
goal.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 58;BA.debugLine="End Sub";
Debug.ShouldStop(33554432);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _labelexpenses_click() throws Exception{
try {
		Debug.PushSubsStack("labelexpenses_Click (goal) ","goal",5,goal.mostCurrent.activityBA,goal.mostCurrent,44);
if (RapidSub.canDelegate("labelexpenses_click")) { return b4a.example.goal.remoteMe.runUserSub(false, "goal","labelexpenses_click");}
 BA.debugLineNum = 44;BA.debugLine="Private Sub labelexpenses_Click";
Debug.ShouldStop(2048);
 BA.debugLineNum = 45;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(4096);
goal.mostCurrent._pnlmenu.runMethod(true,"setVisible",goal.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 46;BA.debugLine="StartActivity(expenses)";
Debug.ShouldStop(8192);
goal.mostCurrent.__c.runVoidMethod ("StartActivity",goal.processBA,(Object)((goal.mostCurrent._expenses.getObject())));
 BA.debugLineNum = 47;BA.debugLine="Activity.Finish";
Debug.ShouldStop(16384);
goal.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 48;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _labelgoal_click() throws Exception{
try {
		Debug.PushSubsStack("labelgoal_Click (goal) ","goal",5,goal.mostCurrent.activityBA,goal.mostCurrent,50);
if (RapidSub.canDelegate("labelgoal_click")) { return b4a.example.goal.remoteMe.runUserSub(false, "goal","labelgoal_click");}
 BA.debugLineNum = 50;BA.debugLine="Private Sub labelgoal_Click";
Debug.ShouldStop(131072);
 BA.debugLineNum = 51;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(262144);
goal.mostCurrent._pnlmenu.runMethod(true,"setVisible",goal.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 52;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _labelhome_click() throws Exception{
try {
		Debug.PushSubsStack("labelhome_Click (goal) ","goal",5,goal.mostCurrent.activityBA,goal.mostCurrent,38);
if (RapidSub.canDelegate("labelhome_click")) { return b4a.example.goal.remoteMe.runUserSub(false, "goal","labelhome_click");}
 BA.debugLineNum = 38;BA.debugLine="Private Sub labelhome_Click";
Debug.ShouldStop(32);
 BA.debugLineNum = 39;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(64);
goal.mostCurrent._pnlmenu.runMethod(true,"setVisible",goal.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 40;BA.debugLine="StartActivity(home)";
Debug.ShouldStop(128);
goal.mostCurrent.__c.runVoidMethod ("StartActivity",goal.processBA,(Object)((goal.mostCurrent._home.getObject())));
 BA.debugLineNum = 41;BA.debugLine="Activity.Finish";
Debug.ShouldStop(256);
goal.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 42;BA.debugLine="End Sub";
Debug.ShouldStop(512);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _loadgoals() throws Exception{
try {
		Debug.PushSubsStack("LoadGoals (goal) ","goal",5,goal.mostCurrent.activityBA,goal.mostCurrent,95);
if (RapidSub.canDelegate("loadgoals")) { return b4a.example.goal.remoteMe.runUserSub(false, "goal","loadgoals");}
RemoteObject _c = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL.CursorWrapper");
 BA.debugLineNum = 95;BA.debugLine="Sub LoadGoals";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 96;BA.debugLine="Dim c As Cursor";
Debug.ShouldStop(-2147483648);
_c = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL.CursorWrapper");Debug.locals.put("c", _c);
 BA.debugLineNum = 97;BA.debugLine="c = sql.ExecQuery(\"SELECT category, goal_amount,";
Debug.ShouldStop(1);
_c = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.sql.SQL.CursorWrapper"), goal._sql.runMethod(false,"ExecQuery",(Object)(RemoteObject.createImmutable("SELECT category, goal_amount, current_amount FROM tblgoal"))));Debug.locals.put("c", _c);
 BA.debugLineNum = 98;BA.debugLine="If c.RowCount > 0 Then";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean(">",_c.runMethod(true,"getRowCount"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 99;BA.debugLine="c.Position = 0";
Debug.ShouldStop(4);
_c.runMethod(true,"setPosition",BA.numberCast(int.class, 0));
 BA.debugLineNum = 100;BA.debugLine="txtgoalcategory1.Text = c.GetString2(0)";
Debug.ShouldStop(8);
goal.mostCurrent._txtgoalcategory1.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_c.runMethod(true,"GetString2",(Object)(BA.numberCast(int.class, 0)))));
 BA.debugLineNum = 101;BA.debugLine="txtaddedgoal1.Text = c.GetDouble2(1)";
Debug.ShouldStop(16);
goal.mostCurrent._txtaddedgoal1.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_c.runMethod(true,"GetDouble2",(Object)(BA.numberCast(int.class, 1)))));
 BA.debugLineNum = 102;BA.debugLine="txtaddedgoal1.Text = c.GetDouble2(2)";
Debug.ShouldStop(32);
goal.mostCurrent._txtaddedgoal1.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_c.runMethod(true,"GetDouble2",(Object)(BA.numberCast(int.class, 2)))));
 BA.debugLineNum = 104;BA.debugLine="If c.GetDouble2(2) > 0 Then";
Debug.ShouldStop(128);
if (RemoteObject.solveBoolean(">",_c.runMethod(true,"GetDouble2",(Object)(BA.numberCast(int.class, 2))),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 105;BA.debugLine="ProgressBar1.Progress = (c.GetDouble2(1) / c.Ge";
Debug.ShouldStop(256);
goal.mostCurrent._progressbar1.runMethod(true,"setProgress",BA.numberCast(int.class, RemoteObject.solve(new RemoteObject[] {(RemoteObject.solve(new RemoteObject[] {_c.runMethod(true,"GetDouble2",(Object)(BA.numberCast(int.class, 1))),_c.runMethod(true,"GetDouble2",(Object)(BA.numberCast(int.class, 2)))}, "/",0, 0)),RemoteObject.createImmutable(100)}, "*",0, 0)));
 }else {
 BA.debugLineNum = 107;BA.debugLine="ProgressBar1.Progress = 0";
Debug.ShouldStop(1024);
goal.mostCurrent._progressbar1.runMethod(true,"setProgress",BA.numberCast(int.class, 0));
 };
 };
 BA.debugLineNum = 110;BA.debugLine="c.Close";
Debug.ShouldStop(8192);
_c.runVoidMethod ("Close");
 BA.debugLineNum = 111;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="Dim sql As SQL";
goal._sql = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL");
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}