def call(login)
{
bat label: '', script: """
set PATH=%PATH%;C:/Program Files/dotnet/
dotnet tool install dotnet-sonarscanner
dotnet tool restore
dotnet dotnet-sonarscanner begin /k:dotnetcoresqlsampleapp /d:sonar.host.url=http://localhost:9000 /d:sonar.login=${login}
dotnet build
dotnet sonarscanner end /d:sonar.login=${login}
"""
}
