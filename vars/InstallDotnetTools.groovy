def call()
{
bat label: '', script: """
dotnet tool install --global JetBrains.dotCover.GlobalTool
dotnet tool install --global dotnet-sonarscanner
"""
}
