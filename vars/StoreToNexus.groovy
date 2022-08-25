def call(username, password, build)
{
  powershell label: '', script: '''
  New-Item "PublishFolder" -itemType Directory
  dotnet publish -o PublishFolder
  Compress-Archive -Path PublishFolder/* -DestinationPath app.zip
  
  $publishUrl='http://localhost:8081/repository/dotnet-build-artifacts/dotnetcore/sample/${build}/app.zip'
  $packageName = 'PublishFolder/app.zip'
  $username= ${username}
  $password= ${password}
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
  Remove-Item "PublishFolder"
  '''
}
