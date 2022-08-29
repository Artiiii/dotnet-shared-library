def call()
{
bat label: '', script: '''
set PATH=%PATH%;C:/Program Files (x86)/Microsoft SDKs/Azure/CLI2/wbin
az login --service-principal -u $azcon_CLIENT_ID -p $azcon_CLIENT_SECRET -t $azcon_TENANT_ID
az vm list-ip-addresses --resource-group ranjith --name dotnetcoresamplevm > fetchpublicip.json
'''
powershell label: '', script: """  
(Get-Content fetchpublicip.json | ConvertFrom-Json).virtualMachine.network.publicIpAddresses.ipAddress > output.txt
Get-Content output.txt
"""
}
