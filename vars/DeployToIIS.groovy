def call()
{
 powershell label:'', script: '''
  $myip=Get-Content output.txt
  Write-Output $myip
  Set-Item 'WSMan:localhost/client/trustedhosts' -value "$myip" -Force
  $user = "$myip\\dotnet"
  Write-Output $user
  Enable-PSRemoting -Force
  $Pass=ConvertTo-SecureString -String 'Devops@123456' -AsPlainText -Force
  $Credential=New-Object System.Management.Automation.PSCredential ($user, $Pass)
  $s=New-PSSession -ComputerName $myip -Credential $Credential
  Write-Output $s
  Invoke-Command -Session $s {Remove-Item 'C:/inetpub/wwwroot/app.zip'}
  Copy-Item 'app.zip' 'C:/inetpub/wwwroot/' -ToSession $s
  Invoke-Command -Session $s {
  Remove-Item 'C:/inetpub/wwwroot/dotnetcore/*' -Recurse
  Expand-Archive -Path 'C:/inetpub/wwwroot/app.zip' -DestinationPath 'C:/inetpub/wwwroot/dotnetcore'}
'''
}
