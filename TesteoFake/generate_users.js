const faker = require('faker')
const fs = require('fs')

function generateUsers() {

    let usuarios = []
    let contactoUsuarios = []
    let inquilinos = []
    let contactoInquilinos = []
    let propietarios = []
    let contactoPropietarios = []
    let notas = []
    let users = []
    let nota = []
    let aptos = []
    let consumos = []
    //SIEMPRE VA A AGREGAR EL USUARIO LOGIN:admin pass: admin

    usuarios.push({
            "nombre": "Emiliano",
            "apellido1": "Bayona",
            "email": "emi.bayonaa@gmm.com",
            "login": "admin",
            "password": "admin",
            "documento": 45829287,
            "tipoUsuario": "admin"
    })


    for (let id=1; id <= 10; id++) {

        let nombre = faker.name.firstName()
        let apellido1 = faker.name.lastName()
        let login = "jch"+faker.random.number()
        let password = faker.random.hexaDecimal()
        let email = faker.internet.email()
        let documento = faker.random.number()
        let telefono = faker.random.number({min:100000, max:999999});
        contactoUsuarios.push({
            "nombre": nombre,
            "apellido": apellido1,
            "email": email,
            "documento": documento,
            "telefono": "093"+telefono
        })
        usuarios.push({
            "nombre": nombre,
            "apellido1": apellido1,
            "email": email,
            "login": login,
            "password": password,
            "tipoUsuario":"recepcion",
            "documento": documento
        })

        users.push({
                "login":login,
                "password":password
        })

    }
    for (let i = 0; i <= 5; i++) {
              
            //hago un objeto nota
            let titulo = "Nota de prueba "+i
            let texto = faker.hacker.phrase()
            nota.push({
                "titulo":titulo,
                "texto":texto
            })

            //le clavo al array notas el user y la nota
            
    }  
        notas.push({
                "nota":nota,
                "user":users
        }) 

    for (let id=1; id <= 200; id++) {

        let nombre = faker.name.firstName()
        let apellido1 = faker.name.lastName()
        let apellido2 = faker.name.lastName()
        let documento = faker.random.number()
        let email = faker.internet.email()
        let telefono = faker.random.number({min:100000, max:999999});
        contactoInquilinos.push({
            "nombre": nombre,
            "apellido": apellido1,
            "email": email,
            "documento": documento,
            "telefono": "093"+telefono
        })
        inquilinos.push({
            "nombre": nombre,
            "apellido1": apellido1,
            "apellido2": apellido2,
            "documento": documento
        })
    }


    for (let id=1; id <= 100; id++) {

        let nombre = faker.name.firstName()
        let apellido1 = faker.name.lastName()
        let apellido2 = faker.name.lastName()
        let documento = faker.random.number()
        let email = faker.internet.email()
        let direccion = faker.address.streetName()
        let telefono = faker.random.number({min:100000, max:999999});
        contactoPropietarios.push({
            "nombre": nombre,
            "apellido": apellido1,
            "email": email,
            "documento": documento,
            "telefono": "093"+telefono
        })
        propietarios.push({
            "nombre": nombre,
            "apellido1": apellido1,
            "apellido2": apellido2,
            "documento": documento,
            "direccion" : direccion
        })

                let libre = false
                let notas = faker.hacker.phrase()
                aptos.push({
                    "documento" : documento,
                    "notas" : notas,
                    "libre" : libre,
                    "nroApto" : id
                })
    }

   for (let id=1; id <= 100; id++) {
        let fechaInicio = faker.date.recent()
        let fechaFinal = null
        let tomaInicial = faker.random.number({min:1129, max:31230});
        let tomaFinal = 0

        consumos.push({
            "tomaInicial":tomaInicial,
            "tomaFinal" : tomaFinal
        })
    }


    return { 
        "usuarios" : usuarios, 
        "contactoUsuarios" : contactoUsuarios,
        "inquilinos" : inquilinos,
        "contactoInquilinos" : contactoInquilinos,
        "propietarios" : propietarios,
        "contactoPropietarios" : contactoPropietarios,
        "notas" : nota,
        "aptos" : aptos,
        "consumos": consumos
    }
}

let dataObj = generateUsers();

fs.writeFileSync('populateBD.json', JSON.stringify(dataObj, null, '\t'));