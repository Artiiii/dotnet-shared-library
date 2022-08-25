def call(username, password)
{
 powershell label:'', script: '''
  Set-Item 'WSMan:localhost/client/trustedhosts' -value '40.114.48.109' -Force
  Enable-PSRemoting -Force
  $User='${username}'
  $Pass=ConvertTo-SecureString -String '${password}' -AsPlainText -Force
  $Credential=New-Object System.Management.Automation.PSCredential ("40.114.48.109\$User", $Pass)
  $s=New-PSSession -ComputerName '40.114.48.109' -Credential $Credential
  Write-Output $s
  Copy-Item 'app.zip' 'C:/inetpub/wwwroot' -ToSession $s
'''
}
