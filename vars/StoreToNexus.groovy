def call()
{
  bat label: '', script: '''
  set PATH=%PATH%;C:/Program Files/dotnet/
  mkdir PublishFolder
  dotnet publish -o PublishFolder
  '''
 
  powershell label: '', script: '''
  Compress-Archive -Path PublishFolder/* -DestinationPath app.zip
  $version = Get-Content version.txt
  $publishUrl='http://localhost:8081/repository/dotnet-build-artifacts/dotnetcore/sample/'+$version+'/app.zip'
  $username = 'admin'
  $password = 'admin'
  $packageName = 'app.zip'
  $params = @{
  UseBasicParsing = ${true}
  Uri             = $publishUrl
  Method          = 'PUT'
  InFile          = $packageName
  Headers         = @{
    ContentType   = 'application/zip'
    Authorization = 'Basic $([System.Convert]::ToBase64String([System.Text.Encoding]::ASCII.GetBytes("admin:admin")))' 
  }
  Verbose         = ${true}
  }
  Invoke-WebRequest @params
  '''
}
