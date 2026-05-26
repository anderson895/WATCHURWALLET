package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class main_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,30);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 30;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(536870912);
 BA.debugLineNum = 31;BA.debugLine="If FirstTime Then";
Debug.ShouldStop(1073741824);
if (_firsttime.<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 32;BA.debugLine="CopyDB";
Debug.ShouldStop(-2147483648);
_copydb();
 BA.debugLineNum = 33;BA.debugLine="sql.Initialize(File.DirInternal, \"saddbb.db\", Fa";
Debug.ShouldStop(1);
main._sql.runVoidMethod ("Initialize",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(BA.ObjectToString("saddbb.db")),(Object)(main.mostCurrent.__c.getField(true,"False")));
 };
 BA.debugLineNum = 35;BA.debugLine="Activity.LoadLayout(\"loginlayout\")";
Debug.ShouldStop(4);
main.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("loginlayout")),main.mostCurrent.activityBA);
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
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,38);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.main.remoteMe.runUserSub(false, "main","activity_resume");}
 BA.debugLineNum = 38;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(32);
 BA.debugLineNum = 39;BA.debugLine="txtUsername.Text = \"\"";
Debug.ShouldStop(64);
main.mostCurrent._txtusername.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 40;BA.debugLine="txtPassword.Text = \"\"";
Debug.ShouldStop(128);
main.mostCurrent._txtpassword.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(""));
 BA.debugLineNum = 41;BA.debugLine="txtUsername.RequestFocus";
