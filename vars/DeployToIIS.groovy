def call(user, passwd)
{
remote = [:]
remote.name = 'azurevdotnetvm'
remote.user = user
remote.password = passwd
remote.allowAnyHosts = true
remote.host = "13.82.219.124"
sshCommand remote: remote, command: "ls C:/inetpub"
//sshPut remote: remote, from: 'app.zip', into: 'C:/inetpub/wwwroot/'
}
