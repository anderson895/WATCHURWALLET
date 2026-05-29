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
	Private txtemail As EditText
	Private txtfname As EditText
	Private txtlname As EditText
End Sub

Sub Activity_Create(FirstTime As Boolean)
	If FirstTime Then
		sql.Initialize(File.DirInternal, "saddbb.db", False)
	End If
	BuildScreen
End Sub

Sub BuildScreen
	Activity.RemoveAllViews
	Activity.Color = Colors.White

	Dim w As Int = 100%x
	Dim pad As Int = 24dip
	Dim spacing As Int = 8dip
	Dim labelH As Int = 22dip
	Dim ctrlH As Int = 44dip
	Dim btnH As Int = 48dip
	Dim contentW As Int = w - pad * 2
	Dim primary As Int = Colors.RGB(0, 150, 136)
	Dim cardBG As Int = Colors.RGB(235, 245, 245)

	Dim cardW As Int = MinInt(contentW, 360dip)
	Dim cardX As Int = (w - cardW) / 2

	Dim y As Int = pad

	AddBackButton(pad, y, primary)
	AddBrandTitle("Sign Up", cardX, y, cardW, primary)
	y = y + 50dip + spacing * 2

	AddHeaderLabel("Username", cardX, y, cardW, primary)
	y = y + labelH + 4dip
	txtUser.Initialize("txtUser")
	StyleInputEditText(txtUser, "", cardBG)
	Activity.AddView(txtUser, cardX, y, cardW, ctrlH)
	y = y + ctrlH + spacing

	AddHeaderLabel("Password", cardX, y, cardW, primary)
	y = y + labelH + 4dip
	txtPass.Initialize("txtPass")
	StyleInputEditText(txtPass, "", cardBG)
	txtPass.PasswordMode = True
	Activity.AddView(txtPass, cardX, y, cardW, ctrlH)
	y = y + ctrlH + spacing

	AddHeaderLabel("Email", cardX, y, cardW, primary)
	y = y + labelH + 4dip
	txtemail.Initialize("txtemail")
	StyleInputEditText(txtemail, "", cardBG)
	Activity.AddView(txtemail, cardX, y, cardW, ctrlH)
	y = y + ctrlH + spacing

	AddHeaderLabel("First Name", cardX, y, cardW, primary)
	y = y + labelH + 4dip
	txtfname.Initialize("txtfname")
	StyleInputEditText(txtfname, "", cardBG)
	Activity.AddView(txtfname, cardX, y, cardW, ctrlH)
	y = y + ctrlH + spacing

	AddHeaderLabel("Last Name", cardX, y, cardW, primary)
	y = y + labelH + 4dip
	txtlname.Initialize("txtlname")
	StyleInputEditText(txtlname, "", cardBG)
	Activity.AddView(txtlname, cardX, y, cardW, ctrlH)
	y = y + ctrlH + spacing * 2

	Dim btnCreate As Button
	btnCreate.Initialize("btnCreate")
	StyleButton(btnCreate, "Create Account", primary)
	Activity.AddView(btnCreate, cardX, y, cardW, btnH)
End Sub

Sub MinInt(a As Int, b As Int) As Int
	If a < b Then Return a
	Return b
End Sub

Sub AddBackButton(x As Int, y As Int, color As Int)
	Dim btn As Button
	btn.Initialize("btnBack")
	btn.Text = Chr(0x2190)
	btn.TextSize = 24
	btn.TextColor = color
	btn.Color = Colors.White
	Activity.AddView(btn, x, y, 44dip, 44dip)
End Sub

Sub AddBrandTitle(text As String, x As Int, y As Int, w As Int, color As Int)
	Dim lbl As Label
	lbl.Initialize("")
	lbl.Text = text
	lbl.TextSize = 24
	lbl.TextColor = color
	lbl.Typeface = Typeface.DEFAULT_BOLD
	lbl.Gravity = Gravity.CENTER
	Activity.AddView(lbl, x, y, w, 50dip)
End Sub

Sub AddHeaderLabel(text As String, x As Int, y As Int, w As Int, color As Int)
	Dim lbl As Label
	lbl.Initialize("")
	lbl.Text = text
	lbl.TextSize = 13
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
