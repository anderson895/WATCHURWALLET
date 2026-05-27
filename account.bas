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
	Private lblfullname As Label
	Private lblemail As Label
	Private lblusername As Label
	Private btneditexpenses As Button
	Private ListView1 As ListView
	Private EditText1 As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	sql = Main.sql

	Activity.LoadLayout("layaccount")
	pnlmenu.LoadLayout("laymenu")
	pnlmenu.Visible = False
	lblfullname.Text = Main.fname & " " & Main.lname
	lblusername.Text = Main.usernamee
	lblemail.Text = Main.email
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
	Dim spacing As Int = 8dip
	Dim labelH As Int = 22dip
	Dim ctrlH As Int = 44dip
	Dim btnH As Int = 44dip
	Dim contentW As Int = w - pad * 2
	Dim primary As Int = Colors.RGB(0, 150, 136)
	Dim cardBG As Int = Colors.RGB(225, 240, 240)

	Dim y As Int = pad

	AddMenuButton(pad, y, primary)
	AddHeaderText("Account", pad + 56dip, y, contentW - 56dip - 50dip, 44dip, primary, 20)
	AddEditProfileButton(w - pad - 44dip, y, primary)
	y = y + 50dip + spacing * 2

	StyleLabelCard(lblfullname, pad, y, contentW, ctrlH, cardBG, Colors.Black, 16, True)
	y = y + ctrlH + spacing
	StyleLabelCard(lblusername, pad, y, contentW, 36dip, Colors.White, Colors.DarkGray, 13, False)
	y = y + 36dip + spacing
	StyleLabelCard(lblemail, pad, y, contentW, 36dip, Colors.White, Colors.DarkGray, 13, False)
	y = y + 36dip + spacing * 2

	AddHeaderLabel("Set Allowance", pad, y, contentW, primary)
	y = y + labelH + 6dip
	StyleInputEditText(EditText1, pad, y, contentW * 0.62, ctrlH, "0.00", cardBG)

	Dim btnSet As Button
	btnSet.Initialize("btnsetallowance")
	StyleButton(btnSet, "Set", primary)
	Activity.AddView(btnSet, pad + contentW * 0.64, y, contentW * 0.36, ctrlH)
	y = y + ctrlH + spacing * 2

	AddHeaderLabel("History", pad, y, contentW, primary)
	y = y + labelH + 6dip

	Dim btnRowY As Int = 100%y - pad - btnH
	Dim editH As Int = btnH
	Dim editGap As Int = 8dip
	Dim lvH As Int = btnRowY - y - editH - editGap - spacing
	If lvH < 100dip Then lvH = 100dip

	ListView1.Left = pad
	ListView1.Top = y
	ListView1.Width = contentW
	ListView1.Height = lvH

	Dim editsY As Int = y + lvH + spacing

	btneditexpenses.Left = pad
	btneditexpenses.Top = editsY
	btneditexpenses.Width = (contentW - editGap) / 2
	btneditexpenses.Height = editH
	StyleButton(btneditexpenses, "Edit Expenses", primary)

	Dim btnEditGoals As Button
	btnEditGoals.Initialize("btneditgoals")
	StyleButton(btnEditGoals, "Edit Goals", primary)
	Activity.AddView(btnEditGoals, pad + (contentW - editGap) / 2 + editGap, editsY, (contentW - editGap) / 2, editH)

	pnlmenu.Width = 70%x
	pnlmenu.Height = 100%y
End Sub

Sub TagGlobals
	lblfullname.Tag = "g"
	lblemail.Tag = "g"
	lblusername.Tag = "g"
	btneditexpenses.Tag = "g"
	ListView1.Tag = "g"
	EditText1.Tag = "g"
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

Sub AddMenuButton(x As Int, y As Int, color As Int)
	Dim btn As Button
	btn.Initialize("btnmenu")
	btn.Text = Chr(0x2630)
	btn.TextSize = 22
	btn.TextColor = color
	btn.Color = Colors.White
	Activity.AddView(btn, x, y, 44dip, 44dip)
End Sub

Sub AddEditProfileButton(x As Int, y As Int, color As Int)
	Dim btn As Button
	btn.Initialize("btneditmyacc")
	btn.Text = Chr(0x270E)
	btn.TextSize = 20
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

Sub AddHeaderText(text As String, x As Int, y As Int, w As Int, h As Int, color As Int, sz As Int)
	Dim lbl As Label
	lbl.Initialize("")
	lbl.Text = text
	lbl.TextSize = sz
	lbl.TextColor = color
	lbl.Typeface = Typeface.DEFAULT_BOLD
	Activity.AddView(lbl, x, y, w, h)
End Sub

Sub StyleLabelCard(lbl As Label, x As Int, y As Int, w As Int, h As Int, bg As Int, txtColor As Int, sz As Int, bold As Boolean)
	lbl.Left = x : lbl.Top = y : lbl.Width = w : lbl.Height = h
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
	btn.TextSize = 14
	Dim cd As ColorDrawable
	cd.Initialize2(color, 12dip, 0, color)
	btn.Background = cd
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

Sub Activity_Resume
	LoadHistory
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
	StartActivity(goal)
	Activity.Finish
End Sub

Private Sub labelaccount_Click
	pnlmenu.Visible = False
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

	Dim today As String = DateTime.Date(DateTime.Now)

	sql.ExecNonQuery2( _
		"INSERT INTO tblallowance (amount, date, username) VALUES (?, ?, ?)", _
		Array As Object(allowanceAmount, today, Main.usernamee))

	sql.ExecNonQuery2( _
		"INSERT INTO tbltransac (type, amount, date, username) VALUES (?, ?, ?, ?)", _
		Array As Object("Allowance", allowanceAmount, today, Main.usernamee))

	LoadHistory
	ToastMessageShow("Allowance Set: " & NumberFormat(allowanceAmount, 1, 2), False)
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
	Dim c1 As Cursor
	c1 = sql.ExecQuery2( _
		"SELECT type, amount, date FROM tbltransac WHERE username=? ORDER BY transac_id DESC", _
		Array As String(Main.usernamee))
	ListView1.Clear
	For i = 0 To c1.RowCount - 1
		c1.Position = i
		ListView1.AddSingleLine( _
			c1.GetString2(0) & ": " & NumberFormat(c1.GetDouble2(1), 1, 2) & " on " & c1.GetString2(2))
	Next
	c1.Close
End Sub
