def call(password)
{
 powershell label: '', script: '''
 $myip = Get-Content output.txt
 #$password = ${password}
 Set-Item 'WSMan:localhost/client/trustedhosts' -value "$myip" -Force
 Enable-PSRemoting -Force
 $user="$myip\\dotnet"
 $Pass=ConvertTo-SecureString -String 'Devops@123456' -AsPlainText -Force
 $Credential=New-Object System.Management.Automation.PSCredential ($user, $Pass)
 
 Write-Output "####### PS SESSION TO REMOTE #########"
 
 Write-Output "#------ INSTALLING DOTNET CORE HOSTING BUNDLE------#"
 
 $s=New-PSSession -ComputerName $myip -Credential $Credential   
 Copy-Item 'dotnet-hosting-6.0.8-win.exe' 'C:/inetpub/wwwroot' -ToSession $s
 Invoke-Command -Session $s {
 $pathvargs = { & 'C:/inetpub/wwwroot/dotnet-hosting-6.0.8-win.exe' /S /v/qn }
 
 if (Test-Path "C:/inetpub/wwwroot/dotnet-hosting-6.0.8-win.exe" ) {
     Write-Host "Dotnet Hosting Bundle Already Installed"
 }
 else{
 Invoke-Command -ScriptBlock $pathvargs
 }
 
 Write-Output "#------- CREATE IIS WEBSITE OR STOP WEBSITE iF ALREADY EXISTS------------------#"

 $Exists=Get-WebSite -Name 'dotnetcore'
 Write-Output $Exists
 if(!$Exists)
 {
 if (Test-Path "C:/inetpub/wwwroot/dotnetcoresql" ) {
 
     Write-Host "Folder Exists"
     Remove-Item "C:/inetpub/wwwroot/dotnetcoresql" -Force
 }
 New-Item "C:/inetpub/wwwroot/dotnetcoresql" -ItemType Directory
 New-WebSite -Name "dotnetcore" -Port "98" -PhysicalPath "C:/inetpub/wwwroot/dotnetcoresql"
 }
 else
 {
 Stop-WebSite "dotnetcore"
 Write-Output "Website Stopped"
 }
 }
 '''
}
