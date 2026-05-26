package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class account_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (account) ","account",6,account.mostCurrent.activityBA,account.mostCurrent,25);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.account.remoteMe.runUserSub(false, "account","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 25;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 26;BA.debugLine="sql = Main.sql";
Debug.ShouldStop(33554432);
account._sql = account.mostCurrent._main._sql /*RemoteObject*/ ;
 BA.debugLineNum = 28;BA.debugLine="Activity.LoadLayout(\"layaccount\")";
Debug.ShouldStop(134217728);
account.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("layaccount")),account.mostCurrent.activityBA);
 BA.debugLineNum = 29;BA.debugLine="pnlmenu.LoadLayout(\"laymenu\")";
Debug.ShouldStop(268435456);
account.mostCurrent._pnlmenu.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("laymenu")),account.mostCurrent.activityBA);
 BA.debugLineNum = 30;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(536870912);
account.mostCurrent._pnlmenu.runMethod(true,"setVisible",account.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 31;BA.debugLine="lblfullname.Text = Main.fname & \" \" & Main.lname";
Debug.ShouldStop(1073741824);
account.mostCurrent._lblfullname.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(account.mostCurrent._main._fname /*RemoteObject*/ ,RemoteObject.createImmutable(" "),account.mostCurrent._main._lname /*RemoteObject*/ )));
 BA.debugLineNum = 32;BA.debugLine="lblusername.Text = Main.usernamee";
Debug.ShouldStop(-2147483648);
account.mostCurrent._lblusername.runMethod(true,"setText",BA.ObjectToCharSequence(account.mostCurrent._main._usernamee /*RemoteObject*/ ));
 BA.debugLineNum = 33;BA.debugLine="lblemail.Text = Main.email";
Debug.ShouldStop(1);
account.mostCurrent._lblemail.runMethod(true,"setText",BA.ObjectToCharSequence(account.mostCurrent._main._email /*RemoteObject*/ ));
 BA.debugLineNum = 34;BA.debugLine="End Sub";
Debug.ShouldStop(2);
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
		Debug.PushSubsStack("Activity_Resume (account) ","account",6,account.mostCurrent.activityBA,account.mostCurrent,36);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.account.remoteMe.runUserSub(false, "account","activity_resume");}
 BA.debugLineNum = 36;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(8);
 BA.debugLineNum = 37;BA.debugLine="LoadHistory";
Debug.ShouldStop(16);
_loadhistory();
 BA.debugLineNum = 38;BA.debugLine="End Sub";
Debug.ShouldStop(32);
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
		Debug.PushSubsStack("btnmenu_Click (account) ","account",6,account.mostCurrent.activityBA,account.mostCurrent,40);
if (RapidSub.canDelegate("btnmenu_click")) { return b4a.example.account.remoteMe.runUserSub(false, "account","btnmenu_click");}
 BA.debugLineNum = 40;BA.debugLine="Private Sub btnmenu_Click";
Debug.ShouldStop(128);
 BA.debugLineNum = 41;BA.debugLine="pnlmenu.Visible = True";
Debug.ShouldStop(256);
account.mostCurrent._pnlmenu.runMethod(true,"setVisible",account.mostCurrent.__c.getField(true,"True"));
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
public static RemoteObject  _btnsetallowance_click() throws Exception{
try {
		Debug.PushSubsStack("btnsetallowance_Click (account) ","account",6,account.mostCurrent.activityBA,account.mostCurrent,66);
if (RapidSub.canDelegate("btnsetallowance_click")) { return b4a.example.account.remoteMe.runUserSub(false, "account","btnsetallowance_click");}
RemoteObject _allowanceamount = RemoteObject.createImmutable(0);
RemoteObject _date = RemoteObject.createImmutable("");
 BA.debugLineNum = 66;BA.debugLine="Private Sub btnsetallowance_Click";
Debug.ShouldStop(2);
 BA.debugLineNum = 67;BA.debugLine="Dim allowanceAmount As Double = EditText1.Text";
Debug.ShouldStop(4);
_allowanceamount = BA.numberCast(double.class, account.mostCurrent._edittext1.runMethod(true,"getText"));Debug.locals.put("allowanceAmount", _allowanceamount);Debug.locals.put("allowanceAmount", _allowanceamount);
 BA.debugLineNum = 68;BA.debugLine="Dim date As String = DateTime.Date(DateTime.Now)";
Debug.ShouldStop(8);
_date = account.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"Date",(Object)(account.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow")));Debug.locals.put("date", _date);Debug.locals.put("date", _date);
 BA.debugLineNum = 70;BA.debugLine="sql.ExecNonQuery2(\"INSERT INTO tblallowance (amou";
Debug.ShouldStop(32);
account._sql.runVoidMethod ("ExecNonQuery2",(Object)(BA.ObjectToString("INSERT INTO tblallowance (amount, date) VALUES (?, ?)")),(Object)(account.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("Object",new int[] {2},new Object[] {(_allowanceamount),(_date)})))));
 BA.debugLineNum = 71;BA.debugLine="sql.ExecNonQuery2(\"INSERT INTO tbltransac (type,";
Debug.ShouldStop(64);
account._sql.runVoidMethod ("ExecNonQuery2",(Object)(BA.ObjectToString("INSERT INTO tbltransac (type, amount, date) VALUES (?, ?, ?)")),(Object)(account.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("Object",new int[] {3},new Object[] {RemoteObject.createImmutable(("Allowance")),(_allowanceamount),(_date)})))));
 BA.debugLineNum = 73;BA.debugLine="LoadHistory";
