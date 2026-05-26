B4A=true
Group=Default Group
ModulesStructureVersion=1
Type=Service
Version=9.9
@EndOfDesignText@
#Region  Service Attributes
	#StartAtBoot: False
	#ExcludeFromLibrary: True
#End Region

Sub Process_Globals
	'These global variables will be declared once when the application starts.
	'These variables can be accessed from all modules.

End Sub

Sub Service_Create
	'This is the program entry point.
	'This is a good place to load resources that are not specific to a single activity.

End Sub

Sub Service_Start (StartingIntent As Intent)
	Service.StopAutomaticForeground 'Starter service can start in the foreground state in some edge cases.
End Sub

Sub Service_TaskRemoved
	'This event will be raised when the user removes the app from the recent apps list.
End Sub

'Return true to allow the OS default exceptions handler to handle the uncaught exception.
Sub Application_Error (Error As Exception, StackTrace As String) As Boolean
	Return True
End Sub

Sub Service_Destroy

End Sub

' Exports the COMPLETE live SQLite database file (all tables, all rows) to shared storage.
' Callable from any activity via: Starter.ExportDBAndShowDialog(True)
Public Sub ExportDBAndShowDialog(showDialog As Boolean)
	Dim sourcePath As String = File.Combine(File.DirInternal, "saddbb.db")
	Dim sourceSize As Long = 0
	If File.Exists(File.DirInternal, "saddbb.db") Then
		sourceSize = File.Size(File.DirInternal, "saddbb.db")
	End If

	Dim outName As String = "watchurwallet.db"

	Dim targets() As String = Array As String( _
		"/sdcard/Download", _
		"/storage/emulated/0/Download", _
		"/sdcard", _
		"/storage/emulated/0", _
		"/sdcard/windows/BstSharedFolder", _
		"/mnt/windows/BstSharedFolder")

	Dim successPath As String = ""
	Dim failures As String = ""

	For i = 0 To targets.Length - 1
		Try
			If File.Exists(targets(i), "") Then
				File.Copy(File.DirInternal, "saddbb.db", targets(i), outName)
				If successPath = "" Then successPath = targets(i) & "/" & outName
				Log("DB exported to " & targets(i) & "/" & outName)
			Else
				failures = failures & targets(i) & " (not found)" & CRLF
			End If
		Catch
			failures = failures & targets(i) & " (error: " & LastException.Message & ")" & CRLF
		End Try
	Next

	If showDialog Then
		Msgbox(BuildExportSummary(sourcePath, sourceSize, successPath, failures), "SQLite DB Exported")
	End If
End Sub

Sub BuildExportSummary(sourcePath As String, sourceSize As Long, successPath As String, failures As String) As String
	Dim msg As String
	msg = "SOURCE (live DB on device):" & CRLF & sourcePath & CRLF & _
		"Size: " & sourceSize & " bytes" & CRLF & CRLF

	msg = msg & "DATA IN DATABASE:" & CRLF
	msg = msg & TableSummary("tblusers") & CRLF
	msg = msg & TableSummary("tblallowance") & CRLF
	msg = msg & TableSummary("tblexpenses") & CRLF
	msg = msg & TableSummary("tblgoal") & CRLF
	msg = msg & TableSummary("tbltransac") & CRLF
	msg = msg & TableSummary("tblsplit") & CRLF & CRLF

	If successPath <> "" Then
		msg = msg & "EXPORTED TO:" & CRLF & successPath & CRLF & CRLF & _
			"How to access in Windows:" & CRLF & _
			"1. Install 'Files by Google' from Play Store (if not yet)" & CRLF & _
			"2. Open it -> Downloads folder" & CRLF & _
			"3. Find 'watchurwallet.db' -> Share -> save to PC" & CRLF & _
			"4. Open .db in DB Browser for SQLite (sqlitebrowser.org)"
	Else
		msg = msg & "EXPORT FAILED in all locations:" & CRLF & failures
	End If
	Return msg
End Sub

Sub TableSummary(tableName As String) As String
	Try
		Dim c As Cursor
		c = Main.sql.ExecQuery("SELECT COUNT(*) FROM " & tableName)
		c.Position = 0
		Dim n As Int = c.GetInt2(0)
		c.Close
		Return "  " & tableName & ": " & n & " rows"
	Catch
		Return "  " & tableName & ": (missing table)"
	End Try
End Sub
