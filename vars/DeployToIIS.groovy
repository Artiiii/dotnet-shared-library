def call(user, passwd)
{
remote = [:]
remote.name = 'azurevdotnetvm'
remote.user = user
remote.password = passwd
remote.allowAnyHosts = true
remote.host = "40.114.48.109"
sshCommand remote: remote, command: "ls C:/inetpub"
//sshPut remote: remote, from: 'app.zip', into: 'C:/inetpub/wwwroot/'
}
