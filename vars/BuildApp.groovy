def call()
{
bat label: '', script: '''
dotnet ef migrartions add azsql
dotnet ef database update
dotnet build 
'''
}
