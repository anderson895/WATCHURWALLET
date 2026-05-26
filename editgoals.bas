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
	Activity.AddView(lvGoals, 16dip, 120dip, 100%x - 32dip, 100%y - 220dip)
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
			c.GetString2(1) & " - " & NumberFormat(c.GetDouble2(3), 1, 2) & " / " & NumberFormat(c.GetDouble2(2), 1, 2))
	Next
	c.Close

	If lvGoals.Size = 0 Then
		ToastMessageShow("No goals to edit.", False)
	End If
End Sub

Private Sub lvGoals_ItemClick (Position As Int, Value As Object)
	If Position < 0 Or Position >= goalIds.Size Then Return

	Dim gid As Int = goalIds.Get(Position)
	Dim choice As Int = Msgbox2("What do you want to do?", "Edit Goal", "Add Progress", "Delete", "Cancel", Null)

	If choice = DialogResponse.POSITIVE Then
		Dim inpdlg As InputDialog
		inpdlg.Input = ""
		Dim res As Int = inpdlg.Show("Amount to add to current progress:", "Add Progress", "OK", "Cancel", "", Null)
		If res = DialogResponse.POSITIVE And IsNumber(inpdlg.Input) Then
			Dim addAmt As Double = inpdlg.Input
			sql.ExecNonQuery2( _
				"UPDATE tblgoal SET current_amount = current_amount + ? WHERE goal_id=? AND username=?", _
				Array As Object(addAmt, gid, Main.usernamee))
			ToastMessageShow("Progress added.", False)
			LoadGoals
		End If
	Else If choice = DialogResponse.NEGATIVE Then
		sql.ExecNonQuery2( _
			"DELETE FROM tblgoal WHERE goal_id=? AND username=?", _
			Array As Object(gid, Main.usernamee))
		ToastMessageShow("Goal deleted.", False)
		LoadGoals
	End If
End Sub

Private Sub btnback_Click
	Activity.Finish
End Sub

Private Sub lblsavechanges_Click
	Activity.Finish
End Sub
