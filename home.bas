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
	Private btnmenu As Button
	Private txtstudentsname As EditText
	Private txtallowance As EditText
	Private txtdate As EditText
	Private txtspent As EditText
	Private txtbalance As EditText
	Private txtamountexpenses As EditText
	Private spinnercategory As Spinner
	Private btnacceptexpenses As Button
	Private txtamountgoal As EditText
	Private btnacceptgoal As Button
	Private txtgoal As EditText

	Dim TotalSpent As Double = 0
	Dim Balance As Double = 0
	Dim Allowance As Double = 0
End Sub

Sub Activity_Create(FirstTime As Boolean)
	sql = Main.sql
	Activity.LoadLayout("layhome")
	pnlmenu.LoadLayout("laymenu")
	pnlmenu.Visible = False

	If ExpenseList.IsInitialized = False Then ExpenseList.Initialize
	If CategoryList.IsInitialized = False Then CategoryList.Initialize

	spinnercategory.Clear
	spinnercategory.Add("Foods & Groceries")
	spinnercategory.Add("Transpo")
	spinnercategory.Add("Rent")
	spinnercategory.Add("School Supplies")
	spinnercategory.Add("School Projects")

	DateTime.DateFormat = "MMMM dd, yyyy"
	txtdate.Text = DateTime.Date(DateTime.Now)

	txtspent.Enabled = False
	txtbalance.Enabled = False
	txtdate.Enabled = False

	txtstudentsname.Text = Main.fname & " " & Main.lname

	MakeResponsive
	AddExportButtonToMenu
End Sub

Sub MakeResponsive
	Activity.Color = Colors.White
	TagGlobals
	HoistTaggedToActivity
	HideExtras

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

	btnmenu.Left = pad
	btnmenu.Top = y
	btnmenu.Width = 44dip
	btnmenu.Height = 44dip
	btnmenu.Text = Chr(0x2630)
	btnmenu.TextSize = 22
	btnmenu.TextColor = primary
	btnmenu.Color = Colors.White

	txtstudentsname.Left = pad + 56dip
	txtstudentsname.Top = y + 4dip
	txtstudentsname.Width = contentW - 56dip
	txtstudentsname.Height = 36dip
	txtstudentsname.TextSize = 18
	txtstudentsname.TextColor = Colors.Black
	txtstudentsname.Background = Null
	txtstudentsname.Enabled = False

	y = y + 50dip + spacing

	txtdate.Left = pad
	txtdate.Top = y
	txtdate.Width = contentW
	txtdate.Height = 28dip
	txtdate.TextSize = 13
	txtdate.TextColor = Colors.DarkGray
	txtdate.Background = Null
	y = y + 28dip + spacing

	AddHeaderLabel("Allowance", pad, y, contentW, primary)
	y = y + labelH + 4dip
	StyleCardEditText(txtallowance, pad, y, contentW, ctrlH, cardBG)
	y = y + ctrlH + spacing

	AddHeaderLabel("Spent", pad, y, contentW, primary)
	y = y + labelH + 4dip
	StyleCardEditText(txtspent, pad, y, contentW, ctrlH, cardBG)
	y = y + ctrlH + spacing

	AddHeaderLabel("Balance", pad, y, contentW, primary)
	y = y + labelH + 4dip
	StyleCardEditText(txtbalance, pad, y, contentW, ctrlH, cardBG)
	y = y + ctrlH + spacing * 2

	AddHeaderLabel("Add Expenses", pad, y, contentW, primary)
	y = y + labelH + spacing

	spinnercategory.Left = pad
	spinnercategory.Top = y
	spinnercategory.Width = contentW
	spinnercategory.Height = ctrlH
	y = y + ctrlH + spacing

	StyleInputEditText(txtamountexpenses, pad, y, contentW, ctrlH, "Amount", cardBG)
	y = y + ctrlH + spacing

	btnacceptexpenses.Width = 60%x
	btnacceptexpenses.Left = (w - btnacceptexpenses.Width) / 2
	btnacceptexpenses.Top = y
	btnacceptexpenses.Height = btnH
	StyleButton(btnacceptexpenses, "Accept", primary)
	y = y + btnH + spacing * 2

	AddHeaderLabel("Add to Goals", pad, y, contentW, primary)
	y = y + labelH + spacing

	StyleInputEditText(txtgoal, pad, y, contentW, ctrlH, "Goal name", cardBG)
	y = y + ctrlH + spacing

	StyleInputEditText(txtamountgoal, pad, y, contentW, ctrlH, "Amount", cardBG)
	y = y + ctrlH + spacing

	btnacceptgoal.Width = 60%x
	btnacceptgoal.Left = (w - btnacceptgoal.Width) / 2
	btnacceptgoal.Top = y
	btnacceptgoal.Height = btnH
	StyleButton(btnacceptgoal, "Add Goal", primary)

	pnlmenu.Width = 70%x
	pnlmenu.Height = 100%y
End Sub

Sub TagGlobals
	btnmenu.Tag = "g"
	txtstudentsname.Tag = "g"
	txtdate.Tag = "g"
	txtallowance.Tag = "g"
	txtspent.Tag = "g"
	txtbalance.Tag = "g"
	spinnercategory.Tag = "g"
	txtamountexpenses.Tag = "g"
	btnacceptexpenses.Tag = "g"
	txtgoal.Tag = "g"
	txtamountgoal.Tag = "g"
	btnacceptgoal.Tag = "g"
	pnlmenu.Tag = "g"
End Sub

Sub HideExtras
	For i = 0 To Activity.NumberOfViews - 1
		Dim v As View = Activity.GetView(i)
		Dim t As String = "" & v.Tag
		If t <> "g" Then v.Visible = False
	Next
End Sub

Sub HoistTaggedToActivity
	For i = 0 To Activity.NumberOfViews - 1
		Dim v As View = Activity.GetView(i)
		Dim vt As String = "" & v.Tag
		If v Is Panel And vt <> "g" Then
			Dim p As Panel = v
			For j = p.NumberOfViews - 1 To 0 Step -1
				Dim child As View = p.GetView(j)
				Dim ct As String = "" & child.Tag
				If ct = "g" Then
					Dim cx As Int = child.Left + p.Left
					Dim cy As Int = child.Top + p.Top
					Dim cw As Int = child.Width
					Dim ch As Int = child.Height
					child.RemoveView
					Activity.AddView(child, cx, cy, cw, ch)
				End If
			Next
		End If
	Next
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

Sub StyleCardEditText(et As EditText, x As Int, y As Int, w As Int, h As Int, bg As Int)
	et.Left = x : et.Top = y : et.Width = w : et.Height = h
	et.TextSize = 16
	et.TextColor = Colors.Black
	Dim cd As ColorDrawable
	cd.Initialize2(bg, 10dip, 0, bg)
	et.Background = cd
	et.SingleLine = True
	et.Padding = Array As Int(14dip, 0, 14dip, 0)
End Sub

Sub StyleInputEditText(et As EditText, x As Int, y As Int, w As Int, h As Int, hint As String, bg As Int)
	et.Left = x : et.Top = y : et.Width = w : et.Height = h
	et.Hint = hint
	et.TextSize = 15
	et.TextColor = Colors.Black
	Dim cd As ColorDrawable
	cd.Initialize2(bg, 10dip, 0, bg)
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
	ReloadFromDB
End Sub

Private Sub btnmenu_Click
	pnlmenu.Visible = True
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

Private Sub txtallowance_TextChanged (Old As String, New As String)
	If New = "" Then Return
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
