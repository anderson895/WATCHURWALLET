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
	Dim rs As ResultSet
End Sub

Sub Globals
	Private B4XTable1 As B4XTable
	Private btnDelete As Button
	Private btnUpdate As Button
	Dim inpdlg As InputDialog
End Sub

Sub Activity_Create(FirstTime As Boolean)
	sql = Main.sql

	Activity.LoadLayout("AdminPanelLayout")

	B4XTable1.AddColumn("Username", B4XTable1.COLUMN_TYPE_TEXT)
	B4XTable1.AddColumn("Role", B4XTable1.COLUMN_TYPE_TEXT)
	B4XTable1.AddColumn("First Name", B4XTable1.COLUMN_TYPE_TEXT)
	B4XTable1.AddColumn("Last Name", B4XTable1.COLUMN_TYPE_TEXT)
	B4XTable1.AddColumn("Email", B4XTable1.COLUMN_TYPE_TEXT)
	B4XTable1.AddColumn("Password", B4XTable1.COLUMN_TYPE_TEXT)

	ShowUsers
End Sub

Sub Activity_Resume
	ShowUsers
End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub ShowUsers
	Dim data As List
	data.Initialize

	rs = sql.ExecQuery("SELECT username, role, fname, lname, email, password FROM tblusers ORDER BY username")

	Do While rs.NextRow
		Dim row(6) As Object
		row(0) = rs.GetString("username")
		row(1) = rs.GetString("role")
		row(2) = rs.GetString("fname")
		row(3) = rs.GetString("lname")
		row(4) = rs.GetString("email")
		row(5) = rs.GetString("password")
		data.Add(row)
	Loop

	rs.Close
	B4XTable1.SetData(data)
End Sub


Private Sub btnDelete_Click
	Dim uname As String
	inpdlg.Input = ""

	Dim res As Int = inpdlg.Show("Enter username to delete", "Delete User", "OK", "Cancel", "", Null)
	uname = inpdlg.Input

	If res = DialogResponse.POSITIVE Then
		If uname = "" Then
			ToastMessageShow("Username required.", False)
			Return
		End If

		If uname = Main.usernamee Then
			Msgbox("You cannot delete your own admin account.", "")
			Return
		End If

		Dim c As Cursor
		c = sql.ExecQuery2("SELECT * FROM tblusers WHERE username = ?", Array As String(uname))

		If c.RowCount > 0 Then

			Dim confirm As Int
			confirm = Msgbox2("Delete this user and all related data?", "Confirm", "Yes", "", "No", Null)

			If confirm = DialogResponse.POSITIVE Then
				sql.ExecNonQuery2("DELETE FROM tblusers WHERE username = ?", Array As String(uname))
				sql.ExecNonQuery2("DELETE FROM tblallowance WHERE username = ?", Array As String(uname))
				sql.ExecNonQuery2("DELETE FROM tbltransac WHERE username = ?", Array As String(uname))
				sql.ExecNonQuery2("DELETE FROM tblgoal WHERE username = ?", Array As String(uname))
				sql.ExecNonQuery2("DELETE FROM tblexpenses WHERE username = ?", Array As String(uname))
				sql.ExecNonQuery2("DELETE FROM tblsplit WHERE username = ?", Array As String(uname))
				ShowUsers
				ToastMessageShow("User Deleted", False)
			End If

		Else
			Msgbox("User not found", "")
		End If
		c.Close
	End If
End Sub

Private Sub btnUpdate_Click
	Dim uname As String
	inpdlg.Input = ""

	Dim res As Int = inpdlg.Show("Enter username to update", "Update User", "OK", "Cancel", "", Null)
	uname = inpdlg.Input

	If res = DialogResponse.POSITIVE Then
		If uname = "" Then
			ToastMessageShow("Username required.", False)
			Return
		End If

		Dim c As Cursor
		c = sql.ExecQuery2("SELECT * FROM tblusers WHERE username = ?", Array As String(uname))

		If c.RowCount > 0 Then

			inpdlg.Input = ""
			inpdlg.Show("Enter new password", "Update", "OK", "Cancel", "", Null)
			Dim newpass As String = inpdlg.Input

			inpdlg.Input = ""
			inpdlg.Show("Enter role (admin/user)", "Update", "OK", "Cancel", "", Null)
			Dim newrole As String = inpdlg.Input.ToLowerCase

			If newrole <> "admin" And newrole <> "user" Then
				Msgbox("Role must be 'admin' or 'user'.", "")
				c.Close
				Return
			End If

			If newpass = "" Then
				sql.ExecNonQuery2("UPDATE tblusers SET role=? WHERE username=?", Array As Object(newrole, uname))
			Else
				sql.ExecNonQuery2("UPDATE tblusers SET password=?, role=? WHERE username=?", Array As Object(newpass, newrole, uname))
			End If

			ShowUsers
			ToastMessageShow("User Updated", False)

		Else
			Msgbox("User not found", "")
		End If
		c.Close
	End If
End Sub

Private Sub btnBack_Click
	StartActivity(dashboard)
End Sub
