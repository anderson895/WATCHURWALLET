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
	Private txtaddedgoal1 As EditText
	Private txtgoalcategory1 As EditText
	Private ProgressBar1 As ProgressBar
	Private btnaddgoal As Button
End Sub

Sub Activity_Create(FirstTime As Boolean)
	sql = Main.sql
	Activity.LoadLayout("laygoal")
	pnlmenu.LoadLayout("laymenu")
	pnlmenu.Visible = False
End Sub

Sub Activity_Resume
	LoadGoals
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
End Sub

Private Sub labelaccount_Click
	pnlmenu.Visible = False
	StartActivity(account)
	Activity.Finish
End Sub

Private Sub btnaddgoal_Click
	Dim category As String = txtgoalcategory1.Text.Trim
	Dim target As Double

	If category = "" Then
		Msgbox("Please enter a category", "")
		Return
	End If

	If IsNumber(txtaddedgoal1.Text) = False Then
		Msgbox("Target amount must be a number", "")
		Return
	End If

	target = txtaddedgoal1.Text
	If target <= 0 Then
		Msgbox("Target amount must be greater than 0", "")
		Return
	End If

	Dim today As String = DateTime.Date(DateTime.Now)

	sql.ExecNonQuery2( _
		"INSERT INTO tblgoal (category, goal_amount, current_amount, username) VALUES (?, ?, ?, ?)", _
		Array As Object(category, target, 0, Main.usernamee))

	sql.ExecNonQuery2( _
		"INSERT INTO tbltransac (type, amount, date, username) VALUES (?, ?, ?, ?)", _
		Array As Object("Goal Set - " & category, target, today, Main.usernamee))

	ToastMessageShow("Goal Added: " & category & " - " & NumberFormat(target, 1, 2), False)

	txtgoalcategory1.Text = ""
	txtaddedgoal1.Text = ""
	LoadGoals
End Sub

Sub LoadGoals
	Dim c As Cursor
	c = sql.ExecQuery2( _
		"SELECT category, goal_amount, current_amount FROM tblgoal WHERE username=? ORDER BY goal_id DESC LIMIT 1", _
		Array As String(Main.usernamee))

	If c.RowCount > 0 Then
		c.Position = 0
		Dim cat As String = c.GetString2(0)
		Dim goalAmt As Double = c.GetDouble2(1)
		Dim curAmt As Double = c.GetDouble2(2)

		txtgoalcategory1.Text = cat
		txtaddedgoal1.Text = NumberFormat(goalAmt, 1, 2)

		If goalAmt > 0 Then
			Dim pct As Double = (curAmt / goalAmt) * 100
			If pct > 100 Then pct = 100
			ProgressBar1.Progress = pct
		Else
			ProgressBar1.Progress = 0
		End If
	Else
		ProgressBar1.Progress = 0
	End If
	c.Close
End Sub
