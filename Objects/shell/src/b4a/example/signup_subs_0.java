package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class signup_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (signup) ","signup",1,signup.mostCurrent.activityBA,signup.mostCurrent,18);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.signup.remoteMe.runUserSub(false, "signup","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 18;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(131072);
 BA.debugLineNum = 19;BA.debugLine="If FirstTime Then";
Debug.ShouldStop(262144);
if (_firsttime.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 20;BA.debugLine="sql.Initialize(File.DirInternal, \"saddbb.db\", Fa";
Debug.ShouldStop(524288);
signup._sql.runVoidMethod ("Initialize",(Object)(signup.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(BA.ObjectToString("saddbb.db")),(Object)(signup.mostCurrent.__c.getField(true,"False")));
 };
 BA.debugLineNum = 22;BA.debugLine="Activity.LoadLayout(\"SignupLayout\")";
Debug.ShouldStop(2097152);
signup.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("SignupLayout")),signup.mostCurrent.activityBA);
 BA.debugLineNum = 23;BA.debugLine="End Sub";
Debug.ShouldStop(4194304);
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
		Debug.PushSubsStack("Activity_Resume (signup) ","signup",1,signup.mostCurrent.activityBA,signup.mostCurrent,25);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.signup.remoteMe.runUserSub(false, "signup","activity_resume");}
 BA.debugLineNum = 25;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(16777216);
 BA.debugLineNum = 26;BA.debugLine="txtUser.Text = \"\"";
Debug.ShouldStop(33554432);
signup.mostCurrent._txtuser.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 27;BA.debugLine="txtPass.Text = \"\"";
Debug.ShouldStop(67108864);
signup.mostCurrent._txtpass.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 28;BA.debugLine="txtemail.Text = \"\"";
Debug.ShouldStop(134217728);
signup.mostCurrent._txtemail.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 29;BA.debugLine="txtfname.Text = \"\"";
Debug.ShouldStop(268435456);
signup.mostCurrent._txtfname.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 30;BA.debugLine="txtlname.Text = \"\"";
Debug.ShouldStop(536870912);
signup.mostCurrent._txtlname.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 32;BA.debugLine="txtUser.RequestFocus";
Debug.ShouldStop(-2147483648);
signup.mostCurrent._txtuser.runVoidMethod ("RequestFocus");
 BA.debugLineNum = 33;BA.debugLine="End Sub";
Debug.ShouldStop(1);
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
		Debug.PushSubsStack("btnBack_Click (signup) ","signup",1,signup.mostCurrent.activityBA,signup.mostCurrent,56);
if (RapidSub.canDelegate("btnback_click")) { return b4a.example.signup.remoteMe.runUserSub(false, "signup","btnback_click");}
 BA.debugLineNum = 56;BA.debugLine="Private Sub btnBack_Click";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 57;BA.debugLine="StartActivity(Main)";
