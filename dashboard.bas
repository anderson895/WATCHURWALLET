B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Activity
Version=13.4
@EndOfDesignText@
#Region  Activity Attributes
	#FullScreen: False
	#IncludeTitle: False
#End Region

Sub Process_Globals
	Dim sql As SQL
End Sub

Sub Globals
	Private btnLogout As Button
	Private lbluserbudget As Label
	Private btnaccount As Button
	Private btnExportDB As Button
	Private lblDBPath As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	sql = Main.sql
	Activity.LoadLayout("dashboardlayout")

	btnExportDB.Initialize("btnExportDB")
	lblDBPath.Initialize("")

	MakeResponsive
End Sub

Sub MakeResponsive
	Activity.Color = Colors.White
	TagGlobals
	HideExtras

	Dim w As Int = 100%x
	Dim h As Int = 100%y
	Dim pad As Int = 16dip
	Dim spacing As Int = 12dip
	Dim btnH As Int = 50dip
	Dim contentW As Int = w - pad * 2
	Dim primary As Int = Colors.RGB(0, 150, 136)
	Dim cardBG As Int = Colors.RGB(225, 240, 240)

	Dim y As Int = pad + 20dip

	lbluserbudget.Left = pad
	lbluserbudget.Top = y
	lbluserbudget.Width = contentW
	lbluserbudget.Height = 60dip
	lbluserbudget.TextSize = 18
	lbluserbudget.TextColor = primary
	lbluserbudget.Typeface = Typeface.DEFAULT_BOLD
	lbluserbudget.Gravity = Gravity.CENTER
	Dim cd As ColorDrawable
	cd.Initialize2(cardBG, 12dip, 0, cardBG)
	lbluserbudget.Background = cd

	Dim centerY As Int = h / 2 - btnH / 2
	Dim gap As Int = 12dip
	Dim halfW As Int = (contentW - gap) / 2

	btnaccount.Left = pad
	btnaccount.Top = centerY
	btnaccount.Width = halfW
	btnaccount.Height = btnH
	StyleButton(btnaccount, "Account", primary)

	btnLogout.Left = pad + halfW + gap
	btnLogout.Top = centerY
	btnLogout.Width = halfW
	btnLogout.Height = btnH
	StyleButton(btnLogout, "Logout", Colors.RGB(200, 60, 60))

	btnExportDB.Text = "Export Database"
	StyleButton(btnExportDB, "Export Database", Colors.RGB(40, 120, 180))
	Activity.AddView(btnExportDB, pad, h - pad - btnH, contentW, btnH)

	lblDBPath.Text = "DB: " & File.Combine(File.DirInternal, "saddbb.db")
	lblDBPath.TextSize = 10
	lblDBPath.TextColor = Colors.Gray
	lblDBPath.Gravity = Gravity.CENTER
	Activity.AddView(lblDBPath, pad, h - pad - btnH - 20dip, contentW, 18dip)
End Sub

Sub TagGlobals
	btnLogout.Tag = "g"
	lbluserbudget.Tag = "g"
	btnaccount.Tag = "g"
End Sub

Sub HideExtras
	For i = 0 To Activity.NumberOfViews - 1
		Dim v As View = Activity.GetView(i)
		Dim t As String = "" & v.Tag
		If t <> "g" Then v.Visible = False
	Next
End Sub

Sub StyleButton(btn As Button, text As String, color As Int)
	btn.Text = text
	btn.TextColor = Colors.White
	btn.TextSize = 16
	Dim cd As ColorDrawable
	cd.Initialize2(color, 12dip, 0, color)
	btn.Background = cd
End Sub

Sub Activity_Resume
	UpdateHeader
End Sub

Sub UpdateHeader
	Dim allowance As Double = 0
	Dim spent As Double = 0

	Dim c As Cursor
	c = sql.ExecQuery2( _
		"SELECT IFNULL(SUM(amount),0) FROM tblallowance WHERE username=?", _
		Array As String(Main.usernamee))
	c.Position = 0
	allowance = c.GetDouble2(0)
	c.Close

	c = sql.ExecQuery2( _
		"SELECT IFNULL(SUM(amount),0) FROM tblexpenses WHERE username=?", _
		Array As String(Main.usernamee))
	c.Position = 0
	spent = c.GetDouble2(0)
	c.Close

	lbluserbudget.Text = Main.usernamee & CRLF & "Balance: " & NumberFormat(allowance - spent, 1, 2)
End Sub

Private Sub btnLogout_Click
	Main.usernamee = ""
	Main.fname = ""
	Main.lname = ""
	Main.email = ""
	Main.role = ""
	StartActivity(Main)
	Activity.Finish
End Sub

Private Sub btnaccount_Click
	StartActivity(account)
End Sub

Private Sub btnExportDB_Click
	Starter.ExportDBAndShowDialog(True)
End Sub
