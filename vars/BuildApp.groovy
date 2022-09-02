def call()
{
bat label: '', script: '''
dotnet tool restore
dotnet build 
'''
}
