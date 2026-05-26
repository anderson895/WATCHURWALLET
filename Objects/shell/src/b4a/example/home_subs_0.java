package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class home_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (home) ","home",3,home.mostCurrent.activityBA,home.mostCurrent,35);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.home.remoteMe.runUserSub(false, "home","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 35;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(4);
 BA.debugLineNum = 36;BA.debugLine="sql = Main.sql";
Debug.ShouldStop(8);
home._sql = home.mostCurrent._main._sql /*RemoteObject*/ ;
 BA.debugLineNum = 37;BA.debugLine="Activity.LoadLayout(\"layhome\")";
Debug.ShouldStop(16);
home.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("layhome")),home.mostCurrent.activityBA);
 BA.debugLineNum = 38;BA.debugLine="pnlmenu.LoadLayout(\"laymenu\")";
Debug.ShouldStop(32);
home.mostCurrent._pnlmenu.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("laymenu")),home.mostCurrent.activityBA);
 BA.debugLineNum = 39;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(64);
home.mostCurrent._pnlmenu.runMethod(true,"setVisible",home.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 41;BA.debugLine="If ExpenseList.IsInitialized = False Then";
Debug.ShouldStop(256);
if (RemoteObject.solveBoolean("=",home._expenselist.runMethod(true,"IsInitialized"),home.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 42;BA.debugLine="ExpenseList.Initialize";
Debug.ShouldStop(512);
home._expenselist.runVoidMethod ("Initialize");
 };
 BA.debugLineNum = 45;BA.debugLine="If CategoryList.IsInitialized = False Then";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean("=",home._categorylist.runMethod(true,"IsInitialized"),home.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 46;BA.debugLine="CategoryList.Initialize";
Debug.ShouldStop(8192);
home._categorylist.runVoidMethod ("Initialize");
 };
 BA.debugLineNum = 49;BA.debugLine="spinnercategory.Add(\"Foods & Groceries\")";
Debug.ShouldStop(65536);
home.mostCurrent._spinnercategory.runVoidMethod ("Add",(Object)(RemoteObject.createImmutable("Foods & Groceries")));
 BA.debugLineNum = 50;BA.debugLine="spinnercategory.Add(\"Transpo\")";
Debug.ShouldStop(131072);
home.mostCurrent._spinnercategory.runVoidMethod ("Add",(Object)(RemoteObject.createImmutable("Transpo")));
 BA.debugLineNum = 51;BA.debugLine="spinnercategory.Add(\"Rent\")";
Debug.ShouldStop(262144);
home.mostCurrent._spinnercategory.runVoidMethod ("Add",(Object)(RemoteObject.createImmutable("Rent")));
 BA.debugLineNum = 52;BA.debugLine="spinnercategory.Add(\"School Supplies\")";
Debug.ShouldStop(524288);
home.mostCurrent._spinnercategory.runVoidMethod ("Add",(Object)(RemoteObject.createImmutable("School Supplies")));
 BA.debugLineNum = 53;BA.debugLine="spinnercategory.Add(\"School Projects\")";
Debug.ShouldStop(1048576);
home.mostCurrent._spinnercategory.runVoidMethod ("Add",(Object)(RemoteObject.createImmutable("School Projects")));
 BA.debugLineNum = 55;BA.debugLine="DateTime.DateFormat = \"MMMM dd, yyyy\"";
Debug.ShouldStop(4194304);
home.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"setDateFormat",BA.ObjectToString("MMMM dd, yyyy"));
 BA.debugLineNum = 56;BA.debugLine="txtdate.Text = DateTime.Date(DateTime.Now)";
Debug.ShouldStop(8388608);
home.mostCurrent._txtdate.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(home.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"Date",(Object)(home.mostCurrent.__c.getField(false,"DateTime").runMethod(true,"getNow")))));
 BA.debugLineNum = 58;BA.debugLine="txtspent.Text = \"0.00\"";
