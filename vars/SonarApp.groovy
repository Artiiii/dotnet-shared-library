def call(login)
{
bat label: '', script: """
'%USERPROFILE%/.dotnet/tools/dotnet-sonarscanner.exe' begin /k:"dotnetcoresqlsampleapp" /d:sonar.host.url="http://localhost:9000" /d:sonar.login=${login}
dotnet build
dotnet sonarscanner end /d:sonar.login=${login}
"""
}
