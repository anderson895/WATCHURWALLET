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

	' Add Export DB button programmatically (no need to edit layout in Designer).
	btnExportDB.Initialize("btnExportDB")
	btnExportDB.Text = "Export Database"
	btnExportDB.TextSize = 14
	Activity.AddView(btnExportDB, 16dip, 100%y - 110dip, 100%x - 32dip, 44dip)

	lblDBPath.Initialize("")
	lblDBPath.TextSize = 11
	lblDBPath.Text = "DB: " & File.Combine(File.DirInternal, "saddbb.db")
	Activity.AddView(lblDBPath, 16dip, 100%y - 60dip, 100%x - 32dip, 50dip)
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

	lbluserbudget.Text = Main.usernamee & " - Balance: " & NumberFormat(allowance - spent, 1, 2)
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
	Main.ExportDBAndShowDialog(True)
End Sub
