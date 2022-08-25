def call()
{
bat label: '', script: '''
dotnet new tool-manifest
dotnet tool install dotnet-ef
dotnet tool restore
dotnet ef migrartions add azsql
dotnet ef database update
dotnet build 
'''
}
