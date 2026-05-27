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
	MakeResponsive
End Sub

Sub MakeResponsive
	Activity.Color = Colors.White
	TagGlobals
	HideExtras

	Dim w As Int = 100%x
	Dim pad As Int = 24dip
	Dim spacing As Int = 8dip
	Dim labelH As Int = 22dip
	Dim ctrlH As Int = 44dip
	Dim btnH As Int = 48dip
	Dim contentW As Int = w - pad * 2
	Dim primary As Int = Colors.RGB(0, 150, 136)
	Dim cardBG As Int = Colors.RGB(235, 245, 245)

	Dim cardW As Int = Min(contentW, 360dip)
	Dim cardX As Int = (w - cardW) / 2

	Dim y As Int = pad

	AddBackButton(pad, y, primary)
	AddBrandTitle("Sign Up", cardX, y, cardW, primary)
	y = y + 50dip + spacing * 2

	AddHeaderLabel("Username", cardX, y, cardW, primary)
	y = y + labelH + 4dip
	StyleInputEditText(txtUser, cardX, y, cardW, ctrlH, "", cardBG)
	y = y + ctrlH + spacing

	AddHeaderLabel("Password", cardX, y, cardW, primary)
	y = y + labelH + 4dip
	StyleInputEditText(txtPass, cardX, y, cardW, ctrlH, "", cardBG)
	txtPass.PasswordMode = True
	y = y + ctrlH + spacing

	AddHeaderLabel("Email", cardX, y, cardW, primary)
	y = y + labelH + 4dip
	StyleInputEditText(txtemail, cardX, y, cardW, ctrlH, "", cardBG)
	y = y + ctrlH + spacing

	AddHeaderLabel("First Name", cardX, y, cardW, primary)
	y = y + labelH + 4dip
	StyleInputEditText(txtfname, cardX, y, cardW, ctrlH, "", cardBG)
	y = y + ctrlH + spacing

	AddHeaderLabel("Last Name", cardX, y, cardW, primary)
	y = y + labelH + 4dip
	StyleInputEditText(txtlname, cardX, y, cardW, ctrlH, "", cardBG)
	y = y + ctrlH + spacing * 2

	btnCreate.Left = cardX : btnCreate.Top = y : btnCreate.Width = cardW : btnCreate.Height = btnH
	StyleButton(btnCreate, "Create Account", primary)
End Sub

Sub Min(a As Int, b As Int) As Int
	If a < b Then Return a
	Return b
End Sub

Sub TagGlobals
	txtUser.Tag = "g"
	txtPass.Tag = "g"
	btnCreate.Tag = "g"
	txtemail.Tag = "g"
	txtfname.Tag = "g"
	txtlname.Tag = "g"
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
