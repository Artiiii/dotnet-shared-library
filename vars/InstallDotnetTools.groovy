def call()
{
bat label: '', script: """
dotnet tool install JetBrains.dotCover.GlobalTool
dotnet tool install dotnet-sonarscanner
"""
}
