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
 $exists = Invoke-Command -Session $s {Test-Path 'C:/inetpub/wwwroot/dotnet-hosting-6.0.8-win.exe'}
 Write-Output $exists
 if($exists -eq "True")
 {
 Write-Output "File Exists"
 }
 else{
 Copy-Item 'dotnet-hosting-6.0.8-win.exe' 'C:/inetpub/wwwroot' -ToSession $s
 $pathvargs = { & 'C:/inetpub/wwwroot/dotnet-hosting-6.0.8-win.exe' /S /v/qn }
 Invoke-Command -ScriptBlock $pathvargs
 }

 
 
 Write-Output "#------- CREATE IIS WEBSITE OR STOP WEBSITE iF ALREADY EXISTS------------------#"
 if (Test-Path "C:/inetpub/wwwroot/dotnetcoresql" ) {
 
     Write-Host "Folder Exists"
     Remove-Item "C:/inetpub/wwwroot/dotnetcoresql" -Force
 }
 New-Item "C:/inetpub/wwwroot/dotnetcoresql" -ItemType Directory

Import-Module WebAdministration    
$iisAppPoolName = "dotnetcoresql"  
$iisAppPoolDotNetVersion = "v4.0"    
$iisWebsiteFolderPath = "C:/inetpub/wwwroot/dotnetcoresql"  
$iisWebsiteName = "dotnetcoresql"  
$iisWebsiteBindings = @(  
   @{protocol="http";bindingInformation="*:80:"}
)
if (!(Test-Path IIS:\AppPools\$iisAppPoolName -pathType container))  
{  
New-Item IIS:\AppPools\$iisAppPoolName  
Set-ItemProperty IIS:\AppPools\$iisAppPoolName -name "managedRuntimeVersion" -value $iisAppPoolDotNetVersion  
}    
if (!(Test-Path IIS:\Sites\$iisWebsiteName -pathType container))  
{  
Remove-Website "Default Web Site"
New-Item IIS:\Sites\$iisWebsiteName -bindings $iisWebsiteBindings -physicalPath $iisWebsiteFolderPath  
Set-ItemProperty IIS:\Sites\$iisWebsiteName -name applicationPool -value $iisAppPoolName  
}  
Stop-WebSite "dotnetcore"
}
 '''
}
