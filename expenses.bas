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
	Private btnmenu As Button

	Private txttotweekexp As EditText
	Private listexpenses As ListView

	Private txtsplitbill As EditText
	Private spinnergroup As Spinner
	Private btnevensplit As Button
	Private listsplit As ListView
	Private txtsplitwith As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	sql = Main.sql
	Activity.LoadLayout("layexpenses")

	pnlmenu.LoadLayout("laymenu")
	pnlmenu.Visible = False

	txttotweekexp.Enabled = False

	LoadSpinnerCategories

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
	Dim btnH As Int = 46dip
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

	AddHeaderText("Expenses", pad + 56dip, y, contentW - 56dip, 44dip, primary, 20)
	y = y + 50dip + spacing

	AddHeaderLabel("Total Spent", pad, y, contentW, primary)
	y = y + labelH + 4dip
	StyleCardEditText(txttotweekexp, pad, y, contentW, ctrlH, cardBG)
	y = y + ctrlH + spacing * 2

	AddHeaderLabel("Expense History", pad, y, contentW, primary)
	y = y + labelH + 6dip

	Dim histH As Int = 150dip
	listexpenses.Left = pad
	listexpenses.Top = y
	listexpenses.Width = contentW
	listexpenses.Height = histH
	y = y + histH + spacing * 2

	AddHeaderLabel("Split a Bill", pad, y, contentW, primary)
	y = y + labelH + spacing

	spinnergroup.Left = pad
	spinnergroup.Top = y
	spinnergroup.Width = contentW
	spinnergroup.Height = ctrlH
	y = y + ctrlH + spacing

	StyleInputEditText(txtsplitbill, pad, y, contentW, ctrlH, "Total Bill", cardBG)
	y = y + ctrlH + spacing

	StyleInputEditText(txtsplitwith, pad, y, contentW, ctrlH, "Number of Persons", cardBG)
	y = y + ctrlH + spacing

	btnevensplit.Width = 60%x
	btnevensplit.Left = (w - btnevensplit.Width) / 2
	btnevensplit.Top = y
	btnevensplit.Height = btnH
	StyleButton(btnevensplit, "Split Evenly", primary)
	y = y + btnH + spacing * 2

	AddHeaderLabel("Split History", pad, y, contentW, primary)
	y = y + labelH + 6dip
	Dim splitH As Int = 100%y - y - pad
	If splitH < 80dip Then splitH = 80dip
	listsplit.Left = pad
	listsplit.Top = y
	listsplit.Width = contentW
	listsplit.Height = splitH

	pnlmenu.Width = 70%x
	pnlmenu.Height = 100%y
End Sub

Sub TagGlobals
	btnmenu.Tag = "g"
	txttotweekexp.Tag = "g"
	listexpenses.Tag = "g"
	txtsplitbill.Tag = "g"
	spinnergroup.Tag = "g"
	btnevensplit.Tag = "g"
	listsplit.Tag = "g"
	txtsplitwith.Tag = "g"
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

Sub AddHeaderText(text As String, x As Int, y As Int, w As Int, h As Int, color As Int, sz As Int)
	Dim lbl As Label
	lbl.Initialize("")
	lbl.Text = text
	lbl.TextSize = sz
	lbl.TextColor = color
	lbl.Typeface = Typeface.DEFAULT_BOLD
	Activity.AddView(lbl, x, y, w, h)
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

Sub LoadSpinnerCategories
	spinnergroup.Clear
	spinnergroup.Add("Foods & Groceries")
	spinnergroup.Add("Transpo")
	spinnergroup.Add("Rent")
	spinnergroup.Add("School Supplies")
	spinnergroup.Add("School Projects")
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
