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
	Private btnLogout As Button
	Private lbluserbudget As Label
	Private btnaccount As Button
	Private btnExportDB As Button
	Private lblDBPath As Label
End Sub

Sub Activity_Create(FirstTime As Boolean)
	sql = Main.sql
	Activity.LoadLayout("dashboardlayout")

	' Add Export DB button programmatically (no need to edit layout in Designer).
	btnExportDB.Initialize("btnExportDB")
	btnExportDB.Text = "Export Database"
	btnExportDB.TextSize = 14
	Activity.AddView(btnExportDB, 16dip, 100%y - 110dip, 100%x - 32dip, 44dip)

	lblDBPath.Initialize("")
	lblDBPath.TextSize = 11
	lblDBPath.Text = "DB: " & File.Combine(File.DirInternal, "saddbb.db")
	Activity.AddView(lblDBPath, 16dip, 100%y - 60dip, 100%x - 32dip, 50dip)
End Sub

Sub Activity_Resume
	UpdateHeader
End Sub

Sub UpdateHeader
	Dim allowance As Double = 0
	Dim spent As Double = 0

	Dim c As Cursor
	c = sql.ExecQuery2( _
		"SELECT IFNULL(SUM(amount),0) FROM tblallowance WHERE username=?", _
		Array As String(Main.usernamee))
	c.Position = 0
	allowance = c.GetDouble2(0)
	c.Close

	c = sql.ExecQuery2( _
		"SELECT IFNULL(SUM(amount),0) FROM tblexpenses WHERE username=?", _
		Array As String(Main.usernamee))
	c.Position = 0
	spent = c.GetDouble2(0)
	c.Close

	lbluserbudget.Text = Main.usernamee & " - Balance: " & NumberFormat(allowance - spent, 1, 2)
End Sub

Private Sub btnLogout_Click
	Main.usernamee = ""
	Main.fname = ""
	Main.lname = ""
	Main.email = ""
	Main.role = ""
	StartActivity(Main)
	Activity.Finish
End Sub

Private Sub btnaccount_Click
	StartActivity(account)
End Sub

Private Sub btnExportDB_Click
	Dim sourcePath As String = File.Combine(File.DirInternal, "saddbb.db")
	Dim sourceSize As Long = 0

	If File.Exists(File.DirInternal, "saddbb.db") Then
		sourceSize = File.Size(File.DirInternal, "saddbb.db")
	End If

	Dim attempts() As String = Array As String( _
		"/sdcard/Download", _
		"/storage/emulated/0/Download", _
		"/sdcard", _
		"/storage/emulated/0", _
		"/sdcard/windows/BstSharedFolder", _
		"/mnt/windows/BstSharedFolder")

	Dim successPath As String = ""
	Dim failures As String = ""

	For i = 0 To attempts.Length - 1
		Try
			If File.Exists(attempts(i), "") Then
				File.Copy(File.DirInternal, "saddbb.db", attempts(i), "saddbb_export.db")
				If successPath = "" Then successPath = attempts(i) & "/saddbb_export.db"
				Log("Exported to: " & attempts(i))
			Else
				failures = failures & attempts(i) & " (not found)" & CRLF
			End If
		Catch
			failures = failures & attempts(i) & " (error: " & LastException.Message & ")" & CRLF
		End Try
	Next

	Dim msg As String
	msg = "Source DB path:" & CRLF & sourcePath & CRLF & _
		"Size: " & sourceSize & " bytes" & CRLF & CRLF

	If successPath <> "" Then
		msg = msg & "Exported successfully to:" & CRLF & successPath & CRLF & CRLF & _
			"To get this file on Windows:" & CRLF & _
			"1. Open 'Files by Google' app in BlueStacks" & CRLF & _
			"2. Navigate to Downloads folder" & CRLF & _
			"3. Long-press saddbb_export.db -> Share -> save to BlueStacks shared folder"
	Else
		msg = msg & "Export FAILED to all locations:" & CRLF & failures
	End If

	Msgbox(msg, "Database Export")
End Sub
