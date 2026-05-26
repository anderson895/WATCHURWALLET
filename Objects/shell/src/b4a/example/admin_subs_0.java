package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class admin_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (admin) ","admin",7,admin.mostCurrent.activityBA,admin.mostCurrent,18);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.admin.remoteMe.runUserSub(false, "admin","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 18;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(131072);
 BA.debugLineNum = 19;BA.debugLine="If FirstTime Then";
Debug.ShouldStop(262144);
if (_firsttime.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 20;BA.debugLine="sql.Initialize(File.DirInternal, \"saddbb.db\", Fa";
Debug.ShouldStop(524288);
admin._sql.runVoidMethod ("Initialize",(Object)(admin.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(BA.ObjectToString("saddbb.db")),(Object)(admin.mostCurrent.__c.getField(true,"False")));
 };
 BA.debugLineNum = 23;BA.debugLine="Activity.LoadLayout(\"AdminPanelLayout\")";
Debug.ShouldStop(4194304);
admin.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("AdminPanelLayout")),admin.mostCurrent.activityBA);
 BA.debugLineNum = 25;BA.debugLine="B4XTable1.AddColumn(\"Username\", B4XTable1.COLUMN_";
Debug.ShouldStop(16777216);
admin.mostCurrent._b4xtable1.runClassMethod (b4a.example.b4xtable.class, "_addcolumn" /*RemoteObject*/ ,(Object)(BA.ObjectToString("Username")),(Object)(admin.mostCurrent._b4xtable1.getField(true,"_column_type_text" /*RemoteObject*/ )));
 BA.debugLineNum = 26;BA.debugLine="B4XTable1.AddColumn(\"Password\", B4XTable1.COLUMN_";
Debug.ShouldStop(33554432);
admin.mostCurrent._b4xtable1.runClassMethod (b4a.example.b4xtable.class, "_addcolumn" /*RemoteObject*/ ,(Object)(BA.ObjectToString("Password")),(Object)(admin.mostCurrent._b4xtable1.getField(true,"_column_type_text" /*RemoteObject*/ )));
 BA.debugLineNum = 27;BA.debugLine="B4XTable1.AddColumn(\"Role\", B4XTable1.COLUMN_TYPE";
Debug.ShouldStop(67108864);
admin.mostCurrent._b4xtable1.runClassMethod (b4a.example.b4xtable.class, "_addcolumn" /*RemoteObject*/ ,(Object)(BA.ObjectToString("Role")),(Object)(admin.mostCurrent._b4xtable1.getField(true,"_column_type_text" /*RemoteObject*/ )));
 BA.debugLineNum = 29;BA.debugLine="ShowUsers";
Debug.ShouldStop(268435456);
_showusers();
 BA.debugLineNum = 30;BA.debugLine="End Sub";
