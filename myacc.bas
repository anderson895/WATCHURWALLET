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
	Private EditText1 As EditText
	Private ListView1 As ListView
	Private txtfname As EditText
	Private txtlname As EditText
	Private txtusername As EditText
	Private txtemail As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	Activity.LoadLayout("laymyacc")
	sql = Main.sql

	LoadAccountInfo
	MakeResponsive
End Sub

Sub MakeResponsive
	Dim w As Int = 100%x
	Dim pad As Int = 16dip
	Dim contentW As Int = w - pad * 2

	txtusername.Left = pad : txtusername.Width = contentW
	txtfname.Left = pad : txtfname.Width = contentW
	txtlname.Left = pad : txtlname.Width = contentW
	txtemail.Left = pad : txtemail.Width = contentW
End Sub

Sub Activity_Resume

End Sub

Sub Activity_Pause (UserClosed As Boolean)

End Sub

Sub LoadAccountInfo
	Dim c As Cursor
	c = sql.ExecQuery2( _
		"SELECT fname, lname, email FROM tblusers WHERE username=?", _
		Array As String(Main.usernamee))
	If c.RowCount > 0 Then
		c.Position = 0
		txtfname.Text = c.GetString2(0)
		txtlname.Text = c.GetString2(1)
		txtemail.Text = c.GetString2(2)
		txtusername.Text = Main.usernamee
		txtusername.Enabled = False
	End If
	c.Close
End Sub

Private Sub btneditflname_Click
	If txtfname.Text = "" Or txtlname.Text = "" Or txtemail.Text = "" Then
		ToastMessageShow("All fields required.", False)
		Return
	End If

	Try
		sql.ExecNonQuery2( _
			"UPDATE tblusers SET fname=?, lname=?, email=? WHERE username=?", _
			Array As Object(txtfname.Text, txtlname.Text, txtemail.Text, Main.usernamee))

		Main.fname = txtfname.Text
		Main.lname = txtlname.Text
		Main.email = txtemail.Text

		ToastMessageShow("Account updated.", False)
	Catch
		ToastMessageShow("Update failed (email may already exist).", False)
		Log(LastException.Message)
	End Try
End Sub

Private Sub btnlogout_Click
	Main.usernamee = ""
	Main.fname = ""
	Main.lname = ""
	Main.email = ""
	Main.role = ""

	StartActivity(Main)
	Activity.Finish
End Sub

Private Sub btnback_Click
	Activity.Finish
End Sub
