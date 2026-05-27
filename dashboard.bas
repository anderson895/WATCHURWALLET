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
	Private lbluserbudget As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	sql = Main.sql
	BuildScreen
End Sub

Sub BuildScreen
	Activity.RemoveAllViews
	Activity.Color = Colors.White

	Dim w As Int = 100%x
	Dim h As Int = 100%y
	Dim pad As Int = 16dip
	Dim btnH As Int = 50dip
	Dim contentW As Int = w - pad * 2
	Dim primary As Int = Colors.RGB(0, 150, 136)
	Dim cardBG As Int = Colors.RGB(225, 240, 240)

	Dim y As Int = pad + 20dip

	lbluserbudget.Initialize("")
	lbluserbudget.TextSize = 18
	lbluserbudget.TextColor = primary
	lbluserbudget.Typeface = Typeface.DEFAULT_BOLD
	lbluserbudget.Gravity = Gravity.CENTER
	Dim cd As ColorDrawable
	cd.Initialize2(cardBG, 12dip, 0, cardBG)
	lbluserbudget.Background = cd
	Activity.AddView(lbluserbudget, pad, y, contentW, 70dip)

	Dim centerY As Int = h / 2 - btnH / 2
	Dim gap As Int = 12dip
	Dim halfW As Int = (contentW - gap) / 2

	Dim btnAccount As Button
	btnAccount.Initialize("btnaccount")
	StyleButton(btnAccount, "Account", primary)
	Activity.AddView(btnAccount, pad, centerY, halfW, btnH)

	Dim btnLogout As Button
	btnLogout.Initialize("btnLogout")
	StyleButton(btnLogout, "Logout", Colors.RGB(200, 60, 60))
	Activity.AddView(btnLogout, pad + halfW + gap, centerY, halfW, btnH)

	Dim btnExportDB As Button
	btnExportDB.Initialize("btnExportDB")
	StyleButton(btnExportDB, "Export Database", Colors.RGB(40, 120, 180))
	Activity.AddView(btnExportDB, pad, h - pad - btnH, contentW, btnH)

	Dim lblDBPath As Label
	lblDBPath.Initialize("")
	lblDBPath.Text = "DB: " & File.Combine(File.DirInternal, "saddbb.db")
	lblDBPath.TextSize = 10
	lblDBPath.TextColor = Colors.Gray
	lblDBPath.Gravity = Gravity.CENTER
	Activity.AddView(lblDBPath, pad, h - pad - btnH - 20dip, contentW, 18dip)
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

	lbluserbudget.Text = Main.usernamee & CRLF & "Balance: " & NumberFormat2(allowance - spent, 1, 2, 2, False)
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
