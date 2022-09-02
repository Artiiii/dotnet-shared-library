def call()
{
 powershell label:'', script: '''
  $myip=Get-Content output.txt
  Write-Output $myip
  $user = "dotnet"
  Write-Output $user
  $Pass=ConvertTo-SecureString -String 'Devops@123456' -AsPlainText -Force
  $Credential=New-Object System.Management.Automation.PSCredential ($user, $Pass)
  $s=New-PSSession -ComputerName $myip -Credential $Credential
  Write-Output $s
  Invoke-Command -Session $s {
     Remove-Item 'C:/inetpub/wwwroot/app.zip'
}
  Copy-Item 'app.zip' 'C:/inetpub/wwwroot/' -ToSession $s
  Invoke-Command -Session $s {
  Remove-Item 'C:/inetpub/wwwroot/dotnetcoresql/*' -Recurse 
  Expand-Archive -Path 'C:/inetpub/wwwroot/app.zip' -DestinationPath 'C:/inetpub/wwwroot/dotnetcoresql'}
'''
}
