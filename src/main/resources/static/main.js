function register() {
    const register = document.getElementById('register');
    let url = 'http://localhost:7777/api';

    register.addEventListener('click', async function (event) {

        event.preventDefault();

        let firstname = document.getElementById('firstname').value;
        let lastname = document.getElementById('lastName').value;
        let username = document.getElementById('username').value;
        let age = document.getElementById('age').value;
        let email = document.getElementById('email').value;
        let password = document.getElementById('password').value;
        let roles = [];
        if (document.getElementById('role_user').checked) {
            let array = new Object();
            array.id = 1;
            roles.push(array);
        }
        if (document.getElementById('role_admin').checked) {
            let role_a = new Object();
            role_a.id = 2;
            roles.push(role_a);
        }

        let response = await fetch(url, {
            method: "POST",
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                firstName: firstname,
                lastName: lastname,
                email: email,
                age: age,
                username: username,
                password: password,
                roles: roles
            })
        });
        window.location.reload(true);
        let answer = await response.name;
        console.log(answer);
    });
}

//---------------Delete user-------------
function deleteUser(id) {
    fetch('http://localhost:7777/api/delete/' + id, {
        method: "DELETE"
    })
    window.location.reload(true);
}


//-------------Update user--------------------------------
function updateUser() {

    let al = document.getElementById('edit');
    al.addEventListener('click', function (event) {
        event.preventDefault();
    })
    let id = document.getElementById('id').value;
    let firstname = document.getElementById('edit_firstname').value;
    let lastname = document.getElementById('edit_lastName').value;
    let username = document.getElementById('edit_username').value;
    let age = document.getElementById('edit_age').value;
    let email = document.getElementById('edit_email').value;
    let password = document.getElementById('edit_password').value;


    let roles = [];
    if (document.getElementById('role_user1').checked) {
        let array = new Object();
        array.id = 1;
        roles.push(array);
    }
    if (document.getElementById('role_admin1').checked) {
        let role_a = new Object();
        role_a.id = 2;
        roles.push(role_a);
    }

    fetch('http://localhost:7777/api/'+ id, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: id,
            firstName: firstname,
            lastName: lastname,
            email: email,
            age: age,
            username: username,
            password: password,
            roles: roles
        })

    });
    window.location.reload(true);
}

//--------------findById------------------

function findById(id) {
    fetch('http://localhost:7777/api/find/' + id)
        .then(response => response.json())
        .then(data => {

            let output = `
             <form id="edit" >
                    <h2 class="text-center">${data.firstName} update</h2>
                 <input id="id" type="hidden" value="${data.id}">
                <br> <label for="edit_firstname">Имя</label><br>
                 <input id="edit_firstname" type="text" value="${data.firstName}">
                <br> <label for="edit_lastName">Фамилия</label><br>
                 <input id="edit_lastName" type="text" value="${data.lastName}">
                 <br><label for="edit_username">Ник</label><br>
                 <input id="edit_username" type="text" value="${data.username}"/>
                 <br><label for="edit_age">Возраст</label><br>
                 <input id="edit_age" type="text" value="${data.age}"/>
                <br> <label for="edit_email">Почта</label><br>
                 <input id="edit_email" type="text" value="${data.email}"/>
                <br> <label for="edit_password">Пароль</label><br>
                 <input id="edit_password" type="text" value="${data.password}"/>
                 <br>
                 <label> Роль </label><br>
                 <input type="checkbox" id="role_user1">
                 <label for="role_user1">ROLE_USER</label><br>

                 <input type="checkbox" id="role_admin1">
                 <label for="role_admin1">ROLE_ADMIN</label>
                  <br>
                <button id="noUpdate" onclick="updateUser()" class="btn btn-success">Update</button>
                  <button type="button" class="btn btn-secondary m-2" onclick="cancelForm()">Cancel</button>
       </form>
          `
            let value = document.getElementById('update');
            value.innerHTML = output;
        })
}


//-------GetAllUsers-------------------------------------

let url2 = 'http://localhost:7777/api/getUsers';
fetch(url2)
    .then(response => response.json())
    .then(data => {
        data.forEach(function (user) {
            let mass = [];
            user.roles.map(role => {
                mass.push(role.nameRole);
                console.log(mass);
            })
            let output = `
                    <td id="user-id"> ${user.id}</td>
                    <td> ${user.firstName}</td>
                    <td> ${user.lastName}</td>
                    <td> ${user.username}</td>
                    <td> ${user.age}</td>
                    <td> ${user.password}</td>
                    <td> ${user.email}</td>
                    <td> ${mass} </td>
                    <td>
                        <input   type="button" onclick="findById(${user.id})" value="update" class=""btn btn-info" >
                    </td>
                    <td>
                    <input type="button" onclick="deleteUser(${user.id})" value="delete" class="btn btn-danger">
                    </td>
                 `;

            let value1 = document.getElementById('table-id');
            // let navTitleValue = document.getElementById('navTitle');
            console.log(value1)
            value1.innerHTML += output;
            // navTitleValue.innerHTML = `${user.nameRole.toString()}`;
        })
    })

function cancelForm() {
    window.location.reload(true);

}
