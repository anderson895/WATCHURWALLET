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
	Public ExpenseList As List
	Public CategoryList As List
	Dim sql As SQL
End Sub

Sub Globals
	Private pnlmenu As Panel
	Private txtstudentsname As EditText
	Private txtallowance As EditText
	Private txtdate As EditText
	Private txtspent As EditText
	Private txtbalance As EditText
	Private txtamountexpenses As EditText
	Private spinnercategory As Spinner
	Private txtamountgoal As EditText
	Private txtgoal As EditText

	Dim TotalSpent As Double = 0
	Dim Balance As Double = 0
	Dim Allowance As Double = 0
End Sub

Sub Activity_Create(FirstTime As Boolean)
	sql = Main.sql

	If ExpenseList.IsInitialized = False Then ExpenseList.Initialize
	If CategoryList.IsInitialized = False Then CategoryList.Initialize

	BuildScreen
End Sub

Sub BuildScreen
	Activity.RemoveAllViews
	Activity.Color = Colors.White

	Dim w As Int = 100%x
	Dim h As Int = 100%y
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

	txtstudentsname.Initialize("")
	txtstudentsname.Text = Main.fname & " " & Main.lname
	txtstudentsname.TextSize = 18
	txtstudentsname.TextColor = Colors.Black
	txtstudentsname.Background = Null
	txtstudentsname.Enabled = False
	Activity.AddView(txtstudentsname, pad + 56dip, y + 4dip, contentW - 56dip, 36dip)

	y = y + 50dip + spacing

	DateTime.DateFormat = "MMMM dd, yyyy"
	txtdate.Initialize("")
	txtdate.Text = DateTime.Date(DateTime.Now)
	txtdate.TextSize = 13
	txtdate.TextColor = Colors.DarkGray
	txtdate.Background = Null
	txtdate.Enabled = False
	Activity.AddView(txtdate, pad, y, contentW, 28dip)
	y = y + 28dip + spacing

	AddHeaderLabel("Allowance", pad, y, contentW, primary)
	y = y + labelH + 4dip
	txtallowance.Initialize("")
	StyleCardEditText(txtallowance, cardBG)
	Activity.AddView(txtallowance, pad, y, contentW, ctrlH)
	y = y + ctrlH + spacing

	AddHeaderLabel("Spent", pad, y, contentW, primary)
	y = y + labelH + 4dip
	txtspent.Initialize("")
	StyleCardEditText(txtspent, cardBG)
	txtspent.Enabled = False
	txtspent.TextColor = Colors.Black
	Activity.AddView(txtspent, pad, y, contentW, ctrlH)
	y = y + ctrlH + spacing

	AddHeaderLabel("Balance", pad, y, contentW, primary)
	y = y + labelH + 4dip
	txtbalance.Initialize("")
	StyleCardEditText(txtbalance, cardBG)
	txtbalance.Enabled = False
	txtbalance.TextColor = Colors.Black
	Activity.AddView(txtbalance, pad, y, contentW, ctrlH)
	y = y + ctrlH + spacing * 2

	AddHeaderLabel("Add Expenses", pad, y, contentW, primary)
	y = y + labelH + spacing

	spinnercategory.Initialize("")
	spinnercategory.Add("Foods & Groceries")
	spinnercategory.Add("Transpo")
	spinnercategory.Add("Rent")
	spinnercategory.Add("School Supplies")
	spinnercategory.Add("School Projects")
	Activity.AddView(spinnercategory, pad, y, contentW, ctrlH)
	y = y + ctrlH + spacing

	txtamountexpenses.Initialize("")
	StyleInputEditText(txtamountexpenses, "Amount", cardBG)
	txtamountexpenses.InputType = txtamountexpenses.INPUT_TYPE_DECIMAL_NUMBERS
	Activity.AddView(txtamountexpenses, pad, y, contentW, ctrlH)
	y = y + ctrlH + spacing

	Dim btnAcceptExp As Button
	btnAcceptExp.Initialize("btnacceptexpenses")
	StyleButton(btnAcceptExp, "Accept", primary)
	Activity.AddView(btnAcceptExp, (w - 60%x) / 2, y, 60%x, btnH)
	y = y + btnH + spacing * 2

	AddHeaderLabel("Add to Goals", pad, y, contentW, primary)
	y = y + labelH + spacing

	txtgoal.Initialize("")
	StyleInputEditText(txtgoal, "Goal name", cardBG)
	Activity.AddView(txtgoal, pad, y, contentW, ctrlH)
	y = y + ctrlH + spacing

	txtamountgoal.Initialize("")
	StyleInputEditText(txtamountgoal, "Amount", cardBG)
	txtamountgoal.InputType = txtamountgoal.INPUT_TYPE_DECIMAL_NUMBERS
	Activity.AddView(txtamountgoal, pad, y, contentW, ctrlH)
	y = y + ctrlH + spacing

	Dim btnAcceptGoal As Button
	btnAcceptGoal.Initialize("btnacceptgoal")
	StyleButton(btnAcceptGoal, "Add Goal", primary)
	Activity.AddView(btnAcceptGoal, (w - 60%x) / 2, y, 60%x, btnH)

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

