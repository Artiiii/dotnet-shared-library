def call()
{
 powershell label:'', script: '''
  Set-Item 'WSMan:localhost/client/trustedhosts' -value '40.114.48.109' -Force
  Enable-PSRemoting -Force
  #$User='dotnet'
  $Pass=ConvertTo-SecureString -String 'Devops@123456' -AsPlainText -Force
  $Credential=New-Object System.Management.Automation.PSCredential ("40.114.48.109\\dotnet", $Pass)
  $s=New-PSSession -ComputerName '40.114.48.109' -Credential $Credential
  Write-Output $s
  Copy-Item 'app.zip' 'C:/inetpub/wwwroot' -ToSession $s
'''
}
