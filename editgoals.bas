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
	Private lvGoals As ListView
	Private goalIds As List
End Sub

Sub Activity_Create(FirstTime As Boolean)
	sql = Main.sql
	Activity.LoadLayout("layeditgoals")

	goalIds.Initialize
	lvGoals.Initialize("lvGoals")

	MakeResponsive
End Sub

Sub MakeResponsive
	Activity.Color = Colors.White
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
	AddHeaderText("Edit Goals", pad + 56dip, y, contentW - 56dip, 44dip, primary, 20)
	y = y + 50dip + spacing

	AddHeaderLabel("Tap a goal to add progress or delete", pad, y, contentW, Colors.DarkGray)
	y = y + 22dip + 6dip

	Dim listH As Int = h - y - pad - btnH - spacing
	If listH < 100dip Then listH = 100dip

	Activity.AddView(lvGoals, pad, y, contentW, listH)
	lvGoals.Tag = "g"
	y = y + listH + spacing

	Dim btnSave As Button
	btnSave.Initialize("btnsavechanges")
	StyleButton(btnSave, "Done", primary)
	Activity.AddView(btnSave, pad, y, contentW, btnH)
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
	LoadGoals
End Sub

Sub LoadGoals
	lvGoals.Clear
	goalIds.Clear

	Dim c As Cursor
	c = sql.ExecQuery2( _
		"SELECT goal_id, category, goal_amount, current_amount FROM tblgoal WHERE username=? ORDER BY goal_id DESC", _
		Array As String(Main.usernamee))

	For i = 0 To c.RowCount - 1
		c.Position = i
		Dim gid As Int = c.GetInt2(0)
		goalIds.Add(gid)
		lvGoals.AddSingleLine( _
			c.GetString2(1) & " - " & NumberFormat2(c.GetDouble2(3), 1, 2, 2, False) & " / " & NumberFormat2(c.GetDouble2(2), 1, 2, 2, False))
	Next
	c.Close

	If lvGoals.Size = 0 Then
		ToastMessageShow("No goals to edit.", False)
	End If
End Sub

Private Sub lvGoals_ItemClick (Position As Int, Value As Object)
	If Position < 0 Or Position >= goalIds.Size Then Return

	Dim gid As Int = goalIds.Get(Position)
	Dim first As Int = Msgbox2("What do you want to do?", "Edit Goal", "Edit", "Add Progress", "Delete", Null)

	If first = DialogResponse.POSITIVE Then
		EditGoalDetails(gid)
	Else If first = DialogResponse.NEGATIVE Then
		AddGoalProgress(gid)
	Else If first = DialogResponse.CANCEL Then
		DeleteGoal(gid)
	End If
End Sub

Sub EditGoalDetails(gid As Int)
	Dim oldCat As String = GoalCategory(gid)
	Dim oldTarget As Double = GoalTarget(gid)

	Dim inpdlg As InputDialog
	inpdlg.Input = oldCat
	Dim res As Int = inpdlg.Show("New category name:", "Edit Category", "Next", "Cancel", "", Null)
	If res <> DialogResponse.POSITIVE Then Return
	Dim newCat As String = inpdlg.Input.Trim
	If newCat = "" Then
		ToastMessageShow("Category cannot be empty.", False)
		Return
	End If

	inpdlg.Input = NumberFormat2(oldTarget, 1, 2, 2, False)
	res = inpdlg.Show("New target amount:", "Edit Target", "Save", "Cancel", "", Null)
	If res <> DialogResponse.POSITIVE Then Return
	If IsNumber(inpdlg.Input) = False Then
		ToastMessageShow("Target must be a number.", False)
		Return
	End If
	Dim newTarget As Double = inpdlg.Input
	If newTarget <= 0 Then
		ToastMessageShow("Target must be greater than 0.", False)
		Return
	End If

	Dim today As String = DateTime.Date(DateTime.Now)

	sql.ExecNonQuery2( _
		"UPDATE tblgoal SET category=?, goal_amount=? WHERE goal_id=? AND username=?", _
		Array As Object(newCat, newTarget, gid, Main.usernamee))

	sql.ExecNonQuery2( _
		"INSERT INTO tbltransac (type, amount, date, username) VALUES (?, ?, ?, ?)", _
		Array As Object("Goal Edited - " & newCat & " (was " & oldCat & " " & NumberFormat2(oldTarget, 1, 2, 2, False) & ")", newTarget, today, Main.usernamee))

	ToastMessageShow("Goal updated.", False)
	LoadGoals
End Sub

Sub AddGoalProgress(gid As Int)
	Dim inpdlg As InputDialog
	inpdlg.Input = ""
	Dim res As Int = inpdlg.Show("Amount to add to current progress:", "Add Progress", "OK", "Cancel", "", Null)
	If res = DialogResponse.POSITIVE And IsNumber(inpdlg.Input) Then
		Dim addAmt As Double = inpdlg.Input

		Dim cat As String = GoalCategory(gid)
		Dim today As String = DateTime.Date(DateTime.Now)

		sql.ExecNonQuery2( _
			"UPDATE tblgoal SET current_amount = current_amount + ? WHERE goal_id=? AND username=?", _
			Array As Object(addAmt, gid, Main.usernamee))

		sql.ExecNonQuery2( _
			"INSERT INTO tbltransac (type, amount, date, username) VALUES (?, ?, ?, ?)", _
			Array As Object("Goal Progress - " & cat, addAmt, today, Main.usernamee))

		ToastMessageShow("Progress added.", False)
		LoadGoals
	End If
End Sub

Sub DeleteGoal(gid As Int)
	Dim confirm As Int = Msgbox2("Delete this goal permanently?", "Confirm Delete", "Yes", "", "No", Null)
	If confirm <> DialogResponse.POSITIVE Then Return

	sql.ExecNonQuery2( _
		"DELETE FROM tblgoal WHERE goal_id=? AND username=?", _
		Array As Object(gid, Main.usernamee))
	ToastMessageShow("Goal deleted.", False)
	LoadGoals
End Sub

Sub GoalTarget(gid As Int) As Double
	Dim c As Cursor
	c = sql.ExecQuery2( _
		"SELECT goal_amount FROM tblgoal WHERE goal_id=? AND username=?", _
		Array As String(gid, Main.usernamee))
	Dim t As Double = 0
	If c.RowCount > 0 Then
		c.Position = 0
		t = c.GetDouble2(0)
	End If
	c.Close
	Return t
End Sub

Sub GoalCategory(gid As Int) As String
	Dim c As Cursor
	c = sql.ExecQuery2( _
		"SELECT category FROM tblgoal WHERE goal_id=? AND username=?", _
		Array As String(gid, Main.usernamee))
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
