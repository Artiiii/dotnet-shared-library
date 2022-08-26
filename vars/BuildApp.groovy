def call()
{
bat label: '', script: '''
set PATH=%PATH%;C:/Program Files/dotnet/
dotnet new tool-manifest
dotnet tool install dotnet-ef
dotnet tool restore
dotnet ef migrations add azsql --project DotNetCoreSqlDb/DotNetCoreSqlDb.csproj
dotnet ef database update --project DotNetCoreSqlDb/DotNetCoreSqlDb.csproj
dotnet build 
'''
}
