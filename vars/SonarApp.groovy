def call(login)
{
bat label: '', script: """
dotnet sonarscanner begin /k:"dotnetcoresqlsampleapp" /d:sonar.host.url="http://localhost:9000" /d:sonar.login=${login} /d:sonar.cs.vscoveragexml.reportsPaths=coverage.xml
dotnet build --no-incremental
dotnet dotcover test --dcReportType=HTML
dotnet sonarscanner end /d:sonar.login=${login}
"""
}
