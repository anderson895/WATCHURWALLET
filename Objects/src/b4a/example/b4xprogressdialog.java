package b4a.example;


import anywheresoftware.b4a.BA;
import anywheresoftware.b4a.B4AClass;
import anywheresoftware.b4a.BALayout;
import anywheresoftware.b4a.debug.*;

public class b4xprogressdialog extends B4AClass.ImplB4AClass implements BA.SubDelegator{
    private static java.util.HashMap<String, java.lang.reflect.Method> htSubs;
    private void innerInitialize(BA _ba) throws Exception {
        if (ba == null) {
            ba = new anywheresoftware.b4a.ShellBA(_ba, this, htSubs, "b4a.example.b4xprogressdialog");
            if (htSubs == null) {
                ba.loadHtSubs(this.getClass());
                htSubs = ba.htSubs;
            }
            
        }
        if (BA.isShellModeRuntimeCheck(ba)) 
			   this.getClass().getMethod("_class_globals", b4a.example.b4xprogressdialog.class).invoke(this, new Object[] {null});
        else
            ba.raiseEvent2(null, true, "class_globals", false);
    }

 
    public void  innerInitializeHelper(anywheresoftware.b4a.BA _ba) throws Exception{
        innerInitialize(_ba);
    }
    public Object callSub(String sub, Object sender, Object[] args) throws Exception {
        return BA.SubDelegator.SubNotFound;
    }
public anywheresoftware.b4a.keywords.Common __c = null;
public anywheresoftware.b4a.objects.B4XViewWrapper.XUI _xui = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _mbase = null;
public Object _mtext = null;
public b4a.example.b4xloadingindicator _loadingindicator = null;
public anywheresoftware.b4a.objects.B4XViewWrapper _label1 = null;
public b4a.example.b4xdialog _mdialog = null;
public b4a.example.dateutils _dateutils = null;
public b4a.example.main _main = null;
public b4a.example.signup _signup = null;
public b4a.example.myacc _myacc = null;
public b4a.example.home _home = null;
public b4a.example.expenses _expenses = null;
public b4a.example.goal _goal = null;
public b4a.example.account _account = null;
public b4a.example.admin _admin = null;
public b4a.example.editexpenses _editexpenses = null;
public b4a.example.editgoals _editgoals = null;
public b4a.example.dashboard _dashboard = null;
public b4a.example.starter _starter = null;
public b4a.example.xuiviewsutils _xuiviewsutils = null;
public anywheresoftware.b4a.objects.B4XViewWrapper  _getpanel(b4a.example.b4xprogressdialog __ref,b4a.example.b4xdialog _dialog) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xprogressdialog";
if (Debug.shouldDelegate(ba, "getpanel", true))
	 {return ((anywheresoftware.b4a.objects.B4XViewWrapper) Debug.delegate(ba, "getpanel", new Object[] {_dialog}));}
RDebugUtils.currentLine=24838144;
 //BA.debugLineNum = 24838144;BA.debugLine="Public Sub GetPanel (Dialog As B4XDialog) As B4XVi";
RDebugUtils.currentLine=24838145;
 //BA.debugLineNum = 24838145;BA.debugLine="Return mBase";
if (true) return __ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ;
RDebugUtils.currentLine=24838146;
 //BA.debugLineNum = 24838146;BA.debugLine="End Sub";
return null;
}
public String  _show(b4a.example.b4xprogressdialog __ref,b4a.example.b4xdialog _dialog) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xprogressdialog";
if (Debug.shouldDelegate(ba, "show", true))
	 {return ((String) Debug.delegate(ba, "show", new Object[] {_dialog}));}
RDebugUtils.currentLine=25034752;
 //BA.debugLineNum = 25034752;BA.debugLine="Private Sub Show (Dialog As B4XDialog) 'ignore";
RDebugUtils.currentLine=25034753;
 //BA.debugLineNum = 25034753;BA.debugLine="LoadingIndicator.Show";
__ref._loadingindicator /*b4a.example.b4xloadingindicator*/ ._show /*String*/ (null);
RDebugUtils.currentLine=25034754;
 //BA.debugLineNum = 25034754;BA.debugLine="End Sub";
return "";
}
public String  _dialogclosed(b4a.example.b4xprogressdialog __ref,int _result) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xprogressdialog";
if (Debug.shouldDelegate(ba, "dialogclosed", true))
	 {return ((String) Debug.delegate(ba, "dialogclosed", new Object[] {_result}));}
RDebugUtils.currentLine=25100288;
 //BA.debugLineNum = 25100288;BA.debugLine="Private Sub DialogClosed(Result As Int) 'ignore";
