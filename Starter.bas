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
	' STEP 1: Capture summary metadata BEFORE closing the SQL connection.
	Dim summary As String = BuildDataSummary()

	' STEP 2: Flush any pending WAL transactions into the main .db file.
	FlushWAL

	' STEP 3: CLOSE the SQL connection completely. This is the only way to
	' guarantee that all in-memory pages, OS caches, and WAL sidecar data are
	' fully merged into the main saddbb.db file on disk. Without this, the
	' export may still miss the most recent inserts.
	Try
		Main.sql.Close
		Log("SQL connection closed for export")
	Catch
		Log("SQL close failed: " & LastException.Message)
	End Try

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
				' Remove any stale sidecars at destination so we end up with a
				' single clean .db file (connection close already merged everything).
				DeleteIfExists(targets(i), outName & "-wal")
				DeleteIfExists(targets(i), outName & "-shm")
				DeleteIfExists(targets(i), outName & "-journal")

				' Copy the main DB file only.
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

	' STEP 4: Re-open the SQL connection so the app keeps working after export.
	Try
		Main.sql.Initialize(File.DirInternal, "saddbb.db", False)
		Log("SQL connection re-opened after export")
	Catch
		Log("SQL re-open failed: " & LastException.Message)
	End Try

	If showDialog Then
		Msgbox(BuildExportSummary(sourcePath, sourceSize, successPath, failures, summary), "SQLite DB Exported")
	End If
End Sub

' Force any uncommitted WAL pages to be merged into the main .db file.
' Android SQLite requires PRAGMAs that return rows to go through ExecQuery,
' not ExecNonQuery.
Sub FlushWAL
	Try
		Dim c As Cursor = Main.sql.ExecQuery("PRAGMA wal_checkpoint(TRUNCATE)")
		c.Close
		Log("WAL checkpoint TRUNCATE OK")
	Catch
		Try
			Dim c2 As Cursor = Main.sql.ExecQuery("PRAGMA wal_checkpoint(FULL)")
			c2.Close
			Log("WAL checkpoint FULL OK")
		Catch
			Log("WAL checkpoint failed: " & LastException.Message)
		End Try
	End Try

	' Also switch to journal_mode=DELETE momentarily, which forces a hard merge
	' of WAL into the main file. This guarantees the exported .db is complete.
	Try
		Dim c3 As Cursor = Main.sql.ExecQuery("PRAGMA journal_mode=DELETE")
		c3.Close
		Log("journal_mode set to DELETE (forced WAL flush)")
	Catch
		Log("journal_mode switch failed: " & LastException.Message)
	End Try
End Sub

Sub DeleteIfExists(targetDir As String, fileName As String)
	Try
		If File.Exists(targetDir, fileName) Then
			File.Delete(targetDir, fileName)
		End If
	Catch
		Log("Sidecar delete " & fileName & " failed: " & LastException.Message)
	End Try
End Sub

Sub BuildExportSummary(sourcePath As String, sourceSize As Long, successPath As String, failures As String, dataSummary As String) As String
	Dim msg As String
	msg = "SOURCE (live DB on device):" & CRLF & sourcePath & CRLF & _
		"Size: " & sourceSize & " bytes" & CRLF & CRLF

	msg = msg & "DATA IN DATABASE:" & CRLF & dataSummary & CRLF

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

' Builds the table-row-count summary while the SQL connection is still open.
Sub BuildDataSummary() As String
	Dim s As String
	s = TableSummary("tblusers") & CRLF
	s = s & TableSummary("tblallowance") & CRLF
	s = s & TableSummary("tblexpenses") & CRLF
	s = s & TableSummary("tblgoal") & CRLF
	s = s & TableSummary("tbltransac") & CRLF
	s = s & TableSummary("tblsplit")
	Return s
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
