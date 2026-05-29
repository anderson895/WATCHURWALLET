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
	Private btnback As Button
	Private lblsavechanges As Label
	Private lvExpenses As ListView
	Private expenseIds As List
End Sub

Sub Activity_Create(FirstTime As Boolean)
	sql = Main.sql
	Activity.LoadLayout("layeditexpenses")

	expenseIds.Initialize
	lvExpenses.Initialize("lvExpenses")

	MakeResponsive
End Sub

Sub MakeResponsive
	Activity.Color = Colors.White
	TagGlobals
	HideExtras

	Dim w As Int = 100%x
	Dim h As Int = 100%y
	Dim pad As Int = 16dip
	Dim spacing As Int = 10dip
	Dim btnH As Int = 48dip
	Dim contentW As Int = w - pad * 2
	Dim primary As Int = Colors.RGB(0, 150, 136)

	Dim y As Int = pad

	AddBackButton(pad, y, primary)
	AddHeaderText("Edit Expenses", pad + 56dip, y, contentW - 56dip, 44dip, primary, 20)
	y = y + 50dip + spacing

	AddHeaderLabel("Tap an item to delete", pad, y, contentW, Colors.DarkGray)
	y = y + 22dip + 6dip

	Dim listH As Int = h - y - pad - btnH - spacing
	If listH < 100dip Then listH = 100dip

	Activity.AddView(lvExpenses, pad, y, contentW, listH)
	lvExpenses.Tag = "g"
	y = y + listH + spacing

	Dim btnSave As Button
	btnSave.Initialize("btnsavechanges")
	StyleButton(btnSave, "Done", primary)
	Activity.AddView(btnSave, pad, y, contentW, btnH)
End Sub

Sub TagGlobals
End Sub

Sub HideExtras
	For i = 0 To Activity.NumberOfViews - 1
		Dim v As View = Activity.GetView(i)
		Dim t As String = "" & v.Tag
		If t <> "g" Then v.Visible = False
	Next
End Sub

Sub AddBackButton(x As Int, y As Int, color As Int)
	Dim btn As Button
	btn.Initialize("btnback")
	btn.Text = Chr(0x2190)
	btn.TextSize = 24
	btn.TextColor = color
	btn.Color = Colors.White
	Activity.AddView(btn, x, y, 44dip, 44dip)
	btn.Tag = "g"
End Sub

Sub AddHeaderText(text As String, x As Int, y As Int, w As Int, h As Int, color As Int, sz As Int)
	Dim lbl As Label
	lbl.Initialize("")
	lbl.Text = text
	lbl.TextSize = sz
	lbl.TextColor = color
	lbl.Typeface = Typeface.DEFAULT_BOLD
	Activity.AddView(lbl, x, y, w, h)
	lbl.Tag = "g"
End Sub

Sub AddHeaderLabel(text As String, x As Int, y As Int, w As Int, color As Int)
	Dim lbl As Label
	lbl.Initialize("")
	lbl.Text = text
	lbl.TextSize = 13
	lbl.TextColor = color
	Activity.AddView(lbl, x, y, w, 22dip)
	lbl.Tag = "g"
End Sub

Sub StyleButton(btn As Button, text As String, color As Int)
	btn.Text = text
	btn.TextColor = Colors.White
	btn.TextSize = 16
	Dim cd As ColorDrawable
	cd.Initialize2(color, 12dip, 0, color)
	btn.Background = cd
	btn.Tag = "g"
End Sub

Sub Activity_Resume
	LoadExpenses
End Sub

Sub LoadExpenses
	lvExpenses.Clear
	expenseIds.Clear

	Dim c As Cursor
	c = sql.ExecQuery2( _
		"SELECT expense_id, category, amount, date FROM tblexpenses WHERE username=? ORDER BY expense_id DESC", _
		Array As String(Main.usernamee))

	For i = 0 To c.RowCount - 1
		c.Position = i
		Dim eid As Int = c.GetInt2(0)
		expenseIds.Add(eid)
		lvExpenses.AddSingleLine( _
			c.GetString2(1) & " - " & NumberFormat2(c.GetDouble2(2), 1, 2, 2, False) & " (" & c.GetString2(3) & ")")
	Next
	c.Close

	If lvExpenses.Size = 0 Then
		ToastMessageShow("No expenses to edit.", False)
	End If
End Sub

Private Sub lvExpenses_ItemClick (Position As Int, Value As Object)
	If Position < 0 Or Position >= expenseIds.Size Then Return

	Dim eid As Int = expenseIds.Get(Position)
	Dim choice As Int = Msgbox2("What do you want to do?", "Edit Expense", "Edit Amount", "Delete", "Cancel", Null)

	If choice = DialogResponse.POSITIVE Then
		Dim oldAmt As Double = GetExpenseAmount(eid)
		Dim oldCat As String = GetExpenseCategory(eid)

		Dim inpdlg As InputDialog
		inpdlg.Input = NumberFormat2(oldAmt, 1, 2, 2, False)
		Dim res As Int = inpdlg.Show("New amount for " & oldCat & ":", "Edit Amount", "Save", "Cancel", "", Null)

		If res = DialogResponse.POSITIVE Then
			If IsNumber(inpdlg.Input) = False Then
				ToastMessageShow("Amount must be a number.", False)
				Return
			End If
			Dim newAmt As Double = inpdlg.Input
			If newAmt <= 0 Then
				ToastMessageShow("Amount must be greater than 0.", False)
				Return
			End If

			Dim today As String = DateTime.Date(DateTime.Now)

			sql.ExecNonQuery2( _
				"UPDATE tblexpenses SET amount=? WHERE expense_id=? AND username=?", _
				Array As Object(newAmt, eid, Main.usernamee))

			sql.ExecNonQuery2( _
				"INSERT INTO tbltransac (type, amount, date, username) VALUES (?, ?, ?, ?)", _
				Array As Object("Expense Edited - " & oldCat & " (was " & NumberFormat2(oldAmt, 1, 2, 2, False) & ")", newAmt, today, Main.usernamee))

			ToastMessageShow("Expense updated.", False)
			LoadExpenses
		End If
	Else If choice = DialogResponse.NEGATIVE Then
		sql.ExecNonQuery2( _
			"DELETE FROM tblexpenses WHERE expense_id=? AND username=?", _
			Array As Object(eid, Main.usernamee))
		ToastMessageShow("Expense deleted.", False)
		LoadExpenses
	End If
End Sub

Sub GetExpenseAmount(eid As Int) As Double
	Dim c As Cursor
	c = sql.ExecQuery2( _
		"SELECT amount FROM tblexpenses WHERE expense_id=? AND username=?", _
		Array As String(eid, Main.usernamee))
	Dim amt As Double = 0
	If c.RowCount > 0 Then
		c.Position = 0
		amt = c.GetDouble2(0)
	End If
	c.Close
	Return amt
End Sub

Sub GetExpenseCategory(eid As Int) As String
	Dim c As Cursor
	c = sql.ExecQuery2( _
		"SELECT category FROM tblexpenses WHERE expense_id=? AND username=?", _
		Array As String(eid, Main.usernamee))
	Dim cat As String = ""
	If c.RowCount > 0 Then
		c.Position = 0
		cat = c.GetString2(0)
	End If
	c.Close
	Return cat
End Sub

Private Sub btnback_Click
	Activity.Finish
End Sub

Private Sub btnsavechanges_Click
	ToastMessageShow("Changes saved.", False)
	Activity.Finish
End Sub
