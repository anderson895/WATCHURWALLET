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
	Private pnlcontent As Panel
	Private lblfullname As Label
	Private lblemail As Label
	Private lblusername As Label
	Private ListView1 As ListView
	Private EditText1 As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	sql = Main.sql
	BuildScreen
End Sub

Sub BuildScreen
	Activity.RemoveAllViews
	Activity.Color = Colors.White

	pnlcontent.Initialize("")
	Activity.AddView(pnlcontent, 0, 0, 100%x, 100%y)

	Dim w As Int = 100%x
	Dim h As Int = 100%y
	Dim pad As Int = 16dip
	Dim spacing As Int = 8dip
	Dim labelH As Int = 22dip
	Dim ctrlH As Int = 44dip
	Dim btnH As Int = 44dip
	Dim contentW As Int = w - pad * 2
	Dim primary As Int = Colors.RGB(0, 150, 136)
	Dim cardBG As Int = Colors.RGB(235, 245, 245)

	Dim y As Int = pad

	Dim btnMenu As Button
	btnMenu.Initialize("btnmenu")
	btnMenu.Text = Chr(0x2630)
	btnMenu.TextSize = 22
	btnMenu.TextColor = primary
	btnMenu.Color = Colors.White
	pnlcontent.AddView(btnMenu, pad, y, 44dip, 44dip)

	Dim lblTitle As Label
	lblTitle.Initialize("")
	lblTitle.Text = "Account"
	lblTitle.TextSize = 20
	lblTitle.TextColor = primary
	lblTitle.Typeface = Typeface.DEFAULT_BOLD
	pnlcontent.AddView(lblTitle, pad + 56dip, y, contentW - 56dip - 50dip, 44dip)

	Dim btnEdit As Button
	btnEdit.Initialize("btneditmyacc")
	btnEdit.Text = Chr(0x270E)
	btnEdit.TextSize = 20
	btnEdit.TextColor = primary
	btnEdit.Color = Colors.White
	pnlcontent.AddView(btnEdit, w - pad - 44dip, y, 44dip, 44dip)
	y = y + 50dip + spacing * 2

	lblfullname.Initialize("")
	lblfullname.Text = Main.fname & " " & Main.lname
	StyleLabelCard(lblfullname, cardBG, Colors.Black, 16, True)
	pnlcontent.AddView(lblfullname, pad, y, contentW, ctrlH)
	y = y + ctrlH + spacing

	lblusername.Initialize("")
	lblusername.Text = Main.usernamee
	StyleLabelCard(lblusername, Colors.White, Colors.DarkGray, 13, False)
	pnlcontent.AddView(lblusername, pad, y, contentW, 36dip)
	y = y + 36dip + spacing

	lblemail.Initialize("")
	lblemail.Text = Main.email
	StyleLabelCard(lblemail, Colors.White, Colors.DarkGray, 13, False)
	pnlcontent.AddView(lblemail, pad, y, contentW, 36dip)
	y = y + 36dip + spacing * 2

	AddLabel("Set Allowance", pad, y, contentW, primary)
	y = y + labelH + 6dip

	EditText1.Initialize("EditText1")
	StyleInputEditText(EditText1, "0.00", cardBG)
	EditText1.InputType = EditText1.INPUT_TYPE_DECIMAL_NUMBERS
	pnlcontent.AddView(EditText1, pad, y, contentW * 0.62, ctrlH)

	Dim btnSet As Button
	btnSet.Initialize("btnsetallowance")
	StyleButton(btnSet, "Set", primary)
	pnlcontent.AddView(btnSet, pad + contentW * 0.64, y, contentW * 0.36, ctrlH)
	y = y + ctrlH + spacing * 2

	AddLabel("History", pad, y, contentW, primary)
	y = y + labelH + 6dip

	Dim btnRowY As Int = h - pad - btnH
	Dim editGap As Int = 8dip
	Dim lvH As Int = btnRowY - y - spacing
	If lvH < 100dip Then lvH = 100dip

	ListView1.Initialize("")
	ListView1.SingleLineLayout.ItemHeight = 56dip
	ListView1.SingleLineLayout.Label.TextSize = 14
	ListView1.SingleLineLayout.Label.TextColor = Colors.Black
	ListView1.SingleLineLayout.Label.Gravity = Gravity.CENTER_VERTICAL + Gravity.LEFT
	ListView1.SingleLineLayout.Label.Padding = Array As Int(14dip, 0, 14dip, 0)
	pnlcontent.AddView(ListView1, pad, y, contentW, lvH)

	Dim btnEditExp As Button
	btnEditExp.Initialize("btneditexpenses")
	StyleButton(btnEditExp, "Edit Expenses", primary)
	pnlcontent.AddView(btnEditExp, pad, btnRowY, (contentW - editGap) / 2, btnH)

	Dim btnEditGoals As Button
	btnEditGoals.Initialize("btneditgoals")
	StyleButton(btnEditGoals, "Edit Goals", primary)
	pnlcontent.AddView(btnEditGoals, pad + (contentW - editGap) / 2 + editGap, btnRowY, (contentW - editGap) / 2, btnH)

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

