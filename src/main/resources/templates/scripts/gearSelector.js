//SC = 70/71
//OC = 72/85
//OO = 88
//NC = 90

const rightGear = [ 
//index 0, st DMG, nd CHANCE, rd ATTK
SC = [ necklace =["55","45","50"], ring = "50", boots = ["35","50"] ],
//index 1
OC = [ necklace =["65","55","60"], ring = "60", boots = ["40","60"] ],
//index 2
OO = [ necklace =["70","60","65"], ring = "65", boots = ["45","65"] ],
//index 3
NC = [ necklace =["70","60","65"], ring = "65", boots = ["45","65"] ]
];

//spd del 88
//console.log(rightGear[2][2][0]);
console.log(rightGear[0][2][1]);

const leftGear = [ 
//index 0
SC = [ weapon = "440",helmet = "2360",chest = "260" ],
//index 1
OC = [ weapon = "500",helmet = "2700", chest = "300" ],
//index 2
OO = [ weapon = "515",helmet = "2765", chest = "310" ],
//index 3
NC = [ weapon = "525",helmet = "2835", chest = "310" ]
];
	
	// nivel - tipo - especifico(spd ej, cd)
	// botas spd del 88
	// 2 - 2 - 0
	// var nivel = str.substring(0,1);
	// var nivel = str.substring(2,4);
	// var nombre = str.substring(5);
	// document.getElementById("demo").innerHTML = str;
	// document.getElementById("demo1").innerHTML = tipo;
	// document.getElementById("demo2").innerHTML = nivel;
	// document.getElementById("demo3").innerHTML = nombre;

function displayRightGear(id,valor) {

	//Cortado de string
	var str = valor;
	var nivel = str.substring(0,1);
	var tipo = str.substring(2,3);
	var especifico = str.substring(4,5);
	//good crop

	console.log(valor,nivel+tipo+especifico)

	if (id == "necklaceLv"){
		document.getElementById("necklace").src = "../utils/testGear"+valor+".png"

		var base = Number(document.getElementById("baseCr").innerHTML);
		var calculed = base+Number(rightGear[nivel][tipo][especifico]);
		document.getElementById("cr").innerHTML = (calculed);

	}else if (id == "ringLv"){
		document.getElementById("ring").src = "../utils/testGear"+valor+".png"

		var base = Number(document.getElementById("atk").innerHTML);
		var calculed = (base*Number(rightGear[nivel][tipo][especifico]))/10;

		document.getElementById("atk").innerHTML = (base+calculed);

	}else if (id == "bootsLv"){
		document.getElementById("boots").src = "../utils/testGear"+valor+".png"

		var base = Number(document.getElementById("baseAtk").innerHTML);
		console.log(baseAtk)
		console.log(base)

		var calculed = (base*Number(rightGear[nivel][tipo][especifico]))/10;
console.log(calculed)
		var base1 = Number(document.getElementById("atk").innerHTML);
console.log(base1)
		document.getElementById("atk").innerHTML = (base1+calculed);
	}

}

function displayLeftGear(id,valor) {
	
console.log(id,valor)
if (id == "weaponLv"){
		var base = Number(document.getElementById("baseAtk").innerHTML);
		var calculed = base*(0.6);
		document.getElementById("atk").innerHTML =(base + calculed);

		document.getElementById("weapon").src = "../utils/testGear"+valor+".png"
		console.log(document.getElementById("weapon").src);
		return "hola";
	}else if (id == "helmetLv"){
		document.getElementById("helmet").src = "../utils/testGear"+valor+".png"
		console.log(document.getElementById("helmet").src);
	}else if (id == "chestLv"){
		document.getElementById("chest").src = "../utils/testGear"+valor+".png"
		console.log(document.getElementById("chest").src);
	}
}


function calcRight(valor,gear){
	var actual = 1;
	var base = 1;
	var calculated = 1;

	if (valor == "70"){

	}else if (valor == "85"){

	}else if (valor == "88"){

	}if (valor == "90"){

	}	
	

}

function calcLeft(valor,gear){
	var actual = 1;
	var base = 1;
	var calculated =1 ;

	if (valor == "70"){

	}else if (valor == "85"){

	}else if (valor == "88"){

	}if (valor == "90"){

	}	
	

}