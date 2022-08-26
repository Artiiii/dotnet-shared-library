def call()
{
  bat label: '', script: """
  set ARM_CLIENT_ID=$azcon_CLIENT_ID
  set ARM_CLIENT_SECRET=$azcon_CLIENT_SECRET
  set ARM_SUBSCRIPTION_ID=$azcon_SUBSCRIPTION_ID
  set ARM_TENANT_ID=$azcon_TENANT_ID
  
  #terraform -chdir=azstorage init -input=false
  #terraform -chdir=azstorage plan -out=tfplanaz -input=false
  #terraform -chdir=azstorage apply -input=false tfplanaz 
  #
  #az login --service-principal -u $azcon_CLIENT_ID -p $azcon_CLIENT_SECRET -t $azcon_TENANT_ID
  #(Get-AzStorageAccountKey -ResourceGroupName  "ranjith" -Name  "dotnettfstate")[0].value > acoountkey.txt
  #set ARM_ACCESS_KEY= Get-content acoountkey.txt
  
  terraform -chdir=infra init -input=false
  terraform -chdir=infra plan -out=tfplan -input=false
  terraform -chdir=infra apply -input=false tfplan   
  """
}