Debug.ShouldStop(256);
_loadhistory();
 BA.debugLineNum = 74;BA.debugLine="ToastMessageShow(\"Allowance Set: \" & allowanceAmo";
Debug.ShouldStop(512);
account.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Allowance Set: "),_allowanceamount))),(Object)(account.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 75;BA.debugLine="EditText1.Text = \"\"";
Debug.ShouldStop(1024);
account.mostCurrent._edittext1.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 76;BA.debugLine="End Sub";
Debug.ShouldStop(2048);
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
account.mostCurrent._pnlmenu = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 16;BA.debugLine="Private Button1 As Button";
account.mostCurrent._button1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private lblfullname As Label";
account.mostCurrent._lblfullname = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private lblemail As Label";
account.mostCurrent._lblemail = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private lblusername As Label";
account.mostCurrent._lblusername = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private btneditexpenses As Button";
account.mostCurrent._btneditexpenses = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Private ListView1 As ListView";
account.mostCurrent._listview1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ListViewWrapper");
 //BA.debugLineNum = 22;BA.debugLine="Private EditText1 As EditText";
account.mostCurrent._edittext1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 23;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _labelaccount_click() throws Exception{
try {
		Debug.PushSubsStack("labelaccount_Click (account) ","account",6,account.mostCurrent.activityBA,account.mostCurrent,62);
if (RapidSub.canDelegate("labelaccount_click")) { return b4a.example.account.remoteMe.runUserSub(false, "account","labelaccount_click");}
 BA.debugLineNum = 62;BA.debugLine="Private Sub labelaccount_Click";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 63;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(1073741824);
account.mostCurrent._pnlmenu.runMethod(true,"setVisible",account.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 64;BA.debugLine="End Sub";
Debug.ShouldStop(-2147483648);
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
		Debug.PushSubsStack("labelexpenses_Click (account) ","account",6,account.mostCurrent.activityBA,account.mostCurrent,50);
if (RapidSub.canDelegate("labelexpenses_click")) { return b4a.example.account.remoteMe.runUserSub(false, "account","labelexpenses_click");}
 BA.debugLineNum = 50;BA.debugLine="Private Sub labelexpenses_Click";
Debug.ShouldStop(131072);
 BA.debugLineNum = 51;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(262144);
account.mostCurrent._pnlmenu.runMethod(true,"setVisible",account.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 52;BA.debugLine="StartActivity(expenses)";
Debug.ShouldStop(524288);
account.mostCurrent.__c.runVoidMethod ("StartActivity",account.processBA,(Object)((account.mostCurrent._expenses.getObject())));
 BA.debugLineNum = 53;BA.debugLine="Activity.Finish";
Debug.ShouldStop(1048576);
account.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 54;BA.debugLine="End Sub";
Debug.ShouldStop(2097152);
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
		Debug.PushSubsStack("labelgoal_Click (account) ","account",6,account.mostCurrent.activityBA,account.mostCurrent,56);
if (RapidSub.canDelegate("labelgoal_click")) { return b4a.example.account.remoteMe.runUserSub(false, "account","labelgoal_click");}
 BA.debugLineNum = 56;BA.debugLine="Private Sub labelgoal_Click";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 57;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(16777216);
account.mostCurrent._pnlmenu.runMethod(true,"setVisible",account.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 58;BA.debugLine="StartActivity(goal)";
Debug.ShouldStop(33554432);
account.mostCurrent.__c.runVoidMethod ("StartActivity",account.processBA,(Object)((account.mostCurrent._goal.getObject())));
 BA.debugLineNum = 59;BA.debugLine="Activity.Finish";
Debug.ShouldStop(67108864);
account.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 60;BA.debugLine="End Sub";
Debug.ShouldStop(134217728);
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
		Debug.PushSubsStack("labelhome_Click (account) ","account",6,account.mostCurrent.activityBA,account.mostCurrent,44);
if (RapidSub.canDelegate("labelhome_click")) { return b4a.example.account.remoteMe.runUserSub(false, "account","labelhome_click");}
 BA.debugLineNum = 44;BA.debugLine="Private Sub labelhome_Click";
Debug.ShouldStop(2048);
 BA.debugLineNum = 45;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(4096);
account.mostCurrent._pnlmenu.runMethod(true,"setVisible",account.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 46;BA.debugLine="StartActivity(home)";
Debug.ShouldStop(8192);
account.mostCurrent.__c.runVoidMethod ("StartActivity",account.processBA,(Object)((account.mostCurrent._home.getObject())));
 BA.debugLineNum = 47;BA.debugLine="Activity.Finish";
Debug.ShouldStop(16384);
account.mostCurrent._activity.runVoidMethod ("Finish");
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
public static RemoteObject  _loadhistory() throws Exception{
try {
		Debug.PushSubsStack("LoadHistory (account) ","account",6,account.mostCurrent.activityBA,account.mostCurrent,78);
if (RapidSub.canDelegate("loadhistory")) { return b4a.example.account.remoteMe.runUserSub(false, "account","loadhistory");}
RemoteObject _c1 = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL.CursorWrapper");
int _i = 0;
 BA.debugLineNum = 78;BA.debugLine="Sub LoadHistory";
Debug.ShouldStop(8192);
 BA.debugLineNum = 79;BA.debugLine="Dim c1 As Cursor";
Debug.ShouldStop(16384);
_c1 = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL.CursorWrapper");Debug.locals.put("c1", _c1);
 BA.debugLineNum = 80;BA.debugLine="c1 = sql.ExecQuery(\"SELECT type, amount, date FRO";
Debug.ShouldStop(32768);
_c1 = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.sql.SQL.CursorWrapper"), account._sql.runMethod(false,"ExecQuery",(Object)(RemoteObject.createImmutable("SELECT type, amount, date FROM tbltransac ORDER BY transac_id DESC"))));Debug.locals.put("c1", _c1);
 BA.debugLineNum = 81;BA.debugLine="ListView1.Clear";
Debug.ShouldStop(65536);
account.mostCurrent._listview1.runVoidMethod ("Clear");
 BA.debugLineNum = 82;BA.debugLine="For i = 0 To c1.RowCount - 1";
Debug.ShouldStop(131072);
{
final int step4 = 1;
final int limit4 = RemoteObject.solve(new RemoteObject[] {_c1.runMethod(true,"getRowCount"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step4 > 0 && _i <= limit4) || (step4 < 0 && _i >= limit4) ;_i = ((int)(0 + _i + step4))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 83;BA.debugLine="c1.Position = i";
Debug.ShouldStop(262144);
_c1.runMethod(true,"setPosition",BA.numberCast(int.class, _i));
 BA.debugLineNum = 84;BA.debugLine="ListView1.AddSingleLine(c1.GetString2(0) & \": ₱\"";
Debug.ShouldStop(524288);
account.mostCurrent._listview1.runVoidMethod ("AddSingleLine",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(_c1.runMethod(true,"GetString2",(Object)(BA.numberCast(int.class, 0))),RemoteObject.createImmutable(": ₱"),account.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(_c1.runMethod(true,"GetDouble2",(Object)(BA.numberCast(int.class, 1)))),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 2))),RemoteObject.createImmutable(" on "),_c1.runMethod(true,"GetString2",(Object)(BA.numberCast(int.class, 2)))))));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 86;BA.debugLine="c1.Close";
Debug.ShouldStop(2097152);
_c1.runVoidMethod ("Close");
 BA.debugLineNum = 87;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
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
account._sql = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL");
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}