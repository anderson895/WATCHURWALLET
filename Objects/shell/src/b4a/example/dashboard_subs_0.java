package b4a.example;

import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.pc.*;

public class dashboard_subs_0 {


public static RemoteObject  _activity_create(RemoteObject _firsttime) throws Exception{
try {
		Debug.PushSubsStack("Activity_Create (dashboard) ","dashboard",10,dashboard.mostCurrent.activityBA,dashboard.mostCurrent,17);
if (RapidSub.canDelegate("activity_create")) { return b4a.example.dashboard.remoteMe.runUserSub(false, "dashboard","activity_create", _firsttime);}
Debug.locals.put("FirstTime", _firsttime);
 BA.debugLineNum = 17;BA.debugLine="Sub Activity_Create(FirstTime As Boolean)";
Debug.ShouldStop(65536);
 BA.debugLineNum = 18;BA.debugLine="Activity.LoadLayout(\"dashboardlayout\")";
Debug.ShouldStop(131072);
dashboard.mostCurrent._activity.runMethodAndSync(false,"LoadLayout",(Object)(RemoteObject.createImmutable("dashboardlayout")),dashboard.mostCurrent.activityBA);
 BA.debugLineNum = 19;BA.debugLine="lbluserbudget.Text = Main.usernamee & \" Budget\"";
Debug.ShouldStop(262144);
dashboard.mostCurrent._lbluserbudget.runMethod(true,"setText",BA.ObjectToCharSequence(RemoteObject.concat(dashboard.mostCurrent._main._usernamee /*RemoteObject*/ ,RemoteObject.createImmutable(" Budget"))));
 BA.debugLineNum = 20;BA.debugLine="End Sub";
Debug.ShouldStop(524288);
return RemoteObject.createImmutable("");
}
catch (Exception e) {
			throw Debug.ErrorCaught(e);
		} 
finally {
			Debug.PopSubsStack();
		}}
public static RemoteObject  _btnaccount_click() throws Exception{
try {
		Debug.PushSubsStack("btnaccount_Click (dashboard) ","dashboard",10,dashboard.mostCurrent.activityBA,dashboard.mostCurrent,27);
if (RapidSub.canDelegate("btnaccount_click")) { return b4a.example.dashboard.remoteMe.runUserSub(false, "dashboard","btnaccount_click");}
 BA.debugLineNum = 27;BA.debugLine="Private Sub btnaccount_Click";
Debug.ShouldStop(67108864);
 BA.debugLineNum = 28;BA.debugLine="StartActivity(account)";
Debug.ShouldStop(134217728);
dashboard.mostCurrent.__c.runVoidMethod ("StartActivity",dashboard.processBA,(Object)((dashboard.mostCurrent._account.getObject())));
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
public static RemoteObject  _btnlogout_click() throws Exception{
try {
		Debug.PushSubsStack("btnLogout_Click (dashboard) ","dashboard",10,dashboard.mostCurrent.activityBA,dashboard.mostCurrent,22);
if (RapidSub.canDelegate("btnlogout_click")) { return b4a.example.dashboard.remoteMe.runUserSub(false, "dashboard","btnlogout_click");}
 BA.debugLineNum = 22;BA.debugLine="Private Sub btnLogout_Click";
Debug.ShouldStop(2097152);
 BA.debugLineNum = 23;BA.debugLine="StartActivity(Main)";
Debug.ShouldStop(4194304);
dashboard.mostCurrent.__c.runVoidMethod ("StartActivity",dashboard.processBA,(Object)((dashboard.mostCurrent._main.getObject())));
 BA.debugLineNum = 24;BA.debugLine="Activity.Finish";
Debug.ShouldStop(8388608);
dashboard.mostCurrent._activity.runVoidMethod ("Finish");
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
public static RemoteObject  _globals() throws Exception{
 //BA.debugLineNum = 11;BA.debugLine="Sub Globals";
 //BA.debugLineNum = 12;BA.debugLine="Private btnLogout As Button";
dashboard.mostCurrent._btnlogout = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 13;BA.debugLine="Private lbluserbudget As Label";
dashboard.mostCurrent._lbluserbudget = RemoteObject.createNew ("anywheresoftware.b4a.objects.LabelWrapper");
 //BA.debugLineNum = 14;BA.debugLine="Private btnaccount As Button";
dashboard.mostCurrent._btnaccount = RemoteObject.createNew ("anywheresoftware.b4a.objects.ButtonWrapper");
 //BA.debugLineNum = 15;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
public static RemoteObject  _process_globals() throws Exception{
 //BA.debugLineNum = 6;BA.debugLine="Sub Process_Globals";
 //BA.debugLineNum = 10;BA.debugLine="End Sub";
return RemoteObject.createImmutable("");
}
}