Debug.ShouldStop(16777216);
signup.mostCurrent.__c.runVoidMethod ("StartActivity",signup.processBA,(Object)((signup.mostCurrent._main.getObject())));
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
public static RemoteObject  _btncreate_click() throws Exception{
try {
		Debug.PushSubsStack("btnCreate_Click (signup) ","signup",1,signup.mostCurrent.activityBA,signup.mostCurrent,35);
if (RapidSub.canDelegate("btncreate_click")) { return b4a.example.signup.remoteMe.runUserSub(false, "signup","btncreate_click");}
RemoteObject _rs = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL.ResultSetWrapper");
 BA.debugLineNum = 35;BA.debugLine="Private Sub btnCreate_Click";
Debug.ShouldStop(4);
 BA.debugLineNum = 36;BA.debugLine="If txtUser.Text = \"\" Or txtPass.Text = \"\" Or txtf";
Debug.ShouldStop(8);
if (RemoteObject.solveBoolean("=",signup.mostCurrent._txtuser.runMethod(true,"getText"),BA.ObjectToString("")) || RemoteObject.solveBoolean("=",signup.mostCurrent._txtpass.runMethod(true,"getText"),BA.ObjectToString("")) || RemoteObject.solveBoolean("=",signup.mostCurrent._txtfname.runMethod(true,"getText"),BA.ObjectToString("")) || RemoteObject.solveBoolean("=",signup.mostCurrent._txtlname.runMethod(true,"getText"),BA.ObjectToString("")) || RemoteObject.solveBoolean("=",signup.mostCurrent._txtemail.runMethod(true,"getText"),BA.ObjectToString(""))) { 
 BA.debugLineNum = 37;BA.debugLine="Msgbox(\"All fields must be completed.\", \"\")";
Debug.ShouldStop(16);
signup.mostCurrent.__c.runVoidMethodAndSync ("Msgbox",(Object)(BA.ObjectToCharSequence("All fields must be completed.")),(Object)(BA.ObjectToCharSequence(RemoteObject.createImmutable(""))),signup.mostCurrent.activityBA);
 BA.debugLineNum = 38;BA.debugLine="Return";
Debug.ShouldStop(32);
if (true) return RemoteObject.createImmutable("");
 };
 BA.debugLineNum = 42;BA.debugLine="Dim rs As ResultSet";
Debug.ShouldStop(512);
_rs = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL.ResultSetWrapper");Debug.locals.put("rs", _rs);
 BA.debugLineNum = 43;BA.debugLine="rs = sql.ExecQuery2(\"SELECT * FROM tblusers WHERE";
Debug.ShouldStop(1024);
_rs = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.sql.SQL.ResultSetWrapper"), signup._sql.runMethod(false,"ExecQuery2",(Object)(BA.ObjectToString("SELECT * FROM tblusers WHERE username = ?")),(Object)(RemoteObject.createNewArray("String",new int[] {1},new Object[] {signup.mostCurrent._txtuser.runMethod(true,"getText")}))));Debug.locals.put("rs", _rs);
 BA.debugLineNum = 45;BA.debugLine="If rs.NextRow Then";
Debug.ShouldStop(4096);
if (_rs.runMethod(true,"NextRow").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 46;BA.debugLine="ToastMessageShow(\"Username already exists!\", Fal";
Debug.ShouldStop(8192);
signup.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Username already exists!")),(Object)(signup.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 47;BA.debugLine="rs.Close";
Debug.ShouldStop(16384);
_rs.runVoidMethod ("Close");
 }else {
 BA.debugLineNum = 49;BA.debugLine="rs.Close";
Debug.ShouldStop(65536);
_rs.runVoidMethod ("Close");
 BA.debugLineNum = 50;BA.debugLine="sql.ExecNonQuery2(\"INSERT INTO tblusers VALUES (";
Debug.ShouldStop(131072);
signup._sql.runVoidMethod ("ExecNonQuery2",(Object)(BA.ObjectToString("INSERT INTO tblusers VALUES (?,?,?,?,?,?)")),(Object)(signup.mostCurrent.__c.runMethod(false, "ArrayToList", (Object)(RemoteObject.createNewArray("Object",new int[] {6},new Object[] {(signup.mostCurrent._txtuser.runMethod(true,"getText")),(signup.mostCurrent._txtpass.runMethod(true,"getText")),RemoteObject.createImmutable(("User")),(signup.mostCurrent._txtfname.runMethod(true,"getText")),(signup.mostCurrent._txtlname.runMethod(true,"getText")),(signup.mostCurrent._txtemail.runMethod(true,"getText"))})))));
 BA.debugLineNum = 51;BA.debugLine="ToastMessageShow(\"Account Created!\", False)";
Debug.ShouldStop(262144);
signup.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Account Created!")),(Object)(signup.mostCurrent.__c.getField(true,"False")));
 BA.debugLineNum = 52;BA.debugLine="Activity.Finish";
Debug.ShouldStop(524288);
signup.mostCurrent._activity.runVoidMethod ("Finish");
 };
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
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 9;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 10;BA.debugLine="Private txtUser As EditText";
signup.mostCurrent._txtuser = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 11;BA.debugLine="Private txtPass As EditText";
signup.mostCurrent._txtpass = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 12;BA.debugLine="Private btnCreate As Button";
signup.mostCurrent._btncreate = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 13;BA.debugLine="Private txtemail As EditText";
signup.mostCurrent._txtemail = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 14;BA.debugLine="Private txtfname As EditText";
signup.mostCurrent._txtfname = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 15;BA.debugLine="Private txtlname As EditText";
signup.mostCurrent._txtlname = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 16;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 5;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 6;BA.debugLine="Dim sql As SQL";
signup._sql = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL");
 //BA.debugLineNum = 7;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}