def call()
{
powershell label: '', script: '''
dotnet new tool-manifest
dotnet tool install dotnet-ef
dotnet tool restore
dotnet ef migrations add azsql --project DotNetCoreSqlDb/DotNetCoreSqlDb.csproj
dotnet ef database update --project DotNetCoreSqlDb/DotNetCoreSqlDb.csproj
dotnet build 
'''
}