Sub AddLabel(text As String, x As Int, y As Int, w As Int, color As Int)
	Dim lbl As Label
	lbl.Initialize("")
	lbl.Text = text
	lbl.TextSize = 14
	lbl.TextColor = color
	lbl.Typeface = Typeface.DEFAULT_BOLD
	pnlcontent.AddView(lbl, x, y, w, 22dip)
End Sub

Sub StyleLabelCard(lbl As Label, bg As Int, txtColor As Int, sz As Int, bold As Boolean)
	lbl.TextSize = sz
	lbl.TextColor = txtColor
	If bold Then lbl.Typeface = Typeface.DEFAULT_BOLD
	If bg <> Colors.White Then
		Dim cd As ColorDrawable
		cd.Initialize2(bg, 10dip, 0, bg)
		lbl.Background = cd
		lbl.Padding = Array As Int(14dip, 0, 14dip, 0)
	Else
		lbl.Background = Null
		lbl.Padding = Array As Int(4dip, 0, 4dip, 0)
	End If
	lbl.Gravity = Gravity.CENTER_VERTICAL + Gravity.LEFT
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
	btn.TextSize = 14
	Dim cd As ColorDrawable
	cd.Initialize2(color, 12dip, 0, color)
	btn.Background = cd
End Sub

Private Sub ExportDB_Click
	Starter.ExportDBAndShowDialog(True)
End Sub

Sub Activity_Resume
	' Refresh sql in case the connection was reinitialized
	' (e.g. after Export DB closed/reopened the connection).
	sql = Main.sql
	LoadHistory
End Sub

Private Sub btnmenu_Click
	pnlcontent.Visible = False
	pnlmenu.Visible = True
End Sub

Private Sub labelhome_Click
	pnlmenu.Visible = False
	pnlcontent.Visible = True
	StartActivity(home)
	Activity.Finish
End Sub

Private Sub labelexpenses_Click
	pnlmenu.Visible = False
	pnlcontent.Visible = True
	StartActivity(expenses)
	Activity.Finish
End Sub

Private Sub labelgoal_Click
	pnlmenu.Visible = False
	pnlcontent.Visible = True
	StartActivity(goal)
	Activity.Finish
End Sub

Private Sub labelaccount_Click
	pnlmenu.Visible = False
	pnlcontent.Visible = True
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

	If Main.usernamee = "" Then
		ToastMessageShow("Not logged in.", True)
		Return
	End If

	' Ensure a live connection. Export DB closes/reopens Main.sql,
	' which can leave our local reference pointing at a stale handle.
	sql = Main.sql

	Dim today As String = DateTime.Date(DateTime.Now)

	Try
		sql.ExecNonQuery2( _
			"INSERT INTO tblallowance (amount, date, username) VALUES (?, ?, ?)", _
			Array As Object(allowanceAmount, today, Main.usernamee))
		Log("INSERT tblallowance OK for " & Main.usernamee & " amount=" & allowanceAmount)
	Catch
		Log("INSERT tblallowance FAILED: " & LastException.Message)
		ToastMessageShow("Save failed: " & LastException.Message, True)
		Return
	End Try

	Try
		sql.ExecNonQuery2( _
			"INSERT INTO tbltransac (type, amount, date, username) VALUES (?, ?, ?, ?)", _
			Array As Object("Allowance", allowanceAmount, today, Main.usernamee))
		Log("INSERT tbltransac OK")
	Catch
		Log("INSERT tbltransac FAILED: " & LastException.Message)
	End Try

	LoadHistory
	ToastMessageShow("Allowance Set: " & NumberFormat2(allowanceAmount, 1, 2, 2, False), False)
	EditText1.Text = ""
End Sub

Private Sub btneditexpenses_Click
	StartActivity(editexpenses)
End Sub

Private Sub btneditmyacc_Click
	StartActivity(myacc)
End Sub

Private Sub btneditgoals_Click
	StartActivity(editgoals)
End Sub

Sub LoadHistory
	sql = Main.sql
	ListView1.Clear

	Try
		Dim c1 As Cursor
		c1 = sql.ExecQuery2( _
			"SELECT type, amount, date FROM tbltransac WHERE username=? ORDER BY transac_id DESC", _
			Array As String(Main.usernamee))
		Log("LoadHistory rows for " & Main.usernamee & ": " & c1.RowCount)
		For i = 0 To c1.RowCount - 1
			c1.Position = i
			ListView1.AddSingleLine( _
				c1.GetString2(0) & ": " & NumberFormat2(c1.GetDouble2(1), 1, 2, 2, False) & " on " & c1.GetString2(2))
		Next
		c1.Close
	Catch
		Log("LoadHistory FAILED: " & LastException.Message)
		ToastMessageShow("Failed to load history: " & LastException.Message, True)
	End Try
End Sub
