package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class editexpenses_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (editexpenses) ","editexpenses",8,editexpenses.mostCurrent.activityBA,editexpenses.mostCurrent,20);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.editexpenses.remoteMe.runUserSub(false, "editexpenses","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 20;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(524288);
 BA.debugLineNum = 22;BA.debugLine="Activity.LoadLayout(\"layeditexpenses\")";
Debug.ShouldStop(2097152);
editexpenses.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("layeditexpenses")),editexpenses.mostCurrent.activityBA);
 BA.debugLineNum = 24;BA.debugLine="End Sub";
Debug.ShouldStop(8388608);
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
		Debug.PushSubsStack("Activity_Pause (editexpenses) ","editexpenses",8,editexpenses.mostCurrent.activityBA,editexpenses.mostCurrent,30);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.editexpenses.remoteMe.runUserSub(false, "editexpenses","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 30;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(536870912);
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
public static RemoteObject  _activity_resume() throws Exception{
try {
		Debug.PushSubsStack("Activity_Resume (editexpenses) ","editexpenses",8,editexpenses.mostCurrent.activityBA,editexpenses.mostCurrent,26);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.editexpenses.remoteMe.runUserSub(false, "editexpenses","activity_resume");}
 BA.debugLineNum = 26;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(33554432);
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
public static RemoteObject  _btnback_click() throws Exception{
try {
		Debug.PushSubsStack("btnback_Click (editexpenses) ","editexpenses",8,editexpenses.mostCurrent.activityBA,editexpenses.mostCurrent,35);
if (RapidSub.canDelegate("btnback_click")) { return b4a.example.editexpenses.remoteMe.runUserSub(false, "editexpenses","btnback_click");}
 BA.debugLineNum = 35;BA.debugLine="Private Sub btnback_Click";
Debug.ShouldStop(4);
 BA.debugLineNum = 36;BA.debugLine="Activity.LoadLayout(\"layaccount\")";
Debug.ShouldStop(8);
editexpenses.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("layaccount")),editexpenses.mostCurrent.activityBA);
 BA.debugLineNum = 37;BA.debugLine="End Sub";
Debug.ShouldStop(16);
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
 //BA.debugLineNum = 16;BA.debugLine="Private btnback As Button";
editexpenses.mostCurrent._btnback = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 17;BA.debugLine="Private lblsavechanges As Label";
editexpenses.mostCurrent._lblsavechanges = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 18;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _lblsavechanges_click() throws Exception{
try {
		Debug.PushSubsStack("lblsavechanges_Click (editexpenses) ","editexpenses",8,editexpenses.mostCurrent.activityBA,editexpenses.mostCurrent,39);
if (RapidSub.canDelegate("lblsavechanges_click")) { return b4a.example.editexpenses.remoteMe.runUserSub(false, "editexpenses","lblsavechanges_click");}
 BA.debugLineNum = 39;BA.debugLine="Private Sub lblsavechanges_Click";
Debug.ShouldStop(64);
 BA.debugLineNum = 40;BA.debugLine="Activity.Finish";
Debug.ShouldStop(128);
editexpenses.mostCurrent._activity.runVoidMethod ("Finish");
 BA.debugLineNum = 41;BA.debugLine="End Sub";
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
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}