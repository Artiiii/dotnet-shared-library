def call()
{

powershell label:'', script:'''
$version = Get-Content version.txt
$publishUrl='http://localhost:8081/repository/dotnet-build-artifacts/dotnetcore/sample/'+$version+'/app.zip'
Invoke-WebRequest -Method GET $publishUrl  -ContentType "application/json" -OutFile app.zip
'''
}
