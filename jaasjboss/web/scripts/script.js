function confirmarExclusao(){
	
	if(confirm('Atenção! \n Esta operação não poderá ser desfeita! \n Confirma a exclusão do registro?')){
		return true;
	}
	else{
		return false;
	}
}