def call(password)
{
 powershell: '', script """
 $ip = Get-Content output.txt
 $password = ${password}
 Set-Item 'WSMan:localhost/client/trustedhosts' -value $ip -Force
 Enable-PSRemoting -Force
 $Pass=ConvertTo-SecureString -String $password -AsPlainText -Force
 $Credential=New-Object System.Management.Automation.PSCredential ("$ip\\dotnet", $Pass)
 $s=New-PSSession -ComputerName $ip -Credential $Credential      
 Invoke-Command -Session $s {
 Copy-Item 'dotnet-hosting-6.0.8-win.exe' 'Downloads/' -ToSession $s
 $pathvargs = {Downloads/dotnet-hosting-6.0.8-win.exe /S /v/qn }
 Invoke-Command -ScriptBlock $pathvargs
 """ 
}
}
