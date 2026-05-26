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
	AddExportButtonToMenu

	txttotweekexp.Enabled = False

	LoadSpinnerCategories

	txtsplitbill.Background = Null
	txtsplitwith.Background = Null
	MakeResponsive
End Sub

Sub MakeResponsive
	Dim w As Int = 100%x
	Dim pad As Int = 16dip
	Dim contentW As Int = w - pad * 2

	txttotweekexp.Left = pad : txttotweekexp.Width = contentW
	spinnergroup.Left = pad : spinnergroup.Width = contentW
	txtsplitbill.Left = pad : txtsplitbill.Width = contentW
	txtsplitwith.Left = pad : txtsplitwith.Width = contentW

	btnevensplit.Width = 60%x
	btnevensplit.Left = (w - btnevensplit.Width) / 2

	listexpenses.Left = pad : listexpenses.Width = contentW
	listsplit.Left = pad : listsplit.Width = contentW

	pnlmenu.Width = 70%x
	pnlmenu.Height = 100%y
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