Debug.ShouldStop(256);
main.mostCurrent._txtusername.runVoidMethod ("RequestFocus");
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
public static RemoteObject  _btnlogin_click() throws Exception{
try {
		Debug.PushSubsStack("btnLogin_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,43);
if (RapidSub.canDelegate("btnlogin_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnlogin_click");}
RemoteObject _rs = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL.ResultSetWrapper");
RemoteObject _role = RemoteObject.createImmutable("");
 BA.debugLineNum = 43;BA.debugLine="Private Sub btnLogin_Click";
Debug.ShouldStop(1024);
 BA.debugLineNum = 45;BA.debugLine="Dim rs As ResultSet";
Debug.ShouldStop(4096);
_rs = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL.ResultSetWrapper");Debug.locals.put("rs", _rs);
 BA.debugLineNum = 46;BA.debugLine="Dim role As String";
Debug.ShouldStop(8192);
_role = RemoteObject.createImmutable("");Debug.locals.put("role", _role);
 BA.debugLineNum = 48;BA.debugLine="rs = sql.ExecQuery2(\"SELECT * FROM tblusers WHERE";
Debug.ShouldStop(32768);
_rs = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.sql.SQL.ResultSetWrapper"), main._sql.runMethod(false,"ExecQuery2",(Object)(BA.ObjectToString("SELECT * FROM tblusers WHERE username=? AND password=?")),(Object)(RemoteObject.createNewArray("String",new int[] {2},new Object[] {main.mostCurrent._txtusername.runMethod(true,"getText"),main.mostCurrent._txtpassword.runMethod(true,"getText")}))));Debug.locals.put("rs", _rs);
 BA.debugLineNum = 50;BA.debugLine="If rs.NextRow Then";
Debug.ShouldStop(131072);
if (_rs.runMethod(true,"NextRow").<Boolean>get().booleanValue()) { 
 BA.debugLineNum = 51;BA.debugLine="role = rs.GetString(\"role\")";
Debug.ShouldStop(262144);
_role = _rs.runMethod(true,"GetString",(Object)(RemoteObject.createImmutable("role")));Debug.locals.put("role", _role);
 BA.debugLineNum = 52;BA.debugLine="usernamee = txtUsername.Text";
Debug.ShouldStop(524288);
main._usernamee = main.mostCurrent._txtusername.runMethod(true,"getText");
 BA.debugLineNum = 54;BA.debugLine="fname = rs.GetString(\"fname\")";
Debug.ShouldStop(2097152);
main._fname = _rs.runMethod(true,"GetString",(Object)(RemoteObject.createImmutable("fname")));
 BA.debugLineNum = 55;BA.debugLine="lname = rs.GetString(\"lname\")";
Debug.ShouldStop(4194304);
main._lname = _rs.runMethod(true,"GetString",(Object)(RemoteObject.createImmutable("lname")));
 BA.debugLineNum = 56;BA.debugLine="email = rs.GetString(\"email\")";
Debug.ShouldStop(8388608);
main._email = _rs.runMethod(true,"GetString",(Object)(RemoteObject.createImmutable("email")));
 BA.debugLineNum = 57;BA.debugLine="If role = \"admin\" Then";
Debug.ShouldStop(16777216);
if (RemoteObject.solveBoolean("=",_role,BA.ObjectToString("admin"))) { 
 BA.debugLineNum = 58;BA.debugLine="StartActivity(admin)";
Debug.ShouldStop(33554432);
main.mostCurrent.__c.runVoidMethod ("StartActivity",main.processBA,(Object)((main.mostCurrent._admin.getObject())));
 }else {
 BA.debugLineNum = 60;BA.debugLine="StartActivity(dashboard)";
Debug.ShouldStop(134217728);
main.mostCurrent.__c.runVoidMethod ("StartActivity",main.processBA,(Object)((main.mostCurrent._dashboard.getObject())));
 };
 }else {
 BA.debugLineNum = 63;BA.debugLine="ToastMessageShow(\"Invalid Login\", False)";
Debug.ShouldStop(1073741824);
main.mostCurrent.__c.runVoidMethod ("ToastMessageShow",(Object)(BA.ObjectToCharSequence("Invalid Login")),(Object)(main.mostCurrent.__c.getField(true,"False")));
 };
 BA.debugLineNum = 65;BA.debugLine="rs.Close";
Debug.ShouldStop(1);
_rs.runVoidMethod ("Close");
 BA.debugLineNum = 66;BA.debugLine="End Sub";
Debug.ShouldStop(2);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnsignup_click() throws Exception{
try {
		Debug.PushSubsStack("btnSignup_Click (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,68);
if (RapidSub.canDelegate("btnsignup_click")) { return b4a.example.main.remoteMe.runUserSub(false, "main","btnsignup_click");}
 BA.debugLineNum = 68;BA.debugLine="Private Sub btnSignup_Click";
Debug.ShouldStop(8);
 BA.debugLineNum = 69;BA.debugLine="StartActivity(signup)";
Debug.ShouldStop(16);
main.mostCurrent.__c.runVoidMethod ("StartActivity",main.processBA,(Object)((main.mostCurrent._signup.getObject())));
 BA.debugLineNum = 70;BA.debugLine="End Sub";
Debug.ShouldStop(32);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _copydb() throws Exception{
try {
		Debug.PushSubsStack("CopyDB (main) ","main",0,main.mostCurrent.activityBA,main.mostCurrent,72);
if (RapidSub.canDelegate("copydb")) { return b4a.example.main.remoteMe.runUserSub(false, "main","copydb");}
 BA.debugLineNum = 72;BA.debugLine="Sub CopyDB()";
Debug.ShouldStop(128);
 BA.debugLineNum = 73;BA.debugLine="If File.Exists(File.DirInternal, \"saddbb.db\") = F";
Debug.ShouldStop(256);
if (RemoteObject.solveBoolean("=",main.mostCurrent.__c.getField(false,"File").runMethod(true,"Exists",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("saddbb.db"))),main.mostCurrent.__c.getField(true,"False"))) { 
 BA.debugLineNum = 74;BA.debugLine="File.Copy(File.DirAssets, \"saddbb.db\", File.DirI";
Debug.ShouldStop(512);
main.mostCurrent.__c.getField(false,"File").runVoidMethod ("Copy",(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirAssets")),(Object)(BA.ObjectToString("saddbb.db")),(Object)(main.mostCurrent.__c.getField(false,"File").runMethod(true,"getDirInternal")),(Object)(RemoteObject.createImmutable("saddbb.db")));
 };
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
 //BA.debugLineNum = 23;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 24;BA.debugLine="Private txtUsername As EditText";
main.mostCurrent._txtusername = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 25;BA.debugLine="Private txtPassword As EditText";
main.mostCurrent._txtpassword = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 26;BA.debugLine="Private btnLogin As Button";
main.mostCurrent._btnlogin = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 27;BA.debugLine="Private btnSignup As Button";
main.mostCurrent._btnsignup = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 28;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}

public static void initializeProcessGlobals() {
    
    if (main.processGlobalsRun == false) {
	    main.processGlobalsRun = true;
		try {
		        main_subs_0._process_globals();
signup_subs_0._process_globals();
myacc_subs_0._process_globals();
home_subs_0._process_globals();
expenses_subs_0._process_globals();
goal_subs_0._process_globals();
account_subs_0._process_globals();
admin_subs_0._process_globals();
editexpenses_subs_0._process_globals();
editgoals_subs_0._process_globals();
dashboard_subs_0._process_globals();
starter_subs_0._process_globals();
xuiviewsutils_subs_0._process_globals();
main.myClass = BA.getDeviceClass ("b4a.example.main");
signup.myClass = BA.getDeviceClass ("b4a.example.signup");
myacc.myClass = BA.getDeviceClass ("b4a.example.myacc");
home.myClass = BA.getDeviceClass ("b4a.example.home");
expenses.myClass = BA.getDeviceClass ("b4a.example.expenses");
goal.myClass = BA.getDeviceClass ("b4a.example.goal");
account.myClass = BA.getDeviceClass ("b4a.example.account");
admin.myClass = BA.getDeviceClass ("b4a.example.admin");
editexpenses.myClass = BA.getDeviceClass ("b4a.example.editexpenses");
editgoals.myClass = BA.getDeviceClass ("b4a.example.editgoals");
dashboard.myClass = BA.getDeviceClass ("b4a.example.dashboard");
starter.myClass = BA.getDeviceClass ("b4a.example.starter");
b4xtable.myClass = BA.getDeviceClass ("b4a.example.b4xtable");
b4xformatter.myClass = BA.getDeviceClass ("b4a.example.b4xformatter");
animatedcounter.myClass = BA.getDeviceClass ("b4a.example.animatedcounter");
anotherprogressbar.myClass = BA.getDeviceClass ("b4a.example.anotherprogressbar");
b4xbreadcrumb.myClass = BA.getDeviceClass ("b4a.example.b4xbreadcrumb");
b4xcolortemplate.myClass = BA.getDeviceClass ("b4a.example.b4xcolortemplate");
b4xcombobox.myClass = BA.getDeviceClass ("b4a.example.b4xcombobox");
b4xdatetemplate.myClass = BA.getDeviceClass ("b4a.example.b4xdatetemplate");
b4xdialog.myClass = BA.getDeviceClass ("b4a.example.b4xdialog");
b4xfloattextfield.myClass = BA.getDeviceClass ("b4a.example.b4xfloattextfield");
b4ximageview.myClass = BA.getDeviceClass ("b4a.example.b4ximageview");
b4xinputtemplate.myClass = BA.getDeviceClass ("b4a.example.b4xinputtemplate");
b4xlisttemplate.myClass = BA.getDeviceClass ("b4a.example.b4xlisttemplate");
b4xloadingindicator.myClass = BA.getDeviceClass ("b4a.example.b4xloadingindicator");
b4xlongtexttemplate.myClass = BA.getDeviceClass ("b4a.example.b4xlongtexttemplate");
b4xplusminus.myClass = BA.getDeviceClass ("b4a.example.b4xplusminus");
b4xprogressdialog.myClass = BA.getDeviceClass ("b4a.example.b4xprogressdialog");
b4xradiobutton.myClass = BA.getDeviceClass ("b4a.example.b4xradiobutton");
b4xsearchtemplate.myClass = BA.getDeviceClass ("b4a.example.b4xsearchtemplate");
b4xseekbar.myClass = BA.getDeviceClass ("b4a.example.b4xseekbar");
b4xsignaturetemplate.myClass = BA.getDeviceClass ("b4a.example.b4xsignaturetemplate");
b4xswitch.myClass = BA.getDeviceClass ("b4a.example.b4xswitch");
b4xtimedtemplate.myClass = BA.getDeviceClass ("b4a.example.b4xtimedtemplate");
madewithlove.myClass = BA.getDeviceClass ("b4a.example.madewithlove");
roundslider.myClass = BA.getDeviceClass ("b4a.example.roundslider");
scrollinglabel.myClass = BA.getDeviceClass ("b4a.example.scrollinglabel");
swiftbutton.myClass = BA.getDeviceClass ("b4a.example.swiftbutton");
xuiviewsutils.myClass = BA.getDeviceClass ("b4a.example.xuiviewsutils");
		
        } catch (Exception e) {
			throw new RuntimeException(e);
		}
    }
}public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 15;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 16;BA.debugLine="Dim sql As SQL";
main._sql = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL");
 //BA.debugLineNum = 17;BA.debugLine="Dim usernamee As String";
main._usernamee = RemoteObject.createImmutable("");
 //BA.debugLineNum = 18;BA.debugLine="Dim fname As String";
main._fname = RemoteObject.createImmutable("");
 //BA.debugLineNum = 19;BA.debugLine="Dim lname As String";
main._lname = RemoteObject.createImmutable("");
 //BA.debugLineNum = 20;BA.debugLine="Dim email As String";
main._email = RemoteObject.createImmutable("");
 //BA.debugLineNum = 21;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}