Debug.ShouldStop(33554432);
home.mostCurrent._txtspent.runMethodAndSync(true,"setText",BA.ObjectToCharSequence("0.00"));
 BA.debugLineNum = 59;BA.debugLine="txtbalance.Text = \"0.00\"";
Debug.ShouldStop(67108864);
home.mostCurrent._txtbalance.runMethodAndSync(true,"setText",BA.ObjectToCharSequence("0.00"));
 BA.debugLineNum = 61;BA.debugLine="txtspent.Enabled = False";
Debug.ShouldStop(268435456);
home.mostCurrent._txtspent.runMethod(true,"setEnabled",home.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 62;BA.debugLine="txtbalance.Enabled = False";
Debug.ShouldStop(536870912);
home.mostCurrent._txtbalance.runMethod(true,"setEnabled",home.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 63;BA.debugLine="txtdate.Enabled = False";
Debug.ShouldStop(1073741824);
home.mostCurrent._txtdate.runMethod(true,"setEnabled",home.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 65;BA.debugLine="txtallowance.Background = Null";
Debug.ShouldStop(1);
home.mostCurrent._txtallowance.runMethod(false,"setBackground",(home.mostCurrent.__c.getField(false,"Null")));
 BA.debugLineNum = 66;BA.debugLine="txtamountexpenses.Background = Null";
Debug.ShouldStop(2);
home.mostCurrent._txtamountexpenses.runMethod(false,"setBackground",(home.mostCurrent.__c.getField(false,"Null")));
 BA.debugLineNum = 67;BA.debugLine="txtgoal.Background = Null";
Debug.ShouldStop(4);
home.mostCurrent._txtgoal.runMethod(false,"setBackground",(home.mostCurrent.__c.getField(false,"Null")));
 BA.debugLineNum = 68;BA.debugLine="txtamountgoal.Background = Null";
Debug.ShouldStop(8);
home.mostCurrent._txtamountgoal.runMethod(false,"setBackground",(home.mostCurrent.__c.getField(false,"Null")));
 BA.debugLineNum = 70;BA.debugLine="ShowAllowance";
Debug.ShouldStop(32);
_showallowance();
 BA.debugLineNum = 71;BA.debugLine="End Sub";
Debug.ShouldStop(64);
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
		Debug.PushSubsStack("Activity_Resume (home) ","home",3,home.mostCurrent.activityBA,home.mostCurrent,73);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.home.remoteMe.runUserSub(false, "home","activity_resume");}
 BA.debugLineNum = 73;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(256);
 BA.debugLineNum = 74;BA.debugLine="ShowAllowance";
Debug.ShouldStop(512);
_showallowance();
 BA.debugLineNum = 75;BA.debugLine="End Sub";
Debug.ShouldStop(1024);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnacceptexpenses_click() throws Exception{
try {
		Debug.PushSubsStack("btnacceptexpenses_Click (home) ","home",3,home.mostCurrent.activityBA,home.mostCurrent,103);
if (RapidSub.canDelegate("btnacceptexpenses_click")) { return b4a.example.home.remoteMe.runUserSub(false, "home","btnacceptexpenses_click");}
RemoteObject _expense = RemoteObject.createImmutable(0);
RemoteObject _category = RemoteObject.createImmutable("");
 BA.debugLineNum = 103;BA.debugLine="Private Sub btnacceptexpenses_Click";
Debug.ShouldStop(64);
 BA.debugLineNum = 105;BA.debugLine="If txtamountexpenses.Text = \"\" Then";
Debug.ShouldStop(256);
if (RemoteObject.solveBoolean("=",home.mostCurrent._txtamountexpenses.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 106;BA.debugLine="ToastMessageShow(\"Please enter expense amount.\",";
Debug.ShouldStop(512);
home.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Please enter expense amount.")),(Object)(home.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 107;BA.debugLine="Return";
Debug.ShouldStop(1024);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 110;BA.debugLine="If IsNumber(txtamountexpenses.Text) = False Then";
Debug.ShouldStop(8192);
if (RemoteObject.solveBoolean("=",home.mostCurrent.__c.runMethod(true,"IsNumber",(Object)(home.mostCurrent._txtamountexpenses.runMethod(true,"getText"))),home.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 111;BA.debugLine="ToastMessageShow(\"Numbers only.\", False)";
Debug.ShouldStop(16384);
home.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Numbers only.")),(Object)(home.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 112;BA.debugLine="Return";
Debug.ShouldStop(32768);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 115;BA.debugLine="Dim Expense As Double";
Debug.ShouldStop(262144);
_expense = RemoteObject.createImmutable(0);Debug.locals.put("Expense", _expense);
 BA.debugLineNum = 116;BA.debugLine="Expense = txtamountexpenses.Text";
Debug.ShouldStop(524288);
_expense = BA.numberCast(double.class, home.mostCurrent._txtamountexpenses.runMethod(true,"getText"));Debug.locals.put("Expense", _expense);
 BA.debugLineNum = 118;BA.debugLine="If Expense > Balance Then";
Debug.ShouldStop(2097152);
if (RemoteObject.solveBoolean(">",_expense,home._balance)) { 
 BA.debugLineNum = 119;BA.debugLine="ToastMessageShow(\"Not enough balance.\", False)";
Debug.ShouldStop(4194304);
home.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Not enough balance.")),(Object)(home.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 120;BA.debugLine="Return";
Debug.ShouldStop(8388608);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 123;BA.debugLine="TotalSpent = TotalSpent + Expense";
Debug.ShouldStop(67108864);
home._totalspent = RemoteObject.solve(new RemoteObject[] {home._totalspent,_expense}, "+",1, 0);
 BA.debugLineNum = 125;BA.debugLine="txtspent.Text = NumberFormat(TotalSpent,1,2)";
Debug.ShouldStop(268435456);
home.mostCurrent._txtspent.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(home.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(home._totalspent),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 2)))));
 BA.debugLineNum = 127;BA.debugLine="Balance = Allowance - TotalSpent";
Debug.ShouldStop(1073741824);
home._balance = RemoteObject.solve(new RemoteObject[] {home._allowance,home._totalspent}, "-",1, 0);
 BA.debugLineNum = 129;BA.debugLine="txtbalance.Text = NumberFormat(Balance,1,2)";
Debug.ShouldStop(1);
home.mostCurrent._txtbalance.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(home.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(home._balance),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 2)))));
 BA.debugLineNum = 131;BA.debugLine="Dim Category As String";
Debug.ShouldStop(4);
_category = RemoteObject.createImmutable("");Debug.locals.put("Category", _category);
 BA.debugLineNum = 132;BA.debugLine="Category = spinnercategory.SelectedItem";
Debug.ShouldStop(8);
_category = home.mostCurrent._spinnercategory.runMethod(true,"getSelectedItem");Debug.locals.put("Category", _category);
 BA.debugLineNum = 134;BA.debugLine="ExpenseList.Add(Expense)";
Debug.ShouldStop(32);
home._expenselist.runVoidMethod ("Add",(Object)((_expense)));
 BA.debugLineNum = 135;BA.debugLine="CategoryList.Add(Category)";
Debug.ShouldStop(64);
home._categorylist.runVoidMethod ("Add",(Object)((_category)));
 BA.debugLineNum = 137;BA.debugLine="ToastMessageShow(\"Expense added to \" & Category,";
Debug.ShouldStop(256);
home.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("Expense added to "),_category))),(Object)(home.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 139;BA.debugLine="txtamountexpenses.Text = \"\"";
Debug.ShouldStop(1024);
home.mostCurrent._txtamountexpenses.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 141;BA.debugLine="ShowAllowance";
Debug.ShouldStop(4096);
_showallowance();
 BA.debugLineNum = 142;BA.debugLine="End Sub";
Debug.ShouldStop(8192);
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
		Debug.PushSubsStack("btnmenu_Click (home) ","home",3,home.mostCurrent.activityBA,home.mostCurrent,77);
if (RapidSub.canDelegate("btnmenu_click")) { return b4a.example.home.remoteMe.runUserSub(false, "home","btnmenu_click");}
 BA.debugLineNum = 77;BA.debugLine="Private Sub btnmenu_Click";
Debug.ShouldStop(4096);
 BA.debugLineNum = 78;BA.debugLine="pnlmenu.Visible = True";
Debug.ShouldStop(8192);
home.mostCurrent._pnlmenu.runMethod(true,"setVisible",home.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 79;BA.debugLine="End Sub";
Debug.ShouldStop(16384);
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
 //BA.debugLineNum = 13;BA.debugLine="Private pnlmenu As Panel";
home.mostCurrent._pnlmenu = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 14;BA.debugLine="Private btnmenu As Button";
home.mostCurrent._btnmenu = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 15;BA.debugLine="Private txtstudentsname As EditText";
home.mostCurrent._txtstudentsname = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 16;BA.debugLine="Private txtallowance As EditText";
home.mostCurrent._txtallowance = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private txtdate As EditText";
home.mostCurrent._txtdate = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private txtspent As EditText";
home.mostCurrent._txtspent = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private txtbalance As EditText";
home.mostCurrent._txtbalance = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private txtamountexpenses As EditText";
home.mostCurrent._txtamountexpenses = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Private spinnercategory As Spinner";
home.mostCurrent._spinnercategory = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");
 //BA.debugLineNum = 22;BA.debugLine="Private btnacceptexpenses As Button";
home.mostCurrent._btnacceptexpenses = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 23;BA.debugLine="Private txtamountgoal As EditText";
home.mostCurrent._txtamountgoal = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 24;BA.debugLine="Private btnacceptgoal As Button";
home.mostCurrent._btnacceptgoal = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Private txtgoal As EditText";
home.mostCurrent._txtgoal = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Dim TotalSpent As Double = 0";
home._totalspent = BA.numberCast(double.class, 0);
 //BA.debugLineNum = 28;BA.debugLine="Dim Balance As Double = 0";
home._balance = BA.numberCast(double.class, 0);
 //BA.debugLineNum = 29;BA.debugLine="Dim Allowance As Double = 0";
home._allowance = BA.numberCast(double.class, 0);
 //BA.debugLineNum = 31;BA.debugLine="Dim GoalAmount As Double = 0";
home._goalamount = BA.numberCast(double.class, 0);
 //BA.debugLineNum = 32;BA.debugLine="Dim GoalName As String";
home.mostCurrent._goalname = RemoteObject.createImmutable("");
 //BA.debugLineNum = 33;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _labelaccount_click() throws Exception{
try {
		Debug.PushSubsStack("labelaccount_Click (home) ","home",3,home.mostCurrent.activityBA,home.mostCurrent,97);
if (RapidSub.canDelegate("labelaccount_click")) { return b4a.example.home.remoteMe.runUserSub(false, "home","labelaccount_click");}
 BA.debugLineNum = 97;BA.debugLine="Private Sub labelaccount_Click";
Debug.ShouldStop(1);
 BA.debugLineNum = 98;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(2);
home.mostCurrent._pnlmenu.runMethod(true,"setVisible",home.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 99;BA.debugLine="StartActivity(account)";
Debug.ShouldStop(4);
home.mostCurrent.__c.runVoidMethod ("StartActivity",home.processBA,(Object)((home.mostCurrent._account.getObject())));
 BA.debugLineNum = 100;BA.debugLine="Activity.Finish";
Debug.ShouldStop(8);
home.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 101;BA.debugLine="End Sub";
Debug.ShouldStop(16);
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
		Debug.PushSubsStack("labelexpenses_Click (home) ","home",3,home.mostCurrent.activityBA,home.mostCurrent,85);
if (RapidSub.canDelegate("labelexpenses_click")) { return b4a.example.home.remoteMe.runUserSub(false, "home","labelexpenses_click");}
 BA.debugLineNum = 85;BA.debugLine="Private Sub labelexpenses_Click";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 86;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(2097152);
home.mostCurrent._pnlmenu.runMethod(true,"setVisible",home.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 87;BA.debugLine="StartActivity(expenses)";
Debug.ShouldStop(4194304);
home.mostCurrent.__c.runVoidMethod ("StartActivity",home.processBA,(Object)((home.mostCurrent._expenses.getObject())));
 BA.debugLineNum = 88;BA.debugLine="Activity.Finish";
Debug.ShouldStop(8388608);
home.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 89;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
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
		Debug.PushSubsStack("labelgoal_Click (home) ","home",3,home.mostCurrent.activityBA,home.mostCurrent,91);
if (RapidSub.canDelegate("labelgoal_click")) { return b4a.example.home.remoteMe.runUserSub(false, "home","labelgoal_click");}
 BA.debugLineNum = 91;BA.debugLine="Private Sub labelgoal_Click";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 92;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(134217728);
home.mostCurrent._pnlmenu.runMethod(true,"setVisible",home.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 93;BA.debugLine="StartActivity(goal)";
Debug.ShouldStop(268435456);
home.mostCurrent.__c.runVoidMethod ("StartActivity",home.processBA,(Object)((home.mostCurrent._goal.getObject())));
 BA.debugLineNum = 94;BA.debugLine="Activity.Finish";
Debug.ShouldStop(536870912);
home.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 95;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
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
		Debug.PushSubsStack("labelhome_Click (home) ","home",3,home.mostCurrent.activityBA,home.mostCurrent,81);
if (RapidSub.canDelegate("labelhome_click")) { return b4a.example.home.remoteMe.runUserSub(false, "home","labelhome_click");}
 BA.debugLineNum = 81;BA.debugLine="Private Sub labelhome_Click";
Debug.ShouldStop(65536);
 BA.debugLineNum = 82;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(131072);
home.mostCurrent._pnlmenu.runMethod(true,"setVisible",home.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 83;BA.debugLine="End Sub";
Debug.ShouldStop(262144);
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
 //BA.debugLineNum = 7;BA.debugLine="Public ExpenseList As List";
home._expenselist = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 8;BA.debugLine="Public CategoryList As List";
home._categorylist = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");
 //BA.debugLineNum = 9;BA.debugLine="Dim sql As SQL";
home._sql = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL");
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _showallowance() throws Exception{
try {
		Debug.PushSubsStack("ShowAllowance (home) ","home",3,home.mostCurrent.activityBA,home.mostCurrent,157);
if (RapidSub.canDelegate("showallowance")) { return b4a.example.home.remoteMe.runUserSub(false, "home","showallowance");}
RemoteObject _c = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL.CursorWrapper");
 BA.debugLineNum = 157;BA.debugLine="Sub ShowAllowance";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 158;BA.debugLine="Dim c As Cursor";
Debug.ShouldStop(536870912);
_c = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL.CursorWrapper");Debug.locals.put("c", _c);
 BA.debugLineNum = 159;BA.debugLine="c = sql.ExecQuery(\"SELECT SUM(amount) FROM tblallo";
Debug.ShouldStop(1073741824);
_c = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.sql.SQL.CursorWrapper"), home._sql.runMethod(false,"ExecQuery",(Object)(RemoteObject.createImmutable("SELECT SUM(amount) FROM tblallowance"))));Debug.locals.put("c", _c);
 BA.debugLineNum = 160;BA.debugLine="If c.RowCount > 0 Then";
Debug.ShouldStop(-2147483648);
if (RemoteObject.solveBoolean(">",_c.runMethod(true,"getRowCount"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 161;BA.debugLine="c.Position = 0";
Debug.ShouldStop(1);
_c.runMethod(true,"setPosition",BA.numberCast(int.class, 0));
 BA.debugLineNum = 162;BA.debugLine="Allowance = c.GetDouble2(0)";
Debug.ShouldStop(2);
home._allowance = _c.runMethod(true,"GetDouble2",(Object)(BA.numberCast(int.class, 0)));
 }else {
 BA.debugLineNum = 164;BA.debugLine="Allowance = 0";
Debug.ShouldStop(8);
home._allowance = BA.numberCast(double.class, 0);
 };
 BA.debugLineNum = 166;BA.debugLine="c.Close";
Debug.ShouldStop(32);
_c.runVoidMethod ("Close");
 BA.debugLineNum = 168;BA.debugLine="Balance = Allowance - TotalSpent";
Debug.ShouldStop(128);
home._balance = RemoteObject.solve(new RemoteObject[] {home._allowance,home._totalspent}, "-",1, 0);
 BA.debugLineNum = 170;BA.debugLine="txtallowance.Text = NumberFormat(Allowance,1,2)";
Debug.ShouldStop(512);
home.mostCurrent._txtallowance.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(home.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(home._allowance),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 2)))));
 BA.debugLineNum = 171;BA.debugLine="txtspent.Text = NumberFormat(TotalSpent,1,2)";
Debug.ShouldStop(1024);
home.mostCurrent._txtspent.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(home.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(home._totalspent),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 2)))));
 BA.debugLineNum = 172;BA.debugLine="txtbalance.Text = NumberFormat(Balance,1,2)";
Debug.ShouldStop(2048);
home.mostCurrent._txtbalance.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(home.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(home._balance),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 2)))));
 BA.debugLineNum = 173;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _txtallowance_textchanged(RemoteObject _old,RemoteObject _new) throws Exception{
try {
		Debug.PushSubsStack("txtallowance_TextChanged (home) ","home",3,home.mostCurrent.activityBA,home.mostCurrent,144);
if (RapidSub.canDelegate("txtallowance_textchanged")) { return b4a.example.home.remoteMe.runUserSub(false, "home","txtallowance_textchanged", _old, _new);}
Debug.locals.put("Old", _old);
Debug.locals.put("New", _new);
 BA.debugLineNum = 144;BA.debugLine="Private Sub txtallowance_TextChanged (Old As Strin";
Debug.ShouldStop(32768);
 BA.debugLineNum = 145;BA.debugLine="If New = \"\" Then";
Debug.ShouldStop(65536);
if (RemoteObject.solveBoolean("=",_new,BA.ObjectToString(""))) { 
 BA.debugLineNum = 146;BA.debugLine="txtbalance.Text = \"0.00\"";
Debug.ShouldStop(131072);
home.mostCurrent._txtbalance.runMethodAndSync(true,"setText",BA.ObjectToCharSequence("0.00"));
 BA.debugLineNum = 147;BA.debugLine="Return";
Debug.ShouldStop(262144);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 150;BA.debugLine="If IsNumber(New) Then";
Debug.ShouldStop(2097152);
if (home.mostCurrent.__c.runMethod(true,"IsNumber",(Object)(_new)).<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 151;BA.debugLine="Allowance = New";
Debug.ShouldStop(4194304);
home._allowance = BA.numberCast(double.class, _new);
 BA.debugLineNum = 152;BA.debugLine="Balance = Allowance - TotalSpent";
Debug.ShouldStop(8388608);
home._balance = RemoteObject.solve(new RemoteObject[] {home._allowance,home._totalspent}, "-",1, 0);
 BA.debugLineNum = 153;BA.debugLine="txtbalance.Text = NumberFormat(Balance,1,2)";
Debug.ShouldStop(16777216);
home.mostCurrent._txtbalance.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(home.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(home._balance),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 2)))));
 };
 BA.debugLineNum = 155;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}