Sub AddHeaderLabel(text As String, x As Int, y As Int, w As Int, color As Int)
	Dim lbl As Label
	lbl.Initialize("")
	lbl.Text = text
	lbl.TextSize = 15
	lbl.TextColor = color
	lbl.Typeface = Typeface.DEFAULT_BOLD
	Activity.AddView(lbl, x, y, w, 22dip)
End Sub

Sub StyleCardEditText(et As EditText, bg As Int)
	et.TextSize = 16
	et.TextColor = Colors.Black
	Dim cd As ColorDrawable
	cd.Initialize2(bg, 10dip, 0, bg)
	et.Background = cd
	et.SingleLine = True
	et.Padding = Array As Int(14dip, 0, 14dip, 0)
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

Sub Activity_Resume
	sql = Main.sql
	ReloadFromDB
End Sub

Private Sub btnmenu_Click
	pnlmenu.Visible = True
	pnlmenu.BringToFront
End Sub

Private Sub labelhome_Click
	pnlmenu.Visible = False
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
	StartActivity(account)
	Activity.Finish
End Sub

Private Sub ExportDB_Click
	Starter.ExportDBAndShowDialog(True)
End Sub

Private Sub btnacceptexpenses_Click
	If txtamountexpenses.Text = "" Then
		ToastMessageShow("Please enter expense amount.", False)
		Return
	End If

	If IsNumber(txtamountexpenses.Text) = False Then
		ToastMessageShow("Numbers only.", False)
		Return
	End If

	Dim Expense As Double = txtamountexpenses.Text

	If Expense <= 0 Then
		ToastMessageShow("Amount must be greater than 0.", False)
		Return
	End If

	If Expense > Balance Then
		ToastMessageShow("Not enough balance.", False)
		Return
	End If

	Dim Category As String = spinnercategory.SelectedItem
	Dim today As String = DateTime.Date(DateTime.Now)

	sql.ExecNonQuery2( _
		"INSERT INTO tblexpenses (username, category, amount, date) VALUES (?, ?, ?, ?)", _
		Array As Object(Main.usernamee, Category, Expense, today))

	sql.ExecNonQuery2( _
		"INSERT INTO tbltransac (type, amount, date, username) VALUES (?, ?, ?, ?)", _
		Array As Object("Expense - " & Category, Expense, today, Main.usernamee))

	ToastMessageShow("Expense added to " & Category, False)
	txtamountexpenses.Text = ""
	ReloadFromDB
End Sub

Private Sub btnacceptgoal_Click
	If txtgoal.Text = "" Or txtamountgoal.Text = "" Then
		ToastMessageShow("Please complete goal info.", False)
		Return
	End If

	If IsNumber(txtamountgoal.Text) = False Then
		ToastMessageShow("Goal amount must be a number.", False)
		Return
	End If

	Dim target As Double = txtamountgoal.Text
	If target <= 0 Then
		ToastMessageShow("Goal amount must be greater than 0.", False)
		Return
	End If

	Dim today As String = DateTime.Date(DateTime.Now)

	sql.ExecNonQuery2( _
		"INSERT INTO tblgoal (category, goal_amount, current_amount, username) VALUES (?, ?, ?, ?)", _
		Array As Object(txtgoal.Text, target, 0, Main.usernamee))

	sql.ExecNonQuery2( _
		"INSERT INTO tbltransac (type, amount, date, username) VALUES (?, ?, ?, ?)", _
		Array As Object("Goal Set - " & txtgoal.Text, target, today, Main.usernamee))

	ToastMessageShow("Goal saved: " & txtgoal.Text, False)
	txtgoal.Text = ""
	txtamountgoal.Text = ""
End Sub

Sub ReloadFromDB
	Dim c As Cursor
	c = sql.ExecQuery2( _
		"SELECT IFNULL(SUM(amount),0) FROM tblallowance WHERE username=?", _
		Array As String(Main.usernamee))
	c.Position = 0
	Allowance = c.GetDouble2(0)
	c.Close

	c = sql.ExecQuery2( _
		"SELECT IFNULL(SUM(amount),0) FROM tblexpenses WHERE username=?", _
		Array As String(Main.usernamee))
	c.Position = 0
	TotalSpent = c.GetDouble2(0)
	c.Close

	Balance = Allowance - TotalSpent

	txtallowance.Text = NumberFormat(Allowance, 1, 2)
	txtspent.Text = NumberFormat(TotalSpent, 1, 2)
	txtbalance.Text = NumberFormat(Balance, 1, 2)

	ExpenseList.Clear
	CategoryList.Clear
	c = sql.ExecQuery2( _
		"SELECT category, amount FROM tblexpenses WHERE username=? ORDER BY expense_id DESC", _
		Array As String(Main.usernamee))
	For i = 0 To c.RowCount - 1
		c.Position = i
		CategoryList.Add(c.GetString2(0))
		ExpenseList.Add(c.GetDouble2(1))
	Next
	c.Close
End Sub
