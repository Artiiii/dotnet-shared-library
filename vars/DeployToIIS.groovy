def call(user, passwd)
{
remote = [:]
remote.name = 'azurevdotnetvm'
remote.user = user
remote.password = passwd
remote.allowAnyHosts = true
remote.host = ""
sshPut remote: remote, from: 'app.zip', into: 'C:/intepub/wwwroot/'
}
