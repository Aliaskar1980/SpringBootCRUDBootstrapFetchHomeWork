let url = 'http://localhost:7777/api/user';
fetch(url)
    .then(response => response.json())
    .then(data => {

        let roleArray = [];
        data.roles.map(role => {
            roleArray.push(role.nameRole);
            console.log(roleArray);
        })
        let obj = `
                    <td>${data.id}</td>
                    <td>${data.firstName}</td>
                    <td>${data.lastName}</td>
                    <td>${data.username}</td>
                    <td>${data.email}</td>
                    <td>${roleArray}</td>
                    `;

        let value1 = document.getElementById('user-page-table');
        console.log(value1);
        value1.innerHTML += obj;
    })