RDebugUtils.currentLine=25100289;
 //BA.debugLineNum = 25100289;BA.debugLine="LoadingIndicator.Hide";
__ref._loadingindicator /*b4a.example.b4xloadingindicator*/ ._hide /*String*/ (null);
RDebugUtils.currentLine=25100290;
 //BA.debugLineNum = 25100290;BA.debugLine="End Sub";
return "";
}
public String  _class_globals(b4a.example.b4xprogressdialog __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xprogressdialog";
RDebugUtils.currentLine=24576000;
 //BA.debugLineNum = 24576000;BA.debugLine="Sub Class_Globals";
RDebugUtils.currentLine=24576001;
 //BA.debugLineNum = 24576001;BA.debugLine="Private xui As XUI";
_xui = new anywheresoftware.b4a.objects.B4XViewWrapper.XUI();
RDebugUtils.currentLine=24576002;
 //BA.debugLineNum = 24576002;BA.debugLine="Public mBase As B4XView";
_mbase = new anywheresoftware.b4a.objects.B4XViewWrapper();
RDebugUtils.currentLine=24576003;
 //BA.debugLineNum = 24576003;BA.debugLine="Private mText As Object";
_mtext = new Object();
RDebugUtils.currentLine=24576004;
 //BA.debugLineNum = 24576004;BA.debugLine="Public LoadingIndicator As B4XLoadingIndicator";
_loadingindicator = new b4a.example.b4xloadingindicator();
RDebugUtils.currentLine=24576005;
 //BA.debugLineNum = 24576005;BA.debugLine="Public Label1 As B4XView";
_label1 = new anywheresoftware.b4a.objects.B4XViewWrapper();
RDebugUtils.currentLine=24576006;
 //BA.debugLineNum = 24576006;BA.debugLine="Public mDialog As B4XDialog";
_mdialog = new b4a.example.b4xdialog();
RDebugUtils.currentLine=24576007;
 //BA.debugLineNum = 24576007;BA.debugLine="End Sub";
return "";
}
public Object  _gettext(b4a.example.b4xprogressdialog __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xprogressdialog";
if (Debug.shouldDelegate(ba, "gettext", true))
	 {return ((Object) Debug.delegate(ba, "gettext", null));}
RDebugUtils.currentLine=24772608;
 //BA.debugLineNum = 24772608;BA.debugLine="Public Sub getText As Object";
RDebugUtils.currentLine=24772609;
 //BA.debugLineNum = 24772609;BA.debugLine="Return mText";
if (true) return __ref._mtext /*Object*/ ;
RDebugUtils.currentLine=24772610;
 //BA.debugLineNum = 24772610;BA.debugLine="End Sub";
return null;
}
public String  _hide(b4a.example.b4xprogressdialog __ref) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xprogressdialog";
if (Debug.shouldDelegate(ba, "hide", true))
	 {return ((String) Debug.delegate(ba, "hide", null));}
RDebugUtils.currentLine=24969216;
 //BA.debugLineNum = 24969216;BA.debugLine="Public Sub Hide";
RDebugUtils.currentLine=24969217;
 //BA.debugLineNum = 24969217;BA.debugLine="mDialog.Close(0)";
__ref._mdialog /*b4a.example.b4xdialog*/ ._close /*boolean*/ (null,(int) (0));
RDebugUtils.currentLine=24969218;
 //BA.debugLineNum = 24969218;BA.debugLine="End Sub";
