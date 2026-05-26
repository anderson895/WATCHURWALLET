package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class editgoals_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (editgoals) ","editgoals",9,editgoals.mostCurrent.activityBA,editgoals.mostCurrent,17);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.editgoals.remoteMe.runUserSub(false, "editgoals","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 17;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(65536);
 BA.debugLineNum = 19;BA.debugLine="Activity.LoadLayout(\"layeditgoals\")";
Debug.ShouldStop(262144);
editgoals.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("layeditgoals")),editgoals.mostCurrent.activityBA);
 BA.debugLineNum = 21;BA.debugLine="End Sub";
Debug.ShouldStop(1048576);
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
		Debug.PushSubsStack("Activity_Pause (editgoals) ","editgoals",9,editgoals.mostCurrent.activityBA,editgoals.mostCurrent,27);
if (RapidSub.canDelegate("activity_pause")) { return b4a.example.editgoals.remoteMe.runUserSub(false, "editgoals","activity_pause", _userclosed);}
Debug.locals.put("UserClosed", _userclosed);
 BA.debugLineNum = 27;BA.debugLine="Sub Activity_Pause (UserClosed As Boolean)";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 29;BA.debugLine="End Sub";
Debug.ShouldStop(268435456);
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
		Debug.PushSubsStack("Activity_Resume (editgoals) ","editgoals",9,editgoals.mostCurrent.activityBA,editgoals.mostCurrent,23);
if (RapidSub.canDelegate("activity_resume")) { return b4a.example.editgoals.remoteMe.runUserSub(false, "editgoals","activity_resume");}
 BA.debugLineNum = 23;BA.debugLine="Sub Activity_Resume";
Debug.ShouldStop(4194304);
 BA.debugLineNum = 25;BA.debugLine="End Sub";
Debug.ShouldStop(16777216);
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
		Debug.PushSubsStack("btnback_Click (editgoals) ","editgoals",9,editgoals.mostCurrent.activityBA,editgoals.mostCurrent,31);
if (RapidSub.canDelegate("btnback_click")) { return b4a.example.editgoals.remoteMe.runUserSub(false, "editgoals","btnback_click");}
 BA.debugLineNum = 31;BA.debugLine="Private Sub btnback_Click";
Debug.ShouldStop(1073741824);
 BA.debugLineNum = 32;BA.debugLine="Activity.LoadLayout(\"layaccount\")";
Debug.ShouldStop(-2147483648);
editgoals.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("layaccount")),editgoals.mostCurrent.activityBA);
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
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 14;BA.debugLine="Private btnback As Button";
editgoals.mostCurrent._btnback = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _lblsavechanges_click() throws Exception{
try {
		Debug.PushSubsStack("lblsavechanges_Click (editgoals) ","editgoals",9,editgoals.mostCurrent.activityBA,editgoals.mostCurrent,35);
if (RapidSub.canDelegate("lblsavechanges_click")) { return b4a.example.editgoals.remoteMe.runUserSub(false, "editgoals","lblsavechanges_click");}
 BA.debugLineNum = 35;BA.debugLine="Private Sub lblsavechanges_Click";
Debug.ShouldStop(4);
 BA.debugLineNum = 36;BA.debugLine="Activity.Finish";
Debug.ShouldStop(8);
editgoals.mostCurrent._activity.runVoidMethod ("Finish");
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
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 9;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}