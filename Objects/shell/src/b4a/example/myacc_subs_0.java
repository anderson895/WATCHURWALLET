package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class myacc_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (myacc) ","myacc",2,myacc.mostCurrent.activityBA,myacc.mostCurrent,24);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.myacc.remoteMe.runUserSub(false, "myacc","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 24;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(8388608);
 BA.debugLineNum = 26;BA.debugLine="Activity.LoadLayout(\"laymyacc\")";
Debug.ShouldStop(33554432);
myacc.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("laymyacc")),myacc.mostCurrent.activityBA);
 BA.debugLineNum = 27;BA.debugLine="sql = Main.sql";
Debug.ShouldStop(67108864);
myacc._sql = myacc.mostCurrent._main._sql /*RemoteObject*/ ;
 BA.debugLineNum = 29;BA.debugLine="LoadAccountInfo";
Debug.ShouldStop(268435456);
_loadaccountinfo();
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
		Debug.PushSubsStack("Activity_Pause (myacc) ","myacc",2,myacc.mostCurrent.activityBA,myacc.mostCurrent,36);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.myacc.remoteMe.runUserSub(false, "myacc","activity_pause", _userclosed);}
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
		Debug.PushSubsStack("Activity_Resume (myacc) ","myacc",2,myacc.mostCurrent.activityBA,myacc.mostCurrent,32);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.myacc.remoteMe.runUserSub(false, "myacc","activity_resume");}
 BA.debugLineNum = 32;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(-2147483648);
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
public static RemoteObject  _btneditflname_click() throws Exception{
try {
		Debug.PushSubsStack("btneditflname_Click (myacc) ","myacc",2,myacc.mostCurrent.activityBA,myacc.mostCurrent,52);
if (RapidSub.canDelegate("btneditflname_click")) { return b4a.example.myacc.remoteMe.runUserSub(false, "myacc","btneditflname_click");}
 BA.debugLineNum = 52;BA.debugLine="Private Sub btneditflname_Click";
Debug.ShouldStop(524288);
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
public static RemoteObject  _btnlogout_click() throws Exception{
try {
		Debug.PushSubsStack("btnlogout_Click (myacc) ","myacc",2,myacc.mostCurrent.activityBA,myacc.mostCurrent,56);
if (RapidSub.canDelegate("btnlogout_click")) { return b4a.example.myacc.remoteMe.runUserSub(false, "myacc","btnlogout_click");}
 BA.debugLineNum = 56;BA.debugLine="Private Sub btnlogout_Click";
Debug.ShouldStop(8388608);
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
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 12;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 16;BA.debugLine="Private EditText1 As EditText";
myacc.mostCurrent._edittext1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private ListView1 As ListView";
myacc.mostCurrent._listview1 = RemoteObject.createNew ("anywheresoftware.b4a.objects.ListViewWrapper");
 //BA.debugLineNum = 18;BA.debugLine="Private txtfname As EditText";
myacc.mostCurrent._txtfname = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 19;BA.debugLine="Private txtlname As EditText";
myacc.mostCurrent._txtlname = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 20;BA.debugLine="Private txtusername As EditText";
myacc.mostCurrent._txtusername = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 21;BA.debugLine="Private txtemail As EditText";
myacc.mostCurrent._txtemail = RemoteObject.createNew ("anywheresoftware.b4a.objects.EditTextWrapper");
 //BA.debugLineNum = 22;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _loadaccountinfo() throws Exception{
try {
		Debug.PushSubsStack("LoadAccountInfo (myacc) ","myacc",2,myacc.mostCurrent.activityBA,myacc.mostCurrent,40);
if (RapidSub.canDelegate("loadaccountinfo")) { return b4a.example.myacc.remoteMe.runUserSub(false, "myacc","loadaccountinfo");}
RemoteObject _c = RemoteObject.declareNull("anywheresoftware.b4a.sql.SQL.CursorWrapper");
 BA.debugLineNum = 40;BA.debugLine="Sub LoadAccountInfo";
Debug.ShouldStop(128);
 BA.debugLineNum = 41;BA.debugLine="Dim c As Cursor";
Debug.ShouldStop(256);
_c = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL.CursorWrapper");Debug.locals.put("c", _c);
 BA.debugLineNum = 42;BA.debugLine="c = sql.ExecQuery2(\"SELECT fname, lname FROM tblu";
Debug.ShouldStop(512);
_c = RemoteObject.declareNull("anywheresoftware.b4a.AbsObjectWrapper").runMethod(false, "ConvertToWrapper", RemoteObject.createNew("anywheresoftware.b4a.sql.SQL.CursorWrapper"), myacc._sql.runMethod(false,"ExecQuery2",(Object)(BA.ObjectToString("SELECT fname, lname FROM tblusers WHERE username=?")),(Object)(RemoteObject.createNewArray("String",new int[] {1},new Object[] {myacc.mostCurrent._main._usernamee /*RemoteObject*/ }))));Debug.locals.put("c", _c);
 BA.debugLineNum = 43;BA.debugLine="If c.RowCount > 0 Then";
Debug.ShouldStop(1024);
if (RemoteObject.solveBoolean(">",_c.runMethod(true,"getRowCount"),BA.numberCast(double.class, 0))) { 
 BA.debugLineNum = 44;BA.debugLine="c.Position = 0";
Debug.ShouldStop(2048);
_c.runMethod(true,"setPosition",BA.numberCast(int.class, 0));
 BA.debugLineNum = 45;BA.debugLine="txtfname.Text = c.GetString2(0)";
Debug.ShouldStop(4096);
myacc.mostCurrent._txtfname.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_c.runMethod(true,"GetString2",(Object)(BA.numberCast(int.class, 0)))));
 BA.debugLineNum = 46;BA.debugLine="txtlname.Text = c.GetString2(1)";
Debug.ShouldStop(8192);
myacc.mostCurrent._txtlname.runMethodAndSync(true,"setText",BA.ObjectToCharSequence(_c.runMethod(true,"GetString2",(Object)(BA.numberCast(int.class, 1)))));
 };
 BA.debugLineNum = 48;BA.debugLine="c.Close";
Debug.ShouldStop(32768);
_c.runVoidMethod ("Close");
 BA.debugLineNum = 49;BA.debugLine="End Sub";
Debug.ShouldStop(65536);
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
myacc._sql = RemoteObject.createNew ("anywheresoftware.b4a.sql.SQL");
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}