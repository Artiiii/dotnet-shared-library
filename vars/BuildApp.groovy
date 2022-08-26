def call()
{
bat label: '', script: '''
C:\\Program Files\\dotnet\\dotnet.exe new tool-manifest
C:\\Program Files\\dotnet\\dotnet.exe tool install dotnet-ef
C:\\Program Files\\dotnet\\dotnet.exe tool restore
C:\\Program Files\\dotnet\\dotnet.exe ef migrations add azsql --project DotNetCoreSqlDb/DotNetCoreSqlDb.csproj
C:\\Program Files\\dotnet\\dotnet.exe ef database update --project DotNetCoreSqlDb/DotNetCoreSqlDb.csproj
C:\\Program Files\\dotnet\\dotnet.exe build 
'''
}
