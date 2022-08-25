def call()
{
powershell  label: '', script: '''
Invoke-WebRequest -Method Get 'http://localhost:8081/repository/dotnet-build-artifacts/dotnetcore/sample/${build}/app.zip' -OutFile 'app.zip'
Get-Item .
#Copy-Item "app.zip" "C:/Users/Arti Pal/Documents/tools/apache-tomcat-10.0.23-windows-x64/apache-tomcat-10.0.23/webapps" -Force -Verbose'
}
