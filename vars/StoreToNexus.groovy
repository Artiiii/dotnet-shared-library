def call()
{
  powershell label: '', script: '''
 #set PATH=%PATH%;C:/Program Files/dotnet/
 #New-Item "PublishFolder" -itemType Directory
 #dotnet publish -o PublishFolder
 #Compress-Archive -Path PublishFolder/* -DestinationPath app.zip
  $version = Get-Content version.txt
  $publishUrl='http://localhost:8081/repository/dotnet-build-artifacts/dotnetcore/sample/'+$version+'/app.zip'
  $packageName = 'app.zip'
  $username = 'admin'
  $password = 'admin'
  $params = @{
  UseBasicParsing = $true
  Uri             = $publishUrl
  Method          = "PUT"
  InFile          = $packageName
  Headers         = @{
    ContentType   = "application/zip"
    Authorization = "Basic $([System.Convert]::ToBase64String([System.Text.Encoding]::ASCII.GetBytes("$username`:$password")))" 
  }
  Verbose         = $true
  }
  Invoke-WebRequest @params
  '''
}
