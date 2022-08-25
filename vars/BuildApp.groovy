def call()
{
bat label: '', script: '''
dotnet new tool-manifest
dotnet tool install dotnet-ef
dotnet tool restore
dotnet ef migrartions add azsql --project DotNetCoreSqlDb/DotNetCoreSqlDb.csproj
dotnet ef database update --project DotNetCoreSqlDb/DotNetCoreSqlDb.csproj
dotnet build 
'''
}