return "";
}
public String  _initialize(b4a.example.b4xprogressdialog __ref,anywheresoftware.b4a.BA _ba,anywheresoftware.b4a.objects.B4XViewWrapper _parent) throws Exception{
__ref = this;
innerInitialize(_ba);
RDebugUtils.currentModule="b4xprogressdialog";
if (Debug.shouldDelegate(ba, "initialize", true))
	 {return ((String) Debug.delegate(ba, "initialize", new Object[] {_ba,_parent}));}
RDebugUtils.currentLine=24641536;
 //BA.debugLineNum = 24641536;BA.debugLine="Public Sub Initialize (Parent As B4XView)";
RDebugUtils.currentLine=24641537;
 //BA.debugLineNum = 24641537;BA.debugLine="mBase = xui.CreatePanel(\"mBase\")";
__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/  = __ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .CreatePanel(ba,"mBase");
RDebugUtils.currentLine=24641538;
 //BA.debugLineNum = 24641538;BA.debugLine="mBase.SetLayoutAnimated(0, 0, 0, 300dip, 60dip)";
__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetLayoutAnimated((int) (0),(int) (0),(int) (0),__c.DipToCurrent((int) (300)),__c.DipToCurrent((int) (60)));
RDebugUtils.currentLine=24641539;
 //BA.debugLineNum = 24641539;BA.debugLine="mBase.LoadLayout(\"XV_ProgressTemplate\")";
__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .LoadLayout("XV_ProgressTemplate",ba);
RDebugUtils.currentLine=24641540;
 //BA.debugLineNum = 24641540;BA.debugLine="mBase.SetColorAndBorder(xui.Color_White, 0, 0, 5d";
__ref._mbase /*anywheresoftware.b4a.objects.B4XViewWrapper*/ .SetColorAndBorder(__ref._xui /*anywheresoftware.b4a.objects.B4XViewWrapper.XUI*/ .Color_White,(int) (0),(int) (0),__c.DipToCurrent((int) (5)));
RDebugUtils.currentLine=24641541;
 //BA.debugLineNum = 24641541;BA.debugLine="LoadingIndicator.Hide";
__ref._loadingindicator /*b4a.example.b4xloadingindicator*/ ._hide /*String*/ (null);
RDebugUtils.currentLine=24641542;
 //BA.debugLineNum = 24641542;BA.debugLine="mDialog.Initialize(Parent)";
__ref._mdialog /*b4a.example.b4xdialog*/ ._initialize /*String*/ (null,ba,_parent);
RDebugUtils.currentLine=24641543;
 //BA.debugLineNum = 24641543;BA.debugLine="mDialog.ButtonsHeight = -2dip";
__ref._mdialog /*b4a.example.b4xdialog*/ ._buttonsheight /*int*/  = (int) (-__c.DipToCurrent((int) (2)));
RDebugUtils.currentLine=24641544;
 //BA.debugLineNum = 24641544;BA.debugLine="mDialog.BorderWidth = 0";
__ref._mdialog /*b4a.example.b4xdialog*/ ._borderwidth /*int*/  = (int) (0);
RDebugUtils.currentLine=24641545;
 //BA.debugLineNum = 24641545;BA.debugLine="mDialog.BorderCornersRadius = 5dip";
__ref._mdialog /*b4a.example.b4xdialog*/ ._bordercornersradius /*int*/  = __c.DipToCurrent((int) (5));
RDebugUtils.currentLine=24641546;
 //BA.debugLineNum = 24641546;BA.debugLine="End Sub";
return "";
}
public String  _settext(b4a.example.b4xprogressdialog __ref,Object _t) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xprogressdialog";
if (Debug.shouldDelegate(ba, "settext", true))
	 {return ((String) Debug.delegate(ba, "settext", new Object[] {_t}));}
RDebugUtils.currentLine=24707072;
 //BA.debugLineNum = 24707072;BA.debugLine="Public Sub setText(t As Object)";
RDebugUtils.currentLine=24707073;
 //BA.debugLineNum = 24707073;BA.debugLine="XUIViewsUtils.SetTextOrCSBuilderToLabel(Label1, t";
_xuiviewsutils._settextorcsbuildertolabel /*String*/ (ba,__ref._label1 /*anywheresoftware.b4a.objects.B4XViewWrapper*/ ,_t);
RDebugUtils.currentLine=24707074;
 //BA.debugLineNum = 24707074;BA.debugLine="End Sub";
return "";
}
public String  _showdialog(b4a.example.b4xprogressdialog __ref,Object _text) throws Exception{
__ref = this;
RDebugUtils.currentModule="b4xprogressdialog";
if (Debug.shouldDelegate(ba, "showdialog", true))
	 {return ((String) Debug.delegate(ba, "showdialog", new Object[] {_text}));}
RDebugUtils.currentLine=24903680;
 //BA.debugLineNum = 24903680;BA.debugLine="Public Sub ShowDialog (Text As Object)";
RDebugUtils.currentLine=24903681;
 //BA.debugLineNum = 24903681;BA.debugLine="setText(Text)";
__ref._settext /*String*/ (null,_text);
RDebugUtils.currentLine=24903682;
 //BA.debugLineNum = 24903682;BA.debugLine="If mDialog.Visible = False Then";
if (__ref._mdialog /*b4a.example.b4xdialog*/ ._getvisible /*boolean*/ (null)==__c.False) { 
RDebugUtils.currentLine=24903683;
 //BA.debugLineNum = 24903683;BA.debugLine="mDialog.ShowTemplate(Me, \"\", \"\", \"\")";
__ref._mdialog /*b4a.example.b4xdialog*/ ._showtemplate /*anywheresoftware.b4a.keywords.Common.ResumableSubWrapper*/ (null,this,(Object)(""),(Object)(""),(Object)(""));
 };
RDebugUtils.currentLine=24903685;
 //BA.debugLineNum = 24903685;BA.debugLine="End Sub";
return "";
}
}