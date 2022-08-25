def call()
{
  powershell label: '', script: '''
 Set-Item 'WSMan:localhost/client/trustedhosts' -value '40.114.48.109' -Force
 Enable-PSRemoting -Force
 $Pass=ConvertTo-SecureString -String 'Dotnet@123456' -AsPlainText -Force
 $Credential=New-Object System.Management.Automation.PSCredential ("40.114.48.109\dotnet", $Pass)
 $s=New-PSSession -ComputerName '40.114.48.109' -Credential $Credential      
 Invoke-Command -Session $s {
 Stop-Website "dotnetcore" 
 }  
 '''
}
