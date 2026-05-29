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
	Private txtfname As EditText
	Private txtlname As EditText
	Private txtusername As EditText
	Private txtemail As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	sql = Main.sql
	BuildScreen
	LoadAccountInfo
End Sub

Sub BuildScreen
	Activity.RemoveAllViews
	Activity.Color = Colors.White

	Dim w As Int = 100%x
	Dim pad As Int = 16dip
	Dim spacing As Int = 10dip
	Dim labelH As Int = 22dip
	Dim ctrlH As Int = 44dip
	Dim btnH As Int = 48dip
	Dim contentW As Int = w - pad * 2
	Dim primary As Int = Colors.RGB(0, 150, 136)
	Dim cardBG As Int = Colors.RGB(235, 245, 245)

	Dim y As Int = pad

	AddBackButton(pad, y, primary)
	AddTitle("My Account", pad + 56dip, y, contentW - 56dip, primary)
	y = y + 50dip + spacing * 2

	AddHeaderLabel("Username", pad, y, contentW, primary)
	y = y + labelH + 6dip
	txtusername.Initialize("txtusername")
	StyleInputEditText(txtusername, "", cardBG)
	Activity.AddView(txtusername, pad, y, contentW, ctrlH)
	y = y + ctrlH + spacing

	AddHeaderLabel("First Name", pad, y, contentW, primary)
	y = y + labelH + 6dip
	txtfname.Initialize("txtfname")
	StyleInputEditText(txtfname, "", cardBG)
	Activity.AddView(txtfname, pad, y, contentW, ctrlH)
	y = y + ctrlH + spacing

	AddHeaderLabel("Last Name", pad, y, contentW, primary)
	y = y + labelH + 6dip
	txtlname.Initialize("txtlname")
	StyleInputEditText(txtlname, "", cardBG)
	Activity.AddView(txtlname, pad, y, contentW, ctrlH)
	y = y + ctrlH + spacing

	AddHeaderLabel("Email", pad, y, contentW, primary)
	y = y + labelH + 6dip
	txtemail.Initialize("txtemail")
	StyleInputEditText(txtemail, "", cardBG)
	Activity.AddView(txtemail, pad, y, contentW, ctrlH)
	y = y + ctrlH + spacing * 2

	Dim btnSave As Button
	btnSave.Initialize("btneditflname")
	StyleButton(btnSave, "Save Changes", primary)
	Activity.AddView(btnSave, pad, y, contentW, btnH)
	y = y + btnH + spacing

	Dim btnLogout As Button
	btnLogout.Initialize("btnlogout")
	StyleButton(btnLogout, "Logout", Colors.RGB(200, 60, 60))
	Activity.AddView(btnLogout, pad, y, contentW, btnH)
End Sub

Sub AddBackButton(x As Int, y As Int, color As Int)
	Dim btn As Button
	btn.Initialize("btnback")
	btn.Text = Chr(0x2190)
	btn.TextSize = 24
	btn.TextColor = color
	btn.Color = Colors.White
	Activity.AddView(btn, x, y, 44dip, 44dip)
End Sub

Sub AddTitle(text As String, x As Int, y As Int, w As Int, color As Int)
	Dim lbl As Label
	lbl.Initialize("")
	lbl.Text = text
	lbl.TextSize = 20
	lbl.TextColor = color
	lbl.Typeface = Typeface.DEFAULT_BOLD
	Activity.AddView(lbl, x, y, w, 44dip)
End Sub

Sub AddHeaderLabel(text As String, x As Int, y As Int, w As Int, color As Int)
	Dim lbl As Label
	lbl.Initialize("")
	lbl.Text = text
	lbl.TextSize = 14
	lbl.TextColor = color
	lbl.Typeface = Typeface.DEFAULT_BOLD
	Activity.AddView(lbl, x, y, w, 22dip)
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
	btn.TextSize = 16
	Dim cd As ColorDrawable
	cd.Initialize2(color, 12dip, 0, color)
	btn.Background = cd
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
