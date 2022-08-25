def call(login)
{
powershell label: '', script: """
dotnet new tool-manifest
dotnet tool install dotnet-sonarscanner
dotnet tool restore
dotnet sonarscanner begin /k:'dotnetcoresqlsampleapp' /d:sonar.host.url='http://localhost:9000' /d:sonar.login=${login}
dotnet build
dotnet sonarscanner end /d:sonar.login=${login}
"""
}