Debug.ShouldStop(536870912);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _activity_pause(RemoteObject _userclosed) throws Exception{
try {
		Debug.PushSubsStack("Activity_Pause (admin) ","admin",7,admin.mostCurrent.activityBA,admin.mostCurrent,36);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.admin.remoteMe.runUserSub(false, "admin","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 36;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(8);
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
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (admin) ","admin",7,admin.mostCurrent.activityBA,admin.mostCurrent,32);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.admin.remoteMe.runUserSub(false, "admin","activity_resume");}
 BA.debugLineNum = 32;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(-2147483648);
 BA.debugLineNum = 33;BA.debugLine="ShowUsers";
Debug.ShouldStop(1);
_showusers();
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
public static RemoteObject  _btnback_click() throws Exception{
try {
		Debug.PushSubsStack("btnBack_Click (admin) ","admin",7,admin.mostCurrent.activityBA,admin.mostCurrent,125);
if (RapidSub.canDelegate("btnback_click")) { return b4a.example.admin.remoteMe.runUserSub(false, "admin","btnback_click");}
 BA.debugLineNum = 125;BA.debugLine="Private Sub btnBack_Click";
Debug.ShouldStop(268435456);
 BA.debugLineNum = 126;BA.debugLine="StartActivity(dashboard)";
Debug.ShouldStop(536870912);
admin.mostCurrent.__c.runVoidMethod ("StartActivity",admin.processBA,(Object)((admin.mostCurrent._dashboard.getObject())));
 BA.debugLineNum = 127;BA.debugLine="End Sub";
Debug.ShouldStop(1073741824);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btndelete_click() throws Exception{
try {
		Debug.PushSubsStack("btnDelete_Click (admin) ","admin",7,admin.mostCurrent.activityBA,admin.mostCurrent,59);
if (RapidSub.canDelegate("btndelete_click")) { return b4a.example.admin.remoteMe.runUserSub(false, "admin","btndelete_click");}
RemoteObject _uname = RemoteObject.createImmutable("");
RemoteObject _res = RemoteObject.createImmutable(0);
RemoteObject _c = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL.CursorWrapper");
RemoteObject _confirm = RemoteObject.createImmutable(0);
 BA.debugLineNum = 59;BA.debugLine="Private Sub btnDelete_Click";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 61;BA.debugLine="Dim uname As String";
Debug.ShouldStop(268435456);
_uname = RemoteObject.createImmutable("");Debug.locals.put("uname", _uname);
 BA.debugLineNum = 62;BA.debugLine="inpdlg.Input = \"\"";
Debug.ShouldStop(536870912);
admin.mostCurrent._inpdlg.runMethod(true,"setInput",BA.ObjectToString(""));
 BA.debugLineNum = 64;BA.debugLine="Dim res As Int = inpdlg.Show(\"Enter username to d";
Debug.ShouldStop(-2147483648);
_res = admin.mostCurrent._inpdlg.runMethodAndSync(true,"Show",(Object)(BA.ObjectToString("Enter username to delete")),(Object)(BA.ObjectToString("Delete User")),(Object)(BA.ObjectToString("OK")),(Object)(BA.ObjectToString("Cancel")),(Object)(BA.ObjectToString("")),admin.mostCurrent.activityBA,(Object)((admin.mostCurrent.__c.getField(false,"Null"))));Debug.locals.put("res", _res);Debug.locals.put("res", _res);
 BA.debugLineNum = 65;BA.debugLine="uname = inpdlg.Input";
Debug.ShouldStop(1);
_uname = admin.mostCurrent._inpdlg.runMethod(true,"getInput");Debug.locals.put("uname", _uname);
 BA.debugLineNum = 67;BA.debugLine="If res = DialogResponse.POSITIVE Then";
Debug.ShouldStop(4);
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, admin.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
 BA.debugLineNum = 69;BA.debugLine="Dim c As Cursor";
Debug.ShouldStop(16);
_c = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL.CursorWrapper");Debug.locals.put("c", _c);
 BA.debugLineNum = 70;BA.debugLine="c = sql.ExecQuery2(\"SELECT * FROM tblusers WHERE";
Debug.ShouldStop(32);
_c = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.sql.SQL.CursorWrapper"), admin._sql.runMethod(false,"ExecQuery2",(Object)(BA.ObjectToString("SELECT * FROM tblusers WHERE username = ?")),(Object)(RemoteObject.createNewArray("String",new int[] {1},new Object[] {_uname}))));Debug.locals.put("c", _c);
 BA.debugLineNum = 72;BA.debugLine="If c.RowCount > 0 Then";
Debug.ShouldStop(128);
if (RemoteObject.solveBoolean(">",_c.runMethod(true,"getRowCount"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 74;BA.debugLine="Dim confirm As Int";
Debug.ShouldStop(512);
_confirm = RemoteObject.createImmutable(0);Debug.locals.put("confirm", _confirm);
 BA.debugLineNum = 75;BA.debugLine="confirm = Msgbox2(\"Delete this user?\", \"Confirm";
Debug.ShouldStop(1024);
_confirm = admin.mostCurrent.__c.runMethodAndSync(true,"Msgbox2",(Object)(BA.ObjectToCharSequence("Delete this user?")),(Object)(BA.ObjectToCharSequence("Confirm")),(Object)(BA.ObjectToString("Yes")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("No")),(Object)((admin.mostCurrent.__c.getField(false,"Null"))),admin.mostCurrent.activityBA);Debug.locals.put("confirm", _confirm);
 BA.debugLineNum = 77;BA.debugLine="If confirm = DialogResponse.POSITIVE Then";
Debug.ShouldStop(4096);
if (RemoteObject.solveBoolean("=",_confirm,BA.numberCast(double.class, admin.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
 BA.debugLineNum = 78;BA.debugLine="sql.ExecNonQuery2(\"DELETE FROM tblusers WHERE";
Debug.ShouldStop(8192);
admin._sql.runVoidMethod ("ExecNonQuery2",(Object)(BA.ObjectToString("DELETE FROM tblusers WHERE username = ?")),(Object)(admin.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("String",new int[] {1},new Object[] {_uname})))));
 BA.debugLineNum = 79;BA.debugLine="ShowUsers";
Debug.ShouldStop(16384);
_showusers();
 BA.debugLineNum = 80;BA.debugLine="ToastMessageShow(\"User Deleted\", False)";
Debug.ShouldStop(32768);
admin.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("User Deleted")),(Object)(admin.mostCurrent.__c.getField(true,"False")));
 };
 }else {
 BA.debugLineNum = 84;BA.debugLine="Msgbox(\"User not found\", \"\")";
Debug.ShouldStop(524288);
admin.mostCurrent.__c.runVoidMethodAndSync ("Msgbox",(Object)(BA.ObjectToCharSequence("User not found")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable(""))),admin.mostCurrent.activityBA);
 };
 };
 BA.debugLineNum = 88;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnupdate_click() throws Exception{
try {
		Debug.PushSubsStack("btnUpdate_Click (admin) ","admin",7,admin.mostCurrent.activityBA,admin.mostCurrent,90);
if (RapidSub.canDelegate("btnupdate_click")) { return b4a.example.admin.remoteMe.runUserSub(false, "admin","btnupdate_click");}
RemoteObject _uname = RemoteObject.createImmutable("");
RemoteObject _res = RemoteObject.createImmutable(0);
RemoteObject _c = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL.CursorWrapper");
RemoteObject _newpass = RemoteObject.createImmutable("");
RemoteObject _newrole = RemoteObject.createImmutable("");
 BA.debugLineNum = 90;BA.debugLine="Private Sub btnUpdate_Click";
Debug.ShouldStop(33554432);
 BA.debugLineNum = 92;BA.debugLine="Dim uname As String";
Debug.ShouldStop(134217728);
_uname = RemoteObject.createImmutable("");Debug.locals.put("uname", _uname);
 BA.debugLineNum = 93;BA.debugLine="inpdlg.Input = \"\"";
Debug.ShouldStop(268435456);
admin.mostCurrent._inpdlg.runMethod(true,"setInput",BA.ObjectToString(""));
 BA.debugLineNum = 95;BA.debugLine="Dim res As Int = inpdlg.Show(\"Enter username to u";
Debug.ShouldStop(1073741824);
_res = admin.mostCurrent._inpdlg.runMethodAndSync(true,"Show",(Object)(BA.ObjectToString("Enter username to update")),(Object)(BA.ObjectToString("Update User")),(Object)(BA.ObjectToString("OK")),(Object)(BA.ObjectToString("Cancel")),(Object)(BA.ObjectToString("")),admin.mostCurrent.activityBA,(Object)((admin.mostCurrent.__c.getField(false,"Null"))));Debug.locals.put("res", _res);Debug.locals.put("res", _res);
 BA.debugLineNum = 96;BA.debugLine="uname = inpdlg.Input";
Debug.ShouldStop(-2147483648);
_uname = admin.mostCurrent._inpdlg.runMethod(true,"getInput");Debug.locals.put("uname", _uname);
 BA.debugLineNum = 98;BA.debugLine="If res = DialogResponse.POSITIVE Then";
Debug.ShouldStop(2);
if (RemoteObject.solveBoolean("=",_res,BA.numberCast(double.class, admin.mostCurrent.__c.getField(false,"DialogResponse").getField(true,"POSITIVE")))) { 
 BA.debugLineNum = 100;BA.debugLine="Dim c As Cursor";
Debug.ShouldStop(8);
_c = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL.CursorWrapper");Debug.locals.put("c", _c);
 BA.debugLineNum = 101;BA.debugLine="c = sql.ExecQuery2(\"SELECT * FROM tblusers WHERE";
Debug.ShouldStop(16);
_c = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.sql.SQL.CursorWrapper"), admin._sql.runMethod(false,"ExecQuery2",(Object)(BA.ObjectToString("SELECT * FROM tblusers WHERE username = ?")),(Object)(RemoteObject.createNewArray("String",new int[] {1},new Object[] {_uname}))));Debug.locals.put("c", _c);
 BA.debugLineNum = 103;BA.debugLine="If c.RowCount > 0 Then";
Debug.ShouldStop(64);
if (RemoteObject.solveBoolean(">",_c.runMethod(true,"getRowCount"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 105;BA.debugLine="inpdlg.Input = \"\"";
Debug.ShouldStop(256);
admin.mostCurrent._inpdlg.runMethod(true,"setInput",BA.ObjectToString(""));
 BA.debugLineNum = 106;BA.debugLine="inpdlg.Show(\"Enter new password\", \"Update\", \"OK";
Debug.ShouldStop(512);
admin.mostCurrent._inpdlg.runMethodAndSync(true,"Show",(Object)(BA.ObjectToString("Enter new password")),(Object)(BA.ObjectToString("Update")),(Object)(BA.ObjectToString("OK")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("")),admin.mostCurrent.activityBA,(Object)((admin.mostCurrent.__c.getField(false,"Null"))));
 BA.debugLineNum = 107;BA.debugLine="Dim newpass As String = inpdlg.Input";
Debug.ShouldStop(1024);
_newpass = admin.mostCurrent._inpdlg.runMethod(true,"getInput");Debug.locals.put("newpass", _newpass);Debug.locals.put("newpass", _newpass);
 BA.debugLineNum = 109;BA.debugLine="inpdlg.Input = \"\"";
Debug.ShouldStop(4096);
admin.mostCurrent._inpdlg.runMethod(true,"setInput",BA.ObjectToString(""));
 BA.debugLineNum = 110;BA.debugLine="inpdlg.Show(\"Enter role (admin/user)\", \"Update\"";
Debug.ShouldStop(8192);
admin.mostCurrent._inpdlg.runMethodAndSync(true,"Show",(Object)(BA.ObjectToString("Enter role (admin/user)")),(Object)(BA.ObjectToString("Update")),(Object)(BA.ObjectToString("OK")),(Object)(BA.ObjectToString("")),(Object)(BA.ObjectToString("")),admin.mostCurrent.activityBA,(Object)((admin.mostCurrent.__c.getField(false,"Null"))));
 BA.debugLineNum = 111;BA.debugLine="Dim newrole As String = inpdlg.Input";
Debug.ShouldStop(16384);
_newrole = admin.mostCurrent._inpdlg.runMethod(true,"getInput");Debug.locals.put("newrole", _newrole);Debug.locals.put("newrole", _newrole);
 BA.debugLineNum = 113;BA.debugLine="sql.ExecNonQuery2(\"UPDATE tblusers SET password";
Debug.ShouldStop(65536);
admin._sql.runVoidMethod ("ExecNonQuery2",(Object)(BA.ObjectToString("UPDATE tblusers SET password=?, role=? WHERE username=?")),(Object)(admin.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("Object",new int[] {3},new Object[] {(_newpass),(_newrole),(_uname)})))));
 BA.debugLineNum = 115;BA.debugLine="ShowUsers";
Debug.ShouldStop(262144);
_showusers();
 BA.debugLineNum = 116;BA.debugLine="ToastMessageShow(\"User Updated\", False)";
Debug.ShouldStop(524288);
admin.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("User Updated")),(Object)(admin.mostCurrent.__c.getField(true,"False")));
 }else {
 BA.debugLineNum = 119;BA.debugLine="Msgbox(\"User not found\", \"\")";
Debug.ShouldStop(4194304);
admin.mostCurrent.__c.runVoidMethodAndSync ("Msgbox",(Object)(BA.ObjectToCharSequence("User not found")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable(""))),admin.mostCurrent.activityBA);
 };
 };
 BA.debugLineNum = 123;BA.debugLine="End Sub";
Debug.ShouldStop(67108864);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 12;BA.debugLine="Private B4XTable1 As B4XTable";
admin.mostCurrent._b4xtable1 = RemoteObject.createNew ("b4a.example.b4xtable");
 //BA.debugLineNum = 13;BA.debugLine="Private btnDelete As Button";
admin.mostCurrent._btndelete = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 14;BA.debugLine="Private btnUpdate As Button";
admin.mostCurrent._btnupdate = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 15;BA.debugLine="Dim inpdlg As InputDialog";
admin.mostCurrent._inpdlg = RemoteObject.createNew ("anywheresoftware.b4a.agraham.dialogs.InputDialog");
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 7;BA.debugLine="Dim sql As SQL";
admin._sql = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL");
 //BA.debugLineNum = 8;BA.debugLine="Dim rs As ResultSet";
admin._rs = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL.ResultSetWrapper");
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _showusers() throws Exception{
try {
		Debug.PushSubsStack("ShowUsers (admin) ","admin",7,admin.mostCurrent.activityBA,admin.mostCurrent,40);
if (RapidSub.canDelegate("showusers")) { return b4a.example.admin.remoteMe.runUserSub(false, "admin","showusers");}
RemoteObject _data = RemoteObject.declareNull("anywheresoftware.b4a.objects.collections.List");
RemoteObject _row = null;
 BA.debugLineNum = 40;BA.debugLine="Sub ShowUsers";
Debug.ShouldStop(128);
 BA.debugLineNum = 41;BA.debugLine="Dim data As List";
Debug.ShouldStop(256);
_data = RemoteObject.createNew ("anywheresoftware.b4a.objects.collections.List");Debug.locals.put("data", _data);
 BA.debugLineNum = 42;BA.debugLine="data.Initialize";
Debug.ShouldStop(512);
_data.runVoidMethod ("Initialize");
 BA.debugLineNum = 44;BA.debugLine="rs = sql.ExecQuery(\"SELECT * FROM tblusers\")";
Debug.ShouldStop(2048);
admin._rs = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.sql.SQL.ResultSetWrapper"), admin._sql.runMethod(false,"ExecQuery",(Object)(RemoteObject.createImmutable("SELECT * FROM tblusers"))));
 BA.debugLineNum = 46;BA.debugLine="Do While rs.NextRow";
Debug.ShouldStop(8192);
while (admin._rs.runMethod(true,"NextRow").<Boolean>get().booleanValue()) {
 BA.debugLineNum = 47;BA.debugLine="Dim row(3) As Object";
Debug.ShouldStop(16384);
_row = RemoteObject.createNewArray ("Object", new int[] {3}, new Object[]{});Debug.locals.put("row", _row);
 BA.debugLineNum = 48;BA.debugLine="row(0) = rs.GetString(\"username\")";
Debug.ShouldStop(32768);
_row.setArrayElement ((admin._rs.runMethod(true,"GetString",(Object)(RemoteObject.createImmutable("username")))),BA.numberCast(int.class, 0));
 BA.debugLineNum = 49;BA.debugLine="row(1) = rs.GetString(\"password\")";
Debug.ShouldStop(65536);
_row.setArrayElement ((admin._rs.runMethod(true,"GetString",(Object)(RemoteObject.createImmutable("password")))),BA.numberCast(int.class, 1));
 BA.debugLineNum = 50;BA.debugLine="row(2) = rs.GetString(\"role\")";
Debug.ShouldStop(131072);
_row.setArrayElement ((admin._rs.runMethod(true,"GetString",(Object)(RemoteObject.createImmutable("role")))),BA.numberCast(int.class, 2));
 BA.debugLineNum = 51;BA.debugLine="data.Add(row)";
Debug.ShouldStop(262144);
_data.runVoidMethod ("Add",(Object)((_row)));
 }
;
 BA.debugLineNum = 54;BA.debugLine="rs.Close";
Debug.ShouldStop(2097152);
admin._rs.runVoidMethod ("Close");
 BA.debugLineNum = 55;BA.debugLine="B4XTable1.SetData(data)";
Debug.ShouldStop(4194304);
admin.mostCurrent._b4xtable1.runClassMethod (b4a.example.b4xtable.class, "_setdata" /*RemoteObject*/ ,(Object)(_data));
 BA.debugLineNum = 56;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
}