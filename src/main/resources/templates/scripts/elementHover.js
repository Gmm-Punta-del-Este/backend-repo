function changeContent() {
		//obtengo el class value del bicho
		var heroActualClass = document.getElementById('classSama');
		console.log(1111);
		console.log(heroActualClass.value);
		console.log(2222);
        var MyDesc = document.getElementById('classSama');
        //print console
        console.log('<img src='+MyDesc.value+'>');
        //le pongo en su lugar la imaagen.
       // document.getElementById('hero-class').innerHTML = '<img src='+MyDesc.value+'>';
    }