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
	Private txtUser As EditText
	Private txtPass As EditText
	Private btnCreate As Button
	Private txtemail As EditText
	Private txtfname As EditText
	Private txtlname As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	If FirstTime Then
		sql.Initialize(File.DirInternal, "saddbb.db", False)
	End If
	Activity.LoadLayout("SignupLayout")
End Sub

Sub Activity_Resume
	txtUser.Text = ""
	txtPass.Text = ""
	txtemail.Text = ""
	txtfname.Text = ""
	txtlname.Text = ""

	txtUser.RequestFocus
End Sub

Private Sub btnCreate_Click
	Dim u As String = txtUser.Text.Trim
	Dim p As String = txtPass.Text
	Dim em As String = txtemail.Text.Trim
	Dim fn As String = txtfname.Text.Trim
	Dim ln As String = txtlname.Text.Trim

	If u = "" Or p = "" Or fn = "" Or ln = "" Or em = "" Then
		Msgbox("All fields must be completed.", "")
		Return
	End If

	If em.Contains("@") = False Or em.Contains(".") = False Then
		Msgbox("Please enter a valid email.", "")
		Return
	End If

	If p.Length < 4 Then
		Msgbox("Password must be at least 4 characters.", "")
		Return
	End If

	Dim rs As ResultSet
	rs = sql.ExecQuery2("SELECT 1 FROM tblusers WHERE username = ?", Array As String(u))
	If rs.NextRow Then
		ToastMessageShow("Username already exists!", False)
		rs.Close
		Return
	End If
	rs.Close

	rs = sql.ExecQuery2("SELECT 1 FROM tblusers WHERE email = ?", Array As String(em))
	If rs.NextRow Then
		ToastMessageShow("Email already in use!", False)
		rs.Close
		Return
	End If
	rs.Close

	Try
		sql.ExecNonQuery2( _
			"INSERT INTO tblusers (username, password, role, fname, lname, email) VALUES (?, ?, ?, ?, ?, ?)", _
			Array As Object(u, p, "user", fn, ln, em))
		ToastMessageShow("Account Created!", False)
		Activity.Finish
	Catch
		ToastMessageShow("Sign up failed: " & LastException.Message, False)
	End Try
End Sub

Private Sub btnBack_Click
	StartActivity(Main)
End Sub
