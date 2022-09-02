def call()
{
bat label: '', script: '''
az login --service-principal -u $azcon_CLIENT_ID -p $azcon_CLIENT_SECRET -t $azcon_TENANT_ID 
az vm list-ip-address --resource-group ranjith --name dotnetcoresamplevm > fetchpublicip.json
'''
powershell label: '', script: '''
(Get-Content fetchpublicip.json | ConvertFrom-Json).virtualMachine.network.publicIpAddresses.ipAddress > output.txt
Get-Content output.txt
'''
}
