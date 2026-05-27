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
	Private txtaddedgoal1 As EditText
	Private txtgoalcategory1 As EditText
	Private ProgressBar1 As ProgressBar
End Sub

Sub Activity_Create(FirstTime As Boolean)
	sql = Main.sql
	BuildScreen
End Sub

Sub BuildScreen
	Activity.RemoveAllViews
	Activity.Color = Colors.White

	Dim w As Int = 100%x
	Dim pad As Int = 16dip
	Dim spacing As Int = 10dip
	Dim labelH As Int = 22dip
	Dim ctrlH As Int = 44dip
	Dim btnH As Int = 48dip
	Dim contentW As Int = w - pad * 2
	Dim primary As Int = Colors.RGB(0, 150, 136)
	Dim cardBG As Int = Colors.RGB(225, 240, 240)

	Dim y As Int = pad

	AddMenuButton(pad, y, primary)
	AddTitle("Goals & Savings", pad + 56dip, y, contentW - 56dip, primary)
	y = y + 50dip + spacing * 2

	AddHeaderLabel("Category", pad, y, contentW, primary)
	y = y + labelH + 6dip
	txtgoalcategory1.Initialize("")
	StyleInputEditText(txtgoalcategory1, "e.g. New Laptop", cardBG)
	Activity.AddView(txtgoalcategory1, pad, y, contentW, ctrlH)
	y = y + ctrlH + spacing * 2

	AddHeaderLabel("Target Amount", pad, y, contentW, primary)
	y = y + labelH + 6dip
	txtaddedgoal1.Initialize("")
	StyleInputEditText(txtaddedgoal1, "0.00", cardBG)
	txtaddedgoal1.InputType = txtaddedgoal1.INPUT_TYPE_DECIMAL_NUMBERS
	Activity.AddView(txtaddedgoal1, pad, y, contentW, ctrlH)
	y = y + ctrlH + spacing * 2

	AddHeaderLabel("Progress", pad, y, contentW, primary)
	y = y + labelH + 6dip
	ProgressBar1.Initialize("")
	Activity.AddView(ProgressBar1, pad, y, contentW, 24dip)
	y = y + 24dip + spacing * 2

	Dim btnAddGoal As Button
	btnAddGoal.Initialize("btnaddgoal")
	StyleButton(btnAddGoal, "Add Goal", primary)
	Activity.AddView(btnAddGoal, (w - 60%x) / 2, y, 60%x, btnH)

	BuildSideNav
End Sub

Sub BuildSideNav
	pnlmenu.Initialize("")
	pnlmenu.Color = Colors.White
	Activity.AddView(pnlmenu, 0, 0, 70%x, 100%y)
	pnlmenu.LoadLayout("laymenu")
	pnlmenu.Visible = False

	Dim btn As Button
	btn.Initialize("ExportDB")
	btn.Text = "Export DB"
	btn.TextSize = 14
	btn.TextColor = Colors.White
	btn.Color = Colors.RGB(40, 120, 180)
	pnlmenu.AddView(btn, 16dip, 100%y - 70dip, pnlmenu.Width - 32dip, 48dip)
End Sub

Sub AddMenuButton(x As Int, y As Int, color As Int)
	Dim btn As Button
	btn.Initialize("btnmenu")
	btn.Text = Chr(0x2630)
	btn.TextSize = 22
	btn.TextColor = color
	btn.Color = Colors.White
	Activity.AddView(btn, x, y, 44dip, 44dip)
End Sub

Sub AddTitle(text As String, x As Int, y As Int, w As Int, color As Int)
	Dim lbl As Label
	lbl.Initialize("")
	lbl.Text = text
	lbl.TextSize = 20
	lbl.TextColor = color
	lbl.Typeface = Typeface.DEFAULT_BOLD
	Activity.AddView(lbl, x, y, w, 44dip)
End Sub

Sub AddHeaderLabel(text As String, x As Int, y As Int, w As Int, color As Int)
	Dim lbl As Label
	lbl.Initialize("")
	lbl.Text = text
	lbl.TextSize = 15
	lbl.TextColor = color
	lbl.Typeface = Typeface.DEFAULT_BOLD
	Activity.AddView(lbl, x, y, w, 22dip)
End Sub

Sub StyleInputEditText(et As EditText, hint As String, bg As Int)
	et.Hint = hint
	et.TextSize = 15
	et.TextColor = Colors.Black
	Dim cd As ColorDrawable
	cd.Initialize2(bg, 10dip, 1dip, Colors.LightGray)
	et.Background = cd
	et.SingleLine = True
	et.Padding = Array As Int(14dip, 0, 14dip, 0)
End Sub

Sub StyleButton(btn As Button, text As String, color As Int)
	btn.Text = text
	btn.TextColor = Colors.White
	btn.TextSize = 16
	Dim cd As ColorDrawable
	cd.Initialize2(color, 12dip, 0, color)
	btn.Background = cd
End Sub

Private Sub ExportDB_Click
	Starter.ExportDBAndShowDialog(True)
End Sub

Sub Activity_Resume
	sql = Main.sql
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
