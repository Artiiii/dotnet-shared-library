def call(login)
{
bat label: '', script: """
dotnet sonarscanner begin /k:"dotnetcoresqlsampleapp" /d:sonar.host.url="http://localhost:9000" /d:sonar.login=${login}
dotnet build login}
dotnet sonarscanner end /d:sonar.login=${login}
"""
}
