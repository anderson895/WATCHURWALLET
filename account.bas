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
	Private pnlmenu As Panel
	Private Button1 As Button
	Private lblfullname As Label
	Private lblemail As Label
	Private lblusername As Label
	Private btneditexpenses As Button
	Private ListView1 As ListView
	Private EditText1 As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	sql = Main.sql

	Activity.LoadLayout("layaccount")
	pnlmenu.LoadLayout("laymenu")
	pnlmenu.Visible = False
	AddExportButtonToMenu
	lblfullname.Text = Main.fname & " " & Main.lname
	lblusername.Text = Main.usernamee
	lblemail.Text = Main.email
	MakeResponsive
End Sub

Sub MakeResponsive
	Dim w As Int = 100%x
	Dim pad As Int = 16dip

	lblfullname.Left = pad : lblfullname.Width = w - pad * 2
	lblusername.Left = pad : lblusername.Width = w - pad * 2
	lblemail.Left = pad : lblemail.Width = w - pad * 2

	EditText1.Left = pad : EditText1.Width = w - pad * 2

	ListView1.Left = pad : ListView1.Width = w - pad * 2
	ListView1.Height = 100%y - ListView1.Top - pad

	pnlmenu.Width = 70%x
	pnlmenu.Height = 100%y
End Sub

Sub AddExportButtonToMenu
	Dim btn As Button
	btn.Initialize("ExportDB")
	btn.Text = "Export DB"
	btn.TextSize = 14
	btn.TextColor = Colors.White
	btn.Color = Colors.RGB(40, 120, 180)
	pnlmenu.AddView(btn, 16dip, pnlmenu.Height - 70dip, pnlmenu.Width - 32dip, 48dip)
End Sub

Private Sub ExportDB_Click
	Starter.ExportDBAndShowDialog(True)
End Sub

Sub Activity_Resume
	LoadHistory
End Sub

Private Sub btnmenu_Click
	pnlmenu.Visible = True
End Sub

Private Sub labelhome_Click
	pnlmenu.Visible = False
	StartActivity(home)
	Activity.Finish
End Sub

Private Sub labelexpenses_Click
	pnlmenu.Visible = False
	StartActivity(expenses)
	Activity.Finish
End Sub

Private Sub labelgoal_Click
	pnlmenu.Visible = False
	StartActivity(goal)
	Activity.Finish
End Sub

Private Sub labelaccount_Click
	pnlmenu.Visible = False
End Sub

Private Sub btnsetallowance_Click
	If EditText1.Text = "" Then
		ToastMessageShow("Enter an amount", False)
		Return
	End If

	If IsNumber(EditText1.Text) = False Then
		ToastMessageShow("Amount must be a number", False)
		Return
	End If

	Dim allowanceAmount As Double = EditText1.Text
	If allowanceAmount <= 0 Then
		ToastMessageShow("Amount must be greater than 0", False)
		Return
	End If

	Dim today As String = DateTime.Date(DateTime.Now)

	sql.ExecNonQuery2( _
		"INSERT INTO tblallowance (amount, date, username) VALUES (?, ?, ?)", _
		Array As Object(allowanceAmount, today, Main.usernamee))

	sql.ExecNonQuery2( _
		"INSERT INTO tbltransac (type, amount, date, username) VALUES (?, ?, ?, ?)", _
		Array As Object("Allowance", allowanceAmount, today, Main.usernamee))

	LoadHistory
	ToastMessageShow("Allowance Set: " & NumberFormat(allowanceAmount, 1, 2), False)
	EditText1.Text = ""
End Sub

Private Sub btneditexpenses_Click
	StartActivity(editexpenses)
End Sub

' Edit profile icon (pencil) at the top.
Private Sub btneditmyacc_Click
	StartActivity(myacc)
End Sub

' "Edit Goals/Saving" button.
Private Sub btneditgoals_Click
	StartActivity(editgoals)
End Sub

Sub LoadHistory
	Dim c1 As Cursor
	c1 = sql.ExecQuery2( _
		"SELECT type, amount, date FROM tbltransac WHERE username=? ORDER BY transac_id DESC", _
		Array As String(Main.usernamee))
	ListView1.Clear
	For i = 0 To c1.RowCount - 1
		c1.Position = i
		ListView1.AddSingleLine( _
			c1.GetString2(0) & ": " & NumberFormat(c1.GetDouble2(1), 1, 2) & " on " & c1.GetString2(2))
	Next
	c1.Close
End Sub
