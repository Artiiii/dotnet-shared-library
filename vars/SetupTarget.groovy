def call(password)
{
 powershell: '', script """
 $ip = Get-Content output.txt
 $password = ${password}
 Set-Item 'WSMan:localhost/client/trustedhosts' -value $ip -Force
 Enable-PSRemoting -Force
 $Pass=ConvertTo-SecureString -String $password -AsPlainText -Force
 $Credential=New-Object System.Management.Automation.PSCredential ("$ip\\dotnet", $Pass)
 
 Write-Output "####### PS SESSION TO REMOTE #########"
 
 Write-Output "#------ INSTALLING DOTNET CORE HOSTING BUNDLE------#"
 
 $s=New-PSSession -ComputerName $ip -Credential $Credential      
 Invoke-Command -Session $s {
 Copy-Item 'dotnet-hosting-6.0.8-win.exe' 'Downloads/' -ToSession $s
 $pathvargs = {Downloads/dotnet-hosting-6.0.8-win.exe /S /v/qn }
 Invoke-Command -ScriptBlock $pathvargs
 
 Write-Output "#------- CREATE IIS WEBSITE OR STOP WEBSITE iF ALREADY EXISTS------------------#"
 
 $Exists=Get-WebSite -Name 'dotnetcore'
 Write-Output $Exists
 if(!$Exists)
 {
 if (Test-Path "C:\inetpub\wwwroot\dotnetcoresql" ) {
 
     Write-Host "Folder Exists"
     Remove-Item "C:\inetpub\wwwroot\dotnetcoresql" -Force
 }
 New-Item "C:\inetpub\wwwroot\dotnetcoresql" -ItemType Directory
 New-WebSite -Name "dotnetcore" -Port "98" -PhysicalPath "C:\inetpub\wwwroot\dotnetcoresql"
 }
 else
 {
 Stop-WebSite "dotnetcore"
 Write-Output "Website Stopped"
 }
 }
 """ 
}