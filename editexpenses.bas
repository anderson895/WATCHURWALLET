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
	Activity.AddView(lvExpenses, 16dip, 120dip, 100%x - 32dip, 100%y - 220dip)
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
			c.GetString2(1) & " - " & NumberFormat(c.GetDouble2(2), 1, 2) & " (" & c.GetString2(3) & ")")
	Next
	c.Close

	If lvExpenses.Size = 0 Then
		ToastMessageShow("No expenses to edit.", False)
	End If
End Sub

Private Sub lvExpenses_ItemClick (Position As Int, Value As Object)
	If Position < 0 Or Position >= expenseIds.Size Then Return

	Dim res As Int = Msgbox2("Delete this expense?", "Confirm", "Yes", "", "No", Null)
	If res = DialogResponse.POSITIVE Then
		Dim eid As Int = expenseIds.Get(Position)
		sql.ExecNonQuery2( _
			"DELETE FROM tblexpenses WHERE expense_id=? AND username=?", _
			Array As Object(eid, Main.usernamee))
		ToastMessageShow("Expense deleted.", False)
		LoadExpenses
	End If
End Sub

Private Sub btnback_Click
	Activity.Finish
End Sub

Private Sub lblsavechanges_Click
	ToastMessageShow("Changes saved.", False)
	Activity.Finish
End Sub
