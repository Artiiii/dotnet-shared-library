def call()
{
powershell label:'', script: """
Set-Item 'WSMan:localhost/client/trustedhosts' -value '40.114.48.109'
Enable-PSRemoting -Force
$User='dotnet'
$Pass=ConvertTo-SecureString -String 'Devops@123456' -AsPlainText -Force
$Credential=New-Object -TypeName System.Management.Automation.PSCredential -ArgumentList $User, $Pass
$s=New-PSSession -ComputerName '40.114.48.109' -Credential $Credential
Copy-Item 'app.zip' 'C:/inetpub/wwwroot' -ToSession $s
"""
}
