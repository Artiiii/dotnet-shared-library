def call()
{
  powershell label:'', script: '''
  Set-Item 'WSMan:localhost/client/trustedhosts' -value '40.114.48.109' -Force
  Enable-PSRemoting -Force
  $Pass=ConvertTo-SecureString -String 'Devops@123456' -AsPlainText -Force
  $Credential=New-Object System.Management.Automation.PSCredential ("104.41.133.141\\dotnet", $Pass)
  $s=New-PSSession -ComputerName '104.41.133.141' -Credential $Credential
  Write-Output $s
  Invoke-Command -Session $s {Start-Website "dotnetcore"}
  '''
}
