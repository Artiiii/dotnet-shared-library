def call()
{

powershell label:'', script:'''
$version = Get-Content version.txt
$publishUrl='http://localhost:8081/repository/dotnet-build-artifacts/dotnetcore/sample/'+$version+'/app.zip'
Invoke-WebRequest -Method GET $publishUrl  -ContentType "application/json" -OutFile app.zip
Write-Output "Downloading Dotnet bundle"
Invoke-WebRequest -Method GET 'http://localhost:8081/repository/dotnet-build-artifacts/tools/dotnet-hosting-6.0.8-win.exe  -ContentType "application/json" -OutFile dotnet-hosting-6.0.8-win.exe
Write-Output "Downloaded Dotnet bundle"
'''
}
