def call()
{
 powershell label:'', script: '''
  Set-Item 'WSMan:localhost/client/trustedhosts' -value '40.114.48.109' -Force
  Enable-PSRemoting -Force
  $Pass=ConvertTo-SecureString -String 'Devops@123456' -AsPlainText -Force
  $Credential=New-Object System.Management.Automation.PSCredential ("40.114.48.109\\dotnet", $Pass)
  $s=New-PSSession -ComputerName '40.114.48.109' -Credential $Credential
  Write-Output $s
  Invoke-Command -Session $s {Remove-Item 'C:/inetpub/wwwroot/app.zip'}
  Copy-Item 'app.zip' 'C:/inetpub/wwwroot/' -ToSession $s
  Invoke-Command -Session $s {
  Remove-Item 'C:/inetpub/wwwroot/dotnetcore/*' -Recurse
  Expand-Archive -Path 'C:/inetpub/wwwroot/app.zip' -DestinationPath 'C:/inetpub/wwwroot/dotnetcore'}
'''
}
