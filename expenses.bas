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
	Private txttotweekexp As EditText
	Private listexpenses As ListView
	Private txtsplitbill As EditText
	Private spinnergroup As Spinner
	Private listsplit As ListView
	Private txtsplitwith As EditText
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
	Dim spacing As Int = 10dip
	Dim labelH As Int = 22dip
	Dim ctrlH As Int = 44dip
	Dim btnH As Int = 46dip
	Dim contentW As Int = w - pad * 2
	Dim primary As Int = Colors.RGB(0, 150, 136)
	Dim cardBG As Int = Colors.RGB(225, 240, 240)

	Dim y As Int = pad

	AddMenuButton(pad, y, primary)
	AddTitle("Expenses", pad + 56dip, y, contentW - 56dip, primary)
	y = y + 50dip + spacing

	AddHeaderLabel("Total Spent", pad, y, contentW, primary)
	y = y + labelH + 4dip
	txttotweekexp.Initialize("")
	StyleCardEditText(txttotweekexp, cardBG)
	txttotweekexp.Enabled = False
	Activity.AddView(txttotweekexp, pad, y, contentW, ctrlH)
	y = y + ctrlH + spacing * 2

	AddHeaderLabel("Expense History", pad, y, contentW, primary)
	y = y + labelH + 6dip
	Dim histH As Int = 150dip
	listexpenses.Initialize("")
	Activity.AddView(listexpenses, pad, y, contentW, histH)
	y = y + histH + spacing * 2

	AddHeaderLabel("Split a Bill", pad, y, contentW, primary)
	y = y + labelH + spacing

	spinnergroup.Initialize("")
	spinnergroup.Add("Foods & Groceries")
	spinnergroup.Add("Transpo")
	spinnergroup.Add("Rent")
	spinnergroup.Add("School Supplies")
	spinnergroup.Add("School Projects")
	Activity.AddView(spinnergroup, pad, y, contentW, ctrlH)
	y = y + ctrlH + spacing

	txtsplitbill.Initialize("")
	StyleInputEditText(txtsplitbill, "Total Bill", cardBG)
	txtsplitbill.InputType = txtsplitbill.INPUT_TYPE_DECIMAL_NUMBERS
	Activity.AddView(txtsplitbill, pad, y, contentW, ctrlH)
	y = y + ctrlH + spacing

	txtsplitwith.Initialize("")
	StyleInputEditText(txtsplitwith, "Number of Persons", cardBG)
	txtsplitwith.InputType = txtsplitwith.INPUT_TYPE_NUMBERS
	Activity.AddView(txtsplitwith, pad, y, contentW, ctrlH)
	y = y + ctrlH + spacing

	Dim btnSplit As Button
	btnSplit.Initialize("btnevensplit")
	StyleButton(btnSplit, "Split Evenly", primary)
	Activity.AddView(btnSplit, (w - 60%x) / 2, y, 60%x, btnH)
	y = y + btnH + spacing * 2

	AddHeaderLabel("Split History", pad, y, contentW, primary)
	y = y + labelH + 6dip
	Dim splitH As Int = h - y - pad
	If splitH < 80dip Then splitH = 80dip
	listsplit.Initialize("")
	Activity.AddView(listsplit, pad, y, contentW, splitH)

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
	pnlmenu.AddView(btn, 16dip, pnlmenu.Height - 70dip, pnlmenu.Width - 32dip, 48dip)
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
	LoadExpenses
	LoadSplits
End Sub

Sub LoadExpenses
	listexpenses.Clear
	Dim Total As Double = 0

	Dim c As Cursor
	c = sql.ExecQuery2( _
		"SELECT category, amount, date FROM tblexpenses WHERE username=? ORDER BY expense_id DESC", _
		Array As String(Main.usernamee))

	If c.RowCount = 0 Then
		txttotweekexp.Text = "0.00"
		c.Close
		Return
	End If

	For i = 0 To c.RowCount - 1
		c.Position = i
		Dim cat As String = c.GetString2(0)
		Dim amt As Double = c.GetDouble2(1)
		Dim d As String = c.GetString2(2)
		Total = Total + amt
		listexpenses.AddSingleLine(cat & " - " & NumberFormat(amt, 1, 2) & " (" & d & ")")
	Next
	c.Close

	txttotweekexp.Text = NumberFormat(Total, 1, 2)
End Sub

Sub LoadSplits
	listsplit.Clear
	Dim c As Cursor
	c = sql.ExecQuery2( _
		"SELECT category, persons, bill, share FROM tblsplit WHERE username=? ORDER BY split_id DESC", _
		Array As String(Main.usernamee))
	For i = 0 To c.RowCount - 1
		c.Position = i
		listsplit.AddSingleLine( _
			c.GetString2(0) & " - " & c.GetInt2(1) & " Person - " & _
			NumberFormat(c.GetDouble2(2), 1, 2) & " - " & _
			NumberFormat(c.GetDouble2(3), 1, 2) & " each")
	Next
	c.Close
End Sub

Private Sub ExportDB_Click
	Starter.ExportDBAndShowDialog(True)
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

Private Sub btnevensplit_Click
	If txtsplitbill.Text = "" Or txtsplitwith.Text = "" Then
		ToastMessageShow("Complete split information.", False)
		Return
	End If

	If IsNumber(txtsplitbill.Text) = False Then
		ToastMessageShow("Bill must be number.", False)
		Return
	End If

	If IsNumber(txtsplitwith.Text) = False Then
		ToastMessageShow("Persons must be number.", False)
		Return
	End If

	Dim TotalBill As Double = txtsplitbill.Text
	Dim Persons As Int = txtsplitwith.Text

	If Persons <= 0 Then
		ToastMessageShow("Persons must be greater than 0.", False)
		Return
	End If

	Dim Share As Double = TotalBill / Persons
	Dim Category As String = spinnergroup.SelectedItem
	Dim today As String = DateTime.Date(DateTime.Now)

	sql.ExecNonQuery2( _
		"INSERT INTO tblsplit (username, category, persons, bill, share, date) VALUES (?, ?, ?, ?, ?, ?)", _
		Array As Object(Main.usernamee, Category, Persons, TotalBill, Share, today))

	sql.ExecNonQuery2( _
		"INSERT INTO tbltransac (type, amount, date, username) VALUES (?, ?, ?, ?)", _
		Array As Object("Split - " & Category & " (" & Persons & " pax)", Share, today, Main.usernamee))

	ToastMessageShow("Bill successfully split.", False)

	txtsplitbill.Text = ""
	txtsplitwith.Text = ""
	LoadSplits
End Sub
