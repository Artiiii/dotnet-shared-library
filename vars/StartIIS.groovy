def call()
{
 powershell label:'', script: '''
 $myip = Get-Content output.txt
 Set-Item 'WSMan:localhost/client/trustedhosts' -value "$myip" -Force
 Enable-PSRemoting -Force
 $user="$myip\\dotnet"
 $Pass=ConvertTo-SecureString -String 'Devops@123456' -AsPlainText -Force
 $Credential=New-Object System.Management.Automation.PSCredential ($user, $Pass) 
 $s=New-PSSession -ComputerName $myip -Credential $Credential   
 Invoke-Command -Session $s {Start-Website "dotnetcore"}
 '''
}
