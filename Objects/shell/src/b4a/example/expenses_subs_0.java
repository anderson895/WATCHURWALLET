package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class expenses_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (expenses) ","expenses",4,expenses.mostCurrent.activityBA,expenses.mostCurrent,24);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.expenses.remoteMe.runUserSub(false, "expenses","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 24;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 25;BA.debugLine="Activity.LoadLayout(\"layexpenses\")";
Debug.ShouldStop(16777216);
expenses.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("layexpenses")),expenses.mostCurrent.activityBA);
 BA.debugLineNum = 27;BA.debugLine="pnlmenu.LoadLayout(\"laymenu\")";
Debug.ShouldStop(67108864);
expenses.mostCurrent._pnlmenu.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("laymenu")),expenses.mostCurrent.activityBA);
 BA.debugLineNum = 28;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(134217728);
expenses.mostCurrent._pnlmenu.runMethod(true,"setVisible",expenses.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 30;BA.debugLine="txttotweekexp.Enabled = False";
Debug.ShouldStop(536870912);
expenses.mostCurrent._txttotweekexp.runMethod(true,"setEnabled",expenses.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 32;BA.debugLine="LoadExpenses";
Debug.ShouldStop(-2147483648);
_loadexpenses();
 BA.debugLineNum = 33;BA.debugLine="LoadSpinnerCategories";
Debug.ShouldStop(1);
_loadspinnercategories();
 BA.debugLineNum = 35;BA.debugLine="txtsplitbill.Background = Null";
Debug.ShouldStop(4);
expenses.mostCurrent._txtsplitbill.runMethod(false,"setBackground",(expenses.mostCurrent.__c.getField(false,"Null")));
 BA.debugLineNum = 36;BA.debugLine="txtsplitwith.Background = Null";
Debug.ShouldStop(8);
expenses.mostCurrent._txtsplitwith.runMethod(false,"setBackground",(expenses.mostCurrent.__c.getField(false,"Null")));
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
public static RemoteObject  _btnevensplit_click() throws Exception{
try {
		Debug.PushSubsStack("btnevensplit_Click (expenses) ","expenses",4,expenses.mostCurrent.activityBA,expenses.mostCurrent,101);
if (RapidSub.canDelegate("btnevensplit_click")) { return b4a.example.expenses.remoteMe.runUserSub(false, "expenses","btnevensplit_click");}
RemoteObject _totalbill = RemoteObject.createImmutable(0);
RemoteObject _persons = RemoteObject.createImmutable(0);
RemoteObject _share = RemoteObject.createImmutable(0);
RemoteObject _category = RemoteObject.createImmutable("");
 BA.debugLineNum = 101;BA.debugLine="Private Sub btnevensplit_Click";
Debug.ShouldStop(16);
 BA.debugLineNum = 102;BA.debugLine="If txtsplitbill.Text = \"\" Or txtsplitwith.Text =";
Debug.ShouldStop(32);
if (RemoteObject.solveBoolean("=",expenses.mostCurrent._txtsplitbill.runMethod(true,"getText"),BA.ObjectToString("")) || RemoteObject.solveBoolean("=",expenses.mostCurrent._txtsplitwith.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 103;BA.debugLine="ToastMessageShow(\"Complete split information.\",";
Debug.ShouldStop(64);
expenses.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Complete split information.")),(Object)(expenses.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 104;BA.debugLine="Return";
Debug.ShouldStop(128);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 107;BA.debugLine="If IsNumber(txtsplitbill.Text) = False Then";
Debug.ShouldStop(1024);
if (RemoteObject.solveBoolean("=",expenses.mostCurrent.__c.runMethod(true,"IsNumber",(Object)(expenses.mostCurrent._txtsplitbill.runMethod(true,"getText"))),expenses.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 108;BA.debugLine="ToastMessageShow(\"Bill must be number.\", False)";
Debug.ShouldStop(2048);
expenses.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Bill must be number.")),(Object)(expenses.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 109;BA.debugLine="Return";
Debug.ShouldStop(4096);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 112;BA.debugLine="If IsNumber(txtsplitwith.Text) = False Then";
Debug.ShouldStop(32768);
if (RemoteObject.solveBoolean("=",expenses.mostCurrent.__c.runMethod(true,"IsNumber",(Object)(expenses.mostCurrent._txtsplitwith.runMethod(true,"getText"))),expenses.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 113;BA.debugLine="ToastMessageShow(\"Persons must be number.\", Fals";
Debug.ShouldStop(65536);
expenses.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Persons must be number.")),(Object)(expenses.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 114;BA.debugLine="Return";
Debug.ShouldStop(131072);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 117;BA.debugLine="Dim TotalBill As Double";
Debug.ShouldStop(1048576);
_totalbill = RemoteObject.createImmutable(0);Debug.locals.put("TotalBill", _totalbill);
 BA.debugLineNum = 118;BA.debugLine="Dim Persons As Int";
Debug.ShouldStop(2097152);
_persons = RemoteObject.createImmutable(0);Debug.locals.put("Persons", _persons);
 BA.debugLineNum = 119;BA.debugLine="Dim Share As Double";
Debug.ShouldStop(4194304);
_share = RemoteObject.createImmutable(0);Debug.locals.put("Share", _share);
 BA.debugLineNum = 120;BA.debugLine="Dim Category As String";
Debug.ShouldStop(8388608);
_category = RemoteObject.createImmutable("");Debug.locals.put("Category", _category);
 BA.debugLineNum = 122;BA.debugLine="TotalBill = txtsplitbill.Text";
Debug.ShouldStop(33554432);
_totalbill = BA.numberCast(double.class, expenses.mostCurrent._txtsplitbill.runMethod(true,"getText"));Debug.locals.put("TotalBill", _totalbill);
 BA.debugLineNum = 123;BA.debugLine="Persons = txtsplitwith.Text";
Debug.ShouldStop(67108864);
_persons = BA.numberCast(int.class, expenses.mostCurrent._txtsplitwith.runMethod(true,"getText"));Debug.locals.put("Persons", _persons);
 BA.debugLineNum = 125;BA.debugLine="If Persons <= 0 Then";
Debug.ShouldStop(268435456);
if (RemoteObject.solveBoolean("k",_persons,BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 126;BA.debugLine="ToastMessageShow(\"Persons must be greater than 0";
Debug.ShouldStop(536870912);
expenses.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Persons must be greater than 0.")),(Object)(expenses.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 127;BA.debugLine="Return";
Debug.ShouldStop(1073741824);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 130;BA.debugLine="Share = TotalBill / Persons";
Debug.ShouldStop(2);
_share = RemoteObject.solve(new RemoteObject[] {_totalbill,_persons}, "/",0, 0);Debug.locals.put("Share", _share);
 BA.debugLineNum = 132;BA.debugLine="Category = spinnergroup.SelectedItem";
Debug.ShouldStop(8);
_category = expenses.mostCurrent._spinnergroup.runMethod(true,"getSelectedItem");Debug.locals.put("Category", _category);
 BA.debugLineNum = 134;BA.debugLine="listsplit.AddSingleLine( _     Category & \" - \" &";
Debug.ShouldStop(32);
expenses.mostCurrent._listsplit.runVoidMethod ("AddSingleLine",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(_category,RemoteObject.createImmutable(" - "),_persons,RemoteObject.createImmutable(" Person - ₱"),expenses.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(_totalbill),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 2))),RemoteObject.createImmutable(" - ₱"),expenses.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(_share),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 2))),RemoteObject.createImmutable(" each")))));
 BA.debugLineNum = 140;BA.debugLine="ToastMessageShow(\"Bill successfully split.\", Fals";
Debug.ShouldStop(2048);
expenses.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Bill successfully split.")),(Object)(expenses.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 142;BA.debugLine="txtsplitbill.Text = \"\"";
Debug.ShouldStop(8192);
expenses.mostCurrent._txtsplitbill.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 143;BA.debugLine="txtsplitwith.Text = \"\"";
Debug.ShouldStop(16384);
expenses.mostCurrent._txtsplitwith.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 144;BA.debugLine="End Sub";
Debug.ShouldStop(32768);
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
		Debug.PushSubsStack("btnmenu_Click (expenses) ","expenses",4,expenses.mostCurrent.activityBA,expenses.mostCurrent,75);
if (RapidSub.canDelegate("btnmenu_click")) { return b4a.example.expenses.remoteMe.runUserSub(false, "expenses","btnmenu_click");}
 BA.debugLineNum = 75;BA.debugLine="Private Sub btnmenu_Click";
Debug.ShouldStop(1024);
 BA.debugLineNum = 76;BA.debugLine="pnlmenu.Visible = True";
Debug.ShouldStop(2048);
expenses.mostCurrent._pnlmenu.runMethod(true,"setVisible",expenses.mostCurrent.__c.getField(true,"True"));
 BA.debugLineNum = 77;BA.debugLine="End Sub";
Debug.ShouldStop(4096);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 10;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 11;BA.debugLine="Private pnlmenu As Panel";
expenses.mostCurrent._pnlmenu = RemoteObject.createNew ("anywheresoftware.b4a.objects.PanelWrapper");
 //BA.debugLineNum = 12;BA.debugLine="Private btnmenu As Button";
expenses.mostCurrent._btnmenu = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 14;BA.debugLine="Private txttotweekexp As EditText";
expenses.mostCurrent._txttotweekexp = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 15;BA.debugLine="Private listexpenses As ListView";
expenses.mostCurrent._listexpenses = RemoteObject.createNew ("anywheresoftware.b4a.objects.ListViewWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private txtsplitbill As EditText";
expenses.mostCurrent._txtsplitbill = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private spinnergroup As Spinner";
expenses.mostCurrent._spinnergroup = RemoteObject.createNew ("anywheresoftware.b4a.objects.SpinnerWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private btnevensplit As Button";
expenses.mostCurrent._btnevensplit = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private listsplit As ListView";
expenses.mostCurrent._listsplit = RemoteObject.createNew ("anywheresoftware.b4a.objects.ListViewWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Private txtsplitwith As EditText";
expenses.mostCurrent._txtsplitwith = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _labelaccount_click() throws Exception{
try {
		Debug.PushSubsStack("labelaccount_Click (expenses) ","expenses",4,expenses.mostCurrent.activityBA,expenses.mostCurrent,95);
if (RapidSub.canDelegate("labelaccount_click")) { return b4a.example.expenses.remoteMe.runUserSub(false, "expenses","labelaccount_click");}
 BA.debugLineNum = 95;BA.debugLine="Private Sub labelaccount_Click";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 96;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(-2147483648);
expenses.mostCurrent._pnlmenu.runMethod(true,"setVisible",expenses.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 97;BA.debugLine="StartActivity(account)";
Debug.ShouldStop(1);
expenses.mostCurrent.__c.runVoidMethod ("StartActivity",expenses.processBA,(Object)((expenses.mostCurrent._account.getObject())));
 BA.debugLineNum = 98;BA.debugLine="Activity.Finish";
Debug.ShouldStop(2);
expenses.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 99;BA.debugLine="End Sub";
Debug.ShouldStop(4);
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
		Debug.PushSubsStack("labelexpenses_Click (expenses) ","expenses",4,expenses.mostCurrent.activityBA,expenses.mostCurrent,85);
if (RapidSub.canDelegate("labelexpenses_click")) { return b4a.example.expenses.remoteMe.runUserSub(false, "expenses","labelexpenses_click");}
 BA.debugLineNum = 85;BA.debugLine="Private Sub labelexpenses_Click";
Debug.ShouldStop(1048576);
 BA.debugLineNum = 86;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(2097152);
expenses.mostCurrent._pnlmenu.runMethod(true,"setVisible",expenses.mostCurrent.__c.getField(true,"False"));
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
public static RemoteObject  _labelgoal_click() throws Exception{
try {
		Debug.PushSubsStack("labelgoal_Click (expenses) ","expenses",4,expenses.mostCurrent.activityBA,expenses.mostCurrent,89);
if (RapidSub.canDelegate("labelgoal_click")) { return b4a.example.expenses.remoteMe.runUserSub(false, "expenses","labelgoal_click");}
 BA.debugLineNum = 89;BA.debugLine="Private Sub labelgoal_Click";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 90;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(33554432);
expenses.mostCurrent._pnlmenu.runMethod(true,"setVisible",expenses.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 91;BA.debugLine="StartActivity(goal)";
Debug.ShouldStop(67108864);
expenses.mostCurrent.__c.runVoidMethod ("StartActivity",expenses.processBA,(Object)((expenses.mostCurrent._goal.getObject())));
 BA.debugLineNum = 92;BA.debugLine="Activity.Finish";
Debug.ShouldStop(134217728);
expenses.mostCurrent._activity.runVoidMethod ("Finish");
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
public static RemoteObject  _labelhome_click() throws Exception{
try {
		Debug.PushSubsStack("labelhome_Click (expenses) ","expenses",4,expenses.mostCurrent.activityBA,expenses.mostCurrent,79);
if (RapidSub.canDelegate("labelhome_click")) { return b4a.example.expenses.remoteMe.runUserSub(false, "expenses","labelhome_click");}
 BA.debugLineNum = 79;BA.debugLine="Private Sub labelhome_Click";
Debug.ShouldStop(16384);
 BA.debugLineNum = 80;BA.debugLine="pnlmenu.Visible = False";
Debug.ShouldStop(32768);
expenses.mostCurrent._pnlmenu.runMethod(true,"setVisible",expenses.mostCurrent.__c.getField(true,"False"));
 BA.debugLineNum = 81;BA.debugLine="StartActivity(home)";
Debug.ShouldStop(65536);
expenses.mostCurrent.__c.runVoidMethod ("StartActivity",expenses.processBA,(Object)((expenses.mostCurrent._home.getObject())));
 BA.debugLineNum = 82;BA.debugLine="Activity.Finish";
Debug.ShouldStop(131072);
expenses.mostCurrent._activity.runVoidMethod ("Finish");
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
public static RemoteObject  _loadexpenses() throws Exception{
try {
		Debug.PushSubsStack("LoadExpenses (expenses) ","expenses",4,expenses.mostCurrent.activityBA,expenses.mostCurrent,40);
if (RapidSub.canDelegate("loadexpenses")) { return b4a.example.expenses.remoteMe.runUserSub(false, "expenses","loadexpenses");}
RemoteObject _total = RemoteObject.createImmutable(0);
int _i = 0;
RemoteObject _expense = RemoteObject.createImmutable(0);
RemoteObject _category = RemoteObject.createImmutable("");
 BA.debugLineNum = 40;BA.debugLine="Sub LoadExpenses";
Debug.ShouldStop(128);
 BA.debugLineNum = 41;BA.debugLine="Dim Total As Double = 0";
Debug.ShouldStop(256);
_total = BA.numberCast(double.class, 0);Debug.locals.put("Total", _total);Debug.locals.put("Total", _total);
 BA.debugLineNum = 43;BA.debugLine="listexpenses.Clear";
Debug.ShouldStop(1024);
expenses.mostCurrent._listexpenses.runVoidMethod ("Clear");
 BA.debugLineNum = 44;BA.debugLine="If home.ExpenseList.IsInitialized = False Or home";
Debug.ShouldStop(2048);
if (RemoteObject.solveBoolean("=",expenses.mostCurrent._home._expenselist /*RemoteObject*/ .runMethod(true,"IsInitialized"),expenses.mostCurrent.__c.getField(true,"False")) || RemoteObject.solveBoolean("=",expenses.mostCurrent._home._expenselist /*RemoteObject*/ .runMethod(true,"getSize"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 45;BA.debugLine="ToastMessageShow(\"No expenses yet\", False)";
Debug.ShouldStop(4096);
expenses.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("No expenses yet")),(Object)(expenses.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 46;BA.debugLine="txttotweekexp.Text = \"₱0.00\"";
Debug.ShouldStop(8192);
expenses.mostCurrent._txttotweekexp.runMethodAndSync(true,"setText",BA.ObjectToCharSequence("₱0.00"));
 BA.debugLineNum = 47;BA.debugLine="Return";
Debug.ShouldStop(16384);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 50;BA.debugLine="For i = 0 To home.ExpenseList.Size - 1";
Debug.ShouldStop(131072);
{
final int step8 = 1;
final int limit8 = RemoteObject.solve(new RemoteObject[] {expenses.mostCurrent._home._expenselist /*RemoteObject*/ .runMethod(true,"getSize"),RemoteObject.createImmutable(1)}, "-",1, 1).<Integer>get().intValue();
_i = 0 ;
for (;(step8 > 0 && _i <= limit8) || (step8 < 0 && _i >= limit8) ;_i = ((int)(0 + _i + step8))  ) {
Debug.locals.put("i", _i);
 BA.debugLineNum = 52;BA.debugLine="Dim Expense As Double = home.ExpenseList.Get(i)";
Debug.ShouldStop(524288);
_expense = BA.numberCast(double.class, expenses.mostCurrent._home._expenselist /*RemoteObject*/ .runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("Expense", _expense);Debug.locals.put("Expense", _expense);
 BA.debugLineNum = 53;BA.debugLine="Dim Category As String = home.CategoryList.Get(i";
Debug.ShouldStop(1048576);
_category = BA.ObjectToString(expenses.mostCurrent._home._categorylist /*RemoteObject*/ .runMethod(false,"Get",(Object)(BA.numberCast(int.class, _i))));Debug.locals.put("Category", _category);Debug.locals.put("Category", _category);
 BA.debugLineNum = 55;BA.debugLine="Total = Total + Expense";
Debug.ShouldStop(4194304);
_total = RemoteObject.solve(new RemoteObject[] {_total,_expense}, "+",1, 0);Debug.locals.put("Total", _total);
 BA.debugLineNum = 57;BA.debugLine="listexpenses.AddSingleLine( _         Category &";
Debug.ShouldStop(16777216);
expenses.mostCurrent._listexpenses.runVoidMethod ("AddSingleLine",(Object)(BA.ObjectToCharSequence(RemoteObject.concat(_category,RemoteObject.createImmutable(" - ₱"),expenses.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(_expense),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 2)))))));
 }
}Debug.locals.put("i", _i);
;
 BA.debugLineNum = 62;BA.debugLine="txttotweekexp.Text = \"₱\" & NumberFormat(Total,1,2";
Debug.ShouldStop(536870912);
expenses.mostCurrent._txttotweekexp.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(RemoteObject.createImmutable("₱"),expenses.mostCurrent.__c.runMethod(true,"NumberFormat",(Object)(_total),(Object)(BA.numberCast(int.class, 1)),(Object)(BA.numberCast(int.class, 2))))));
 BA.debugLineNum = 63;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _loadspinnercategories() throws Exception{
try {
		Debug.PushSubsStack("LoadSpinnerCategories (expenses) ","expenses",4,expenses.mostCurrent.activityBA,expenses.mostCurrent,65);
if (RapidSub.canDelegate("loadspinnercategories")) { return b4a.example.expenses.remoteMe.runUserSub(false, "expenses","loadspinnercategories");}
 BA.debugLineNum = 65;BA.debugLine="Sub LoadSpinnerCategories";
Debug.ShouldStop(1);
 BA.debugLineNum = 66;BA.debugLine="spinnergroup.Clear";
Debug.ShouldStop(2);
expenses.mostCurrent._spinnergroup.runVoidMethod ("Clear");
 BA.debugLineNum = 68;BA.debugLine="spinnergroup.Add(\"Foods & Groceries\")";
Debug.ShouldStop(8);
expenses.mostCurrent._spinnergroup.runVoidMethod ("Add",(Object)(RemoteObject.createImmutable("Foods & Groceries")));
 BA.debugLineNum = 69;BA.debugLine="spinnergroup.Add(\"Transpo\")";
Debug.ShouldStop(16);
expenses.mostCurrent._spinnergroup.runVoidMethod ("Add",(Object)(RemoteObject.createImmutable("Transpo")));
 BA.debugLineNum = 70;BA.debugLine="spinnergroup.Add(\"Rent\")";
Debug.ShouldStop(32);
expenses.mostCurrent._spinnergroup.runVoidMethod ("Add",(Object)(RemoteObject.createImmutable("Rent")));
 BA.debugLineNum = 71;BA.debugLine="spinnergroup.Add(\"School Supplies\")";
Debug.ShouldStop(64);
expenses.mostCurrent._spinnergroup.runVoidMethod ("Add",(Object)(RemoteObject.createImmutable("School Supplies")));
 BA.debugLineNum = 72;BA.debugLine="spinnergroup.Add(\"School Projects\")";
Debug.ShouldStop(128);
expenses.mostCurrent._spinnergroup.runVoidMethod ("Add",(Object)(RemoteObject.createImmutable("School Projects")));
 BA.debugLineNum = 73;BA.debugLine="End Sub";
Debug.ShouldStop(256);
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
 //BA.debugLineNum = 8;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}