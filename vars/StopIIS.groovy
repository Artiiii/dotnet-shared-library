def call()
{
  powershell label: '', script: '''
 Set-Item 'WSMan:localhost/client/trustedhosts' -value '40.114.48.109' -Force
 Enable-PSRemoting -Force
 $Pass=ConvertTo-SecureString -String 'Dotnet@123456' -AsPlainText -Force
 $Credential=New-Object System.Management.Automation.PSCredential ("40.114.48.109\dotnet", $Pass)
 $s=New-PSSession -ComputerName '40.114.48.109' -Credential $Credential      
 Invoke-Command -Session $s {
 $Exists=Get-IISSite -Name 'dotnetcore'
 if($Exists -ccontains "WARNING")
 {
 New-Item "C:\inetpub\wwwroot\dotnetcoresql" -ItemType Directory
 New-IISSite -Name "dotnetcore" -BindingInformation "*:82:" -PhysicalPath "C:\inetpub\wwwroot\dotnetcoresql" -Protocol http
 }
 else
 {
 Start-Website "dotnetcore" 
 }}
 '''  
}
